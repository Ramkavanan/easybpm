/***
 * jwma Java WebMail
 * Copyright (c) 2000-2003 jwma team
 *
 * jwma is free software; you can distribute and use this source
 * under the terms of the BSD-style license received along with
 * the distribution.
 ***/
package dtw.webmail.util;

import java.io.*;
import java.net.*;
import java.util.*;

import org.apache.log4j.Logger;

//import dtw.webmail.JwmaKernel;
import dtw.webmail.model.*;
import dtw.webmail.util.StringUtil;

/**
 * Class implementing a wrapper for jwma's settings.
 *
 * @author Dieter Wimberger
 * @version 0.9.7 07/02/2003
 */
public class JwmaSettings {

  //logging
  private static Logger log = Logger.getLogger(JwmaSettings.class);

  //instance attributes
  private String m_WebInfDir;
  private File m_PropFile;
  private Properties m_Properties;


  /**
   * Constructs a new <tt>JwmaSettings</tt> instance.
   * The given path should point to the persistent settings
   * file.
   * At the moment the file is a standard properties file.
   *
   * @param path the full path of the persistent settings file.
   */
  protected JwmaSettings(String path) {
    m_WebInfDir = path;
  }//constructor

  /*** Account related *************************************************/

  /**
   * Tests if creation of accounts is enabled.
   *
   * @return true if account creation is enabled, false otherwise.
   */
  public boolean isAccountCreationEnabled() {
    return (new Boolean(
        m_Properties.getProperty("jwma.account.creation.enabled"))
        .booleanValue());
  }//isAccountCreationEnabled

  /**
   * Sets the flag that controls if the automatic creation of
   * accounts is enabled.
   *
   * @param true if account creation is enabled, false otherwise.
   */
  public void setAccountCreationEnabled(boolean enabled) {
    m_Properties.setProperty("jwma.account.creation.enabled", new Boolean(enabled).toString());
  }//setAccountCreationEnabled

  /**
   * Returns a <tt>String</tt> representing a JwmaKernel
   * view constant, denoting the view to be presented on
   * account creation.
   *
   * @return the account creation view key as <tt>String</tt>.
   */
  public String getAccountCreationView() {
    return m_Properties.getProperty("jwma.account.creation.view");
  }//getAccountCreationView

  /**
   * Sets the view key constant of the view to be presented to
   * the user on account creation.
   *
   * @param view the view key as <tt>String</tt>.
   */
  public void setAccountCreationView(String view) {
    m_Properties.setProperty("jwma.account.creation.view", view);
  }//setAccountCreationView

  /*** End Account related *********************************************/

  /*** Admin related ***************************************************/

  /**
   * Returns a <tt>String</tt> representing a list
   * of usernames that have administrative rights.
   *
   * @return the list of admin's usernames as <tt>String</tt>.
   */
  public String[] getAdminUsernames() {
    return StringUtil.split(
        m_Properties.getProperty("jwma.admin.usernames"),
        ","
    );
  }//getAdminUsernames

  /**
   * Sets the admin's usernames.
   *
   * @param admins the admin's usernames as <tt>String[]</tt>.
   */
  public void setAdminUsernames(String[] admins) {
    m_Properties.setProperty(
        "jwma.admin.usernames",
        StringUtil.join(admins, ",")
    );
  }//setAdminUsername

  /**
   * Tests if accessing the system's administration is enabled.
   *
   * @return true if administration is enabled, false otherwise.
   */
  public boolean isAdminEnabled() {
    return (new Boolean(
        m_Properties.getProperty("jwma.admin.enabled"))
        .booleanValue());
  }//isAdminEnabled

  /**
   * Sets the flag that controls if the system's adminstration is
   * enabled.
   *
   * @param true if administration is enabled, false otherwise.
   */
  public void setAdminEnabled(boolean enabled) {
    m_Properties.setProperty("jwma.admin.enabled", new Boolean(enabled).toString());
  }//setAdminEnabled



  /*** End admin related ***********************************************/

  /*** Controllers related *********************************************/

  /**
   * Returns a <tt>String</tt> representing the prefix
   * for the controllers.
   *
   * @return the prefix for the controllers as <tt>String</tt>.
   */
  public String getControllerPrefix() {
    return m_Properties.getProperty("jwma.controller.prefix");
  }//getControllerPrefix


  /**
   * Sets the prefix for the controllers.
   *
   * @param prefix the prefix for the controllers as <tt>String</tt>.
   */
  public void setControllerPrefix(String prefix) {
    m_Properties.setProperty("jwma.controller.prefix", prefix);
  }//setControllerPrefix

