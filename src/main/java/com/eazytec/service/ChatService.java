package com.eazytec.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.search.FlagTerm;
import javax.inject.Singleton;

import org.cometd.annotation.Configure;
import org.cometd.annotation.Listener;
import org.cometd.annotation.Service;
import org.cometd.annotation.Session;
import org.cometd.bayeux.client.ClientSessionChannel;
import org.cometd.bayeux.server.BayeuxServer;
import org.cometd.bayeux.server.ConfigurableServerChannel;
import org.cometd.bayeux.server.ServerMessage;
import org.cometd.bayeux.server.ServerSession;
import org.cometd.server.authorizer.GrantAuthorizer;
import org.cometd.server.filter.DataFilter;
import org.cometd.server.filter.DataFilterMessageListener;
import org.cometd.server.filter.JSONDataFilter;
import org.cometd.server.filter.NoMarkupFilter;
import org.springframework.beans.factory.annotation.Autowired;

import com.eazytec.bpm.runtime.task.service.TaskService;
import com.eazytec.common.util.CommonUtil;

import dtw.webmail.JwmaSession;
import dtw.webmail.model.JwmaFolderImpl;

@Named
@Singleton
@Service("chat")
public class ChatService {

    private final ConcurrentMap<String, Map<String, String>> _members = new ConcurrentHashMap<String, Map<String, String>>();

    private final Set<Map<String,Object>> membersList = new HashSet<Map<String,Object>>();
    
    private TaskService taskService;
    
    @Autowired
    public void setTaskService(TaskService taskService){
    	this.taskService = taskService;
    }
    
    @Inject
    private BayeuxServer _bayeux;

    @Session
    private ServerSession _session;

    @PostConstruct
    public void init()
    {
    }

    @Configure ({"/chat/**","/members/**","/count/**","/countRoom/**"})
    protected void configureChatStarStar(ConfigurableServerChannel channel) {
        DataFilterMessageListener noMarkup = new DataFilterMessageListener(new NoMarkupFilter(),new BadWordFilter());

        channel.addListener(noMarkup);

        channel.addAuthorizer(GrantAuthorizer.GRANT_ALL);

    }

    

    @Configure ("/service/members")
    protected void configureMembers(ConfigurableServerChannel channel) {
	
        channel.addAuthorizer(GrantAuthorizer.GRANT_PUBLISH);

        channel.setPersistent(true);

    }
 
