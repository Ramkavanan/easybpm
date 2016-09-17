/***
 * jwma Java WebMail
 * Copyright (c) 2000-2003 jwma team
 *
 * jwma is free software; you can distribute and use this source
 * under the terms of the BSD-style license received along with
 * the distribution.
 ***/
package dtw.webmail.util;

import java.util.Properties;
import java.net.*;
import java.io.*;

/**
 * Utility class providing two simple
 * yet powerful methods for loading properties.
 *
 * @author Dieter Wimberger
 * @version 0.9.7 07/02/2003
 */
public class PropertiesLoader {

  /**
   * Returns a <tt>Properties</tt> instance loaded from the
   * URL given as <tt>String</tt>.
   *
   * @param url that refers to the properties file as <tt>String</tt>.
   *
   * @return loaded <tt>Properties</tt> instance.
   *
   * @throws MalformedURLException if the <tt>String</tt> does not
   *         represent a valid URL.
   * @throws IOException if the method fails to access the URL.
   */
  public static Properties loadProperties(String url)
      throws MalformedURLException, IOException {

    return loadProperties(new URL(url));
  }//loadProperties(String)

  /**
   * Returns a <tt>Properties</tt> instance loaded from the
   * given <tt>URL</tt>.
   *
   * @param url that refers to the properties file as <tt>java.net.URL</tt>.
   *
   * @return loaded <tt>Properties</tt> instance.
   *
   * @throws IOException if the method fails to access the URL.
   */
  public static Properties loadProperties(URL url)
      throws IOException {

    Properties newprops = new Properties();
    InputStream in = url.openStream();
    newprops.load(in);
    in.close();

    return newprops;
  }//loadProperties(URL)

}//class PropertiesLoader
