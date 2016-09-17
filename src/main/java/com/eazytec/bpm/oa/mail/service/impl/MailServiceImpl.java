package com.eazytec.bpm.oa.mail.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;

import javax.jws.WebService;
import javax.mail.Authenticator;
import javax.mail.FetchProfile;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.search.FlagTerm;
import javax.mail.Store;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eazytec.bpm.common.util.StringUtil;
import com.eazytec.bpm.oa.mail.dao.MailDao;
import com.eazytec.bpm.oa.mail.service.MailService;
import com.eazytec.bpm.runtime.task.service.TaskService;
import com.eazytec.bpm.oa.mail.thread.EmailAuthenticationThread;
import com.eazytec.bpm.runtime.listView.service.ListViewService;
import com.eazytec.common.util.CipherUtils;
import com.eazytec.common.util.CommonUtil;
import com.eazytec.exceptions.BpmException;
import com.eazytec.exceptions.EazyBpmException;
import com.eazytec.model.EmailConfiguration;
import com.eazytec.model.EmailContact;
import com.eazytec.model.InternalMessage;
import com.eazytec.model.User;
import com.eazytec.service.MailEngine;
import com.eazytec.service.UserManager;
import com.eazytec.service.impl.GenericManagerImpl;
import com.eazytec.util.DateUtil;
import com.eazytec.util.PropertyReader;

import dtw.webmail.JwmaKernel;
import dtw.webmail.JwmaSession;
import dtw.webmail.model.JwmaException;
import dtw.webmail.model.JwmaFolderImpl;
import dtw.webmail.model.JwmaStoreImpl;
import dtw.webmail.model.JwmaStoreInfo;
import dtw.webmail.util.config.JwmaConfiguration;

/**
 * Implementation of mailing interface.
 *
 * @author mathi
 */

@Service("mailService")
@WebService(serviceName = "MailService", endpointInterface = "com.eazytec.bpm.oa.mail.service.MailService")
public class MailServiceImpl extends GenericManagerImpl<EmailContact, String> implements MailService{
	private MailDao mailDao;
	private UserManager userManager;
	private ListViewService listViewService;
	private MailEngine mailEngine;
	private TaskService taskService;
	
	private int inboxMailOldCount = 0;
	private int draftMailOldCount = 0;
	private int sentMailOldCount = 0;
	private Message[] inboxMailOldMessages;
	private Message[] draftMailOldMessages;
	private Message[] sentMailOldMessages;
	
    @Autowired
    public void setMailDao(MailDao mailDao) {
        this.dao = mailDao;
        this.mailDao = mailDao;
    }

