/***
 * jwma Java WebMail
 * Copyright (c) 2000-2003 jwma team
 *
 * jwma is free software; you can distribute and use this source
 * under the terms of the BSD-style license received along with
 * the distribution.
 ***/
package dtw.webmail.util.config;

import dtw.webmail.util.StringUtil;

import java.util.Set;
import java.util.TreeSet;
import java.util.Collections;
import java.io.Serializable;

/** 
 * This class...
 * <p>
 * @author Dieter Wimberger (wimpi)
 * @version (created Feb 24, 2003)
 */
public class Administration
    implements Serializable {

   private Set m_Admins;
   private boolean m_Enabled = false;

   public Administration() {
     m_Admins = Collections.synchronizedSet(new TreeSet());
   }//constructor

   /**
   * Tests if the administration part is enabled.
   *
   * @return true if enabled, false otherwise.
   */
  public boolean isEnabled() {
    return m_Enabled;
  }//isEnabled

  /**
   * Sets the flag that controls if the adminstration part
   * is enabled.
   *
   * @param b true if enabled, false otherwise.
   */
  public void setEnabled(boolean b) {
    m_Enabled = b;
  }//setEnabled

  /**
   * Convenience method for obtaining the admin users
   * (by username) as a simple comma seperated list.
   * <p>
   * Note that this is supposed to be helpful for simplifying
   * the persistency mechanism.
   *
   * @return the list as <tt>String</tt>.
   */
  public String getAdminList() {
    return StringUtil.join(listAdmins(),",");
  }//getAdminList

  /**
   * Convenience method for setting the admin users
   * (by username) as a simple comma seperated list.
   * <p>
   * Note that this is supposed to be helpful for simplifying
   * the persistency mechanism.
   *
   * @return the list as <tt>String</tt>.
   */
  public void setAdminList(String list) {
    String[] admins = StringUtil.split(list,",");
    m_Admins.clear();
    for (int i = 0; i < admins.length; i++) {
      addAdmin(admins[i]);
    }
  }//setAdminList

  /**
   * Returns the list of users (by username) with
   * administrative rights.
   *
   * @return the list as <tt>String[]</tt>.
   */
  public String[] listAdmins() {
    String[] admins = new String[m_Admins.size()];
    return (String[]) m_Admins.toArray(admins);
  }//getAdminUsernames

  /**
   * Adds an administrator (by username).
   *
   * @param username the username as <tt>String</tt>.
   */
  public void addAdmin(String username) {
    //check if contains
    m_Admins.add(username);
  }//addAdmin

  /**
   * Removes an administrator (by username).
   *
   * @param username the username as <tt>String</tt>.
   */
  public void removeAdmin(String username) {
    m_Admins.remove(username);
  }//removeAdmin

  /**
   * Tests if a given user (by username) has
   * administrative rights.
   *
   * @return true if admin rights, false otherwise.
   */
  public boolean isAdmin(String username) {
    return m_Admins.contains(username);
  }//isAdmin

}//class Administration
