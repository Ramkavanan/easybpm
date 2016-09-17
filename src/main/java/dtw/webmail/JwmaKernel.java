/***
 * jwma Java WebMail
 * Copyright (c) 2000-2003 jwma team
 *
 * jwma is free software; you can distribute and use this source
 * under the terms of the BSD-style license received along with
 * the distribution.
 ***/
package dtw.webmail;


import java.util.*;
import java.io.*;
import java.net.*;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.BasicConfigurator;

import com.eazytec.common.util.CommonUtil;
import com.eazytec.model.EmailConfiguration;
import com.eazytec.util.PropertyReader;

import dtw.webmail.util.*;
import dtw.webmail.util.config.*;
import dtw.webmail.model.*;
import dtw.webmail.admin.*;
import dtw.webmail.plugin.*;
import dtw.webmail.plugin.std.CastorHelper;
import net.wimpi.text.*;

import javax.mail.Transport;

/**
 * A kernel that represents a hub for internal "globally" used
 * jwma functions & data (system settings).
 *
 * This class implements the singleton pattern, which means there is
 * only one instance throughout run-time.
 *
 * @author Dieter Wimberger
 * @version 0.9.7 07/02/2003
 */
public class JwmaKernel {

  //class attributes
  private static JwmaKernel c_Self = null;		//Singleton instance
  //logging
  private static Logger log = Logger.getLogger(JwmaKernel.class);

  //instance attributes
  private int m_Diag = 0;             //diagnostic counter
  private String m_ConfigurationFile;
  private JwmaConfiguration m_Configuration; //configuration

  //i18n
  private URLClassLoader m_i18nLoader;		//class loader for resource bundles
  private ResourceBundle m_LogMessages;		//Log message resource bundle
  private ResourceBundle m_ErrorMessages;   //Error message resource bundle
  private ResourceBundle m_ViewContentmessages; //View Content message resource bundle

  //Admin related
  private boolean m_StatusEnabled;  //Status enabled
  private String[] m_Admins;        //administrator usernames

  //Text processing
  private Processor m_MessageProcessor;         //default message processor
  private ProcessingKernel m_ProcessingKernel;  //processing kernel
  private String m_TextProcFile;                //jTextproc properties

  //Directories
  private String m_RootDir;   //root directory
  private String m_EtcDir;    //etc dir
  private String m_DataDir;   //data dir
  private String m_LogDir;    //log dir
  private String m_i18nDir;   //i18n bundle dir

  //Logging
  private String m_LogProperties;   //log4j properties file

  //Plugin references
  private PreferencesPersistencePlugin m_PrefsPersistencePlugin;
  private RandomAppendPlugin m_RandomAppendPlugin;
  private ContactManagementPlugin m_ContactManagementPlugin;

  //Controller Urls
  private String m_MainController;
  private String m_MailingController;
  private String m_AdminController;
  private String m_ContactsController;

  //View urls
  private Properties m_Views;
  
  private EmailConfiguration emailConfig;

  /**
   * Constructs a JwmaKernel instance.
   * Private to prevent construction from outside of
   * this class.
   */
  private JwmaKernel() {
    c_Self = this;
    m_Views = new Properties();
  }//constructor

  /**
   * Prepares the kernel for service.
   *
   * @param path representing the root directory where jwma's
   *        files reside, as <tt>String</tt>.
   *
   * @throws Exception when it fails to load the properties or
   *         set up system functionality according to the settings
   *         in the properties.
   */
  public void setup(String path)
      throws Exception {
    //Configure to log to standard out at least.
    BasicConfigurator.configure();

    //1.setup directories
    setupDirectories(path);
    m_Diag++;

    //2.setup filenames
    setupFiles();
    m_Diag++;

    //3.load configuration
    loadConfiguration();
    m_Diag++;

    //4.setup i18n
    prepareI18N();
    m_Diag++;

    //5.prepare log files
    //prepareLogging();
    //m_Diag++;

    //From here on we write to the own log
    try {

      //6.prepare plugins
      preparePlugins();
      m_Diag++;
      //7.prepare processing
      prepareProcessing();
      m_Diag++;

      //8.prepare postoffice and transport settings
      prepareMailServices();
      m_Diag++;

      //9.prepare controller urls
      prepareControllerUrls();
      m_Diag++;

      //10.prepare view urls
      prepareViewUrls();
      m_Diag++;

    } catch (JwmaException err) {
      //log exception with description and trace if inlined exception
      //available, else with stacktrace.
      if (err.hasException()) {
        log.error(err.getMessage(), err.getException());
      } else {
        log.error(err.getMessage(), err);
      }
      throw err;
    } catch (Exception ex) {
      log.error(ex.getMessage(), ex);
      throw ex;
    } finally {
      //JwmaStatus.createJwmaStatus();
    }
  }//setup