    @Autowired
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }
    
    @Autowired
    public void setListViewService(ListViewService listViewService) {
        this.listViewService = listViewService;
    }
    
    @Autowired
	public void setMailEngine(MailEngine mailEngine) {
		this.mailEngine = mailEngine;
	}
    
    @Autowired
    public void setTaskService(TaskService taskService){
    	this.taskService = taskService;
    }
    /**
     * {@inheritDoc}
     */
    public EmailContact saveEmailContact(EmailContact contact){
    	return mailDao.save(contact);
    }
    
    /**
     * {@inheritDoc}
     */
    public List<EmailContact> getUserContacts(String user){
    	return mailDao.getUserContacts(user);
    }
    
    /**
     * {@inheritDoc}
     */
    public void getEmailAuthentication(HttpSession websession, User user){
    	log.info("getting email authentication "+user.getFullName());
		Thread mailAuthenticationThread = new Thread(new EmailAuthenticationThread(websession, user));
		mailAuthenticationThread.start();
	}
    
    /**
     * {@inheritDoc}
     */
	public EmailConfiguration getEmailConfig(){
		return mailDao.getEmailConfig();
	}
	
    /**
     * {@inheritDoc}
     */
    public EmailConfiguration saveEmailConfig(EmailConfiguration emailConfig){
		return mailDao.saveEmailConfig(emailConfig);
	}
    
    /**
     * {@inheritDoc}
     */
    public User saveEmailSetting(User user, HttpServletRequest request, HttpServletResponse response){
    	return userManager.saveEmailSetting(user, request, response);
    }
    
    /**
     * {@inheritDoc}
     */
    public void removeContacts(List<String> contactIds){
    	List<EmailContact> contactList=mailDao.getContactsByIds(contactIds);
    	for (EmailContact contact : contactList) {
			mailDao.remove(contact);
		}
    }
	
    /**
     * {@inheritDoc}
     */
	public JwmaSession checkMailAuthentication(HttpServletRequest req, HttpServletResponse res, String sessionType){
	      HttpSession websession = req.getSession(true);
	      JwmaSession session = null;
	      if(sessionType.equalsIgnoreCase("email")){
	    	  Object o = websession.getValue("jwma.emailSession");
		      session = ((o == null)? new JwmaSession(websession, "email"):((JwmaSession) o));
	      } else {
	    	  Object o = websession.getValue("jwma.internalMessageSession");
		      session = ((o == null)? new JwmaSession(websession, "internalMessage"):((JwmaSession) o));
	      }
	      session.setRequest(req);
	      session.setResponse(res);
	      session.setWebSession(websession);
	      return session;
	}
	
    /**
     * {@inheritDoc}
     */
	public JwmaSession getInternalMessageAuthentication(HttpServletRequest req, HttpServletResponse res, User user){
		HttpSession websession = req.getSession(true);
		Object o = websession.getValue("jwma.internalMessageSession");
		//update session information
		JwmaSession session = ((o == null)? new JwmaSession(websession, "internalMessage"):((JwmaSession) o));
		session.setRequest(req);
		session.setResponse(res);
		session.setWebSession(websession);
		return getAuthentication(session, CommonUtil.getInternalMessageId(user.getUsername()), user.getUsername());
	}
	
	/**
     * {@inheritDoc}
     */
	public JwmaSession getEmailAuthentication(HttpServletRequest req, HttpServletResponse res, User user, boolean isMailSettings){
		HttpSession websession = req.getSession(true);
		Object o = websession.getValue("jwma.emailSession");
		//update session information
		JwmaSession session = ((o == null)? new JwmaSession(websession, "email"):((JwmaSession) o));
		if(isMailSettings){
			if(session != null && session.isAuthenticated()){
				session.endMailSession();
			}
		}
		
		session.setRequest(req);
		session.setResponse(res);
		session.setWebSession(websession);
		String password = "";
		if(!StringUtil.isEmptyString(user.getEmailPassword())){
			String currentEmailPassword = userManager.getUserEmailPassword(user.getId());
			if(currentEmailPassword.equals(user.getEmailPassword())){
				password =CipherUtils.decrypt(user.getEmailPassword());
			} else {
				password = user.getEmailPassword();
			}
		} 
		return getAuthentication(session, user.getEmail(), password);
	}
	
	/**
	 * get authentication for mail account
	 * 
	 * @param session
	 * @param username
	 * @param password
	 */
	public JwmaSession getAuthentication(JwmaSession session, String username, String password){
		log.info("getting authentication of "+username);
		try{
			boolean newAccount = true;
		    String postoffice = null;
		    JwmaKernel kernel = JwmaKernel.getReference();
			JwmaConfiguration config = kernel.getConfiguration();
			session.setPostOffice(config.getPostOfficeByName(postoffice));
			System.out.println("Mail Setting Save == "+username+" Password == "+password);
			session.authenticate(username, password, newAccount);
			JwmaStoreImpl store = session.getJwmaStore();
			session.storeBean("jwma.storeinfo", (JwmaStoreInfo) store);
			session.storeBean("jwma.inboxinfo", store.getInboxInfo());
			session.storeBean("jwma.trashinfo", store.getTrashInfo());
			session.storeBean("jwma.folder", store.getActualFolder());
			session.getJwmaStore().prepareRootFolder();
			session.getJwmaStore().prepareTrashFolder();
			session.getJwmaStore().prepareSentLaterFolder();
			return session;
		} catch (Exception e) {
			 if(e.getMessage().equals("session.login.authentication")){
				 return session;
			 } else {
				 throw new BpmException(e.getMessage(),e);
			 }
		}
	}
    
    /**
     * {@inheritDoc}
     */
	 public String getInboxMailGridScript(JwmaSession session, String sessionType) throws JwmaException, MessagingException{
		 String script = "";
		 int startcount = 1;
		 Message[] msgs;
		 int emailLimit = Integer.parseInt(PropertyReader.getInstance()
					.getPropertyFromFile("Integer", "system.email.limit"));
		 JwmaFolderImpl jwmaFolder = session.getJwmaStore().getInboxFolder();
    	 if( jwmaFolder == null){
    		 session.getJwmaStore().prepareInboxFolder();
  	    	 jwmaFolder=session.getJwmaStore().getInboxFolder();
  	     } 
    	 Folder folder = jwmaFolder.getFolder();
    	 try {
    		 //for listing only
    		 if (!folder.isOpen()) {
    			 folder.open(Folder.READ_ONLY);
    		 }
    		 int messageCount = folder.getMessages().length;
    		 if(messageCount > emailLimit){
    			 startcount = messageCount - emailLimit;
    		 }
    		 if(inboxMailOldCount != messageCount || messageCount == 0){
    			 msgs = folder.getMessages(startcount, folder.getMessages().length);
    			 //fetch messages with a slim profile
        		 FetchProfile fp = new FetchProfile();
        		 fp.add(FetchProfile.Item.ENVELOPE);		//contains the headers
        		 fp.add(FetchProfile.Item.FLAGS);
        		 fp.add(FetchProfile.Item.CONTENT_INFO);//contains the flags
        		 folder.fetch(msgs, fp);
    			 inboxMailOldMessages = msgs;
    		 } else {
    			 msgs = inboxMailOldMessages;
    		 }
    		 script=listViewService.getListViewForInboxMail(msgs, sessionType);
    	 } catch (Exception ex) {
	         throw new JwmaException("jwma.messagelist.failedcreation");
  	   	 } finally {
  	   		 try {
	         //close the folder
  	   			if (folder.isOpen()) {
  	   				folder.close(false);
  	   			}
  	   		 } catch (MessagingException mesx) {
  	   			//don't care, the specs say it IS closed anyway
  	   		 }
  	   	}
	 return script;
	 }
	 
	/**
     * {@inheritDoc}
     */
	 public String getDraftMailGridScript(JwmaSession session, String sessionType) throws JwmaException, MessagingException{
		 String script = "";
		 int startcount = 1;
		 Message[] msgs;
		 int emailLimit = Integer.parseInt(PropertyReader.getInstance()
					.getPropertyFromFile("Integer", "system.email.limit"));
		 JwmaFolderImpl jwmaFolder = session.getJwmaStore().getInboxDraftFolder();
    	 if( jwmaFolder == null){
    		session.getJwmaStore().prepareDraftFolder();
    		jwmaFolder=session.getJwmaStore().getInboxDraftFolder();
  	     } 
  	     Folder folder = jwmaFolder.getFolder();
  	     try {
  	    	 //for listing only
  	    	 if (!folder.isOpen()) {
  	    		 folder.open(Folder.READ_ONLY);
  	    	 }
  	    	 int messageCount = folder.getMessages().length;
  	    	 if(messageCount > emailLimit){
  	    		 startcount = messageCount - emailLimit;
  	    	 }
  	    	 if(draftMailOldCount != messageCount || messageCount == 0){
  	    		 msgs = folder.getMessages(startcount, folder.getMessages().length);
  	    		 //fetch messages with a slim profile
  	    		 FetchProfile fp = new FetchProfile();
  	    		 fp.add(FetchProfile.Item.ENVELOPE);		//contains the headers
  	    		 fp.add(FetchProfile.Item.FLAGS); //contains the flags
  	    		 fp.add(FetchProfile.Item.CONTENT_INFO);//contains the content info
  	    		 folder.fetch(msgs, fp);
  	    		 draftMailOldMessages = msgs;
  	    	 } else {
  	    		 msgs = draftMailOldMessages;
  	    	 }
  	    	 script=listViewService.getListViewForDraftMail(msgs, sessionType);
  	     } catch (Exception ex) {
  	    	 throw new JwmaException("jwma.messagelist.failedcreation");
  	     } finally {
  	    	 try {
  	    		 //close the folder
  	    		 if (folder.isOpen()) {
  	    			 folder.close(false);
  	    		 }
  	    	 } catch (MessagingException mesx) {
  	    		 //don't care, the specs say it IS closed anyway
  	    	 }
  	     }
	 return script;
	 }
	 
	 /**
      * {@inheritDoc}
      */
	 public String getSentMailGridScript(JwmaSession session, String sessionType) throws JwmaException, MessagingException{
		 String script = "";
		 int startcount = 1;
		 Message[] msgs;
		 int emailLimit = Integer.parseInt(PropertyReader.getInstance()
					.getPropertyFromFile("Integer", "system.email.limit"));
		 JwmaFolderImpl jwmaFolder=session.getJwmaStore().getSentFolder();
		 if( jwmaFolder == null){
			 session.getJwmaStore().prepareSentFolder();
			 jwmaFolder=session.getJwmaStore().getSentFolder();
  	     } 
		 Folder folder = jwmaFolder.getFolder();
		 try {
  	    	 //for listing only
  	    	 if (!folder.isOpen()) {
  	    		 folder.open(Folder.READ_ONLY);
  	    	 }
  	    	 int messageCount = folder.getMessages().length;
  	    	 if(messageCount > emailLimit){
  	    		 startcount = messageCount - emailLimit;
  	    	 }
  	    	 if(sentMailOldCount != messageCount || messageCount == 0){
  	    		 msgs = folder.getMessages(startcount, folder.getMessages().length);
  	    		 //fetch messages with a slim profile
  	    		 FetchProfile fp = new FetchProfile();
  	    		 fp.add(FetchProfile.Item.ENVELOPE);		//contains the headers
  	    		 fp.add(FetchProfile.Item.FLAGS);
  	    		 fp.add(FetchProfile.Item.CONTENT_INFO);//contains the flags
  	    		 folder.fetch(msgs, fp);
  	    		 sentMailOldMessages = msgs;
  	    	 } else {
  	    		 msgs = sentMailOldMessages;
  	    	 }
  	    	 script=listViewService.getListViewForSentMail(msgs, sessionType);
  	     } catch (Exception ex) {
  	    	 throw new JwmaException("jwma.messagelist.failedcreation");
  	     } finally {
  	    	 try {
  	    		 //close the folder
  	    		 if (folder.isOpen()) {
  	    			 folder.close(false);
  	    		 }
  	    	 } catch (MessagingException mesx) {
  	    		 //don't care, the specs say it IS closed anyway
  	    	 }
  	     }
	 return script;
	 }

	 /**
      * {@inheritDoc}
      */
	 public String getSentLaterMessageGridScript(JwmaSession session, String sessionType) throws JwmaException, MessagingException{
		 String script = "";
		 int startcount = 1;
		 Message[] msgs;
		 int emailLimit = Integer.parseInt(PropertyReader.getInstance()
					.getPropertyFromFile("Integer", "system.email.limit"));
		 JwmaFolderImpl jwmaFolder=session.getJwmaStore().getSentLaterFolder();
		 if( jwmaFolder == null){
			 session.getJwmaStore().prepareSentLaterFolder();
			 jwmaFolder=session.getJwmaStore().getSentLaterFolder();
  	     } 
		 Folder folder = jwmaFolder.getFolder();
		 try {
  	    	 //for listing only
  	    	 if (!folder.isOpen()) {
  	    		 folder.open(Folder.READ_ONLY);
  	    	 }
  	    	 int messageCount = folder.getMessages().length;
  	    	 if(messageCount > emailLimit){
  	    		 startcount = messageCount - emailLimit;
  	    	 }
  	    	 if(sentMailOldCount != messageCount || messageCount == 0){
  	    		 msgs = folder.getMessages(startcount, folder.getMessages().length);
  	    		 //fetch messages with a slim profile
  	    		 FetchProfile fp = new FetchProfile();
  	    		 fp.add(FetchProfile.Item.ENVELOPE);		//contains the headers
  	    		 fp.add(FetchProfile.Item.FLAGS);
  	    		 fp.add(FetchProfile.Item.CONTENT_INFO);//contains the flags
  	    		 folder.fetch(msgs, fp);
  	    		 sentMailOldMessages = msgs;
  	    	 } else {
  	    		 msgs = sentMailOldMessages;
  	    	 }
  	    	 script=listViewService.getListViewForSentLaterMessage(msgs, sessionType);
  	     } catch (Exception ex) {
  	    	 throw new JwmaException("jwma.messagelist.failedcreation");
  	     } finally {
  	    	 try {
  	    		 //close the folder
  	    		 if (folder.isOpen()) {
  	    			 folder.close(false);
  	    		 }
  	    	 } catch (MessagingException mesx) {
  	    		 //don't care, the specs say it IS closed anyway
  	    	 }
  	     }
	 return script;
	 }
	 
	 /**
      * {@inheritDoc}
      */
	public Map<String, Object> checkRecentReceivedMail(HttpServletRequest req,	HttpServletResponse res) {
		
		Map<String, Object> mailCount = new HashMap<String, Object>();
		JwmaSession session = null;
		HttpSession websession = req.getSession(true);
		int unreadEmailCount = 0;
		int unreadInternalMessageCount = 0;
		User loggedInUser = CommonUtil.getLoggedInUser();
		//try {
			/*if (session == null || !session.isValidWebSession()
					|| !session.isAuthenticated()) {
				session = getInternalMessageAuthentication(req, res, loggedInUser);
			}
			if (null == session.getJwmaStore().getInboxFolder()) {
				
					session.getJwmaStore().prepareInboxFolder();
			}*/
			
		  /*  Object o = websession.getValue("jwma.internalMessageSession");
			session = ((o == null)? new JwmaSession(websession, "internalMessage"):((JwmaSession) o));
		    //session = checkMailAuthentication(req, res, "internalMessage");
			if (session !=null && session.isAuthenticated()) {
				 JwmaFolderImpl jwmaFolder = session.getJwmaStore().getInboxFolder();
		    	 if( jwmaFolder == null){
		    		 session.getJwmaStore().prepareInboxFolder();
		  	    	 jwmaFolder=session.getJwmaStore().getInboxFolder();
		  	     } 
		    	 Folder folder = jwmaFolder.getFolder();
		    	 if(folder != null){
		    		 if(!folder.isOpen()){
		    			 folder.open(Folder.READ_ONLY);
		    		 }
		    		 unreadInternalMessageCount = getUnreadMailCount(folder);// session.getJwmaStore().getInboxFolder().getInboxUnReadMessageCount();
		    	 } else {
		    		 unreadInternalMessageCount = session.getJwmaStore().getInboxFolder().getInboxUnReadMessageCount();
		    	 }
				
			}
		}catch(Exception e){
			// nothing handling here...
		}
		try{	
			/*if (session == null || !session.isValidWebSession()
					|| !session.isAuthenticated()) {
				session = getEmailAuthentication(req, res, loggedInUser);
			}
			if (null == session.getJwmaStore().getInboxFolder()) {
					session.getJwmaStore().prepareInboxFolder();
			}*/
		/*	Object o = websession.getValue("jwma.emailSession");
		    session = ((o == null)? new JwmaSession(websession, "email"):((JwmaSession) o));
			//session = checkMailAuthentication(req, res, "email");
		//	if(session !=null && session.isValidWebSession()
				//	&& session.isAuthenticated() && session.getJwmaStore().getInboxFolder() !=null){
			if(session !=null && session.isAuthenticated()){
				 JwmaFolderImpl jwmaFolder = session.getJwmaStore().getInboxFolder();
		    	 if( jwmaFolder == null){
		    		 session.getJwmaStore().prepareInboxFolder();
		  	    	 jwmaFolder=session.getJwmaStore().getInboxFolder();
		  	     } 
		    	 Folder folder = jwmaFolder.getFolder();
		    	 if(folder != null){
		    		 if(!folder.isOpen()){
		    			 folder.open(Folder.READ_ONLY);
		    		 }
		    		 unreadEmailCount = getUnreadMailCount(folder);
		    	 } else {
		    		 unreadEmailCount = session.getJwmaStore().getInboxFolder().getInboxUnReadMessageCount() + (session.getJwmaStore().getInboxFolder().getMessageCount() - session.getJwmaStore().getInboxFolder().getOldMessageCount()) ;
		    	 }
				
			}
		} catch (Exception e) {
			// nothing handling here...
		}*/
		int totalCount = unreadEmailCount + unreadInternalMessageCount;
		mailCount.put("unreadEmailCount", unreadEmailCount);
		mailCount.put("unreadMessageCount", unreadInternalMessageCount);
		mailCount.put("totalCount",totalCount);
		mailCount.put("user", loggedInUser.getUsername());
		Map<String,Object> taskCountMap = new HashMap<String,Object>();
		try{
			taskCountMap = taskService.getTaskCountByUserId(CommonUtil.getLoggedInUser());
			mailCount.put("toDoListCount", taskCountMap.get("toDoListCount"));
			mailCount.put("toReadCount", taskCountMap.get("toReadCount"));
		} catch(Exception e){
			//Nothing to handle here
		}
		
		return mailCount;
	}
	
	/**
	 * get the folder unread mail count
	 * 
	 * @param folder
	 * @return
	 */
	public int getUnreadMailCount(Folder folder){
		int startMailCount = 1;
		int unReadMailCount = 0;
		try{ 
			if(folder.getMessages().length > 20){
				startMailCount = folder.getMessages().length - 20;
			}
			Message messages[] = folder.getMessages(startMailCount, folder.getMessages().length);
			Message inboxMessages[] = folder.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false), messages);
			unReadMailCount = inboxMessages.length;
		} catch(Exception e){
		}
		return unReadMailCount;
	}
	
	/**
     * {@inheritDoc}
     */
	public boolean sendInternalMessages(String messageSendDate){
		try{
			List<InternalMessage> internalMessages = mailDao.getInternalMessages(messageSendDate);
			if(!internalMessages.isEmpty()){
				for(InternalMessage internalMessage : internalMessages){
					Map<String, Object> resultMap = new HashMap<String, Object>();
					Store store = getInternalMessageStore(CommonUtil.getInternalMessageId(internalMessage.getFromUserId()), internalMessage.getFromUserId());
					Message message = getMessage(store, internalMessage.getMessageNumber());
					resultMap = mailEngine.sendInternalMessage((MimeMessage)message, internalMessage.getFromUserId());
					if(!resultMap.isEmpty()){
					    if(new Boolean(resultMap.get("sent").toString())){
					    	if(addSentMessage(store, message)){
					    		deleteSentLaterMessage(store, internalMessage.getMessageNumber());
					    	}
					    	internalMessage.setStatus("sent");
					    	internalMessage.setStatusMessage(resultMap.get("successMessage").toString());
					    } else {
					    	internalMessage.setStatus("failed");
					    	internalMessage.setStatusMessage(resultMap.get("errorMessage").toString());
					    }
					    internalMessage = mailDao.saveInternalMessage(internalMessage);
				    }
				}
			}
			return true;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			return false;
		}
	}
	
	/**
	 * get internal message store
	 * 
	 * @param userName
	 * @param password
	 * @return
	 * @throws MessagingException
	 */
	public Store getInternalMessageStore(final String userName, final String password) throws MessagingException{
		Properties props = new Properties();
		props.setProperty("mail.imap.host",CommonUtil.getInternalMessageIMAPHost());
    	props.setProperty("mail.imap.socketFactory.port", ""+CommonUtil.getInternalMessageIMAPPort());
    	props.setProperty("mail.imap.port", ""+CommonUtil.getInternalMessageIMAPPort());
		if(CommonUtil.getInternalMessageSSL()){
			props.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.setProperty("mail.imap.starttls.enable", "true");
		} else {
			props.setProperty("mail.imap.socketFactory.class", "");
			props.setProperty("mail.imap.starttls.enable", "false");
		}
		
		Session mailSession = Session.getDefaultInstance(props, new Authenticator() {
		    protected PasswordAuthentication getPasswordAuthentication() {
		      return new PasswordAuthentication(userName, password);
		    }
		  });
      Store store = mailSession.getStore(CommonUtil.getInternalMessageIMAPProtocol());
      store.connect(CommonUtil.getInternalMessageIMAPHost(), CommonUtil.getInternalMessageIMAPPort(), userName, password);
      return store;
	}
	
	/**
	 * get internal message to send
	 * 
	 * @param store
	 * @param msgNumber
	 * @return
	 * @throws MessagingException
	 */
	public Message getMessage(Store store, int msgNumber) throws MessagingException{
		Message message = null;
		Folder sentLaterFolder = null;
		try{
			sentLaterFolder = store.getFolder("INBOX.sent-later");
			if(!sentLaterFolder.isOpen()){
				sentLaterFolder.open(Folder.READ_WRITE);
			}
			message = sentLaterFolder.getMessage(msgNumber);
			message = new MimeMessage((MimeMessage) message); 
			return message;
		} catch (Exception e){
			log.error(e.getMessage(), e);
			return message;
		} finally {
			if(!sentLaterFolder.isOpen()){
		    	  sentLaterFolder.close(true);
		      }
		}
	}
	
	/**
	 * add message to sent message folder after successfully sent.
	 *  
	 * @param store
	 * @param message
	 * @return
	 * @throws MessagingException
	 */
	public boolean addSentMessage(Store store, Message message) throws MessagingException{
		Folder sentMailFolder = null;
		try{
			sentMailFolder = store.getFolder("INBOX.sent-mail");
			if(sentMailFolder == null || !sentMailFolder.exists()){
				sentMailFolder.create(Folder.HOLDS_MESSAGES);
			}
			if(!sentMailFolder.isOpen()){
				sentMailFolder.open(Folder.READ_WRITE);
			}
			message.setSentDate(new Date());
			Message[] tosave = {message};
			sentMailFolder.appendMessages(tosave);
		} catch (Exception e){
			log.error(e.getMessage(), e);
			return false;
		} finally {
			if(!sentMailFolder.isOpen()){
				sentMailFolder.close(true);
		      }
		}
		return true;
	}
	
	/**
	 * delete message from sent later folder after successfully sent.
	 * 
	 * @param store
	 * @param msgNumber
	 * @return
	 * @throws MessagingException
	 */
	public boolean deleteSentLaterMessage(Store store, int msgNumber) throws MessagingException{
		Folder sentLaterFolder = null;
		int[] msgNums = {msgNumber};
		try{
			sentLaterFolder = store.getFolder("INBOX.sent-later");
			if(!sentLaterFolder.isOpen()){
				sentLaterFolder.open(Folder.READ_WRITE);
			}
			sentLaterFolder.setFlags(msgNums, new Flags(Flags.Flag.DELETED), true);
			return true;
		} catch (Exception e){
			log.error(e.getMessage(), e);
			return false;
		} finally {
			if(!sentLaterFolder.isOpen()){
		    	  sentLaterFolder.close(true);
		      }
		}
	}
	
	/**
     * {@inheritDoc}
     */
	public void saveOrUpdateInternalMessage(String from, String to, String cc, String bcc, String subject, String body, int messageNumber, String sendTime, String timeZoneOffset) throws EazyBpmException{
		Date sendDate = new Date();
		try {
			log.info("sendTime------------"+sendTime);
    		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    		sendDate = df.parse(sendTime);
    		System.out.println("----offset"+sendDate.getMinutes()+"---"+Integer.parseInt(timeZoneOffset));
    		sendDate.setMinutes(sendDate.getMinutes()+Integer.parseInt(timeZoneOffset));
    		log.info("sendDate------------"+sendDate);
    		sendTime = DateUtil.convertDateToEazytecFormat(sendDate);
    		log.info("sendTime------------"+sendTime);
    		sendDate = df.parse(sendTime);
    		log.info("sendDate------------"+sendDate);
			InternalMessage internalMessage = new InternalMessage(from, CommonUtil.getLoggedInUserId(), to, cc, bcc, subject, body, messageNumber, sendDate, "Active");
			mailDao.saveInternalMessage(internalMessage);
		} catch (ParseException e) {
			log.info(e.getMessage(), e);
			throw new EazyBpmException(e.getMessage(),e);
		}
	}
}
