/***
 * jwma Java WebMail
 * Copyright (c) 2000-2003 jwma team
 *
 * jwma is free software; you can distribute and use this source
 * under the terms of the BSD-style license received along with
 * the distribution.
 ***/
package dtw.webmail.model;

import net.wimpi.text.Processor;

import java.text.DateFormat;
import java.util.Locale;

/**
 * An interface defining the contract for interaction with
 * the JwmaPreferences model.
 * <p>
 * JwmaPreferences allows a view programmer to obtain
 * information about the users preferences  to display
 * them for reading or editing.
 *
 * @author Dieter Wimberger
 * @version 0.9.7 07/02/2003
 */
public interface JwmaPreferences {

  /**
   * Returns a <tt>String</tt> representing
   * identity of the owner of this <tt>JwmaPreferences</tt>.
   * <p>
   * <em>Note</em>:<br>
   * The format of the string has to be
   * <tt>&lt;username&gt;@&lt;postofficehost&gt;</tt>.
   * <br>
   *
   * @return the firstname of this preferences owner.
   */
  public String getUserIdentity();

  /**
   * Returns a <tt>String</tt> representing the
   * firstname of the owner of this <tt>JwmaPreferences</tt>.
   *
   * @return the firstname of this preferences owner.
   */
  public String getFirstname();

  /**
   * Returns a <tt>String</tt> representing the
   * lastname of the owner of this <tt>JwmaPreferences</tt>.
   *
   * @return the lastname of this preferences owner.
   */
  public String getLastname();

  /**
   * Returns a <tt>String</tt> representing the
   * user's last login date and originating host.
   *
   * @return the last login with date and originating
   *         host as <tt>String</tt>.
   */
  public String getLastLogin();

  /**
   * Returns a <tt>String</tt> representing the
   * quote character.
   *
   * <i><b>Note</b>: it always contains only one character.</i>
   *
   * @return the quote character as String.
   */
  public String getQuoteChar();

  /**
   * Tests if messages should be quoted automatically
   * when replying.
   *
   * @return true if messages should be quoted
   *         automatically, false otherwise.
   */
  public boolean isAutoQuote();

  /**
   * Returns a <tt>String</tt> representing the
   * path of the mail root folder.
   * <p>
   * <i><b>Note</b>:
   * This setting varies for different IMAP implementations.
   * </i>
   *
   * @return the mail root folder as String.
   */
  public String getRootFolder();

  /**
   * Returns a <tt>String</tt> representing the
   * full name of the draft folder.
   *
   * @return the full name of the draft folder as String.
   */
  public String getDraftFolder();

  /**
   * Tests if messages should be automatically
   * archived when sent.
   *
   * @return true if messages should be archived
   *         automatically, false otherwise.
   */
  public boolean isAutoArchiveSent();

  /**
   * Returns a <tt>String</tt> representing the
   * path of the sent-mail-archive.
   *
   * @return the path of the sent-mail-archive as <tt>String</tt>.
   */
  public String getSentMailArchive();

  /**
   * Tests if messages should be automatically
   * moved when read.
   *
   * @return true if messages should be moved
   *         automatically, false otherwise.
   */
  public boolean isAutoMoveRead();

  /**
   * Returns a <tt>String</tt> representing the
   * path of the read-mail-archive.
   *
   * @return the path of the read-mail-archive as String.
   */
  public String getReadMailArchive();

  /**
   * Tests if the trash should be emptied
   * automatically when logging out.
   *
   * @return true if the trash should be emptied
   *         automatically, false otherwise.
   */
  public boolean isAutoEmpty();

  /**
   * Returns a <tt>String</tt> representing the
   * path of the read-mail-archive.
   *
   * @return the path of the read-mail-archive as String.
   */
  public String getTrashFolder();

  /**
   * Returns a <tt>String</tt> representing the
   * language.
   * <p>
   * Note that this method is a shortcut for
   * <tt>getLocale().getLanguage()</tt>.
   * <br>
   *
   * @return the language locale as <tt>String</tt>.
   */
  public String getLanguage();

  /**
   * Returns the <tt>Locale</tt> associated with this
   * <tt>JwmaPreferences</tt>.
   *
   * @return the associated <tt>Locale</tt>.
   */
  public Locale getLocale();

  /**
   * Returns a <tt>Processor</tt> representing the
   * users preferred message processor for
   * processing text/plain messages.
   *
   * @return the message processor as <tt>Processor</tt>.
   */
  public Processor getMessageProcessor();

  /**
   * Convenience method that returns a <tt>String</tt> representing
   * the users preferred message processor name.
   *
   * @return the name of the message processor as <tt>String</tt>.
   */
  public String getMessageProcessorName();


  /**
   * Returns a <tt>DateFormat</tt> representing the user's
   * preferred date format.
   *
   * @return the user's preferred <tt>DateFormat</tt>.
   */
  public DateFormat getDateFormat();

  /**
   * Returns the user's default <tt>JwmaMailIdentity</tt>.
   *
   * @return a the preferred or default <tt>JwmaMailIdentity</tt>.
   */
  public JwmaMailIdentity getMailIdentity();

  /**
   * Returns the <tt>JwmaMailIdentity</tt> with the given
   * unique identifier. If the identifier is invalid,
   * the method will return the default mail identity.
   *
   * @param uid a unique identifier of a mail identity.
   *
   * @return the <tt>JwmaMailIdentity</tt> with the given uid.
   */
  public JwmaMailIdentity getMailIdentity(String uid);


  /**
   * Returns a <tt>JwmaMailIdentities[]</tt> that
   * contains all <tt>JwmaMailIdentity</tt> instances
   * of this <tt>JwmaPreferences</tt>.
   *
   * @return the user's mail identities as <tt>JwmaMailIdentity[]</tt>.
   */
  public JwmaMailIdentity[] listMailIdentities();


  /**
   * Tests if the user considers to be an xpert.
   *
   * @return true if expert, false otherwise.
   */
  public boolean isExpert();

  /**
   * Returns a <tt>String</tt> representing the users
   * preferred style.
   *
   * @return the style as <tt>String</tt>.
   */
  public String getStyle();

  /**
   * Tests if known mime message content should be inlined
   * when displaying the message.
   *
   * @return true if inlining is enabled, false otherwise.
   */
  public boolean isDisplayingInlined();

  /**
   * Returns the sort criteria preference for messages.
   *
   * @return the sort criteria as <tt>int</tt>.
   */
  public int getMessageSortCriteria();

  /**
   * Sets the sort criteria preference for messages.
   *
   * @param crit the sort criteria as <tt>int</tt>.
   */
  public void setMessageSortCriteria(int crit);

}//class JwmaPreferences
