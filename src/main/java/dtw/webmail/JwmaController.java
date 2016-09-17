/***
 * jwma Java WebMail
 * Copyright (c) 2000-2003 jwma team
 *
 * jwma is free software; you can distribute and use this source
 * under the terms of the BSD-style license received along with
 * the distribution.
 ***/
package dtw.webmail;

import java.io.*;
import java.util.*;
import java.text.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import org.apache.log4j.Logger;
import org.apache.log4j.NDC;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.eazytec.bpm.oa.mail.service.MailService;
import com.eazytec.model.EmailConfiguration;

import dtw.webmail.util.*;
import dtw.webmail.util.config.JwmaConfiguration;
import dtw.webmail.util.config.PostOffice;
import dtw.webmail.model.*;
import dtw.webmail.plugin.RandomAppendPlugin;

/**
 * Class extending the <tt>HttpServlet</tt> to implement
 * the main controller of jwma.
 * <p>
 * Please see the related documentation for more detailed
 * information on process flow and functionality.
 *
 * @author Dieter Wimberger
 * @version 0.9.7 07/02/2003
 */
public class JwmaController extends HttpServlet {

  //logging
  private static Logger log = Logger.getLogger(JwmaController.class);

  /**
   * Initializes the servlet when it is loaded by the
   * servlet engine.
   * <p>
   * This implementation "boots" jwma by starting up
   * the <tt>JwmaKernel</tt>.
   *
   * @param config the configuration as <tt>ServletConfig</tt>
   *
   * @throws ServletException if initialization fails.
   *
   * @see dtw.webmail.JwmaKernel
   */
  public void init(ServletConfig config)
      throws ServletException {

    super.init(config);

    try {
      //kernel bootup
      JwmaKernel myKernel = JwmaKernel.getReference();

      //check runtime environment
      String datadir = config.getInitParameter("datadir");
      boolean absolutepaths =
          new Boolean(config.getInitParameter("absolutepaths")).booleanValue();
      boolean status =
          new Boolean(config.getInitParameter("status")).booleanValue();

      if (!absolutepaths) {
        datadir = config.getServletContext().getRealPath(datadir);
      }

      ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
      MailService mailService = (MailService) ctx.getBean("mailService");
      EmailConfiguration emailConfig = mailService.getEmailConfig();
      myKernel.setEmailConfig(emailConfig);
      //set status
      myKernel.setJwmaStatusEnabled(status);
      //setup kernel with location
      myKernel.setup(datadir);

      log.info("Controller inited.");
      log.info("jwma ready.");

    } catch (Exception ex) {
      config.getServletContext().log(ex, "Initialization failed.");
      throw new UnavailableException(this, ex.getMessage());
    }
  }//init


  /**
   * Handles the incoming requests.
   * <p>
   * This implementation ensures authenticated session
   * existence, retrieves the <tt>acton</tt>
   * and <tt>todo</tt> parameters, and dispatches all
   * valid actions to the target dispatchers.
   * <p>
   * The flow of the process is described in the related
   * documentation.
   * <p>
   * Application related errors/exceptions are handled
   * by forwarding the request to an error page, or the actual page
   * in case of an inlined error.
   *
   * @param req a reference to the actual <tt>HttpServletRequest</tt>
   *        instance.
   * @param res a reference to the actual <tt>HttpServletResponse</tt>
   *        instance.
   *
   * @throws ServletException if servlet related operations fail.
   * @throws IOException if i/o operations fail.
   */
  public void service(HttpServletRequest req, HttpServletResponse res)
      throws ServletException, IOException {

    try {
      //prepare NDC with client host name
      NDC.push(req.getRemoteHost());

      //1. Handle session
      HttpSession websession = req.getSession(true);
      Object o = websession.getValue("jwma.emailSession");
      String acton = req.getParameter("acton");
      String dome = req.getParameter("todo");

      //2. update session information
      JwmaSession session =
          ((o == null)? new JwmaSession(websession, "email"):((JwmaSession) o));

      session.setRequest(req);
      session.setResponse(res);
      session.setWebSession(websession);

      //redirect to login view if no valid websession
      if (!session.isValidWebSession()) {
        session.redirect(JwmaKernel.LOGIN_VIEW);
        return;
      }

      //dispatch actions
      try {
        if (acton == null || acton.equals("")) {
          throw new JwmaException("request.target.missing");
        } else if (dome == null || dome.equals("")) {
          throw new JwmaException("request.action.missing");
        } else if (acton.equals("session")) {
          doDispatchSessionActions(session, dome);
        } else {
          //ensure authentication & postoffice connection
          session.ensureAuthenticated();
          session.ensurePostOfficeConnection();

          if (acton.equals("folder")) {
            doDispatchFolderActions(session, dome);
          } else if (acton.equals("message")) {
            doDispatchMessageActions(session, dome);
          } else if (acton.equals("preferences")) {
            doDispatchPreferencesActions(session, dome);
          } else {
            throw new JwmaException("request.target.invalid");
          }
        }
      } catch (JwmaException ex) {
        //log.debug("Exception handling routine",ex);
        boolean login = false;

        //pickup possible relogin
        String message = ex.getMessage();
        if (!session.isAuthenticated() && message.equals("request.target.missing")) {
          login = true;
        }
        //log.debug("login="+login);
        //Log the problem
        if (!login) {
          //oneliner resolving of key to message in
          message = JwmaKernel.getReference().getLogMessage("jwma.usererror")
              + JwmaKernel.getReference().getErrorMessage(message);

          //log exception with description and trace if inlined exception
          //available, else with stacktrace.
          if (ex.hasException()) {
            log.error(message, ex.getException());
          } else {
            log.error(message, ex);
          }
          //store error to be shown
          session.storeBean("jwma.error", ex);
        }

        //1. if not authenticated forward to login page
        if (!session.isAuthenticated()) {
          //ensure all errors are inline
          ex.setInlineError(true);
          session.redirect(JwmaKernel.LOGIN_VIEW);
        } else {
          //redirect last view with inlined error or
          //to error page.
          if (ex.isInlineError()) {
            session.redirectToActual();
          } else {
            session.redirect(JwmaKernel.ERROR_VIEW);
          }
        }
      }
    } finally {
      NDC.pop();
    }
  }//service

