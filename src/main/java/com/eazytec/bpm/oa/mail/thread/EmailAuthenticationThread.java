package com.eazytec.bpm.oa.mail.thread;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.eclipse.jetty.util.log.Log;

import com.eazytec.bpm.common.util.StringUtil;
import com.eazytec.common.util.CipherUtils;
import com.eazytec.common.util.CommonUtil;
import com.eazytec.model.User;
import com.eazytec.util.PropertyReader;

import dtw.webmail.JwmaKernel;
import dtw.webmail.JwmaSession;
import dtw.webmail.model.JwmaException;
import dtw.webmail.model.JwmaStoreImpl;
import dtw.webmail.model.JwmaStoreInfo;
import dtw.webmail.util.config.JwmaConfiguration;

/**
 * Class for get email authentication.
 *
 * @author mathi
 */

public class EmailAuthenticationThread implements Runnable{

	private HttpSession websession;
	private User userObj;
	
	public EmailAuthenticationThread(HttpSession websession, User userObj) {
		// TODO Auto-generated constructor stub
		this.websession = websession;
		this.userObj = userObj;
	}
	
	/**
	 * Create session and get mail authentication, set the folders
	 * 
	 */
	@Override
	public void run(){
		getInternalMessageAuthentication();
		if(!StringUtil.isEmptyString(userObj.getEmail())){
			getEmailAuthentication();
		}
	}
	
	/**
	 * get user internal mail authentication
	 * 
	 */
	public boolean getInternalMessageAuthentication(){
		Object o = websession.getValue("jwma.internalMessageSession");
		//update session information
		JwmaSession session = ((o == null)? new JwmaSession(websession, "internalMessage"):((JwmaSession) o));
		session.setWebSession(websession);
		return getAuthentication(session, CommonUtil.getInternalMessageId(userObj.getUsername()), userObj.getUsername());
	}
	
	/**
	 * get user external mail authentication
	 * 
	 */
	public boolean getEmailAuthentication(){
		Object o = websession.getValue("jwma.emailSession");
		//update session information
		JwmaSession session = ((o == null)? new JwmaSession(websession, "email"):((JwmaSession) o));
		session.setWebSession(websession);
		String password = "";
		if(!StringUtil.isEmptyString(userObj.getEmailPassword())){
			password = CipherUtils.decrypt(userObj.getEmailPassword());
		} 
		return getAuthentication(session, userObj.getEmail(), password);
	}
	
	/**
	 * get authentication for mail account
	 * 
	 * @param session
	 * @param username
	 * @param password
	 */
	public boolean getAuthentication(JwmaSession session, String username, String password){
		try{
			boolean newAccount = true;
		    String postoffice = null;

		    JwmaKernel kernel = JwmaKernel.getReference();
			JwmaConfiguration config = kernel.getConfiguration();
			
			//set post office host, if not allowed or invalid,
			//it will be the system's set default postoffice host
			session.setPostOffice(config.getPostOfficeByName(postoffice));
			
			//user exists for jwma -> authenticate user
			session.authenticate(username, password, newAccount);
			//we have now a created store
			JwmaStoreImpl store = session.getJwmaStore();
			//store storeinfo bean
			session.storeBean("jwma.storeinfo", (JwmaStoreInfo) store);
			//store inboxinfo bean
			session.storeBean("jwma.inboxinfo", store.getInboxInfo());
			//store trashinfo bean
			session.storeBean("jwma.trashinfo", store.getTrashInfo());
			//store actual folder
			session.storeBean("jwma.folder", store.getActualFolder());
			session.getJwmaStore().prepareRootFolder();
			session.getJwmaStore().prepareTrashFolder();
			session.getJwmaStore().prepareSentLaterFolder();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			// need to handle
			Log.warn(e.getMessage());
			return false;
		}
	}
}	
