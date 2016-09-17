/**** jwma Java WebMail* Copyright (c) 2000-2003 jwma team** jwma is free software; you can distribute and use this source* under the terms of the BSD-style license received along with* the distribution. ***/
package dtw.webmail.admin;

import java.io.*;

import org.apache.log4j.Logger;

import dtw.webmail.util.*;
import dtw.webmail.model.*;
import dtw.webmail.JwmaKernel;

/** * Class that implements a wrapper for and a proxy to available * status information. * <p> * * @author Dieter Wimberger * @version 0.9.7 07/02/2003 */
public class JwmaStatus {

  //class attributes
  private static JwmaStatus c_Self;
  //logging
  private static Logger log = Logger.getLogger(JwmaStatus.class);


  //instance attributes and associations
  //private JwmaSettings m_Settings;
  private int m_KernelStatus;
  private String m_KernelReport;
  private FileStatus[] m_DirStatus;
  private FileStatus[] m_FileStatus;


  /**   * Constructs a new <tt>JwmaStatus</tt> instance.   */
  private JwmaStatus() throws JwmaException {
    c_Self = this;
    //m_Settings = JwmaKernel.getReference().getSettings();
    //prepareStatus();
  }//JwmaStatus

  /**   * Returns the number of observed directories as   * <tt>int</tt>.   *   * @return the number of observed directories as <tt>int</tt>.   */
  public int getDirectoryCount() {
    if (m_DirStatus != null) {
      return m_DirStatus.length;
    }
    return 0;
  }//getDirectoryCount

  /**   * Tests if the given directory is in the prescribed state.   * <p>   * The states are encoded within the   * <tt>FileStatus</tt> class as constants, and prescribed   * for the observed directories in the <tt>DIRECTORY_TYPES</tt>   * constant.   *   * @param directory, the index of the directory as <tt>int</tt>.   *   * @return true if the given directory is in prescribed state,   *         false otherwise.   *   * @see #DIRECTORY_TYPES   * @see dtw.webmail.admin.FileStatus#READABLE_DIRECTORY   * @see dtw.webmail.admin.FileStatus#WRITEABLE_DIRECTORY   */
  public boolean isDirectoryOk(int directory) {
    return m_DirStatus[directory].isType(
        DIRECTORY_TYPES[directory]
    );
  }//isDirectoryOk

  /**   * Returns the resolve hint, if the directory is not   * in the prescribed state.   * <p>   * The method returns an empty string if the directory is   * in the prescribed state.   *   * @param directory, the index of the directory as <tt>int</tt>.   *   * @return the resolve hint as <tt>String</tt>.   */
  public String getDirResolveHint(int directory) {
    if (!isDirectoryOk(directory)) {
      return m_DirStatus[directory].getIsState() +
          " => " +
          FileStatus.SHOULD_STATE[DIRECTORY_TYPES[directory]];
    } else {
      return "";
    }
  }//getDirectoryReport

  /**   * Returns the full path of the directory as <tt>String</tt>.   *   * @param directory, the index of the directory as <tt>int</tt>.   *   * @return the full path as <tt>String</tt>.   */
  public String getDirectoryPath(int directory) {
    return m_DirStatus[directory].getFilename();
  }//getDirectoryPath

  /**   * Returns the number of observed files as   * <tt>int</tt>.   *   * @return the number of observed files as <tt>int</tt>.   */
  public int getFileCount() {
    if (m_FileStatus != null) {
      return m_FileStatus.length;
    }
    return 0;
  }//getFileCount