    public int getUnreadMailCount(Folder folder){
		int startMailCount = 1;
		int unReadMailCount = 0;
		try{ 
			if(folder.getMessages().length >= 20){
				startMailCount = folder.getMessages().length - 20;
			}
			Message messages[] = folder.getMessages(startMailCount, folder.getMessages().length);
			Message inboxMessages[] = folder.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false), messages);
			unReadMailCount = inboxMessages.length;
		} catch(Exception e){
		}
		return unReadMailCount;
	}

    @Listener("/service/members")
    public void handleMembership(ServerSession client, ServerMessage message) {
        Map<String, Object> data = message.getDataAsMap();
        boolean isDublicate = false;
        final String room = ((String)data.get("room")).substring("/chat/".length());

        Map<String, String> roomMembers = _members.get(room);

        if (roomMembers == null) {

            Map<String, String> new_room = new ConcurrentHashMap<String, String>();

            roomMembers = _members.putIfAbsent(room, new_room);
            if (roomMembers == null) roomMembers = new_room;

        }

        final Map<String, String> members = roomMembers;

        String userName = (String)data.get("user");
        //String userFullName = (String)data.get("userFullName");

        members.put(userName, client.getId());
        
        for(Map<String, Object> member : membersList){
        	if(member.get("user") != null){
	    		if(member.get("user").equals(data.get("user"))){
	    			isDublicate = true;
	    		}
        	}
    	}
        if(!isDublicate){
             data.put("sessionId", client.getId());
             membersList.add(data);
        }
        
        client.addListener(new ServerSession.RemoveListener() {

            public void removed(ServerSession session, boolean timeout) {

                members.values().remove(session.getId());

                for(Map<String, Object> member : membersList){
            		if(member.get("sessionId").equals(session.getId())){
            			membersList.remove(member);
            		}
            	}
               
                broadcastMembers(room, membersList);

            }
        });

        broadcastMembers(room, membersList);

    }
    int oldCount=0;
    @Listener("/count/getCount")
    public void handleCount(ServerSession client, ServerMessage message) {
    	Map<String, Object> data = message.getDataAsMap();
    	String userName = (String) data.get("user");
    	final String room = "demo";
    	boolean isNewMail = false;
    	JwmaSession jwmaSession = null;
		int unreadInternalMessageCount = 0;
		int unreadEmailCount = 0;
		try {
			Object o = _bayeux.getContext().getHttpSessionAttribute("jwma.internalMessageSession"); 
		    jwmaSession = ((o != null)? ((JwmaSession) o) : null );
		    //session = checkMailAuthentication(req, res, "internalMessage");
			if (jwmaSession !=null && jwmaSession.isAuthenticated()) {
				JwmaFolderImpl jwmaFolder = jwmaSession.getJwmaStore().getInboxFolder();
		    	 if( jwmaFolder == null){
		    		 jwmaSession.getJwmaStore().prepareInboxFolder();
		  	    	 jwmaFolder=jwmaSession.getJwmaStore().getInboxFolder();
		  	     } 
		    	 Folder folder = jwmaFolder.getFolder();
		    	 if(folder != null){
		    		 if(!folder.isOpen()){
		    			 folder.open(Folder.READ_ONLY);
		    		 }
		    		 unreadInternalMessageCount = getUnreadMailCount(folder);// session.getJwmaStore().getInboxFolder().getInboxUnReadMessageCount();
		    	 } else {
		    		 unreadInternalMessageCount = jwmaSession.getJwmaStore().getInboxFolder().getInboxUnReadMessageCount();
		    	 }
				
			}
		} catch(Exception e){
			
		}
		try{
			Object o = _bayeux.getContext().getHttpSessionAttribute("jwma.emailSession");
			jwmaSession = ((o != null)? ((JwmaSession) o) : null );
			//session = checkMailAuthentication(req, res, "email");
		//	if(session !=null && session.isValidWebSession()
				//	&& session.isAuthenticated() && session.getJwmaStore().getInboxFolder() !=null){
			if(jwmaSession !=null && jwmaSession.isAuthenticated()){
				 JwmaFolderImpl jwmaFolder = jwmaSession.getJwmaStore().getInboxFolder();
		    	 if( jwmaFolder == null){
		    		 jwmaSession.getJwmaStore().prepareInboxFolder();
		  	    	 jwmaFolder=jwmaSession.getJwmaStore().getInboxFolder();
		  	     } 
		    	 Folder folder = jwmaFolder.getFolder();
		    	 if(folder != null){
		    		 if(!folder.isOpen()){
		    			 folder.open(Folder.READ_ONLY);
		    		 }
		    		 unreadEmailCount = getUnreadMailCount(folder);
		    	 } else {
		    		 unreadEmailCount = jwmaSession.getJwmaStore().getInboxFolder().getInboxUnReadMessageCount() + (jwmaSession.getJwmaStore().getInboxFolder().getMessageCount() - jwmaSession.getJwmaStore().getInboxFolder().getOldMessageCount()) ;
		    	 }
		    	/* if(oldCount != 0 && oldCount < jwmaSession.getJwmaStore().getInboxFolder().getMessageCount()){
				    //System.out.println("====oldcount=="+oldCount+"===messsahe======"+jwmaSession.getJwmaStore().getInboxFolder().getMessageCount()+"====unread========="+unreadEmailCount);
		    		 isNewMail = true;
		    	 }*/
		    	 oldCount = jwmaSession.getJwmaStore().getInboxFolder().getMessageCount();
			}
		} catch(Exception e){
			
		}
		Map<String,Object> mailCount = new HashMap<String,Object>();
		mailCount.put("mailCount", unreadEmailCount);
		mailCount.put("messageCount", unreadInternalMessageCount);
		mailCount.put("isNewMail", isNewMail);
		Map<String ,Map<String,Object>> countMap = new HashMap<String ,Map<String,Object>>();
		try{
			//Map<String,Object> taskCountMap = new HashMap<String,Object>();
			//taskCountMap = taskService.getTaskCountByUserId(CommonUtil.getLoggedInUser());
			mailCount.put("toDoListCount", taskService.getTaskCountByUserId(CommonUtil.getLoggedInUser()).get("toDoListCount"));
			mailCount.put("toReadCount", taskService.getTaskCountByUserId(CommonUtil.getLoggedInUser()).get("toReadCount"));
		} catch(Exception e){
			//Nothing to handle here
		}
		countMap.put(userName, mailCount);
		broadcastMailCount(room,countMap);
		
    }

    private void broadcastMailCount(String room,Map<String ,Map<String,Object>> countMap){
    	 ClientSessionChannel channel = _session.getLocalSession().getChannel("/countRoom/"+room);
         channel.publish(countMap);
    }
    
    private void broadcastMembers(String room, Set<Map<String, Object>> members) {

        // Broadcast the new members list

        ClientSessionChannel channel = _session.getLocalSession().getChannel("/members/"+room);
        channel.publish(members);

    }
 

    @Configure ("/service/privatechat")
    protected void configurePrivateChat(ConfigurableServerChannel channel) {

        DataFilterMessageListener noMarkup = new DataFilterMessageListener(new NoMarkupFilter(),new BadWordFilter());

        channel.setPersistent(true);

        channel.addListener(noMarkup);

        channel.addAuthorizer(GrantAuthorizer.GRANT_PUBLISH);

    }

 

    @Listener("/service/privatechat")
    public void privateChat(ServerSession client, ServerMessage message) {

        Map<String,Object> data = message.getDataAsMap();
        message.put("userFullName", data.get("userFullName"));
        message.put("userEmailId", data.get("userEmailId"));
        
        String room = ((String)data.get("room")).substring("/chat/".length());

        Map<String, String> membersMap = _members.get(room);

        if (membersMap == null) {

            Map<String,String>new_room=new ConcurrentHashMap<String, String>();

            membersMap=_members.putIfAbsent(room,new_room);

            if (membersMap==null)

                membersMap=new_room;

        }

         

        String peerName = (String)data.get("peer");

        String peerId = membersMap.get(peerName);

         

        if (peerId != null) {

             

         ServerSession peer = _bayeux.getSession(peerId);

             

            if (peer != null) {
             Map<String, Object> chat = new HashMap<String, Object>();

                String text = (String)data.get("chat");

                chat.put("chat", text);

                chat.put("user", data.get("user"));
                
                chat.put("userFullName", data.get("userFullName"));
                
                chat.put("userEmailId", data.get("userEmailId"));
                
                chat.put("loginUserImage", data.get("loginUserImage"));
                
                chat.put("loginUserPosition", data.get("loginUserPosition"));
                
                chat.put("scope", "private");

                chat.put("peer", peerName);
                
                chat.put("peerFullName", data.get("peerFullName"));
                
                chat.put("peerEmailId", data.get("peerEmailId"));
                
                chat.put("peerUserImage", data.get("peerUserImage"));
                
                chat.put("peerUserPosition", data.get("peerUserPosition"));

                ServerMessage.Mutable forward = _bayeux.newMessage();

                forward.setChannel("/chat/" + room);

                forward.setId(message.getId());

                forward.setData(chat);
 

                if (text.lastIndexOf("lazy") > 0) {

                    forward.setLazy(true);

                }

                if (peer != client) {

                    peer.deliver(_session, forward);

                }

                client.deliver(_session, forward);

            }

        }
    }

 

    class BadWordFilter extends JSONDataFilter {

        @Override
        protected Object filterString(String string) {

            if (string.indexOf("dang") >= 0) {

                throw new DataFilter.Abort();

            }

            return string;

        }
    }

}