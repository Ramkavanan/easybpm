/***
 * jwma Java WebMail
 * Copyright (c) 2000-2003 jwma team
 *
 * jwma is free software; you can distribute and use this source
 * under the terms of the BSD-style license received along with
 * the distribution.
 ***/
package dtw.webmail.util;

import java.util.*;

import org.apache.log4j.Logger;
import org.exolab.castor.jdo.*;

/**
 * Class that implements a pool for <tt>CastorDatabase</tt>
 * instances.
 * <br>
 * The factory method is used for creating pools of the
 * given database and size, using the database's factory method
 * to create the requested amount of instances.
 *
 * @author Dieter Wimberger
 * @version 0.9.7 07/02/2003
 */
public class CastorDatabasePool {

  //logging
  private static Logger log = Logger.getLogger(CastorDatabasePool.class);

  //Instance attributes
  private List m_Pool;
  private boolean m_Serving = false;
  private CastorDatabase m_Database;
  private int m_Size;

  /**
   * Private, and empty to disallow any form of construction
   * via new.
   */
  private CastorDatabasePool() {
  }//CastorDatabasePool

  /**
   * Constructs a <tt>CastorDatabasePool</tt> instance.
   */
  private CastorDatabasePool(int size, CastorDatabase database) {
    m_Size = size;
    m_Database = database;
  }//CastorDatabasePool

  /**
   * Returns a leased <tt>CastorDatabase</tt> instance
   * from this pool.
   *
   * @return the leased <tt>CastorDatabase</tt> from this pool.
   */
  public synchronized CastorDatabase leaseDatabase() {
    if (!m_Serving) {
      return null;
    }
    boolean served = false;
    CastorDatabase freeDatabase = null;
    while (!served) {
      if (m_Pool.size() == 0) {
        try {
          wait();
        } catch (Exception ex) {
          //do nothing but wait again :)
        }
      } else {
        freeDatabase = (CastorDatabase) m_Pool.get(0);
        m_Pool.remove(0);
        served = true;
      }
    }
    return freeDatabase;
  }//leaseDatabase

  /**
   * Releases a formerly leased <tt>CastorDatabase</tt>, returning
   * it into this pool.
   *
   * @param database the formerly leased <tt>CastorDatabase</tt>
   *        to be released.
   */
  public synchronized void releaseDatabase(CastorDatabase database) {
    //ensure the last transaction has been commited:
    if (database.isActive()) {
      try {
        database.commit();
      } catch (PersistenceException ex) {
        try {
          database.rollback();
        } catch (TransactionNotInProgressException tex) {
          //do nothing, should not happen, just satisfy compiler
        }
      }
    }
    if (database.isClosed()) {
      //replace with another one
      try {
        database = m_Database.createCastorDatabase();
      } catch (Exception ex) {
        notifyAll();
        return;
      }
    }
    m_Pool.add(database);
    notifyAll();
  }//releaseDatabase

  /**
   * Returns the ceiling (in terms of size) of this pool.
   *
   * @return the ceiling size of this pool as <tt>int</tt>.
   */
  public int getCeiling() {
    return m_Size;
  }//getCeiling

  /**
   * Returns the size of this pool.
   *
   * @return the actual size of this pool as <tt>int</tt>.
   */
  public int size() {
    return m_Pool.size();
  }//size

  /**
   * Resizes the pool.
   *
   * @param size the new size as <tt>int</tt>.
   */
  public void resize(int size) {
    //FIXME: not very efficient
    synchronized (m_Pool) {
      clear();
      m_Size = size;
      initPool();
    }
  }//resize

  /**
   * Removes all <tt>CastorDatabase</tt> references from
   * this pool.
   * Note that it will do so gracefully, waiting for
   * leased <tt>CastorDatabases</tt>.
   */
  public void clear() {
    m_Serving = false;
    //wait until pool complete
    while (m_Pool.size() != m_Size) {
      try {
        wait();
      } catch (Exception ex) {
        //do nothing but wait again :)
      }
    }
    //remove all elements
    m_Pool.clear();
  }//clearPool


  /**
   * Initializes a pool of <tt>CastorDatabases</tt> instances.
   */
  protected void initPool() {
    m_Pool = new ArrayList(m_Size);
    for (int i = 0; i < m_Size; i++) {
      try {
        m_Pool.add(m_Database.createCastorDatabase());
        log.debug("Added new database");
      } catch (Exception ex) {
        //log probably
        ex.printStackTrace();
      }
    }
    m_Serving = true;
  }//initPool

  /**
   * Creates a <tt>CastorDatabasePool</tt> with the given size,
   * containing the given <tt>CastorDatabase</tt> instances.
   *
   * @param size the size as <tt>int</tt>.
   * @param CastorDatabase the <tt>CastorDatabase</tt> to be pooled.
   */
  public static CastorDatabasePool createCastorDatabasePool(int size,
                                                            CastorDatabase database) {

    CastorDatabasePool pool = new CastorDatabasePool(size, database);
    pool.initPool();
    return pool;
  }//createCastorDatabasePool


}//class CastorDatabasePool
