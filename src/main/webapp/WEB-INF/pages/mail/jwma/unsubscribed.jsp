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
	JwmaStoreInfo store=(JwmaStoreInfo) session.getValue("jwma.storeinfo");
  JwmaFolder actualfolder = (JwmaFolder) session.getValue("jwma.folder");
	JwmaInboxInfo inbox=(JwmaInboxInfo) session.getValue("jwma.inboxinfo");
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
    <meta http-equiv="Expires" CONTENT="0">
    <meta http-equiv="Cache-Control" CONTENT="no-cache">

    <title><fmt:message key="jwma.webMail"/></title>
</head>
<body bgcolor="#ffffff" link="#666666" vlink="#666666" alink="#ffffff">

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
<%    error.setDisplayed(true);
    }
 }
%>
<%-- End Display inlined error --%>
<br>
<%-- folder list --%>

<form method="post" action="<%= htmlhelper.getControllerUrl() %>">
<input type="hidden" name="acton" value="folder">
<input type="hidden" name="todo" value="subscribe">
    <table width="90%" cellpadding="1" cellspacing="1" border="0">
        <tr bgcolor="#000000">
            <td nowrap colspan="2" width="100%">
              <img src="images/folder_small.png" width="20" height="20" alt="<i18n:message key="folder.folders"/>">
              <font face="Arial,Helvetica" size="+1" color="#ffffff"><b><%= htmlhelper.getPathHierarchyNavigator(store,actualfolder) %></b></font>
              &nbsp;&nbsp;<b><font color="#FFFFFF" face="Arial, Helvetica, sans-serif"><i18n:message key="folder.unsubscribed"/> <i18n:message key="folder.folders"/></font></b>
              &nbsp;&nbsp;<font size="-1" face="Arial, Helvetica, sans-serif" color="#ffffff">
              (<a href="<%= htmlhelper.getControllerUrl() %>?acton=folder&amp;todo=display_subscribed"><i18n:message key="folder.subscribed"/></a>)
              </font>
			      </td>
        </tr>
        <tr bgcolor="#dadada">
            <td nowrap width="90%">
              <font color="#000000" face="Arial,Helvetica"><b><i18n:message key="folder.folder"/></b></font>
            </td>
            <td nowrap width="10%">
              <font color="#000000" face="Arial,Helvetica"><b><i18n:message key="folder.subscribe"/></b></font>
            </td>
        </tr>
        <%-- loop over unsubscribed folders --%>
		<% 	JwmaFolder[] folders=actualfolder.listSubfolders(JwmaFolder.TYPE_ALL,false);
	   		for (int index=0;index<folders.length;index++) {
				  JwmaFolder folder = folders[index];
          //skip subscribed
          if (folder.isSubscribed()) {
            continue;
          }
		%>
        <tr>
            <td bgcolor="#eeeeee" width="90%">
                <%= folder.getName() %>
            </td>
            <td bgcolor="#eeeeee" width="10%">
                <input type="checkbox" name="paths" value="<%= folder.getPath() %>">
            </td>
     <%	}//for end %> <%-- end loop --%>
        <tr>
            <td colspan="2" bgcolor="#000000" width="100%" align="right">
                <input type="submit" name="subscribe" value="<i18n:message key="folder.subscribe"/>">
            </td>
        </tr>
    </table>
</form>


<%--End folder list --%>

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
