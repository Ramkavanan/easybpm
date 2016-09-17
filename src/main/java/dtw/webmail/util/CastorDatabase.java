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

import org.exolab.castor.jdo.*;
import dtw.webmail.model.JwmaException;

/**
 * Abstract class that encapsulates a <tt>Database</tt>
 * from the Castor lib, to enhance performance by adding
 * the reuse of queries (which is perfectly possible).
 *
 * @author Dieter Wimberger
 * @version 0.9.7 07/02/2003
 */
public abstract class CastorDatabase {

  protected Database m_Database;
  protected HashMap m_Queries;

  /**
   * Simple and empty constructor, to create a lightweight
   * instance that serves only as factory object for "real"
   * instances.
   */
  public CastorDatabase() {
  }//constructor

  /**
   * Delegates creation of an OQL query with no statement.
   * {@link OQLQuery#create} must be called before the
   * query can be executed.
   *
   * @return a new nonprepared OQL query.
   */
  public OQLQuery getOQLQuery() {
    return m_Database.getOQLQuery();
  }//OQLQuery

  /**
   * Delegates creation of an OQL query from the
   * supplied statement.
   *
   * @param query An OQL query statement
   *
   * @return An OQL query
   *
   * @throws QueryException The query syntax is invalid
   */
  public OQLQuery getOQLQuery(String oql) throws QueryException {
    return m_Database.getOQLQuery(oql);
  }//getOQLQuery

  /**
   * Delegates loading of an object of the specified type and
   * given identity to the encapsulated <tt>Database</tt>.
   *
   *
   * @param type The object's type
   * @param identity The object's identity
   *
   * @throws ObjectNotFoundException No object of the given type and
   *  identity was found in persistent storage.
   * @throws LockNotGrantedException Timeout or deadlock occured
   *  attempting to acquire a lock on the object.
   * @throws TransactionNotInProgressException Method called while
   *   transaction is not in progress.
   * @throws PersistenceException An error reported by the
   *  persistence engine.
   */
  public Object load(Class type, Object identity)
      throws TransactionNotInProgressException, ObjectNotFoundException,
      LockNotGrantedException, PersistenceException {

    return m_Database.load(type, identity);
  }//load

  /**
   * Delegates creation of a new object in persistent storage to the
   * encapsulated <tt>Database</tt>. The object will only be persisted
   * if the transaction commits.
   *
   * @param object The object to create
   *
   * @throws TransactionNotInProgressException Method called while
   *   transaction is not in progress.
   * @throws DuplicateIdentityException An object with this identity
   *  already exists in persistent storage
   * @throws ClassNotPersistenceCapableException The class is not
   *  persistent capable.
   * @throws PersistenceException An error reported by the
   *  persistence engine.
   */
  public void create(Object object)
      throws ClassNotPersistenceCapableException, DuplicateIdentityException,
      TransactionNotInProgressException, PersistenceException {
    m_Database.create(object);
  }//create

  /**
   * Delegates removal of the object from persistent storage.
   * The deletion will take effect only if the transaction is committed,
   * but the object is no longer visible to queries in the current
   * transaction and locks for access from other transactions will
   * block until this transaction completes.
   *
   * @param object The object to remove
   *
   * @throws TransactionNotInProgressException Method called while
   *   transaction is not in progress.
   * @throws ObjectNotPersistentException The object has not been
   *  queried or created in this transaction.
   * @throws LockNotGrantedException Timeout or deadlock occured
   *  attempting to acquire a lock on the object.
   * @throws PersistenceException An error reported by the
   *  persistence engine.
   */
  public void remove(Object object)
      throws ObjectNotPersistentException, LockNotGrantedException,
      TransactionNotInProgressException, PersistenceException {
    m_Database.remove(object);
  }//remove

