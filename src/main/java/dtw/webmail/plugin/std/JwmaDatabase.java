/***
 * jwma Java WebMail
 * Copyright (c) 2000-2003 jwma team
 *
 * jwma is free software; you can distribute and use this source
 * under the terms of the BSD-style license received along with
 * the distribution.
 ***/
package dtw.webmail.plugin.std;

import dtw.webmail.model.JwmaException;
import dtw.webmail.util.CastorDatabase;
import org.apache.log4j.Logger;
import org.exolab.castor.jdo.Database;
import org.exolab.castor.jdo.PersistenceException;

import java.util.HashMap;

//import dtw.webmail.JwmaKernel;

/**
 * Class extending the abstract CastorDatabase class,
 * to specialize it for the jwma database.
 * It caches and reuses queries used within jwma.
 *
 * @author Dieter Wimberger
 * @version 0.9.7 07/02/2003
 */
public class JwmaDatabase extends CastorDatabase {

  //logging
  private static Logger log =
      Logger.getLogger(JwmaDatabase.class);

  public JwmaDatabase() {
  }

  /**
   * Creates a new instance with a prepared amount
   * of slots for queries.
   * This denotes a size for a hashmap, add 30% more
   * then you really want to use for good performance.
   */
  private JwmaDatabase(Database db, int queries) {
    m_Database = db;
    m_Queries = new HashMap(queries);
  }//constructor

  public CastorDatabase createCastorDatabase()
      throws JwmaException {

    CastorDatabase newdb =
        new JwmaDatabase(CastorHelper.getReference().getJDODatabase(), 5);
    //add prepared queries
    //1. preferences instance query
    try {
      newdb.putQuery(PREFINSTANCE_BYUSERID,
          newdb.getOQLQuery(PREFINSTANCE_BYUSERID_QUERY)
      );
      log.debug("Set new Query");
    } catch (PersistenceException pex) {
      throw new JwmaException("").setException(pex);
    }
    return newdb;
  }//createCastorDatabase

  /**
   * Defines the identifier of the query for a preferences instance
   * by user identity.
   */
  public static final String PREFINSTANCE_BYUSERID = "preferencesbyuserid";

  private static final String PREFINSTANCE_BYUSERID_QUERY =
      "SELECT p FROM dtw.webmail.plugin.std.CastorPreferences p WHERE UserIdentity=$1";

}//JwmaDatabase