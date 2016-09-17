/***
 * jwma Java WebMail
 * Copyright (c) 2000-2003 jwma team
 *
 * jwma is free software; you can distribute and use this source
 * under the terms of the BSD-style license received along with
 * the distribution.
 ***/
package dtw.webmail.util;

/**
 * Abstract class implementing the
 * <tt>AbstractIdentifiable</tt> interface for
 * associated instances.
 *
 * @author Dieter Wimberger
 * @version 0.9.7 07/02/2003
 */
public abstract class AssociatedAbstractIdentifiable
    extends AbstractIdentifiable {

  //instance attributes
  private String m_AssociatorUID;

  /**
   * Returns the unique identifier of the
   * <tt>Associator</tt> of this
   * <tt>AssociatedAbstractIdentifiable</tt>.
   *
   * @return the unique identifier as <tt>String</tt>.
   */
  public String getAssociatorUID() {
    return m_AssociatorUID;
  }//getAssociatorUID

  /**
   * Sets the unique identifier of the
   * <tt>Associator</tt> of this
   * <tt>AssociatedAbstractIdentifiable</tt>.
   *
   * @param uid the unique identifier as <tt>String</tt>.
   */
  public void setAssociatorUID(String uid) {
    m_AssociatorUID = uid;
  }//setAssociatorUID

  /**
   * Resets the unique identifier of the
   * <tt>Associator</tt> of this
   * <tt>AssociatedAbstractIdentifiable</tt>.
   */
  public void resetAssociatorUID() {
    m_AssociatorUID = "";
  }//resetAssociatorUID

  /**
   * Tests if this <tt>AssociatedAbstractIdentifiable</tt>
   * is associated with a valid <tt>Associator</tt>.
   */
  public boolean isAssociated() {
    if (m_AssociatorUID == null || m_AssociatorUID.length() == 0) {
      return false;
    } else {
      return true;
    }
  }//isAssociated

}//abstract class AssociatedAbstractIdentifiable