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

import org.apache.log4j.Logger;
import org.apache.log4j.NDC;

import dtw.webmail.util.*;
import dtw.webmail.model.*;

public class JwmaContactsController
    extends HttpServlet {

  //logging
  private static Logger log = Logger.getLogger(JwmaContactsController.class);


  /**
   * Handles the incoming requests.
   * <p>
   * This implementation ensures authenticated session
   * existence, retrieves the <tt>acton</tt>
   * and <tt>todo</tt> parameters, and dispatches all
   * valid actions to the target dispatchers.
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
      NDC.push(req.getRemoteHost());
      //1. Handle session
      HttpSession websession = req.getSession(true);
      Object o = websession.getValue("jwma.emailSession");
      Object c = websession.getValue("jwma.contacts");
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


      //3. prepare the contact database
      JwmaContactsImpl ctdb =
          ((c == null) ? null: ((JwmaContactsImpl) c));

      //dispatch actions
      try {
        if (acton == null || acton.equals("")) {
          throw new JwmaException("request.target.missing");
        } else if (dome == null || dome.equals("")) {
          throw new JwmaException("request.action.missing");
        } else {
          //ensure authentication
          session.ensureAuthenticated();
          //session.ensurePostOfficeConnection();

          if (acton.equals("database")) {
            doDispatchDatabaseActions(session, dome, ctdb);
          } else if (acton.equals("group")) {
            doDispatchGroupActions(session, dome, ctdb);
          } else if (acton.equals("contact")) {
            doDispatchContactActions(session, dome, ctdb);
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
    } finally {
      NDC.pop();
    }
  }//service

  /*** Dispatchers ***************************************************/

  /**
   * Dispatches actions targeting a <tt>JwmaContactsImpl</tt>.
   *
   * @param session a <tt>JwmaSession</tt> instance.
   * @param dome the task as <tt>String</tt>.
   * @param ctdb a <tt>JwmaContactsImpl</tt> instance.
   *
   * @throws JwmaException if it fails to dispatch the request to a
   *         method (i.e. invalid request), or the action method fails
   *         to execute the task.
   */
  private void doDispatchDatabaseActions(
      JwmaSession session, String dome, JwmaContactsImpl ctdb)
      throws JwmaException {

    if (dome.equals("display")) {
      doDisplayDatabase(session, ctdb);
    } else {
      //ensure ctdb loaded
      if (ctdb == null) {
        throw new JwmaException("database.action.notloaded");
      }

      if (dome.equals("store")) {
        doStoreDatabase(session, ctdb);
      } else if (dome.equals("export")) {
        //check type
        //do export in type
      } else if (dome.equals("setfilter")) {
        String filtertype = session.getRequestParameter("filtertype");
        if (filtertype == null || filtertype.length() == 0) {
          throw new JwmaException("database.action.filtertypemissing");
        }
        String filter = session.getRequestParameter("filter");
        if (filter == null || filter.length() == 0) {
          throw new JwmaException("database.action.filtermissing");
        }
        doSetContactsFilter(session, ctdb, filtertype, filter);
      } else {
        throw new JwmaException("database.action.invalid");
      }
    }
  }//doDispatchDatabaseActions

  /**
   * Dispatches actions targeting a <tt>ContactGroup</tt>.
   *
   * @param session a <tt>JwmaSession</tt> instance.
   * @param dome the task as <tt>String</tt>.
   * @param ctdb a <tt>JwmaContactsImpl</tt> instance.
   *
   * @throws JwmaException if it fails to dispatch the request to a
   *         method (i.e. invalid request), or the action method fails
   *         to execute the task.
   */
  private void doDispatchGroupActions(
      JwmaSession session, String dome, JwmaContactsImpl ctdb)
      throws JwmaException {
    log.debug("Dispatching group action=" + dome);
    String[] gids = session.getRequestParameters("group.id");

    if (dome.equals("display")) {
      if (gids == null || gids[0] == null || gids[0].equals("")) {
        throw new JwmaException("group.action.gidmissing");
      } else {
        doDisplayGroup(session, ctdb, gids[0]);
      }
    } else if (dome.equals("edit")) {
      if (gids == null || gids[0] == null || gids[0].equals("")) {
        throw new JwmaException("group.action.gidmissing");
      } else {
        doEditGroup(session, ctdb, gids[0]);
      }
    } else if (dome.equals("update")) {
      if (gids == null || gids[0] == null || gids[0].equals("")) {
        throw new JwmaException("group.action.gidmissing");
      }
      doUpdateGroup(session, ctdb, gids[0]);
    } else if (dome.equals("create")) {
      doCreateGroup(session, ctdb);
    } else if (dome.equals("delete")) {
      if (gids == null || gids.length == 0) {
        throw new JwmaException("group.action.gidmissing");
      }
      doDeleteGroups(session, ctdb, gids);
    } else if (dome.equals("removecontacts")) {
      if (gids == null || gids[0] == null || gids[0].equals("")) {
        throw new JwmaException("group.action.gidmissing");
      }
      String[] cids = session.getRequestParameters("contact.member.id");
      if (cids == null || cids.length == 0) {
        throw new JwmaException("group.action.cidmissing");
      }
      doRemoveContacts(session, ctdb, gids[0], cids);
    } else if (dome.equals("addcontacts")) {
      if (gids == null || gids[0] == null || gids[0].equals("")) {
        throw new JwmaException("group.action.gidmissing");
      }
      String[] cids = session.getRequestParameters("contact.nomember.id");
      if (cids == null || cids.length == 0) {
        throw new JwmaException("group.action.cidmissing");
      }
      doAddContacts(session, ctdb, gids[0], cids);
    } else {
      throw new JwmaException("group.action.invalid");
    }
  }//doDispatchGroupActions

  /**
   * Dispatches actions targeting a <tt>Contact</tt>.
   *
   * @param session a <tt>JwmaSession</tt> instance.
   * @param dome the task as <tt>String</tt>.
   * @param ctdb a <tt>JwmaContactsImpl</tt> instance.
   *
   * @throws JwmaException if it fails to dispatch the request to a
   *         method (i.e. invalid request), or the action method fails
   *         to execute the task.
   */
  private void doDispatchContactActions(
      JwmaSession session, String dome, JwmaContactsImpl ctdb)
      throws JwmaException {

    if (dome.equals("create")) {
      doUpdateContact(session, ctdb, null);
    } else if (dome.equals("import")) {
      Object ct = session.getWebSession().getValue("jwma.contacts.import");
      if (ct == null) {
        throw new JwmaException("contact.action.importmissing");
      }
      doImportContact(session, ctdb, (JwmaContactImpl) ct);
    } else {
      String[] cids = session.getRequestParameters("contact.id");
      if (cids == null || cids[0] == null || cids[0].equals("")) {
        throw new JwmaException("contact.action.idmissing");
      }
      if (dome.equals("display")) {
        doDisplayContact(session, ctdb, cids[0]);
      } else if (dome.equals("edit")) {
        doEditContact(session, ctdb, cids[0]);
      } else if (dome.equals("update")) {
        doUpdateContact(session, ctdb, cids[0]);
      } else if (dome.equals("delete")) {
        doDeleteContacts(session, ctdb, cids);
      } else {
        throw new JwmaException("contact.action.invalid");
      }
    }
  }//doDispatchContactActions


  /*** BEGIN database actions **********************************************************/

  /**
   * Prepares the user's contact database and redirects him to the contacts
   * view.
   *
   * @param session a <tt>JwmaSession</tt> instance.
   * @param ctdb a <tt>JwmaContactsImpl</tt> instance.
   *
   * @throws JwmaException if it fails to execute properly.
   */
  private void doDisplayDatabase(JwmaSession session, JwmaContactsImpl ctdb)
      throws JwmaException {

    session.redirect(JwmaKernel.CONTACTS_VIEW);
    return;
  }//doDisplayDatabase

  /**
   * Stores the user's contact database and redirects him to the contacts
   * view.
   *
   * @param session a <tt>JwmaSession</tt> instance.
   * @param ctdb a <tt>JwmaContactsImpl</tt> instance.
   *
   * @throws JwmaException if it fails to execute properly.
   */
  private void doStoreDatabase(JwmaSession session, JwmaContactsImpl ctdb)
      throws JwmaException {

    JwmaKernel.getReference().getContactManagementPlugin()
        .saveContacts(ctdb);

    session.redirect(JwmaKernel.CONTACTS_VIEW);
    return;
  }//doStoreDatabase

  private void doSetContactsFilter(JwmaSession session,
                                   JwmaContactsImpl ctdb,
                                   String filtertype,
                                   String filter)
      throws JwmaException {

    //log.debug("Setting filter type="+filtertype+" value="+filter);
    if (filtertype.equals("alphabetic")) {
      if (filter.equals("none")) {
        ctdb.setContactFilter(null);
        //log.debug("Alphabetic filter reset.");
      } else {
        ctdb.setContactFilter(new LastnameStartsWithFilter(filter));
        //log.debug("Alphabetic filter set to "+filter);
      }

    } else if (filtertype.equals("category")) {
      if (filter.equals("none")) {
        ctdb.setCategoryFilter("");
      } else {
        ctdb.setCategoryFilter(filter);
      }
    } else {
      throw new JwmaException("database.setfilter.uknowntype");
    }
    session.redirectToActual();
    //redirect(JwmaKernel.CONTACTS_VIEW);
    return;

  }//doSetContactsFilter



  /*** END database actions ************************************************************/

  /*** BEGIN group actions ************************************************************/

  /**
   * Displays a contact group entry from the contact database.
   *
   * @param session a <tt>JwmaSession</tt> instance.
   * @param ctdb a <tt>JwmaContactsImpl</tt> instance.
   * @param cid a contact identifier as <tt>String</tt>.
   *
   * @throws JwmaException if it fails to execute properly.
   */
  private void doDisplayGroup(JwmaSession session,
                              JwmaContactsImpl ctdb,
                              String cid)
      throws JwmaException {

    if (ctdb.containsContactGroup(cid)) {
      session.storeBean("jwma.contacts.group", ctdb.getContactGroup(cid));
    } else {
      throw new JwmaException("group.action.invalidgroup", true);
    }
    session.redirect(JwmaKernel.CONTACTGROUP_VIEW);
    return;
  }//doDisplayGroup

  /**
   * Displays a contact entry from the contact database for editing.
   *
   * @param session a <tt>JwmaSession</tt> instance.
   * @param ctdb a <tt>JwmaContactsImpl</tt> instance.
   * @param cid a contact identifier as <tt>String</tt>.
   *
   * @throws JwmaException if it fails to execute properly.
   */
  private void doEditGroup(JwmaSession session,
                           JwmaContactsImpl ctdb,
                           String cid)
      throws JwmaException {

    if (ctdb.containsContactGroup(cid)) {
      session.storeBean("jwma.contacts.group", ctdb.getContactGroup(cid));
    } else {
      throw new JwmaException("group.action.invalidgroup", true);
    }
    session.redirect(JwmaKernel.CONTACTGROUP_EDIT_VIEW);
    return;
  }//doEditGroup

  /**
   * Updates a group entry in the contact database.
   *
   * @param session a <tt>JwmaSession</tt> instance.
   * @param ctdb a <tt>JwmaContactsImpl</tt> instance.
   *
   * @throws JwmaException if it fails to execute properly.
   */
  private void doUpdateGroup(JwmaSession session,
                             JwmaContactsImpl ctdb,
                             String id)
      throws JwmaException {


    //check if id exists
    if (ctdb.containsContactGroup(id)) {
      String name = session.getRequestParameter("group.name");
      String comments = session.getRequestParameter("group.comments");

      //check if groupname already exists
      if (ctdb.containsContactGroupName(name) && !name.equals(ctdb.getContactGroup(id).getName())) {
        throw new JwmaException("group.name.duplicate");
      } else {
        JwmaContactGroupImpl group = (JwmaContactGroupImpl)
            ctdb.getContactGroup(id);
        //set name if not null, or empty string
        if (name != null && !name.equals("") && !name.equals(group.getName())) {
          group.setName(name);
        }
        group.setComments(comments);
      }
    } else {
      throw new JwmaException("group.action.invalidgroup", true);
    }
    session.redirect(JwmaKernel.CONTACTGROUP_VIEW);
    return;
  }//doUpdateGroup

  /**
   * Creates a group entry in the contact database.
   *
   * @param session a <tt>JwmaSession</tt> instance.
   * @param ctdb a <tt>JwmaContactsImpl</tt> instance.
   *
   * @throws JwmaException if it fails to execute properly.
   */
  private void doCreateGroup(JwmaSession session, JwmaContactsImpl ctdb)
      throws JwmaException {

    //1. retrieve name and note
    String name = session.getRequestParameter("group.name");
    String comments = session.getRequestParameter("group.comments");
    log.debug("Creating group name=" + name + " " + " comments=" + comments);
    //check if groupname already exists
    if (ctdb.containsContactGroupName(name)) {
      throw new JwmaException("group.name.duplicate", true);
    } else {
      //check name if not null, or empty string
      if (name != null && !name.equals("")) {
        //create a new group in the users database
        JwmaContactGroupImpl group = ctdb.createContactGroup(name);
        group.setComments(comments);
        ctdb.addContactGroup(group);
        session.storeBean("jwma.contacts.group", group);
      }
    }
    JwmaKernel.getReference().getContactManagementPlugin()
        .saveContacts(ctdb);

    session.redirect(JwmaKernel.CONTACTGROUP_EDIT_VIEW);
    return;
  }//doCreateGroup

  /**
   * Deletes group entries from the contact database.
   *
   * @param session a <tt>JwmaSession</tt> instance.
   * @param ctdb a <tt>JwmaContactsImpl</tt> instance.
   *
   * @throws JwmaException if it fails to execute properly.
   */
  private void doDeleteGroups(JwmaSession session,
                              JwmaContactsImpl ctdb,
                              String[] gids)
      throws JwmaException {

    log.debug("Deleting groups...");
    for (int i = 0; i < gids.length; i++) {
      JwmaContactGroupImpl group = (JwmaContactGroupImpl)
          ctdb.getContactGroup(gids[i]);
      if (group != null) {
        log.debug("Deleting " + gids[i]);
        ctdb.removeContactGroup(group);
      } else {
        throw new JwmaException("group.action.invalidgroup", true);
      }
    }
    JwmaKernel.getReference().getContactManagementPlugin()
        .saveContacts(ctdb);
    session.redirect(JwmaKernel.CONTACTS_VIEW);
    return;
  }//doDeleteGroups

  /**
   * Adds contacts to a group entry in the contact database.
   *
   * @param session a <tt>JwmaSession</tt> instance.
   * @param ctdb a <tt>JwmaContactsImpl</tt> instance.
   *
   * @throws JwmaException if it fails to execute properly.
   */
  private void doAddContacts(JwmaSession session,
                             JwmaContactsImpl ctdb,
                             String gid,
                             String[] cids)
      throws JwmaException {

    //check if id exists
    if (ctdb.containsContactGroup(gid)) {
      //retrieve group
      JwmaContactGroupImpl group = (JwmaContactGroupImpl)
          ctdb.getContactGroup(gid);
      //add contacts
      for (int i = 0; i < cids.length; i++) {
        log.debug(
            "Trying to add #" + i + " is in database=" + ctdb.containsContact(cids[i]) + " with id=" + ctdb.getContact(cids[i])
        );
        if (ctdb.containsContact(cids[i])) {
          group.addContact((JwmaContactImpl) ctdb.getContact(cids[i]));
        } else {
          throw new JwmaException("contact.action.invalidcontact", true);
        }
      }
    } else {
      throw new JwmaException("group.action.invalidgroup", true);
    }

    session.redirect(JwmaKernel.CONTACTGROUP_EDIT_VIEW);
    return;
  }//doAddContacts


  /**
   * Removes contacts from a group entry in the contact database.
   *
   * @param session a <tt>JwmaSession</tt> instance.
   * @param ctdb a <tt>JwmaContactsImpl</tt> instance.
   *
   * @throws JwmaException if it fails to execute properly.
   */
  private void doRemoveContacts(JwmaSession session,
                                JwmaContactsImpl ctdb,
                                String gid,
                                String[] cids)
      throws JwmaException {

    //check if id exists
    if (ctdb.containsContactGroup(gid)) {
      //retrieve group
      JwmaContactGroupImpl group = (JwmaContactGroupImpl)
          ctdb.getContactGroup(gid);
      //add contacts
      for (int i = 0; i < cids.length; i++) {
        log.debug(
            "Trying to remove #" + i + " is in database=" + ctdb.containsContact(cids[i]) + " with id=" + cids[i])
            ;
        if (ctdb.containsContact(cids[i]) && group.containsContact(cids[i])) {
          group.removeContact((JwmaContactImpl) ctdb.getContact(cids[i]));
        } else {
          throw new JwmaException("contact.action.invalidcontact");
        }
      }
    } else {
      throw new JwmaException("group.action.invalidgroup", true);
    }
    session.redirect(JwmaKernel.CONTACTGROUP_EDIT_VIEW);
    return;
  }//doDeleteContacts

  /*** END group actions ************************************************************/

  /*** BEGIN contact actions **********************************************************/

  /**
   * Displays a contact entry from the contact database.
   *
   * @param session a <tt>JwmaSession</tt> instance.
   * @param ctdb a <tt>JwmaContactsImpl</tt> instance.
   * @param cid a contact identifier as <tt>String</tt>.
   *
   * @throws JwmaException if it fails to execute properly.
   */
  private void doDisplayContact(JwmaSession session,
                                JwmaContactsImpl ctdb,
                                String cid)
      throws JwmaException {

    if (ctdb.containsContact(cid)) {
      session.storeBean(
          "jwma.contacts.contact",
          ctdb.getContact(cid)
      );
    } else {
      throw new JwmaException("contact.action.invalidcontact", true);
    }
    session.redirect(JwmaKernel.CONTACT_VIEW);
    return;
  }//doDisplayContact

  /**
   * Displays a contact entry from the contact database for editing.
   *
   * @param session a <tt>JwmaSession</tt> instance.
   * @param ctdb a <tt>JwmaContactsImpl</tt> instance.
   * @param cid a contact identifier as <tt>String</tt>.
   *
   * @throws JwmaException if it fails to execute properly.
   */
  private void doEditContact(JwmaSession session,
                             JwmaContactsImpl ctdb,
                             String cid)
      throws JwmaException {

    if (ctdb.containsContact(cid)) {
      session.storeBean(
          "jwma.contacts.contact",
          ctdb.getContact(cid)
      );
    } else {
      throw new JwmaException("contact.action.invalidcontact", true);
    }
    session.redirect(JwmaKernel.CONTACT_EDIT_VIEW);
    return;
  }//doEditContact

  /**
   * Updates or creates a contact entry in the contact database.
   *
   * @param session a <tt>JwmaSession</tt> instance.
   * @param ctdb a <tt>JwmaContactsImpl</tt> instance.
   * @param cid a contact identifier as <tt>String</tt>.
   *
   * @throws JwmaException if it fails to execute properly.
   */
  private void doUpdateContact(JwmaSession session,
                               JwmaContactsImpl ctdb,
                               String cid)
      throws JwmaException {

    JwmaContactImpl ct = null;
    boolean create = false;

    if (cid == null) {
      ct = ctdb.createContact();
      log.debug("Created new contact=" + ct.getUID());
      create = true;
    } else {
      if (ctdb.containsContact(cid)) {
        ct = (JwmaContactImpl) ctdb.getContact(cid);
      } else {
        throw new JwmaException("contact.action.invalidcontact");
      }
    }

    String category = session.getRequestParameter("category");
    String newcategory = session.getRequestParameter("_category");
    if (category != null && category.length() > 0 && newcategory != null &&
        !ctdb.existsContactCategory(category) && newcategory.length() > 0) {
      category = newcategory;
      ctdb.addContactCategory(newcategory);
    }

    String firstname = session.getRequestParameter("firstname");
    String lastname = session.getRequestParameter("lastname");
    String middlename = session.getRequestParameter("middlename");
    String nickname = session.getRequestParameter("nickname");

    String company = session.getRequestParameter("company");
    String title = session.getRequestParameter("title");
    String role = session.getRequestParameter("role");


    //phone numbers
    String homenum = session.getRequestParameter("phone.home");
    String worknum = session.getRequestParameter("phone.work");
    String pagernum = session.getRequestParameter("phone.pager");
    String faxnum = session.getRequestParameter("phone.fax");
    String mobilenum = session.getRequestParameter("phone.mobile");

    //primary location
    boolean primlocation = new Boolean(
        session.getRequestParameter("primary.location")).booleanValue();

    //work
    String workstreet = session.getRequestParameter("work.street");
    String workcity = session.getRequestParameter("work.city");
    String workregion = session.getRequestParameter("work.region");
    String workcountry = session.getRequestParameter("work.country");
    String workzip = session.getRequestParameter("work.zip");

    //home
    String homestreet = session.getRequestParameter("home.street");
    String homecity = session.getRequestParameter("home.city");
    String homeregion = session.getRequestParameter("home.region");
    String homecountry = session.getRequestParameter("home.country");
    String homezip = session.getRequestParameter("home.zip");

    //internet
    String email = session.getRequestParameter("email.primary");
    String altemail = session.getRequestParameter("email.alternate");
    String url = session.getRequestParameter("personal.url");
    String compurl = session.getRequestParameter("company.url");
    //misc
    String birthday = session.getRequestParameter("birthdate");
    String comments = session.getRequestParameter("comments");
    boolean frequent = new Boolean(
        session.getRequestParameter("frequent")).booleanValue();

    ct.setCategory(category);
    ct.setFirstname(firstname);
    ct.setLastname(lastname);
    ct.setMiddlename(middlename);
    if (nickname != null && nickname.length() > 0) {
      log.debug("after testing nick");
      //Check if already existing nickname:
      JwmaContact ct_n = ctdb.getContactByNickname(nickname);
      if (ct_n != null && !ct_n.getUID().equals(ct.getUID())) {
        log.debug("contact=" + ct.getUID() + " nickct=" + ct_n.getUID());
        throw new JwmaException("contact.update.duplicatenickname", true);
      } else {
        ct.setNickname(nickname);
      }
    } else {
      ct.setNickname("");
    }

    ct.setCompany(company);
    ct.setTitle(title);
    ct.setRole(role);
    ct.setCompanyURL(compurl);
    ct.setHomePhoneNumber(homenum);
    ct.setWorkPhoneNumber(worknum);
    ct.setPagerNumber(pagernum);
    ct.setFaxNumber(faxnum);
    ct.setMobileNumber(mobilenum);
    ct.setPrimarilyWorkContact(primlocation);
    ct.setWorkStreet(workstreet);
    ct.setWorkCity(workcity);
    ct.setWorkRegion(workregion);
    ct.setWorkCountry(workcountry);
    ct.setWorkZIP(workzip);
    ct.setHomeStreet(homestreet);
    ct.setHomeCity(homecity);
    ct.setHomeRegion(homeregion);
    ct.setHomeCountry(homecountry);
    ct.setHomeZIP(homezip);
    ct.setEmail(email);
    ct.setAlternateEmail(altemail);
    ct.setURL(url);
    if (!ct.isFrequentRecipient() && frequent) {
      ctdb.addFrequentRecipient(ct);
    }
    if (ct.isFrequentRecipient() && !frequent) {
      ctdb.removeFrequentRecipient(ct);
    }
    ct.setFrequentRecipient(frequent);
    ct.setComments(comments);

    try {
      if (birthday != null && birthday.length() > 0) {
        ct.setBirthDate(session.getPreferences().getDateFormat().parse(birthday));
      }
    } catch (Exception ex) {
      //ignore now throw new JwmaException("");
      log.error("doUpdateContact()", ex);
    }
    if (create) {
      ctdb.addContact(ct);
    }
    saveContactDatabase(ctdb);
    session.storeBean("jwma.contacts.contact", ct);

    session.redirect(JwmaKernel.CONTACT_EDIT_VIEW);
    return;
  }//doUpdateContact

  /**
   * Deletes contact entries from the contact database.
   *
   * @param session a <tt>JwmaSession</tt> instance.
   * @param ctdb a <tt>JwmaContactsImpl</tt> instance.
   * @param cid a contact identifier as <tt>String</tt>.
   *
   * @throws JwmaException if it fails to execute properly.
   */
  private void doDeleteContacts(JwmaSession session,
                                JwmaContactsImpl ctdb,
                                String[] cids)
      throws JwmaException {


    for (int i = 0; i < cids.length; i++) {
      if (ctdb.containsContact(cids[i])) {
        ctdb.removeContact(
            (JwmaContactImpl) ctdb.getContact(cids[i])
        );
      } else {
        throw new JwmaException("contact.action.invalidcontact", true);
      }
    }

    session.redirect(JwmaKernel.CONTACTS_VIEW);
    return;
  }//doDeleteContacts

  /**
   * Imports a contact buffered from a message.
   *
   * @param session a <tt>JwmaSession</tt> instance.
   * @param ctdb a <tt>JwmaContactsImpl</tt> instance.
   * @param ct a contact as <tt>JwmaContactImpl</tt>.
   *
   * @throws JwmaException if it fails to execute properly.
   */
  private void doImportContact(JwmaSession session,
                               JwmaContactsImpl ctdb,
                               JwmaContactImpl ct)
      throws JwmaException {
    if (!ctdb.containsContact(ct.getUID())) {
      ctdb.addContact(ct);
    } else {
      throw new JwmaException("contact.action.invalidcontact", true);
    }


    session.storeBean("jwma.contacts.contact", ct);

    session.redirect(JwmaKernel.CONTACT_EDIT_VIEW);
    return;
  }//doDeleteContacts


  /*** END contact actions ************************************************************/


  private final void saveContactDatabase(JwmaContactsImpl ctdb)
      throws JwmaException {

    JwmaKernel.getReference().getContactManagementPlugin()
        .saveContacts(ctdb);
  }//saveContactDatabase

}//class JwmaContactsController
