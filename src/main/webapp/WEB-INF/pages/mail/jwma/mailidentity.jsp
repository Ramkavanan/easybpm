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
<%@ taglib uri="http://jakarta.apache.org/taglibs/i18n-1.0" prefix="i18n" %>
<i18n:bundle baseName="viewcontent" localeAttribute="jwma.locale"
             resourceClassLoader="jwma.resourceloader" id="bundle" />
<i18n:bundle baseName="errormessages" localeAttribute="jwma.locale"
             resourceClassLoader="jwma.resourceloader" id="errorbundle" />
<%-- Prepare references for use in the page --%>
<%
	JwmaHtmlHelper htmlhelper=(JwmaHtmlHelper) session.getValue("jwma.htmlhelper");
	JwmaInboxInfo inbox=(JwmaInboxInfo) session.getValue("jwma.inboxinfo");
	JwmaPreferences prefs=(JwmaPreferences) session.getValue("jwma.preferences");
	String muid=request.getParameter("mid.uid");
	JwmaMailIdentity mid=prefs.getMailIdentity(muid);
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

<%-- Header & Menu --%>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
	    <td height="36" width="80%"> <img src="images/inbox.png" alt="Inbox" width="36" height="36" align="middle">
            <font face="Arial,Helvetica" size="-1">
              <b><a href="<%= htmlhelper.getFolderDisplayAction(inbox) %>"><i18n:message key="inbox.name"/></a></b>
              <em>
                <i18n:message key="inbox.status">
                  <i18n:messageArg value="<%= new Integer(inbox.getUnreadMessageCount()) %>" />
                  <i18n:messageArg value="<%= new Integer(inbox.getMessageCount()) %>" />
                </i18n:message>
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
            <a href="<%= htmlhelper.getControllerUrl() %>?acton=session&amp;todo=redirect&amp;view=main">
                <img src="images/main_small.png" width="20" height="20" border="0" alt="<i18n:message key="menu.tomain"/>">
            </a>
        </td>
        <td width="3%" align="center">
            <a href="<%= htmlhelper.getControllerUrl() %>?acton=message&amp;todo=compose">
                <img src="images/compose_small.png" width="20" height="20" border="0" alt="<i18n:message key="menu.compose"/>">
            </a>
        </td>
        <td width="10%" align="center">
            <a href="contacts.jsp">
                <img src="images/addresses_small.png" width="20" height="20" border="0" alt="<i18n:message key="menu.contacts"/>">
            </a>
        </td>
        <td width="7%" align="center">
            <a href="preferences.jsp">
                <img src="images/settings_small.png" width="20" height="20" border="0" alt="<i18n:message key="menu.settings"/>">
            </a>
        </td>
        <td width="6%" align="center">
            <a href="<%= htmlhelper.getControllerUrl() %>?acton=session&amp;todo=logout">
                <img src="images/logout_small.png" width="21" height="20" border="0" alt="<i18n:message key="menu.logout"/>">
            </a>
        </td>
        <td width="65%" bgcolor="#000000">&nbsp;</td>
    </tr>
    <tr bgcolor="#dadada">
        <td width="9%" align="center" nowrap>
            <a href="<%= htmlhelper.getControllerUrl() %>?acton=session&amp;todo=redirect&amp;view=main">
                <font face="Arial, Helvetica, sans-serif" size="-1"><b><i18n:message key="menu.tomain"/></b></font>
            </a>
        </td>
        <td width="3%" align="center" nowrap>
            <a href="<%= htmlhelper.getControllerUrl() %>?acton=message&amp;todo=compose">
            <font face="Arial, Helvetica, sans-serif" size="-1"><b><i18n:message key="menu.compose"/></b></font>
            </a>
        </td>
        <td width="10%" align="center" nowrap>
            <a href="contacts.jsp">
                <font face="Arial, Helvetica, sans-serif" size="-1"><b><i18n:message key="menu.contacts"/></b></font>
            </a>
        </td>
        <td width="7%" align="center" nowrap>
            <a href="preferences.jsp">
                <font face="Arial, Helvetica, sans-serif" size="-1"><b><i18n:message key="menu.settings"/></b></font>
            </a>
        </td>
        <td width="6%" align="center" nowrap>
            <a href="<%= htmlhelper.getControllerUrl() %>?acton=session&amp;todo=logout">
                <font face="Arial, Helvetica, sans-serif" size="-1"><b><i18n:message key="menu.logout"/></b></font>
            </a>
        </td>
        <td width="65%" nowrap><font color="#00000">&nbsp;</font></td>
    </tr>
</table>
<%-- End Header & Menu --%>
<%-- Display inlined error --%>
<% if (error!=null && !error.isDisplayed()) {
  String[] errors=error.getDescriptions();
  for (int i=0;i<errors.length;i++) {
%>
	<font size="+1" color="#ff0000"><b><i18n:message key="<%= errors[i] %>" bundle="<%= errorbundle %>"/></b></font><br>
<%  error.setDisplayed(true);
  }
}
%>
<%-- End Display inlined error --%>

