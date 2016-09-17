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
import dtw.webmail.model.JwmaPreferencesImpl;
import dtw.webmail.model.JwmaMailIdentityImpl;
import dtw.webmail.plugin.PreferencesPersistencePlugin;
import dtw.webmail.util.CastorDatabase;
import dtw.webmail.util.CastorDatabasePool;
import dtw.webmail.util.JwmaSettings;
import dtw.webmail.util.config.JwmaConfiguration;
import dtw.webmail.util.config.PostOffice;
import org.apache.log4j.Logger;
import org.exolab.castor.jdo.Query;

import java.io.FileReader;
import java.io.FileWriter;

/**
 * A <tt>PreferencesPersistancePlugin</tt> implementation
 * based on Castor JDO.
 * <p>
 * Stores the preferences in a database.
 *
 * @author Dieter Wimberger
 * @version 0.9.7 07/02/2003
 */
public class CastorSQLPreferencesPersistence
    implements PreferencesPersistencePlugin {

  //logging
  private static Logger log = Logger.getLogger(CastorSQLPreferencesPersistence.class);

  private String m_PreferencesTemplateFile;
  private CastorPreferences m_PreferencesTemplate;
  private CastorDatabasePool m_DBPool;

  public JwmaPreferencesImpl loadPreferences(String identity)
      throws JwmaException {

    CastorPreferences prefs = null;
    CastorDatabase db = null;
    try {
      db = m_DBPool.leaseDatabase();
      //Begin transaction
      db.begin();
      //get cached query
      Query oql = db.getQuery(
          JwmaDatabase.PREFINSTANCE_BYUSERID
      );
      //bind param
      oql.bind(identity);
      //retrieve prefs instance
      prefs = (CastorPreferences) oql.execute().next();
      //end transaction
      db.commit();

    } catch (Exception ex) {
      throw new JwmaException("").setException(ex);
    } finally {
      //release the database
      m_DBPool.releaseDatabase(db);
    }
    return prefs;
  }//loadPreferences

  public void savePreferences(JwmaPreferencesImpl prefs)
      throws JwmaException {

    CastorDatabase db = null;
    CastorPreferences cprefs = (CastorPreferences) prefs;
    try {
      db = m_DBPool.leaseDatabase();
      // Begin a transaction
      db.begin();

      //check if the preferences are not persistent yet.
      //simplest is if the timestamp is not zero.
      if (cprefs.jdoGetTimeStamp() != 0) {
        cprefs.updatePreferences(db.getDatabase());
      } else {
        cprefs.persistPreferences(db.getDatabase());
      }
      // Commit the transaction
      db.commit();
    } catch (Exception ex) {
      throw new JwmaException("").setException(ex);
    } finally {
      m_DBPool.releaseDatabase(db);
    }
  }//savePreferences

  public boolean isPersistent(String identity)
      throws JwmaException {

    //FIXME:This is definately not very efficient, but
    //if ok, and castor caches..hmm
    if (identity == null || identity.length() == 0) {
      return false;
    }
    CastorDatabase db = null;
    boolean exists = false;
    try {
      db = m_DBPool.leaseDatabase();
      //Begin transaction
      db.begin();

      //get cached query
      Query oql = db.getQuery(
          JwmaDatabase.PREFINSTANCE_BYUSERID
      );
      //bind param
      oql.bind(identity);

      //retrieve prefs instance
      if (oql.execute().hasMore()) {
        exists = true;
      }

      //end transaction
      db.commit();

    } catch (Exception ex) {
      String msg = JwmaKernel.getReference().getLogMessage("jwma.plugin.castor.objcheck");
      log.error(msg, ex);
      throw new JwmaException(msg).setException(ex);
    } finally {
      //release the database
      m_DBPool.releaseDatabase(db);
    }
    return exists;
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
    JwmaKernel kernel = JwmaKernel.getReference();
    try {
      String etc = kernel.getDirectoryPath(JwmaKernel.ETC_DIR);

      //String m_MappingFile = etc + settings.getMappingFilename();
      m_PreferencesTemplateFile = etc + JwmaConfiguration.TEMPLATE_FILENAME;

      //Setup castor helper
      CastorHelper helper = CastorHelper.getReference();

      //load mapping
      //helper.loadMapping(m_MappingFile);

      //prepare jdo
      helper.prepareJDO(etc + "database.xml");

      //set the pool
      m_DBPool = helper.getDatabasePool();

      //load the template
      m_PreferencesTemplate = (CastorPreferences)
          helper.unmarshal(new FileReader(m_PreferencesTemplateFile));

      log.info(JwmaKernel.getReference().getLogMessage("jwma.plugin.activation"));
    } catch (Exception ex) {
      log.info(JwmaKernel.getReference().getLogMessage("jwma.plugin.failedactivation"));
      throw new JwmaException("").setException(ex);
    }
  }//activate

  public void deactivate() {
    //FIXME: shut down pool, close down connections?
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

}//class CastorSQLPreferencesPersistence