  /**
   * Sets up all necessary directory paths.
   *
   * @param path the root path pointing to the directory where
   *        the jwma data resides.
   */
  private void setupDirectories(String path) {
    //Directories
    m_RootDir = path;
    m_EtcDir = m_RootDir + File.separator + JwmaSettings.ETC_DIR + File.separator;
    m_LogDir = m_RootDir + File.separator + JwmaSettings.LOG_DIR + File.separator;
    m_DataDir = m_RootDir + File.separator + JwmaSettings.DATA_DIR + File.separator;
    m_i18nDir = StringUtil.repairPath(
        m_RootDir + File.separator + JwmaSettings.I18N_DIR + File.separator
    );
  }//setupDirectories

  /**
   * Sets up all necessary file paths.
   */
  private void setupFiles() {
    m_ConfigurationFile = m_EtcDir + JwmaConfiguration.CONFIG_FILENAME;
    m_LogProperties = m_EtcDir + JwmaConfiguration.LOG4J_CONFIG;
    m_TextProcFile = m_EtcDir + JwmaConfiguration.JTEXTPROC_CONFIG;
  }//setupFiles

  public void loadConfiguration()
      throws Exception {

    CastorXMLConfiguration cxmlconf =
        new CastorXMLConfiguration(new File (m_ConfigurationFile));
    m_Configuration = cxmlconf.load();
	//setConfiguration();
	setInternalMessageConfiguration();
    log.info("Loaded configuration.");
  }//loadConfiguration

	/**
	 * @return the emailConfig
	 */
	public EmailConfiguration getEmailConfig() {
		return emailConfig;
	}
	
	/**
	 * @param emailConfig the emailConfig to set
	 */
	public void setEmailConfig(EmailConfiguration emailConfig) {
		this.emailConfig = emailConfig;
	}
	
	/**
	 * set external mail configuration
	 * 
	 */
	public void setConfiguration(){
		  MailTransportAgent m_MTA = new MailTransportAgent(emailConfig.getMtaName(), emailConfig.getMtaAddress(), emailConfig.getMtaPort(), emailConfig.isMtaSecure(), "", emailConfig.getMtaProtocol(), emailConfig.isMtaAuthenticated());
		  m_Configuration.setMTA(m_MTA);
		  m_Configuration.removePostOffice(m_Configuration.getDefaultPostOffice());
		  PostOffice po = new PostOffice(emailConfig.getPoName(), emailConfig.getPoAddress(), emailConfig.getPoPort(), emailConfig.getPoRootFolder(), emailConfig.isPoSecure(), emailConfig.getPoType(), emailConfig.getPoProtocol(), emailConfig.isPoDefault(), emailConfig.getPoReplyToDomain());
		  m_Configuration.addPostOffice(po);
	}
	
	/**
	 * set internal message mail configuration
	 * 
	 */
	public void setInternalMessageConfiguration(){
		MailTransportAgent m_MTA = new MailTransportAgent("Internal Message MTA", CommonUtil.getInternalMessageSMTPHost(), CommonUtil.getInternalMessageSMTPPort(), CommonUtil.getInternalMessageSSL(), "", CommonUtil.getInternalMessageSMTPProtocol(), true);
		m_Configuration.setMTA(m_MTA);
		m_Configuration.removePostOffice(m_Configuration.getDefaultPostOffice());
		PostOffice internalMessagePO = new PostOffice("Internal Message PostOffice", CommonUtil.getInternalMessageIMAPHost(), CommonUtil.getInternalMessageIMAPPort(), "INBOX", CommonUtil.getInternalMessageSSL(), "mixed", CommonUtil.getInternalMessageIMAPProtocol(), true, null);
		m_Configuration.addPostOffice(internalMessagePO);
	}
	
