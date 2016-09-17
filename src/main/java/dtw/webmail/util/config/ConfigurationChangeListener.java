/***
 * jwma Java WebMail
 * Copyright (c) 2000-2003 jwma team
 *
 * jwma is free software; you can distribute and use this source
 * under the terms of the BSD-style license received along with
 * the distribution.
 ***/
package dtw.webmail.util.config;

/** 
 * This interface...
 * <p>
 * @author Dieter Wimberger (wimpi)
 * @version (created Feb 27, 2003)
 */
public interface ConfigurationChangeListener {

  /**
   * Handles a change to the configuration.
   */
  public void changedConfiguration();

}//interface ConfigurationChangeListener