  /*** Dispatchers ***************************************************/

  /**
   * Dispatches actions targeting a <tt>session</tt>.
   *
   * @param session a <tt>JwmaSession</tt> instance.
   * @param dome the task as <tt>String</tt>.
   *
   * @throws JwmaException if it fails to dispatch the request to a
   *         method (i.e. invalid request), or the action method fails
   *         to execute the task.
   */
  private void doDispatchSessionActions(JwmaSession session, String dome)
      throws JwmaException {

    if (dome.equals("logout")) {
      session.ensureAuthenticated();
      doLogout(session);
    } else if (dome.equals("login")) {
      String user = session.getRequestParameter("username");
      String passwd = session.getRequestParameter("password");
      String postoffice = session.getRequestParameter("postoffice");
      //FIXME:catch invalid and trim the vars!

      doLogin(session, user, passwd, postoffice);
    } else if (dome.equals("redirect")) {
      session.ensureAuthenticated();
      String view = session.getRequestParameter("view");
      doRedirect(session, view);
    } else {
      throw new JwmaException("session.action.invalid");
    }
  }//doDispatchSessionActions

  /**
   * Dispatches actions targeting a <tt>folder</tt>.
   *
   * @param session a <tt>JwmaSession</tt> instance.
   * @param dome the task as <tt>String</tt>.
   *
   * @throws JwmaException if it fails to dispatch the request to a
   *         method (i.e. invalid request), or the action method fails
   *         to execute the task.
   */
  private void doDispatchFolderActions(JwmaSession session, String dome)
      throws JwmaException {

    if (dome.equals("display")) {
      String name = session.getRequestParameter("path");
      if (name == null) {
        throw new JwmaException("folder.display.missingpath");
      } else {
        doDisplayFolder(session, name);
      }
    } else if (dome.equals("sortmessages")) {
      String criteria = session.getRequestParameter("criteria");
      if (criteria == null) {
        throw new JwmaException("folder.sortmessages.missingcriteria");
      } else {
        doSortFolderMessages(session, toInt(criteria));
      }
    } else if (dome.equals("move")) {
      String[] folders =
          session.getRequest().getParameterValues("paths");
      String destination =
          session.getRequestParameter("destination");

      if (folders == null || folders.length == 0) {
        throw new JwmaException("folder.move.missingpaths");
      } else if (destination == null) {
        throw new JwmaException("folder.move.missingdestination");
      } else {
        doMoveFolders(session, folders, destination);
      }
    } else if (dome.equals("create")) {
      String path = session.getRequestParameter("aname");
      String type = session.getRequestParameter("type");
      if (path == null) {
        throw new JwmaException("folder.create.missingname");
      } else if (type == null) {
        throw new JwmaException("folder.create.missingtype");
      } else {
        doCreateFolder(session, path, toInt(type));
      }
    } else if (dome.equals("delete")) {
      String[] folders =
          session.getRequestParameters("paths");
      if (folders == null || folders.length == 0) {
        throw new JwmaException("folder.delete.missingpaths");
      } else {
        doDeleteFolders(session, folders);
      }
    } else if(dome.equals("display_subscribed")) {
        doDisplaySubscribedFolders(session);
    } else if(dome.equals("subscribe")) {
      String[] folders =
          session.getRequestParameters("paths");
      if (folders == null || folders.length == 0) {
        throw new JwmaException("folder.subscribe.missingpaths");
      } else {
        doSubscribeFolders(session, folders);
      }
    } else if(dome.equals("display_unsubscribed")) {
        doDisplayUnsubscribedFolders(session);
    } else if(dome.equals("unsubscribe")) {
      String[] folders =
          session.getRequestParameters("paths");
      if (folders == null || folders.length == 0) {
        throw new JwmaException("folder.unsubscribe.missingpaths");
      } else {
        doUnsubscribeFolders(session, folders);
      }
    } else {
      throw new JwmaException("folder.action.invalid");
    }
  }//doDispatchFolderActions

