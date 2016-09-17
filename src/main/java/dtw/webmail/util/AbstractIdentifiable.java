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
 * <tt>Identifiable</tt> interface.
 *
 * @author Dieter Wimberger
 * @version 0.9.7 07/02/2003
 */
public abstract class AbstractIdentifiable
    implements Identifiable {

  //instance attributes
  private String m_UID;

  public AbstractIdentifiable() {
    //ensure a unique identifier to be set
    setUID("");
  }//constructor

  public String getUID() {
    return m_UID;
  }//getUID

  /**
   * Sets the unique identifier.
   *
   * @param uid the unique identifer as <tt>String</tt>.
   */
  public void setUID(String uid) {
    if (uid == null || uid.length() == 0 || uid.indexOf(UID_PREFIX) == -1) {
      m_UID = UID_PREFIX + UIDGenerator.getUID();
    } else {
      m_UID = uid;
    }
  }//setUID

  public boolean equals(Object o) {
    if(o==null) return false;
    String oid="";
    if (o instanceof Identifiable) {
      oid=((Identifiable)o).getUID();
    } else if (o instanceof String) {
      oid=(String)o;
    } else {
      oid=o.toString();
    }
    return m_UID.equals(oid);
  }//equals

  private static final String UID_PREFIX="jwma-";

}//interface Identifiable
