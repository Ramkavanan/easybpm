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

/**
 * Interface for <tt>JwmaContactGroup</tt> implementations.
 * This is the interface any specialized implementation
 * has to expose internal to controllers and models.
 *
 * @author Dieter Wimberger
 * @version 0.9.7 07/02/2003
 */
public interface JwmaContactGroupImpl
    extends JwmaContactGroup, Serializable {

  /**
   * Sets the name of this <tt>JwmaContactGroupImpl</tt>.
   *
   * @param name the name of this group as <tt>String</tt>.
   */
  public void setName(String name);

  /**
   * Sets the comments about this <tt>JwmaContactGroupImpl</tt>.
   *
   * @param comments the comments about this group as <tt>String</tt>.
   */
  public void setComments(String comments);


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
   * Tests if this <tt>JwmaContactGroupImpl</tt> contains a
   * contact with the given unique identifier.
   *
   * @param uid a unique identifier of a contact.
   *
   * @return true if contained, false otherwise.
   */
  public boolean containsContact(String uid);

}// interface JwmaContactGroupImpl
