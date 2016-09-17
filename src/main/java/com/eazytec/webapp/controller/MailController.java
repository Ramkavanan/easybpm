package com.eazytec.webapp.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Locale;
import java.util.Map;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.ContentType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.eazytec.bpm.common.util.StringUtil;
import com.eazytec.bpm.oa.mail.service.MailService;
import com.eazytec.bpm.runtime.listView.service.ListViewService;
import com.eazytec.common.util.CommonUtil;
import com.eazytec.exceptions.BpmException;
import com.eazytec.exceptions.EazyBpmException;
import com.eazytec.model.EmailConfiguration;
import com.eazytec.model.EmailContact;
import com.eazytec.model.User;
import com.eazytec.service.UserService;
import com.eazytec.util.DateUtil;

import dtw.webmail.JwmaKernel;
import dtw.webmail.JwmaSession;
import dtw.webmail.model.JwmaComposeMessage;
import dtw.webmail.model.JwmaContactsImpl;
import dtw.webmail.model.JwmaDisplayMessage;
import dtw.webmail.model.JwmaException;
import dtw.webmail.model.JwmaFolderImpl;
import dtw.webmail.model.JwmaMessage;
import dtw.webmail.model.JwmaMessagePart;
import dtw.webmail.model.JwmaMessagePartImpl;
import dtw.webmail.util.MultipartRequest;
import dtw.webmail.util.config.JwmaConfiguration;
import dtw.webmail.util.config.MailTransportAgent;
import dtw.webmail.util.config.PostOffice;

/**
 * Does the mail related actions.
 * 
 * @author Vinoth
 * @author Mathi
 */
@Controller
public class MailController extends BaseFormController{
    protected final Log log = LogFactory.getLog(getClass());
    public VelocityEngine velocityEngine;
    private ListViewService listViewService;
    private MailService mailService;
    private UserService userService;
    private String sessionType = "email";
    