	/**
	 * set external mail configuration
	 * 
	 * @param emailConfig
	 */
	public void setConfiguration(EmailConfiguration emailConfig) throws Exception{
		MailTransportAgent m_MTA = new MailTransportAgent(emailConfig.getMtaName(), emailConfig.getMtaAddress(), emailConfig.getMtaPort(), emailConfig.isMtaSecure(), "", emailConfig.getMtaProtocol(), emailConfig.isMtaAuthenticated());
		m_Configuration.setMTA(m_MTA);
		PostOffice po = new PostOffice(emailConfig.getPoName(), emailConfig.getPoAddress(), emailConfig.getPoPort(), emailConfig.getPoRootFolder(), emailConfig.isPoSecure(), emailConfig.getPoType(), emailConfig.getPoProtocol(), emailConfig.isPoDefault(), emailConfig.getPoReplyToDomain());
		m_Configuration.addPostOffice(po);
		prepareMailServices();
	}
  /**
   * Prepares i18n for jwma
   */
  private void prepareI18N()
      throws Exception {

    //1. setup classloader for bundles
    URL[] urls = {new File(m_i18nDir).toURL()};
    log.debug(urls[0].toString());
    m_i18nLoader = new URLClassLoader(urls);

    //2. retrieve instance from configuration
    Internationalization i18n = m_Configuration.getI18N();

    log.debug("Loading logmessage bundle:" + i18n.getSystemLanguage());
    //3. load log messages bundle for system locale
    m_LogMessages = ResourceBundle.getBundle(
        "logmessages", i18n.getSystemLocale(), m_i18nLoader
    );

    log.debug("Loading logmessage bundle:" + i18n.getSystemLanguage());
    //4. load error messages for system locale
    m_ErrorMessages = ResourceBundle.getBundle(
        "errormessages", i18n.getSystemLocale(), m_i18nLoader
    );

    log.debug("Loading viewcontent bundle:" + i18n.getSystemLanguage());
    //4. load error messages for system locale
    m_ViewContentmessages = ResourceBundle.getBundle(
        "viewcontent", i18n.getSystemLocale(), m_i18nLoader
    );
    
    log.info(getLogMessage("jwma.kernel.prepared.i18n"));
  }//prepareI18N

  /**
   * Prepares jwma log files.
   */
  private void prepareLogging()
      throws Exception {

    //1.load the properties
    Properties props = new Properties();
    props.load(new FileInputStream(m_LogProperties));

    //2. fix relative paths
    for (Enumeration enumeration = props.propertyNames(); enumeration.hasMoreElements();) {
      String property = (String) enumeration.nextElement();
      if (property.indexOf(".File") > 0) {
        File file = new File(props.getProperty(property));
        if (!file.isAbsolute()) {
          props.setProperty(property, m_LogDir + file.getPath());
        }
      }
    }

    //configure log4j
    PropertyConfigurator.configure(props);

    log.info(getLogMessage("jwma.kernel.prepared.logging"));
  }//prepareLogging

  /**
   * Prepare contact management.
   */
  private void preparePlugins()
      throws Exception {

    log.debug("Loading and activating plugins.");
    //1. Load & activate preferences persistence plugin
    m_PrefsPersistencePlugin = (PreferencesPersistencePlugin)
        Class.forName(m_Configuration.getPreferencePersistencePlugin()).newInstance();
    m_PrefsPersistencePlugin.activate();

    //2. Load & activate contact management plugin
    m_ContactManagementPlugin = (ContactManagementPlugin)
        Class.forName(m_Configuration.getContactManagementPlugin()).newInstance();
    m_ContactManagementPlugin.activate();

    //3. Load & activate random append plugin
    String rndapp = m_Configuration.getRandomAppendPlugin();
    if (rndapp != null && rndapp.length() != 0) {
      m_RandomAppendPlugin = (RandomAppendPlugin)
          Class.forName(rndapp).newInstance();
      m_RandomAppendPlugin.activate();
    }

  }//preparePlugins