  /**
   * Dispatches actions targeting a <tt>message</tt>.
   *
   * @param session a <tt>JwmaSession</tt> instance.
   * @param dome the task as <tt>String</tt>.
   *
   * @throws JwmaException if it fails to dispatch the request to a
   *         method (i.e. invalid request), or the action method fails
   *         to execute the task.
   */
  private void doDispatchMessageActions(JwmaSession session, String dome)
      throws JwmaException {

    if (dome.equals("display")) {
      String number = session.getRequestParameter("number");
      if (number == null) {
        throw new JwmaException("message.display.missingnumber");
      } else {
        //check for "magic" numbers
        if (number.equals("next")) {
          doDisplayNextMessage(session);
        } else if (number.equals("prev")) {
          doDisplayPreviousMessage(session);
        } else {
          doDisplayMessage(session, toInt(number));
        }
      }
    } else if (dome.equals("displaypart")) {
      String number = session.getRequestParameter("number");
      if (number == null) {
        throw new JwmaException("message.displaypart.missingnumber");
      } else {
        doDisplayMessagePart(session, toInt(number));
        return;
      }
    } else if (dome.equals("move")) {
      String[] messages =
          session.getRequest().getParameterValues("numbers");
      String destination =
          session.getRequestParameter("destination");

      if (messages == null || messages.length == 0) {
        throw new JwmaException("message.move.missingnumbers");
      } else if (destination == null) {
        throw new JwmaException("message.move.missingdestination");
      } else {
        //check if it's about the active message
        if (messages[0].trim().equals("actual")) {
          doMoveActualMessage(session, destination);
        } else {
          doMoveMessages(session, destination, toInts(messages));
        }
      }
    } else if (dome.equals("compose")) {
      String to = session.getRequestParameter("to");
      if (to == null) {
        to = "";
      }
      boolean reply = new Boolean(
          session.getRequestParameter("reply")
      ).booleanValue();
      boolean toall = new Boolean(
          session.getRequestParameter("toall")
      ).booleanValue();
      boolean togglequote = new Boolean(
          session.getRequestParameter("togglequote")
      ).booleanValue();
      //see if its a forward
      boolean forward = new Boolean(
          session.getRequestParameter("forward")
      ).booleanValue();
      //and if attachments should be forwarded
      boolean attfwd = new Boolean(
          session.getRequestParameter("withattachments")
      ).booleanValue();

      doComposeMessage(session, to, forward, reply, toall, togglequote, attfwd);

      return;
    } else if (dome.equals("composedraft")) {
      String number = session.getRequestParameter("number");
      if (number == null) {
        throw new JwmaException("message.draft.missingnumber");
      }
      doComposeDraft(session, toInt(number));
    } else if (dome.equals("delete")) {
      String[] messages =
          session.getRequest().getParameterValues("numbers");
      if (messages == null || messages.length == 0) {
        throw new JwmaException("message.delete.missingnumbers");
      } else {
        //check if it's about the active message
        if (messages[0].trim().equals("actual")) {
          doDeleteActualMessage(session);
        } else {
          doDeleteMessages(session, toInts(messages));
        }
      }
    } else {
      throw new JwmaException("message.action.invalid");
    }
  }//doDispatchMessageActions

  /**
   * Dispatches actions targeting <tt>preferences</tt>.
   *
   * @param session a <tt>JwmaSession</tt> instance.
   * @param dome the task as <tt>String</tt>.
   *
   * @throws JwmaException if it fails to dispatch the request to a
   *         method (i.e. invalid request), or the action method fails
   *         to execute the task.
   */
  private void doDispatchPreferencesActions(
      JwmaSession session, String dome)
      throws JwmaException {

    if (dome.equals("update")) {
      doUpdatePreferences(session);
    } else if (dome.equals("addmailid")) {
      doAddMailIdentity(session);
    } else if (dome.equals("removemailid")) {
      //retrieve the uid
      String uid = session.getRequestParameter("mid.uid");
      if (uid == null) {
        throw new JwmaException("preferences.mailid.missinguid");
      } else {
        doRemoveMailIdentity(session, uid);
        return;
      }
    } else if (dome.equals("updatemailid")) {
      //retrieve the uid
      String uid = session.getRequestParameter("mid.uid");
      if (uid == null) {
        throw new JwmaException("preferences.mailid.missinguid");
      } else {
        doUpdateMailIdentity(session, uid);
        return;
      }
    } else {
      throw new JwmaException("preferences.action.invalid");
    }
  }//doDispatchPreferencesActions


  /*** End Dispatchers *************************************************/

  /*** Session Actions  ************************************************/

