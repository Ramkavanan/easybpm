/***
 * jwma Java WebMail
 * Copyright (c) 2000-2003 jwma team
 *
 * jwma is free software; you can distribute and use this source
 * under the terms of the BSD-style license received along with
 * the distribution.
 ***/
package dtw.webmail.model;

import java.io.Serializable;
import java.util.Date;

import dtw.webmail.JwmaSession;


/**
 * Interface for <tt>JwmaContact</tt> implementations.
 * This is the interface any specialized implementation
 * has to expose internal to controllers and models.
 *
 * @author Dieter Wimberger
 * @version 0.9.7 07/02/2003
 */
public interface JwmaContactImpl extends JwmaContact,Serializable {

  /**
   * Set the the category of this <tt>JwmaContactImpl</tt>.
   *
   * @param category of this contact as <tt>String</tt>.
   */
  public void setCategory(String category);

  /**
   * Sets the nickname of this <tt>JwmaContactImpl</tt>.
   *
   * @param nickname of this contact as <tt>String</tt>.
   */
  public void setNickname(String nickname);

  /**
   * Sets the firstname of this <tt>JwmaContactImpl</tt>.
   *
   * @param firstname of this contact as <tt>String</tt>.
   */
  public void setFirstname(String firstname);

  /**
   * Sets the lastname of this <tt>JwmaContactImpl</tt>.
   *
   * @param lastname of this contact as <tt>String</tt>.
   */
  public void setLastname(String lastname);

  /**
   * Sets the middlename of this <tt>JwmaContactImpl</tt>.
   *
   * @param middlename of this contact as <tt>String</tt>.
   */
  public void setMiddlename(String middlename);

  /**
   * Sets the company name of this <tt>JwmaContactImpl</tt>.
   *
   * @param company of this contact as <tt>String</tt>.
   */
  public void setCompany(String company);

  /**
   * Sets the title of this <tt>JwmaContactImpl</tt>.
   *
   * @param title of this contact as <tt>String</tt>.
   */
  public void setTitle(String title);

  /**
   * Sets the organizational role of this <tt>JwmaContactImpl</tt>.
   *
   * @return role the organizational role of this contact as <tt>String</tt>.
   */
  public void setRole(String role);


  /**
   * Sets the home phone number of this <tt>JwmaContactImpl</tt>.
   *
   * @param number the home phone number of this contact as
   *        <tt>String</tt>.
   *
   * @throws <tt>JwmaException</tt> if the number is an invalid format.
   */
  public void setHomePhoneNumber(String number)
      throws JwmaException;

  /**
   * Sets the work phone number of this <tt>JwmaContactImpl</tt>.
   *
   * @param number the work phone number of this contact as <tt>String</tt>.
   *
   * @throws <tt>JwmaException</tt> if the number is an invalid format.
   */
  public void setWorkPhoneNumber(String number)
      throws JwmaException;

  /**
   * Sets the pager number of this <tt>JwmaContactImpl</tt>.
   *
   * @param number the pager number of this contact as <tt>String</tt>.
   *
   * @throws <tt>JwmaException</tt> if the number is an invalid format.
   */
  public void setPagerNumber(String number)
      throws JwmaException;

  /**
   * Sets the fax number of this <tt>JwmaContactImpl</tt>.
   *
   * @param number the fax number of this contact as String.
   *
   * @throws <tt>JwmaException</tt> if the number is an invalid format.
   */
  public void setFaxNumber(String number)
      throws JwmaException;

  /**
   * Sets the mobile phone number of this <tt>JwmaContactImpl</tt>.
   *
   * @param number the mobile phone number of this contact as <tt>String</tt>.
   *
   * @throws <tt>JwmaException</tt> if the number is an invalid format.
   */
  public void setMobileNumber(String number)
      throws JwmaException;

  /**
   * Sets if this <tt>JwmaContactImpl</tt> is primarily a work
   * contact.
   *
   * @param b true if primarily work contact, false otherwise.
   */
  public void setPrimarilyWorkContact(boolean b);


