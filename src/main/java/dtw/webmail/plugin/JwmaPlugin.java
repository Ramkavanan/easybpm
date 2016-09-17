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
 * Generic Interface for any plugin used from jwma.<p>
 *
 * @author Dieter Wimberger
 * @version 0.9.7 07/02/2003
 */
public interface JwmaPlugin {

  /**
   * Activates the plugin for subsequent use.
   * <p>
   * This method is called during bootstrap to allow
   * an implementation to initialize properly.
   * If the plugin cannot perform it's task, it has to
   * throw a <tt>JwmaException</tt>.
   *
   * @throws JwmaException if initialization failed.
   */
  public void activate() throws JwmaException;

  /**
   * Deactivates the plugin.
   * <p>
   * This method is called during shutdown or probably
   * on dynamic implementation exchange, to allow an
   * implementation to finalize properly.
   */
  public void deactivate();

}//interface JwmaPlugin