  /**
   * Returns a <tt>String</tt> representing the main
   * controller alias.
   *
   * @return the alias of the main controller as <tt>String</tt>.
   */
  public String getMainControllerAlias() {
    return m_Properties.getProperty("jwma.controller.main");
  }//getMainControllerAlias

  /**
   * Sets the alias for the main controller.
   *
   * @param alias the alias for the main controller as <tt>String</tt>.
   */
  public void setMainControllerAlias(String alias) {
    m_Properties.setProperty("jwma.controller.main", alias);
  }//setMainControllerAlias


  /**
   * Returns a <tt>String</tt> representing the mailing
   * controller alias.
   *
   * @return the alias of the mailing controller as <tt>String</tt>.
   */
  public String getMailingControllerAlias() {
    return m_Properties.getProperty("jwma.controller.mailing");
  }//getMailingControllerAlias

  /**
   * Sets the alias for the mailing controller.
   *
   * @param alias the alias for the mailing controller as <tt>String</tt>.
   */
  public void setMailingControllerAlias(String alias) {
    m_Properties.setProperty("jwma.controller.mailing", alias);
  }//setMailingControllerAlias


  /**
   * Returns a <tt>String</tt> representing the admin
   * controller alias.
   *
   * @return the alias of the admin controller as <tt>String</tt>.
   */
  public String getAdminControllerAlias() {
    return m_Properties.getProperty("jwma.controller.admin");
  }//getAdminControllerAlias

  /**
   * Sets the alias for the admin controller.
   *
   * @param alias the alias for the admin controller as <tt>String</tt>.
   */
  public void setAdminControllerAlias(String alias) {
    m_Properties.setProperty("jwma.controller.admin", alias);
  }//setAdminControllerAlias

  /**
   * Returns a <tt>String</tt> representing the contacts
   * controller alias.
   *
   * @return the alias of the contacts controller as <tt>String</tt>.
   */
  public String getContactsControllerAlias() {
    return m_Properties.getProperty("jwma.controller.contacts");
  }//getContactsControllerAlias

  /**
   * Sets the alias for the contacts controller.
   *
   * @param alias the alias for the contacts controller as <tt>String</tt>.
   */
  public void setContactsControllerAlias(String alias) {
    m_Properties.setProperty("jwma.controller.contacts", alias);
  }//setContactsControllerAlias

  /*** END Controllers related *****************************************/

  /*** Postoffice related **********************************************/

  /**
   * Returns a <tt>String</tt> representing the post office
   * protocol name.
   *
   * @return the postoffice protocol name as <tt>String</tt>.
   */
  public String getPostOfficeProtocol() {
    return m_Properties.getProperty("jwma.postoffice.protocol");
  }//getPostOfficeProtocol

  /**
   * Sets the name of the postoffice protocol.
   *
   * @param protocol the name of the postoffice protocol as <tt>String</tt>.
   */
  public void setPostOfficeProtocol(String protocol) {
    m_Properties.setProperty("jwma.postoffice.protocol", protocol);
  }//setPostOfficeProtocol

  /**
   * Returns a <tt>String</tt> representing the postoffice's
   * hostname.
   *
   * @return the postoffice hostname as <tt>String</tt>.
   */
  public String getPostOfficeHost() {
    return m_Properties.getProperty("jwma.postoffice.host");
  }//getPostOfficeHost

  /**
   * Sets the host name of the postoffice.
   *
   * @param host the hostname of the postoffice as <tt>String</tt>.
   */
  public void setPostOfficeHost(String host) {
    m_Properties.setProperty("jwma.postoffice.host", host);
  }//setPostOfficeHost

  /**
   * Returns an <tt>int</tt> representing the port
   * the postoffice is running on.
   *
   * @return the port number as <tt>int</tt>.
   */
  public int getPostOfficePort() {
    return new Integer(
        m_Properties.getProperty("jwma.postoffice.port")
    ).intValue();
  }//getPostOfficePort

  /**
   * Sets the port the postoffice is running on.
   *
   * @param the port number as <tt>int</tt>.
   */
  public void setPostOfficePort(int num) {
    m_Properties.setProperty(
        "jwma.postoffice.port",
        new Integer(num).toString()
    );
  }//setPostOfficePort

