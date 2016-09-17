/***
 * jwma Java WebMail
 * Copyright (c) 2000-2003 jwma team
 *
 * jwma is free software; you can distribute and use this source
 * under the terms of the BSD-style license received along with
 * the distribution.
 ***/
package dtw.webmail.directory;

import java.util.*;
import java.io.*;
import javax.naming.*;
import javax.naming.directory.*;

/**
 * Class that implements a pool for <tt>DirContext</tt>
 * instances.
 * Note that each context has a distinct connection, thus
 * allows to be used concurrently with the other contexts in the pool.
 * However, a context itself cannot be used concurrently.
 *
 * @author Dieter Wimberger
 * @version 0.9.7 07/02/2003
 *
 * @see javax.naming.directory.DirContext
 */
public class ContextPool {

  //Instance attributes
  private List m_Pool;
  private int m_Size;
  private String m_ContextURL;
  private boolean m_Serving = false;
  private Properties m_Environment;
  private String m_SearchName;
  private String[] m_ResultAttributes;
  private String m_BaseFilter;

  /**
   * Constructs a <tt>DirContextPool</tt> instance.
   *
   * @param size the size of the pool as <tt>int</tt>.
   * @param url the URL specifying the directory context as <tt>String</tt>.
   */
  public ContextPool(int size, String url) {
    m_Size = size;
    m_ContextURL = url;
    prepareEnvironment();
    initPool();
  }//ContextPool

  /**
   * Constructs a <tt>DirContextPool</tt> instance.
   * This instance enables authenticated connections to the directory,
   * using the given username and password.
   *
   * @param size the size of the pool as <tt>int</tt>.
   * @param url the URL specifying the directory context as <tt>String</tt>.
   * @param username the username to be used for authentication as <tt>String</tt>.
   * @param password the password to be used for authentication as <tt>String</tt>.
   */
  public ContextPool(int size, String url,
                     String username, String password) {
    m_Size = size;
    m_ContextURL = url;
    prepareEnvironment();
    //add credentials to use
    m_Environment.put(Context.SECURITY_PRINCIPAL, username);
    m_Environment.put(Context.SECURITY_CREDENTIALS, password);
    initPool();
  }//ContextPool

  /**
   * Returns a leased <tt>DirContext</tt> instance from this
   * pool.
   *
   * @return the leased <tt>DirContext</tt> from this pool.
   */
  public synchronized DirContext leaseContext() {
    if (!m_Serving) {
      return null;
    }
    boolean served = false;
    DirContext ctx = null;
    while (!served) {
      if (m_Pool.size() == 0) {
        try {
          wait();
        } catch (Exception ex) {
          //do nothing but wait again :)
        }
      } else {
        ctx = (DirContext) m_Pool.get(0);
        m_Pool.remove(0);
        served = true;
      }
    }
    return ctx;
  }//leaseContext

  /**
   * Releases a formerly leased <tt>DirContext</tt>, returning
   * it into this pool.
   *
   * @param ctx the formerly leased <tt>DirContext</tt>
   *        to be released.
   */
  public synchronized void releaseContext(DirContext ctx) {
    m_Pool.add(ctx);
    notifyAll();
  }//releaseContext

  /**
   * Returns the ceiling (in terms of size) of this pool.
   *
   * @return the ceiling size of this pool as <tt>int</tt>.
   */
  public int getCeiling() {
    return m_Size;
  }//getCeiling

  /**
   * Returns the size of this pool.
   *
   * @return the actual size of this pool as <tt>int</tt>.
   */
  public int size() {
    return m_Pool.size();
  }//size

  /**
   * Resizes the pool.
   *
   * @param size the new size as <tt>int</tt>.
   */
  public void resize(int size) {
    //FIXME: not very efficient
    synchronized (m_Pool) {
      clear();
      m_Size = size;
      initPool();
    }
  }//resize

  /**
   * Removes all <tt>DirContext</tt> references from
   * this pool.
   * Note that it will do so gracefully, waiting for
   * leased <tt>ProcessingResources</tt>.
   */
  public void clear() {
    m_Serving = false;
    //wait until pool complete
    while (m_Pool.size() != m_Size) {
      try {
        wait();
      } catch (Exception ex) {
        //do nothing but wait again :)
      }
    }
    //close all contexts
    for (Iterator iter = m_Pool.iterator(); iter.hasNext();) {
      try {
        ((DirContext) iter.next()).close();
      } catch (NamingException nex) {
      }
    }
    //remove all elements
    m_Pool.clear();
  }//clear

  public String getSearchName() {
    return m_SearchName;
  }//getSearchName

  public void setSearchName(String sname) {
    m_SearchName = sname;
  }//setSearchName

  public String getBaseFilter() {
    return m_BaseFilter;
  }//getBaseFilter

  public void setBaseFilter(String filter) {
    m_BaseFilter = filter;
  }//setBaseFilter

  public String[] getResultAttributes() {
    return m_ResultAttributes;
  }//getResultAttributes

  public void setResultAttributes(String[] atts) {
    m_ResultAttributes = atts;
  }//setResultAttributes

  /**
   * Initializes a pool of <tt>DirContext</tt> instances.
   */
  protected void initPool() {
    m_Pool = new ArrayList(m_Size);
    for (int i = 0; i < m_Size; i++) {
      try {
        m_Pool.add(new InitialDirContext(m_Environment));
      } catch (NamingException ex) {
        //handle
      }
    }
    m_Serving = true;
  }//initPool

  /**
   * Prepares the environment that is used for the build
   * context instances.
   */
  private void prepareEnvironment() {
    m_Environment = new Properties();
    //prepare the environment
    m_Environment.put(Context.INITIAL_CONTEXT_FACTORY,
        "com.sun.jndi.ldap.LdapCtxFactory");
    m_Environment.put(Context.PROVIDER_URL, m_ContextURL);
  }//prepareEnvironment

}//ContextPool