  /**
   * Prepares the processing.
   */
  private void prepareProcessing()
      throws Exception {


    Properties procprops = new Properties();
    FileInputStream in = new FileInputStream(m_TextProcFile);
    procprops.load(in);

    //create processing kernel
    m_ProcessingKernel =
        ProcessingKernel.createProcessingKernel(procprops);

    //lookup default message processing pipe
    m_MessageProcessor = getMessageProcessor(
        m_Configuration.getDefaultMessageProcessor()
    );

    if (m_MessageProcessor == null) {
      throw new Exception("Failed to load default message processing pipe.");
    }

    log.info(getLogMessage("jwma.kernel.prepared.processing"));
  }//prepareProcessing


  /**
   * Prepares the mail service related settings.
   */
  private void prepareMailServices()
      throws Exception {

    //1. setup transport agent
    MailTransportAgent mta = m_Configuration.getMTA();

    //set system properties
    //TODO: Check if really required
    System.getProperties().put(
        "mail." + mta.getProtocol() + ".auth",
        new Boolean(mta.isAuthenticated()).toString()
    );
    System.getProperties().put("mail.smtp.host", mta.getAddress());
    if(mta.isSecure()) {
      m_Configuration.getSecurity().addSocketProperties(mta.getProtocol(),mta.getPort());
    }

    //2. post office security setup
    for(Iterator iter = m_Configuration.getPostOffices(); iter.hasNext();) {
      PostOffice po = (PostOffice) iter.next();
      if(po.isSecure()) {
        m_Configuration.getSecurity().addSocketProperties(po.getProtocol(),po.getPort());
      }
    }


    log.info(getLogMessage("jwma.kernel.prepared.mailservices"));
  }//prepareMailServices

  /**
   * Prepares the controller url's
   */
  private void prepareControllerUrls() {
    Properties ctrls = new Properties();
    try {
      ctrls.load(
        Thread.currentThread().getContextClassLoader()
            .getResourceAsStream("dtw/webmail/resources/controllers.properties")
      );
      m_MainController = ctrls.getProperty("jwma.controller.main");
      m_MailingController = ctrls.getProperty("jwma.controller.mailing");
      m_ContactsController = ctrls.getProperty("jwma.controller.contacts");
      m_AdminController = ctrls.getProperty("jwma.controller.admin");
    } catch (IOException ex) {
      log.error("prepareControllerUrls()",ex);
      return;
    }
    for(Iterator iter = ctrls.keySet().iterator(); iter.hasNext();) {
      String key= (String) iter.next();
      log.debug(key + "=" + ctrls.get(key));
    }
    log.info(getLogMessage("jwma.kernel.prepared.curls"));
  }//prepareControllerUrls

  /**
   * Prepares the view url's.
   */
  private void prepareViewUrls() {
    m_Views = new Properties();
    try {
      m_Views.load(
         Thread.currentThread().getContextClassLoader()
             .getResourceAsStream("dtw/webmail/resources/views.properties")
      );
     for(Iterator iter = m_Views.keySet().iterator(); iter.hasNext();) {
      String key= (String) iter.next();
      log.debug(key + "=" + m_Views.get(key));
     }
    } catch (IOException ex) {
      log.error("prepareViewUrls()",ex);
      return;
    }
    log.info(getLogMessage("jwma.kernel.prepared.vurls"));
  }//prepareViewUrls

  /**
   * Returns the active <tt>JwmaConfiguration</tt>
   * instance.
   *
   * @return a <tt>JwmaConfiguration</tt> instance.
   */
  public JwmaConfiguration getConfiguration() {
    return m_Configuration;
  }//getConfiguration

  /**
   * Returns the controller URL setting of the local
   * jwma installation.
   *
   * @return local controller's URL as <tt>String</tt>.
   */
  public String getMainControllerUrl() {
    return m_MainController;
  }//getMainControllerUrl