  /**
   * Returns a <tt>String</tt> representing the postoffice's
   * type.
   * <ul>
   * <li>mixed: folders in the store can hold folders and files.</li>
   * <li>plain: folders in the store can hold only folders or messages.</li>
   * <li>auto: folder types are checked by jwma</li>
   * <ul>
   * Note that in jwma terminology the folder that can only hold messages
   * is called a <tt>mailbox</tt>
   *
   * @return the postoffice type as <tt>String</tt>.
   */
  public String getPostOfficeType() {
    return m_Properties.getProperty("jwma.postoffice.type");
  }//getPostOfficeType

  /**
   * Sets the type of the postoffice.
   *
   * @param type the type of the postoffice as <tt>String</tt>.
   */
  public void setPostOfficeType(String type) {
    m_Properties.setProperty("jwma.postoffice.type", type);
  }//setPostOfficeType

  /**
   * Tests if overriding the system's set postoffice is allowed.
   *
   * @return true if overriding is allowed, false otherwise.
   */
  public boolean getPostOfficeAllowOverride() {
    return (new Boolean(
        m_Properties.getProperty("jwma.postoffice.allowoverride"))
        .booleanValue());
  }//getPostOfficeAllowOverride

  /**
   * Sets the flag that controls if overriding the system's set postoffice
   * is allowed or not.
   *
   * @param true if the overriding is to be allowed, false otherwise.
   */
  public void setPostOfficeAllowOverride(boolean allowoverride) {
    m_Properties.setProperty("jwma.postoffice.allowoverride", new Boolean(allowoverride).toString());
  }//setPostOfficeAllowOverride

  /**
   * Tests if the connection to the postoffice should be secure.
   *
   * @return true if secure, false otherwise.
   */
  public boolean isPostOfficeSecure() {
    return (new Boolean(
        m_Properties.getProperty("jwma.postoffice.secure"))
        .booleanValue());
  }//isPostOfficeSecure

  /**
   * Sets the flag that controls if connections to the MTA
   * should be secure.
   *
   * @param true if secure, false otherwise.
   */
  public void setPostOfficeSecure(boolean b) {
    m_Properties.setProperty(
        "jwma.postoffice.secure",
        new Boolean(b).toString()
    );
  }//setPostOfficeSecure

  /**
   * Returns a <tt>String</tt> representing the
   * fully qualified class name of the secure socket factory
   * to be used for secure connections to the postoffice.
   *
   * @return the secure socket factory classname as <tt>String</tt>.
   */
  public String getPostOfficeSecureSocketFactory() {
    return m_Properties.getProperty("jwma.postoffice.securesocketfactory");
  }//getPostOfficeSecureSocketFactory

  /**
   * Sets the fully qualified class name of the secure socket
   * factory to be used for secure connections to the postoffice.
   *
   * @param classname of the factory as <tt>String</tt>.
   */
  public void setPostSecureFactory(String factory) {
    m_Properties.setProperty("jwma.postoffice.securesocketfactory", factory);
  }//setPostOfficeSecureSocketFactory

  /*** END Postoffice related ******************************************/

  /*** Mail transport related ******************************************/

  /**
   * Returns a <tt>String</tt> representing the mail transport
   * protocol name.
   *
   * @return the mail transport protocol name as <tt>String</tt>.
   */
  public String getMailTransportProtocol() {
    return m_Properties.getProperty("jwma.mailtransport.protocol");
  }//getMailTransportProtocol

  /**
   * Sets the name of the mail transport protocol.
   *
   * @param protocol the name of the mail transport protocol
   *		  as <tt>String</tt>.
   */
  public void setMailTransportProtocol(String protocol) {
    m_Properties.setProperty("jwma.mailtransport.protocol", protocol);
  }//setMailTransportProtocol

  /**
   * Returns a <tt>String</tt> representing the MTA's
   * hostname.
   *
   * @return the MTA's hostname as <tt>String</tt>.
   */
  public String getMailTransportHost() {
    return m_Properties.getProperty("jwma.mailtransport.host");
  }//getMailTransportHost

  /**
   * Sets the host name of the MTA.
   *
   * @param host the hostname of the MTA as <tt>String</tt>.
   */
  public void setMailTransportHost(String host) {
    m_Properties.setProperty("jwma.mailtransport.host", host);
  }//setMailTransportHost

  /**
   * Returns an <tt>int</tt> representing the port
   * the MTA is running on.
   *
   * @return the port number as <tt>int</tt>.
   */
  public int getMailTransportPort() {
    return new Integer(
        m_Properties.getProperty("jwma.mailtransport.port")
    ).intValue();
  }//getMailTransportPort

