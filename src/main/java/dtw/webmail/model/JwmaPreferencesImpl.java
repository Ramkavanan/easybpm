/***
 * jwma Java WebMail
 * Copyright (c) 2000-2003 jwma team
 *
 * jwma is free software; you can distribute and use this source
 * under the terms of the BSD-style license received along with
 * the distribution.
 ***/
package dtw.webmail.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import net.wimpi.text.Processor;


/**
 * Interface for <tt>JwmaPreferences</tt> implementations.
 * This is the interface any specialized implementation
 * has to expose internal to controllers and models.
 *
 * @author Dieter Wimberger
 * @version 0.9.7 07/02/2003
 */
public interface JwmaPreferencesImpl
    extends JwmaPreferences, Cloneable, Serializable {

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
  public void setUserIdentity(String userid);

  /**
   * Sets the firstname of the owner of this
   * <tt>JwmaPreferences</tt>.
   *
   * @param firstname the owner's firstname.
   */
  public void setFirstname(String firstname);

  /**
   * Sets the lastname of the owner of this
   * <tt>JwmaPreferences</tt>.
   *
   * @param lastname the owner's lastname.
   */
  public void setLastname(String lastname);

  /**
   * Sets the the user's last login date and originating host.
   *
   * @param address the internet address of the last login.
   */
  public void setLastLogin(String lastlogin);


  /**
   * Sets a <tt>String</tt> representing the
   * language.
   * <p>
   * Note that this method is a shortcut for
   * <tt>getLocale().setLanguage()</tt>.
   * <br>
   *
   * @param language the standard ISO639 identifier as <tt>String</tt>.
   */
  public void setLanguage(String language);

  /**
   * Sets the <tt>Locale</tt> associated with this
   * <tt>JwmaPreferences</tt>.
   *
   * @param locale the <tt>Locale</tt> to be set.
   */
  public void setLocale(Locale locale);


  /**
   * Sets the quoting character.
   * <p>
   * <i><b>Note</b>:
   * only the first character is taken from the String.</i>
   *
   * @return the quote character as String.
   */
  public void setQuoteChar(String qc);

  /**
   * Sets the flag that controls wheter messages should be automatically
   * quoted on reply.
   *
   * @param doquote true if messages being replied to  should be automatically
   *        quoted, false otherwise.
   */
  public void setAutoQuote(boolean doquote);

  /**
   * Sets the path of the mail root folder.
   *
   * @param path the path of root mail folder as
   *        <tt>String</tt>.
   */
  public void setRootFolder(String path);

  /**
   * Sets the path of the sent-mail-archive.
   *
   * @param path the path of the sent-mail-archive as <tt>String</tt>.
   */
  public void setSentMailArchive(String path);

  /**
   * Sets the path of the read-mail-archive.
   *
   * @param path the path of the read-mail-archive as <tt>String</tt>.
   */
  public void setReadMailArchive(String path);

  /**
   * Sets the path of the trashfolder.
   *
   * @param path the path of the trashfolder as <tt>String</tt>.
   */
  public void setTrashFolder(String path);

  /**
   * Sets the path of the draft folder.
   *
   * @param path the path of the draft folder as <tt>String</tt>.
   */
  public void setDraftFolder(String path);

  /**
   * Sets the flag that controls wheter messages should be automatically
   * archived when sent.
   *
   * @param doarchive true if messages being sent should be automatically
   *        archived, false otherwise.
   */
  public void setAutoArchiveSent(boolean doarchive);

  /**
   * Sets the flag that controls wheter messages should be automatically
   * moved when read.
   *
   * @param domoveread true if read messages should be automatically
   *        moved, false otherwise.
   */
  public void setAutoMoveRead(boolean domoveread);

  /**
   * Sets the flag that controls wheter messages should be automatically
   * deleted from the trash on logout.
   *
   * @param b true if messages in trash should be automatically
   *        deleted on logout, false otherwise.
   */
  public void setAutoEmpty(boolean b);


  /**
   * Returns a <tt>String</tt> representing the
   * user's contact database UID.
   *
   * @return the UID of the contact database as <tt>String</tt>.
   */
  public String getContactDatabaseID();

  /**
   * Sets a <tt>String</tt> representing the
   * user's contact database UID.
   *
   * @return the UID of the contact database as <tt>String</tt>.
   */
  public void setContactDatabaseID(String dbid);

  /**
   * Sets the <tt>Processor</tt> representing the
   * users preferred message processor for
   * processing plain/text messages by its name.
   * <p>
   * Note that this method is a shortcut for
   * <pre>
   * <tt>
   * setMessageProcessor(
   *         JwmaKernel.getReference().getMessageProcessor(String name)
   * );
   * </tt>
   * </pre>
   * <br>
   *
   * @param name the <tt>Processor</tt>'s identifier as <tt>String</tt>.
   */
  public void setMessageProcessorName(String name);

  /**
   * Sets the <tt>Processor</tt> representing the
   * users preferred message processor for
   * processing plain/text messages.
   *
   * @param processor the preferred message processor.
   */
  public void setMessageProcessor(Processor processor);

  /**
   * Sets the <tt>DateFormat</tt> representing the user's
   * preferred date format.
   * Note that the passed in dateformat should be an instance
   * of <tt>SimpleDateFormat</tt>, which is enforced by the
   * parameter type.
   *
   * @param dateformat the user's preferred <tt>SimpleDateFormat</tt>.
   */
  public void setDateFormat(SimpleDateFormat dateformat);

  /**
   * Sets the user's default identity
   * <tt>JwmaMailIdentity</tt> instance.
   *
   * @param uid the identifier of the <tt>JwmaMailIdentity</tt>
   *        to be set as default.
   */
  public void setDefaultMailIdentity(String uid);

  /**
   * Adds the given <tt>JwmaMailIdentity</tt>
   * to this <tt>JwmaPreferences</tt>.
   *
   * @param identity the <tt>JwmaMailIdentity</tt> to be added.
   */
  public void addMailIdentity(JwmaMailIdentity identity);

  /**
   * Removes the <tt>JwmaMailIdentity</tt> with the given id
   * from this <tt>JwmaPreferences</tt>.
   *
   * @param uid the identifier of the <tt>JwmaMailIdentity</tt>
   *        to be removed.
   */
  public void removeMailIdentity(String uid);

  /**
   * Tests if a <tt>JwmaMailIdentity</tt> with the given index
   * exists.
   *
   * @param uid the identifier of the <tt>JwmaMailIdentity</tt>.
   *
   * @param b true if exists, false otherwise.
   */
  public boolean existsMailIdentity(String uid);

  /**
   * Returns the count of <tt>JwmaMailIdentity</tt> instances
   * that are associated with this <tt>JwmaPreferences</tt>.
   *
   * @return the number of <tt>JwmaMailIdentity</tt> instances
   *         associated with this <tt>JwmaPreferences</tt>.
   */
  public int getMailIdentityCount();

  /**
   * Sets the flag that shows wheter the owner of
   * this <tt>JwmaPreferences</tt> considers to be an xpert.
   *
   * @param b true if expert, false otherwise.
   */
  public void setExpert(boolean b);

  /**
   * Sets a <tt>String</tt> representing the users
   * preferred style.
   *
   * @param style the style as <tt>String</tt>.
   */
  public void setStyle(String style);

  /**
   * Sets the flag that controls whether known
   * mime message content is displayed inline.
   *
   * @return true if inline displaying is to be enabled,
   *         false otherwise.
   */
  public void setDisplayingInlined(boolean b);

  /**
   * Factory method for an implementation specific
   * <tt>JwmaMailIdentityImpl</tt>.
   *
   * @return an instance of the related <tt>JwmaMailIdentityImpl</tt>
   *         implementation.
   */
  public JwmaMailIdentityImpl createMailIdentity();

}//interface JwmaPreferencesImpl