  /**
   * Delegates updating a data object which is queried/loaded/created
   * in <b>another transaction</b> to the encapsulated <tt>Database</tt>.
   * This method is used only for long transaction support.
   * Calling this method for data object queried/loaded/created
   * in the same transaction results in Exception.
   *
   * @param object The object to create
   *
   * @throws TransactionNotInProgressException Method called while
   *   transaction is not in progress.
   * @throws ClassNotPersistenceCapableException The class is not
   *  persistent capable.
   * @throws PersistenceException An error reported by the
   *  persistence engine.
   */
  public void update(Object object)
      throws ClassNotPersistenceCapableException,
      TransactionNotInProgressException, PersistenceException {
    m_Database.update(object);
  }//update

  /**
   * Delegates testing of the current transaction autoStore flag state
   * to the encapsulated <tt>Database</tt>.
   *
   * @return true if autostoring is on, false otherwise.
   */
  public boolean isAutoStore() {
    return m_Database.isAutoStore();
  }//isAutoStore


  /**
   * Delegates setting of the autoStore flag state
   * to the encapsulated <tt>Database</tt>.
   *
   * @param autoStore true if on, false otherwise.
   */
  public void setAutoStore(boolean autoStore) {
    m_Database.setAutoStore(autoStore);
  }//setAutoStore

  /**
   * Delegates beginning a new transaction to the encapsulated
   * <tt>Database</tt>. A transaction must be open in order
   * to query and persist objects.
   *
   * @throws PersistenceException A transaction is already open on
   *  this database, or an error reported by the persistence engine
   */
  public void begin() throws PersistenceException {
    m_Database.begin();
  }//begin

  /**
   * Delegates the rollback method to the encapsulated
   * <tt>Database</tt> instance.
   */
  public void rollback()
      throws TransactionNotInProgressException {

    m_Database.rollback();
  }//rollback


  /**
   * Delegates the commit method to the encapsulated
   * <tt>Database</tt> instance.
   *
   * @throws TransactionNotInProgressException Method called while
   *  transaction is not in progress.
   * @throws TransactionAbortedException The transaction cannot
   *  commit and has been rolled back.
   */
  public void commit()
      throws TransactionNotInProgressException, TransactionAbortedException {

    m_Database.commit();
  }//commit

  /**
   * Tests if a transaction is currently active.
   *
   * @return true if a transaction is active, false otherwise.
   */
  public boolean isActive() {
    return m_Database.isActive();
  }//isActive


  /**
   * Tests if a database has been closed.
   *
   * @return true if the database has been closed, false otherwise.
   */
  public boolean isClosed() {
    return m_Database.isClosed();
  }//isClosed


  /**
   * Closes the database. If a client transaction is in progress the
   * transaction will be rolled back and an exception thrown.
   * If an app-server transaction is in progress, the transaction
   * will commit/rollback when triggered by the application server.
   *
   * @throws PersistenceException An error occured while
   *  attempting to close the database
   */
  public void close()
      throws PersistenceException {
    m_Database.close();
  }//close


  /**
   * Delegates testing of a given object's persistency state.
   * An object is persistent if it was created or queried
   * in this transaction. If the object was created or queried
   * in another transaction, or there is no open transaction,
   * this method returns null(??).
   *
   * @param object the object to be tested.
   *
   * @return True if persistent in this transaction
   */
  public boolean isPersistent(Object object) {
    return m_Database.isPersistent(object);
  }//isPersistent


  /**
   * Delegates checking an object's identity to the encapsulated
   * <tt>Database</tt>.
   *
   * @param object the object to be checked.
   *
   * @return the object's identity, or null if nonexistant.
   */
  public Object getIdentity(Object object) {
    return m_Database.getIdentity(object);
  }//getIdentity

  /**
   * Returns the encapsulated <tt>Database</tt> instance.
   *
   * @return the encapsulated database instance.
   */
  public Database getDatabase() {
    return m_Database;
  }//getDatabase

  public OQLQuery getQuery(String identifier) {
    return (OQLQuery) m_Queries.get(identifier);
  }//getQuery

  public void putQuery(String identifier, OQLQuery query) {
    m_Queries.put(identifier, query);
  }//putQuery

  public abstract CastorDatabase createCastorDatabase()
      throws JwmaException;

}//CastorDatabase