  /**
   * Sets the port the MTA is running on.
   *
   * @param the port number as <tt>int</tt>.
   */
  public void setMailTransportPort(int num) {
    m_Properties.setProperty(
        "jwma.mailtransport.port",
        new Integer(num).toString()
    );
  }//setMailTransportPort

  /**
   * Tests if the connection to the MTA should be authenticated.
   *
   * @return true if authentication required, false otherwise.
   */
  public boolean isMailTransportAuthenticated() {
    return (new Boolean(
        m_Properties.getProperty("jwma.mailtransport.authenticated"))
        .booleanValue());
  }//isMailTransportAuthenticated

  /**
   * Sets the flag that controls if connections to the MTA
   * should be authenticated.
   *
   * @param true if authentication required, false otherwise.
   */
  public void setMailTransportAuthenticated(boolean auth) {
    m_Properties.setProperty(
        "jwma.mailtransport.authenticated",
        new Boolean(auth).toString()
    );
  }//setMailTransportAuthenticated


  /**
   * Returns an <tt>int</tt> representing the maximum
   * size in kB's allowed for a message to be transported.
   *
   * @return maximum size of a message in kB's as <tt>int</tt>.
   */
  public int getMailTransportLimit() {
    return new Integer(
        m_Properties.getProperty("jwma.mailtransport.maxsize")
    ).intValue();
  }//getMailTransportLimit

  /**
   * Sets the transport size limitation.
   *
   * @param size the number of kB's a message can reach at maximum
   *        as <tt>int</tt>.
   */
  public void setMailTransportLimit(int size) {
    m_Properties.setProperty("jwma.mailtransport.maxsize", new Integer(size).toString());
  }//setMailTransportLimit

  /*** END Mail transport related **************************************/

  /*** File related ****************************************************/

  /**
   * Returns a <tt>String</tt> representing filename of the
   * mapping file used for XML serialization.
   *
   * @return the filename of the mapping file as <tt>String</tt>.
   */
  public String getMappingFilename() {
    //fix for simplicity
    return "jwma_mapping.xml";
  }//getMappingFilename

  /**
   * Sets the the filename of the mapping file used for XML
   * serialization.<p>
   * There should not be any path appended to this filename,
   * because the path is constructed from different settings.
   *
   * @param name the filename of the mapping file as <tt>String</tt>.
   */
  public void setMappingFilename(String name) {
    //fix for simplicity
    return;
  }//setMappingFilename

  /**
   * Returns a <tt>String</tt> representing filename of the
   * site's settings template file.
   * <p>
   * This file represents a serialized JwmaPreferences instance
   * that is used for cloning new user's settings.
   *
   * @return the filename of the site's settings template file
   *         as <tt>String</tt>.
   */
  public String getTemplateFilename() {
    //fix for simplicity
    return "site_template.xml";
  }//getTemplateFilename

  /**
   * Sets the the filename of the site's settings template file.
   * <p>
   * This file represents a serialized JwmaPreferences instance
   * that is used for cloning new user's settings.
   * There should not be any path appended to this filename,
   * because the path is constructed from different settings.
   *
   * @param name the filename of the site's settings template file
   *        as <tt>String</tt>.
   */
  public void setTemplateFilename(String name) {
    //fix for simplicity
    return;
  }//setTemplateFilename


  /*** END File related ************************************************/

  /*** Log related *****************************************************/

  /**
   * Returns a <tt>String</tt> representing filename of the
   * log4j properties file.
   *
   * @return the filename of the log4j properties as <tt>String</tt>.
   */
  public String getLogPropertiesFilename() {
    return m_Properties.getProperty("jwma.log4j.properties");
  }//getSyslogFilename

  /**
   * Sets the the filename of the log4j properties file.
   *
   * @param name the filename of the log4j properties as <tt>String</tt>.
   */
  public void setLogPropertiesFilename(String name) {
    m_Properties.setProperty("jwma.log4j.properties", name);
  }//setLogPropertiesFilename


  /*** END Log related *************************************************/

  /*** Processing related **********************************************/

  /**
   * Returns a <tt>String</tt> representing filename of the
   * text processing properties.
   *
   * @return the filename of the text processing properties
   *         as <tt>String</tt>.
   */
  public String getTextProcFilename() {
    return m_Properties.getProperty("jwma.processing.properties");
  }//getTextProcFileName

  /**
   * Sets the filename of the text processing properties.
   *
   * @param the filename of the text processing properties
   *         as <tt>String</tt>.
   */
  public void setTextProcFilename(String name) {
    m_Properties.setProperty("jwma.processing.properties", name);
  }//getTextProcFileName

