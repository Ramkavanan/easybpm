/***
 * jwma Java WebMail
 * Copyright (c) 2000-2003 jwma team
 *
 * jwma is free software; you can distribute and use this source
 * under the terms of the BSD-style license received along with
 * the distribution.
 ***/
package dtw.webmail.util;

import dtw.webmail.model.JwmaContact;
import org.apache.log4j.Logger;

public class LastnameStartsWithFilter implements ContactFilter {

  private static Logger log =
      Logger.getLogger(LastnameStartsWithFilter.class);
  private String m_Startswith = "";

  public LastnameStartsWithFilter(String start) {
    m_Startswith = start;
  }//constructor

  public boolean isFiltered(JwmaContact contact) {
    //log.debug("isFiltered()::"+contact.getLastname()+"::"+m_Startswith+"::"+
    //(!contact.getLastname().startsWith(m_Startswith))
    //);
    if (m_Startswith.length() == 0) {
      return false;
    } else {
      return !contact.getLastname().startsWith(m_Startswith);
    }
  }//isFiltered

  public boolean isAllowed(JwmaContact contact) {
    //log.debug("isAllowed()::"+contact.getLastname()+"::"+m_Startswith+"::"+
    //(contact.getLastname().startsWith(m_Startswith))
    //);
    if (m_Startswith.length() == 0) {
      return true;
    } else {
      return contact.getLastname().startsWith(m_Startswith);
    }
  }//isAllowed

  public String getStartsWith() {
    return m_Startswith;
  }//getStartsWith

  public void setStartsWith(String startswith) {
    m_Startswith = startswith;
  }//setStartsWith

  public String toString() {
    return m_Startswith;
  }//toString

}//LastnameStartsWithFilter