    @Autowired
	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}
    
    @Autowired
    public void setListViewService(ListViewService listViewService) {
        this.listViewService = listViewService;
    }
	
    @Autowired
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }
    
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    
    /**
	 * Authentication of user mail account
	 * 
	 * @param model
	 * @param req
	 * @param res
	 * 
	 * @return
	 */
	@RequestMapping(value = "/bpm/mail/jwma/getMailAuthentication",method = RequestMethod.POST)
    public ModelAndView getEmailAuthentication(ModelMap model, HttpServletRequest req, HttpServletResponse res) {
		Locale locale = req.getLocale();
		JwmaSession session = null;
		String script = "";
		String from = req.getParameter("from");
		try {
			if(from == null){
				from=(String)model.get("from");
			}
			User loggedInUser = CommonUtil.getLoggedInUser();
			if(sessionType.equalsIgnoreCase("email")){
				session = mailService.getEmailAuthentication(req, res, loggedInUser, true);
			} else {
				session = mailService.getInternalMessageAuthentication(req, res, loggedInUser);
			}
			if(from.equalsIgnoreCase("inbox")) {
				 model.addAttribute("from", "inbox");
				 script = mailService.getInboxMailGridScript(session, sessionType);
		    	 model.addAttribute("script", script);
		    	 model.addAttribute("sessionType",sessionType);
		    	 return new ModelAndView("mail/jwma/mailInbox",model);
			}else if(from.equalsIgnoreCase("draft")){
				model.addAttribute("from", "draft");
				script = mailService.getDraftMailGridScript(session, sessionType);
		    	model.addAttribute("script", script);
		    	model.addAttribute("sessionType",sessionType);
				return new ModelAndView("mail/jwma/mailInbox",model);
			} else if(from.equalsIgnoreCase("sent-mail")){
				model.addAttribute("from", "sent-mail");
				script = mailService.getSentMailGridScript(session, sessionType);
		    	model.addAttribute("script", script);
		    	model.addAttribute("sessionType",sessionType);
				return new ModelAndView("mail/jwma/mailInbox",model);
			} else if(from.equalsIgnoreCase("sent-later")){
				model.addAttribute("from", "sent-later");
				script = mailService.getSentLaterMessageGridScript(session, sessionType);
		    	model.addAttribute("script", script);
		    	model.addAttribute("sessionType",sessionType);
				return new ModelAndView("mail/jwma/mailInbox",model);
			} else if(from.equalsIgnoreCase("settings")){
				model.addAttribute("user",loggedInUser);
				model.addAttribute("sessionType",sessionType);
				return new ModelAndView("mail/jwma/mailSetting",model);
			} else {
				model.addAttribute("from", "compose");
				model.addAttribute("sessionType",sessionType);
				return showMailCompose(model, req, res);
			}
		} catch (JwmaException ex) {
			log.error(ex);
			if(ex.getMessage().equals("session.login.authentication")){
				saveError(req, getText("error.mail.login",locale));
			} else {
				saveError(req, getText("error.mail.connection",locale)+" "+ex.getMessage());
			}
			model.addAttribute("sessionType",sessionType);
			if(sessionType.equalsIgnoreCase("email")){
				return mailSettings(model, req, res);
			} else {
				return new ModelAndView("redirect:/bpm/user/homePage");
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			saveError(req, getText("error.mail.connection",locale)+" "+e.getMessage());
			model.addAttribute("sessionType",sessionType);
			if(sessionType.equalsIgnoreCase("email")){
				return mailSettings(model, req, res);
			} else {
				return new ModelAndView("redirect:/bpm/user/homePage");
			}
		}
	}
	
	/**
	 * To show compose mail page
	 * 
	 * @param model
	 * @param req
	 * @param res
	 * 
	 * @return
	 */
	@RequestMapping(value = "/bpm/mail/jwma/showMailCompose",method = RequestMethod.GET)
    public ModelAndView showMailCompose(ModelMap model,HttpServletRequest req, HttpServletResponse res) {
		Locale locale = req.getLocale();
		model.addAttribute("from", "compose");
		String sessionTypeParam = req.getParameter("sessionType");
		if(!com.eazytec.common.util.StringUtil.isEmptyString(sessionTypeParam)){
			sessionType = sessionTypeParam;
		}
		JwmaSession session = null;
		try {
			session = mailService.checkMailAuthentication(req, res, sessionType);
		    if (session == null || !session.isValidWebSession() || !session.isAuthenticated()) {
		    	model.addAttribute("sessionType",sessionType);
		    	return getEmailAuthentication(model,req,res);
		    }
		    if(session.getWebMailStore() == null || !session.getWebMailStore().isConnected()){
		    	session.endMailSession();
		    	return getEmailAuthentication(model,req,res);
		    }
			String to = session.getRequestParameter("to");
			if (to == null) {
			  to = "";
			}
			JwmaComposeMessage message = JwmaComposeMessage.createJwmaComposeMessage(
			      session.getMailSession()
			  );
			User user = CommonUtil.getLoggedInUser();
			String signature=user.getSignature();
			if(signature!=null && !signature.isEmpty() && signature!=""){
				 message.setBody(message.getBody()+"\n\n\n\n\n\n\n\n\n"+signature);
			}else{
				 message.setBody(message.getBody());
			}
			session.storeBean("jwma.composemessage",  message);
			model.addAttribute("sessionType",sessionType);
      	 	log.info("composed Successfully");
			return new ModelAndView("mail/jwma/mailCompose",model);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			if(sessionType.equalsIgnoreCase("email")){
				saveError(req, getText("error.mail.compose", locale));
			 }else {
				 saveError(req, getText("error.internalMessage.compose", locale));
			 }
		    model.addAttribute("script", "");
		    model.addAttribute("sessionType",sessionType);
			return new ModelAndView("mail/jwma/mailInbox",model);
		}
    }
	
	/**
	 * show mail inbox page
	 * 
	 * @param model
	 * @param req
	 * @param res
	 * 
	 * @return
	 */
	@RequestMapping(value = "/bpm/mail/jwma/showMailInbox",method = RequestMethod.GET)
    public ModelAndView showMailInbox(ModelMap model,HttpServletRequest req, HttpServletResponse res) {
		Locale locale = req.getLocale();
		model.addAttribute("from", "inbox");
		String sessionTypeParam = req.getParameter("sessionType");
		if(!com.eazytec.common.util.StringUtil.isEmptyString(sessionTypeParam)){
			sessionType = sessionTypeParam;
		}
		JwmaSession session = null;
		try {
			session = mailService.checkMailAuthentication(req, res, sessionType);
		    if (session == null || !session.isValidWebSession() || !session.isAuthenticated()) {
		    	model.addAttribute("sessionType",sessionType);
		    	return getEmailAuthentication(model,req,res);
		    }
		    if(session.getWebMailStore() == null || !session.getWebMailStore().isConnected()){
		    	session.endMailSession();
		    	return getEmailAuthentication(model,req,res);
		    }
		    String script = mailService.getInboxMailGridScript(session, sessionType);
		    model.addAttribute("sessionType",sessionType);
		    model.addAttribute("script", script);
      	 	log.info("Inbox loded Successfully");
		    return new ModelAndView("mail/jwma/mailInbox",model);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			if(sessionType.equalsIgnoreCase("email")){
		    	saveError(req, getText("error.mail.inbox", locale));
			 }else {
				 saveError(req, getText("error.internalMessage.compose", locale));
			 }
	    	model.addAttribute("sessionType",sessionType);
	    	return getEmailAuthentication(model,req,res);
		}
    }
	
	/**
	 * show draft mail page
	 * 
	 * @param model
	 * @param req
	 * @param res
	 * 
	 * @return
	 */
	@RequestMapping(value = "/bpm/mail/jwma/showMailDraft",method = RequestMethod.GET)
    public ModelAndView showMailDraft(ModelMap model,HttpServletRequest req, HttpServletResponse res) {
		model.addAttribute("from", "draft");
		Locale locale = req.getLocale();
		JwmaSession session = null;
		String sessionTypeParam = req.getParameter("sessionType");
		if(!com.eazytec.common.util.StringUtil.isEmptyString(sessionTypeParam)){
			sessionType = sessionTypeParam;
		}
		try {
			session = mailService.checkMailAuthentication(req, res, sessionType);
		    if (session == null || !session.isValidWebSession() || !session.isAuthenticated()) {
		    	model.addAttribute("sessionType",sessionType);
		    	return getEmailAuthentication(model,req,res);
		    }
		    if(session.getWebMailStore() == null || !session.getWebMailStore().isConnected()){
		    	session.endMailSession();
		    	return getEmailAuthentication(model,req,res);
		    }
			String script = mailService.getDraftMailGridScript(session, sessionType);
			model.addAttribute("sessionType",sessionType);
		    model.addAttribute("script", script);
      	 	log.info("Draft Successfully");
			return new ModelAndView("mail/jwma/mailInbox",model);		
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			if(sessionType.equalsIgnoreCase("email")){
				saveError(req, getText("error.mail.draft", locale));
			 }else {
					saveError(req, getText("error.internalMessage.draft", locale));
			 }
			model.addAttribute("sessionType",sessionType);
			return getEmailAuthentication(model,req,res);
		}
	}
	
	/**
	 * To show sent-mail page
	 * 
	 * @param model
	 * @param req
	 * @param res
	 * 
	 * @return
	 */
	@RequestMapping(value = "/bpm/mail/jwma/showSentMail",method = RequestMethod.GET)
    public ModelAndView showSentMail(ModelMap model,HttpServletRequest req, HttpServletResponse res) {
		Locale locale = req.getLocale();
		model.addAttribute("from", "sent-mail");
		String sessionTypeParam = req.getParameter("sessionType");
		if(!com.eazytec.common.util.StringUtil.isEmptyString(sessionTypeParam)){
			sessionType = sessionTypeParam;
		}
		JwmaSession session = null;
		try {
			session = mailService.checkMailAuthentication(req, res, sessionType);
		    if (session == null || !session.isValidWebSession() || !session.isAuthenticated()) {
		    	model.addAttribute("sessionType",sessionType);
		    	return getEmailAuthentication(model,req,res);
		    }
		    if(session.getWebMailStore() == null || !session.getWebMailStore().isConnected()){
		    	session.endMailSession();
		    	return getEmailAuthentication(model,req,res);
		    }
			String script = mailService.getSentMailGridScript(session, sessionType);
		    model.addAttribute("script", script);
		    model.addAttribute("sessionType",sessionType);
      	 	log.info("Sent Items Displayed Successfully");
			return new ModelAndView("mail/jwma/mailInbox",model);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			if(sessionType.equalsIgnoreCase("email")){
				saveError(req, getText("error.mail.sent", locale));
			 }else {
				saveError(req, getText("error.internalMessage.sent", locale));
			 }
			model.addAttribute("sessionType",sessionType);
			return getEmailAuthentication(model,req,res);
		}
	}	
	
	/**
	 * To show sent-later message page
	 * 
	 * @param model
	 * @param req
	 * @param res
	 * 
	 * @return
	 */
	@RequestMapping(value = "/bpm/mail/jwma/showSentLaterMessage",method = RequestMethod.GET)
    public ModelAndView showSentLaterMessage(ModelMap model,HttpServletRequest req, HttpServletResponse res) {
		Locale locale = req.getLocale();
		model.addAttribute("from", "sent-later");
		String sessionTypeParam = req.getParameter("sessionType");
		if(!com.eazytec.common.util.StringUtil.isEmptyString(sessionTypeParam)){
			sessionType = sessionTypeParam;
		}
		JwmaSession session = null;
		try {
			session = mailService.checkMailAuthentication(req, res, sessionType);
		    if (session == null || !session.isValidWebSession() || !session.isAuthenticated()) {
		    	model.addAttribute("sessionType",sessionType);
		    	return getEmailAuthentication(model,req,res);
		    }
		    if(session.getWebMailStore() == null || !session.getWebMailStore().isConnected()){
		    	session.endMailSession();
		    	return getEmailAuthentication(model,req,res);
		    }
			String script = mailService.getSentLaterMessageGridScript(session, sessionType);
		    model.addAttribute("script", script);
		    model.addAttribute("sessionType",sessionType);
      	 	log.info("Sent Later maeesage is displayed Successfully");
			return new ModelAndView("mail/jwma/mailInbox",model);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			if(sessionType.equalsIgnoreCase("email")){
				saveError(req, getText("error.mail.sent", locale));
			 }else {
				saveError(req, getText("error.internalMessage.sent", locale));
			 }
			model.addAttribute("sessionType",sessionType);
			return getEmailAuthentication(model,req,res);
		}
	}	
	
	/**
	 * To show compose mail when click reply and forward
	 * 
	 * @param model
	 * @param req
	 * @param res
	 * 
	 * @return
	 */
	 @RequestMapping(value = "bpm/mail/jwma/showReplyMail",method = RequestMethod.POST)
	 public ModelAndView showReplyMail(ModelMap model,HttpServletRequest req, HttpServletResponse res) {
		 Locale locale = req.getLocale();
	     String dome = req.getParameter("todo");
	     String from = req.getParameter("from");
	     JwmaSession session = null;
		 try {
			 session = mailService.checkMailAuthentication(req, res, sessionType);
			 if (session == null || !session.isValidWebSession() || !session.isAuthenticated()) {
				 model.addAttribute("sessionType",sessionType);
				return getEmailAuthentication(model,req,res);
			 }
			 if(session.getWebMailStore() == null || !session.getWebMailStore().isConnected()){
			    	session.endMailSession();
			    	return getEmailAuthentication(model,req,res);
			    }
	         if (dome.equals("compose")) {
	        	 String to = session.getRequestParameter("to");
	    	     if (to == null) {
	    	        to = "";
	    	     }
	    	     boolean reply = new Boolean(session.getRequestParameter("reply")).booleanValue();
	    	     boolean toall = new Boolean(session.getRequestParameter("toall")).booleanValue();
	    	     boolean togglequote = new Boolean(session.getRequestParameter("togglequote")).booleanValue();
	    	     boolean forward = new Boolean(session.getRequestParameter("forward")).booleanValue();
	    	     JwmaFolderImpl folder = null;
	    		 JwmaComposeMessage message = null;
	    		 JwmaDisplayMessage actualmsg = null;
	    	     if(from.equalsIgnoreCase("inbox")){
	    	    	  folder = session.getJwmaStore().getInboxFolder();
	    	     } else if(from.equalsIgnoreCase("draft")){
	    	    	  folder = session.getJwmaStore().getInboxDraftFolder();
	    	     } else if(from.equalsIgnoreCase("sent-mail")){
	    	    	  folder = session.getJwmaStore().getSentFolder();
	    	     } else if(from.equalsIgnoreCase("sent-later")){
	    	    	  folder = session.getJwmaStore().getSentLaterFolder();
	    	     } else {
	    	    	  folder = session.getJwmaStore().getActualFolder();
	    	     }
	    		 if (reply || forward) {
	    		      actualmsg = (JwmaDisplayMessage) folder.getActualMessage();
	    		 }
	    		 if (reply) {
	    		      message = JwmaComposeMessage.createReply(actualmsg, toall,
	    		          session.getPreferences(), togglequote);
	    		      String toAddress = actualmsg.getFrom();
	    		      if(sessionType.equalsIgnoreCase("email")){
	    		    	  if(actualmsg.getFrom().contains("<")){
		    		    	  String mailTo[] = actualmsg.getFrom().split("<");
		    		          String mailId[] =mailTo[1].split(">");
		    		          toAddress = mailId[0];
		    		      } else if(actualmsg.getFrom().contains("&lt;")){
		    		    	  String mailTo[] = actualmsg.getFrom().split("&lt;");
		    		          String mailId[] =mailTo[1].split("&gt;");
		    		          toAddress = mailId[0];
		    		      }
		    		      User user = getUserManager().getUserByEmail(toAddress);
					      if(user !=null ){
					    	model.addAttribute("to", user.getUsername());
					      }else {
					    	  model.addAttribute("to", toAddress);
					      }
	    		      } else {
	    		    	  toAddress = CommonUtil.getUsernameFromEmailAddress(actualmsg.getFrom()); 
	    		    	  model.addAttribute("to", toAddress);
	    		      }
	    		 } else if (forward) {
	    		      message = JwmaComposeMessage.createForward(session.getMailSession(), actualmsg, to,session.getPreferences(), togglequote, true);
	    		      model.addAttribute("to", to);
	    		 } else {
	    		      message = JwmaComposeMessage.createJwmaComposeMessage(
	    		          session.getMailSession()
	    		      );
	    		      if (to != null) {
	    		        try {
	    		        	User user = getUserManager().getUserByEmail(to);
					    	if(user !=null ){
					    		message.setTo(user.getUsername());
					    	}else {
					    		message.setTo(to.replace("&lt;", "<").replace("&gt;", ">"));
					    	}
	    		        } catch (MessagingException mex) {
	    		          //handle probably?
	    		        }
	    		      }
	    		 }
	    		 User user = CommonUtil.getLoggedInUser();
	    		 String signature=user.getSignature();
	    		 if(signature!=null && !signature.isEmpty() && signature!=""){
	    		    	 message.setBody(message.getBody()+"\n\n\n\n\n\n\n\n\n"+signature);
	    		 }else{
	    		    	 message.setBody(message.getBody());
	    		 }
	    		 model.addAttribute("sessionType",sessionType);
	    		 session.storeBean("jwma.composemessage", message);
	    		 return new ModelAndView("mail/jwma/mailCompose",model);
   	    } else {
   	    	log.error("message.action.invalid");
   	    	saveError(req, getText("message.action.invalid", locale));
   	    	model.addAttribute("script", "");
   	    	model.addAttribute("from","inbox");
   	    	model.addAttribute("sessionType",sessionType);
   	        return new ModelAndView("mail/jwma/mailInbox",model);
   	    }
     	} catch (JwmaException ex) {
     		log.error(ex);
	    	saveError(req, getText("error.mail.compose", locale));
	    	if(sessionType.equalsIgnoreCase("email")){
		    	saveError(req, getText("error.mail.compose", locale));
			 }else {
				saveError(req, getText("error.internalMessage.compose", locale));
			 }
	    	model.addAttribute("from","inbox");
	    	model.addAttribute("script", "");
	    	model.addAttribute("sessionType",sessionType);
	        return new ModelAndView("mail/jwma/mailInbox",model);
	    } catch (Exception e) {
	    	log.error(e.getMessage(), e);
	    	saveError(req, getText("error.mail.compose", locale));
	    	if(sessionType.equalsIgnoreCase("email")){
		    	saveError(req, getText("error.mail.compose", locale));
			 }else {
				saveError(req, getText("error.internalMessage.compose", locale));
			 }
	    	model.addAttribute("script", "");
	    	model.addAttribute("from","inbox");
	    	model.addAttribute("sessionType",sessionType);
	        return new ModelAndView("mail/jwma/mailInbox",model);
	    }
	 }
	 
	/**
	 * send the mail 
	 * 
	 * @param model
	 * @param req
	 * @param res
	 * 
	 * @return
	 */
	@RequestMapping(value = "bpm/mail/jwma/sendMail",method = RequestMethod.POST)
	public ModelAndView sendMail(ModelMap model,@RequestParam("files") List<MultipartFile> files, HttpServletRequest req, HttpServletResponse res) {
		Locale locale = req.getLocale();
		boolean to_missing = false;
		boolean receivers_invalid = false;
		String successSendMessage = "success.internalMessage.sent";
		String successSaveDraft = "success.internalMessage.draft";
		StringBuffer tobuf = new StringBuffer(50);
		MultipartRequest multi = null;
		JwmaSession session = null;
		String sessionTypeParam = req.getParameter("sessionType");
		if(!com.eazytec.common.util.StringUtil.isEmptyString(sessionTypeParam)){
			sessionType = sessionTypeParam;
		}
		
		try {
			session = mailService.checkMailAuthentication(req, res, sessionType);
		    if (session == null || !session.isValidWebSession() || !session.isAuthenticated()) {
		    	model.addAttribute("sessionType",sessionType);
		    	return getEmailAuthentication(model,req,res);
		    }
		    if(session.getWebMailStore() == null || !session.getWebMailStore().isConnected()){
		    	model.addAttribute("sessionType",sessionType);
		    	session.endMailSession();
		    	return getEmailAuthentication(model,req,res);
		    }
			try {
				JwmaContactsImpl ctdb =	    (JwmaContactsImpl) session.getWebSession().getValue("jwma.contacts");
				//multi = new MultipartRequest(session.getRequest(), session.getMTA().getTransportLimit());
				User user = CommonUtil.getLoggedInUser();
				JwmaComposeMessage composeMessage = (JwmaComposeMessage) session.retrieveBean("jwma.composemessage");
				JwmaComposeMessage message = JwmaComposeMessage.createJwmaComposeMessage(session.getMailSession());
				
				boolean savedraft = req.getParameter("savedraft").equals("true");
				String to="";
				String cc="";
				String bcc="";
				String toUsers = req.getParameter("to");
				String[] removedAttachments = req.getParameterValues("removedAttachment");
				String ccUsers = req.getParameter("ccto");
				String bccUsers = req.getParameter("bccto");
				String subject = req.getParameter("subject");
				String body = req.getParameter("body");
				if((!ccUsers.isEmpty() && ccUsers.length() > 0) || (!bccUsers.isEmpty() && bccUsers.length() > 0)){
					List<String> toUsersList = new ArrayList<String>(Arrays.asList(toUsers.split(",")));
					List<String> ccUsersList = new ArrayList<String>(Arrays.asList(ccUsers.split(",")));
					List<String> bccUsersList = new ArrayList<String>(Arrays.asList(bccUsers.split(",")));
					bccUsersList.removeAll(toUsersList);
					ccUsersList.removeAll(toUsersList);
					bccUsersList.removeAll(ccUsersList);
					toUsers = toUsersList.toString();
					toUsers = toUsers.replace("[","");
					toUsers = toUsers.replace("]","").replace(" ", "");
					bccUsers = bccUsersList.toString().replace("[", "").replace("]", "").replace(" ", "");
					ccUsers = ccUsersList.toString().replace("[", "").replace("]", "").replace(" ", "");
				}
				
				if (subject != null) {
				  message.setSubject(subject);
				}
				if (body != null) {
				  message.setBody(body);
				}
				
				if (composeMessage.getAttachments() != null && composeMessage.getAttachments().getCount() > 0) {
					if(removedAttachments != null){
						if(composeMessage.getMessageParts().length != removedAttachments.length){
							message.addForwardAttachments(composeMessage.getAttachments(), removedAttachments);
						}
					} else {
						message.addAttachments(composeMessage.getAttachments());
					}
					message.setMessageParts(composeMessage.getMessageParts());
				}
				
				if(null != files && !files.isEmpty() ){
					message.setAttachements(files, composeMessage.getMessageParts());
				}
				
				if(removedAttachments != null && removedAttachments.length > 0 ){
					boolean isRemoved = false;
					JwmaMessagePart[] msgParts = message.getMessageParts();
					List<JwmaMessagePart> msgPartList = new ArrayList<JwmaMessagePart>();
					for(int i=0; i<msgParts.length; i++){
						for(int j=0; j<removedAttachments.length; j++){
							if(Integer.parseInt(removedAttachments[j]) != msgParts[i].getPartNumber()){
								isRemoved = true;
							}
						}
						if(isRemoved){
							msgPartList.add(msgParts[i]);
							isRemoved = false;
						}
					}
					JwmaMessagePart[] m_Parts = new JwmaMessagePart[msgPartList.size()];
					m_Parts = (JwmaMessagePart[]) msgPartList.toArray(m_Parts);
					message.setMessageParts(m_Parts);
           		}
				
				message.setMessageNumber(composeMessage.getMessageNumber());
				message.setDraft(composeMessage.isDraft());
				if(com.eazytec.bpm.common.util.StringUtil.isEmptyString(toUsers) && savedraft != true){
					session.storeBean("jwma.composemessage",
						    message
						);
					model.addAttribute("to", toUsers);
		    		model.addAttribute("cc", ccUsers);
		    		model.addAttribute("bcc", bccUsers);
					saveError(req, getText("error.mailSend.recipient", locale));
					model.addAttribute("sessionType",sessionType);
					return new ModelAndView("mail/jwma/mailCompose",model);
				}
				try{
					if(sessionType.equalsIgnoreCase("internalMessage")){
						to = getEmailIdForInternalMessageIds(toUsers);
						cc = getEmailIdForInternalMessageIds(ccUsers);
						bcc = getEmailIdForInternalMessageIds(bccUsers);
						successSendMessage = "success.internalMessage.sent";
						successSaveDraft = "success.internalMessage.draft";
					} else {
						to = getMailIdForUser(toUsers,sessionType);
						cc = getMailIdForUser(ccUsers,sessionType);
						bcc = getMailIdForUser(bccUsers,sessionType);
						successSendMessage = "success.mail.sent";
						successSaveDraft = "success.mail.draft";
					}
				}catch (EazyBpmException e) {
					// TODO: handle exception
					if(e.getMessage().equals("invalid email address") && savedraft != true){
						session.storeBean("jwma.composemessage",  message);
						 model.addAttribute("to", toUsers);
			    		 model.addAttribute("cc", ccUsers);
			    		 model.addAttribute("bcc", bccUsers);
						saveError(req, getText("error.mail.invalidEmail", locale));
						model.addAttribute("sessionType",sessionType);
						return new ModelAndView("mail/jwma/mailCompose",model);
					}
					
				}
				String from = "";
				/*if (from == null || from.length() == 0) {
					tobuf.delete(0, tobuf.length());
					if(sessionType.equalsIgnoreCase("email")){
						  from = tobuf.append(user.getFirstName())
						      .append(" ")
						  .append(user.getLastName())
						  .append(" <")
						  .append(user.getEmail())
						  .append(">").toString();
					} else {
						from = tobuf.append(user.getFirstName())
					      .append(" ")
					  .append(user.getLastName())
					  .append(" <")
					  .append(CommonUtil.getInternalMessageId(CommonUtil.getLoggedInUserName()))
					  .append(">").toString();
					}
					
				} else {
					  tobuf.delete(0, tobuf.length());
					  from = tobuf.append(user.getFirstName())
					      .append(" ")
					  .append(user.getLastName())
					  .append(" <")
					  .append(from)
					  .append(">").toString();
				}*/
				if(sessionType.equalsIgnoreCase("email")){
					  from = user.getEmail();
				} else {
					from = CommonUtil.getInternalMessageId(CommonUtil.getLoggedInUserName());
				}
				message.setFrom(from, user.getFullName());
				
				try {
					if(sessionType.equalsIgnoreCase("email")){
						if (to != null && !to.trim().equals("")) {
							message.setTo(to.replace("&lt;", "<").replace("&gt;", ">"));
						} else {
							to_missing = true;
							}
						if (cc != null && !cc.trim().equals("")) {
						    message.setCCTo(cc.replace("&lt;", "<").replace("&gt;", ">"));
						}
						if (bcc != null && !bcc.trim().equals("")) {
							message.setBCCTo(bcc.replace("&lt;", "<").replace("&gt;", ">"));
						}
					} else {
						if (to != null && !to.trim().equals("")) {
							message.setTo(getEmailAddressForInternalMessageIds(toUsers));
						} else {
							to_missing = true;
							}
						if (cc != null && !cc.isEmpty() && !cc.trim().equals("")) {
						    message.setCCTo(getEmailAddressForInternalMessageIds(ccUsers));
						}
						if (bcc != null && !bcc.isEmpty() && !bcc.trim().equals("")) {
							message.setBCCTo(getEmailAddressForInternalMessageIds(bccUsers));
						}
					}
					
				} catch (MessagingException mex) {
					  log.debug("Setting receivers:", mex);
					  receivers_invalid = true;
				}
			
				if (savedraft) {
				  message.closeDraft(session.getJwmaStore());
				  int msgNumber = composeMessage.getMessageNumber();
				  if(composeMessage.isDraft()){
					  JwmaFolderImpl folder = session.getJwmaStore().getInboxDraftFolder();
					  folder.deleteMessage(msgNumber);
				  }
				} else {
					if(sessionType.equalsIgnoreCase("internalMessage") 
							&& "true".equalsIgnoreCase(req.getParameter("sendLater")) 
							&& !StringUtil.isEmptyString(req.getParameter("sendTime"))){
						message.setMessageContent(session);
						String sendTime = req.getParameter("sendTime");
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
						Date sendDate = df.parse(sendTime);
			    		/*sendDate.setMinutes(sendDate.getMinutes()+Integer.parseInt(req.getParameter("timeZoneOffset")));
			    		sendTime = DateUtil.convertDateToEazytecFormat(sendDate);
			    		sendDate = df.parse(sendTime);*/
						message.getMessage().setSentDate(sendDate);
						Date currentDate = new Date();
						if((sendDate.before(currentDate) && sendDate.compareTo(currentDate) == -1) ||(sendDate.equals(currentDate) && sendDate.compareTo(currentDate) == 0)){
							message.send(session);
							saveMessage(req, getText("success.internalMessage.sent", locale));
						} else {
							int msgNumber = session.getJwmaStore().archiveSentLaterMail(message.getMessage());
							mailService.saveOrUpdateInternalMessage(from, to, cc, bcc, subject, body, msgNumber, req.getParameter("sendTime"),req.getParameter("timeZoneOffset"));
							saveMessage(req, getText("success.internalMessage.sentLater", locale));
						}
						
					} else {
						message.send(session);
						int msgNumber = composeMessage.getMessageNumber();
						if(composeMessage.isDraft()){
							  JwmaFolderImpl folder = session.getJwmaStore().getInboxDraftFolder();
							  folder.deleteMessage(msgNumber);
						}
						session.getJwmaStore().archiveSentMail(message.getMessage());
					}
				}
			    if (savedraft) {
				    String script = mailService.getDraftMailGridScript(session, sessionType);
				    model.addAttribute("script", script);
				    model.addAttribute("from", "draft");
				    saveMessage(req, getText(successSaveDraft, locale));
				    model.addAttribute("sessionType",sessionType);
					return new ModelAndView("mail/jwma/mailInbox",model);
				} else {
					String script = mailService.getInboxMailGridScript(session, sessionType);
				    model.addAttribute("script", script);
				    model.addAttribute("from", "inbox");
				    if(sessionType.equalsIgnoreCase("internalMessage") 
							&& "true".equalsIgnoreCase(req.getParameter("sendLater")) 
							&& !StringUtil.isEmptyString(req.getParameter("sendTime"))){
				    	//saveMessage(req, getText("success.internalMessage.sentLater", locale));
				    } else {
				    	saveMessage(req, getText(successSendMessage, locale));
				    }
				    model.addAttribute("sessionType",sessionType);
					return new ModelAndView("mail/jwma/mailInbox",model);
				}
			} catch (IOException ioex) {
				log.error(ioex);
				if(sessionType.equalsIgnoreCase("email")){
					saveError(req, getText("error.mail.sending", locale));
				 }else {
					saveError(req, getText("error.internalMessage.sending", locale));
				 }
				session.storeBean("jwma.composemessage",  message);
				model.addAttribute("to",req.getParameter("to"));
	    		model.addAttribute("cc", req.getParameter("ccto"));
	    		model.addAttribute("bcc", req.getParameter("bccto"));
				model.addAttribute("sessionType",sessionType);
				return new ModelAndView("mail/jwma/mailCompose",model);
			} finally {
			    if (req != null){ //&& req.hasAttachments()) {
			      System.gc();
			    }
			}
				  
		} catch (JwmaException ex) {
			log.error(ex);
			if(sessionType.equalsIgnoreCase("email")){
				saveError(req, getText("error.mail.sending", locale));
			 }else {
				saveError(req, getText("error.internalMessage.sending", locale));
			 }
			session.storeBean("jwma.composemessage",  message);
			model.addAttribute("to",req.getParameter("to"));
    		model.addAttribute("cc", req.getParameter("ccto"));
    		model.addAttribute("bcc", req.getParameter("bccto"));
			model.addAttribute("sessionType",sessionType);
			return new ModelAndView("mail/jwma/mailCompose",model);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			if(sessionType.equalsIgnoreCase("email")){
				saveError(req, getText("error.mail.sending", locale));
			 }else {
				saveError(req, getText("error.internalMessage.sending", locale));
			 }
			session.storeBean("jwma.composemessage",  (JwmaComposeMessage) session.retrieveBean("jwma.composemessage"));
			model.addAttribute("to",req.getParameter("to"));
    		model.addAttribute("cc", req.getParameter("ccto"));
    		model.addAttribute("bcc", req.getParameter("bccto"));
			model.addAttribute("sessionType",sessionType);
			return new ModelAndView("mail/jwma/mailCompose",model);
		}
	}
	

	 /**
	  * To show the mail when click the subject from inbox,
	  * @param model
	  * @return
	  */
	 @RequestMapping(value = "bpm/mail/jwma/getMessage",method = RequestMethod.GET)
	 public ModelAndView getInboxMessage(ModelMap model,HttpServletRequest req, HttpServletResponse res) {
	     String dome = req.getParameter("todo");
	     String folderName = req.getParameter("folderName");
	     model.addAttribute("from", folderName);
	     JwmaSession session = null; 
		 try {
			 log.info("Getting Message from folder:"+folderName);
		      session = mailService.checkMailAuthentication(req, res, sessionType);
		      if (session == null || !session.isValidWebSession() || !session.isAuthenticated()) {
		    	  model.addAttribute("sessionType",sessionType);
					return getEmailAuthentication(model,req,res);
			  }
		      try {
		          if (dome.equals("display") || folderName.equalsIgnoreCase("sent-mail")) {
		    	      String number = session.getRequestParameter("number");
		    	      if (number == null) {
		    	        throw new JwmaException("message.display.missingnumber");
		    	      } else {
		    	        	JwmaFolderImpl folder = null;
	    	        		if(folderName.equals("sent-mail")){
	    	        			folder = session.getJwmaStore().getSentFolder();
	    	        		} else if(folderName.equals("sent-later")){
	    	        			folder = session.getJwmaStore().getSentLaterFolder();
	    	        		} else {
	    	        			folder = session.getJwmaStore().getInboxFolder();
	    	        		}
			    	        folder.getJwmaMessage(toInt(number));
			    	      	JwmaDisplayMessage message = (JwmaDisplayMessage) folder.getActualMessage();
			    	       // JwmaMessage message = folder.getJwmaMessage(toInt(number));
	    	        		
	    	        		String to = "";
	 		    		    String cc = "";
	 		    		    String bcc = "";
	 		    		    String toAddr = "";
	 		    		    String ccAddr = ""; 
	 		    		    String bccAddr = ""; 
	 		    		   String fromAddr = message.getFrom();
	 		    		 if (message.getCCTo() != null && !message.getCCTo().isEmpty() && message.getCCTo() != "" && (getUserForMailId(message.getCCTo()).equalsIgnoreCase(CommonUtil.getLoggedInUserId()) || CommonUtil.getUsernameFromEmailAddress(message.getCCTo()).equalsIgnoreCase(CommonUtil.getLoggedInUserId()))){
	 		    			toAddr = message.getTo();
	 		    			ccAddr = message.getCCTo();
	 		    		 }else if (message.getTo() != null && !message.getTo().isEmpty() && message.getTo() != "" && (getUserForMailId(message.getTo()).equalsIgnoreCase(CommonUtil.getLoggedInUserId()) || CommonUtil.getUsernameFromEmailAddress(message.getTo()).equalsIgnoreCase(CommonUtil.getLoggedInUserId()))){
	 		    			  toAddr = message.getTo();
	 		    			  ccAddr = message.getCCTo();
	 		    		 } else if (message.getBCCTo() != null && !message.getBCCTo().isEmpty() && message.getBCCTo() != "" && (getUserForMailId(message.getBCCTo()).equalsIgnoreCase(CommonUtil.getLoggedInUserId()) || CommonUtil.getUsernameFromEmailAddress(message.getBCCTo()).equalsIgnoreCase(CommonUtil.getLoggedInUserId()))){
	 		    			 toAddr = message.getTo();
	 		    			 ccAddr = message.getCCTo();
	 		    			 bccAddr = message.getBCCTo();
	 		    		 } else {
	 		    			  toAddr = message.getTo();
	 		    			  ccAddr = message.getCCTo();
	 		    			  bccAddr = CommonUtil.getLoggedInUser().getFullName();
	 		    			  
 	 		    		  }
	 		    		 if(folderName.equalsIgnoreCase("sent-mail")){
	 		    			bccAddr = message.getBCCTo();
	 		    		 }
	 		    		  if((!toAddr.contains("<") || !toAddr.contains(">")) && (fromAddr.equalsIgnoreCase("eazytecnotification@gmail.com") || fromAddr.equalsIgnoreCase("notificationadmin@mydomain.com"))){
	 		    			bccAddr = "";
	 		    			fromAddr = "Notication Admin";
	 		    			toAddr = toAddr.split("@")[0];
	 		    			toAddr = getEmailIdForInternalMessageIds(toAddr);
	 		    		  } else if(fromAddr.equalsIgnoreCase("eazytecnotification@gmail.com")){
	 		    			 bccAddr = "";
	 		    			 fromAddr = "Notication Admin";
	 		    		  }
	 		    		  if(sessionType.equalsIgnoreCase("email")){
		 		    		   to = getUserFullNameForMailId(toAddr);
		 		    		   cc = getUserFullNameForMailId(ccAddr);
		 		    		   bcc = getUserFullNameForMailId(bccAddr);
	 		    		   } else {
	 		    			  
	 		    			   to = CommonUtil.getFullNameFromEmailAddress(toAddr);
	 		    			   cc = CommonUtil.getFullNameFromEmailAddress(ccAddr);
	 		    			   bcc = CommonUtil.getFullNameFromEmailAddress(bccAddr);
	 		    		   }
	 		    		   String fromAddress = "";
	 		    		   if(sessionType.equalsIgnoreCase("email")){
	 		    			  fromAddress = fromAddr;
	 		    		    } else {
	 		    		    	fromAddress = CommonUtil.getFullNameFromEmailAddress(fromAddr);
	 		    		    }
 		    		   
	 		    		   model.addAttribute("folder", folderName);
	 		    		   SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	 		    		   model.addAttribute("sentDate", df.format(message.getSentDate()));
	 		    		   model.addAttribute("fromAddress", fromAddress);
	 		    		   model.addAttribute("to", to);
	 		    		   model.addAttribute("cc", cc);
	 		    		   model.addAttribute("bcc", bcc);
	 		    		   model.addAttribute("body", message.getBody());
	 		    		   model.addAttribute("sessionType", sessionType);
	 		    		   session.storeBean("jwma.message", message);
			    	  	   return new ModelAndView("mail/jwma/message",model);
		    	      }
		    	    } else if (dome.equals("composedraft")) {
		    	      String number = session.getRequestParameter("number");
		    	      if (number == null) {
		    	        throw new JwmaException("message.draft.missingnumber");
		    	      }
		    	      JwmaFolderImpl folder = session.getJwmaStore().getInboxDraftFolder();
		    	        folder.getJwmaMessage(toInt(number));
		    	      	JwmaDisplayMessage actualmsg = (JwmaDisplayMessage) folder.getActualMessage();
		    	      	JwmaMessage message = JwmaComposeMessage.createDraftMessage(session.getMailSession(), actualmsg,session.getPreferences(), false, true);
		    		    String to = "";
		    		    String cc = "";
		    		    String bcc = "";
		    		    String toAddr = message.getTo();
		    		    String ccAddr = message.getCCTo();
		    		    String bccAddr = message.getBCCTo();
		    		    if(sessionType.equalsIgnoreCase("email")){
			    		    to = getUserForMailId(toAddr);
			    		    cc = getUserForMailId(ccAddr);
			    		    bcc = getUserForMailId(bccAddr);
		    		    } else {
		    		    	to = CommonUtil.getUsernameFromEmailAddress(toAddr);
		    		    	cc = CommonUtil.getUsernameFromEmailAddress(ccAddr);
		    		    	bcc = CommonUtil.getUsernameFromEmailAddress(bccAddr);
		    		    }
		    		    model.addAttribute("to", to);
		    		    model.addAttribute("cc", cc);
		    		    model.addAttribute("bcc", bcc);
		    		    model.addAttribute("body", message.getBody());
		    		    session.storeBean("jwma.composemessage",
		    		        message
		    		    );
		    		    model.addAttribute("sessionType", sessionType);
		    		    return new ModelAndView("mail/jwma/mailCompose",model);
		    	    } else {
		    	      throw new JwmaException("message.action.invalid");
		    	    }
		      } catch (JwmaException ex) {
		    	  ex.printStackTrace();
		        boolean login = false;
		        String message = ex.getMessage();
		        if (!session.isAuthenticated() && message.equals("request.target.missing")) {
		          login = true;
		        }
		        if (!login) {
		          message = JwmaKernel.getReference().getLogMessage("jwma.usererror")
		              + JwmaKernel.getReference().getErrorMessage(message);
		          if (ex.hasException()) {
		            log.error(message, ex.getException());
		          } else {
		            log.error(message, ex);
		          }
		          session.storeBean("jwma.error", ex);
		        }
		        if (!session.isAuthenticated()) {
		          ex.setInlineError(true);
		        } else {
		          if (ex.isInlineError()) {
		        	  JwmaFolderImpl folder=session.getJwmaStore().getInboxFolder();
					  String script=listViewService.getListViewForMailInbox(folder, "inbox", sessionType);
					  model.addAttribute("script", script);
					  model.addAttribute("sessionType",sessionType);
					  return new ModelAndView("mail/jwma/mailInbox",model);
		          } else {
		        	  JwmaFolderImpl folder=session.getJwmaStore().getInboxFolder();
					  String script=listViewService.getListViewForMailInbox(folder, "inbox", sessionType);
					  model.addAttribute("script", script);
					  model.addAttribute("sessionType",sessionType);
					  return new ModelAndView("mail/jwma/mailInbox",model);
		          }
		        }
		      }
		    }catch (Exception e) {
		    	e.printStackTrace();
		    	model.addAttribute("sessionType",sessionType);
				return new ModelAndView("mail/jwma/mailInbox",model);
			}
		 return new ModelAndView("mail/jwma/folder",model);
	 }
		
	 /**
	  * Download attached files in mail
	  * 
	  * @param req
	  * @param res
	  * 
	  */
	 @RequestMapping(value = "bpm/mail/jwma/downloadAttachments",method = RequestMethod.GET)
	 public void downloadAttachments(HttpServletRequest req, HttpServletResponse res){
		 try {
			  String number = req.getParameter("number");
		      JwmaSession session = mailService.checkMailAuthentication(req, res, sessionType);
		      if (number == null) {
		        throw new JwmaException("message.displaypart.missingnumber");
		      } else {
		        doDisplayMessagePart(session, toInt(number));
		      }
	      	 	log.info("Attachments downloaded Successfully");
		 } catch (Exception e) {
			// TODO: handle exception
	    	log.error(e.getMessage(), e);
		 }
	 }
	 
	 
	 /**
	  * Delete selected mails
	  * 
	  * @param model
	  * @param req
	  * @param res
	  * 
	  */
	 @RequestMapping(value = "bpm/mail/jwma/deleteSelectedMails",method = RequestMethod.GET)
	 public ModelAndView deleteSelectedMails(ModelMap model, HttpServletRequest req, HttpServletResponse res){
		 Locale locale = req.getLocale();
		 String mailNumbers = req.getParameter("mailNumbers");
		 String from = req.getParameter("from");
		 JwmaSession session = null;
		 try {
			 session = mailService.checkMailAuthentication(req, res, sessionType);
		     if (session == null || !session.isValidWebSession() || !session.isAuthenticated()) {
		    	 model.addAttribute("sessionType",sessionType);
		    	 return getEmailAuthentication(model,req,res);
			 }
		     if(mailNumbers.contains(",")){
		    	  String numbers[] = mailNumbers.split(","); 
		    	  for(String number : numbers){
		    		  if(from.equalsIgnoreCase("draft")){
		    			  session.getJwmaStore().getInboxDraftFolder().deleteMessage(toInt(number));
		    		  } else if(from.equalsIgnoreCase("sent-mail")){
		    			  session.getJwmaStore().getSentFolder().deleteMessage(toInt(number));
		    		  } else if(from.equalsIgnoreCase("sent-later")){
			            	 session.getJwmaStore().getSentLaterFolder().deleteMessage(toInt(number));
			          } else if(from.equalsIgnoreCase("inbox")){
		            	 session.getJwmaStore().getInboxFolder().deleteMessage(toInt(number));
		              }
		    	  }
		     } else {
		    	  if(from.equalsIgnoreCase("draft")){
	    			  session.getJwmaStore().getInboxDraftFolder().deleteMessage(toInt(mailNumbers));
	    		  } else if(from.equalsIgnoreCase("sent-mail")){
	    			  session.getJwmaStore().getSentFolder().deleteMessage(toInt(mailNumbers));
	              } else if(from.equalsIgnoreCase("sent-later")){
	    			  session.getJwmaStore().getSentLaterFolder().deleteMessage(toInt(mailNumbers));
	              } else if(from.equalsIgnoreCase("inbox")){
	            	 session.getJwmaStore().getInboxFolder().deleteMessage(toInt(mailNumbers));
	              }
		     }
		      
		     String script = "";
		     if(from.equalsIgnoreCase("draft")){
				  script = mailService.getDraftMailGridScript(session, sessionType);
	         } else if(from.equalsIgnoreCase("sent-mail")){
	        	  script = mailService.getSentMailGridScript(session, sessionType);
	         } else if(from.equalsIgnoreCase("sent-later")){
	        	 script = mailService.getSentLaterMessageGridScript(session, sessionType);
	         } else{
	       	      script = mailService.getInboxMailGridScript(session, sessionType);
	         }
	    	 model.addAttribute("script", script);
	    	 model.addAttribute("from",from);
	    	 model.addAttribute("sessionType",sessionType);
	    	 if(sessionType.equalsIgnoreCase("email")){
		    	 saveMessage(req, getText("success.mail.delete", locale));
		    	 log.info(getText("success.mail.delete", locale));
			 }else {
		    	 saveMessage(req, getText("success.internalMessage.delete", locale));
		    	 log.info(getText("success.internalMessage.delete", locale));
			 }
		     return new ModelAndView("mail/jwma/mailInbox",model);
		 } catch (Exception e) {
			 log.error(e.getMessage(), e);
			 if(sessionType.equalsIgnoreCase("email")){
				 saveError(req, getText("error.mail.delete", locale));
			 }else {
				 saveError(req, getText("error.internalMessage.delete", locale)); 
			 }
		     model.addAttribute("script", "");
		     model.addAttribute("sessionType",sessionType);
		     return new ModelAndView("mail/jwma/mailInbox",model);
		 }
	 }
	
	 /**
	  * Refresh inbox
	  * 
	  * @param model
	  * @param req
	  * @param res
	  * 
	  * @return
	  */
	 @RequestMapping(value = "/bpm/mail/jwma/refreshMailInbox",method = RequestMethod.GET)
	 public ModelAndView refreshMailInbox(ModelMap model,HttpServletRequest req, HttpServletResponse res) {
		 Locale locale = req.getLocale();
		 String from = req.getParameter("from");
		 JwmaSession session = null;
		 try {
			 session = mailService.checkMailAuthentication(req, res, sessionType);
			 if (session == null || !session.isValidWebSession() || !session.isAuthenticated()) {
				 model.addAttribute("sessionType",sessionType);
				return getEmailAuthentication(model,req,res);
			 }
		     try {
		    	  String script = getGridScript(session, from);
		    	  model.addAttribute("script", script);
		    	  model.addAttribute("from",from);
		    	  model.addAttribute("sessionType",sessionType);
		    	  
		    	  return new ModelAndView("mail/jwma/mailInbox",model);
		     } catch (JwmaException ex) {
		    	  log.error(ex);
		    	  if(sessionType.equalsIgnoreCase("email")){
			    		saveError(req, getText("error.mail.inbox", locale));
					 }else {
						 saveError(req, getText("error.internalMessage.inbox", locale));
					 }
			      model.addAttribute("from","inbox");
			      model.addAttribute("sessionType",sessionType);
		    	  return new ModelAndView("mail/jwma/mailInbox",model);
		     }
		 } catch (Exception e) {
	    	log.error(e.getMessage(), e);
	    	if(sessionType.equalsIgnoreCase("email")){
	    		saveError(req, getText("error.mail.inbox", locale));
			 }else {
				 saveError(req, getText("error.internalMessage.inbox", locale));
			 }
		    model.addAttribute("from","inbox");
		    model.addAttribute("sessionType",sessionType);
	    	return new ModelAndView("mail/jwma/mailInbox",model);
		 }
	}
	 
	/**
	 * To show the mail settings page
	 * 
	 * @param model
	 * @param req
	 * @param res
	 * 
	 * @return
	 */
	 @RequestMapping(value = "bpm/mail/jwma/mailSettings",method = RequestMethod.GET)
	 public ModelAndView mailSettings(ModelMap model,HttpServletRequest req, HttpServletResponse res) {
		 String status = "Not Connected"; 
		 model.addAttribute("from", "settings");
		 HttpSession websession = req.getSession(true);
		 Object o = websession.getValue("jwma.emailSession");
		 JwmaSession session =
		     ((o == null)? new JwmaSession(websession,"email"):((JwmaSession) o));
		 if(session.isAuthenticated()){
		   status = "Connected";
		 }
		 User user = userService.getUserById(CommonUtil.getLoggedInUserId());
		 model.addAttribute("user",user);
		 model.addAttribute("status", status);
		 model.addAttribute("sessionType",sessionType);
   	 	 log.info("Mail Setting Done Successfully");
		 return new ModelAndView("mail/jwma/mailSetting",model);
	 }
	 
	 /**
	  * method for user Email Setting
	  * 
	  * @param userId
	  * @param request
	  * 
	  * @return
	  */
	 @RequestMapping(value = "bpm/mail/jwma/saveEmailSetting", method = RequestMethod.POST)
	 public ModelAndView saveEmailSetting(User user,ModelMap model,BindingResult errors, HttpServletRequest request, HttpServletResponse response) {
		 Locale locale = request.getLocale();
		 try {
			 if (validator != null) {
				 validator.validate(user, errors);
				 if (errors.hasErrors()) {
					model.addAttribute("user",user);
					model.addAttribute("sessionType",sessionType);
					return new ModelAndView("mail/jwma/mailSetting",model);
				 }
		     }
			 User oldUser = userService.getUserById(CommonUtil.getLoggedInUserId());
		     oldUser.setSignature(user.getSignature());
			 oldUser.setFirstName(user.getFirstName());
			 oldUser.setLastName(user.getLastName());
			 oldUser.setEmailPassword(user.getEmailPassword());
			 mailService.saveEmailSetting(oldUser, request, response);
			 model.addAttribute("user",oldUser);
			 model.addAttribute("status", "Connected");
			 saveMessage(request, getText("jwma.user.mail.setting.success",locale));
			 //model.addAttribute("sessionType",sessionType);
	      	 log.info("Email Setting Saveed Successfully");
			 return showMailInbox(model,request, response);
		 } catch(BpmException e){
 			 log.error(e.getMessage(), e);
			 if(e.getMessage().equals("session.login.authentication")){
			     saveError(request, getText("error.mail.login",locale));
			 } else {
				 saveError(request, getText("error.mail.connection",locale));
			 }
			 model.addAttribute("status", "Not Connected");
			 model.addAttribute("sessionType",sessionType);
			 return new ModelAndView("mail/jwma/mailSetting",model);
	 	 } catch (Exception e) {
			 log.error(e.getMessage(), e);
			 model.addAttribute("status", "Not Connected");
			 saveError(request, getText("jwma.user.mail.setting.error",e.getMessage(),locale));
			 model.addAttribute("sessionType",sessionType);
			 return new ModelAndView("mail/jwma/mailSetting",model);
		 }
	 }
	 
	 /**
	  * To show the contact form for create email external contacts,
	  * 
	  * @param model
	  * @return
	  */
	 @RequestMapping(value = "bpm/mail/jwma/showMailContact",method = RequestMethod.GET)
	 public ModelAndView showMailContact(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		if(com.eazytec.bpm.common.util.StringUtil.isEmptyString(id)){
			model.addAttribute("emailContact", new EmailContact());
		} else {
			model.addAttribute("emailContact", mailService.get(id));
		}
		model.addAttribute("sessionType",sessionType);
  	 	log.info("Mail Contact displayed Successfully");
	   return new ModelAndView("mail/jwma/mailContact",model);
	 }
	
	 /**
	  * To save external mail contacts
	  * 
	  * @param model
	  * @return
	  */
	 @RequestMapping(value = "bpm/mail/jwma/saveMailContact",method = RequestMethod.POST)
	 public ModelAndView saveMailContact(ModelMap model, EmailContact emailContact, BindingResult errors, HttpServletRequest request, HttpServletResponse response) {
		Locale locale = request.getLocale();
		try{
			if (validator != null) {
	        	validator.validate(emailContact, errors);
	            if (errors.hasErrors()) { 
	            	return new ModelAndView("mail/jwma/mailContact",model);
	            }
	        }
			emailContact.setCreatedBy(request.getRemoteUser());
			emailContact = mailService.saveEmailContact(emailContact);
			model.addAttribute("emailContact", emailContact);
			saveMessage(request, getText("success.emailContact.save",locale));
			model.addAttribute("sessionType",sessionType);
      	 	log.info("Mail Contact displayed Successfully");
		    return new ModelAndView("mail/jwma/mailContact",model);
		} catch (Exception e) {
			model.addAttribute("sessionType",sessionType);
			model.addAttribute("emailContact", emailContact);
			saveError(request, getText("error.emailContact.save", locale));
	        return new ModelAndView("mail/jwma/mailContact",model);
		}
	 }
	
	
	 /**
	  * To get the logged in user contacts
	  * @param tableId
	  * @return
	  */
	 @RequestMapping(value="/bpm/mail/jwma/getUserEmailContacts", method = RequestMethod.GET)
	 public @ResponseBody List<Map<String, String>> allTableColumns(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		 List<Map<String, String>> contactDetailsList = new ArrayList<Map<String, String>>();
	     try{
	         List<EmailContact> contacts = mailService.getUserContacts(request.getRemoteUser());
	         if (contacts != null){
	             for(EmailContact contact : contacts){
	                 Map<String,String> contactDetail = new HashMap<String, String>();
	                 contactDetail.put("id", contact.getId());
	                 contactDetail.put("firstName", contact.getFirstName());
	                 contactDetail.put("lastName", contact.getLastName());
	                 contactDetail.put("fullName", contact.getFullName());
	                 contactDetail.put("email", contact.getEmail());
	                 contactDetailsList.add(contactDetail);
	             }
	             return contactDetailsList;
	         }else{
	        	 model.addAttribute("errorMsg", "Error in loading contacts.");
	         }
	      	 log.info("All contacts Displayed Successfully");

	     }catch(Exception e){
	         log.error("Error while getting all contacts "+e);
	     }
	     
	     return new ArrayList<Map<String, String>>();
	 }
	 
	 
	 /**
	  * To show the mail config form for setup email configuration,
	  * 
	  * @param model
	  * @return
	  */
	 @RequestMapping(value = "bpm/mail/jwma/showMailConfig",method = RequestMethod.GET)
	 public ModelAndView showMailConfig(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		EmailConfiguration emailConfig = mailService.getEmailConfig();
		if(emailConfig != null){
			model.addAttribute("emailConfig", emailConfig);
		} else {
			model.addAttribute("emailConfig", new EmailConfiguration());
		}
		model.addAttribute("sessionType",sessionType);
  	 	log.info("mail Config Displayed Successfully");
	    return new ModelAndView("mail/jwma/mailConfiguration",model);
 	 }
	
	 /**
	  * To save mail configuration,
	  * 
	  * @param model
	  * @return
	  */
	 @RequestMapping(value = "bpm/mail/jwma/saveMailConfig",method = RequestMethod.POST)
	 public ModelAndView saveMailConfig(ModelMap model, EmailConfiguration emailConfig, BindingResult errors, HttpServletRequest request, HttpServletResponse response) {
		Locale locale = request.getLocale();
		String id = request.getParameter("id");
		try{
			JwmaKernel kernel = JwmaKernel.getReference();
			JwmaConfiguration config = kernel.getConfiguration();
			emailConfig.setMtaName("Default MTA");
			emailConfig.setMtaAuthenticated(true);
			emailConfig.setPoName("Default PostOffice");
			emailConfig.setPoDefault(true);
			emailConfig.setPoType("mixed");
			emailConfig.setPoRootFolder("INBOX");
			if(com.eazytec.bpm.common.util.StringUtil.isEmptyString(id)){
				emailConfig.setId(null);
				emailConfig = mailService.saveEmailConfig(emailConfig);
				config.setMTA(new MailTransportAgent(emailConfig.getMtaName(), emailConfig.getMtaAddress(), emailConfig.getMtaPort(), emailConfig.isMtaSecure(), "", emailConfig.getMtaProtocol(), emailConfig.isMtaAuthenticated()));
				config.removePostOffice(config.getDefaultPostOffice());
				config.addPostOffice(new PostOffice(emailConfig.getPoName(), emailConfig.getPoAddress(), emailConfig.getPoPort(), emailConfig.getPoRootFolder(), emailConfig.isPoSecure(), emailConfig.getPoType(), emailConfig.getPoProtocol(), emailConfig.isPoDefault(), emailConfig.getPoReplyToDomain()));
				model.addAttribute("emailConfig", emailConfig);
				saveMessage(request, getText("success.emailConfig.save",locale));
	      	 	log.info(getText("success.emailConfig.save",locale));

			} else {
				emailConfig = mailService.saveEmailConfig(emailConfig);
				config.setMTA(new MailTransportAgent(emailConfig.getMtaName(), emailConfig.getMtaAddress(), emailConfig.getMtaPort(), emailConfig.isMtaSecure(), "", emailConfig.getMtaProtocol(), emailConfig.isMtaAuthenticated()));
				config.removePostOffice(config.getDefaultPostOffice());
				config.addPostOffice(new PostOffice(emailConfig.getPoName(), emailConfig.getPoAddress(), emailConfig.getPoPort(), emailConfig.getPoRootFolder(), emailConfig.isPoSecure(), emailConfig.getPoType(), emailConfig.getPoProtocol(), emailConfig.isPoDefault(), emailConfig.getPoReplyToDomain()));
				model.addAttribute("emailConfig", emailConfig);
				saveMessage(request, getText("success.emailConfig.update",locale));
	      	 	log.info(getText("success.emailConfig.update",locale));

			}
			return new ModelAndView("redirect:/logout");
		} catch (Exception e) {
			model.addAttribute("emailConfig", emailConfig);
			if(com.eazytec.bpm.common.util.StringUtil.isEmptyString(id)){
				saveError(request, getText("error.emailConfig.save", locale));
				log.error(getText("error.emailConfig.save", locale));
			} else {
				saveError(request, getText("error.emailConfig.update", locale));
				log.error(getText("error.emailConfig.update", locale));
			}
			model.addAttribute("sessionType",sessionType);
	        return new ModelAndView("mail/jwma/mailConfiguration",model);
		}
	 }
	
	
	 /**
	  * method for delete the selected contacts from contacts grid
	  * 
	  * @param contactIds
	  * @param request
	  * 
	  * @return
	  */
	 @RequestMapping(value = "bpm/mail/jwma/deleteSelectedContacts", method = RequestMethod.POST)
	 public @ResponseBody Map<String, Object> deleteSelectedContacts(@RequestParam("contactIds") String contactIds, HttpServletRequest request) {
		 Map<String, Object> resultMap = new HashMap<String, Object>();
		 Locale locale = request.getLocale();
		 List<String> contactIdList = new ArrayList<String>();
		 if (contactIds.contains(",")) {
			  String[] ids = contactIds.split(",");
			  for(String id :ids){
				  contactIdList.add(id);
			  }
			} else {
				contactIdList.add(contactIds);
			}
		try{
			mailService.removeContacts(contactIdList);
			resultMap.put("successMsg", getText("success.contact.delete",locale));
      	 	log.info("selected Contact deleted Successfully");
			return resultMap;
		}catch (Exception e) {
			log.error(e.getMessage(), e);
			resultMap.put("errorMsg", getText("error.contact.delete",e.getMessage(),locale));
		}	
		return resultMap;
	 }
		
		
	 /**
	  * method to get mail id from the address
	  * 
	  * @param toAddr
	  * @return
	  */
	 public String getMailIdFromMailAddress(String toAddr){
		if(toAddr.contains("<")){
			String mailTo[] = toAddr.split("<");
        	String mailId[] = mailTo[1].split(">");
        	return mailId[0];
		}else {
			return toAddr;
		}
	}
	 
	 /**
	  * method to get mailId for users
	  * 
	  * @param toUsers
	  * @return
	  */
	 public String getMailIdForUser(String toUsers, String sessionType) throws EazyBpmException{
		 String to = "";
		 if(toUsers != null && toUsers.length() > 0){
			    if (toUsers.contains(",")) {
					String[] userIds = toUsers.split(",");
					for(String userId : userIds){
						if(userId.contains("<")){
							to = addToAddress(to, getMailIdFromMailAddress(userId));
						} else {
							User user = getUserManager().getUserById(userId);
							if(user != null){
								if(sessionType.equalsIgnoreCase("email")){
									to = addToAddress(to, user.getEmail());
								} else {
									to = addToAddress(to, CommonUtil.getInternalMessageId(user.getUsername()));
								}
							} else if(!com.eazytec.bpm.common.util.StringUtil.isEmptyString(userId)){
								to = addToAddress(to, userId);
							}
						}
					}
				} else {
					if(toUsers.contains("<")){
						to = addToAddress(to, getMailIdFromMailAddress(toUsers));
					} else {
						User user = getUserManager().getUserById(toUsers);
						if(user != null){
							if(sessionType.equalsIgnoreCase("email")){
								to = addToAddress(to, user.getEmail());
							} else {
								to = addToAddress(to, CommonUtil.getInternalMessageId(user.getUsername()));
							}
						} else if(!com.eazytec.bpm.common.util.StringUtil.isEmptyString(toUsers)){
							to = addToAddress(to, toUsers);
						}
					}
				}
			}
		 return to;
	 }
	 
	 
	 private String getEmailIdForInternalMessageIds(String toUsers){
		 String emailAddress = "";
		 if(toUsers.contains(",")){
				String[] toUserNames = toUsers.split(",");
				for(String username : toUserNames){
					if(username.contains("@")){
						String[] userName = username.split("@");
						username = userName[0];
					}
					String userFullName = getUserManager().getUserFullNamesByUsername(username);
					if(userFullName != null){
						if(emailAddress.length() > 0){
							 StringBuffer emailAddressBuffer = new StringBuffer();
							emailAddress = emailAddress + ","+emailAddressBuffer.append(userFullName)
							  .append(" <")
							  .append(CommonUtil.getInternalMessageId(username))
							  .append(">").toString();
						} else {
							 StringBuffer emailAddressBuffer = new StringBuffer();
							emailAddress = emailAddressBuffer.append(userFullName)
							  .append(" <")
							  .append(CommonUtil.getInternalMessageId(username))
							  .append(">").toString();
						}
						
					} else {
						emailAddress = toUsers;
					}
				}
			} else {
				if(toUsers.contains("@")){
					String[] userName = toUsers.split("@");
					toUsers = userName[0];
				}
				String userFullName = getUserManager().getUserFullNamesByUsername(toUsers);
				if(userFullName != null){
					
					if(emailAddress.length() > 0){
						 StringBuffer emailAddressBuffer = new StringBuffer();
						emailAddress = emailAddress + "," +emailAddressBuffer.append(userFullName)
						  .append(" <")
						  .append(CommonUtil.getInternalMessageId(toUsers))
						  .append(">").toString();
					} else {
						 StringBuffer emailAddressBuffer = new StringBuffer();
						emailAddress = emailAddressBuffer.append(userFullName)
						  .append(" <")
						  .append(CommonUtil.getInternalMessageId(toUsers))
						  .append(">").toString();
					}
				} else {
					emailAddress = toUsers;
				}
				
			}
		 return emailAddress;
	 }
	 
	 private Address[] getEmailAddressForInternalMessageIds(String toUsers) throws AddressException, UnsupportedEncodingException{
		 List<Address> emailAddr = new ArrayList<Address>();
		 if(toUsers.contains(",")){
				String[] toUserNames = toUsers.split(",");
				for(String username : toUserNames){
					if(username.contains("@")){
						String[] userName = username.split("@");
						username = userName[0];
					}
					InternetAddress mailAddr = new InternetAddress(CommonUtil.getInternalMessageId(username));
					String userFullName = getUserManager().getUserFullNamesByUsername(username);
					if(userFullName != null){
						mailAddr.setPersonal(userFullName, "UTF-8");
					}
					emailAddr.add(mailAddr);
				}
			} else {
				if(toUsers.contains("@")){
					String[] userName = toUsers.split("@");
					toUsers = userName[0];
				}
				InternetAddress mailAddr = new InternetAddress(CommonUtil.getInternalMessageId(toUsers));
				String userFullName = getUserManager().getUserFullNamesByUsername(toUsers);
				if(userFullName != null){
					mailAddr.setPersonal(userFullName, "UTF-8");
				}
				emailAddr.add(mailAddr);
				
			}
		 return emailAddr.toArray(new Address[emailAddr.size()]);
	 }
	 
	 /**
	  * method to get users of mail id's
	  * 
	  * @param toUsers
	  * @return
	  */
	 public String getUserFullNameForMailId(String toAddr){
		 String to = "";
		 if(toAddr != null && toAddr.length() > 0){
			 if(toAddr.contains(",")){
				String[] mailIds = toAddr.split(",");
				for(String mailId : mailIds){
					String email = getMailIdFromMailAddress(mailId);
					User user = getUserManager().getUserByEmail(email.trim());
					if(user != null){
						to = addToUsers(to, user.getFirstName()+" "+user.getLastName());
					} else {
						to = addToUsers(to, mailId.trim());
					}
				}
			 } else {
				String email = getMailIdFromMailAddress(toAddr);
				User user = getUserManager().getUserByEmail(email.trim());
				if(user != null){
					to = addToUsers(to, user.getFirstName()+" "+user.getLastName());
				} else {
					to = addToUsers(to, email.trim());
				}
			 }
			 
		 }
		 return to;
	 }
	 
	 /**
	  * method to get users of mail id's 
	  * 
	  * @param toUsers
	  * @return
	  */
	 public String getUserForMailId(String toAddr){
		String to = "";
		if(toAddr != null && toAddr.length() > 0){
            if (toAddr.contains(",")) {
				String[] mailIds = toAddr.split(",");
				for(String mailId : mailIds){
					String email = getMailIdFromMailAddress(mailId);
					User user = getUserManager().getUserByEmail(email.trim());
					if(user != null){
						to = addToUsers(to, user.getUsername());
					} else {
						to = addToUsers(to, mailId.trim());
					}
				}
			} else {
				String email = getMailIdFromMailAddress(toAddr);
				User user = getUserManager().getUserByEmail(email.trim());
				if(user != null){
					to = addToUsers(to, user.getUsername());
				} else {
					to = addToUsers(to, email.trim());
				}
			}
	    }
		return to;
	}
	 
	 /**
	  * method to get users for toAddress
	  * 
	  * @param toUsers
	  * @return
	  */
	 public String addToUsers(String to,String email){
		if(to.length() == 0 ) {
			to+=email;
	    } else {
	    	to+=","+email;
	    }
		 return to;
	 }
	 
	 /**
	  * method to get users for toAddress
	  * 
	  * @param toUsers
	  * @return
	  */
	 public String addToAddress(String to,String email){
		 if(com.eazytec.bpm.common.util.StringUtil.isEmail(email)){
			if(to.length() == 0 ) {
				to+=email;
		    } else {
		    	to+=","+email;
		    }
		 return to;
		 } else {
			 throw new EazyBpmException("invalid email address");
		 }
	 }
	 
	
	 /**
	  * method to pull folder mails and create grid script
	  * 
	  * @param session
	  * @param from
	  * @return
	  * @throws JwmaException
	 * @throws MessagingException 
	  */
	 public String getGridScript(JwmaSession session, String from) throws JwmaException, MessagingException{
		 String script = "";
		 if(from.equalsIgnoreCase("draft")){
       	  	  session.getJwmaStore().pullDraftFromServer();
			  script = mailService.getDraftMailGridScript(session, sessionType);
         } else if(from.equalsIgnoreCase("sent-mail")){
       	  	  session.getJwmaStore().pullSentFromServer();
       	  	  script = mailService.getSentMailGridScript(session, sessionType);
         } else {
       	      session.getJwmaStore().pullInboxFromServer();
       	      script = mailService.getDraftMailGridScript(session, sessionType);
         }
		 return script;
	 }
	 
	 /**
	  * method to get inbox grid script
	  * 
	  * @param session
	  * @return
	  * @throws JwmaException 
	  * @throws MessagingException 
	  */
	 public String getInboxGridScript(JwmaSession session) throws JwmaException, MessagingException{
   	      JwmaFolderImpl folder=session.getJwmaStore().getInboxFolder();
   	      if( folder == null || folder.getMessageInfos() == null || (folder != null && folder.getMessageInfos().size() < 1)){
   	    	session.getJwmaStore().prepareInboxFolder();
   	    	folder=session.getJwmaStore().getInboxFolder();
   	      } else {
   	    	//for listing only
   	    	  if(!folder.getFolder().isOpen()){
   	    		  folder.getFolder().open(Folder.READ_ONLY);
   	    	  }
 	    	  if(folder.getOldMessageCount() < folder.getFolder().getMessageCount()){
 	    		  folder.refreshFolder(folder.getFolder(), folder.getFolder().getMessageCount()-folder.getOldMessageCount());
 	    	  }
   	    	 
   	      }
   	      String script=listViewService.getListViewForMailInbox(folder, "inbox", sessionType);
	 return script;
	 }
	 
	 /**
	  * method to get draft grid script
	  * 
	  * @param session
	  * @return
	  * @throws JwmaException 
	  * @throws MessagingException 
	  */
	 public String getDraftGridScript(JwmaSession session, boolean isDrafted) throws JwmaException, MessagingException{
		 JwmaFolderImpl folder=session.getJwmaStore().getInboxDraftFolder();
		 if( folder == null || folder.getMessageInfos() == null || (folder != null && folder.getMessageInfos().size() < 1)){
    	    	session.getJwmaStore().prepareDraftFolder();
    	    	folder=session.getJwmaStore().getInboxDraftFolder();
    	 }else {
    	    	//for listing only
  	    	  if(!folder.getFolder().isOpen()){
  	    		  folder.getFolder().open(Folder.READ_ONLY);
  	    	  }
 	    	  if(folder.getOldMessageCount() < folder.getFolder().getMessageCount()){
 	    		  folder.refreshFolder(folder.getFolder(), folder.getFolder().getMessageCount()-folder.getOldMessageCount());
 	    	  } else if(isDrafted){
 	    		 folder.refreshFolder(folder.getFolder(), 1);
 	    	  }
  	    	 
  	      }
		 String script=listViewService.getListViewForMailInbox(folder, "draft", sessionType);
		 return script;
	 }
	 
	 /**
	  * method to get sent-mail grid script
	  * 
	  * @param session
	  * @return
	  * @throws JwmaException 
	  * @throws MessagingException 
	  */
	 public String getSentGridScript(JwmaSession session) throws JwmaException, MessagingException{
		 JwmaFolderImpl folder=session.getJwmaStore().getSentFolder();
		 if( folder == null || folder.getMessageInfos() == null || (folder != null && folder.getMessageInfos().size() < 1)){
    	    	session.getJwmaStore().prepareSentFolder();
    	    	folder=session.getJwmaStore().getSentFolder();
	      }else {
   	    	//for listing only
   	    	  if(!folder.getFolder().isOpen()){
   	    		  folder.getFolder().open(Folder.READ_ONLY);
   	    	  }
   	    	  if(folder.getOldMessageCount() < folder.getFolder().getMessageCount()){
   	    		  folder.refreshFolder(folder.getFolder(), folder.getFolder().getMessageCount()-folder.getOldMessageCount());
   	    	  }
   	    	 
   	      }
		 String script=listViewService.getListViewForMailInbox(folder, "sent-mail", sessionType);
		 return script;
	 }
	 
	 /**
		 * method for getting email count
		 * 
		 * @param departmentIds
		 * @param request
		 * 
		 * @return
		 */
	 	@RequestMapping(value = "bpm/mail/getCount", method = RequestMethod.GET,produces="application/json;charset=UTF-8")
		public @ResponseBody
		Map<String,Object> getDepartmentUserGrid(HttpServletRequest request,HttpServletResponse response) {
			Map<String, Object> countMap =new HashMap<String, Object>();
			try {
				countMap =	mailService.checkRecentReceivedMail(request, response);
			} catch (Exception e) {
				//not handling anything... 
				log.error(e.getMessage(), e);
			}
			return countMap;
		}
		

	 /**
	  * Outputs a given message part of the actual message.
	  *
	  * @param session a <tt>JwmaSession</tt> instance.
	  * @param number of the message part to be displayed
	  *        as <tt>int</tt>.
	  *
	  * @throws JwmaException if it fails to execute properly.
	  */
	 private void doDisplayMessagePart(JwmaSession session, int number)
	      throws JwmaException {

	    try {

	      //1.get Messagepart
	      JwmaFolderImpl folder = session.getJwmaStore()
	          .getActualFolder();
	      JwmaDisplayMessage actualmsg =
	          (JwmaDisplayMessage) folder.getActualMessage();

	      JwmaMessagePartImpl part = (JwmaMessagePartImpl) actualmsg.getMessagePart(number);

	      //2. set content type and file name
	      String type = new ContentType(part.getContentType()).getBaseType();
	      String fname = part.getName();
	      session.getResponse().setContentType(type);
	      //we do it all for fun or not?
	      if (fname == null) {
	        fname = "easter.egg";
	      }
	      fname = URLEncoder.encode(fname, "UTF-8");
	      //fname = URLDecoder.decode(fname, "ISO8859_1");
	      //session.getResponse().setHeader("Content-Disposition", "filename=" + fname);
	      session.getResponse().setHeader("Content-disposition", "attachment; filename=\""+ fname + "\"");

	      //3. stream out part
	      ServletOutputStream out =
	          session.getResponse().getOutputStream();
	      folder.writeMessagePart(part.getPart(), out);


	    } catch (Exception ex) {
	    	ex.printStackTrace();
	      throw new JwmaException("message.displaypart.failed").setException(ex);
	    }
	 }//doDisplayMessagePart
	  
	 /*** Helper methods ****************************************************/

	 /**
	  * Converts a <tt>String</tt> into an <tt>int</tt>.
	  */
	 private int toInt(String number) throws JwmaException {
	    try {
	      return Integer.parseInt(number);
	    } catch (Exception ex) {
	      throw new JwmaException("jwma.number.format");
	    }
	 }//toInt

	 /**
	  * Converts a <tt>String[]</tt> into an <tt>int[]</tt>.
	  * Performs a trim on each string.
	  */
	 private int[] toInts(String[] numbers) throws JwmaException {
	    int[] msgnum = new int[numbers.length];
	    for (int i = 0; i < numbers.length; i++) {
	      msgnum[i] = toInt(numbers[i].trim());
	    }
	    return msgnum;
	 }//toInts

	 /*** End Helper methods ************************************************/

	 /**
	  * Returns servlet info as <tt>String</tt>.
	  *
	  * @return Info about this servlet as <tt>String</tt>.
	  */
	 public String getServletInfo() {
	    return "jwma (Java WebMail) Controller Servlet";
	 }//getServletInfo()
	  
	 /**
	  * Tests if an incoming to address is
	  * actually nickname.
	  *
	  * @param true if nickname, false otherwise;
	  */
	 private boolean isNickname(String to) {
	    return (to.startsWith("@"));
	 }//isNickname

	 /**
	  * Tests if an incoming to address is
	  * actually a group.
	  *
	  * @param true if group, false otherwise.
	  */
	 private boolean isGroup(String to) {
	    return (to.endsWith(":;") || to.endsWith(":@;"));
	 }//isGroup

	 /**
	  * Tests if an incoming to address is
	  * an invisible group.
	  *
	  * @param true if invisible group, false otherwise.
	  */
	 private boolean isInvisibleGroup(String to) {
	    return (to.endsWith(":;") && !to.endsWith(":@;"));
	  }//isInvisibleGroup
}