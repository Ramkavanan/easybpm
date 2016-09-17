/***
 * jwma Java WebMail
 * Copyright (c) 2000-2003 jwma team
 *
 * jwma is free software; you can distribute and use this source
 * under the terms of the BSD-style license received along with
 * the distribution.
 ***/
package dtw.webmail.model;

/**
 * An interface defining the contract for interaction with
 * the JwmaMailIdentity model.
 * <p>
 * A <tt>JwmaMailIdentity</tt> allows the view programmer
 * access to mail identity information.
 *
 * @author Dieter Wimberger
 * @version 0.9.7 07/02/2003
 */
public interface JwmaMailIdentity {

  /**
   * Returns the unique identifier of this <tt>JwmaMailIdentity</tt>.
   *
   * @return the unique identifier as <tt>String</tt>.
   */
  public String getUID();

  /**
   * Returns a <tt>String</tt> representing the
   * name of this <tt>JwmaMailIdentity</tt>.
   *
   * @return the name of this <tt>JwmaMailIdentitya</tt>
   *         as <tt>String</tt>.
   */
  public String getName();

  /**
   * Returns a <tt>String</tt> representing the
   * mail address of this <tt>JwmaMailIdentity</tt>.
   *
   * @return the mail address as <tt>String</tt>.
   */
  public String getFrom();

  /**
   * Returns a <tt>String</tt> representing the
   * reply-to mail address of this
   * <tt>JwmaMailIdentity</tt>.
   *
   * @return the reply-to mail address as <tt>String</tt>.
   */
  public String getReplyTo();

  /**
   * Returns a <tt>String</tt> representing the
   * signature of this <tt>JwmaMailIdentity</tt>.
   *
   * @return the standard signature as String.
   */
  public String getSignature();

  /**
   * Returns the unique identifier of the contact related
   * with this <tt>MailIdentity</tt>.
   *
   * @return the UID of the related contact as <tt>String</tt>.
   */
  public String getRelatedContact();

  /**
   * Tests if the signature should be appended
   * automatically for this <tt>JwmaMailIdentity</tt>.
   *
   * @return true if the signature should be appended
   *         automatically, false otherwise.
   */
  public boolean isAutoSigning();

  /**
   * Returns a <tt>String</tt> representing the
   * note of this <tt>JwmaMailIdentity</tt>.
   *
   * @return the note as <tt>String</tt>.
   */
  public String getNote();

  /**
   * Returns a <tt>String</tt> representing the
   * random append type of this <tt>JwmaMailIdentity</tt>.
   *
   * @return the standard signature as String.
   */
  public String getRandomAppendType();

  /**
   * Tests if this <tt>JwmaMailIdentity</tt> want's a
   * random string to be appended to a message.
   *
   * @return true if a random string should be appended,
   *         false otherwise.
   */
  public boolean isRandomAppendAllowed();

}//Interface JwmaMailIdentity
