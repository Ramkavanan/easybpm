/***
 * jwma Java WebMail
 * Copyright (c) 2000-2003 jwma team
 *
 * jwma is free software; you can distribute and use this source
 * under the terms of the BSD-style license received along with
 * the distribution.
 ***/
package dtw.webmail.plugin;

import java.util.*;

import dtw.webmail.model.JwmaException;

/**
 * Interface for a plugin that provides randomly
 * selected quotes or nonsense, for being appended
 * to a text message.<p>
 *
 * @author Dieter Wimberger
 * @version 0.9.7 07/02/2003
 */
public interface RandomAppendPlugin
    extends JwmaPlugin {

  /**
   * Returns a randomly selected quote or nonsense
   * for being appended to a message in a given
   * <tt>Locale</tt>.
   *
   * @param type a <tt>String</tt> representing a type
   * 		  what will be appended.
   * @param locale the locale to be used for the append.
   *
   *
   * @return a random <tt>String</tt> to be appended to
   *         the message.
   */
  public String getRandomAppend(String type, Locale locale);

  /**
   * Returns a list of random append type identifiers this
   * plugin can provide for a given <tt>Locale</tt>.
   *
   *
   * @param locale the <tt>Locale</tt> to be used.
   *
   * @return a <tt>String[]</tt> holding the type
   *         identifiers.
   */
  public String[] listAppendTypes(Locale locale);

  /**
   * Tests whether this <tt>RandomAppendPlugin</tt>
   * supports a given append type for a given
   * <tt>Locale</tt>.
   * <p>
   * <em>Note:</em><br>
   * If the method returns false, the setting will
   * automatically be <tt>TYPE_NONE</tt>, which
   * will tell the application logic, that nothing
   * is to be appended.
   *
   *
   * @param type a <tt>String</tt> representing an
   *        append type.
   * @param locale the <tt>Locale</tt> to be used.
   *
   * @return true if the type is supported, false otherwise.
   */
  public boolean supportsAppendType(String type, Locale loc);

  /**
   * Defines a type that will flag that a user does
   * not want anything to be appended to his email.
   */
  public static final String TYPE_NONE = "randomappend.none";

}//interface RandomAppendPlugin