  /**
   * Returns the sendmail controller URL setting of the local
   * jwma installation.
   *
   * @return local sendmail controller's URL as <tt>String</tt>.
   */
  public String getSendMailControllerUrl() {
    return m_MailingController;
  }//getSendMailControllerUrl

  /**
   * Returns the admin controller URL setting of the local
   * jwma installation.
   *
   * @return local admin controller's URL as <tt>String</tt>.
   */
  public String getAdminControllerUrl() {
    return m_AdminController;
  }//getAdminControllerUrl

  /**
   * Returns the contacts controller URL setting of the local
   * jwma installation.
   *
   * @return local contacts controller's URL as <tt>String</tt>.
   */
  public String getContactsControllerUrl() {
    return m_ContactsController;
  }//getContactsControllerUrl

  /**
   * Returns the site's configured view URL for the given view
   * as <tt>String</tt>.
   *
   * <p>
   * The <tt>String</tt> parameter passed in <b>has to be</b>
   * one of the defined abstract view constants.
   *
   * @param view representing one of the constants defining an abstract
   *        view.
   *
   * @return local configured view URL of a given abstract view
   *         as <tt>String</tt>.
   *
   * @see #LOGIN_VIEW
   * @see #ERROR_VIEW
   * @see #LOGOUT_VIEW
   * @see #FOLDER_VIEW
   * @see #MESSAGE_VIEW
   * @see #PREFERENCES_VIEW
   * @see #COMPOSE_VIEW
   * @see #FIRSTTIME_VIEW
   * @see #CONTACTS_VIEW
   * @see #CONTACT_VIEW
   * @see #CONTACT_EDIT_VIEW
   * @see #CONTACTGROUP_VIEW
   * @see #CONTACTGROUP_EDIT_VIEW
   */
  public String getViewUrl(String view) {
    log.debug("getViewUrl():"+view+":"+m_Views.containsKey(view));
    return m_Views.getProperty(view);
  }//getViewUrl

  /**
   * Returns the active and activated <tt>PreferencesPersistencePlugin</tt>
   * instance that provides the persistency related functionality.
   *
   * @see dtw.webmail.plugin.PreferencesPersistencePlugin
   */
  public PreferencesPersistencePlugin getPrefsPersistencePlugin() {
    return m_PrefsPersistencePlugin;
  }//getPrefsPersistencePlugin

  /**
   * Returns the active and activated <tt>RandomAppendPlugin</tt>
   * instance that provides random appending functionality.
   * This method returns <tt>null</tt> if no such plugin is registered.
   *
   * @see dtw.webmail.plugin.RandomAppendPlugin
   */
  public RandomAppendPlugin getRandomAppendPlugin() {
    return m_RandomAppendPlugin;
  }//getRandomAppendPlugin

  /**
   * Returns the active and activated <tt>ContactManagementPlugin</tt>
   * instance that provides contact management related functionality.
   *
   * @see dtw.webmail.plugin.ContactManagementPlugin
   */
  public ContactManagementPlugin getContactManagementPlugin() {
    return m_ContactManagementPlugin;
  }//getContactManagementPlugin

  /**
   * Returns the class loader for i18n resource bundles.
   * <p>
   * @return the <tt>ClassLoader</tt> used for loading i18n <tt>ResourceBundle</tt>s.
   */
  public ClassLoader getResourceClassLoader() {
    return m_i18nLoader;
  }//getResourceClassLoader

  /**
   * Returns the log message for i18n resource bundles.
   * <p>
   * @return the <tt>ResourceBundle</tt> used for loading i18n.
   */
  public ResourceBundle getViewContentMessage() {
    return m_ViewContentmessages;
  }//getResourceClassLoader
  
  /**
   * Returns the error message for the given key in the
   * set system locale.
   * <p>
   * @return the error message as localized <tt>String</tt>.
   */
  public String getErrorMessage(String key) {
    if (key == null) {
      return "KEY NULL!!!!!!!!";
    }
    try {
      return m_ErrorMessages.getString(key);
    } catch (MissingResourceException mrex) {
      return ("#UNDEFINED=" + key + "#");
    }
  }//getErrorMessage

