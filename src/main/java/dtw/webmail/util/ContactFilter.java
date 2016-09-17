/***
 * jwma Java WebMail
 * Copyright (c) 2000-2003 jwma team
 *
 * jwma is free software; you can distribute and use this source
 * under the terms of the BSD-style license received along with
 * the distribution.
 ***/
package dtw.webmail.util;

import dtw.webmail.model.JwmaContact;

/**
 * Interface defining a<tt>ContactFilter</tt>.
 *
 * @author Dieter Wimberger
 * @version 0.9.7 07/02/2003
 */
public interface ContactFilter {

  public boolean isFiltered(JwmaContact contact);

  public boolean isAllowed(JwmaContact contact);

}//interface ContactFilter
