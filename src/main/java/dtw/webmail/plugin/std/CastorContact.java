/***
 * jwma Java WebMail
 * Copyright (c) 2000-2003 jwma team
 *
 * jwma is free software; you can distribute and use this source
 * under the terms of the BSD-style license received along with
 * the distribution.
 ***/
package dtw.webmail.plugin.std;

import dtw.webmail.model.JwmaContactImpl;
import dtw.webmail.util.AssociatedAbstractIdentifiable;
import org.apache.log4j.Logger;
import org.exolab.castor.jdo.TimeStampable;

import java.util.Date;

/**
 * Class implementing a specialized <tt>JwmaContactImpl</tt>
 * for being persisted with the Castor Plugins.
 *
 * @author Dieter Wimberger
 * @version 0.9.7 07/02/2003
 */
public class CastorContact
    extends AssociatedAbstractIdentifiable
    implements JwmaContactImpl, TimeStampable {

  private static Logger log =
      Logger.getLogger(CastorContact.class);

  //instance attributes
  private String m_Category = EMPTY;
  private String m_Nickname = EMPTY;
  private String m_Firstname = EMPTY;
  private String m_Lastname = EMPTY;
  private String m_Middlename = EMPTY;
  private String m_Company = EMPTY;
  private String m_Title = EMPTY;
  private String m_Role = EMPTY;
  private String m_HomePhoneNumber = EMPTY;
  private String m_WorkPhoneNumber = EMPTY;
  private String m_PagerNumber = EMPTY;
  private String m_FaxNumber = EMPTY;
  private String m_MobileNumber = EMPTY;
  private boolean m_PrimarilyWorkContact = false;
  private String m_WorkStreet = EMPTY;
  private String m_WorkCity = EMPTY;
  private String m_WorkRegion = EMPTY;
  private String m_WorkCountry = EMPTY;
  private String m_WorkZIP = EMPTY;
  private String m_HomeStreet = EMPTY;
  private String m_HomeCity = EMPTY;
  private String m_HomeRegion = EMPTY;
  private String m_HomeCountry = EMPTY;
  private String m_HomeZIP = EMPTY;
  private String m_Email = EMPTY;
  private String m_AlternateEmail = EMPTY;
  private String m_URL = EMPTY;
  private String m_CompanyURL = EMPTY;
  private String m_Comments = EMPTY;
  private Date m_BirthDate = new Date();
  private boolean m_FrequentRecipient = false;
  private long m_Timestamp = TimeStampable.NO_TIMESTAMP;


  public CastorContact() {
  }//constructor

  public String getCategory() {
    return m_Category;
  }//getCategory

  public void setCategory(String category) {
    m_Category = (category == null)? EMPTY:category;
  }//setCategory

  public String getNickname() {
    return m_Nickname;
  }//getNickname

  public void setNickname(String nickname) {
    m_Nickname = (nickname == null)?EMPTY:nickname;
  }//setNickname

  public String getFirstname() {
    return m_Firstname;
  }//getFirstname

  public void setFirstname(String name) {
    m_Firstname = (name == null)?EMPTY:name;
  }//getFirstname

  public String getLastname() {
    return m_Lastname;
  }//getLastname

  public void setLastname(String name) {
    m_Lastname = (name == null)?EMPTY:name;
  }//getLastname


  public String getMiddlename() {
    return m_Middlename;
  }//getMiddlename

  public void setMiddlename(String name) {
    m_Middlename = (name == null)?EMPTY:name;
  }//getMiddlename

  public String getCompany() {
    return m_Company;
  }//getCompany

  public void setCompany(String name) {
    m_Company = (name == null)?EMPTY:name;
  }//setCompany

  public String getTitle() {
    return m_Title;
  }//getTitle

  public void setTitle(String title) {
    m_Title = (title == null)?EMPTY:title;
  }//setTitle

  public String getRole() {
    return m_Role;
  }//getRole

  public void setRole(String role) {
    m_Role = (role == null)?EMPTY:role;
  }//setRole

  public String getHomePhoneNumber() {
    return m_HomePhoneNumber;
  }//getHomePhoneNumber

  public void setHomePhoneNumber(String number) {
    m_HomePhoneNumber = (number == null)?EMPTY:number;
  }//setHomePhoneNumber

  public String getWorkPhoneNumber() {
    return m_WorkPhoneNumber;
  }//getWorkPhoneNumber

  public void setWorkPhoneNumber(String number) {
    m_WorkPhoneNumber = (number == null)?EMPTY:number;
  }//setWorkPhoneNumber

  public String getPagerNumber() {
    return m_PagerNumber;
  }//getPagerNumber

  public void setPagerNumber(String number) {
    m_PagerNumber = (number == null)?EMPTY:number;
  }//setPagerNumber

  public String getFaxNumber() {
    return m_FaxNumber;
  }//getFaxNumber

  public void setFaxNumber(String number) {
    m_FaxNumber = (number == null)?EMPTY:number;
  }//setFaxNumber

  public String getMobileNumber() {
    return m_MobileNumber;
  }//getMobileNumber

  public void setMobileNumber(String number) {
    m_MobileNumber = (number == null)?EMPTY:number;
  }//setMobileNumber


  public boolean isPrimarilyWorkContact() {
    return m_PrimarilyWorkContact;
  }//isPrimarilyWorkContact

  public void setPrimarilyWorkContact(boolean b) {
    m_PrimarilyWorkContact = b;
  }//setPrimarilyWorkContact

  public String getWorkStreet() {
    return m_WorkStreet;
  }//getWorkStreet

  public void setWorkStreet(String street) {
    m_WorkStreet = (street == null)?EMPTY:street;
  }//setWorkStreet

  public String getWorkCity() {
    return m_WorkCity;
  }//getWorkCity

  public void setWorkCity(String city) {
    m_WorkCity = (city == null)?EMPTY:city;
  }//setWorkCity

  public String getWorkRegion() {
    return m_WorkRegion;
  }//getWorkRegion

  public void setWorkRegion(String region) {
    m_WorkRegion = (region == null)?EMPTY:region;
  }//setWorkRegion

  public String getWorkCountry() {
    return m_WorkCountry;
  }//getWorkCountry

  public void setWorkCountry(String country) {
    m_WorkCountry = (country == null)?EMPTY:country;
  }//setWorkCountry

  public String getWorkZIP() {
    return m_WorkZIP;
  }//getWorkZIP

  public void setWorkZIP(String zip) {
    m_WorkZIP = (zip == null)?EMPTY:zip;
  }//setWorkZIP


  public String getHomeStreet() {
    return m_HomeStreet;
  }//getHomeStreet

  public void setHomeStreet(String street) {
    m_HomeStreet = (street == null)?EMPTY:street;
  }//setHomeStreet

  public String getHomeCity() {
    return m_HomeCity;
  }//getHomeCity

  public void setHomeCity(String city) {
    m_HomeCity = (city == null)?EMPTY:city;
  }//setHomeCity

  public String getHomeRegion() {
    return m_HomeRegion;
  }//getHomeRegion

  public void setHomeRegion(String region) {
    m_HomeRegion = (region == null)?EMPTY:region;
  }//setHomeRegion

  public String getHomeCountry() {
    return m_HomeCountry;
  }//getHomeCountry

  public void setHomeCountry(String country) {
    m_HomeCountry = (country == null)?EMPTY:country;
  }//setHomeCountry

  public String getHomeZIP() {
    return m_HomeZIP;
  }//getHomeZIP

  public void setHomeZIP(String zip) {
    m_HomeZIP = (zip == null)?EMPTY:zip;
  }//setHomeZIP

  public String getEmail() {
    return m_Email;
  }//getEmail

  public void setEmail(String email) {
    m_Email = (email == null)?EMPTY:email;
  }//setEmail

  public String getAlternateEmail() {
    return m_AlternateEmail;
  }//getAlternateEmail

  public void setAlternateEmail(String email) {
    m_AlternateEmail = (email == null)?EMPTY:email;
  }//setAlternateEmail

  public String getURL() {
    return m_URL;
  }//getURL

  public void setURL(String url) {
    m_URL = (url == null)?EMPTY:url;
  }//setURL

  public String getCompanyURL() {
    return m_CompanyURL;
  }//getCompanyUrl

  public void setCompanyURL(String url) {
    m_CompanyURL = (url == null)?EMPTY:url;
  }//setCompanyUrl

  public String getComments() {
    return m_Comments;
  }//getComments

  public void setComments(String comments) {
    m_Comments = (comments == null)?EMPTY:comments;
  }//setComments

  public Date getBirthDate() {
    return m_BirthDate;
  }//getBirthDate

  public void setBirthDate(Date date) {
    if (date != null) {
      m_BirthDate = date;
    } else {
      m_BirthDate = new Date();
    }
  }//setBirthDate

  public boolean isFrequentRecipient() {
    return m_FrequentRecipient;
  }//isFrequentRecipient

  public void setFrequentRecipient(boolean b) {
    m_FrequentRecipient = b;
  }//setFrequentRecipient

  public long jdoGetTimeStamp() {
    return m_Timestamp;
  }//jdoGetTimeStamp

  public void jdoSetTimeStamp(long timeStamp) {
    m_Timestamp = timeStamp;
  }//jdoSetTimeStamp

  private final static String EMPTY = "";

}//CastorContact
