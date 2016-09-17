/***
 * jwma Java WebMail
 * Copyright (c) 2000-2003 jwma team
 *
 * jwma is free software; you can distribute and use this source
 * under the terms of the BSD-style license received along with
 * the distribution.
 ***/
package dtw.webmail.plugin.std;

import dtw.webmail.JwmaKernel;
import dtw.webmail.JwmaSession;
import dtw.webmail.model.JwmaException;
import dtw.webmail.model.JwmaMailIdentityImpl;
import dtw.webmail.model.JwmaPreferencesImpl;
import dtw.webmail.plugin.PreferencesPersistencePlugin;
import dtw.webmail.util.MD5;
import dtw.webmail.util.config.JwmaConfiguration;
import dtw.webmail.util.config.PostOffice;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * A <tt>PreferencesPersistancePlugin</tt> implementation
 * based on Castor XML marshalling/unmarshalling.
 * <p>
 * Stores the preferences as XML files.
 *
 * @author Dieter Wimberger
 * @version 0.9.7 07/02/2003
 */
public class CastorXMLPreferencesPersistence
    implements PreferencesPersistencePlugin {

  //logging
  private static Logger log = Logger.getLogger(CastorXMLPreferencesPersistence.class);


  //Preferences
  private String m_PreferencesTemplateFile;	//preferences template file
  private String m_PreferencesMappingFile;	//object <-> xml mapping file (Castor)
  private String m_DataDir;
  private CastorPreferences m_PreferencesTemplate;

  public JwmaPreferencesImpl loadPreferences(String identity)
      throws JwmaException {

    JwmaPreferencesImpl prefs = null;

    try {
      String filename = getFilename(identity);
      File f = new File(filename);
      FileReader reader = null;

      if (f.isAbsolute()) {
        reader = new FileReader(f);
      } else {
        reader = new FileReader(m_DataDir + filename);
      }

      prefs = (CastorPreferences)
          CastorHelper.getReference().unmarshal(reader);

      reader.close();
    } catch (Exception ex) {
      throw new JwmaException(
          "jwma.plugin.std", true
      ).setException(ex);
    }
    return prefs;
  }//loadPreferences

  public void savePreferences(JwmaPreferencesImpl prefs)
      throws JwmaException {

    try {
      String filename = getFilename(prefs.getUserIdentity());
      FileWriter writer = new FileWriter(m_DataDir + filename);
      CastorHelper.getReference().marshal(prefs, writer);
      writer.close();
    } catch (Exception ex) {
      throw new JwmaException("").setException(ex);
    }
  }//savePreferences

  public boolean isPersistent(String identity) throws JwmaException {
    try {
      File file = new File(m_DataDir + getFilename(identity));
      return file.exists();
    } catch (Exception ex) {
      throw new JwmaException("").setException(ex);
    }
  }//isPersistent

  public JwmaPreferencesImpl getPreferencesTemplate(JwmaSession session) {

    //1. get clone
    JwmaPreferencesImpl prefs = m_PreferencesTemplate.getClone();

    //2. make post office config dependent adjustments:
    PostOffice po = session.getPostOffice();

    //set root folder
    prefs.setRootFolder(po.getRootFolder());

    //set replyto domain from postoffice
    String replyto = po.getReplyToDomain();
    if (replyto == null || replyto.length() == 0) {
      replyto = po.getAddress();
    }

    ((JwmaMailIdentityImpl) prefs.getMailIdentity()).setReplyTo(
        session.getUsername() + "@" + replyto
    );

    return prefs;
  }//getPreferencesTemplate

  public void activate() throws JwmaException {
    try {
      JwmaKernel kernel = JwmaKernel.getReference();
      String etc = kernel.getDirectoryPath(JwmaKernel.ETC_DIR);

      //String m_MappingFile = etc + settings.getMappingFilename();
      m_PreferencesTemplateFile = etc + JwmaConfiguration.TEMPLATE_FILENAME;
      m_DataDir = kernel.getDirectoryPath(JwmaKernel.DATA_DIR);

      //Setup castor helper
      CastorHelper helper = CastorHelper.getReference();

      //load mapping
      //helper.loadMapping(m_MappingFile);

      //load the template
      m_PreferencesTemplate = (CastorPreferences)
          helper.unmarshal(new FileReader(m_PreferencesTemplateFile));

      log.info(JwmaKernel.getReference().getLogMessage("jwma.plugin.activation"));
    } catch (Exception ex) {
      log.info(JwmaKernel.getReference().getLogMessage("jwma.plugin.failedactivation"));
      if (ex instanceof JwmaException) {
        throw (JwmaException) ex;
      }
      throw new JwmaException("").setException(ex);
    }
  }//activate

  public void deactivate() {
    //nothing important
  }//deactivate

  public void setPreferencesTemplate(JwmaPreferencesImpl template)
      throws JwmaException {

    //set the reference immediately
    m_PreferencesTemplate = (CastorPreferences) template;
    //store
    try {
      FileWriter writer = new FileWriter(m_PreferencesTemplateFile);
      CastorHelper.getReference().marshal(template, writer);
      writer.close();
    } catch (Exception ex) {
      throw new JwmaException("").setException(ex);
    }
  }//setPreferencesTemplate

  private String getFilename(String identity) {
    return MD5.hash(identity) + ".xml";
  }//getFileName

}//class CastorXMLPreferencesPersistence