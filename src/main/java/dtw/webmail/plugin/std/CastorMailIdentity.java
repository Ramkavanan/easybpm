/***
 * jwma Java WebMail
 * Copyright (c) 2000-2003 jwma team
 *
 * jwma is free software; you can distribute and use this source
 * under the terms of the BSD-style license received along with
 * the distribution.
 ***/
package dtw.webmail.plugin.std;

import java.util.Locale;
import dtw.webmail.JwmaKernel;
import dtw.webmail.model.JwmaMailIdentityImpl;
import dtw.webmail.plugin.RandomAppendPlugin;
import dtw.webmail.util.AssociatedAbstractIdentifiable;
import org.apache.log4j.Logger;
import org.exolab.castor.jdo.TimeStampable;

/**
 * Class implementing a specialized <tt>JwmaMailIdentityImpl</tt>
 * for being persisted with the Castor Plugins.
 *
 * @author Dieter Wimberger
 * @version 0.9.7 07/02/2003
 */
public class CastorMailIdentity
    extends AssociatedAbstractIdentifiable
    implements JwmaMailIdentityImpl, TimeStampable {

  //logging
  private static Logger log =
      Logger.getLogger(CastorMailIdentity.class);

  //instance attributes
  private String m_Name = "";
  private String m_Signature = "";
  private boolean m_AutoSign = true;
  private String m_ReplyTo = "";
  private String m_From = "";
  private String m_RndAppendType = RandomAppendPlugin.TYPE_NONE;
  private String m_Note = "";
  private String m_RelatedContact;
  private long m_Timestamp = TimeStampable.NO_TIMESTAMP;


  /**
   * Constructs a <tt>CastorMailIdentity</tt> instance.
   */
  public CastorMailIdentity() {
    super();
  }//constructor

  public String getName() {
    return m_Name;
  }//getName

  public void setName(String name) {
    m_Name = name;
  }//setName

  public String getReplyTo() {
    return m_ReplyTo;
  }//getReplyTo

  public void setReplyTo(String addr) {
    m_ReplyTo = addr;
  }//setReplyTo

  public String getFrom() {
    return m_From;
  }//getFrom

  public void setFrom(String addr) {
    m_From = addr;
  }//setFrom

  public String getSignature() {
    return m_Signature;
  }//getSignature

  public void setSignature(String sig) {
    m_Signature = sig;
  }//setSignature

  public String getRelatedContact() {
    return m_RelatedContact;
  }//getRelatedContact

  public void setRelatedContact(String uid) {
    m_RelatedContact = uid;
  }//setRelatedContact

  public boolean isAutoSigning() {
    return m_AutoSign;
  }//isAutoSigning

  public void setAutoSigning(boolean b) {
    m_AutoSign = b;
  }//setAutoSigning

  public String getNote() {
    return m_Note;
  }//getNote

  public void setNote(String note) {
    m_Note = note;
  }//setNote

  public String getRandomAppendType() {
    return m_RndAppendType;
  }//getRandomAppendType

  public void setRandomAppendType(String type) {
    m_RndAppendType = type;
  }//setRandomAppendType

  public void setRandomAppendType(String type, Locale loc) {
    RandomAppendPlugin rap = JwmaKernel.getReference().getRandomAppendPlugin();
    if (type == null || type.length() == 0
        || rap == null || !rap.supportsAppendType(type,loc)) {
      m_RndAppendType = RandomAppendPlugin.TYPE_NONE;
    } else {
      m_RndAppendType = type;
    }
  }//setRandomAppendType


  public boolean isRandomAppendAllowed() {
    return (!m_RndAppendType.equals(RandomAppendPlugin.TYPE_NONE));
  }//isRandomAppendAllowed

  public long jdoGetTimeStamp() {
    return m_Timestamp;
  }//jdoGetTimeStamp

  public void jdoSetTimeStamp(long timeStamp) {
    m_Timestamp = timeStamp;
  }//jdoSetTimeStamp

}//CastorMailIdentity
