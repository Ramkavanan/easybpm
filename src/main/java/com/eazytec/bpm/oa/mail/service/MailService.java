package com.eazytec.bpm.oa.mail.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eazytec.bpm.common.util.StringUtil;
import com.eazytec.exceptions.EazyBpmException;
import com.eazytec.model.EmailConfiguration;
import com.eazytec.model.EmailContact;
import com.eazytec.model.User;
import com.eazytec.service.GenericManager;
import com.eazytec.service.UserExistsException;

import dtw.webmail.JwmaSession;
import dtw.webmail.model.JwmaException;

/**
 * Interface to provide mailing service 
 * 
 * @author mathi
 */

@WebService
public interface MailService extends GenericManager<EmailContact, String> {
	
	/**
	 * get email authentication while login user
	 * 
	 * @param websession
	 * @param user
	 */
	void getEmailAuthentication(HttpSession websession, User user);
	
	/**
	 * save email contact 
	 *  
	 * @param contact
	 * @return
	 */
	EmailContact saveEmailContact(EmailContact contact);
	
	/**
	 * get user contacts
	 * 
	 * @param user
	 * @return
	 */
	List<EmailContact> getUserContacts(String user);
	
	/**
	 * save mail configuration
	 * 
	 * @param emailConfiguration
	 * @return
	 */
	EmailConfiguration saveEmailConfig(EmailConfiguration emailConfiguration);
	
	/**
	 * get email configuration 
	 * 
	 */
	EmailConfiguration getEmailConfig();
	
	/**
     * Saves a user's information.
     *
     * @param user the user's information
     * @throws UserExistsException thrown when user already exists
     * @return user the updated user object
     */
    User saveEmailSetting(User user, HttpServletRequest request, HttpServletResponse response);
    
    /**
     * Removes a contacts from the database by their contactIds
     *
     * @param contactId the contact's id
     */
    void removeContacts(List<String> contactIds);
    
    /**
     * check the mail authentication
     *  
     * @param req
     * @param res
     * @param sessionType
     * @return
     */
	JwmaSession checkMailAuthentication(HttpServletRequest req, HttpServletResponse res, String sessionType);
	
    /**
     * Get internal Message Authentication for user
     * 
     * @param req
     * @param res
     * @param user
     * @return
     */
	JwmaSession getInternalMessageAuthentication(HttpServletRequest req, HttpServletResponse res, User user);
	
	/**
     * Get external Mail Authentication for user
     * 
     * @param req
     * @param res
     * @param user
     * @return
     */
	JwmaSession getEmailAuthentication(HttpServletRequest req, HttpServletResponse res, User user , boolean isMailSettings);
		
    /**
	 * method to get inbox grid script
	 * 
	 * @param session
	 * @return
	 * @throws JwmaException 
	 * @throws MessagingException 
	 */
	 String getInboxMailGridScript(JwmaSession session, String sessionType) throws JwmaException, MessagingException;
	 
	/**
	 * method to get draft grid script
	 * 
	 * @param session
	 * @return
	 * @throws JwmaException 
	 * @throws MessagingException 
	 */
	 String getDraftMailGridScript(JwmaSession session, String sessionType) throws JwmaException, MessagingException;
	
	/**
	 * method to get sent mail grid script
	 * 
	 * @param session
	 * @return
	 * @throws JwmaException 
	 * @throws MessagingException 
	 */
	 String getSentMailGridScript(JwmaSession session, String sessionType) throws JwmaException, MessagingException;
	 
	 /**
	  * get sent later message script
	  * 
	  * @param session
	  * @param sessionType
	  * @return
	  * @throws JwmaException
	  * @throws MessagingException
	  */
	 String getSentLaterMessageGridScript(JwmaSession session, String sessionType) throws JwmaException, MessagingException;
	 
	 Map<String, Object> checkRecentReceivedMail(HttpServletRequest req, HttpServletResponse res);
	 
	 /**
	  * send internal message on date
	  * 
	  * @param date
	  * @return
	  */
	 boolean sendInternalMessages(String messageSendDate);
	 
	 /**
	  * save/ update internal message 
	  * 
	  * @param from
	  * @param to
	  * @param cc
	  * @param bcc
	  * @param subject
	  * @param body
	  * @param messageNumber
	  * @param sendTime
	  * @throws EazyBpmException
	  */
	 void saveOrUpdateInternalMessage(String from, String to, String cc, String bcc, String subject, String body, int messageNumber, String sendTime, String timeZoneOffset) throws EazyBpmException;
}