  /**
   * Returns the log message for the given key in the
   * set system locale.
   * <p>
   * @return the log message as localized <tt>String</tt>.
   */
  public String getLogMessage(String key) {
    if (key == null) {
      return "KEY NULL!!!!!!!!";
    }
    try {
      return m_LogMessages.getString(key);
    } catch (MissingResourceException mrex) {
      return ("#UNDEFINED=" + key + "#");
    }
  }//getLogMessage




  /*** Administration *************************************************************/

  /**
   * Tests if status is enabled.
   *
   * @return true if status is enabled, false otherwise.
   */
  public boolean isJwmaStatusEnabled() {
    return m_StatusEnabled;
  }//isJwmaStatusEnabled

  /**
   * Sets the flag that controls if the system's status is
   * enabled.
   *
   * @param true if status is enabled, false otherwise.
   */
  public void setJwmaStatusEnabled(boolean enabled) {
    m_StatusEnabled = enabled;
  }//setJwmaStatusEnabled


  /*** Status report related methods ***********************************************/

  /**
   * Returns the kernel status as <tt>int</tt>.
   * <ol>
   * <li>Directories set</li>
   * <li>Settings loaded</li>
   * <li>Logs prepared</li>
   * <li>Loaded error messages</li>
   * <li>prepared preferences persistency</li>
   * <li>prepared text processing</li>
   * <li>prepared mail services</li>
   * <li>prepared view urls</li>
   * </ol>
   *
   * @return kernel status as <tt>int</tt>.
   */
  public int getKernelStatus() {
    return m_Diag;
  }//getDiagnostic

  /**
   * Lists the directories jwma needs to access.
   *
   * @return array of strings representing the directories.
   */
  public String[] listDirectories() {
    String[] dirs = {
      m_RootDir,
      m_EtcDir,
      m_LogDir,
      m_DataDir
    };
    return dirs;
  }//listDirectories

  /**
   * Returns the path of the given directory.
   */
  public String getDirectoryPath(int DIRECTORY) {
    switch (DIRECTORY) {
      case ROOT_DIR:
        return m_RootDir;
      case ETC_DIR:
        return m_EtcDir;
      case LOG_DIR:
        return m_LogDir;
      case DATA_DIR:
        return m_DataDir;
      default:
        return null;
    }
  }//getDirectory

  /**
   * Lists the files jwma needs to access.
   *
   * @return array of strings representing the files.
   */
  public String[] listFiles() {
    String[] files = new String[0];
    /*
    if (m_Diag<2) {
      files=new String[1];
      files[0]=m_Settings.getPath();
    } else {
      //FILES:
      //String[] lang=new String[m_ErrorResources.size()];
      //lang=(String[]) m_ErrorResources.keySet().toArray(lang);

      files=new String[3+lang.length];
      //settings file path
      files[0]=m_Settings.getPath();
      //log files
      //files[1]=m_SyslogFile;
      //files[2]=m_DebuglogFile;

      //mapping file
      //files[3]=m_PreferencesMappingFile;

      //site preferences template
      //files[4]=m_PreferencesTemplateFile;

      //3- error messages files
      for (int i=0; i<lang.length; i++) {
        files[3+i]=m_EtcDir+"errormessages_"+lang[i]+".properties";

      }
    }
    */
    return files;
  }//listFiles

  /*** End status report related methods *******************************************/

  /*** Text processing related methods *********************************************/

  /**
   * Returns the message processor indicated by the given
   * name. If the name does not refer to any processing pipe,
   * then the default processing pipe will be returned.
   *
   * @param name String that should represent a valid processing
   *        pipe name.
   * @return Processor to be used for message processing.
   */
  public Processor getMessageProcessor(String name) {
    //shortcut for null name
    if (name == null || name.length() == 0) {
      return m_MessageProcessor;
    }
    //Try to get a pipe with the specified name
    Processor proc = m_ProcessingKernel.getProcessingPipe(name);
    if (proc == null) {
      //try to get a processor with the specified name
      proc = m_ProcessingKernel.getProcessor(name);
      if (proc == null) {
        //set the default processor
        proc = m_MessageProcessor;
      }
    }
    //return the pipe, the processor or the default
    return proc;
  }//getMessageProcessor

