/***
 * jwma Java WebMail
 * Copyright (c) 2000-2003 jwma team
 *
 * jwma is free software; you can distribute and use this source
 * under the terms of the BSD-style license received along with
 * the distribution.
 ***/
package dtw.webmail.plugin.std;

import dtw.webmail.JwmaKernel;
import dtw.webmail.model.*;
import dtw.webmail.util.*;
import org.apache.log4j.Logger;
import org.exolab.castor.jdo.Database;
import org.exolab.castor.jdo.PersistenceException;
import org.exolab.castor.jdo.TimeStampable;

import java.util.*;

/**
 * Class implementing a specialized <tt>JwmaContactsImpl</tt>
 * for being persisted with the Castor Plugins.
 *
 * @author Dieter Wimberger
 * @version 0.9.7 07/02/2003
 */
public class CastorContacts
    extends AbstractIdentifiable
    implements JwmaContactsImpl, Associator, TimeStampable {

  private static Logger log =
      Logger.getLogger(CastorContacts.class);

  private List m_Contacts;
  private List m_Groups;
  private Map m_Frequent;
  private Set m_Categories;
  private ContactFilter m_ContactFilter;
  private CategoryFilter m_CategoryFilter;

  private List m_RemovedAssociations;
  private boolean m_Update;
  private long m_Timestamp = TimeStampable.NO_TIMESTAMP;

  public CastorContacts() {
    log.debug("CastorContacts()");
    m_RemovedAssociations =
        Collections.synchronizedList(new ArrayList(5));

    m_Contacts = new ContactsCollection();
    m_Groups = new GroupsCollection();
    m_Frequent = Collections.synchronizedMap(new HashMap());
    m_Categories = Collections.synchronizedSet(new HashSet());
    m_CategoryFilter = new CategoryFilter("");
    m_ContactFilter = new DummyFilter();
  }//constructor

  /** Methods for Castor to set/get collections **/
  public Collection getContactsCollection() {
    //log.debug("Getting contacts collection.");
    return m_Contacts;
  }//getContactsCollection

  public void setContactsCollection(Collection collection) {
    //log.debug("Setting contacts collection!");
    //update map and caches
    for (Iterator iter = collection.iterator(); iter.hasNext();) {
      CastorContact ct = (CastorContact) iter.next();
      addContact(ct);
      if (ct.isFrequentRecipient()) {
        addFrequentRecipient(ct);
      }
      addContactCategory(ct.getCategory());
    }
  }//setContactsCollection

  public Collection getGroupsCollection() {
    //log.debug("Getting groups collection.");
    return m_Groups;
  }//getGroupsCollection

  public void setGroupsCollection(Collection collection) {
    //log.debug("Setting groups collection!");
    for (Iterator iter = collection.iterator(); iter.hasNext();) {
      addContactGroup((CastorContactGroup) iter.next());
    }
  }//setGroupsCollection

  public String[] listContactCategories() {
    String[] cats = new String[m_Categories.size()];
    return (String[]) m_Categories.toArray(cats);
  }//listContactCategories

  public void addContactCategory(String category) {
    if (category != null && category.length() > 0) {
      m_Categories.add(category);
    }
  }//addContactCategory

  public boolean existsContactCategory(String category) {
    if (category != null && category.length() > 0) {
      return m_Categories.contains(category);
    }
    return false;
  }//existsContactCategory

  public boolean containsContact(String uid) {
    //search list
    for (Iterator iter = m_Contacts.iterator(); iter.hasNext();) {
      //equals allows to pass uid
      JwmaContact ct = (JwmaContact) iter.next();
      if (ct.equals(uid)) {
        return true;
      }
    }
    return false;
  }//containsContact

  public JwmaContact getContact(String uid) {
    //search list
    for (Iterator iter = m_Contacts.iterator(); iter.hasNext();) {
      //equals allows to pass uid
      JwmaContact ct = (JwmaContact) iter.next();
      if (ct.equals(uid)) {
        return ct;
      }
    }
    return null;
  }//getContact

  public JwmaContact getContactByNickname(String nick) {
    //search list
    for (Iterator iter = m_Contacts.iterator(); iter.hasNext();) {
      JwmaContact ct = (JwmaContact) iter.next();
      String[] nicknames = StringUtil.split(ct.getNickname(), ",");
      for (int i = 0; i < nicknames.length; i++) {
        if (nick.equals(nicknames[i])) {
          return ct;
        }
      }
    }
    return null;
  }//getContactByNickname

  public boolean containsContactWithNickname(String nick) {
    //search list
    for (Iterator iter = m_Contacts.iterator(); iter.hasNext();) {
      JwmaContact ct = (JwmaContact) iter.next();
      String[] nicknames = StringUtil.split(ct.getNickname(), ",");
      for (int i = 0; i < nicknames.length; i++) {
        if (nick.equals(nicknames[i])) {
          return true;
        }
      }
    }
    return false;
  }//containsContactWithNickname

  public JwmaContactImpl createContact() {
    return new CastorContact();
  }//createContact

  public JwmaContact[] listContacts() {
    List contacts = new ArrayList(m_Contacts.size());
    for (Iterator iter = m_Contacts.iterator(); iter.hasNext();) {
      JwmaContact ct = (JwmaContact) iter.next();
      if (m_CategoryFilter.isAllowed(ct) && m_ContactFilter.isAllowed(ct)) {
        contacts.add(ct);
      }
    }

    JwmaContact[] cts = new JwmaContact[contacts.size()];
    return (JwmaContact[]) contacts.toArray(cts);
  }//listContacts

  public void addContact(JwmaContactImpl contact) {
    //add to wrapped list
    m_Contacts.add(contact);

    //associate
    ((CastorContact) contact).setAssociatorUID(this.getUID());
  }//addContact

  public void removeContact(JwmaContactImpl contact) {
    //remove
    m_Contacts.remove(contact);
    //remove group memberships
    for (Iterator iter = m_Groups.iterator(); iter.hasNext();) {
      JwmaContactGroupImpl group = (JwmaContactGroupImpl) iter.next();
      if (group.containsContact(contact.getUID())) {
        group.removeContact(contact);
      }
    }
    //remove from caches
    removeFrequentRecipient(contact);
    //remove from associated
    ((CastorContact) contact).resetAssociatorUID();
    m_RemovedAssociations.add(contact);
  }//removeContact

  public JwmaContact[] listFrequentRecipients() {
    JwmaContact[] cts = new JwmaContact[m_Frequent.size()];
    return (JwmaContact[]) m_Frequent.values().toArray(cts);
  }//listFrequentRecipients

  public void addFrequentRecipient(JwmaContactImpl contact) {
    //if (contact.isFrequentRecipient()) {
    m_Frequent.put(contact.getUID(), contact);
    contact.setFrequentRecipient(true);
    //}
  }//addFrequentRecipient

  public void removeFrequentRecipient(JwmaContactImpl contact) {
    if (m_Frequent.remove(contact.getUID()) != null) {
      contact.setFrequentRecipient(false);
    }
  }//removeFrequentRecipient

  public boolean containsContactGroup(String uid) {
    //search list
    for (Iterator iter = m_Groups.iterator(); iter.hasNext();) {
      //equals allows to pass uid
      JwmaContactGroup ctg = (JwmaContactGroup) iter.next();
      if (ctg.equals(uid)) {
        return true;
      }
    }
    return false;
  }//containsContactGroup

  public boolean containsContactGroupName(String name) {
    //search list
    for (Iterator iter = m_Groups.iterator(); iter.hasNext();) {
      //equals allows to pass uid
      JwmaContactGroup ctg = (JwmaContactGroup) iter.next();
      if (ctg.getName().equals(name)) {
        return true;
      }
    }
    return false;
  }//containsContactGroup

  public JwmaContactGroup getContactGroup(String cuid) {
    //search list
    for (Iterator iter = m_Groups.iterator(); iter.hasNext();) {
      //equals allows to pass uid
      JwmaContactGroup ctg = (JwmaContactGroup) iter.next();
      if (ctg.equals(cuid)) {
        return ctg;
      }
    }
    return null;
  }//getContactGroup

  public JwmaContactGroup getContactGroupByName(String name) {
    //search list
    for (Iterator iter = m_Groups.iterator(); iter.hasNext();) {
      //equals allows to pass uid
      JwmaContactGroup ctg = (JwmaContactGroup) iter.next();
      if (ctg.getName().equals(name)) {
        return ctg;
      }
    }
    //should not happen
    return null;
  }//getContactGroup

  public JwmaContactGroup[] listContactGroups() {
    JwmaContactGroup[] ctg = new JwmaContactGroup[m_Groups.size()];
    return (JwmaContactGroup[]) m_Groups.toArray(ctg);
  }//listContactGroups

  public void addContactGroup(JwmaContactGroupImpl group) {
    log.debug("Adding group:" + group.getName());
    m_Groups.add(group);
    //associate
    ((CastorContactGroup) group).setAssociatorUID(this.getUID());
    log.debug("Added group:" + group.getName());
  }//addContactGroup

  public void removeContactGroup(JwmaContactGroupImpl group) {
    m_Groups.remove(group.getUID());
    ((CastorContactGroup) group).resetAssociatorUID();
    m_RemovedAssociations.add(group);
  }//removeContactGroup

  public JwmaContactGroupImpl createContactGroup(String name)
      throws JwmaException {

    return new CastorContactGroup(this, name);
  }//createContactGroup

  public void setContactFilter(ContactFilter filter) {
    if (filter != null) {
      m_ContactFilter = filter;
    } else {
      m_ContactFilter = new DummyFilter();
    }
  }//setContactFilter

  public ContactFilter getContactFilter() {
    return m_ContactFilter;
  }//ContactFilter

  public void setCategoryFilter(String category) {
    m_CategoryFilter.setCategory(category);
  }//setCategoryFilter

  public String getCategoryFilter() {
    return m_CategoryFilter.getCategory();
  }//getCategoryFilter

  public Iterator getLastnameStarts() {
    ArrayList chars = new ArrayList(m_Contacts.size());
    boolean contained = false;

    for (Iterator iter = m_Contacts.iterator(); iter.hasNext();) {
      String firstchar =
          ((JwmaContact) iter.next()).getLastname().substring(0, 1).toUpperCase();
      //log.debug("firstchar="+firstchar);
      contained = false;
      for (Iterator iterc = chars.iterator(); iterc.hasNext();) {
        if (((String) iterc.next()).equals(firstchar)) {
          contained = true;
          break;
        }
      }
      if (!contained) {
        chars.add(firstchar);
        //log.debug("Added firstchar="+firstchar);
      }
    }
    return chars.listIterator();
  }//listLastnameStarts


  public List getRemovedAssociations() {
    return m_RemovedAssociations;
  }//getRemovedAssociations


  public void updateContacts(Database db)
      throws PersistenceException {
    m_Update = true;
    try {
      persistContacts(db);
    } finally {
      m_Update = false;
    }
  }//updateDatabase


  public void persistContacts(Database db)
      throws PersistenceException {

    //1. store this
    storeObject(db, this);

    //2. iterate over contacts, ensure order by indexing
    int i = 0;
    for (Iterator iter = m_Contacts.iterator(); iter.hasNext(); i++) {
      Object next = iter.next();
      storeObject(db, next);
    }

    //3. iterate over groups, ensure order by indexing
    i = 0;
    for (Iterator iter = m_Groups.iterator(); iter.hasNext(); i++) {
      Object next = iter.next();
      storeObject(db, next);
    }
  }//persistPreferences


  private void storeObject(Database db, Object o)
      throws PersistenceException {
    if (db == null || o == null) {
      return;
    } else if (db.isPersistent(o) || m_Update) {
      log.debug(JwmaKernel.getReference().getLogMessage("jwma.plugin.castor.objupdate") + o.toString());
      if (o instanceof Associator) {
        cleanupAssociations(db, ((Associator) o).getRemovedAssociations());
      }
      db.update(o);
    } else {
      log.debug(JwmaKernel.getReference().getLogMessage("jwma.plugin.castor.objcreate") + o.toString());
      db.create(o);
    }
  }//storeObject


  private void cleanupAssociations(Database db, List l)
      throws PersistenceException {

    Object o = null;
    for (Iterator iter = l.iterator(); iter.hasNext();) {
      AssociatedAbstractIdentifiable assoc =
          (AssociatedAbstractIdentifiable) iter.next();
      //check if object has an association
      if (!assoc.isAssociated()) {
        log.debug(
            JwmaKernel.getReference().getLogMessage("jwma.plugin.castor.objremove") +
            assoc.getClass() +
            " " +
            assoc.toString()
        );
        //needs to be loaded in this transaction :( hack but works
        try {
          o = db.load(assoc.getClass(), assoc.getUID());
        } catch (PersistenceException pex) {
          /*more hack, required to work*/
        }
        db.remove(o);
      }
    }
    //and definately, remove the references
    l.clear();
  }//cleanupAssociations

  public long jdoGetTimeStamp() {
    return m_Timestamp;
  }//jdoGetTimeStamp

  public void jdoSetTimeStamp(long timeStamp) {
    m_Timestamp = timeStamp;
  }//jdoSetTimeStamp

  private class DummyFilter implements ContactFilter {

    public boolean isAllowed(JwmaContact contact) {
      return true;
    }//isAllowed

    public boolean isFiltered(JwmaContact contact) {
      return false;
    }//isFiltered

    public String toString() {
      return "";
    }//toString

  }//inner class DummyFilter

  /**
   * Workaround CastorXML collection handling:
   * Does not really use "setCollection", rather it uses
   * getCollection() and adds to the collection.
   *
   */
  private class ContactsCollection extends ArrayList {

    public ContactsCollection() {
      super();
    }//constructor

    public boolean add(Object o) {
      CastorContact ct = (CastorContact) o;
      //update caches
      if (ct.isFrequentRecipient()) {
        addFrequentRecipient(ct);
      }
      addContactCategory(ct.getCategory());
      //add to list
      return super.add(o);
    }//add

  }//inner class ContactsCollection

  /**
   * Workaround CastorXML collection handling:
   * Does not really use "setCollection", rather it uses
   * getCollection() and adds to the collection.
   */
  private class GroupsCollection extends ArrayList {

    public GroupsCollection() {
      super();
    }//constructor

    public boolean add(Object o) {
      CastorContactGroup ctg = (CastorContactGroup) o;
      ctg.setContactsDB(CastorContacts.this);
      //add to list
      return super.add(o);
    }//add

  }//inner class ContactsCollection

}//class CastorContacts
