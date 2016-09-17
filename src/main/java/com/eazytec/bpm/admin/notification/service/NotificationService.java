package com.eazytec.bpm.admin.notification.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.springframework.mail.SimpleMailMessage;

import com.eazytec.exceptions.EazyBpmException;
import com.eazytec.model.Notification;

/**
 * It interacts with NotificationDao to save delete retrieve Notification details
 * and send notification to users
 * 
 * @author Ramachandran
 * @author Mathi
 */
@WebService
public interface NotificationService {
	
	
	/**
	 * Used to get the Notification for given messageSendOn
	 * @param messageSendOn
	 * @return
	 */
	List<Notification> getNotificationOnDate(String messageSendOn);
	
	/**
	 * Save or Update the notification entity
	 * @param notification
	 * @return
	 */
	Notification saveOrUpdateNotification(Notification notification);
	
	String getUserMailIdByUser(String userId) throws EazyBpmException;
	
	/**
	 * send notification to corresponding user on notitification date
	 * 
	 * @param notificationDate
	 * @return
	 */
	boolean sendNotifications(String notificationDate);
}