  public String[] listMessageProcessors() {
    //return just processing pipes for now
    return m_ProcessingKernel.listProcessingPipes();
  }//listMessageProcessors


  /*************************************************************************/

  /**
   * Returns the reference of the JwmaKernel singleton
   * instance.
   *
   * <p>
   * <i><b>Note:</b>this also implements kind of a factory method pattern.
   * If the singleton instance does not exist yet, it will be created.</i>
   *
   * @return reference of the <tt>JwmaKernel</tt> singleton
   *         instance.
   *
   */
  public static JwmaKernel getReference() {
    if (c_Self != null) {
      return c_Self;
    } else {
      return new JwmaKernel();
    }
  }//getReference

  /**
   * Defines the abstract login view.
   */
  public static final String LOGIN_VIEW = "view.login";

  /**
   * Defines the abstract error view.
   */
  public static final String ERROR_VIEW = "view.error";

  /**
   * Defines the abstract loggedout view.
   */
  public static final String LOGOUT_VIEW = "view.logout";

  /**
   * Defines the abstract subscribe view.
   */
  public static final String SUBSCRIBED_VIEW = "view.subscribed";

  /**
   * Defines the abstract unsubscribe view.
   */
  public static final String UNSUBSCRIBED_VIEW = "view.unsubscribed";

  /**
   * Defines the abstract folder view.
   */
  public static final String FOLDER_VIEW = "view.folder";

  /**
   * Defines the abstract mailbox view.
   */
  public static final String MAILBOX_VIEW = "view.mailbox";

  /**
   * Defines the abstract message view.
   */
  public static final String MESSAGE_VIEW = "view.message";

  /**
   * Defines the abstract compose view.
   */
  public static final String COMPOSE_VIEW = "view.compose";

  /**
   * Defines the abstract preferences view.
   */
  public static final String PREFERENCES_VIEW = "view.preferences";

  /**
   * Defines the abstract error firsttime view.
   */
  public static final String FIRSTTIME_VIEW = "view.firsttime";

  /**
   * Defines the abstract contacts view.
   */
  public static final String CONTACTS_VIEW = "view.contacts";

  /**
   * Defines the abstract contact view.
   */
  public static final String CONTACT_VIEW = "view.contact";

  /**
   * Defines the abstract contact edit view.
   */
  public static final String CONTACT_EDIT_VIEW = "view.contact.edit";

  /**
   * Defines the abstract contact group view.
   */
  public static final String CONTACTGROUP_VIEW = "view.contactgroup";

  /**
   * Defines the abstract contact group edit view.
   */
  public static final String CONTACTGROUP_EDIT_VIEW = "view.contactgroup.edit";


  /**
   * Defines the abstract admin status view.
   */
  public static final String ADMIN_STATUS_VIEW = "view.admin.status";

  /**
   * Defines the abstract admin preferences view.
   */
  public static final String ADMIN_PREFERENCES_VIEW = "view.admin.preferences";

  /**
   * Defines the abstract admin settings view.
   */
  public static final String ADMIN_SETTINGS_VIEW = "view.admin.settings";

  /**
   * Defines the abstract admin login view.
   */
  public static final String ADMIN_LOGIN_VIEW = "view.admin.login";

  /**
   * Defines the abstract admin menu view.
   */
  public static final String ADMIN_MENU_VIEW = "view.admin.menu";

  /**
   * Defines the abstract admin error view.
   */
  public static final String ADMIN_ERROR_VIEW = "view.admin.error";

  /**
   * Defines the abstract account creation view.
   */
  public static final String ACCOUNT_CREATION_VIEW = "view.account.creation";

  /**
   * Defines the root directory.
   */
  public static final int ROOT_DIR = 1;

  /**
   * Defines the root directory.
   */
  public static final int ETC_DIR = 2;

  /**
   * Defines the root directory.
   */
  public static final int LOG_DIR = 3;

  /**
   * Defines the data directory.
   */
  public static final int DATA_DIR = 4;

}//JwmaKernel
