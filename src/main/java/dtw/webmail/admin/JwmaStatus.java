/***
package dtw.webmail.admin;

import java.io.*;

import org.apache.log4j.Logger;

import dtw.webmail.util.*;
import dtw.webmail.model.*;
import dtw.webmail.JwmaKernel;

/**
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


  /**
  private JwmaStatus() throws JwmaException {
    c_Self = this;
    //m_Settings = JwmaKernel.getReference().getSettings();
    //prepareStatus();
  }//JwmaStatus

  /**
  public int getDirectoryCount() {
    if (m_DirStatus != null) {
      return m_DirStatus.length;
    }
    return 0;
  }//getDirectoryCount

  /**
  public boolean isDirectoryOk(int directory) {
    return m_DirStatus[directory].isType(
        DIRECTORY_TYPES[directory]
    );
  }//isDirectoryOk

  /**
  public String getDirResolveHint(int directory) {
    if (!isDirectoryOk(directory)) {
      return m_DirStatus[directory].getIsState() +
          " => " +
          FileStatus.SHOULD_STATE[DIRECTORY_TYPES[directory]];
    } else {
      return "";
    }
  }//getDirectoryReport

  /**
  public String getDirectoryPath(int directory) {
    return m_DirStatus[directory].getFilename();
  }//getDirectoryPath

  /**
  public int getFileCount() {
    if (m_FileStatus != null) {
      return m_FileStatus.length;
    }
    return 0;
  }//getFileCount

  /**
  public boolean isFileOk(int file) {
    //all errormessages are treated as 5
        FILE_TYPES[((file > 3)? 3:file)]
    );
  }//isFileOk

  /**
  public String getFileResolveHint(int file) {
    if (!isFileOk(file)) {
      return m_FileStatus[file].getIsState() +
          " => " +
          FileStatus.SHOULD_STATE[FILE_TYPES[((file > 3)? 3:file)]];
    } else {
      return "";
    }
  }//getFileReport

  /**
  public String getFilePath(int file) {
    return m_FileStatus[file].getFilename();
  }//getFilePath

  /**
  public boolean isKernelOk() {
    return (m_KernelStatus > 9);
  }//isKernelOk

  /**
  public int getKernelStatus() {
    return m_KernelStatus;
  }//getKernelStatus

  /**
  public String getPostOfficeHost() {
    return "";//JwmaKernel.getReference().getPostOfficeHost();
  }//getPostOfficeHost

  /**
  public String getMTAHost() {
    return System.getProperty("mail.smtp.host");
  }//getMTAHost

  /**
  public boolean supportsMixedFolders() {
    return false;
    //return JwmaKernel.getReference().isPostOfficeStoreMixed();
  }//supportsMixedFolders

  /**
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

  /**
  public String getSettingsDump() {
    //return JwmaKernel.getReference()
    //    .getSettings().getDump();
    return "";
  }//getSettingsDump


  /**
  private void prepareStatus() throws JwmaException {

    //Kernel status
    m_DirStatus = new FileStatus[paths.length];

    for (int i = 0; i < paths.length; i++) {
      m_DirStatus[i] = new FileStatus(paths[i]);
    }
    m_FileStatus = new FileStatus[paths.length];

    for (int i = 0; i < paths.length; i++) {
      m_FileStatus[i] = new FileStatus(paths[i]);
    }
  }//prepareStatus

  /**
  public static JwmaStatus createJwmaStatus()
      throws JwmaException {

    return new JwmaStatus();
  }//createJwmaStatus

  /**
  public static JwmaStatus getReference() {
    return c_Self;
  }//getReference

  /**
  public static final int[] DIRECTORY_TYPES = {
    FileStatus.WRITEABLE_DIRECTORY, //ROOT
  };

  /**
  public static final int[] FILE_TYPES = {
    FileStatus.WRITEABLE_FILE, //SETTINGS
  };

}//JwmaStatus