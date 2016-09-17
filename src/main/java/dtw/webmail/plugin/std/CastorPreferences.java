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
import dtw.webmail.model.JwmaMailIdentity;
import dtw.webmail.model.JwmaMailIdentityImpl;
import dtw.webmail.model.JwmaPreferencesImpl;
import dtw.webmail.util.AbstractIdentifiable;
import dtw.webmail.util.AssociatedAbstractIdentifiable;
import dtw.webmail.util.Associator;
import net.wimpi.text.Processor;
import org.apache.log4j.Logger;
import org.exolab.castor.jdo.Database;
import org.exolab.castor.jdo.PersistenceException;
import org.exolab.castor.jdo.TimeStampable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Class implementing a specialized <tt>JwmaPreferencesImpl</tt>
 * for being persisted with the Castor Plugins.
 *
 * @author Dieter Wimberger
 * @version 0.9.7 07/02/2003
 */
public class CastorPreferences
    extends AbstractIdentifiable
    implements JwmaPreferencesImpl, Associator, TimeStampable {

  //logging
  private static Logger log = Logger.getLogger(CastorPreferences.class);

  //instance attributes
  private String m_UserIdentity;
  private String m_Firstname = "firstname";
  private String m_Lastname = "lastname";
  private String m_LastLogin = "Never.";
  private String m_Signature = "";
  private String m_QuoteChar = ">";
  private boolean m_AutoQuote = false;
  private boolean m_AutoSign = true;

  //Folders
  private String m_RootFolder = "";
  private String m_Inbox = "Inbox";
  private String m_SentMailArchive = "sent-mail";
  private String m_ReadMailArchive = "read-mail";
  private String m_TrashFolder = "trash";
  private String m_DraftFolder = "draft";

  //Auto features
  private boolean m_AutoArchiveSent = false;
  private boolean m_AutoMoveRead = false;
  private boolean m_AutoEmpty = false;

  private Processor m_MessageProcessor;
  private Locale m_Locale = new Locale("en", "");

  private String m_ContactDatabase;
  private SimpleDateFormat m_DateFormat =
      (SimpleDateFormat) DateFormat.getDateInstance(DateFormat.SHORT, m_Locale);

  private String m_DefaultMailIdentity;
  private List m_MailIdentities;
  private boolean m_Expert = false;
  private boolean m_DisplayingInlined;
  private String m_Style = "";
  public int m_MessageSortCriteria;

  private List m_RemovedAssociations;
  private boolean m_Update = false;
  private long m_Timestamp = TimeStampable.NO_TIMESTAMP;


  /**
   * Constructs a JwmaPreferences instance.
   */
  public CastorPreferences() {
    super();
    //ensure default processor to be set
    setMessageProcessorName("");
    m_MailIdentities =
        Collections.synchronizedList(new ArrayList(5));
    m_RemovedAssociations =
        Collections.synchronizedList(new ArrayList(5));
  }//constructor

  public String getUserIdentity() {
    return m_UserIdentity;
  }//getUserIdentity

  /**
   * Sets the identity of this <tt>JwmaPreferences</tt>.
   * <p>
   * <em>Note</em>:<br>
   * The format of the string has to be
   * <tt>&lt;username&gt;@&lt;postofficehost&gt;</tt>.
   * <br>
   *
   * @param id the identity to be set as <tt>String</tt>.
   */
  public void setUserIdentity(String userid) {
    m_UserIdentity = userid;
  }//setUserIdentity

  public String getFirstname() {
    return m_Firstname;
  }//getFirstname

  /**
   * Sets the firstname of the owner of this
   * <tt>JwmaPreferences</tt>.
   *
   * @param firstname the owner's firstname.
   */
  public void setFirstname(String firstname) {
    m_Firstname = firstname;
  }//setFirstname

  public String getLastname() {
    return m_Lastname;
  }//getLastname

  /**
   * Sets the lastname of the owner of this
   * <tt>JwmaPreferences</tt>.
   *
   * @param lastname the owner's lastname.
   */
  public void setLastname(String lastname) {
    m_Lastname = lastname;
  }//setLastname

  public String getLastLogin() {
    return m_LastLogin;
  }//getLastLogin

  /**
   * Sets the the user's last login date and originating host.
   *
   * @param address the internet address of the last login.
   */
  public void setLastLogin(String lastlogin) {
    m_LastLogin = lastlogin;
  }//setLastLogin

  public String getQuoteChar() {
    return m_QuoteChar;
  }//getQuoteChars

  /**
   * Sets the quoting character.
   * <p>
   * <i><b>Note</b>:
   * only the first character is taken from the String.</i>
   *
   * @return the quote character as String.
   */
  public void setQuoteChar(String qc) {
    m_QuoteChar = "" + qc.charAt(0);
  }//setQuoteChars

  public boolean isAutoQuote() {
    return m_AutoQuote;
  }//isAutoQuote

  /**
   * Sets the flag that controls wheter messages should be automatically
   * quoted on reply.
   *
   * @param doquote true if messages being replied to  should be automatically
   *        quoted, false otherwise.
   */
  public void setAutoQuote(boolean doquote) {
    m_AutoQuote = doquote;
  }//setAutoQuote

  public String getRootFolder() {
    return m_RootFolder;
  }//getRootFolder

  /**
   * Sets the path of the mail root folder.
   *
   * @param path the path of root mail folder as
   *        <tt>String</tt>.
   */
  public void setRootFolder(String path) {
    m_RootFolder = path;
  }//setRootFolder

  public String getSentMailArchive() {
    return m_SentMailArchive;
  }//getSentMailArchive

  /**
   * Sets the path of the sent-mail-archive.
   *
   * @param path the path of the sent-mail-archive as <tt>String</tt>.
   */
  public void setSentMailArchive(String path) {
    m_SentMailArchive = path;
  }//setSentMailArchive

  public String getReadMailArchive() {
    return m_ReadMailArchive;
  }//getReadMailArchive

  /**
   * Sets the path of the read-mail-archive.
   *
   * @param path the path of the read-mail-archive as <tt>String</tt>.
   */
  public void setReadMailArchive(String path) {
    m_ReadMailArchive = path;
  }//setReadMailArchive

  public String getTrashFolder() {
    return m_TrashFolder;
  }//getTrashFolder

  /**
   * Sets the path of the trashfolder.
   *
   * @param path the path of the trashfolder as <tt>String</tt>.
   */
  public void setTrashFolder(String path) {
    m_TrashFolder = path;
  }//setTrashFolder

  public String getDraftFolder() {
    return m_DraftFolder;
  }//getDraftFolder

  /**
   * Sets the path of the draft folder.
   *
   * @param path the path of the draft folder as <tt>String</tt>.
   */
  public void setDraftFolder(String path) {
    m_DraftFolder = path;
  }//setDraftFolder

  /**
   * Sets the flag that controls wheter messages should be automatically
   * archived when sent.
   *
   * @param doarchive true if messages being sent should be automatically
   *        archived, false otherwise.
   */
  public void setAutoArchiveSent(boolean doarchive) {
    m_AutoArchiveSent = doarchive;
  }//setAutoArchiveSent

  public boolean isAutoArchiveSent() {
    return m_AutoArchiveSent;
  }//isAutoArchiveSent

  /**
   * Sets the flag that controls wheter messages should be automatically
   * moved when read.
   *
   * @param domoveread true if read messages should be automatically
   *        moved, false otherwise.
   */
  public void setAutoMoveRead(boolean domoveread) {
    m_AutoMoveRead = domoveread;
  }//setAutoMoveRead

  public boolean isAutoMoveRead() {
    return m_AutoMoveRead;
  }//isAutoMoveRead

  public boolean isAutoEmpty() {
    return m_AutoEmpty;
  }//isAutoEmptying

  /**
   * Sets the flag that controls wheter messages should be automatically
   * deleted from the trash on logout.
   *
   * @param b true if messages in trash should be automatically
   *        deleted on logout, false otherwise.
   */
  public void setAutoEmpty(boolean b) {
    m_AutoEmpty = b;
  }//setAutoEmpty

  public String getLanguage() {
    return m_Locale.getLanguage();
  }//getLanguage

  /**
   * Sets the language of this <tt>JwmaPreferences</tt>.
   *
   * @param str the language locale as <tt>String</tt>.
   */
  public void setLanguage(String str) {
    m_Locale = new Locale(str, "");
  }//setLanguage

  public Locale getLocale() {
    return m_Locale;
  }//getLocale

  public void setLocale(Locale locale) {
    m_Locale = locale;
    m_DateFormat =
        (SimpleDateFormat) DateFormat.getDateInstance(DateFormat.SHORT, m_Locale);
  }//setLocale


  public String getContactDatabaseID() {
    return m_ContactDatabase;
  }//getContactDatabaseID

  public void setContactDatabaseID(String dbid) {
    m_ContactDatabase = dbid;
  }//setContactDatabaseID

  public void setMessageProcessorName(String name) {
    //fetch the processor or pipe from the kernel
    m_MessageProcessor =
        JwmaKernel.getReference().getMessageProcessor(name);
  }//setMessageProcessorName

  public String getMessageProcessorName() {
    if (m_MessageProcessor == null) {
      return "";
    } else {
      return m_MessageProcessor.getName();
    }
  }//getMessageProcessorName

  public Processor getMessageProcessor() {
    return m_MessageProcessor;
  }//getMessageProcessor

  public void setMessageProcessor(Processor processor) {
    if (processor != null) {
      m_MessageProcessor = processor;
    }
  }//getMessageProcessor

  public DateFormat getDateFormat() {
    return m_DateFormat;
  }//getDateFormat

  public void setDateFormat(SimpleDateFormat dateformat) {
    m_DateFormat = dateformat;
  }//setDateFormat

  public String getDateFormatPattern() {
    return m_DateFormat.toPattern();
  }//getDateFormatPattern

  public void setDateFormatPattern(String pattern) {
    try {
      m_DateFormat.applyPattern(pattern);
    } catch (Exception ex) {
      //stick with the default
    }
  }//setDateFormatPattern

  public void setDefaultMailIdentity(String uid) {
    m_DefaultMailIdentity = uid;
    //log.debug("Set default mail identity="+uid);
  }//setDefaultMailIdentity

  public String getDefaultMailIdentity() {

    //log.debug("Get default mail identity="+m_DefaultMailIdentity);
    return m_DefaultMailIdentity;
  }//getDefaultMailIdentity

  public JwmaMailIdentity getMailIdentity() {
    return getMailIdentity(m_DefaultMailIdentity);
  }//getMailIdentity

  public Collection getMailIdentityCollection() {
    //log.debug("Getting mail identities.");
    return m_MailIdentities;
  }//getMailIdentityCollection

  public void setMailIdentityCollection(Collection collection) {
    //log.debug("Setting mail identities.");
    m_MailIdentities = Collections.synchronizedList(
        new ArrayList(collection));
    //ensure order
    //Collections.sort(m_MailIdentities,SortingUtil.INDEX_INCREASING);
  }//setMailIdentityCollection

  public List getMailIdentities() {
    return m_MailIdentities;
  }//getMailIdentities

  public JwmaMailIdentity[] listMailIdentities() {
    JwmaMailIdentity[] addr = new JwmaMailIdentity[m_MailIdentities.size()];
    return (JwmaMailIdentity[]) m_MailIdentities.toArray(addr);
  }//listMailIdentities

  public boolean existsMailIdentity(String uid) {
    for (Iterator iter = m_MailIdentities.iterator(); iter.hasNext();) {
      CastorMailIdentity mid = (CastorMailIdentity) iter.next();
      if (mid.equals(uid)) {
        return true;
      }
    }
    return false;
  }//existsMailIdentity

  public JwmaMailIdentity getMailIdentity(String uid) {
    for (Iterator iter = m_MailIdentities.iterator(); iter.hasNext();) {
      CastorMailIdentity mid = (CastorMailIdentity) iter.next();
      if (mid.equals(uid)) {
        return mid;
      }
    }
    return getMailIdentity(m_DefaultMailIdentity);
  }//getMailIdentity

  public void addMailIdentity(JwmaMailIdentity identity) {
    m_MailIdentities.add(identity);
    ((CastorMailIdentity) identity).setAssociatorUID(this.getUID());
  }//addMailIdentity

  public void removeMailIdentity(String uid) {
    for (Iterator iter = m_MailIdentities.iterator(); iter.hasNext();) {
      CastorMailIdentity mid = (CastorMailIdentity) iter.next();
      if (mid.equals(uid)) {
        iter.remove();
        mid.resetAssociatorUID();
        m_RemovedAssociations.add(mid);
      }
    }
  }//removeMailIdentity

  public int getMailIdentityCount() {
    return m_MailIdentities.size();
  }//getMailIdentityCount

  public boolean isExpert() {
    return m_Expert;
  }//isExpert

  public void setExpert(boolean b) {
    m_Expert = b;
  }//setExpert

  public void setStyle(String style) {
    m_Style = style;
  }//setStyle

  public String getStyle() {
    return m_Style;
  }//getStyle

  public boolean isDisplayingInlined() {
    return m_DisplayingInlined;
  }//isDisplayingInlined

  public void setDisplayingInlined(boolean b) {
    m_DisplayingInlined = b;
  }//setDisplayingInlined

  public int getMessageSortCriteria() {
    return m_MessageSortCriteria;
  }//getMessageSortCriteria

  public void setMessageSortCriteria(int messageSortCriteria) {
    m_MessageSortCriteria = messageSortCriteria;
  }//setMessageSortCriteria

  public JwmaMailIdentityImpl createMailIdentity() {
    return new CastorMailIdentity();
  }//createMailIdentity

  /**
   * Returns a clone of this preferences.
   *
   * @return the clone, or null if the cloning process failed.
   */
  public JwmaPreferencesImpl getClone() {
    try {
      CastorPreferences pref = (CastorPreferences) this.clone();
      //assign it it's own unique identifier
      pref.setUID("");
      //clear the mail identities
      m_MailIdentities.clear();
      //add up a default mail identity
      CastorMailIdentity mid = new CastorMailIdentity();
      //with some sense making values
      mid.setName("Default");
      mid.setFrom(getUserIdentity());
      pref.addMailIdentity(mid);
      pref.setDefaultMailIdentity(mid.getUID());
      //FIX: ensures a set message processor
      pref.setMessageProcessorName(pref.getMessageProcessorName());
      return pref;
    } catch (Exception ex) {
      return null;
    }
  }//getClone

  public List getRemovedAssociations() {
    return m_RemovedAssociations;
  }//getRemovedAssociations


  public void updatePreferences(Database db)
      throws PersistenceException {
    m_Update = true;
    try {
      persistPreferences(db);
    } finally {
      m_Update = false;
    }
  }//updateDatabase


  public void persistPreferences(Database db)
      throws PersistenceException {

    //1. store this
    storeObject(db, this);

    //3. iterate over identities, ensure order by indexing
    int i = 0;
    for (Iterator iter = m_MailIdentities.listIterator(); iter.hasNext(); i++) {
      //Object next=iter.next();
      //((Indexable)next).setIndex(i);
      storeObject(db, iter.next());
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

}//class JwmaPreferencesImpl