  /**
   * Returns a <tt>String</tt> representing the name of the default
   * message processing pipe.
   *
   * @return the name of the default message processing pipe as
   *         <tt>String</tt>.
   */
  public String getDefaultMessageProcessingPipe() {
    return m_Properties.getProperty("jwma.processing.defaultpipe");
  }//getDefaultMessageProcessingPipe

  /**
   * Returns a <tt>String</tt> representing the name of the default
   * message processing pipe.
   *
   * @return the name of the default message processing pipe as
   *         <tt>String</tt>.
   */
  public void setDefaultMessageProcessingPipe(String name) {
    m_Properties.setProperty("jwma.processing.defaultpipe", name);
  }//setDefaultMessageProcessingPipe


  /*** END Processing related ******************************************/

  /*** Views related ***************************************************/

  /**
   * Returns a <tt>String</tt> representing the prefix
   * for the views.
   *
   * @return the prefix for the views as <tt>String</tt>.
   */
  public String getViewPrefix() {
    return m_Properties.getProperty("jwma.view.prefix");
  }//getViewPrefix


  /**
   * Sets the prefix for the views.
   *
   * @param prefix the prefix for the views as <tt>String</tt>.
   */
  public void setViewPrefix(String prefix) {
    m_Properties.setProperty("jwma.view.prefix", prefix);
  }//setViewPrefix


  /**
   * Returns a <tt>String</tt> representing the error view
   * filename/URL ending.
   *
   * @return the error view filename/URL ending as <tt>String</tt>.
   */
  public String getErrorView() {
    return m_Properties.getProperty("jwma.view.error");
  }//getErrorView

  /**
   * Sets the filename/URL ending for the error view.
   *
   * @param name the error view filename/URL ending as <tt>String</tt>.
   */
  public void setErrorView(String name) {
    m_Properties.setProperty("jwma.view.error", name);
  }//setErrorView

  /**
   * Returns a <tt>String</tt> representing the login view
   * filename/URL ending.
   *
   * @return the login view filename/URL ending as <tt>String</tt>.
   */
  public String getLoginView() {
    return m_Properties.getProperty("jwma.view.login");
  }//getLoginView

  /**
   * Sets the filename/URL ending for the login view.
   *
   * @param name the login view filename/URL ending as <tt>String</tt>.
   */
  public void setLoginView(String name) {
    m_Properties.setProperty("jwma.view.login", name);
  }//setLoginView

  /**
   * Returns a <tt>String</tt> representing the logout view
   * filename/URL ending.
   *
   * @return the logout view filename/URL ending as <tt>String</tt>.
   */
  public String getLogoutView() {
    return m_Properties.getProperty("jwma.view.logout");
  }//getLogoutView

  /**
   * Sets the filename/URL ending for the logout view.
   *
   * @param name the logout view filename/URL ending as <tt>String</tt>.
   */
  public void setLogoutView(String name) {
    m_Properties.setProperty("jwma.view.logout", name);
  }//setLogoutView


  /**
   * Returns a <tt>String</tt> representing the folder view
   * filename/URL ending.
   *
   * @return the folder view filename/URL ending as <tt>String</tt>.
   */
  public String getFolderView() {
    return m_Properties.getProperty("jwma.view.folder");
  }//getFolderView

  /**
   * Sets the filename/URL ending for the folder view.
   *
   * @param name the folder view filename/URL ending as <tt>String</tt>.
   */
  public void setFolderView(String name) {
    m_Properties.setProperty("jwma.view.folder", name);
  }//setFolderView


  /**
   * Returns a <tt>String</tt> representing the mailbox view
   * filename/URL ending.
   *
   * @return the mailbox view filename/URL ending as <tt>String</tt>.
   */
  public String getMailboxView() {
    return m_Properties.getProperty("jwma.view.mailbox");
  }//getMailboxView

  /**
   * Sets the filename/URL ending for the mailbox view.
   *
   * @param name the mailbox view filename/URL ending as <tt>String</tt>.
   */
  public void setMailboxView(String name) {
    m_Properties.setProperty("jwma.view.mailbox", name);
  }//setMailboxView


  /**
   * Returns a <tt>String</tt> representing the message view
   * filename/URL ending.
   *
   * @return the message view filename/URL ending as <tt>String</tt>.
   */
  public String getMessageView() {
    return m_Properties.getProperty("jwma.view.message");
  }//getMessageView

