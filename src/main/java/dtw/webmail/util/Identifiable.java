/***
 * jwma Java WebMail
 * Copyright (c) 2000-2003 jwma team
 *
 * jwma is free software; you can distribute and use this source
 * under the terms of the BSD-style license received along with
 * the distribution.
 ***/
package dtw.webmail.util;

/**
 * Interface modeling an <tt>Associator</tt>.
 *
 * @author Dieter Wimberger
 * @version 0.9.7 07/02/2003
 */
public interface Identifiable {

  /**
   * Returns the unique identifier of
   * this <tt>Identifiable</tt>.
   *
   * @return the unique identifier as <tt>String</tt>.
   */
  public String getUID();

  /**
   * Tests if this <tt>Identifiable</tt> is
   * equal to a given <tt>Object</tt> instance.
   * Equality is defined by equality of the
   * unique identifier.
   *
   * @param o the <tt>Object</tt> to compare to.
   * @return true if equal, false otherwise.
   */
  public boolean equals(Object o);

}//interface Identifiable