  /**
   * Sets the street name of this <tt>JwmaContactImpl</tt> at work.
   *
   * @param street the street of this contact at work as String.
   */
  public void setWorkStreet(String street);


  /**
   * Sets the city of this <tt>JwmaContactImpl</tt> at work.
   *
   * @param city the city of this contact at work as String.
   */
  public void setWorkCity(String city);

  /**
   * Sets the region of this <tt>JwmaContactImpl</tt> at work.
   *
   * @param region the region of this contact at work as String.
   */
  public void setWorkRegion(String region);

  /**
   * Sets the country of this <tt>JwmaContactImpl</tt> at work.
   *
   * @param country the country of this contact at work as String.
   */
  public void setWorkCountry(String country);


  /**
   * Sets the ZIP of this <tt>JwmaContactImpl</tt> at work.
   *
   * @param zip the ZIP of this contact at work as String.
   *
   * @throws <tt>JwmaException</tt> if the zip is an invalid format.
   */
  public void setWorkZIP(String zip)
      throws JwmaException;

  /**
   * Sets the street name of this <tt>JwmaContactImpl</tt> at home.
   *
   * @param street the street of this contact at home as String.
   */
  public void setHomeStreet(String street);


  /**
   * Sets the city of this <tt>JwmaContactImpl</tt> at home.
   *
   * @param city the city of this contact at home as String.
   */
  public void setHomeCity(String city);

  /**
   * Sets the region of this <tt>JwmaContactImpl</tt> at home.
   *
   * @param region the region of this contact at home as String.
   */
  public void setHomeRegion(String region);

  /**
   * Sets the country of this <tt>JwmaContactImpl</tt> at home.
   *
   * @param country the country of this contact at home as String.
   */
  public void setHomeCountry(String country);


  /**
   * Sets the ZIP of this <tt>JwmaContactImpl</tt> at home.
   *
   * @param zip the ZIP of this contact at home as String.
   *
   * @throws <tt>JwmaException</tt> if the zip is an invalid format.
   */
  public void setHomeZIP(String zip)
      throws JwmaException;

  /**
   * Sets the email of this <tt>JwmaContactImpl</tt>.
   *
   * @param email the email of this contact as String.
   *
   * @throws <tt>JwmaException</tt> if the email is an invalid format.
   */
  public void setEmail(String email)
      throws JwmaException;

  /**
   * Sets the alternate email of this <tt>JwmaContactImpl</tt>.
   *
   * @param email the alternate email of this contact as String.
   *
   * @throws <tt>JwmaException</tt> if the email is an invalid format.
   */
  public void setAlternateEmail(String email)
      throws JwmaException;


  /**
   * Sets the URL associated with this <tt>JwmaContactImpl</tt>.
   *
   * @param url the URL associated with this contact as String.
   *
   * @throws <tt>JwmaException</tt> if the url is an invalid format.
   */
  public void setURL(String url);

  /**
   * Sets the company URL associated with this <tt>JwmaContactImpl</tt>.
   *
   * @param url the company URL associated with this contact as String.
   *
   * @throws <tt>JwmaException</tt> if the url is an invalid format.
   */
  public void setCompanyURL(String url);

  /**
   * Sets the comments for this <tt>JwmaContactImpl</tt>.
   *
   * @param comments the comments of this contact as String.
   */
  public void setComments(String comments);

  /**
   * Sets the birthdate of this <tt>JwmaContactImpl</tt>.
   *
   * @param date the birthdate as <tt>Date</tt>.
   */
  public void setBirthDate(Date date);

  /**
   * Sets if this <tt>JwmaContactImpl</tt> represents a frequent
   * mail recipient.
   *
   * @param b true if this contact represents a frequent
   *         recipient, false otherwise.
   */
  public void setFrequentRecipient(boolean b);

}//interface JwmaContactImpl