  /**
   * Sets the filename/URL ending for the message view.
   *
   * @param name the message view filename/URL ending as <tt>String</tt>.
   */
  public void setMessageView(String name) {
    m_Properties.setProperty("jwma.view.message", name);
  }//setMessageView

  /**
   * Returns a <tt>String</tt> representing the compose view
   * filename/URL ending.
   *
   * @return the compose view filename/URL ending as <tt>String</tt>.
   */
  public String getComposeView() {
    return m_Properties.getProperty("jwma.view.compose");
  }//getComposeView

  /**
   * Sets the filename/URL ending for the compose view.
   *
   * @param name the compose view filename/URL ending as <tt>String</tt>.
   */
  public void setComposeView(String name) {
    m_Properties.setProperty("jwma.view.compose", name);
  }//setComposeView

  /**
   * Returns a <tt>String</tt> representing the preferences view
   * filename/URL ending.
   *
   * @return the preferences view filename/URL ending as <tt>String</tt>.
   */
  public String getPreferencesView() {
    return m_Properties.getProperty("jwma.view.preferences");
  }//getPreferencesView

  /**
   * Sets the filename/URL ending for the preferences view.
   *
   * @param name the preferences view filename/URL ending as <tt>String</tt>.
   */
  public void setPreferencesView(String name) {
    m_Properties.setProperty("jwma.view.preferences", name);
  }//setPreferencesView

  /**
   * Returns a <tt>String</tt> representing the firsttime view
   * filename/URL ending.
   *
   * @return the firsttime view filename/URL ending as <tt>String</tt>.
   */
  public String getFirsttimeView() {
    return m_Properties.getProperty("jwma.view.firsttime");
  }//getFirsttimeView

  /**
   * Sets the filename/URL ending for the firsttime view.
   *
   * @param name the firsttime view filename/URL ending as <tt>String</tt>.
   */
  public void setFirsttimeView(String name) {
    m_Properties.setProperty("jwma.view.firsttime", name);
  }//setFirsttimeView


  /**
   * Returns a <tt>String</tt> representing the contacts view
   * filename/URL ending.
   *
   * @return the contacts view filename/URL ending as <tt>String</tt>.
   */
  public String getContactsView() {
    return m_Properties.getProperty("jwma.view.contacts");
  }//getContactsView

  /**
   * Sets the filename/URL ending for the contacts view.
   *
   * @param name the contacts view filename/URL ending as <tt>String</tt>.
   */
  public void setContactsView(String name) {
    m_Properties.setProperty("jwma.view.contacts", name);
  }//setContactsView

  /**
   * Returns a <tt>String</tt> representing the contact view
   * filename/URL ending.
   *
   * @return the contact view filename/URL ending as <tt>String</tt>.
   */
  public String getContactView() {
    return m_Properties.getProperty("jwma.view.contact");
  }//getContactView

  /**
   * Sets the filename/URL ending for the contact view.
   *
   * @param name the contact view filename/URL ending as <tt>String</tt>.
   */
  public void setContactView(String name) {
    m_Properties.setProperty("jwma.view.contact", name);
  }//setContactView

  /**
   * Returns a <tt>String</tt> representing the contact edit view
   * filename/URL ending.
   *
   * @return the contact edit view filename/URL ending as <tt>String</tt>.
   */
  public String getContactEditView() {
    return m_Properties.getProperty("jwma.view.contactedit");
  }//getContactEditView

  /**
   * Sets the filename/URL ending for the contact edit view.
   *
   * @param name the contact edit view filename/URL ending as <tt>String</tt>.
   */
  public void setContactEditView(String name) {
    m_Properties.setProperty("jwma.view.contactedit", name);
  }//setContactEditView

  /**
   * Returns a <tt>String</tt> representing the contact group view
   * filename/URL ending.
   *
   * @return the contact group view filename/URL ending as <tt>String</tt>.
   */
  public String getContactGroupView() {
    return m_Properties.getProperty("jwma.view.contactgroup");
  }//getContactGroupView

  /**
   * Sets the filename/URL ending for the contact group view.
   *
   * @param name the contact group view filename/URL ending as <tt>String</tt>.
   */
  public void setContactGroupView(String name) {
    m_Properties.setProperty("jwma.view.contactgroup", name);
  }//setContactGroupView

  /**
   * Returns a <tt>String</tt> representing the contact group edit view
   * filename/URL ending.
   *
   * @return the contact group edit view filename/URL ending as <tt>String</tt>.
   */
  public String getContactGroupEditView() {
    return m_Properties.getProperty("jwma.view.contactgroupedit");
  }//getContactGroupEditView

