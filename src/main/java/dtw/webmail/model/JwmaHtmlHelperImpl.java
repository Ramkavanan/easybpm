/***
 * jwma Java WebMail
 * Copyright (c) 2000-2003 jwma team
 *
 * jwma is free software; you can distribute and use this source
 * under the terms of the BSD-style license received along with
 * the distribution.
 ***/
package dtw.webmail.model;

import java.util.*;
import java.net.*;
import java.text.*;
import java.io.ByteArrayInputStream;

import org.apache.log4j.Logger;


import dtw.webmail.*;
import dtw.webmail.util.MessageSortCriterias;
import dtw.webmail.util.StringUtil;
import dtw.webmail.util.MD5;
import dtw.webmail.util.config.ConfigurationChangeListener;
import dtw.webmail.util.config.PostOffice;
import dtw.webmail.plugin.RandomAppendPlugin;
import dtw.webmail.plugin.ContactManagementPlugin;

import javax.servlet.http.HttpSession;

import net.wimpi.text.Processor;


/**
 * Class implementing the JwmaHtmlHelper.
 * <p>Actually represents an utility class that helps
 * to keep complex logic out of the views.
 *
 * @author Dieter Wimberger
 * @version 0.9.7 07/02/2003
 */
public class JwmaHtmlHelperImpl
    implements JwmaHtmlHelper {

  //logging
  private static Logger log = Logger.getLogger(JwmaHtmlHelperImpl.class);
  private static HashMap c_DateFormats;

  //prepare cached dateformat instances
  static {
    Locale[] locales = JwmaKernel.getReference().getConfiguration()
                            .getI18N().listViewLocales();
    c_DateFormats = new HashMap((int) (locales.length * 1.4));
    for (int i = 0; i < locales.length; i++) {
      SimpleDateFormat dfs = (SimpleDateFormat)
          DateFormat.getDateInstance(DateFormat.SHORT, locales[i]);
      SimpleDateFormat dfm = (SimpleDateFormat)
          DateFormat.getDateInstance(DateFormat.MEDIUM, locales[i]);
      SimpleDateFormat dfl = (SimpleDateFormat)
          DateFormat.getDateInstance(DateFormat.LONG, locales[i]);
      SimpleDateFormat[] formats = {dfs, dfm, dfl};
      c_DateFormats.put(locales[i], formats);
    }
  }

  public JwmaHtmlHelperImpl() {
  }//constructor

  public String getControllerUrl() {
    return JwmaKernel.getReference().getMainControllerUrl();
  }//getControllerUrl

  public String getSendMailControllerUrl() {
    return JwmaKernel.getReference().getSendMailControllerUrl();
  }//getSendMailControllerUrl

  public String getContactsControllerUrl() {
    return JwmaKernel.getReference().getContactsControllerUrl();
  }//getContactsControllerUrl

  public String getPostOfficeSelect(String postoffice) {
    boolean def=true;
    if(postoffice != null && postoffice.length() >0) {
      def=false;
    }
    StringBuffer buf = new StringBuffer("<select name=\"postoffice\">");
    for (Iterator iter = JwmaKernel.getReference().getConfiguration().getPostOffices();
         iter.hasNext();) {

      PostOffice po = (PostOffice) iter.next();
      buf.append("  <option value=\"")
          .append(po.getName())
          .append("\"");
      if(def) {
        if (po.isDefault()) {
          buf.append(" selected");
        }
      } else {
        if(MD5.hash(po.getName()).equals(postoffice)) {
          buf.append(" selected");
        }
      }
      buf.append(">");
      buf.append(po.getName());
      buf.append("</option>");
    }
    buf.append("</select>");
    return buf.toString();
  }//getPostOfficeSelect

  public String getPathHierarchyNavigator(JwmaStoreInfo info, JwmaFolder folder) {
    String clickurl =
        "<a href=\"" +
        getControllerUrl() +
        "?acton=folder&amp;todo=display&amp;path={0}\">{1}</a>";

    String fname = "";
    String sep = info.getFolderSeparator() + "";
    //JwmaKernel.getReference().debugLog().write("Path="+path+";ClickUrl="+clickurl);
    StringBuffer fpath = new StringBuffer();
    StringBuffer hierarchy = new StringBuffer();
    StringTokenizer strtok = new StringTokenizer(folder.getPath(), sep, false);
    int i = 0;

    while (strtok.hasMoreElements()) {
      fname = strtok.nextToken();
      if (i > 0) {
        fpath.append(sep);
        hierarchy.append(sep);
      }
      fpath.append(URLEncoder.encode(fname));
      if (strtok.countTokens() == 0) {
        //redirect to folder
        hierarchy.append("<a href=\"")
            .append(getControllerUrl())
            .append("?acton=session&amp;todo=redirect&amp;view=folder\">")
            .append(fname)
            .append("</a>");
      } else {
        Object[] args = {fpath, fname};
        hierarchy.append(MessageFormat.format(clickurl, args));
      }
      i++;
    }
    return hierarchy.toString();
  }//getPathNavigator

  public String getFolderDisplayAction(JwmaFolder folder) {
    if (folder == null) {
      return "nullfolder";
    } else {
      return getControllerUrl() +
          "?acton=folder&amp;todo=display&amp;path=" +
          URLEncoder.encode(folder.getPath());
    }
  }//getFolderDisplayAction

  public String getFolderDisplayAction(JwmaInboxInfo info) {
    return getFolderDisplayAction((JwmaFolder) info);
  }//getFolderDisplayAction

  public String getFolderDisplayAction(JwmaTrashInfo info) {
    return getFolderDisplayAction((JwmaFolder) info);
  }//getFolderDisplayAction

  public String getDestinationsSelect(JwmaFolder[] folders) {
    StringBuffer buf = new StringBuffer("<select name=\"destination\">");
    for (int i = 0; i < folders.length; i++) {
      buf.append("  <option value=\"" + folders[i].getPath() + "\">" +
          folders[i].getPath() + "</option>");
    }
    buf.append("</select>");
    return buf.toString();
  }//getDestinationsSelection

  public String getLanguageSelect(JwmaPreferences prefs) {
    StringBuffer buf = new StringBuffer("<select name=\"language\">");
    Locale[] locales = JwmaKernel.getReference().getConfiguration()
                            .getI18N().listViewLocales();

    for (int i = 0; i < locales.length; i++) {
      buf.append("  <option value=\"")
          .append(locales[i].getLanguage())
          .append("\"");
      if (locales[i].equals(prefs.getLocale())) {
        buf.append(" selected");
      }
      buf.append(">");
      buf.append(locales[i].getDisplayName(locales[i]));
      buf.append("</option>");
    }
    buf.append("</select>");
    return buf.toString();
  }//getLanguageSelect

  public String getSortCriteriaSelect(JwmaPreferences prefs,
                                      String eventhandler) {
    ResourceBundle viewcontent = JwmaKernel.getReference().getViewContentMessage();
    StringBuffer buf = new StringBuffer("<select name=\"criteria\" ");

    if (eventhandler != null) {
      buf.append(eventhandler);
    }
    buf.append(">\n");

    int[] criterianums = MessageSortCriterias.EXUI_CRITERIAS;
    int last = prefs.getMessageSortCriteria();
    String[] names = MessageSortCriterias.EXUI_CRITERIAS_STR;

    for (int i = 0; i < criterianums.length; i++) {
      buf.append("  <option value=\"")
          .append(criterianums[i])
          .append("\"");
      if (criterianums[i] == last) {
        buf.append(" selected");
      }
      buf.append(">");
      buf.append(viewcontent.getString(names[i]));
      buf.append("</option>");
    }
    buf.append("</select>");
    return buf.toString();
  }//getSortingCriteriaSelect


  public String getFrequentSelect(JwmaContacts contacts, String eventhandler) {
    //String[] fnicks = book.listNicknames(true);
    JwmaContact[] frequent = contacts.listFrequentRecipients();
    if (frequent == null || frequent.length == 0) {
      return "";
    }
    StringBuffer buf = new StringBuffer("<select name=\"frequent\" ");

    if (eventhandler != null) {
      buf.append(eventhandler);
    }
    buf.append(">\n");
    buf.append("<option value=\"\" selected>Select frequent</option>\n");
    for (int i = 0; i < frequent.length; i++) {
      JwmaContact ct = frequent[i];
      String nickname = ct.getNickname();
      if (nickname == null || nickname.length() == 0) {
        nickname = new StringBuffer(ct.getLastname()).append(", ")
            .append(ct.getFirstname()).toString();
      }
      buf.append("  <option value=\"")
          .append(ct.getFirstname())
          .append(" ")
          .append(ct.getLastname())
          .append("<")
          .append(ct.getEmail())
          .append(">")
          .append("\">")
          .append(nickname)
          .append("</option>\n");
    }
    buf.append("</select>\n");
    return buf.toString();
  }//getFrequentSelect

  public String getCategoriesSelect(JwmaContact ct,
                                    String[] categories,
                                    ResourceBundle viewcontent) {


    StringBuffer buf = new StringBuffer("<select name=\"category\">\n");
    if (categories != null && categories.length > 0) {
      for (int i = 0; i < categories.length; i++) {
        buf.append("  <option value=\"")
            .append(categories[i])
            .append("\"")
            .append(((categories[i].equals(ct.getCategory()))? " selected>":">"))
            .append(categories[i])
            .append("</option>\n");
      }
    }
    String newcat = viewcontent.getString("contact.newcategory");
    buf.append("  <option value=\"")
        .append(newcat)
        .append("\">")
        .append(newcat)
        .append("</option>\n");

    buf.append("</select>\n");
    return buf.toString();
  }//getCategoriesSelect

  public String getMessageProcessorSelect(JwmaPreferences prefs) {
    String[] procs = JwmaKernel.getReference().listMessageProcessors();
    StringBuffer buf = new StringBuffer("<select name=\"msgprocessor\">\n");
    //add options
    for (int i = 0; i < procs.length; i++) {
      Processor proc = JwmaKernel.getReference().getMessageProcessor(procs[i]);
      buf.append("  <option value=\"")
          .append(procs[i])
          .append("\"")
          .append(((procs[i].equals(prefs.getMessageProcessorName()))? " selected>":">"))
          .append(proc.getDescription(prefs.getLocale()))
          .append("</option>\n");
    }
    buf.append("</select>\n");
    return buf.toString();
  }//getMessageProcessorSelect

  public String getRandomAppendTypesSelect(JwmaPreferences prefs, JwmaMailIdentity mid) {
    RandomAppendPlugin rap = JwmaKernel.getReference().getRandomAppendPlugin();
    String[] rndapp = null;

    if (rap == null) {
      rndapp = new String[1];
      rndapp[0] = RandomAppendPlugin.TYPE_NONE;
    } else {
      rndapp = rap.listAppendTypes(prefs.getLocale());
    }

    StringBuffer buf = new StringBuffer("<select name=\"rndappendtype\">\n");
    //add options
    for (int i = 0; i < rndapp.length; i++) {
      buf.append("  <option value=\"")
          .append(rndapp[i])
          .append("\"")
          .append(((rndapp[i].equals(mid.getRandomAppendType()))? " selected>":">"))
          .append(rndapp[i])
          .append("</option>\n");
    }
    buf.append("</select>\n");

    return buf.toString();
  }//getRandomAppendSelect

  public String getDateFormatSelect(JwmaPreferences prefs) {
    Date d = new Date();
    SimpleDateFormat[] formats =
        (SimpleDateFormat[]) c_DateFormats.get(prefs.getLocale());
    String actualpattern =
        ((SimpleDateFormat) prefs.getDateFormat()).toPattern();
    StringBuffer buf = new StringBuffer("<select name=\"dfpattern\">\n");
    //add options
    for (int i = 0; i < 3; i++) {
      SimpleDateFormat sdf = formats[i];
      String pattern = sdf.toPattern();
      buf.append("  <option value=\"")
          .append(pattern)
          .append("\"")
          .append(((pattern.equals(actualpattern))? " selected>":">"))
          .append(sdf.format(d))
          .append(" (")
          .append(pattern)
          .append(")")
          .append("</option>\n");
    }
    buf.append("</select>\n");

    return buf.toString();
  }//getDateFormatSelect

  public String getMailIdentitySelect(JwmaPreferences prefs) {
    JwmaMailIdentity[] mids = prefs.listMailIdentities();
    StringBuffer buf = new StringBuffer("<select name=\"mailidentity\">\n");
    //add options
    //buf.append("   <option value=\"\" selected>Use</option>\n");
    for (int i = 0; i < mids.length; i++) {
      buf.append("  <option value=\"")
          .append(mids[i].getUID())
          .append("\"")
          .append(((prefs.getMailIdentity().equals(mids[i]))? " selected>":">"))
          .append(mids[i].getName())
          .append(" (")
          .append(mids[i].getFrom())
          .append(")")
          .append("</option>\n");
    }
    buf.append("</select>\n");
    return buf.toString();
  }//getMailIdentitySelect

  public String getGroupMembersSelect(JwmaContactGroup group) {
    JwmaContact[] cts = group.listContacts();
    StringBuffer buf = new StringBuffer("<select name=\"contact.member.id\" size=\"10\" multiple>\n");
    //add options
    //buf.append("   <option value=\"\" selected>Use</option>\n");
    for (int i = 0; i < cts.length; i++) {
      buf.append("  <option value=\"")
          .append(cts[i].getUID())
          .append("\">")
          .append(cts[i].getLastname())
          .append(", ")
          .append(cts[i].getFirstname())
          .append(" ")
          .append(cts[i].getMiddlename())
          .append(" (")
          .append(cts[i].getEmail())
          .append(")")
          .append("</option>\n");
    }
    buf.append("</select>\n");
    return buf.toString();
  }//getGroupMembersSelect

  public String getNonMembersSelect(JwmaContactGroup group, JwmaContacts ctdb) {
    JwmaContact[] cts = ctdb.listContacts();
    JwmaContactGroupImpl grp = (JwmaContactGroupImpl) group;
    StringBuffer buf = new StringBuffer("<select name=\"contact.nomember.id\" size=\"10\" multiple>\n");
    //add options
    //buf.append("   <option value=\"\" selected>Use</option>\n");
    for (int i = 0; i < cts.length; i++) {
      if (!grp.containsContact(cts[i].getUID())) {
        buf.append("  <option value=\"")
            .append(cts[i].getUID())
            .append("\">")
            .append(cts[i].getLastname())
            .append(", ")
            .append(cts[i].getFirstname())
            .append(" ")
            .append(cts[i].getMiddlename())
            .append(" (")
            .append(cts[i].getEmail())
            .append(")")
            .append("</option>\n");
      }
    }
    buf.append("</select>\n");
    return buf.toString();
  }//getNonMembersSelect

  public String displayPartInlined(HttpSession session,
                                   JwmaMessagePart part,
                                   JwmaPreferences prefs, String from) {
	ResourceBundle viewcontent = JwmaKernel.getReference().getViewContentMessage();
    StringBuffer sbuf = new StringBuffer();
    JwmaMessagePartImpl msgpart = (JwmaMessagePartImpl) part;
    log.debug("displaying part inlined type=" + msgpart.getContentType());
    try {
      //handle by type
	  if(msgpart.getName() != null){
    	  sbuf.append(getPartDescription(msgpart, from));
      } else {
	      if (msgpart.isMimeType("text/plain")) {
	        //log.debug("textcontent="+msgpart.getTextContent());
	        sbuf.append("<pre>");
	        sbuf.append(
	            prefs.getMessageProcessor().process(
	                msgpart.getTextContent()
	            )
	        );
	        sbuf.append("</pre>");
	      } else if (msgpart.isMimeType("image/*")) {
	        sbuf.append("<img border=\"0\" src=\"")
	            .append(JwmaKernel.getReference().getMainControllerUrl())
	            .append("?acton=message&todo=displaypart&number=")
	            .append(part.getPartNumber())
	            .append("\">");
	      } else if (msgpart.isMimeType("text/directory")
	          || msgpart.isMimeType("application/directory")) {
	        ContactManagementPlugin cmp =
	            JwmaKernel.getReference().getContactManagementPlugin();
	        String ctype = StringUtil.split(msgpart.getContentType(), ";")[0].toLowerCase();
	        if (cmp.isSupportedContactImportType(ctype)) {
	          //log.debug("displayPartInlined()::Contact:"+msgpart.getTextContent());
	          JwmaContact ct = cmp.importContact(
	              new ByteArrayInputStream(msgpart.getTextContent().getBytes()),
	              ctype
	          );
	
	          session.putValue("jwma.contacts.import", ct);
	
	          sbuf.append("<img border=\"0\" src=\"images/address.png\">")
	              .append(ct.getLastname())
	              .append(",")
	              .append(ct.getFirstname())
	              .append(" <a href=\"")
	              .append(JwmaKernel.getReference().getContactsControllerUrl())
	              .append("?acton=contact&todo=import")
	              .append("\">")
	              .append(viewcontent.getString("contacts.import"))
	              .append("</a>");
	        } else {
	          sbuf.append(getPartDescription(msgpart, from));
	        }
	
	      } else if(msgpart.isMimeType("text/html")){
	    	  if(msgpart.getName() != null){
	    		  sbuf.append(getPartDescription(msgpart, from));
	    	  } else {
	    		  sbuf.append("<pre>");
	  	       /* sbuf.append(
	  	            prefs.getMessageProcessor().process(
	  	                msgpart.getTextContent()
	  	            )
	  	        );*/
	    		  sbuf.append(msgpart.getTextContent());
	  	        sbuf.append("</pre>");
	    	  }
	    	  
	      } else {
	    	  sbuf.append(getPartDescription(msgpart, from));
	      }
      }
      return sbuf.toString();
    } catch (Exception ex) {
      log.debug("displayPartInlined()", ex);
      return viewcontent.getString("message.part.failedinline");
    }
  }//displayPartInlined

  public String getPartDescription(JwmaMessagePart part, String from) {
	ResourceBundle viewcontent = JwmaKernel.getReference().getViewContentMessage();  
    StringBuffer sbuf = new StringBuffer("<div class='row-fluid' id=");
    sbuf.append(part.getPartNumber())
    	.append(" ><p>")
    	.append("<b>")
        //.append(viewcontent.getString("message.part.number"))
        //.append(part.getPartNumber())
        //.append("</b> (<i>")
        //.append(part.getContentType())
        //.append("</i>)")
        //.append("<br>\n")
        //.append("<b>")
        .append(viewcontent.getString("message.part.filename"))
        .append(" : </b>")
        .append("<a href=\"javascript:void(0);\"")
        //.append(getControllerUrl())
        //.append("?acton=message&amp;todo=displaypart&amp;number=")
        //.append("#bpm/mail/jwma/downloadAttachments")
        //.append("\"")
        .append(" onclick=\"")
        .append("downloadAttachments(")
        .append(part.getPartNumber())
        .append(")\">")
        .append(part.getName())
        .append("</a>(<i>")
        .append(getSizeString(part.getSize()))
        .append("</i>)\t&nbsp;&nbsp;&nbsp;");
        if(from.equalsIgnoreCase("compose")){
        	sbuf.append("<b>")
            .append("<a href=\"javascript:void(0);\"")
            .append(" onclick=\"")
            .append("removeFileAttachments(")
            .append(part.getPartNumber())
            .append(")\">")
            .append("Remove")
        	.append("</a>  </b>")
        	.append("\n");
        }
        sbuf.append("</p></div>\n");
    return sbuf.toString();
  }//getPartDescription

  public String getSizeString(int size) {
    int steps = 0;
    while (size > 1024) {
      size = size / 1024;
      steps++;
    }
    if (steps == 0) {
      return size + " bytes";
    } else if (steps == 1) {
      return size + " kB";
    } else if (steps == 2) {
      return size + " MB";
    } else {
      return "Huge ;)";
    }
  }//getSizeString

  public String getAlphabeticFilter(JwmaContacts contacts) {
	  ResourceBundle viewcontent = JwmaKernel.getReference().getViewContentMessage();
    StringBuffer sbuf = new StringBuffer();
    JwmaContactsImpl cts = (JwmaContactsImpl) contacts;

    //1. add the selector that will show all contacts
    if (cts.getContactFilter().toString().equals("")) {
      sbuf.append(viewcontent.getString("contacts.filter.alphabetic.none"))
          .append(" - ");
    } else {
      sbuf.append("<a href=\"")
          .append(JwmaKernel.getReference().getContactsControllerUrl())
          .append("?acton=database&amp;todo=setfilter&amp;filtertype=alphabetic&amp;filter=")
          .append("none")
          .append("\">")
          .append(viewcontent.getString("contacts.filter.alphabetic.none"))
          .append("</a> - ");
    }

    //2. add the selectors for the first characters of all lastnames
    for (Iterator iter = cts.getLastnameStarts(); iter.hasNext();) {
      String firstchar = (String) iter.next();
      if (cts.getContactFilter().toString().equalsIgnoreCase(firstchar)) {
        sbuf.append(firstchar).append("&nbsp;");
      } else {
        sbuf.append("<a href=\"")
            .append(JwmaKernel.getReference().getContactsControllerUrl())
            .append("?acton=database&amp;todo=setfilter&amp;filtertype=alphabetic&amp;filter=")
            .append(firstchar)
            .append("\">")
            .append(firstchar)
            .append("</a>&nbsp;");
      }
    }
    return sbuf.toString();
  }//getAlphabeticFilter

  public String getCategoryFilterSelect(JwmaContacts ctdb,
                                        String eventhandler) {
	  ResourceBundle viewcontent = JwmaKernel.getReference().getViewContentMessage();
    JwmaContactsImpl ctdbimpl = (JwmaContactsImpl) ctdb;
    String[] categories = ctdbimpl.listContactCategories();

    StringBuffer buf = new StringBuffer("<select name=\"filter\" ")
        .append(eventhandler)
        .append(">\n");

    boolean selected = false;
    boolean selectednow = true;
    if (categories != null && categories.length > 0) {
      for (int i = 0; i < categories.length; i++) {
        if (categories[i].equals(ctdbimpl.getCategoryFilter())) {
          selected = true;
          selectednow = true;
        } else {
          selectednow = false;
        }
        buf.append("  <option value=\"")
            .append(categories[i])
            .append("\"")
            .append(((selectednow)? " selected>":">"))
            .append(categories[i])
            .append("</option>\n");
      }
    }

    buf.append("  <option value=\"none\"")
        .append(((selected)? ">":" selected>"))
        .append(viewcontent.getString("contacts.filter.category.all"))
        .append("</option>\n");


    buf.append("</select>\n");
    return buf.toString();
  }//getCategoryFilterSelect

}//class JwmaHtmlHelperImpl
