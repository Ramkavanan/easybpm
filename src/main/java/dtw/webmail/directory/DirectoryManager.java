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

import dtw.webmail.util.*;

public class DirectoryManager {

  //class attributes
  private static DirectoryManager c_Self;				//singleton reference

  //instance attributes
  private Properties m_Properties;
  private HashMap m_LookupContextPools;
  private HashMap m_ValidationContextPools;
  private List m_Directories;
  private DirectoryUtil m_DirUtil;

  private DirectoryManager(Properties properties) {
    m_Properties = properties;
    m_LookupContextPools = new HashMap(10);
    m_ValidationContextPools = new HashMap(10);
    m_Directories = new ArrayList(10);
    m_DirUtil = new DirectoryUtil(this);
    c_Self = this;
  }//constructor

  private void prepare() {
    //1. get the directory names
    String directories = m_Properties.getProperty("directories");

    //m_Properties.list(System.out);
    //System.out.println(directories);

    String[] dirlist = new String[0];
    if (directories == null || directories.length() < 0) {
      //handle
      return;
    } else {
      dirlist = StringUtil.split(directories, ",");
    }
    //get classes for them all
    for (int i = 0; i < dirlist.length; i++) {
      //add dir
      m_Directories.add(dirlist[i]);
      //System.out.println(dirlist[i]);
      //load url
      String url = m_Properties.getProperty(dirlist[i] + ".url");
      //load poolsize
      int poolsize = Integer.parseInt(
          m_Properties.getProperty(dirlist[i] + ".poolsize")
      );
      //load username and passwd if exists
      String user = m_Properties.getProperty(dirlist[i] + ".user");
      String passwd = m_Properties.getProperty(dirlist[i] + ".password");
      //create pool
      ContextPool newpool = null;
      if (user == null || passwd == null
          || user.length() == 0 || passwd.length() == 0) {
        newpool = new ContextPool(poolsize, url);
      } else {
        newpool = new ContextPool(poolsize, url, user, passwd);
      }

      //load searchname & base filter
      newpool.setSearchName(m_Properties.getProperty(dirlist[i] + ".searchname"));
      newpool.setBaseFilter(m_Properties.getProperty(dirlist[i] + ".basefilter"));

      //load type
      String type = m_Properties.getProperty(dirlist[i] + ".type");

      if (type.equals("validation")) {
        String domain = m_Properties.getProperty(dirlist[i] + ".domain");
        //System.out.println(domain);
        m_ValidationContextPools.put(domain, newpool);
      } else if (type.equals("lookup")) {
        newpool.setResultAttributes(
            StringUtil.split(
                m_Properties.getProperty(dirlist[i] + ".resultattributes"), ","
            )
        );
        String name = m_Properties.getProperty(dirlist[i] + ".name");
        m_LookupContextPools.put(name, newpool);
      }
    }//for loop end

  }//prepare

  public DirectoryUtil getDirectoryUtil() {
    return m_DirUtil;
  }//getDirectoryUtil

  public ContextPool getLookupContextPool(String key) {
    return (ContextPool) m_LookupContextPools.get(key);
  }//getLookupPool

  public ContextPool getValidationContextPool(String key) {
    return (ContextPool) m_ValidationContextPools.get(key);
  }//getValidationLookupPool

  /**
   * Returns the reference to the singleton instance.
   */
  public static DirectoryManager getReference() {
    return c_Self;
  }//getReference

  /**
   * Factory method for singleton with parameters
   */
  public static DirectoryManager createDirectoryManager(Properties props) {
    DirectoryManager dirmgr = new DirectoryManager(props);
    dirmgr.prepare();
    return dirmgr;
  }//createDirectoryManager


  public static void main(String[] args) {
    try {
      FileInputStream in = new FileInputStream(args[0]);
      Properties props = new Properties();
      props.load(in);
      DirectoryManager dmgr =
          DirectoryManager.createDirectoryManager(props);
      DirectoryUtil dirutil = dmgr.getDirectoryUtil();
      List l = dirutil.lookupEntry("Users", args[1]);

    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }//main

}//DirectoryManager