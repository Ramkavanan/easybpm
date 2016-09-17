/***
 * jwma Java WebMail
 * Copyright (c) 2000-2003 jwma team
 *
 * jwma is free software; you can distribute and use this source
 * under the terms of the BSD-style license received along with
 * the distribution.
 ***/
package dtw.webmail.model;

import dtw.webmail.util.config.JwmaConfiguration;

import javax.servlet.http.HttpSession;

import java.io.Serializable;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * An interface defining the contract for interaction with
 * the JwmaHtmlHelper model.
 * <p>
 * The JwmaHtmlHelper helps a view programmer constructing
 * certain reuseable HTML elements on one hand, and to obtain
 * information important for views on the other hand (i.e.
 * the controller URL).
 *
 * @author Dieter Wimberger
 * @version 0.9.7 07/02/2003
 *
 */
public interface JwmaHtmlHelper extends Serializable{

  /**
   * Returns a <tt>String</tt> representing the URL
   * of the main controller.
   * <p>
   * The URL is installation specific, this helper method
   * allows to make views more "portable".
   *
   * @return the URL of the main controller as String.
   */
  public String getControllerUrl();

  /**
   * Returns a <tt>String</tt> representing the URL
   * of the SendMailController.
   * <p>
   * The URL is installation specific, this helper method
   * allows to make views more "portable".
   *
   * @return the URL of the SendMailController as String.
   */
  public String getSendMailControllerUrl();

  /**
   * Returns a <tt>String</tt> representing the URL
   * of the ContactsController.
   * <p>
   * The URL is installation specific, this helper method
   * allows to make views more "portable".
   *
   * @return the URL of the ContactsController as String.
   */
  public String getContactsControllerUrl();

  /**
   * Returns a selection of configured post offices.
   * <p>
   * This routine constructs a select with all given
   * post office names for the view programmer,
   * and is supposed to be used for selecting a
   * post office on login.
   *
   * @return a post offices select (HTML) as <tt>String</tt>.
   *
   * @see dtw.webmail.util.config.JwmaConfiguration
   * @see dtw.webmail.util.config.PostOffice
   */
  public String getPostOfficeSelect(String postoffice);


  /**
   * Returns a <tt>String</tt> representing a
   * path navigator in HTML.
   * <p>
   * This routine allows to navigate a more complex
   * folder tree very fast and efficient. It handles
   * the path using the actual folder separator char.
   *
   * @param storeinfo a reference to the actual store info instance.
   * @param path the path to be translated into a navigator.
   *
   * @return a path navigator (HTML) as String.
   */
  public String getPathHierarchyNavigator(JwmaStoreInfo storeinfo, JwmaFolder folder);

  /**
   * Returns a <tt>String</tt> representing the
   * installation specific reference URL for displaying
   * the given folder.
   * <p>
   * The String will not include the anchor tag, but
   * represent the href attribute value.<br>
   * This method is supposed to help making views more
   * portable and simple to write.
   *
   * @param folder the <tt>JwmaFolder</tt> to be used for the
   *        display action.
   * @return the URL for displaying the folder as <tt>String</tt>.
   *
   * @see dtw.webmail.model.JwmaFolder
   */
  public String getFolderDisplayAction(JwmaFolder folder);

  /**
   * Convenience method returning the URL for displaying
   * the inbox folder.
   *
   * @param folder the <tt>JwmaFolder</tt> to be used for the
   *        display action.
   * @return the URL for displaying the folder as <tt>String</tt>.
   *
   * @see #getFolderDisplayAction(JwmaFolder)
   * @see dtw.webmail.model.JwmaInboxInfo
   */
  public String getFolderDisplayAction(JwmaInboxInfo info);

  /**
   * Convenience method returning the URL for displaying
   * the trash folder.
   *
   * @param folder the <tt>JwmaFolder</tt> to be used for the
   *        display action.
   * @return the URL for displaying the folder as <tt>String</tt>.
   *
   * @see #getFolderDisplayAction(JwmaFolder)
   * @see dtw.webmail.model.JwmaInboxInfo
   */
  public String getFolderDisplayAction(JwmaTrashInfo info);

