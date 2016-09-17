/***
 * jwma Java WebMail
 * Copyright (c) 2000-2003 jwma team
 *
 * jwma is free software; you can distribute and use this source
 * under the terms of the BSD-style license received along with
 * the distribution.
 ***/
package dtw.webmail.plugin;

import dtw.webmail.model.*;
import dtw.webmail.JwmaSession;

/**
 * Interface  for a plugin that handles persistency of
 * user preferences.<p>
 *
 * @author Dieter Wimberger
 * @version 0.9.7 07/02/2003
 */
public interface PreferencesPersistencePlugin
    extends JwmaPlugin {

  /**
   * Loads the given user's preferences from the
   * persistent store and returns them as a <tt>
   * JwmaPreferencesImpl</tt> instance.
   * <p>
   * If preferences for the given user do not exist on
   * the store, the method returns <tt>null</tt>.
   *
   * @param identity a <tt>String</tt> representing the user
   *        (in a unique way).
   *
   * @return the <tt>JwmaPreferencesImpl</tt> instance.
   *
   * @throws JwmaException if an I/O error occurs.
   *
   * @see dtw.webmail.model.JwmaPreferencesImpl
   * @see dtw.webmail.model.JwmaPreferencesImpl#getUserIdentity()
   */
  public JwmaPreferencesImpl loadPreferences(String identity)
      throws JwmaException;

  /**
   * Saves the given preferences to the
   * persistent store.
   * <p>
   * If the preferences do not exist on the store,
   * the method should create them, otherwise update
   * the existing version.<br>
   * The identity is retrieved using the <tt>getUserIdentity()</tt>
   * method of the given <tt>JwmaPreferencesImpl</tt> instance.
   *
   * @param prefs the <tt>JwmaPreferencesImpl</tt> instance to
   *        saved.
   *
   * @throws JwmaException if an I/O error occurs.
   *
   * @see dtw.webmail.model.JwmaPreferencesImpl
   * @see dtw.webmail.model.JwmaPreferencesImpl#getUserIdentity()
   */
  public void savePreferences(JwmaPreferencesImpl prefs)
      throws JwmaException;

  /**
   * Tests if preferences for the given user exist
   * on the store.
   *
   * @param identity a <tt>String</tt> representing the user
   *        (in a unique way).
   *
   * @throws JwmaException if an I/O error occurs.
   *
   * @see dtw.webmail.model.JwmaPreferencesImpl#getUserIdentity()
   */
  public boolean isPersistent(String identity)
      throws JwmaException;

  /**
   * Returns a copy of the template <tt>JwmaPreferencesImpl</tt>
   * instance.
   *
   * @param session the actual <tt>JwmaSession</tt> instance.
   * @return the template <tt>JwmaPreferencesImpl</tt> instance.
   *
   * @see dtw.webmail.model.JwmaPreferencesImpl
   */
  public JwmaPreferencesImpl getPreferencesTemplate(JwmaSession session);


  /**
   * Sets the template <tt>JwmaPreferencesImpl</tt>
   * instance.
   * <p>
   * The caller assumes that the plugin handles the persistence
   * of the passed in template instance.
   *
   * @param template <tt>JwmaPreferencesImpl</tt> instance.
   *
   * @throws JwmaException if an I/O error occurs.
   *
   * @see dtw.webmail.model.JwmaPreferencesImpl
   */
  public void setPreferencesTemplate(JwmaPreferencesImpl template)
      throws JwmaException;

}//interface PreferencesPersistencePlugin