  /**
   * Handles a user login to the jwma system.
   * <p>
   * <ul>
   * <li>Checks if a user account exists.</li>
   * <li>Handles account creation according to settings.</li>
   * <li>Initializes session with the post office.</li>
   * <li>Ensures jwma specific folders are there (trash, archives).</li>
   * <li>Redirects the user to the folder view.</li>
   * <ul>
   *
   * @param session a <tt>JwmaSession</tt> instance.
   * @param username of the user as <tt>String</tt>.
   * @param password of the user as <tt>String</tt>.
   * @param post office the name of a post office as <tt>String</tt>.
   *
   * @throws JwmaException if it fails to execute properly.
   */
  private void doLogin(JwmaSession session, String user, String passwd, String postoffice)
      throws JwmaException {
    log.debug("doLogin() called");
    boolean storelogin =
        new Boolean(session.getRequestParameter("remember")).booleanValue();
    JwmaKernel kernel = JwmaKernel.getReference();
    JwmaConfiguration config = kernel.getConfiguration();
    boolean newAccount = false;

    //set post office host, if not allowed or invalid,
    //it will be the system's set default postoffice host
    session.setPostOffice(config.getPostOfficeByName(postoffice));
    log.debug("PostOffice set to: " + session.getPostOffice().getName());

    //get the assembled id, session has no user yet
    String identity = session.getUserIdentity(user);
    //check if user exists (i.e. preferences exist for the user)
    if (!kernel.getPrefsPersistencePlugin().isPersistent(identity)) {
      //get settings to decide strategy
      if (config.isAccountCreationEnabled()) {
        newAccount = true;
      } else {
        //Forward to set view for account creation or
        //for telling how it is done
        session.redirect(JwmaKernel.ACCOUNT_CREATION_VIEW);
      }
    }
    log.debug("identity=" + identity + ":prefsExist=" + (!newAccount));


    //2. user exists for jwma -> authenticate user
    session.authenticate(user, passwd, newAccount);
    log.debug("Authenticated!");
    //we have now a created store
    JwmaStoreImpl store = session.getJwmaStore();
    log.debug("Have Store!");
    //store storeinfo bean
    session.storeBean("jwma.storeinfo", (JwmaStoreInfo) store);
    //store inboxinfo bean
    session.storeBean("jwma.inboxinfo", store.getInboxInfo());
    //store trashinfo bean
    session.storeBean("jwma.trashinfo", store.getTrashInfo());
    //store actual folder
    session.storeBean("jwma.folder", store.getActualFolder());

    //load contacts
    if (kernel.getContactManagementPlugin().isPersistent(session.getPreferences().getContactDatabaseID())) {
      log.debug("Loading contact database.");
      session.storeBean("jwma.contacts",
          kernel.getContactManagementPlugin()
          .loadContacts(session.getPreferences().getContactDatabaseID())
      );
      log.debug("Loaded user's contact database.");
    } else {
      log.debug("Creating contact database.");
      JwmaContactsImpl contacts = kernel.getContactManagementPlugin().createContacts();
      session.getPreferences().setContactDatabaseID(contacts.getUID());
      session.storeBean("jwma.contacts", contacts);
    }
    //add cookie if
      if(storelogin) {
        doSetCookie(session);
      }
    //redirect to folder view
    session.redirect(JwmaKernel.FOLDER_VIEW);

  }//doLogin

  /**
   * Logs a user out of a jwma system, ending the session.
   *
   * @param session a <tt>JwmaSession</tt> instance.
   *
   * @throws JwmaException if it fails to execute properly.
   */
  private void doLogout(JwmaSession session)
      throws JwmaException {

    try {
      //perform cleanup of store
      session.getJwmaStore().cleanup();
      //ensure saving contactsdb
      log.debug("Saving contacts database.");
      JwmaKernel.getReference().getContactManagementPlugin()
          .saveContacts((JwmaContactsImpl) session.getWebSession().getValue("jwma.contacts"));
      //end session
    } catch (JwmaException ex) {
      throw new JwmaException("session.logout.failed").setException(ex);
    } finally {
      session.end();
      session.redirect(JwmaKernel.LOGOUT_VIEW);
    }
  }//doLogout

  /**
   * Redirects a request to a given & possible view,
   * to the last view or to the main view otherwise
   * (defined by the root folder view).
   * <i><b>Note:</b> the model instances are updated!</i>
   *
   * @param session a <tt>JwmaSession</tt> instance.
   * @param view the view as <tt>String</tt>.
   *
   * @throws JwmaException if it fails to execute properly.
   */
  private void doRedirect(JwmaSession session, String view) {
    try {
      if (view != null) {
        //clean whitespace
        view.trim();
        if (view.equals("last")) {
          session.redirectToLast();
          return;
        } else if (view.equals("actual")) {
          session.redirectToActual();
          return;
        } else if (view.equals("folder")) {
          //dispatch to folder display, invoke update
          session.getJwmaStore().getActualFolder().update(session.getJwmaStore());
          session.redirect(JwmaKernel.FOLDER_VIEW);
          return;
        } else if (view.equals("message")) {
          session.storeBean("jwma.message",
              session.getJwmaStore().getActualFolder().getActualMessage()
          );
          session.redirect(JwmaKernel.MESSAGE_VIEW);
        }
      }
      //if fall through here display root folder
      session.storeBean("jwma.folder",
          session.getJwmaStore().resetToRootFolder());
      session.redirect(JwmaKernel.FOLDER_VIEW);
      return;
    } catch (Exception ex) {
      log.error("Failed to redirect.", ex);
      //JwmaKernel.getReference().debugLog().writeStackTrace(ex);

    }
  }//doRedirect

  private void doSetCookie(JwmaSession session)
      throws JwmaException {

    HttpServletResponse res = session.getResponse();
    StringBuffer cracker = new StringBuffer("");
    cracker.append(session.getUsername())
        .append(".")
        .append(MD5.hash(session.getPostOffice().getName()));
    Cookie polly = new Cookie("jwmalogin",cracker.toString());
    polly.setPath("/webmail");
    polly.setMaxAge(COOKIE_EXPIRES);
    res.addCookie(polly);

  }//doSetCookie

  /*** End Session Actions  ***********************************************/


  /*** Folder Actions  ****************************************************/

  /**
   * Prepares a given folder for display and redirects to the
   * folder view.
   *
   * @param session a <tt>JwmaSession</tt> instance.
   * @param foldername the full path of a valid folder of the
   *        actual store.
   *
   * @throws JwmaException if it fails to execute properly.
   */
  private void doDisplayFolder(JwmaSession session, String foldername)
      throws JwmaException {

    JwmaStoreImpl store = session.getJwmaStore();
    //1. check folder existence
    if (!store.checkFolderExistence(foldername)) {
      throw new JwmaException("folder.display.existence");
    }
    //2. store new actual folder, creation delegated to store
    session.storeBean("jwma.folder",
        store.setActualFolder(foldername)
    );

    //redirect to folder view
    session.redirect(JwmaKernel.FOLDER_VIEW);
  }//doDisplayFolder