<form method="post" action="<%= htmlhelper.getControllerUrl() %>">
  <input type="hidden" name="acton" value="preferences">
  <input type="hidden" name="todo" value="updatemailid">
  <input type="hidden" name="mid.uid" value="<%= muid %>">
  <br>
  <table border="0" width="90%" cellspacing="1" cellpadding="1">
    <tr>
      <td colspan=2 bgcolor="#000000" align="left">
        <b><font face="Arial, Helvetica, sans-serif" color="#FFFFFF">
          <i18n:message key="preferences.mailidentity"/> <%= mid.getName() %>
        </font></b>
      </td>
    </tr>
    <tr>
      <td width="20%" align="left" valign="top" bgcolor="#dddddd">
        <b><i18n:message key="preferences.mailid.name"/></b>
      </td>
      <td width="80%" align="left" valign="top" bgcolor="#eeeeee">
        <input type="text" name="mid.name" size="40" maxlength="100" value="<%= mid.getName() %>">
      </td>
    </tr>
    <tr>
      <td width="20%" align="left" valign="top" bgcolor="#dddddd" height="20">
        <b><i18n:message key="message.from"/></b>
      </td>
      <td width="80%" bgcolor="#eeeeee" align="left" valign="top" height="20">
        <input type="text" name="mid.from" size="40" maxlength="100" value="<%= mid.getFrom() %>">
      </td>
    </tr>
    <tr>
      <td width="20%" align="left" valign="top" bgcolor="#dddddd" height="20">
        <b><i18n:message key="message.replyto"/></b>
      </td>
      <td width="80%" bgcolor="#eeeeee" align="left" valign="top" height="20">
        <input type="text" name="mid.replyto" size="40" maxlength="100" value="<%= mid.getReplyTo() %>">
      </td>
    </tr>
    <tr>
      <td width="20%" align="left" valign="top" bgcolor="#dddddd">
        <b><i18n:message key="preferences.mailid.signature"/></b>
      </td>
      <td width="80%" bgcolor="#eeeeee" align="left" valign="top">
        <textarea name="mid.signature" cols="40" rows="5"><%= mid.getSignature() %></textarea>
      </td>
    </tr>
    <tr>
      <td width="20%" align="left" valign="top" bgcolor="#dddddd">
        <b><i18n:message key="preferences.mailid.autosign"/></b>
      </td>
      <td width="80%" align="left" valign="top" bgcolor="#eeeeee">
        <input type="checkbox" name="mid.autosigning"  value="true" <%= ((mid.isAutoSigning())? "checked":"") %>>
        <font face="Arial, Helvetica, sans-serif"><i18n:message key="form.enable"/></font></br>*
        <font size="-2" face="Arial, Helvetica, sans-serif">
          <i18n:message key="preferences.mailid.autosign.description"/>
        </font>
      </td>
    </tr>
    <tr>
      <td width="20%" align="left" valign="top" bgcolor="#dddddd">
        <b><i18n:message key="preferences.mailid.randomappend"/></b>
      </td>
      <td width="80%" align="left" valign="top" bgcolor="#eeeeee">
        <%= htmlhelper.getRandomAppendTypesSelect(prefs,mid) %>
	  </td>
    </tr>
    <tr>
      <td width="20%" align="left" valign="top" bgcolor="#dddddd">
        <b><i18n:message key="preferences.mailid.relatedcontact"/></b>
      </td>
      <td width="80%" align="left" valign="top" bgcolor="#eeeeee">&nbsp;</td>
    </tr>
    <tr>
      <td width="20%" align="left" valign="top" bgcolor="#dddddd">
        <b><i18n:message key="form.default"/> <i18n:message key="preferences.mailidentity"/></b>
      </td>
      <td width="80%" align="left" valign="top" bgcolor="#eeeeee">
        <input type="checkbox" name="mid.default"  value="true" <%= ((prefs.getMailIdentity().equals(muid))? "checked":"") %>>
        <font face="Arial, Helvetica, sans-serif"></font><br>*
        <font size="-2" face="Arial, Helvetica, sans-serif">
          <i18n:message key="preferences.mailid.default.description"/>
        </font>
      </td>
    </tr>
    <tr>
      <td width="20%" align="left" valign="top" bgcolor="#dddddd">
        <b><i18n:message key="preferences.mailid.note"/></b>
      </td>
      <td width="80%" bgcolor="#eeeeee" align="left" valign="top">
        <textarea name="mid.note" cols="40" rows="5"><%= mid.getNote() %></textarea>
      </td>
    </tr>
    <tr bgcolor="#000000" align="right">
      <td height="15" valign="top" colspan="2">
        <input type="reset" name="reset" value="<i18n:message key="form.undo"/>">
        &nbsp;&nbsp;
        <input type="submit" name="submit" value="<i18n:message key="form.update"/>">
      </td>
    </tr>
  </table>
</form>

<%-- Footer --%>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr bgcolor="#000000" valign="bottom">
    <td>
      <a href="#top">
        <img src="images/up_small.png" width="15" height="15" align="right" border="0" alt="<i18n:message key="menu.totop"/>">
      </a>
      <font size="-2" face="Arial, Helvetica, sans-serif" color="#FFFFFF">&copy; 2000-2003 jwma team</font>
    </td>
  </tr>
</table>
<%-- End Footer --%>
</body>
</html>
