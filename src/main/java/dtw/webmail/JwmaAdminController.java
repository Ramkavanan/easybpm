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
import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.log4j.Logger;
import org.apache.log4j.NDC;

import dtw.webmail.util.*;
import dtw.webmail.model.*;
import dtw.webmail.admin.*;

/**
 * Class extending the <tt>HttpServlet</tt> to implement
 * the Admin controller of jwma.
 * <p>
 * Please see the related documentation for more detailed
 * information on process flow and functionality.
 *
 * @author Dieter Wimberger
 * @version 0.9.7 07/02/2003
 */
public class JwmaAdminController extends HttpServlet {

  //logging
  private static Logger log = Logger.getLogger(JwmaAdminController.class);

  /**
   * Initializes the servlet when it is loaded by the
   * servlet engine.
   * <p>This implementation just calls its superclass
   * implementation.
   *
   * @throws ServletException if initialization fails.
   */
  public void init(ServletConfig config)
      throws ServletException {

    super.init(config);
  }//init


  /**
   * Handles the incoming requests.
   * <p>
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
      NDC.push(req.getRemoteHost());
      //1. Handle Session
      HttpSession websession = req.getSession(true);
      Object o = websession.getValue("jwma.emailSession");
      String acton = req.getParameter("acton");
      String dome = req.getParameter("todo");

      //2. Ensure valid jwma session
      JwmaSession session =
          ((o == null)? new JwmaSession(websession, "email"):((JwmaSession) o));
      session.setRequest(req);
      session.setResponse(res);
      session.setWebSession(websession);

      //3. Redirect invalid web sessions
      if (!session.isValidWebSession()) {
        session.redirect(JwmaKernel.LOGIN_VIEW);
      } else {

        //dispatch all actions that need a valid action.
        try {
          if (acton == null || acton.equals("")) {
            throw new JwmaException("request.target.missing");
          } else if (dome == null || dome.equals("")) {
            throw new JwmaException("request.action.missing");
          } else if (acton.equals("system")) {
            doDispatchSystemActions(session, dome);
          } else {
            //ensure authentication & administrator
            session.ensureAuthenticated();
            session.ensureAdministrator();

            if (acton.equals("session")) {
              //doDispatchSessionActions
            } else if (acton.equals("preferences")) {
              //doDispatchPreferencesActions(session,dome);
            } else if (acton.equals("settings")) {
              //doDispatchSettingsActions(session,dome);
            } else {
              throw new JwmaException("request.target.invalid");
            }
          }
        } catch (JwmaException ex) {
          //1. if not authenticated forward to login page
          if (!session.isAuthenticated()) {
            session.redirect(JwmaKernel.LOGIN_VIEW);
          } else {
            String message = ex.getMessage();
            //oneliner resolving of key to message in
            message = JwmaKernel.getReference().getLogMessage("jwma.usererror") +
                JwmaKernel.getReference().getErrorMessage(message);

            //log exception with description and trace if inlined exception
            //available, else with stacktrace.
            if (ex.hasException()) {
              log.error(message, ex.getException());
            } else {
              log.error(message, ex);
            }
            //store error
            session.storeBean("jwma.error", ex);
            //redirect last view with inlined error or
            //to error page.
            if (ex.isInlineError()) {
              session.redirectToActual();
            } else {
              session.redirect(JwmaKernel.ERROR_VIEW);
            }
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
   * @param session a <tt>JwmaAdminSession</tt> instance.
   * @param dome the task as <tt>String</tt>.
   *
   * @throws JwmaException if it fails to dispatch the request to a
   *         method (i.e. invalid request), or the action method fails
   *         to execute the task.
   *
   private void doDispatchSessionActions(JwmaAdminSession session,String dome)
   throws JwmaException {

   if(dome.equals("logout")) {
   doLogout(session);
   } else if(dome.equals("login")) {
   String user=session.getRequest().getParameter("username");
   String passwd=session.getRequest().getParameter("password");
   doLogin(session,user,passwd);
   return;
   } else if(dome.equals("redirect")) {
   String view=session.getRequest().getParameter("view");
   doRedirect(session,view);
   } else {
   throw new JwmaException("session.action.invalid");
   }
   }//doDispatchSessionActions
   */

