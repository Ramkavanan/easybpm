package com.eazytec.bpm.oa.mail.dao;

import java.util.Date;
import java.util.List;

import com.eazytec.dao.GenericDao;
import com.eazytec.model.EmailConfiguration;
import com.eazytec.model.EmailContact;
import com.eazytec.model.InternalMessage;

public interface MailDao extends GenericDao<EmailContact, String>{
	
	/**
	 * get user contacts
	 * 
	 * @param user
	 * @return
	 */
	List<EmailContact> getUserContacts(String user);
	
	
	/**
	 * get user contacts
	 * 
	 * @param contactIds
	 * @return
	 */
	List<EmailContact> getContactsByIds(List<String> contactIds);
	
	/**
	 * save mail configuration
	 * 
	 * @param emailConfiguration
	 * @return
	 */
	EmailConfiguration saveEmailConfig(EmailConfiguration emailConfig);
	
	/**
	 * get email configuration 
	 * 
	 */
	EmailConfiguration getEmailConfig();
	
	/**
	 * get internal messages that need to send now
	 * 
	 * @param date
	 * @return
	 */
	List<InternalMessage> getInternalMessages(String messageSendDate);
	
	/**
	 * save/update internal message for send later
	 * 
	 * @param internalMessage
	 * @return
	 */
	InternalMessage saveInternalMessage(InternalMessage internalMessage);
}