  /**
   * Sorts the messages in the actual folder according to
   * the given criteria and redirects to the folder view.
   *
   * @param session a <tt>JwmaSession</tt> instance.
   * @param criteria a valid criteria for sorting the messages
   *        in the actual folder.
   *
   * @throws JwmaException if it fails to execute properly.
   */
  private void doSortFolderMessages(JwmaSession session, int criteria)
      throws JwmaException {

    //1. get actual folder reference
    JwmaFolderImpl folder = session.getJwmaStore().getActualFolder();
    //2. ensure it is a folder holding messages
    if (folder.hasMessages()) {
      //3. get and sort the message info list
      folder.getMessageInfoList().sort(criteria);
    }
    //4. remember set sort criteria
    session.getPreferences().setMessageSortCriteria(criteria);

    session.redirect(JwmaKernel.FOLDER_VIEW);
    return;
  }//doSortFolderMessages

  /**
   * Deletes the given folders and redirects to the folder view.
   *
   * @param session a <tt>JwmaSession</tt> instance.
   * @param foldernames an array of strings; each <tt>String</tt>
   *        representing the full path of a valid folder of the
   *        actual store.
   *
   * @throws JwmaException if it fails to execute properly.
   */
  private void doDeleteFolders(JwmaSession session, String[] foldernames)
      throws JwmaException {

    //delete Folders by path
    session.getJwmaStore().deleteFolders(foldernames);

    //redirect to folder view
    session.redirect(JwmaKernel.FOLDER_VIEW);
  }//doDeleteFolders

  /**
   * Creates a folder with a given name and a given type and redirects
   * to the folder view.
   *
   * @param session a <tt>JwmaSession</tt> instance.
   * @param foldername the full path of a possible new folder of the
   *        actual store.
   * @param type the type of the folder to be created as <tt>int</tt>.
   *
   * @throws JwmaException if it fails to execute properly.
   */
  private void doCreateFolder(JwmaSession session,
                              String foldername, int type)
      throws JwmaException {


    //create the folder
    session.getJwmaStore().createFolder(foldername, type);

    //redirect to folder view
    session.redirect(JwmaKernel.FOLDER_VIEW);

  }//doCreateFolder

  /**
   * Moves the given folders to a given destination folder
   * and redirects to the folder view.
   *
   * @param session a <tt>JwmaSession</tt> instance.
   * @param foldernames an array of strings; each <tt>String</tt>
   *        representing the full path of a valid folder of the
   *        actual store.
   * @param destination the full path of a valid folder of the
   *        actual store.
   *
   * @throws JwmaException if it fails to execute properly.
   */
  private void doMoveFolders(JwmaSession session, String[] foldernames,
                             String destname)
      throws JwmaException {

    //move the folders
    session.getJwmaStore().moveFolders(foldernames, destname);
    //redirect to folder view
    session.redirect(JwmaKernel.FOLDER_VIEW);
  }//doMoveFolders

  private void doDisplaySubscribedFolders(JwmaSession session) {
    session.redirect(JwmaKernel.SUBSCRIBED_VIEW);
  }//doDisplaySubscribedFolders

  private void doDisplayUnsubscribedFolders(JwmaSession session) {
    session.redirect(JwmaKernel.UNSUBSCRIBED_VIEW);
  }//doDisplayUnsubscribedFolders

  private void doUnsubscribeFolders(JwmaSession session, String[] foldernames)
    throws JwmaException {

    //Unsubscribe the folders
    session.getJwmaStore().updateFolderSubscription(foldernames,false);
    //redirect to subscription view
    session.redirect(JwmaKernel.SUBSCRIBED_VIEW);
  }//doUnsubscribeFolders

  private void doSubscribeFolders(JwmaSession session, String[] foldernames)
      throws JwmaException {

    //Subscribe the folders
    session.getJwmaStore().updateFolderSubscription(foldernames,true);
    //redirect to subscription view
    session.redirect(JwmaKernel.UNSUBSCRIBED_VIEW);
  }//doUnsubscribeFolders

  /*** End Folder Actions ***********************************************************/

  /*** Message Actions *****************************************************/

  /**
   * Prepares a given message for display and redirects to the
   * message view.
   *
   * @param session a <tt>JwmaSession</tt> instance.
   * @param number of the message to be displayed as <tt>int</tt>.
   *
   * @throws JwmaException if it fails to execute properly.
   */
  private void doDisplayMessage(JwmaSession session, int number)
      throws JwmaException {

    JwmaFolderImpl folder = session.getJwmaStore().getActualFolder();
    //1. check folder existence
    if (!folder.checkMessageExistence(number)) {
      throw new JwmaException("message.display.existence");
    }
    //store newly created bean
    session.storeBean("jwma.message",
        folder.getJwmaMessage(number)
    );

    //redirect to message view
    session.redirect(JwmaKernel.MESSAGE_VIEW);

  }//doDisplayMessage

  /**
   * Displays the next message in the actual folder.
   * If there is no message left, it redirects to the folder view.
   *
   * @param session a <tt>JwmaSession</tt> instance.
   *
   * @throws JwmaException if it fails to execute properly.
   */
  private void doDisplayNextMessage(JwmaSession session)
      throws JwmaException {

    int next = session.getJwmaStore().getActualFolder()
        .getNextMessageNumber();
    //if (next != -1) {
    if (next >= 1) {
      doDisplayMessage(session, next);
    } else {
      //FIXME: folder view?
      session.redirect(JwmaKernel.FOLDER_VIEW);
    }
  }//doDisplayNextMessage

