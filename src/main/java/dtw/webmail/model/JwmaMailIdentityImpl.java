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
import java.util.Locale;

/**
 * Interface for <tt>JwmaMailIdentity</tt> implementations.
 * This is the interface any specialized implementation
 * has to expose internal to controllers and models.
 *
 * @author Dieter Wimberger
 * @version 0.9.7 07/02/2003
 */
public interface JwmaMailIdentityImpl
    extends JwmaMailIdentity, Serializable {

  /**
   * Sets the <tt>String</tt> representing the
   * name of this <tt>JwmaMailIdentity</tt>.
   *
   * @param name the name of this <tt>JwmaMailIdentitya</tt>
   *         as <tt>String</tt>.
   */
  public void setName(String name);

  /**
   * Sets the reply-to mail address of
   * this <tt>JwmaMailIdentityImpl</tt>.
   *
   * @param addr the reply-to mail address as <tt>String</tt>.
   */
  public void setReplyTo(String addr);

  /**
   * Sets the mail address of this <tt>JwmaMailIdentity</tt>.
   *
   * @param addr the mail address as <tt>String</tt>.
   */
  public void setFrom(String addr);

  /**
   * Sets the signature of this <tt>MailIdentity</tt>.
   *
   * @param sig the signature as <tt>String</tt>.
   */
  public void setSignature(String sig);

  /**
   * Sets the unique identifier of the contact related
   * with this <tt>MailIdentity</tt>.
   *
   * @param uid the unique identifier of related contact as <tt>String</tt>.
   */
  public void setRelatedContact(String uid);

  /**
   * Sets the flag that controls wheter the signature should
   * be appended automatically or not.
   *
   * @param b true if signature should be appended, false otherwise.
   */
  public void setAutoSigning(boolean b);

  /**
   * Sets the note of this <tt>JwmaMailIdentity</tt>.
   *
   * @param note the note describing or commenting this
   *        <tt>JwmaMailIdentity</tt> as <tt>String</tt>.
   */
  public void setNote(String note);

  /**
   * Sets the random append type of this
   * <tt>JwmaMailIdentity</tt>.
   * <p>
   * This method will check with the plugin, if the type
   * is supported or not. If it is not supported, it will
   * automatically set <tt>RandomAppendPlugin.TYPE_NONE</tt>.
   * This mechanism is also active when setting the attribute from
   * a persistent instance, preventing problems when changing
   * plugin implementations (and supporting other types).
   *
   * @param type the type as <tt>String</tt>.
   * @param loc a <tt>Locale</tt>. 
   */
  public void setRandomAppendType(String type, Locale loc);

}//interface JwmaMailIdentityImpl
