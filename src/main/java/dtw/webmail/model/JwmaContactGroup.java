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
 * the JwmaContactGroup model.
 * <p>
 * The <tt>JwmaContactGroup</tt> allows a view programmer to obtain
 * information about the contact group to display
 * items for reading or editing.
 *
 * @author Dieter Wimberger
 * @version 0.9.7 07/02/2003
 *
 */
public interface JwmaContactGroup {

  /**
   * Returns the unique identifier of this <tt>JwmaContactGroup</tt>.
   *
   * @return the unique identifier as <tt>String</tt>.
   */
  public String getUID();

  /**
   * Returns the name of this <tt>JwmaContactGroup</tt>.
   *
   * @return the name of this group as <tt>String</tt>.
   */
  public String getName();

  /**
   * Returns the comments about this <tt>JwmaContactGroup</tt>.
   *
   * @return the comments about this group as <tt>String</tt>.
   */
  public String getComments();

  /**
   * Returns an array of contacts containing all contacts
   * in this contact group.
   * <p>
   * If this contact group does not contain any contacts,
   * then this method returns an empty array.
   *
   * Otherwise it contains one <tt>JwmaContact</tt> for
   * each entry in this contact group.
   *
   * @return an array of <tt>JwmaContact</tt>'s.
   */
  public JwmaContact[] listContacts();

  /**
   *
   */

}//interface JwmaContactGroup
