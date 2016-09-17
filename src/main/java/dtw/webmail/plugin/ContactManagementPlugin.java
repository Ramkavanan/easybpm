/***
 * jwma Java WebMail
 * Copyright (c) 2000-2003 jwma team
 *
 * jwma is free software; you can distribute and use this source
 * under the terms of the BSD-style license received along with
 * the distribution.
 ***/
package dtw.webmail.plugin;

import dtw.webmail.model.JwmaContactsImpl;
import dtw.webmail.model.JwmaException;
import dtw.webmail.model.JwmaContact;
import dtw.webmail.model.JwmaContacts;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Interface  for a plugin that manages
 * user contact databases.<p>
 *
 * @author Dieter Wimberger
 * @version 0.9.7 07/02/2003
 */
public interface ContactManagementPlugin
    extends JwmaPlugin {

  /**
   * Loads the given contacts database from the
   * persistent store and returns them as a <tt>
   * JwmaContactsImpl</tt> instance.
   * <p>
   * If contacts database with the given identity does not
   * exist on the store, the method returns <tt>null</tt>.
   *
   * @param cuid a <tt>String</tt> representing a unique
   *        identifier for a contact database.
   *
   * @return the <tt>JwmaContactsImpl</tt> instance.
   *
   * @throws JwmaException if an I/O error occurs.
   *
   * @see dtw.webmail.model.JwmaContactsImpl
   * @see dtw.webmail.model.JwmaPreferencesImpl#getContactDatabaseID()
   */
  public JwmaContactsImpl loadContacts(String cuid)
      throws JwmaException;

  /**
   * Saves the given contacts database to the persistent store.
   * <p>
   * If the contacts database does not exist on the store,
   * the method should create it, otherwise update
   * the existing version.<br>
   * The unique identity is retrieved using the <tt>getUID()</tt>
   * method of the given <tt>JwmaContactsImpl</tt> instance.
   *
   * @param contacts the <tt>JwmaContactsImpl</tt> instance to
   *        saved.
   *
   * @throws JwmaException if an I/O error occurs.
   *
   * @see dtw.webmail.model.JwmaContactsImpl
   * @see dtw.webmail.model.JwmaContactsImpl#getUID()
   */
  public void saveContacts(JwmaContactsImpl contacts)
      throws JwmaException;

  /**
   * Tests if a given contact database exists
   * on the store.
   *
   * @param cuid a <tt>String</tt> representing a unique
   *        identifier for a contact database.
   *
   * @throws JwmaException if an I/O error occurs.
   *
   * @see dtw.webmail.model.JwmaContactsImpl
   * @see dtw.webmail.model.JwmaPreferencesImpl#getContactDatabaseID()
   */
  public boolean isPersistent(String cuid)
      throws JwmaException;

  /**
   * Creates a new contact databse instance.
   *
   * @return the <tt>JwmaContactsImpl</tt> instance.
   *
   * @see dtw.webmail.model.JwmaContactsImpl
   */
  public JwmaContactsImpl createContacts();

  /**
   * Exports a contact in the given type.
   *
   * @param contact the <tt>JwmaContact</tt> to be exported.
   * @param TYPE the type of the export.
   *
   * @return the exported contact as <tt>String</tt>.
   *
   * @see #getSupportedTypes(int IMEX_TYPE)
   * @see #CONTACT_EXPORT
   */
  public String exportContact(JwmaContact contact, String TYPE)
      throws JwmaException;

  /**
   * Imports a contact of the given type.
   *
   * @param in source for importing the contact in the given type.
   * @param TYPE the type of the import source.
   *
   * @return the imported contact as <tt>JwmaContact</tt>.
   *
   * @see #getSupportedTypes(int IMEX_TYPE)
   * @see #CONTACT_IMPORT
   */
  public JwmaContact importContact(InputStream in, String TYPE)
      throws JwmaException;

  /**
   * Exports the given contact database in the given type.
   *
   * @param the <tt>OutputStream</tt> to export to.
   * @param db the <tt>JwmaContacts</tt> database to be exported.
   * @param TYPE the type of the export.
   *
   *
   * @see #getSupportedTypes(int IMEX_TYPE)
   * @see #DATABASE_EXPORT
   */
  public void exportDatabase(OutputStream out, JwmaContacts db, String TYPE)
      throws JwmaException;

  /**
   * Imports a database of contacts of the given type.
   *
   * @param in source for importing the contacts in the given type.
   * @param TYPE the type of the import source.
   *
   * @return the imported contacts as <tt>JwmaContact[]</tt>.
   *
   * @see #getSupportedTypes(int IMEX_TYPE)
   * @see #DATABASE_IMPORT
   */
  public JwmaContact[] importDatabase(InputStream in, String Type)
      throws JwmaException;

  /**
   * Returns a list of types for the given import/export method.
   * The types from this array can be passed as parameters
   * to the respective import/export method.
   *
   * @return an array of <tt>String</tt>'s representing types.
   */
  public String[] getSupportedTypes(int IMEX_TYPE);

  /**
   * Tests if the given contact type can be imported.
   *
   * @return true if supported, false otherwise.
   */
  public boolean isSupportedContactImportType(String type);

  public static final int CONTACT_EXPORT = 0;
  public static final int CONTACT_IMPORT = 1;
  public static final int DATABASE_EXPORT = 2;
  public static final int DATABASE_IMPORT = 3;

}//interface ContactManagementPlugin