  /**
   * Sets the filename/URL ending for the contact group edit view.
   *
   * @param name the contact group edit view filename/URL ending as <tt>String</tt>.
   */
  public void setContactGroupEditView(String name) {
    m_Properties.setProperty("jwma.view.contactgroupedit", name);
  }//setContactGroupEditView


  /**
   * Returns a <tt>String</tt> representing the admin preferences view
   * filename/URL ending.
   *
   * @return the admin preferences view filename/URL ending as <tt>String</tt>.
   */
  public String getAdminPreferencesView() {
    return m_Properties.getProperty("jwma.view.admin.preferences");
  }//getAdminPreferencesView

  /**
   * Sets the filename/URL ending for the admin preferences view.
   *
   * @param name the preferences view filename/URL ending as <tt>String</tt>.
   */
  public void setAdminPreferencesView(String name) {
    m_Properties.setProperty("jwma.view.admin.preferences", name);
  }//setAdminPreferencesView

  /**
   * Returns a <tt>String</tt> representing the admin settings view
   * filename/URL ending.
   *
   * @return the admin settings view filename/URL ending as <tt>String</tt>.
   */
  public String getAdminSettingsView() {
    return m_Properties.getProperty("jwma.view.admin.settings");
  }//getAdminSettingsView

  /**
   * Sets the filename/URL ending for the admin settings view.
   *
   * @param name the admin settings view filename/URL ending as <tt>String</tt>.
   */
  public void setAdminSettingsView(String name) {
    m_Properties.setProperty("jwma.view.admin.settings", name);
  }//setAdminSettingsView

  /**
   * Returns a <tt>String</tt> representing the admin login view
   * filename/URL ending.
   *
   * @return the admin login view filename/URL ending as <tt>String</tt>.
   */
  public String getAdminLoginView() {
    return m_Properties.getProperty("jwma.view.admin.login");
  }//getAdminLoginView

  /**
   * Sets the filename/URL ending for the admin login view.
   *
   * @param name the admin login view filename/URL ending as <tt>String</tt>.
   */
  public void setAdminLoginView(String name) {
    m_Properties.setProperty("jwma.view.admin.login", name);
  }//setAdminLoginView

  /**
   * Returns a <tt>String</tt> representing the admin status view
   * filename/URL ending.
   *
   * @return the admin status view filename/URL ending as <tt>String</tt>.
   */
  public String getAdminStatusView() {
    return m_Properties.getProperty("jwma.view.admin.status");
  }//getAdminStatusView

  /**
   * Sets the filename/URL ending for the admin status view.
   *
   * @param name the admin status view filename/URL ending as <tt>String</tt>.
   */
  public void setAdminStatusView(String name) {
    m_Properties.setProperty("jwma.view.admin.status", name);
  }//setAdminStatusView

  /**
   * Returns a <tt>String</tt> representing the admin menu view
   * filename/URL ending.
   *
   * @return the admin menu view filename/URL ending as <tt>String</tt>.
   */
  public String getAdminMenuView() {
    return m_Properties.getProperty("jwma.view.admin.menu");
  }//getAdminMenuView

  /**
   * Sets the filename/URL ending for the admin menu view.
   *
   * @param name the admin menu view filename/URL ending as <tt>String</tt>.
   */
  public void setAdminMenuView(String name) {
    m_Properties.setProperty("jwma.view.admin.menu", name);
  }//setAdminMenuView

  /**
   * Returns a <tt>String</tt> representing the admin error view
   * filename/URL ending.
   *
   * @return the admin error view filename/URL ending as <tt>String</tt>.
   */
  public String getAdminErrorView() {
    return m_Properties.getProperty("jwma.view.admin.error");
  }//getAdminErrorView

  /**
   * Sets the filename/URL ending for the admin error view.
   *
   * @param name the admin error view filename/URL ending as <tt>String</tt>.
   */
  public void setAdminErrorView(String name) {
    m_Properties.setProperty("jwma.view.admin.error", name);
  }//setAdminErrorView


  /*** END Views related ***********************************************/

  /*** i18n related ****************************************************/

  public String getSystemLanguage() {
    return m_Properties.getProperty("jwma.i18n.systemlanguage");
  }//getSystemLanguage

  public void setSystemLanguage(String language) {
    m_Properties.setProperty("jwma.i18n.systemlanguage", language);
  }//setLanguageList

  public String[] getLanguageList() {
    return StringUtil.split(m_Properties.getProperty("jwma.i18n.languages"), ",");
  }//getLanguageList