  /**
   * Dispatches actions targeting the <tt>system</tt>.
   *
   * @param session a <tt>JwmaAdminSession</tt> instance.
   * @param dome the task as <tt>String</tt>.
   *
   * @throws JwmaException if it fails to dispatch the request to a
   *         method (i.e. invalid request), or the action method fails
   *         to execute the task.
   */
  private void doDispatchSystemActions(JwmaSession session, String dome)
      throws JwmaException {

    if (dome.equals("status")) {
      doStatus(session);
    } else {
      throw new JwmaException("session.action.invalid");
    }
  }//doDispatchSystemActions

  /*** End Dispatchers *************************************************/




  /*** Session Actions  ************************************************/

  /**
   * Handles an admin login.
   *
   * @param session actual <tt>JwmaAdminSession</tt> instance.
   * @param username of the user as <tt>String</tt>.
   * @param password of the user as <tt>String</tt>.
   *
   *
   * @throws JwmaException if it fails to execute properly.
   *
   private void doLogin(JwmaAdminSession session,String user, String passwd)
   throws JwmaException {

   if(session.authenticate(user,passwd)) {
   session.redirect(JwmaKernel.ADMIN_MENU_VIEW);
   } else {
   throw new JwmaException("session.login.authentication");
   }
   }//doLogin

   /**
   * Logs the admin out of the admin session.
   *
   * @param session a <tt>JwmaAdminSession</tt> instance.
   *
   * @throws JwmaException if it fails to execute properly.
   *
   private void doLogout(JwmaAdminSession session)
   throws JwmaException {

   try{
   //remove authentication and beans
   session.end();
   session.redirect(JwmaKernel.ADMIN_MENU_VIEW);
   } catch (Exception ex) {
   throw new JwmaException("session.logout.failed");
   }
   }//doLogout
   */

  /**
   * Redirects a request to a given & possible view,
   * to the last view or to the menu view otherwise
   * <i><b>Note:</b> the bean instances are updated!</i>
   *
   * @param session a <tt>JwmaAdminSession</tt> instance.
   * @param view the view as <tt>String</tt>.
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
        } else if (view.equals("preferences")) {

          return;
        } else if (view.equals("settings")) {
          return;
        } else if (view.equals("menu")) {
          session.redirect(JwmaKernel.ADMIN_MENU_VIEW);
          return;
        }
      }
      //if fall through here display menu
      session.redirect(JwmaKernel.ADMIN_MENU_VIEW);
      return;
    } catch (Exception ex) {
      //?
      //JwmaKernel.getReference().debugLog().writeStackTrace(ex);
    }
  }//doRedirect


  /*** End Session Actions  ***********************************************/

  /**
   * Prepares for displaying the status page.
   *
   * @param session a <tt>JwmaAdminSession</tt> instance.
   *
   * @throws JwmaException if it fails to execute properly.
   */
  private void doStatus(JwmaSession session)
      throws JwmaException {

    if (JwmaKernel.getReference().isJwmaStatusEnabled()) {
      session.storeBean("jwma.admin.status", JwmaStatus.getReference());
      session.redirect(JwmaKernel.ADMIN_STATUS_VIEW);
    } else {
      throw new JwmaException("system.status.disabled");
    }

  }//doStatus

  /*** System Actions  ****************************************************/



  /*** End System Actions  ************************************************/


  /**
   * Returns servlet info as <tt>String</tt>.
   *
   * @return Info about this servlet as <tt>String</tt>.
   */
  public String getServletInfo() {
    return "jwma (Java WebMail) AdminController Servlet";
  }//getServletInfo()

}//class JwmaAdminController