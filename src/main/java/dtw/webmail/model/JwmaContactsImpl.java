/***
 * jwma Java WebMail
 * Copyright (c) 2000-2003 jwma team
 *
 * jwma is free software; you can distribute and use this source
 * under the terms of the BSD-style license received along with
 * the distribution.
 ***/
package dtw.webmail.model;

import dtw.webmail.util.ContactFilter;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

/**
 * Interface for <tt>JwmaContacts</tt> implementations.
 * This is the interface any specialized implementation
 * has to expose internal to controllers and models.
 *
 * @author Dieter Wimberger
 * @version 0.9.7 07/02/2003
 */
public interface JwmaContactsImpl
    extends JwmaContacts, Serializable {

  /**
   * Tests if this <tt>JwmaContactsImpl</tt> contains a
   * contact with the given unique identifier.
   *
   * @param uid a unique identifier of a contact.
   *
   * @return true if contained, false otherwise.
   */
  public boolean containsContact(String uid);

  /**
   * Returns contact with given nickname.
   *
   * @param nick the nickname as <tt>String</tt>.
   *
   * @return the contact.
   */
  public JwmaContact getContactByNickname(String nick);

  /**
   * Tests if the contact database contains a
   * contact with the given nickname.
   *
   * @param nick the nickname as <tt>String</tt>.
   *
   * @return true if contained, false otherwise.
   */
  public boolean containsContactWithNickname(String nick);

  /**
   * Adds a <tt>JwmaContactImpl</tt> instance to this
   * contact database.
   *
   * @param contact the <tt>JwmaContactImpl</tt> to be added.
   */
  public void addContact(JwmaContactImpl contact);

  /**
   * Removes a <tt>JwmaContactImpl</tt> instance from this
   * contact database.
   *
   * @param contact the <tt>JwmaContactImpl</tt> to be removed.
   */
  public void removeContact(JwmaContactImpl contact);


  /**
   * Adds a frequent recipient <tt>JwmaContactImpl</tt>
   * instance to this contact database.
   *
   * @param contact the frequent recipient <tt>JwmaContactImpl</tt>
   *       to be added.
   */
  public void addFrequentRecipient(JwmaContactImpl contact);

  /**
   * Removes a frequent recipient <tt>JwmaContactImpl</tt>
   * instance from this contact database.
   *
   * @param contact the frequent recipient <tt>JwmaContactImpl</tt>
   *       to be removed.
   */
  public void removeFrequentRecipient(JwmaContactImpl contact);

  /**
   * Tests if a given category exists in this <tt>JwmaContactsImpl</tt>.
   *
   * @param category a category to be looked up.
   *
   * @return true if the category exists, false otherwise.
   */
  public boolean existsContactCategory(String category);

  /**
   * Adds a contact category to the cached list of
   * categories.
   * <p>
   * If the category already exists, the method returns
   * immediately.
   *
   * @param category the category as <tt>String</tt>.
   */
  public void addContactCategory(String category);

  /**
   * Tests if this <tt>JwmaContactsImpl</tt> contains a
   * contact group with the given unique identifier.
   *
   * @param uid a unique identifier of a contact group.
   *
   * @return true if contained, false otherwise.
   */
  public boolean containsContactGroup(String uid);

  /**
   * Tests if this <tt>JwmaContactsImpl</tt> contains a
   * contact group with the given name.
   *
   * @param name the name for a contact group.
   *
   * @return true if contained, false otherwise.
   */
  public boolean containsContactGroupName(String name);

  /**
   * Returns an <tt>JwmaContactGroupByName</tt> representing the
   * contact group with the given name.
   * <p>
   * If the contact database does not contain any contact group with
   * the given name, it returns null. Otherwise it returns
   * the associated contact group.
   *
   * @return the contact group associated with the given name,
   *         or null.
   */
  public JwmaContactGroup getContactGroupByName(String name);

  /**
   * Adds a <tt>JwmaContactGroupImpl</tt> instance to this
   * contact database.
   *
   * @param contact the <tt>JwmaContactGroupImpl</tt> to be added.
   */
  public void addContactGroup(JwmaContactGroupImpl group);

  /**
   * Removes a <tt>JwmaContactGroupImpl</tt> instance from this
   * contact database.
   *
   * @param contact the <tt>JwmaContactGroupImpl</tt> to be removed.
   */
  public void removeContactGroup(JwmaContactGroupImpl group);

  /**
   * Creates a new <tt>JwmaContactGroupImpl</tt> with the given
   * name.
   *
   * @param name the name of the new group as <tt>String</tt>.
   *
   * @return the newly created <tt>JwmaContactGroupImpl</tt> instance.
   *
   * @throws JwmaException if a group with the same name exists already.
   */
  public JwmaContactGroupImpl createContactGroup(String name)
      throws JwmaException;

  /**
   * Creates and returns a new <tt>JwmaContactImpl</tt>.
   *
   *
   * @return the newly created <tt>JwmaContactImpl</tt> instance.
   *
   */
  public JwmaContactImpl createContact();

  /**
   * Sets an arbitrary contact filter, which will be
   * filtering contacts on listing.
   * Note that this filter is independent from the
   * category filter, filtering proceeds additive
   * (i.e. a contact has to pass both, AND).
   *
   * @param filter an arbitrary <tt>ContactFilter</tt>.
   */
  public void setContactFilter(ContactFilter filter);

  /**
   * Returns the arbitrary contact filter, which is
   * set for filtering contacts on listing.
   *
   * @return the contact filter instance.
   */
  public ContactFilter getContactFilter();

  /**
   * Returns an iterator of non-duplicate strings
   * with the first characters of the lastnames of
   * all contacts in this contact database.
   */
  public Iterator getLastnameStarts();

  /**
   * Sets a category based contact filter, which
   * will be filtering contacts which are not
   * of the given category on listing.
   * Note that this filter is independent from the
   * arbitrary set filter, filtering proceeds
   * additive (i.e. a contact has to pass both, AND).
   *
   * @param category the category which should be listed .
   */
  public void setCategoryFilter(String category);

  /**
   * Returns the name of the category which is not
   * filtered at the moment.
   *
   * @return the category name which is not filtered as <tt>String</tt>.
   */
  public String getCategoryFilter();


}//JwmaContacts