  /**
   * Displays the previous message in the actual folder.
   * If there is no previous message left, it redirects
   * to the folder view.
   *
   * @param session a <tt>JwmaSession</tt> instance.
   *
   * @throws JwmaException if it fails to execute properly.
   */
  private void doDisplayPreviousMessage(JwmaSession session)
      throws JwmaException {

    int previous = session.getJwmaStore().getActualFolder()
        .getPreviousMessageNumber();
    //if (previous != -1) {
    if (previous >= 1) {
      doDisplayMessage(session, previous);
    } else {
      //FIXME: folder view?
      session.redirect(JwmaKernel.FOLDER_VIEW);
    }
  }//doDisplayPreviousMessage

  /**
   * Deletes the active message and redirects either
   * to the next mail (if exists) or to the folder view.
   *
   * @param session a <tt>JwmaSession</tt> instance.
   *
   * @throws JwmaException if it fails to execute properly.
   */
  private void doDeleteActualMessage(JwmaSession session)
      throws JwmaException {

    int next = session.getJwmaStore().getActualFolder()
        .deleteActualMessage();
    //if (next != -1) {
    if (next >= 1) {
      doDisplayMessage(session, next);
    } else {
      //FIXME: folder view?
      session.redirect(JwmaKernel.FOLDER_VIEW);
    }
  }//doDeleteActiveMessage

  /**
   * Deletes the given messages and
   * redirects to the folder view.
   *
   * @param session a <tt>JwmaSession</tt> instance.
   * @param numbers the messages to be deleted as <tt>int[]</tt>.
   *
   * @throws JwmaException if it fails to execute properly.
   */
  private void doDeleteMessages(JwmaSession session, int[] numbers)
      throws JwmaException {

    //do the job
    session.getJwmaStore().getActualFolder().deleteMessages(numbers);

    //redirect to
    //FIXME: really folder view?
    session.redirect(JwmaKernel.FOLDER_VIEW);

  }//doDeleteMessages

  /**
   * Moves the active message to a given destination and
   * redirects either to the next message or to the folder view.
   *
   * @param session a <tt>JwmaSession</tt> instance.
   * @param destination the full path of a valid folder of the
   *        actual store as <tt>String</tt>.
   *
   * @throws JwmaException if it fails to execute properly.
   */
  private void doMoveActualMessage(JwmaSession session, String destination)
      throws JwmaException {

    int next = session.getJwmaStore().getActualFolder()
        .moveActualMessage(destination);
    //if (next != -1) {
    if (next >= 1) {
      doDisplayMessage(session, next);
    } else {
      //FIXME: folder view?
      session.redirect(JwmaKernel.FOLDER_VIEW);
    }
  }//doMoveActiveMessage


  /**
   * Moves the given messages and
   * redirects to the folder view.
   *
   * @param session a <tt>JwmaSession</tt> instance.
   * @param numbers the messages to be moved as <tt>int[]</tt>.
   * @param destination the full path of a valid folder of the
   *        actual store as <tt>String</tt>.
   *
   * @throws JwmaException if it fails to execute properly.
   */
  private void doMoveMessages(JwmaSession session,
                              String destination, int[] numbers)
      throws JwmaException {

    //do the job
    session.getJwmaStore().getActualFolder().moveMessages(numbers, destination);
    //redirect to
    //FIXME: really folder view?
    session.redirect(JwmaKernel.FOLDER_VIEW);
  }//doMoveMessages

  /**
   * Prepares a message model instance for
   * a message to be composed and redirects to
   * the compose view.
   *
   * @param session a <tt>JwmaSession</tt> instance.
   * @param reply flags whether it is a reply or not.
   * @param toall flags whether it is a reply to all senders
   *        or not.
   * @param togglequote flags whether auto-quoting will be toggled for this message.
   * @param attfwd toggles whether attachments will be forwarded.
   *
   * @throws JwmaException if it fails to execute properly.
   */
  private void doComposeMessage(JwmaSession session, String to,
                                boolean forward, boolean reply,
                                boolean toall, boolean togglequote,
                                boolean attfwd)
      throws JwmaException {


    JwmaFolderImpl folder = session.getJwmaStore().getActualFolder();
    JwmaComposeMessage message = null;
    JwmaDisplayMessage actualmsg = null;

    if (reply || forward) {
      actualmsg = (JwmaDisplayMessage) folder.getActualMessage();
    }
    if (reply) {
      message = JwmaComposeMessage.createReply(actualmsg, toall,
          session.getPreferences(), togglequote);
    } else if (forward) {
      message = JwmaComposeMessage.createForward(
          session.getMailSession(), actualmsg, to,
          session.getPreferences(), togglequote, attfwd
      );
    } else {
      message = JwmaComposeMessage.createJwmaComposeMessage(
          session.getMailSession()
      );
      if (to != null) {
        try {
          message.setTo(to);
        } catch (MessagingException mex) {
          //handle probably?
        }
      }
    }

    //store compose message bean
    session.storeBean("jwma.composemessage",
        message
    );

    //redirect to compose view
    session.redirect(JwmaKernel.COMPOSE_VIEW);
  }//doComposeMessage


