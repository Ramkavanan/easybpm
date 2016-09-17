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
import javax.servlet.*;
import javax.servlet.http.*;
import javax.mail.*;
import javax.mail.internet.*;

import org.apache.log4j.Logger;
import org.apache.log4j.NDC;

import dtw.webmail.model.*;
import dtw.webmail.util.*;
import dtw.webmail.plugin.RandomAppendPlugin;

/**
 * Class extending the <tt>HttpServlet</tt> to implement
 * the SendMail controller of jwma.
 * <p>
 * Please see the related documentation for more detailed
 * information on process flow and functionality.
 *
 * @author Dieter Wimberger
 * @version 0.9.7 07/02/2003
 */
public class JwmaSendMail extends HttpServlet {

  //logging
  private static Logger log = Logger.getLogger(JwmaSendMail.class);


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
   * This implementation ensures authenticated session
   * existence and just calls the only supported action
   * (sendmessage).
   * <p>
   * <i><b>Note:</b>incoming data should be <tt>multipart/form-data</tt>
   * encoded.</i>
   * <p>
   * Application related errors/exceptions are handled internally
   * by forwarding the request to an error page (or maybe inlining
   * error information somewhen).
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
      //1. Handle websession
      HttpSession websession = req.getSession(true);
      Object o = websession.getValue("jwma.emailSession");

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
        //dispatch send message
        try {
          //ensure authenticated jwma session
          session.ensureAuthenticated();
          doSendMessage(session);
        } catch (JwmaException ex) {
          //1. if not authenticated forward to login page
          if (!session.isAuthenticated()) {
            session.redirect(JwmaKernel.LOGIN_VIEW);
          } else {
            String message = ex.getMessage();
            if (message.equals("stacked")) {
              message = JwmaKernel.getReference()
                  .getLogMessage("jwma.usererror");
              for (Iterator iter = ex.iterator(); iter.hasNext();) {
                message += "::" + JwmaKernel.getReference()
                    .getErrorMessage((String) iter.next());
              }
            } else {
              //oneliner resolving of key to message in
              message = JwmaKernel.getReference().getLogMessage("jwma.usererror") +
                  JwmaKernel.getReference().getErrorMessage(message);
              //log exception with description and trace if inlined exception
              //available, else with stacktrace.
            }
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

  /**
   * Handles the multipart/form-data request and sends the message.
   */
  private void doSendMessage(JwmaSession session)
      throws JwmaException {

    boolean to_missing = false;
    boolean receivers_invalid = false;
    boolean subject_invalid = false;
    boolean body_invalid = false;
    boolean content_missing = false;
    boolean signature_selection_invalid = false;
    StringBuffer tobuf = new StringBuffer(50);
    
    MultipartRequest multi = null;

    try {
      //JwmaKernel.getReference().debugLog().write("Going to parse Multipart request..");

      //retrieve contacts database
      JwmaContactsImpl ctdb =
          (JwmaContactsImpl) session.getWebSession().getValue("jwma.contacts");

      //Handle the incoming request
      multi = new MultipartRequest(session.getRequest(), session.getMTA().getTransportLimit());

      boolean savedraft = multi.getParameter("savedraft").equals("true");
      //retrieve all necessary parameters into local strings
      //recipients
      String to = multi.getParameter("to");
      String cc = multi.getParameter("ccto");
      String bcc = multi.getParameter("bccto");
      //subject
      String subject = multi.getParameter("subject");
      //sign
      boolean toggleautosign = new Boolean(
          multi.getParameter("toggleautosign")).booleanValue();
      boolean togglerndappend = new Boolean(
          multi.getParameter("togglerndappend")).booleanValue();

      //body
      String body = multi.getParameter("body");
      //mail identity changed?
      String mailid = multi.getParameter("mailidentity");


      //Get the preferences from the userdata
      JwmaPreferencesImpl prefs = session.getPreferences();

      RandomAppendPlugin rap = JwmaKernel.getReference().getRandomAppendPlugin();

      //check out the message itself
      JwmaComposeMessage message = (JwmaComposeMessage)
          session.retrieveBean("jwma.composemessage");


      //open draft message
      if (savedraft) {
        message.openDraft(session.getJwmaStore());
      }

      //get the mail identity
      JwmaMailIdentity mailidentity = prefs.getMailIdentity(mailid);

      //set sender identity
      String from = mailidentity.getFrom();
      if (from == null || from.length() == 0) {
        //set default
        tobuf.delete(0, tobuf.length());
        from = tobuf.append(prefs.getFirstname())
            .append(" ")
            .append(prefs.getLastname())
            .append(" <")
            .append(prefs.getUserIdentity())
            .append(">").toString();

        //from = prefs.getFirstname() + " " + prefs.getLastname() +
        //    " <" + prefs.getUserIdentity() + ">";
      } else {
        //format it nicely
        tobuf.delete(0, tobuf.length());
        from = tobuf.append(prefs.getFirstname())
            .append(" ")
            .append(prefs.getLastname())
            .append(" <")
            .append(from)
            .append(">").toString();

        // from = prefs.getFirstname() + " " + prefs.getLastname() +
        //     " <" + from + ">";
      }
      message.setFrom(from);

      //Set a Reply-To Address
      String repto = mailidentity.getReplyTo();
      if (repto != null && !repto.equals("")) {
        //format it nicely
        tobuf.delete(0, tobuf.length());
        repto = tobuf.append(prefs.getFirstname())
            .append(" ")
            .append(prefs.getLastname())
            .append(" <")
            .append(repto)
            .append(">").toString();

        //repto = prefs.getFirstname() + " " + prefs.getLastname() +
        //    " <" + repto + ">";
        message.setReplyTo(repto);
      }


      //Set all recipients
      try {
        if (to != null && !to.trim().equals("")) {
          if (isNickname(to)) {
            to = to.substring(1, to.length());
            String[] nicks = StringUtil.split(to, ",");
            if (nicks.length > 1) {
              message.setInvisibleToList(true);
            }
            tobuf.delete(0, tobuf.length());
            for (int i = 0; i < nicks.length; i++) {
              JwmaContact ct = ctdb.getContactByNickname(nicks[i]);
              tobuf.append(ct.getFirstname())
                  .append(" ")
                  .append(ct.getLastname())
                  .append(" <")
                  .append(ct.getEmail())
                  .append(">");
              if (i < (nicks.length - 1)) {
                tobuf.append(",");
              }
            }
            to = tobuf.toString();
          } else if (isGroup(to)) {
            if (isInvisibleGroup(to)) {
              message.setInvisibleToList(true);
              to = to.substring(0, to.length() - 2);
            } else {
              to = to.substring(0, to.length() - 3);
            }
            if (ctdb.containsContactGroupName(to)) {
              JwmaContact[] cts = ctdb.getContactGroupByName(to).listContacts();
              tobuf.delete(0, tobuf.length());
              for (int i = 0; i < cts.length; i++) {
                tobuf.append(cts[i].getFirstname())
                    .append(" ")
                    .append(cts[i].getLastname())
                    .append(" <")
                    .append(cts[i].getEmail())
                    .append(">");
                if (i < (cts.length - 1)) {
                  tobuf.append(",");
                }
              }
              to = tobuf.toString();
            }
          }
          message.setTo(to);
        } else {
          //flag error
          to_missing = true;
        }
        if (cc != null) {
          message.setCCTo(cc);
        }
        if (bcc != null) {
          message.setBCCTo(bcc);
        }

      } catch (MessagingException mex) {
        log.debug("Setting receivers:", mex);
        receivers_invalid = true;
      }

      //Set subject
      if (subject != null) {
        message.setSubject(subject);

      } else {
        subject_invalid = true;
      }
      if (body != null) {
        message.setBody(body);
      } else {
        body_invalid = true;
      }
      if ((body != null && body.length() == 0) && (subject != null && subject.length() == 0)) {
        content_missing = true;
      }


      //Handle attachments
      if (multi.hasAttachments()) {
        message.addAttachments(multi.getAttachments());
      }

      if (to_missing || receivers_invalid || subject_invalid
          || body_invalid || content_missing) {

        JwmaException jex = new JwmaException("stacked");

        if (to_missing) {
          jex.addDescription("sm.receivers.tomissing");
        }
        if (receivers_invalid) {
          jex.addDescription("sm.receivers.invalid");
        }
        if (subject_invalid) {
          jex.addDescription("sm.subject.missing");
        }
        if (body_invalid) {
          jex.addDescription("sm.body.missing");
        }
        if (content_missing) {
          jex.addDescription("sm.content.missing");
        }
        throw jex;
      }

      if (mailidentity.isAutoSigning() != toggleautosign) {
        String signature = mailidentity.getSignature();
        if (signature != null && signature.length() > 0) {
          signature = "\n" + signature;
          message.appendBody(signature);
        }
      }

      //Allow saving of message draft
      if (savedraft) {
        message.closeDraft(session.getJwmaStore());
      } else {

        //Handle random append
        if (rap != null && mailidentity.isRandomAppendAllowed() != togglerndappend) {
          message.appendBody(
              rap.getRandomAppend(mailidentity.getRandomAppendType(), prefs.getLocale())
          );
        }

        //Now send message
        message.send(session);

        //Archive message if necessary
        session.getJwmaStore().archiveSentMail(message.getMessage());
      }

      //a little bit of magic :)
      session.redirectToLast();

    } catch (IOException ioex) {
      throw new JwmaException(
          "sm.attachments.gatheringfailed"
      ).setException(ioex);
    } finally {
      //schedule a gc, if there were attachments, could free
      //up to max allowed transport size of mem!
      if (multi != null && multi.hasAttachments()) {
        System.gc();
      }
    }

  }//doSendMessage

  /**
   * Converts a <tt>String</tt> into an <tt>int</tt>.
   */
  private int toInt(String number) throws JwmaException {
    try {
      return Integer.parseInt(number.trim());
    } catch (Exception ex) {
      throw new JwmaException("jwma.number.format");
    }
  }//toInt

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

  /**
   * Returns servlet info as <tt>String</tt>.
   *
   * @return Info about this servlet as <tt>String</tt>.
   */
  public String getServletInfo() {
    return "jwma (Java WebMail) SendMail Servlet";
  }//getServletInfo()

}//JwmaSendMail
