/***
 * jwma Java WebMail
 * Copyright (c) 2000-2003 jwma team
 *
 * jwma is free software; you can distribute and use this source
 * under the terms of the BSD-style license received along with
 * the distribution.
 ***/
package dtw.webmail.util;

import java.util.List;

/**
 * Interface modeling an <tt>Associator</tt>.
 *
 * @author Dieter Wimberger
 * @version 0.9.7 07/02/2003
 */
public interface Associator {

  /**
   * Returns the associated instances.
   *
   * @return the associated instances as <tt>List</tt>.
   */
  public List getRemovedAssociations();

}//interface Identifiable