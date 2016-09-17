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
import dtw.webmail.model.JwmaContact;
import dtw.webmail.model.JwmaContacts;
import dtw.webmail.model.JwmaContactsImpl;
import dtw.webmail.model.JwmaException;
import dtw.webmail.plugin.ContactManagementPlugin;
import dtw.webmail.util.CastorDatabasePool;
import dtw.webmail.util.JwmaSettings;
import org.apache.log4j.Logger;

import java.io.*;

/**
 * A <tt>ContactManagementPlugin</tt> implementation
 * based on Castor XML marshalling/unmarshalling.
 * <p>
 * Stores the contacts as XML files.
 *
 * @author Dieter Wimberger
 * @version 0.9.7 07/02/2003
 */
public class CastorXMLContactManagement
    implements ContactManagementPlugin {

  //logging
  private static Logger log = Logger.getLogger(CastorXMLContactManagement.class);


  private CastorPreferences m_PreferencesTemplate;
  private CastorDatabasePool m_DBPool;
  private String m_DataDir;

  public JwmaContactsImpl loadContacts(String cuid)
      throws JwmaException {

    log.debug("loadContacts(" + cuid + ")");
    CastorContacts cts = null;

    try {

      FileReader reader = new FileReader(getFilename(cuid));
      log.debug("have file reader");
      cts = (CastorContacts)
          CastorHelper.getReference().unmarshal(reader);
      log.debug("unmarshalled from reader");
      reader.close();
    } catch (Exception ex) {
      log.debug("loadContacts():", ex);
      if (ex instanceof JwmaException) {
        throw (JwmaException) ex;
      } else {
        throw new JwmaException(
            "jwma.plugin.std", true
        ).setException(ex);
      }
    }
    return cts;
  }//loadContacts

  public void saveContacts(JwmaContactsImpl contacts)
      throws JwmaException {

    try {
      FileWriter writer = new FileWriter(getFilename(contacts.getUID()));
      CastorHelper.getReference().marshal(contacts, writer);
      writer.close();
    } catch (Exception ex) {
      if (ex instanceof JwmaException) {
        throw (JwmaException) ex;
      } else {
        throw new JwmaException("").setException(ex);
      }
    }
  }//saveContacts

  public boolean isPersistent(String cuid)
      throws JwmaException {
    try {
      return new File(getFilename(cuid)).exists();
    } catch (Exception ex) {
      throw new JwmaException("").setException(ex);
    }
  }//isPersistent

  public JwmaContactsImpl createContacts() {
    return new CastorContacts();
  }//createContacts

  public void activate() throws JwmaException {
    JwmaKernel kernel = JwmaKernel.getReference();
    try {
      String etc = kernel.getDirectoryPath(JwmaKernel.ETC_DIR);

      //String m_MappingFile = etc + settings.getMappingFilename();

      m_DataDir = kernel.getDirectoryPath(JwmaKernel.DATA_DIR);

      //Setup castor helper
      CastorHelper helper = CastorHelper.getReference();

      //load mapping
      //helper.loadMapping(m_MappingFile);


      log.info(JwmaKernel.getReference().getLogMessage("jwma.plugin.activation"));
    } catch (Exception ex) {
      log.info(JwmaKernel.getReference().getLogMessage("jwma.plugin.failedactivation"));
      throw new JwmaException("").setException(ex);
    }
  }//activate

  public void deactivate() {
    //FIXME: shut down pool, close down connections?
  }//deactivate

  public String exportContact(JwmaContact contact, String TYPE)
      throws JwmaException {
    return "";
    /*
    if(TYPE.equals(TYPE_VCARD3)) {
      ByteArrayOutputStream bout=new ByteArrayOutputStream();

      m_vCardMarshaller.marshallContact(bout,((JpimContact)contact).getContact());
      return new String(bout.toByteArray());
    } else {
      return "";
    }
    */
  }//export

  public JwmaContact importContact(InputStream in, String TYPE)
      throws JwmaException {
    return null;
    /*
    if(TYPE.equals(TYPE_VCARD3) || TYPE.equals(TYPE_VCARD2)) {
      try {
        return new JpimContact(
          new vCardUnmarshaller()
                 .unmarshallContact(in)
        );
      } catch (Exception ex) {
        log.error("importContact():",ex);
        return null;
      }
    } else {
      return null;
    }
    */
  }//importContact

  public JwmaContact[] importDatabase(InputStream in, String TYPE)
      throws JwmaException {
    return null;
  }//importDatabase

  public void exportDatabase(OutputStream out, JwmaContacts db, String TYPE)
      throws JwmaException {
    return;
  }//exportDatabase

  public String[] getSupportedTypes(int IMEX_TYPE) {
    switch (IMEX_TYPE) {
      case CONTACT_IMPORT:
        return CONTACT_IMPORT_TYPES;
      case CONTACT_EXPORT:
        return CONTACT_EXPORT_TYPES;
      case DATABASE_EXPORT:
        return DATABASE_EXPORT_TYPES;
      case DATABASE_IMPORT:
        return DATABASE_IMPORT_TYPES;
      default:
        return NO_TYPES;
    }
  }//getSupportedTypes

  public boolean isSupportedContactImportType(String type) {
    for (int i = 0; i < CONTACT_IMPORT_TYPES.length; i++) {
      if (type.equalsIgnoreCase(CONTACT_IMPORT_TYPES[i])) {
        return true;
      }
    }
    return false;
  }//isSupportedContactImportType

  private String getFilename(String uid) {
    return m_DataDir + uid + ".xml";
  }//getFileName

  public static final String TYPE_VCARD3 = "text/directory";
  public static final String TYPE_VCARD2 = "application/directory";
  private static final String[] NO_TYPES = new String[0];

  private static final String[] CONTACT_IMPORT_TYPES = NO_TYPES;
  private static final String[] CONTACT_EXPORT_TYPES = NO_TYPES;
  private static final String[] DATABASE_EXPORT_TYPES = NO_TYPES;
  private static final String[] DATABASE_IMPORT_TYPES = NO_TYPES;

}//class CastorContactManagement

