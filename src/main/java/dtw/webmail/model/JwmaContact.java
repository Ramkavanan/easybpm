/***
 * jwma Java WebMail
 * Copyright (c) 2000-2003 jwma team
 *
 * jwma is free software; you can distribute and use this source
 * under the terms of the BSD-style license received along with
 * the distribution.
 ***/
package dtw.webmail.model;

import java.util.Date;

import dtw.webmail.JwmaSession;


/**
 * An interface defining the contract for interaction with
 * the JwmaContact model.
 * <p>
 * The JwmaContact allows a view programmer to obtain
 * information about an contact to display it for reading
 * or editing.
 *
 * @author Dieter Wimberger
 * @version 0.9.7 07/02/2003
 *
 */
public interface JwmaContact {


  /**
   * Returns the unique identifier of this <tt>JwmaContact</tt>.
   *
   * @return the unique identifier as <tt>String</tt>.
   */
  public String getUID();

  /**
   * Returns a <tt>String</tt> representing the category
   * of this <tt>JwmaContact</tt>.
   *
   * @return the category as <tt>String</tt>.
   */
  public String getCategory();

  /**
   * Returns a <tt>String</tt> representing the nickname
   * of this contact object.
   * <p>
   * Note that the nickname is used as unique identifier of
   * a contact object within the contacts database.
   *
   * @return the nickname of this contact as String.
   *
   * @see dtw.webmail.model.JwmaContacts
   */
  public String getNickname();

  /**
   * Returns a <tt>String</tt> representing the firstname
   * of this contact object.
   *
   * @return the firstname of this contact as String.
   */
  public String getFirstname();

  /**
   * Returns a <tt>String</tt> representing the lastname
   * of this contact object.
   *
   * @return the lastname of this contact as String.
   */
  public String getLastname();

  /**
   * Returns a <tt>String</tt> representing the middlename
   * of this contact object.
   *
   * @return the middlename of this contact as String.
   */
  public String getMiddlename();

  /**
   * Returns a <tt>String</tt> representing the company name
   * of this contact object.
   *
   * @return the company name of this contact as String.
   */
  public String getCompany();

  /**
   * Returns a <tt>String</tt> representing the title
   * of this contact object.
   *
   * @return the title of this contact as String.
   */
  public String getTitle();

  /**
   * Returns a <tt>String</tt> representing the role
   * of this contact object.
   *
   * @return the role of this contact as String.
   */
  public String getRole();


  /**
   * Returns a <tt>String</tt> representing the home
   * phone number of this contact.
   *
   * @return the home phone number of this contact as String.
   */
  public String getHomePhoneNumber();

  /**
   * Returns a <tt>String</tt> representing the work
   * phone number of this contact.
   *
   * @return the work phone number of this contact as String.
   */
  public String getWorkPhoneNumber();

  /**
   * Returns a <tt>String</tt> representing the pager
   * number of this contact.
   *
   * @return the pager number of this contact as String.
   */
  public String getPagerNumber();

  /**
   * Returns a <tt>String</tt> representing the fax
   * number of this contact.
   *
   * @return the fax number of this contact as String.
   */
  public String getFaxNumber();

  /**
   * Returns a <tt>String</tt> representing the mobile
   * phone number of this contact.
   *
   * @return the mobile phone number of this contact as String.
   */
  public String getMobileNumber();

  /**
   * Tests if this <tt>JwmaContact</tt> is primary a work
   * contact.
   *
   * @return true if primarily work contact, false otherwise.
   */
  public boolean isPrimarilyWorkContact();


  /**
   * Returns a <tt>String</tt> representing the
   * street name of this contact at work.
   *
   * @return the street of this contact at work as String.
   */
  public String getWorkStreet();


  /**
   * Returns a <tt>String</tt> representing the
   * city of this contact at work.
   *
   * @return the city of this contact at work as String.
   */
  public String getWorkCity();

  /**
   * Returns a <tt>String</tt> representing the
   * region of this contact at work.
   *
   * @return the region of this contact at work as String.
   */
  public String getWorkRegion();

  /**
   * Returns a <tt>String</tt> representing the
   * country of this contact at work.
   *
   * @return the country of this contact at work as String.
   */
  public String getWorkCountry();


  /**
   * Returns a <tt>String</tt> representing the
   * ZIP of this contact at work.
   *
   * @return the ZIP of this contact at work as String.
   */
  public String getWorkZIP();

  /**
   * Returns a <tt>String</tt> representing the
   * street name of this contact at home.
   *
   * @return the street of this contact at home as String.
   */
  public String getHomeStreet();


  /**
   * Returns a <tt>String</tt> representing the
   * city of this contact at home.
   *
   * @return the city of this contact at home as String.
   */
  public String getHomeCity();

  /**
   * Returns a <tt>String</tt> representing the
   * region of this contact at home.
   *
   * @return the region of this contact at home as String.
   */
  public String getHomeRegion();

  /**
   * Returns a <tt>String</tt> representing the
   * country of this contact at home.
   *
   * @return the country of this contact at home as String.
   */
  public String getHomeCountry();

  /**
   * Returns a <tt>String</tt> representing the
   * ZIP of this contact at home.
   *
   * @return the ZIP of this contact at home as String.
   */
  public String getHomeZIP();

  /**
   * Returns a <tt>String</tt> representing the email
   * of this contact object.
   *
   * @return the email of this contact as String.
   */
  public String getEmail();

  /**
   * Returns a <tt>String</tt> representing the altnerate
   * email of this contact object.
   *
   * @return the alternate email of this contact as String.
   */
  public String getAlternateEmail();


  /**
   * Returns a <tt>String</tt> representing an URL
   * associated with this contact object.
   *
   * @return an URL associated with this contact as String.
   */
  public String getURL();

  /**
   * Returns a <tt>String</tt> representing the company
   * URL associated with this contact object.
   *
   * @return the company URL associated with this contact as String.
   */
  public String getCompanyURL();

  /**
   * Returns a <tt>String</tt> representing the comments
   * of this contact object.
   *
   * @return the comments of this contact as String.
   */
  public String getComments();

  /**
   * Returns the birthdate of this contact as <tt>Date</tt>.
   *
   * @return the birthdate as <tt>Date</tt>.
   */
  public Date getBirthDate();

  /**
   * Tests if this contact represents a frequent
   * recipient.
   *
   * @return true if this contact represents a frequent
   *         recipient, false otherwise.
   */
  public boolean isFrequentRecipient();

}//interface JwmaContact
