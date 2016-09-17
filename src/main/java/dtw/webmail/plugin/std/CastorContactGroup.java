/***
 * jwma Java WebMail
 * Copyright (c) 2000-2003 jwma team
 *
 * jwma is free software; you can distribute and use this source
 * under the terms of the BSD-style license received along with
 * the distribution.
 ***/
package dtw.webmail.plugin.std;

import dtw.webmail.model.JwmaContact;
import dtw.webmail.model.JwmaContactGroupImpl;
import dtw.webmail.model.JwmaContactImpl;
import dtw.webmail.util.AssociatedAbstractIdentifiable;
import dtw.webmail.util.StringUtil;
import org.apache.log4j.Logger;
import org.exolab.castor.jdo.TimeStampable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Class implementing a specialized <tt>JwmaContactGroupImpl</tt>
 * for being persisted with the Castor Plugins.
 *
 * @author Dieter Wimberger
 * @version 0.9.7 07/02/2003
 */
public class CastorContactGroup
    extends AssociatedAbstractIdentifiable
    implements JwmaContactGroupImpl, TimeStampable {

  private static Logger log =
      Logger.getLogger(CastorContactGroup.class);

  private String m_Name = "";
  private String m_Comments = "";
  private CastorContacts m_Contacts;
  private List m_ContactIDs;
  private long m_Timestamp = TimeStampable.NO_TIMESTAMP;

  public CastorContactGroup() {
    super();
    m_ContactIDs =
        Collections.synchronizedList(new ArrayList());
  }//constructor

  public CastorContactGroup(CastorContacts ctdb) {
    super();
    m_Contacts = ctdb;
    m_ContactIDs =
        Collections.synchronizedList(new ArrayList());

  }//constructor

  public CastorContactGroup(CastorContacts ctdb, String name) {
    super();
    m_Contacts = ctdb;
    m_ContactIDs =
        Collections.synchronizedList(new ArrayList());
    m_Name = name;
  }//constructor

  public void setContactsDB(CastorContacts contacts) {
    m_Contacts = contacts;
    //log.debug("Set database: " + contacts.getUID());
  }//setContactsDB

  public String getName() {
    return m_Name;
  }//getName

  public void setName(String name) {
    m_Name = name;
  }//setName

  public String getComments() {
    return m_Comments;
  }//getComments

  public void setComments(String comments) {
    m_Comments = comments;
  }//setComments

  /**
   * Used by castor to get list for persistence.
   */
  public String getContactIDList() {
    String list = StringUtil.join(listContactIDs(), ",");
    //log.debug("List of ids=" + list);
    return list;
    //return StringUtil.joinList(listContactIDs());
  }//getContactIDList

  /**
   * Used by castor to set list from persistent store.
   */
  public void setContactIDList(String list) {
    String[] cont = StringUtil.split(list, ",");
    m_ContactIDs.clear();
    for (int i = 0; i < cont.length; i++) {
      addContactID(cont[i]);
    }
  }//setContactIDList

  protected void addContactID(String uid) {
    m_ContactIDs.add(uid);
    //log.debug("Added contact with id=" + uid);
  }//addContactID

  protected void removeContactID(String uid) {
    m_ContactIDs.remove(uid);
  }//removeContactID

  protected String[] listContactIDs() {
    String[] contacts = new String[m_ContactIDs.size()];
    return (String[]) m_ContactIDs.toArray(contacts);
  }//listContactIDs

  public JwmaContact[] listContacts() {
    //log.debug("Listing " + m_ContactIDs.size() + " contacts.");
    if (m_ContactIDs.size() == 0) {
      return new JwmaContact[0];
    }
    List list = getContacts();
    //log.debug("Retrieved contact references.");
    //return the
    JwmaContact[] contacts = new JwmaContact[list.size()];
    return (JwmaContact[]) list.toArray(contacts);
  }//listContacts

  public List getContacts() {
    ArrayList contacts = new ArrayList(m_ContactIDs.size());
    if (m_ContactIDs.size() > 0) {
      for (Iterator iter = m_ContactIDs.iterator(); iter.hasNext();) {
        contacts.add(
            m_Contacts.getContact((String) iter.next())
        );
        //log.debug("getContacts()::Added contact");
      }
    }
    //return list with references
    return contacts;
  }//getContacts

  public void addContact(JwmaContactImpl con) {
    //add uid
    addContactID(con.getUID());
  }//addContact

  public void removeContact(JwmaContactImpl con) {
    //remove from uids list
    removeContactID(con.getUID());
  }//removeContact

  public boolean containsContact(String uid) {
    return m_ContactIDs.contains(uid);
  }//containsContact

  public long jdoGetTimeStamp() {
    return m_Timestamp;
  }//jdoGetTimeStamp

  public void jdoSetTimeStamp(long timeStamp) {
    m_Timestamp = timeStamp;
  }//jdoSetTimeStamp

}//class CastorContactGroup