  public void setLanguageList(String[] languages) {
    m_Properties.setProperty("jwma.i18n.languages", StringUtil.join(languages, ","));
  }//setLanguageList


  /*** END i18n related ************************************************/

  public String getPrefPersistencePluginClassName() {
    return m_Properties.getProperty("jwma.plugin.preferencespersistence");
  }//getPrefPersistencePluginClassName

  public void setPrefPersistencePluginClassName(String classname) {
    m_Properties.setProperty("jwma.plugin.preferencespersistence", classname);
  }//setPrefPersistencePluginClassName

  public String getContactManagementPluginClassName() {
    return m_Properties.getProperty("jwma.plugin.contactmanagement");
  }//getContactManagementPluginClassName

  public void setContactManagementPluginClassName(String classname) {
    m_Properties.setProperty("jwma.plugin.contactmanagement", classname);
  }//setContactManagementPluginClassName

  public String getRandomAppendPluginClassName() {
    return m_Properties.getProperty("jwma.plugin.randomappend");
  }//getRandomAppendPluginClassName

  public void setRandomAppendPluginClassName(String classname) {
    m_Properties.setProperty("jwma.plugin.randomappend", classname);
  }//setRandomApendPluginClassName


  public String[] getCategories() {
    return StringUtil.split(m_Properties.getProperty("jwma.contacts.categories"), ",");
  }//getCategories

  public void setCategories(String[] cats) {
    m_Properties.setProperty("jwma.contacts.categories", StringUtil.join(cats, ","));
  }//setCategories

  /**
   * Loads the properties file containing jwma's settings.
   */
  public void load()
      throws JwmaException {

    try {
      FileInputStream in = new FileInputStream(m_PropFile);
      m_Properties.load(in);
    } catch (Exception ex) {
      throw new JwmaException(
          "jwma.settings.load.failed"
      ).setException(ex);
    }
  }//load

  /**
   * Saves the properties file containing jwma's settings.
   */
  public void save()
      throws JwmaException {

    try {
      FileOutputStream out = new FileOutputStream(m_PropFile);
      //FIXME: Modification line as string for saving
      m_Properties.store(out, "");
    } catch (Exception ex) {
      throw new JwmaException(
          "jwma.settings.save.failed"
      ).setException(ex);
    }
  }//save

  /**
   * Returns a <tt>String</tt> representing the settings file's
   * path.
   *
   * @return the settings file path as <tt>String</tt>.
   */
  public String getPath() {
    return (m_WebInfDir + File.separator + ETC_DIR + File.separator + serviceproperties);
  }//getPath

  /**
   * Returns a <tt>String</tt> representing the settings file's
   * path.
   *
   * @return the settings file path as <tt>String</tt>.
   */
  public String getDump() {
    StringWriter dump = new StringWriter();
    m_Properties.list(new PrintWriter(dump));
    return dump.toString();
  }//getDump


  /**
   * Prepares for loading/saving the properties file
   * containing jwma's settings.
   */
  private void prepare()
      throws JwmaException {

    try {
      m_PropFile =
          new File(m_WebInfDir + File.separator + ETC_DIR + File.separator + serviceproperties);
      m_Properties = new Properties();
      m_PropFile.exists();
    } catch (Exception ex) {
      throw new JwmaException(
          "jwma.settings.load.prepare"
      ).setException(ex);
    }
  }//prepare

  /**
   * Creates a <tt>JwmaSettings</tt> instance.
   * <p>
   * Requires the path to the directory where jwma's files and
   * folders reside. For the WAR the path should represent
   * the "WEB-INF" directory.
   *
   * @param path the path to the directory where jwma's files
   *        and folders reside.
   */
  public static JwmaSettings createJwmaSettings(String path)
      throws JwmaException {

    //create instance
    JwmaSettings settings = new JwmaSettings(path);
    settings.prepare();
    settings.load();

    return settings;
  }//createJwmaSettings

  /**
   * Defines a name of the static jwma direcory architecture.
   */
  public static final String LOG_DIR = "logs";
  public static final String ETC_DIR = "etc";
  public static final String DATA_DIR = "data";
  public static final String I18N_DIR = "i18n";

  /**
   * Defines the name of jwma's user visible error messages file.
   */
  public static final String ERROR_DESCRIPTION_FILE = "errormessages.properties";

  /**
   * Defines the name of jwma's properties file.
   */
  private static final String serviceproperties = "jwma.properties";


}//class JwmaSettingsImpl