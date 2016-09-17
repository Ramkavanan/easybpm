<%@ page session="true" import="dtw.webmail.model.*" %>

<%-- Ensure authenticated & valid session --%>
<%
	if(session.isNew() 
       || session.getValue("jwma.session")==null 
       || session.getValue("jwma.session.authenticated")==null) {
    		response.sendRedirect(response.encodeRedirectUrl("login.jsp"));
			return;
    }
%>
<%-- Import taglibs and i18n bundle --%>
<%@ include file="/common/taglibs.jsp"%>
<%-- <%@ taglib uri="http://jakarta.apache.org/taglibs/i18n-1.0" prefix="i18n" %>
<i18n:bundle baseName="viewcontent" localeAttribute="jwma.locale"
             resourceClassLoader="jwma.resourceloader" id="bundle" />
<i18n:bundle baseName="errormessages" localeAttribute="jwma.locale"
             resourceClassLoader="jwma.resourceloader" id="errorbundle" /> --%>
<%-- Prepare references for use in the page --%>
<%
	JwmaHtmlHelper htmlhelper=(JwmaHtmlHelper) session.getValue("jwma.htmlhelper");
	JwmaInboxInfo inbox=(JwmaInboxInfo) session.getValue("jwma.inboxinfo");
	JwmaPreferences prefs=(JwmaPreferences) session.getValue("jwma.preferences");
	JwmaMailIdentity mid=prefs.getMailIdentity();
	JwmaError error=null;
    Object o=session.getValue("jwma.error");
	if (o!=null) {
		error=(JwmaError) o;
    }
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"
        "http://www.w3.org/TR/REC-html40/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> 
	<meta http-equiv="Pragma" content="no-cache">
    <title><fmt:message key="jwma.webMail"/></title>
</head>

<body bgcolor="#ffffff" link="#666666" vlink="#666666" alink="#FFFFFF">
<script type="text/javascript">
        function submitUpdate(aform) {
        	aform.todo.value="update";
			aform.submit();
		}//submitUpdate

		function submitRemoveMID(aform){
			aform.todo.value="removemailid";
			aform.submit();
		}//submitRemoveMID

		function submitAddMID(aform){
			aform.todo.value="addmailid";
   		 	aform.submit();
		}//submitAddMID
	</script>