  /**
   * Prepares a draft for composing and redirects to the
   * compose view.
   *
   * @param session a <tt>JwmaSession</tt> instance.
   * @param number of the draft message to be continued as
   *       <tt>int</tt>.
   *
   * @throws JwmaException if it fails to execute properly.
   */
  private void doComposeDraft(JwmaSession session, int number)
      throws JwmaException {

    JwmaFolderImpl folder = session.getJwmaStore().getActualFolder();
    //1. check folder existence
    if (!folder.checkMessageExistence(number)) {
      throw new JwmaException("message.draft.existence");
    }

    JwmaMessage message = folder.getDraftMessage(number);

    //store newly created bean
    //store compose message bean
    session.storeBean("jwma.composemessage",
        message
    );

    //redirect to compose view
    session.redirect(JwmaKernel.COMPOSE_VIEW);
  }//doComposeDraft


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
      //we do it all for fun or not?
      if (fname == null) {
        fname = "easter.egg";
      }
      session.getResponse().setContentType(type);
      session.getResponse().setHeader("Content-Disposition", "filename=" + fname);

      //3. stream out part
      ServletOutputStream out =
          session.getResponse().getOutputStream();
      folder.writeMessagePart(part.getPart(), out);


    } catch (Exception ex) {
      throw new JwmaException("message.displaypart.failed").setException(ex);
    }
  }//doDisplayMessagePart


  /*** End Message Actions ************************************************/

  /*** Preferences Actions ************************************************/

  /**
   * Updates the preferences for system settings.
   * <p>
   * This method handles first-time login too,
   * just allowing to set firstname and lastname.
   *
   * @param session a <tt>JwmaSession</tt> instance.
   *
   * @throws JwmaException if it fails to execute properly.
   */
  private void doUpdatePreferences(JwmaSession session)
      throws JwmaException {

    JwmaPreferencesImpl prefs = session.getPreferences();
    JwmaStoreImpl store = session.getJwmaStore();
    //if anything is set then to the default
    JwmaMailIdentityImpl mailid = null;


    //collect data
    String firstname = session.getRequestParameter("firstname");
    String lastname = session.getRequestParameter("lastname");
    String quotechar = session.getRequestParameter("quotechar");
    String rootfolder = session.getRequestParameter("rootfolder");
    String trashfolder = session.getRequestParameter("trashfolder");
    String draftfolder = session.getRequestParameter("draftfolder");
    String sentmailarchive = session.getRequestParameter("sentmailarchive");
    String readmailarchive = session.getRequestParameter("readmailarchive");
    String language = session.getRequestParameter("language");
    String msgprocessor = session.getRequestParameter("msgprocessor");
    String dfpattern = session.getRequestParameter("dfpattern");
    String style = session.getRequestParameter("style");

    boolean autoquote = new Boolean(
        session.getRequestParameter("autoquote")).booleanValue();
    boolean autoempty = new Boolean(
        session.getRequestParameter("autoempty")).booleanValue();
    boolean automoveread = new Boolean(
        session.getRequestParameter("automoveread")).booleanValue();
    boolean autoarchivesent = new Boolean(
        session.getRequestParameter("autoarchivesent")).booleanValue();
    boolean expert = new Boolean(
        session.getRequestParameter("expert")).booleanValue();
    boolean inlined = new Boolean(
        session.getRequestParameter("inlined")).booleanValue();

    prefs.setAutoQuote(autoquote);
    prefs.setAutoEmpty(autoempty);
    prefs.setAutoMoveRead(automoveread);
    prefs.setAutoArchiveSent(autoarchivesent);
    prefs.setDisplayingInlined(inlined);

    //if not expert, try to update the default mail identity
    if (!expert && !prefs.isExpert()) {
      //log.debug("updatePreferences():updating mail identity");
      updateMailIdentity(
          session,
          (JwmaMailIdentityImpl) prefs.getMailIdentity()
      );
    }
    prefs.setExpert(expert);

    if (firstname != null) {
      prefs.setFirstname(firstname);
    }
    if (lastname != null) {
      prefs.setLastname(lastname);
    }
    if (quotechar != null) {
      prefs.setQuoteChar(quotechar);
    }

    //update send-mail archive
    if (autoarchivesent) {
      if(!prefs.isExpert()) {
        //set the new folder, will set the prefs
        store.setSentMailFolder(prefs.getSentMailArchive());
      } else {
        //set the new folder, will set the prefs
        store.setSentMailFolder(sentmailarchive);
      }
    }

    if (automoveread) {
      if(!prefs.isExpert()) {
        //set the new folder, will set the prefs
        store.setReadMailFolder(prefs.getReadMailArchive());
      } else {
        //set the new folder, will set the prefs
        store.setReadMailFolder(readmailarchive);
      }
    }

    if(trashfolder != null) {
      store.setTrashFolder(trashfolder);
    }
    if(draftfolder != null) {
      store.setDraftFolder(draftfolder);
    }
    if(rootfolder != null) {
      store.updateRootFolder(rootfolder);
    }

    if (msgprocessor != null) {
      prefs.setMessageProcessorName(msgprocessor);
    }

    if (dfpattern != null) {
      prefs.setDateFormat(new SimpleDateFormat(dfpattern));
    }

    if (language != null) {
      //create locale
      Locale locale = new Locale(language, "");
      //set the locale if it is supported and not the one that
      //is already set
      if (JwmaKernel.getReference().getConfiguration()
              .getI18N().isSupportedViewLocale(locale)
          && !locale.equals(prefs.getLocale())) {
        prefs.setLocale(locale);
        session.setLocale();
      }
    }
    if (style !=null) {
      prefs.setStyle(style);
    }

    //save
    session.savePreferences();
    session.redirect(JwmaKernel.PREFERENCES_VIEW);

  }//doUpdatePreferences

  /**
   * Adds a mail identity.
   *
   * @param session a <tt>JwmaSession</tt> instance.
   *
   * @throws JwmaException if it fails to execute properly.
   */
  private void doAddMailIdentity(JwmaSession session)
      throws JwmaException {
    JwmaPreferencesImpl prefs = session.getPreferences();
    JwmaMailIdentityImpl mid = prefs.createMailIdentity();

    RandomAppendPlugin rap = JwmaKernel.getReference().getRandomAppendPlugin();

    //retrieve parameters
    String name = session.getRequestParameter("mid.name");
    String from = session.getRequestParameter("mid.from");
    boolean defaultmid = new Boolean(
        session.getRequestParameter("mid.default")).booleanValue();

    if (name != null && name.length() > 0) {
      mid.setName(name);
    } else {
      throw new JwmaException("preferences.mailid.noname");
    }
    if (from != null) {
      try {
        InternetAddress.parse(from);
      } catch (AddressException adrex) {
        throw new JwmaException("preferences.update.addressinvalid", true);
      }
      mid.setFrom(from);
    }
    //add the new instance
    prefs.addMailIdentity(mid);

    //and set it as default if requested
    if (defaultmid) {
      prefs.setDefaultMailIdentity(mid.getUID());
    }

    //save
    session.savePreferences();
    //redirect
    session.redirect(JwmaKernel.PREFERENCES_VIEW);
  }//doAddMailIdentity

  /**
   * Removes a mail identity.
   *
   * @param session a <tt>JwmaSession</tt> instance.
   *
   * @throws JwmaException if it fails to execute properly.
   */
  private void doRemoveMailIdentity(JwmaSession session, String uid)
      throws JwmaException {
    JwmaPreferencesImpl prefs = session.getPreferences();

    if (prefs.existsMailIdentity(uid)) {
      //remove the instance
      prefs.removeMailIdentity(uid);
    } else {
      throw new JwmaException("preferences.mailid.invaliduid");
    }

    //save
    session.savePreferences();
    //redirect
    session.redirect(JwmaKernel.PREFERENCES_VIEW);
  }//doRemoveMailIdentity

  /**
   * Updates a mail identity.
   *
   * @param session a <tt>JwmaSession</tt> instance.
   *
   * @throws JwmaException if it fails to execute properly.
   */
  private void doUpdateMailIdentity(JwmaSession session, String uid)
      throws JwmaException {
    JwmaPreferencesImpl prefs = session.getPreferences();

    if (prefs.existsMailIdentity(uid)) {
      //make the update
      updateMailIdentity(
          session,
          (JwmaMailIdentityImpl) prefs.getMailIdentity(uid)
      );
    } else {
      throw new JwmaException("preferences.mailid.invaliduid");
    }


    //save
    session.savePreferences();
    //redirect
    session.redirect(JwmaKernel.PREFERENCES_VIEW);
  }//doUpdateMailIdentity

  private void updateMailIdentity(JwmaSession session, JwmaMailIdentityImpl mid)
      throws JwmaException {

    RandomAppendPlugin rap = JwmaKernel.getReference().getRandomAppendPlugin();
    JwmaPreferencesImpl prefs = session.getPreferences();

    //collect data
    String name = session.getRequestParameter("mid.name");
    String from = session.getRequestParameter("mid.from");
    String replyto = session.getRequestParameter("mid.replyto");
    String signature = session.getRequestParameter("mid.signature");
    String note = session.getRequestParameter("mid.note");
    String rndappendtype = session.getRequestParameter("mid.rndappendtype");
    String relcontact = session.getRequestParameter("mid.relcontact");
    boolean defaultmid = new Boolean(
        session.getRequestParameter("mid.default")).booleanValue();

    boolean autosigning = new Boolean(
        session.getRequestParameter("mid.autosigning")).booleanValue();

    if (name != null) {
      mid.setName(name);
    }
    if (from != null) {
      try {
        InternetAddress.parse(from);
      } catch (AddressException adrex) {
        throw new JwmaException("preferences.update.addressinvalid", true);
      }
      mid.setFrom(from);
    }
    if (replyto != null) {
      try {
        InternetAddress.parse(replyto);
      } catch (AddressException adrex) {
        throw new JwmaException("preferences.update.addressinvalid", true);
      }
      mid.setReplyTo(replyto);
    }
    if (signature != null) {
      mid.setSignature(signature);
    } else {
      mid.setSignature("");
    }
    if (note != null) {
      mid.setNote(note);
    } else {
      mid.setNote("");
    }

    if (rndappendtype != null && rap != null
        && rap.supportsAppendType(rndappendtype,
            prefs.getLocale())) {
      mid.setRandomAppendType(rndappendtype, prefs.getLocale());
    }

    if (relcontact != null) {
      mid.setRelatedContact(relcontact);
    } else {
      mid.setRelatedContact("");
    }
    mid.setAutoSigning(autosigning);

    if (defaultmid) {
      prefs.setDefaultMailIdentity(mid.getUID());
    }
  }//updateMailIdenitity

  /*** End Preferences Actions *******************************************/

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

  private static final int COOKIE_EXPIRES = (60 * 24 * 60 * 60); // d -> h -> min -> s
}//class JwmaController



