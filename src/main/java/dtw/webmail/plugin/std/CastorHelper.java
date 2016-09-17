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
import dtw.webmail.model.JwmaException;
import dtw.webmail.util.CastorDatabasePool;
import org.apache.log4j.Logger;
import org.exolab.castor.jdo.Database;
import org.exolab.castor.jdo.JDO;
import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

import java.io.Reader;
import java.io.Writer;
import java.net.URL;

/**
 * An utility/helper class with common functionality
 * for the standard Castor based plugin implementations.
 *
 * @author Dieter Wimberger
 * @version 0.9.7 07/02/2003
 */
public class CastorHelper {

  //Singleton reference
  private static CastorHelper c_Self;
  //logging
  private static Logger log =
      Logger.getLogger(CastorHelper.class);


  //instance attributes
  private Mapping m_Mapping;		//Mapping instance
  private JDO m_JDO;				//JDO Engine instance
  private CastorDatabasePool m_DBPool;
  private boolean m_JDOprepared = false;

  private CastorHelper() {
    //set reference to single instance
    c_Self = this;
    //load mapping
    loadMapping();
  }//CastorHelper

  /**
   * Marshals an object to the given writer.
   *
   * @param object the instance to be marshalled.
   * @param writer the <tt>Writer</tt>.
   *
   * @throws JwmaException on failure.
   */
  public void marshal(Object object, Writer writer)
      throws JwmaException {
    try {
      Marshaller m_Marshaller = new Marshaller(writer);
      m_Marshaller.setMapping(m_Mapping);
      m_Marshaller.marshal(object);
      writer.flush();
    } catch (Exception ex) {
      throw new JwmaException("").setException(ex);
    }
  }//marshal

  /**
   * Unmarshalls an object from the given reader.
   *
   * @param reader the <tt>Reader</tt>.
   */
  public Object unmarshal(Reader reader)
      throws JwmaException {
    try {
      Unmarshaller m_Unmarshaller = new Unmarshaller(m_Mapping);
      return m_Unmarshaller.unmarshal(reader);
    } catch (Exception ex) {
      throw new JwmaException("").setException(ex);
    }
  }//unmarshal

  /**
   * Prepares Castor JDO, with configuration
   * (i.e. database.xml) given as URL.
   *
   * @param configurl the URL to the configuration file.
   */
  public void prepareJDO(String configurl)
      throws JwmaException {
    if (m_JDOprepared) {
      return;
    }
    try {
      //create new jdo object
      m_JDO = new JDO();
      //DEBUG:
      //m_JDO.setLogWriter(new org.exolab.castor.util.Logger(
      //    new PrintWriter(new FileWriter("/Users/wimpi/castorlog.txt"))
      //));
      //load configuration
      m_JDO.loadConfiguration(configurl);

      //pooling connections
      m_JDO.setDatabasePooling(true);
      //the name of the database
      m_JDO.setDatabaseName("jwma");
      //set jwma's class loader, just in case
      //castor was loaded with some strange class loader
      m_JDO.setClassLoader(JwmaKernel.getReference()
          .getClass().getClassLoader());

      //prepare pool
      m_DBPool = CastorDatabasePool.createCastorDatabasePool(
          5, new JwmaDatabase()
      );
      m_JDOprepared = true;
    } catch (Exception ex) {
      String msg = JwmaKernel.getReference().getLogMessage("jwma.plugin.castorhelper.jdofail");
      log.error(msg, ex);
      throw new JwmaException(msg).setException(ex);
    }
  }//prepareJDO

  /**
   * Returns a Castor JDO database.
   *
   * @return a Castor JDO <tt>Database</tt> instance.
   */
  public Database getJDODatabase()
      throws JwmaException {
    try {
      return m_JDO.getDatabase();
    } catch (Exception ex) {
      throw new JwmaException("").setException(ex);
    }
  }//getJDODatabase

  /**
   * Returns the pool of Castor JDO Database
   * instances.
   *
   * @return the pool as <tt>CastorDatabasePool</tt>.
   */
  public CastorDatabasePool getDatabasePool() {
    return m_DBPool;
  }//getDatabasePool

  /**
   * Returns a reference to the singleton instance
   * of the <tt>CastorHelper</tt>.
   *
   * @return the <tt>CastorHelper</tt> singleton.
   */
  public static CastorHelper getReference() {
    if (c_Self != null) {
      return c_Self;
    } else {
      return new CastorHelper();
    }
  }//getReference


  /**
   * Loads the castor mapping file.
   *
   * @param file the url of the mapping file.
   */
  private void loadMapping() {

    log.info("Loading Castor mappings.");

    ClassLoader cl = this.getClass().getClassLoader();
    URL mapping = cl.getResource("dtw/webmail/resources/castor_mappings.xml");
    m_Mapping = new Mapping(cl);

    try {
      m_Mapping.loadMapping(mapping);
    } catch (Exception ex) {
      log.fatal("loadMappings()", ex);
    }
  }//loadMapping

}//class CastorHelper