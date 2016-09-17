/***
 * jwma Java WebMail
 * Copyright (c) 2000-2003 jwma team
 *
 * jwma is free software; you can distribute and use this source
 * under the terms of the BSD-style license received along with
 * the distribution.
 ***/
package dtw.webmail.admin;

import java.util.*;
import java.io.*;

import org.apache.log4j.Logger;

import dtw.webmail.model.*;


/**
 * Class that implements a file status wrapper.
 *
 * @author Dieter Wimberger
 * @version 0.9.7 07/02/2003
 */
public class FileStatus {

  //logging
  private static Logger log = Logger.getLogger(FileStatus.class);

  //instance attributes
  private BitSet m_Status;
  private String m_Filename;
  private boolean m_Ok;

  /**
   * Constructs a new file status instance.
   */
  public FileStatus(String filename) throws JwmaException {
    m_Status = new BitSet(4);
    m_Filename = filename;
    gatherStatus();
  }//constructor

  /**
   * Returns the filename of the file that is
   * described through this <tt>FileStatus</tt>.
   *
   * @return the filename of the file described by this
   *         <tt>FileStatus</tt>.
   */
  public String getFilename() {
    return m_Filename;
  }//getFilename

  /**
   * Tests if the file is writeable.
   *
   * @return true if writeable, false otherwise.
   */
  public boolean isWriteable() {
    return m_Status.get(WRITEABLE);
  }//isWriteable

  /**
   * Tests if the file is a directory.
   *
   * @return true if it is a directory, false otherwise.
   */
  public boolean isDirectory() {
    return m_Status.get(DIRECTORY);
  }//isDirectory

  /**
   * Tests if the file is readable.
   *
   * @return true if readable, false otherwise.
   */
  public boolean isReadable() {
    return m_Status.get(READABLE);
  }//isReadable

  /**
   * Tests if the file exists.
   *
   * @return true if it exists, false otherwise.
   */
  public boolean isExisting() {
    return m_Status.get(EXISTS);
  }//isExisting

  /**
   * Returns the IS state of the file described
   * by this <tt>FileStatus</tt>.
   *
   * @return the is state as <tt>String</tt>.
   */
  public String getIsState() {
    StringBuffer sbuf = new StringBuffer();
    if (isExisting()) {
      sbuf.append('[');
    }
    sbuf.append(((isDirectory())? "D":"!D"));
    sbuf.append(',');
    sbuf.append(((isReadable())? "R":"!R"));
    sbuf.append(',');
    sbuf.append(((isWriteable())? "W":"!W"));
    if (isExisting()) {
      sbuf.append(']');
    }
    return sbuf.toString();
  }//isState

  /**
   * Tests if the file described by this
   * <tt>FileStatus</tt> represents the given type.
   * <p>
   * The types are encoded through given constants.
   *
   * @return true if of the given type, false otherwise.
   */
  public boolean isType(int TYPE) {
    switch (TYPE) {
      case READABLE_FILE:
        return (
            !m_Status.get(DIRECTORY) &&
            m_Status.get(EXISTS) &&
            m_Status.get(READABLE) &&
            !m_Status.get(WRITEABLE)
            );
      case WRITEABLE_FILE:
        return (
            !m_Status.get(DIRECTORY) &&
            m_Status.get(EXISTS) &&
            m_Status.get(READABLE) &&
            m_Status.get(WRITEABLE)
            );
      case READABLE_DIRECTORY:
        return (
            m_Status.get(DIRECTORY) &&
            m_Status.get(EXISTS) &&
            m_Status.get(READABLE) &&
            !m_Status.get(WRITEABLE)
            );
      case WRITEABLE_DIRECTORY:
        return (
            m_Status.get(DIRECTORY) &&
            m_Status.get(EXISTS) &&
            m_Status.get(READABLE) &&
            m_Status.get(WRITEABLE)
            );
      default:
        return false;
    }
  }//isType

  /**
   * Gathers the status of the file that is
   * refered to by the filename of this
   * <tt>FileStatus</tt> instance.
   *
   * @throws JwmaException if file checking fails.
   */
  private void gatherStatus() throws JwmaException {

    File testf = new File(m_Filename);
    try {
      if (testf.isDirectory()) {
        m_Status.set(DIRECTORY);
      }
      if (testf.exists()) {
        m_Status.set(EXISTS);
      }
      if (testf.canRead()) {
        m_Status.set(READABLE);
      }
      if (testf.canWrite()) {
        m_Status.set(WRITEABLE);
      }
    } catch (Exception ex) {
      JwmaException jex =
          new JwmaException("jwma.admin.filestatus")
          .setException(ex);
    }
  }//gatherStatus


  //constants
  private static final int DIRECTORY = 0;
  private static final int EXISTS = 1;
  private static final int READABLE = 2;
  private static final int WRITEABLE = 3;

  /**
   * Represents an existing and readable file.
   * [Exists,!D,R,!W]
   */
  public static final int READABLE_FILE = 0;

  /**
   * Represents an existing and writeable file.
   * [Exists,!D,R,W]
   */
  public static final int WRITEABLE_FILE = 1;

  /**
   * Represents an existing and readable directory.
   * [Exists,D,R,!W]
   */
  public static final int READABLE_DIRECTORY = 2;

  /**
   * Represents an existing and writeable directory.
   * [Exists,D,R,W]
   */
  public static final int WRITEABLE_DIRECTORY = 3;


  public static final String[] SHOULD_STATE = {
    "[!D,R,!W]", "[!D,R,W]", "[D,R,!W]", "[D,R,W]"
  };


}//class FileStatus