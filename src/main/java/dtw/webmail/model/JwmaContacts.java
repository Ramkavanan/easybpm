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
 * the JwmaContacts model.
 * <p>
 * The JwmaContacts allows a view programmer to obtain
 * information about the contact database to display
 * items for reading or editing.
 *
 * @author Dieter Wimberger
 * @version 0.9.7 07/02/2003
 *
 */
public interface JwmaContacts {

  /**
   * Returns the unique identifier of this <tt>JwmaContacts</tt>.
   *
   * @return the unique identifier as <tt>String</tt>.
   */
  public String getUID();

  /**
   * Returns an array of available contact categories.
   *
   * @return an array of <tt>String</tt>'s.
   */
  public String[] listContactCategories();

  /**
   * Returns an array of contacts containing all contacts
   * in this contact database.
   * <p>
   * If this contact database does not contain any contacts,
   * then this method returns an empty array.
   *
   * Otherwise it contains one <tt>JwmaContact</tt> for
   * each entry in this contact database.
   *
   * @return an array of <tt>JwmaContact</tt>'s.
   */
  public JwmaContact[] listContacts();

  /**
   * Returns an array of contacts containing all frequent recipient
   * contacts.
   * <p>
   * If the contact database does not contain any frequent recipient
   * contacts, then this method returns an empty array.
   *
   * Otherwise it contains one <tt>JwmaContact</tt> for
   * each entry in this contact database.
   *
   * @return an array of <tt>JwmaContact</tt>'s.
   */
  public JwmaContact[] listFrequentRecipients();

  /**
   * Returns an array of contact groups containing all
   * groups of this contact database.
   * <p>
   * If this contact database does not contain any groups,
   * then this method returns an empty array.
   *
   * Otherwise it contains one <tt>JwmaContactGroup</tt> for
   * each group entry in this contact database.
   *
   * @return an array of <tt>JwmaContactGroup</tt>'s.
   */
  public JwmaContactGroup[] listContactGroups();

  /**
   * Returns an <tt>JwmaContact</tt> representing the
   * contact with the given unique identifier.
   * <p>
   * If the contact database does not contain any contact with
   * the given identifier, it returns null. Otherwise it returns
   * the associated contact.
   *
   * @return the contact associated with the given identifier,
   *         or null.
   */
  public JwmaContact getContact(String uid);

  /**
   * Returns an <tt>JwmaContactGroup</tt> representing the
   * contact group with the given unique identifier.
   * <p>
   * If the contact database does not contain any contact group with
   * the given identifier, it returns null. Otherwise it returns
   * the associated contact group.
   *
   * @return the contact group associated with the given identifier,
   *         or null.
   */
  public JwmaContactGroup getContactGroup(String uid);

}//JwmaContacts