  /**
   * Returns a <tt>JwmaFolder[]</tt>containing a
   * destination select in HTML.
   * <p>
   * This routine constructs a select with all given
   * paths for the view programmer, and is supposed
   * to be used for selecting a destination for certain
   * folder, mailbox or message move operations.<br>
   * Lists of paths of move targets can be obtained from
   * JwmaStoreInfo.
   *
   * @param folders a <tt>JwmaFolder[]</tt> to be used as
   * 		  options for the select.
   * @return a destinations select (HTML) as String.
   *
   * @see dtw.webmail.model.JwmaFolder
   * @see dtw.webmail.model.JwmaStoreInfo#listFolderMoveTargets()
   * @see dtw.webmail.model.JwmaStoreInfo#listMessageMoveTargets()
   */
  public String getDestinationsSelect(JwmaFolder[] folders);

  /**
   * Returns a <tt>String</tt> representing a
   * frequent recipient select in HTML.
   * <p>
   * This routine constructs a select with all given
   * frequent recipient nicks for the view programmer,
   * and is supposed to be used for selecting a
   * frequent recipient very quick.<br>
   *
   * @param contacts the JwmaContacts instance.
   * @param eventhandler added to the select.
   *
   * @return a frequent recipient select (HTML) as String.
   *
   * @see dtw.webmail.model.JwmaContacts
   */
  public String getFrequentSelect(JwmaContacts contacts, String eventhandler);

  /**
   * Returns a <tt>String</tt> representing a
   * category select in HTML.
   * <p>
   * This routine constructs a select with all given
   * categories for the view programmer,
   * and is supposed to be used for selecting a
   * (user defined) category for a contact.<br>
   * If the user did not define any category, the
   * select will only have the new category option.
   *
   * @param contact the <tt>JwmaContact</tt> instance.
   * @param categories the categories to be listed as options.
   * @param viewcontent the <tt>ResourceBundle</tt> containing the
   *        localized view content strings.
   *
   * @return a categories select (HTML) as String.
   *
   * @see dtw.webmail.model.JwmaContacts
   */
  public String getCategoriesSelect(JwmaContact ct,
                                    String[] categories,
                                    ResourceBundle viewcontent);


  /**
   * Returns a <tt>String</tt> representing a
   * proper language select in HTML.
   * <p>
   * This routine constructs a select with all available
   * languages.<br>
   *
   * @param prefs the users preferences as <tt>JwmaPreferences</tt>.
   * @return a language select (HTML) as String.
   */
  public String getLanguageSelect(JwmaPreferences prefs);

  /**
   * Returns a <tt>String</tt> representing a
   * proper sorting criteria select in HTML.
   * <p>
   * This routine constructs a select with all available
   * message sorting criterias.<br>
   *
   * @param prefs the users preferences as <tt>JwmaPreferences</tt>.
   * @param eventhandler added to the select.
   * @param viewcontent the <tt>ResourceBundle</tt> containing the
   *        localized view content strings.
   *
   * @return a sorting criteria select (HTML) as String.
   */
  public String getSortCriteriaSelect(JwmaPreferences prefs,
                                      String eventhandler);

  /**
   * Returns a <tt>String</tt> representing a
   * MailIdentity select in HTML.
   * <p>
   * This routine constructs a select with the users available
   * mail identities, the default selected.<br>
   *
   * @param prefs the users preferences as <tt>JwmaPreferences</tt>.
   * @return a mail identity select (HTML) as String.
   */
  public String getMailIdentitySelect(JwmaPreferences prefs);

  /**
   * Returns a <tt>String</tt> representing a
   * MessageProcessor select in HTML.
   * <p>
   * This routine constructs a select with the available
   * message processors, with the user's one selected.<br>
   *
   * @param prefs the users preferences as <tt>JwmaPreferences</tt>.
   * @return a message processor select (HTML) as String.
   */
  public String getMessageProcessorSelect(JwmaPreferences prefs);

  /**
   * Returns a <tt>String</tt> representing a
   * DateFormat select in HTML.
   * <p>
   * This routine constructs a select with date format
   * patterns, with the user's one selected.<br>
   *
   * @param prefs the users preferences as <tt>JwmaPreferences</tt>.
   * @return a date format select (HTML) as String.
   */
  public String getDateFormatSelect(JwmaPreferences prefs);