<%-- Header & Menu --%>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
	    <td height="36" width="80%"> <img src="images/inbox.png" alt="Inbox" width="36" height="36" align="middle">
            <font face="Arial,Helvetica" size="-1">
              <b><a href="<%= htmlhelper.getFolderDisplayAction(inbox) %>"><fmt:message key="inbox.name"/></a></b>
              <em>
                <fmt:message key="inbox.status">
                  <%= new Integer(inbox.getUnreadMessageCount()) %>
                  <%= new Integer(inbox.getMessageCount()) %>
                </fmt:message>
              </em>
            </font>
        </td>
        <td height="36" width="20%">
            <img src="images/logo.png" width="195" height="36" align="right" alt="jwma WebMail">
            <a name="top"></a>
        </td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="3">
    <tr bgcolor="#000000">
        <td width="9%" align="center">
            <a href="#bpm/mail/jwma/goMailMainPage?acton=session&amp;todo=redirect&amp;view=main">
                <img src="images/main_small.png" width="20" height="20" border="0" alt="<fmt:message key="menu.tomain"/>">
            </a>
        </td>
        <td width="3%" align="center">
            <a href="#bpm/mail/jwma/composeMail?acton=message&amp;todo=compose">
                <img src="images/compose_small.png" width="20" height="20" border="0" alt="<fmt:message key="menu.compose"/>">
            </a>
        </td>
        <td width="10%" align="center">
            <a href="#bpm/mail/jwma/showMailContacts">
                <img src="images/addresses_small.png" width="20" height="20" border="0" alt="<fmt:message key="menu.contacts"/>">
            </a>
        </td>
        <td width="7%" align="center">
            <a href="#bpm/mail/jwma/showMailPreferences">
                <img src="images/settings_small.png" width="20" height="20" border="0" alt="<fmt:message key="menu.settings"/>">
            </a>
        </td>
        <td width="6%" align="center">
            <a href="#bpm/mail/jwma/mailLogout?acton=session&amp;todo=logout">
                <img src="images/logout_small.png" width="21" height="20" border="0" alt="<fmt:message key="menu.logout"/>">
            </a>
        </td>
        <td width="65%" bgcolor="#000000">&nbsp;</td>
    </tr>
    <tr bgcolor="#dadada">
        <td width="9%" align="center" nowrap>
            <a href="#bpm/mail/jwma/goMailMainPage?acton=session&amp;todo=redirect&amp;view=main">
                <font face="Arial, Helvetica, sans-serif" size="-1"><b><fmt:message key="menu.tomain"/></b></font>
            </a>
        </td>
        <td width="3%" align="center" nowrap>
            <a href="#bpm/mail/jwma/composeMail?acton=message&amp;todo=compose">
            <font face="Arial, Helvetica, sans-serif" size="-1"><b><fmt:message key="menu.compose"/></b></font>
            </a>
        </td>
        <td width="10%" align="center" nowrap>
            <a href="#bpm/mail/jwma/showMailContacts">
                <font face="Arial, Helvetica, sans-serif" size="-1"><b><fmt:message key="menu.contacts"/></b></font>
            </a>
        </td>
        <td width="7%" align="center" nowrap>
            <a href="#bpm/mail/jwma/showMailPreferences">
                <font face="Arial, Helvetica, sans-serif" size="-1"><b><fmt:message key="menu.settings"/></b></font>
            </a>
        </td>
        <td width="6%" align="center" nowrap>
            <a href="#bpm/mail/jwma/mailLogout?acton=session&amp;todo=logout">
                <font face="Arial, Helvetica, sans-serif" size="-1"><b><fmt:message key="menu.logout"/></b></font>
            </a>
        </td>
        <td width="65%" nowrap><font color="#00000">&nbsp;</font></td>
    </tr>
</table>
<%-- End Header & Menu --%>
<br>
<%-- Display inlined error --%>
<% if (error!=null && !error.isDisplayed()) {
      String[] errors=error.getDescriptions();
      for (int i=0;i<errors.length;i++) {
%>
        <font size="+1" color="#ff0000"><b><fmt:message key="<%= errors[i] %>"/></b></font><br>
<%      error.setDisplayed(true);
      }
   }