  /**   * Tests if the given file is in the prescribed state.   * <p>   * The states are encoded within the   * <tt>FileStatus</tt> class as constants, and prescribed   * for the observed directories in the <tt>FILE_TYPES</tt>   * constant.   *   * @param file, the index of the file as <tt>int</tt>.   *   * @return true if the given file is in prescribed state,   *         false otherwise.   *   * @see #FILE_TYPES   * @see dtw.webmail.admin.FileStatus#READABLE_FILE   * @see dtw.webmail.admin.FileStatus#WRITEABLE_FILE   */
  public boolean isFileOk(int file) {
    //all errormessages are treated as 5    return m_FileStatus[file].isType(
        FILE_TYPES[((file > 3)? 3:file)]
    );
  }//isFileOk

  /**   * Returns the resolve hint, if the file is not   * in the prescribed state.   * <p>   * The method returns an empty string if the directory is   * in the prescribed state.   *   * @param file, the index of the file as <tt>int</tt>.   *   * @return the resolve hint as <tt>String</tt>.   */
  public String getFileResolveHint(int file) {
    if (!isFileOk(file)) {
      return m_FileStatus[file].getIsState() +
          " => " +
          FileStatus.SHOULD_STATE[FILE_TYPES[((file > 3)? 3:file)]];
    } else {
      return "";
    }
  }//getFileReport

  /**   * Returns the full path of the file as <tt>String</tt>.   *   * @param file, the index of the file as <tt>int</tt>.   *   * @return the full path as <tt>String</tt>.   */
  public String getFilePath(int file) {
    return m_FileStatus[file].getFilename();
  }//getFilePath

  /**   * Tests if the kernel is up an running completely.   * If this test returns false, the <tt>getKernelStatus()</tt>   * method can be used to obtain status information.   *   * @return true if the kernel is up and running error free,   *         false otherwise.   *   * @see #getKernelStatus()   */
  public boolean isKernelOk() {
    return (m_KernelStatus > 9);
  }//isKernelOk

  /**   * Returns the status of the kernel as <tt>int</tt>.   * The information content of this number can be obtained from   * the documentation (Deployment-> Boot Process) or   * <a href="http://jwma.sourceforge.net/deployment/kernel.html" target="_top">   * online</a>.   *   * @return an <tt>int</tt> describing the state of the kernel.   */
  public int getKernelStatus() {
    return m_KernelStatus;
  }//getKernelStatus

  /**   * Returns the site's post office hostaddress   * setting.   *   * @return local site's post office hostaddress   *         as <tt>String</tt>.   */
  public String getPostOfficeHost() {
    return "";//JwmaKernel.getReference().getPostOfficeHost();
  }//getPostOfficeHost

  /**   * Returns the site's MTA hostaddress   * setting.   *   * @return local site's post MTA hostaddress   *         as <tt>String</tt>.   */
  public String getMTAHost() {
    return System.getProperty("mail.smtp.host");
  }//getMTAHost

  /**   * Tests if the preset post office supports maildir style   * mixture of messages and subfolders.   *   * @return true if mixed mode store, false otherwise.   */
  public boolean supportsMixedFolders() {
    return false;
    //return JwmaKernel.getReference().isPostOfficeStoreMixed();
  }//supportsMixedFolders

  /**   * Returns an <tt>String</tt> representing the maximum   * size allowed for a message to be transported via jwma.   *   * @return maximum size of a message to be transported as   *         <tt>String</tt>.   */
  public String getMailTransportLimit() {
    //int size = JwmaKernel.getReference()
    //    .getSettings().getMailTransportLimit();
    /*
    int steps = 0;
    while (size > 1024) {
      size = size / 1024;
      steps++;
    }
    if (steps == 0) {
      return size + " kB";
    } else if (steps == 1) {
      return size + " MB";
    } else {
      return "Too large.";
    }
    */
    return "";
  }//getTransportSizeLimit

  /**   * Returns a dump of the loaded settings file.   *   * @return the dump of the loaded settings file as <tt>String</tt>.   */
  public String getSettingsDump() {
    //return JwmaKernel.getReference()
    //    .getSettings().getDump();
    return "";
  }//getSettingsDump


  /**   * Prepares the status by gathering information.   */
  private void prepareStatus() throws JwmaException {

    //Kernel status    m_KernelStatus = JwmaKernel.getReference().getKernelStatus();    //Directories status    String[] paths = JwmaKernel.getReference().listDirectories();
    m_DirStatus = new FileStatus[paths.length];

    for (int i = 0; i < paths.length; i++) {
      m_DirStatus[i] = new FileStatus(paths[i]);
    }    //Files status    paths = JwmaKernel.getReference().listFiles();
    m_FileStatus = new FileStatus[paths.length];

    for (int i = 0; i < paths.length; i++) {
      m_FileStatus[i] = new FileStatus(paths[i]);
    }
  }//prepareStatus

  /**   * Creates the <tt>JwmaStatus</tt> singleton   * instance.   *   * @return the newly created singleton instance.   *   * @throws JwmaException if the status can not be prepared.   */
  public static JwmaStatus createJwmaStatus()
      throws JwmaException {

    return new JwmaStatus();
  }//createJwmaStatus

  /**   * Returns the reference to the singleton instance.   */
  public static JwmaStatus getReference() {
    return c_Self;
  }//getReference

  /**   * Prescription of the SHOULD STATE of directories.   */
  public static final int[] DIRECTORY_TYPES = {
    FileStatus.WRITEABLE_DIRECTORY, //ROOT    FileStatus.WRITEABLE_DIRECTORY, //ETC    FileStatus.WRITEABLE_DIRECTORY, //LOG    FileStatus.WRITEABLE_DIRECTORY			//DATA
  };

  /**   * Prescription of the SHOULD STATE of files.   */
  public static final int[] FILE_TYPES = {
    FileStatus.WRITEABLE_FILE, //SETTINGS    FileStatus.WRITEABLE_FILE, //SYSLOG    FileStatus.WRITEABLE_FILE, //DEBUGLOG    //FileStatus.READABLE_FILE,				//PREF_MAPPING    //FileStatus.WRITEABLE_FILE,				//PREF_TEMPLATE    FileStatus.READABLE_FILE				//ERRORMESSAGES FILES
  };

}//JwmaStatus