  /**
   * Returns a <tt>String</tt> representing a
   * random append types select in HTML.
   * <p>
   * This routine constructs a select with the available
   * random append types, with the user's one selected.<br>
   *
   * @param prefs the users preferences as <tt>JwmaPreferences</tt>.
   * @param mid a <tt>JwmaMailIdentity</tt> instance.
   *
   * @return a random append type select (HTML) as String.
   */
  public String getRandomAppendTypesSelect(JwmaPreferences prefs,
                                           JwmaMailIdentity mid);

  /**
   * Returns a <tt>String</tt> representing a
   * group members select in HTML.
   * <p>
   * This routine constructs a select with all
   * contacts which are in the given contact group.
   *
   * @param group the JwmaContactGroup instance.
   *
   * @return a group members select (HTML) as String.
   *
   * @see dtw.webmail.model.JwmaContactGroup
   */
  public String getGroupMembersSelect(JwmaContactGroup group);

  /**
   * Returns a <tt>String</tt> representing a
   * non group members select in HTML.
   * <p>
   * This routine constructs a select with all
   * contacts which are not in the given contact group.
   *
   * @param group the JwmaContactGroup instance.
   * @param ctdb the contact database.
   *
   * @return a non members contact select (HTML) as String.
   *
   * @see dtw.webmail.model.JwmaContactGroup
   * @see dtw.webmail.model.JwmaContacts
   */
  public String getNonMembersSelect(JwmaContactGroup group, JwmaContacts ctdb);

  /**
   * Returns a <tt>String</tt> representing a
   * message part description in HTML.
   * <p>
   * This routine constructs a description with all given
   * information about the part for the view programmer,
   * and is supposed to simplify writing a view.
   *
   * @param part the part to be described in HTML.
   * @param viewcontent the <tt>ResourceBundle</tt> containing the
   *        localized view content strings.
   *
   * @return a message part description (HTML) as String.
   *
   * @see dtw.webmail.model.JwmaMessagePart
   */
  public String getPartDescription(JwmaMessagePart part, String from);

  /**
   * Returns a <tt>String</tt> representing the size
   * in human readable form.
   *
   * @param size the size (in bytes) to be described in
   *  	  human readable form.
   * @return the human readable size as String.
   */
  public String getSizeString(int size);

  /**
   * Returns a <tt>String</tt> representing a
   * HTML tag for embedding the message parts into
   * the view.
   * <p>
   * This routine constructs an inline view of all
   * given message parts in HTML. Unknown content types
   * will be output in form of a description, others
   * inlined by adding a proper HTML tag.
   *
   * @param session the session to store possible contact import.
   * @param parts the parts to be inlined as inlining HTML.
   * @param prefs the user's preferences.
   * @param viewcontent the <tt>ResourceBundle</tt> containing the
   *        localized view content strings.
   *
   * @return a HTML document part inlining the parts as String.
   *
   * @see #getPartDescription(JwmaMessagePart part, ResourceBundle viewcontent)
   * @see dtw.webmail.model.JwmaMessagePart
   */
  public String displayPartInlined(HttpSession session,
                                   JwmaMessagePart part,
                                   JwmaPreferences prefs, String from);



  /**
   * Returns a <tt>String</tt> representing a HTML
   * list of all first characters of the contact's lastnames.
   * The selected character will be used for the filter,
   * as well as displayed non-selectable; an option to remove
   * the filter is appended (ALL).
   *
   * @param contacts the actual session's contact database as
   *        <tt>JwmaContacts</tt>.
   * @param viewcontent the <tt>ResourceBundle</tt> containing the
   *        localized view content strings.
   *
   * @return the filter list as <tt>String</tt> (HTML).
   */
  public String getAlphabeticFilter(JwmaContacts contacts);

  /**
   * Returns a <tt>String</tt> representing a
   * category filter select in HTML.
   * <p>
   * This routine constructs a select with all used
   * categories.<br>
   *
   * @param contacts the actual session's contact database as
   *        <tt>JwmaContacts</tt>.
   * @param eventhandler added to the select.
   * @param viewcontent the <tt>ResourceBundle</tt> containing the
   *        localized view content strings.
   *
   * @return a sorting criteria select (HTML) as String.
   */
  public String getCategoryFilterSelect(JwmaContacts ctdb,
                                        String eventhandler);


}//interface JwmaHtmlHelper
