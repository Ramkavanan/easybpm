package com.eazytec.quartz;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.eazytec.bpm.oa.mail.service.MailService;
import com.eazytec.util.DateUtil;

/**
 * job that can send the mails which is choosen send mail later option. 
 * 
 * @author mathi
 *
 */
public class SendMailLaterJob extends QuartzJobBean {

	private Log logger = LogFactory.getLog(TimeBasedNotificationJob.class);
	
	private ApplicationContext applicationContext;
	
	public void setApplicationContext(ApplicationContext appContext) {
		applicationContext = appContext;
	}

	protected final void executeInternal(final JobExecutionContext ctx)	throws JobExecutionException {
		logger.info("======Scheduler Notification job started with the key============="+ctx.getTrigger().getFireInstanceId());
		String mailDate = DateUtil.convertDateToEazytecFormat(new Date());
		MailService mailService = (MailService) applicationContext.getBean("mailService");
		mailService.sendInternalMessages(mailDate);
	}
}