%>
<%-- End Display inlined error --%>
<form method="post" action="<%= htmlhelper.getControllerUrl() %>">
  <input type="hidden" name="acton" value="preferences">
  <input type="hidden" name="todo" value="">

  <table border="0" width="90%" cellspacing="1" cellpadding="1">
    <tr>
      <td colspan=2 bgcolor="#000000" align="left">
        <b><font face="Arial, Helvetica, sans-serif" color="#FFFFFF"><fmt:message key="preferences.personaldata"/></font></b>
      </td>
    </tr>
    <tr>
      <td width="20%" align="left" valign="top" bgcolor="#dddddd">
        <b><fmt:message key="preferences.firstname"/></b>
      </td>
      <td width="80%" align="left" valign="top" bgcolor="#eeeeee">
        <input type="text" name="firstname" size="40" maxlength="100" value="<%= prefs.getFirstname() %>">
      </td>
    </tr>
    <tr>
      <td width="20%" align="left" valign="top" bgcolor="#dddddd">
        <b><fmt:message key="preferences.lastname"/></b>
      </td>
      <td width="80%" bgcolor="#eeeeee" align="left" valign="top">
        <input type="text" name="lastname" size="40" maxlength="100" value="<%= prefs.getLastname() %>">
      </td>
    </tr>
  </table>
  <br>
  <% if(!prefs.isExpert()) { %>
    <table border="0" width="90%" cellspacing="1" cellpadding="1">
      <tr>
        <td colspan=2 bgcolor="#000000" align="left">
          <b><font face="Arial, Helvetica, sans-serif" color="#FFFFFF"><fmt:message key="preferences.mailidentity"/> </font></b>
        </td>
      </tr>
      <tr>
        <td width="20%" align="left" valign="top" bgcolor="#dddddd">
          <b><fmt:message key="preferences.mailid.name"/></b>
        </td>
        <td width="80%" align="left" valign="top" bgcolor="#eeeeee">
          <input type="text" name="mid.name" size="40" maxlength="100" value="<%= mid.getName() %>">
        </td>
      </tr>
      <tr>
        <td width="20%" align="left" valign="top" bgcolor="#dddddd" height="20">
          <b><fmt:message key="message.from"/></b>
        </td>
        <td width="80%" bgcolor="#eeeeee" align="left" valign="top" height="20">
            <input type="text" name="mid.from" size="40" maxlength="100" value="<%= mid.getFrom() %>">
        </td>
      </tr>
      <tr>
        <td width="20%" align="left" valign="top" bgcolor="#dddddd" height="20">
          <b><fmt:message key="message.replyto"/></b>
        </td>
        <td width="80%" bgcolor="#eeeeee" align="left" valign="top" height="20">
          <input type="text" name="mid.replyto" size="40" maxlength="100" value="<%= mid.getReplyTo() %>">
        </td>
      </tr>
      <tr>
        <td width="20%" align="left" valign="top" bgcolor="#dddddd">
          <b><fmt:message key="preferences.mailid.signature"/></b>
        </td>
        <td width="80%" bgcolor="#eeeeee" align="left" valign="top">
          <textarea name="mid.signature" cols="40" rows="5"><%= mid.getSignature() %></textarea>
        </td>
      </tr>
      <tr>
        <td width="20%" align="left" valign="top" bgcolor="#dddddd">
          <b><fmt:message key="preferences.mailid.autosign"/></b>
        </td>
        <td width="80%" align="left" valign="top" bgcolor="#eeeeee">
          <input type="checkbox" name="mid.autosigning"  value="true" <%= ((mid.isAutoSigning())? "checked":"") %>>
          <font face="Arial, Helvetica, sans-serif"><fmt:message key="form.enable"/></font><br>*
          <font size="-2" face="Arial, Helvetica, sans-serif">
            <fmt:message key="preferences.mailid.autosign.description"/>
          </font>
        </td>
      </tr>
      <tr>
        <td width="20%" align="left" valign="top" bgcolor="#dddddd">
          <b><fmt:message key="preferences.mailid.randomappend"/></b>
        </td>
        <td width="80%" align="left" valign="top" bgcolor="#eeeeee">
          <%= htmlhelper.getRandomAppendTypesSelect(prefs,mid) %>
        </td>
      </tr>
      <tr>
        <td width="20%" align="left" valign="top" bgcolor="#dddddd">
          <b><fmt:message key="preferences.mailid.relatedcontact"/></b>
        </td>
        <td width="80%" align="left" valign="top" bgcolor="#eeeeee">&nbsp;</td>
      </tr>
      <tr>
        <td width="20%" align="left" valign="top" bgcolor="#dddddd">
          <b><fmt:message key="preferences.mailid.note"/></b>
        </td>
        <td width="80%" bgcolor="#eeeeee" align="left" valign="top">
          <textarea name="mid.note" cols="40" rows="5"><%= mid.getNote() %></textarea>
        </td>
      </tr>
    </table>
  <% } else { %>
    <table border="0" width="90%" cellspacing="1" cellpadding="1">
      <tr>
        <td colspan="4" bgcolor="#000000" align="left">
          <b><font face="Arial, Helvetica, sans-serif" color="#FFFFFF"><fmt:message key="preferences.mailidentities"/></font></b>
        </td>
      </tr>
	  <tr bgcolor="#dadada">
        <td nowrap width="5%">
          <font color="#000000" face="Arial,Helvetica"><b>#</b></font>
        </td>
        <td nowrap width="45%">
          <font color="#000000" face="Arial,Helvetica"><b><fmt:message key="preferences.mailid.name"/></b></font>
        </td>
        <td nowrap width="45%">
          <font color="#000000" face="Arial,Helvetica"><b><fmt:message key="message.from"/></b></font>
        </td>
        <td nowrap width="5%">
          <font color="#000000" face="Arial,Helvetica"><b><fmt:message key="form.default"/></b></font>
        </td>
      </tr>
      <%
        JwmaMailIdentity[] identities=prefs.listMailIdentities();
        for (int i=0; i<identities.length; i++) {
      %>
        <tr>
          <td width="5%" bgcolor="#eeeeee">
            <input type="checkbox" name="mid.uid" value="<%= identities[i].getUID() %>">
          </td>
          <td width="45%" align="left" valign="top" bgcolor="#eeeeee">
            <b><a href="mailidentity.jsp?mid.uid=<%= identities[i].getUID() %>"><em><%= identities[i].getName() %></em></a></b>
          </td>
          <td width="45%" align="left" valign="top" bgcolor="#eeeeee">
            <b><%= identities[i].getFrom() %></b>
          </td>
          <td width="5%" align="left" valign="top" bgcolor="#eeeeee">
            <input type="radio" name="mid.defaultid" value="<%= identities[i].getUID() %>" <%= ((prefs.getMailIdentity().equals(identities[i]))? "checked":"") %>>
          </td>
        </tr>
	<% }//end for %>
    <tr>
      <td bgcolor="#000000" height="5" width="5%">&nbsp;</td>
      <td bgcolor="#000000" height="5" width="45%">&nbsp;</td>
      <td bgcolor="#000000" height="5" width="45%">&nbsp;</td>
      <td bgcolor="#000000" height="5" width="5%">&nbsp;</td>
    </tr>
    <%-- Add Mail Identity--%>
    <tr>
      <td bgcolor="#eeeeee" width="5%"> <i><fmt:message key="form.add"/>:</i></td>
      <td bgcolor="#eeeeee" nowrap width="45%">
        <input type="text" name="mid.name" size="40">
      </td>
      <td bgcolor="#eeeeee" nowrap width="45%">
        <input type="text" name="mid.from" size="40">
      </td>
      <td nowrap bgcolor="#eeeeee" width="5%">
        <input type="checkbox" name="mid.default" value="true">
      </td>
    </tr>
    <tr align="right">
      <td colspan="4" bgcolor="#000000">
        <input type="button" name="remove" value="<fmt:message key="form.delete"/>" onclick="submitRemoveMID(this.form);">
        &nbsp;&nbsp;&nbsp;
        <input type="button" name="add" value="<fmt:message key="form.add"/>" onclick="submitAddMID(this.form);">
      </td>
    </tr>
  </table>
  <% }//end if %>
  <br>
  <table border="0" width="90%" cellspacing="1" cellpadding="1">
    <tr>
      <td colspan=2 bgcolor="#000000" align="left">
        <b><font face="Arial, Helvetica, sans-serif" color="#FFFFFF"><fmt:message key="preferences.systemsettings"/></font></b>
      </td>
    </tr>
    <tr>
      <td width="20%" align="left" valign="top" bgcolor="#dddddd">
        <b><fmt:message key="preferences.language"/></b>
      </td>
      <td width="80%" align="left" valign="top" bgcolor="#eeeeee"> <%= htmlhelper.getLanguageSelect(prefs) %>
        <font face="Arial, Helvetica, sans-serif"></font><br>*
        <font size="-2" face="Arial, Helvetica, sans-serif">
          <fmt:message key="preferences.language.description"/>
        </font>
      </td>
    </tr>
    <tr>
      <td width="20%" align="left" valign="top" bgcolor="#dddddd">
        <b><fmt:message key="preferences.expert"/></b>
      </td>
      <td width="80%" align="left" valign="top" bgcolor="#eeeeee">
        <input type="checkbox" name="expert"  value="true" <%= ((prefs.isExpert())? "checked":"") %>>
        <font face="Arial, Helvetica, sans-serif"><fmt:message key="form.enable"/></font><br>*
        <font size="-2" face="Arial, Helvetica, sans-serif"><fmt:message key="preferences.expert.description"/></font>
      </td>
    </tr>
    <tr>
      <td width="20%" align="left" valign="top" bgcolor="#dddddd">
        <b><fmt:message key="preferences.displayinlined"/></b>
      </td>
      <td width="80%" align="left" valign="top" bgcolor="#eeeeee">
        <input type="checkbox" name="inlined"  value="true" <%= ((prefs.isDisplayingInlined())? "checked":"") %>>
        <font face="Arial, Helvetica, sans-serif"><fmt:message key="form.enable"/></font><br>*
        <font size="-2" face="Arial, Helvetica, sans-serif"><fmt:message key="preferences.displayinlined.description"/></font>
      </td>
    </tr>
    <tr>
      <td width="20%" align="left" valign="top" bgcolor="#dddddd">
        <b><fmt:message key="preferences.autoquote"/></b>
      </td>
      <td width="80%" align="left" valign="top" bgcolor="#eeeeee">
        <input type="checkbox" name="autoquote"  value="true" <%= ((prefs.isAutoQuote())? "checked":"") %>>
        <font face="Arial, Helvetica, sans-serif"><fmt:message key="form.enable"/></font><br>*
        <font size="-2" face="Arial, Helvetica, sans-serif">
          <fmt:message key="preferences.autoquote.description"/>
        </font>
      </td>
    </tr>
    <tr>
      <td width="20%" align="left" valign="top" bgcolor="#dddddd">
        <b><fmt:message key="preferences.quotechar"/></b>
      </td>
      <td width="80%" bgcolor="#eeeeee">
        <input type="text" name="quotechar" size="10" maxlength="1" value="<%= prefs.getQuoteChar() %>"><br>*
        <font size="-2" face="Arial, Helvetica, sans-serif">
          <fmt:message key="preferences.quotechar.description"/>
        </font>
      </td>
    </tr>
    <tr>
      <td width="20%" align="left" valign="top" bgcolor="#dddddd">
        <b><fmt:message key="preferences.autoarchive"/></b>
      </td>
      <td width="80%" bgcolor="#eeeeee">
        <input type="checkbox" name="autoarchivesent" value="true" <%= ((prefs.isAutoArchiveSent())? "checked":"") %>>
        <font face="Arial, Helvetica, sans-serif"><fmt:message key="form.enable"/></font><br>*
        <font size="-2" face="Arial, Helvetica, sans-serif">
          <fmt:message key="preferences.autoarchive.description"/>
        </font>
      </td>
    </tr>

    <tr>
      <td width="20%" align="left" valign="top" bgcolor="#dddddd">
        <b><fmt:message key="preferences.automoveread"/></b>
      </td>
      <td width="80%" bgcolor="#eeeeee">
        <input type="checkbox" name="automoveread"  value="true" <%= ((prefs.isAutoMoveRead())? "checked":"") %>>
        <font face="Arial, Helvetica, sans-serif"><fmt:message key="form.enable"/></font><br>*
        <font size="-2" face="Arial, Helvetica, sans-serif">
          <fmt:message key="preferences.automoveread.description"/>
        </font>
      </td>
    </tr>
    <tr>
      <td width="20%" align="left" valign="top" bgcolor="#dddddd">
        <b><fmt:message key="preferences.autoempty"/></b>
      </td>
      <td width="80%" bgcolor="#eeeeee">
        <input type="checkbox" name="autoempty" value="true" <%= ((prefs.isAutoEmpty())? "checked":"") %>>
        <font face="Arial, Helvetica, sans-serif"><fmt:message key="form.enable"/></font><br>*
        <font size="-2" face="Arial, Helvetica, sans-serif">
          <fmt:message key="preferences.autoempty.description"/>
        </font>
      </td>
    </tr>
    <% if(prefs.isExpert()) { %>
      <tr>
        <td width="20%" align="left" valign="top" bgcolor="#dddddd">
          <b><fmt:message key="preferences.rootfolder"/></b>
        </td>
        <td width="80%" bgcolor="#eeeeee">
          <input type="text" name="rootfolder" size="40" maxlength="100" value="<%= prefs.getRootFolder() %>">
          <br>*
          <font size="-2" face="Arial, Helvetica, sans-serif">
            <fmt:message key="preferences.rootfolder.description"/>
          </font>
        </td>
      </tr>
      <tr>
        <td width="20%" align="left" valign="top" bgcolor="#dddddd">
          <b><fmt:message key="preferences.outbox"/></b>
        </td>
        <td width="80%" bgcolor="#eeeeee">
          <input type="text" name="sentmailarchive" size="40" maxlength="100" value="<%= prefs.getSentMailArchive() %>">
          <br>
          *<font size="-2" face="Arial, Helvetica, sans-serif">
            <fmt:message key="preferences.outbox.description"/>
          </font>
        </td>
      </tr>
      <tr>
        <td width="20%" align="left" valign="top" bgcolor="#dddddd">
          <b><fmt:message key="preferences.readmessages"/></b>
        </td>
        <td width="80%" bgcolor="#eeeeee">
          <input type="text" name="readmailarchive" size="40" maxlength="100" value="<%= prefs.getReadMailArchive() %>">
          <br>*
          <font size="-2" face="Arial, Helvetica, sans-serif">
            <fmt:message key="preferences.readmessages.description"/>
          </font>
        </td>
      </tr>
      <tr>
        <td width="20%" align="left" valign="top" bgcolor="#dddddd">
          <b><fmt:message key="preferences.draftfolder"/></b>
        </td>
        <td width="80%" bgcolor="#eeeeee">
          <input type="text" name="draftfolder" size="40" maxlength="100" value="<%= prefs.getDraftFolder() %>">
          <br>*
          <font size="-2" face="Arial, Helvetica, sans-serif">
            <fmt:message key="preferences.draftfolder.description"/>
          </font>
        </td>
      </tr>
      <tr>
        <td width="20%" align="left" valign="top" bgcolor="#dddddd">
          <b><fmt:message key="preferences.trashfolder"/></b>
        </td>
        <td width="80%" bgcolor="#eeeeee">
          <input type="text" name="trashfolder" size="40" maxlength="100" value="<%= prefs.getTrashFolder() %>">
          <br>*
          <font size="-2" face="Arial, Helvetica, sans-serif">
            <fmt:message key="preferences.trashfolder.description"/>
          </font>
        </td>
      </tr>
    <% } %>
    <tr>
      <td width="20%" align="left" valign="top" bgcolor="#dddddd">
        <b><fmt:message key="preferences.dateformat"/></b>
      </td>
      <td width="80%" align="left" valign="top" bgcolor="#eeeeee">
        <%= htmlhelper.getDateFormatSelect(prefs) %>
      </td>
    </tr>
    <tr>
      <td width="20%" align="left" valign="top" bgcolor="#dddddd">
        <b><fmt:message key="preferences.messageprocessor"/></b>
      </td>
      <td width="80%" align="left" valign="top" bgcolor="#eeeeee">
        <%= htmlhelper.getMessageProcessorSelect(prefs) %>
      </td>
    </tr>
  </table>
  <br>
  <table border="0" width="90%" cellspacing="0" cellpadding="0">
    <tr>
      <td height="15" align="left" valign="top" width="25%">&nbsp; </td>
      <td height="15" align="right" valign="top" width="75%" nowrap>
        <input type="reset" name="reset" value="<fmt:message key="form.undo"/>">
        <input type="button" name="update" value="<fmt:message key="form.update"/>" onclick="submitUpdate(this.form);">
      </td>
    </tr>
  </table>
</form>


<%-- Footer --%>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr bgcolor="#000000" valign="bottom">
    <td>
      <a href="#top">
        <img src="images/up_small.png" width="15" height="15" align="right" border="0" alt="<fmt:message key="menu.totop"/>">
      </a>
      <font size="-2" face="Arial, Helvetica, sans-serif" color="#FFFFFF">&copy; 2000-2003 jwma team</font>
    </td>
  </tr>
</table>
<%-- End Footer --%>
</body>
</html>
