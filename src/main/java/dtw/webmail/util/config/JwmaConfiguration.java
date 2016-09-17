/***
 * jwma Java WebMail
 * Copyright (c) 2000-2003 jwma team
 *
 * jwma is free software; you can distribute and use this source
 * under the terms of the BSD-style license received along with
 * the distribution.
 ***/
package dtw.webmail.util.config;

import org.apache.log4j.Logger;

import java.util.*;
import java.io.Serializable;

/**
 * Class implementing a wrapper for jwma's settings.
 *
 * @author Dieter Wimberger
 * @version 0.9.7 07/02/2003
 */
public class JwmaConfiguration
    implements Serializable {

  //logging
  private static Logger log = Logger.getLogger(JwmaConfiguration.class);

  //instance attributes
  private boolean m_POAllowOverride = false;
  private List m_PostOffices;
  private MailTransportAgent m_MTA;
  private Administration m_Administration;
  private Internationalization m_I18N;
  private Security m_Security;
  private boolean m_AutoAccount = true;
  private String m_DefaultMessageProcessor;
  private String m_PreferencePersistencePlugin;
  private String m_ContactManagementPlugin;
  private String m_RandomAppendPlugin;
  private String m_DefaultPostOffice;


  /**
   * Constructs a new <tt>JwmaConfiguration</tt> instance.
   */
  public JwmaConfiguration() {
    m_PostOffices = Collections.synchronizedList(new ArrayList(5));
  }//constructor

  public Security getSecurity() {
    return m_Security;
  }//getSecurity

  public void setSecurity(Security security) {
    m_Security = security;
  }//setSecurity

  /*** Postoffice related **********************************************/

  public Iterator getPostOffices() {
    return m_PostOffices.iterator();
  }//getPostOffices

  public Collection getPostOfficeCollection() {
    return m_PostOffices;
  }//getPostOffices

  public void setPostOfficeCollection(Collection collection) {
    m_PostOffices = Collections.synchronizedList(
        new ArrayList(collection));
  }//setPostOffices

  public void addPostOffice(PostOffice po) {
    if(!existsPostOfficeByName(po.getName())) {
      m_PostOffices.add(po);
    }
  }//addPostOffice

  public void removePostOffice(PostOffice po) {
    m_PostOffices.remove(po);
  }//removePostOffice

  public boolean existsPostOfficeByName(String name) {
    for (Iterator iterator = m_PostOffices.iterator(); iterator.hasNext();) {
      if(((PostOffice) iterator.next()).getName().equals(name)) {
        return true;
      }
    }
    return false;
  }//existsPostOfficeByName

  public PostOffice getPostOfficeByName(String name) {
    for (Iterator iterator = m_PostOffices.iterator(); iterator.hasNext();) {
      PostOffice office = (PostOffice) iterator.next();
      if(office.getName().equals(name)) {
        return office;
      }
    }
    return null;
  }//getPostOfficeByName

  /**
   * Tests if overriding the system's set postoffice is allowed.
   *
   * @return true if overriding is allowed, false otherwise.
   */
  public boolean getPostOfficeAllowOverride() {
    return m_POAllowOverride;
  }//getPostOfficeAllowOverride

  /**
   * Sets the flag that controls if overriding the
   * system's set postoffice is allowed or not.
   *
   * @param b true if the overriding is to be allowed,
   *        false otherwise.
   */
  public void setPostOfficeAllowOverride(boolean b) {
    m_POAllowOverride = b;
  }//setPostOfficeAllowOverride

  public PostOffice getDefaultPostOffice() {
    for (Iterator iterator = m_PostOffices.iterator(); iterator.hasNext();) {
      PostOffice office = (PostOffice) iterator.next();
      if(office.isDefault()) {
        return office;
      }
    }
    return null;
  }//getDefaultPostOffice

  public void setDefaultPostOffice(PostOffice ndpo) {

    PostOffice odpo = getDefaultPostOffice();
    odpo.setDefault(false);
    ndpo.setDefault(true);
  }//setDefaultPostOffice

  /*** END Postoffice related ******************************************/

  /*** Mail transport related ******************************************/

  public MailTransportAgent getMTA() {
    return m_MTA;
  }//getMTA

  public void setMTA(MailTransportAgent mta) {
    m_MTA = mta;
  }//setMTA

  /*** END Mail transport related **************************************/

  public boolean isSSLSetupRequired() {
    if(m_MTA.isSecure()) {
      return true;
    }
    for (Iterator iterator = m_PostOffices.iterator(); iterator.hasNext();) {
      if(((PostOffice) iterator.next()).isSecure()) {
        return true;
      }
    }
    return false;
  }//isSSLSetupRequired

  /*** Admin related ***************************************************/

  public Administration getAdministration() {
    return m_Administration;
  }//getAdministration

  public void setAdministration(Administration administration) {
    m_Administration = administration;
  }//setAdministration

  /*** End admin related ***********************************************/

  /*** Account related *************************************************/

  /**
   * Tests if creation of accounts is enabled.
   *
   * @return true if account creation is enabled, false otherwise.
   */
  public boolean isAccountCreationEnabled() {
    return m_AutoAccount;
  }//isAccountCreationEnabled

  /**
   * Sets the flag that controls if the automatic creation of
   * jwma accounts is enabled.
   * This will cause jwma to create user specific data, if
   * the user can be authenticated against the IMAP server.
   *
   * @param b true if account creation is enabled, false otherwise.
   */
  public void setAccountCreationEnabled(boolean b) {
    m_AutoAccount = b;
  }//setAccountCreationEnabled

  /*** End Account related *********************************************/

  /*** Processing related **********************************************/

  /**
   * Returns a <tt>String</tt> representing the name of the default
   * message processor.
   *
   * @return the name of the default message processor as
   *         <tt>String</tt>.
   */
  public String getDefaultMessageProcessor() {
    return m_DefaultMessageProcessor;
  }//getDefaultMessageProcessor

  /**
   * Returns a <tt>String</tt> representing the name of the default
   * message processor.
   *
   * @return the name of the default message processor as
   *         <tt>String</tt>.
   */
  public void setDefaultMessageProcessor(String name) {
    m_DefaultMessageProcessor = name;
  }//setDefaultMessageProcessor

  /*** END Processing related ******************************************/

  /*** i18n related ****************************************************/

  public Internationalization getI18N() {
    return m_I18N;
  }//getI18N

  public void setI18N(Internationalization i18N) {
    m_I18N = i18N;
  }//setI18N

  /*** END i18n related ************************************************/

  public String getPreferencePersistencePlugin() {
    return m_PreferencePersistencePlugin;
  }//getPreferencePersistencePlugin

  public void setPreferencePersistencePlugin(String classname) {
    m_PreferencePersistencePlugin = classname;
  }//setPreferencePersistencePlugin

  public String getContactManagementPlugin() {
    return m_ContactManagementPlugin;
  }//getContactManagementPlugin

  public void setContactManagementPlugin(String classname) {
    m_ContactManagementPlugin = classname;
  }//setContactManagementPlugin

  public String getRandomAppendPlugin() {
    return m_RandomAppendPlugin;
  }//getRandomAppendPlugin

  public void setRandomAppendPlugin(String classname) {
    m_RandomAppendPlugin = classname;
  }//setRandomApendPlugin

  /**
   * Defines a name of the static jwma direcory architecture.
   */
  public static final String LOG_DIR = "logs";
  public static final String ETC_DIR = "etc";
  public static final String DATA_DIR = "data";
  public static final String I18N_DIR = "i18n";
  public static final String CONFIG_FILENAME = "configuration.xml";
  public static final String LOG4J_CONFIG = "log4j.properties";
  public static final String JTEXTPROC_CONFIG = "textproc.properties";
  public static final String TEMPLATE_FILENAME = "site_template.xml";

}//class JwmaConfiguration