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
    JwmaPreferences prefs=(JwmaPreferences) session.getValue("jwma.preferences");
	JwmaStoreInfo store=(JwmaStoreInfo) session.getValue("jwma.storeinfo");
	JwmaInboxInfo inbox=(JwmaInboxInfo) session.getValue("jwma.inboxinfo");
	JwmaTrashInfo trash=(JwmaTrashInfo) session.getValue("jwma.trashinfo");
	JwmaFolder folder=(JwmaFolder) session.getValue("jwma.folder");
	JwmaError error=null;
    Object o=session.getValue("jwma.error");
	if (o!=null) {
		error=(JwmaError) o;
    }
	String sorthandler="onChange=\"submitSort(this.form)\"";
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
	<script type="text/javascript">
	<!--
		function submitDelete(aform,onwhat) {
			aform.acton.value=onwhat;
			aform.todo.value="delete";
			aform.submit();
		}
		function submitMove(aform,onwhat){
			aform.acton.value=onwhat;
			aform.todo.value="move";
    		aform.submit();
		}
		function submitCreate(aform) {
			aform.acton.value="folder";
			aform.todo.value="create";
			aform.submit();
		}
		function submitSort(aform) {
			aform.acton.value="folder";
			aform.todo.value="sortmessages";
			aform.submit();
		}

	// -->
	</script>
</head>
<body bgcolor="#ffffff" link="#666666" vlink="#666666" alink="#ffffff">

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
<%-- Display inlined error --%>
<% if (error!=null && !error.isDisplayed()) {
    String[] errors=error.getDescriptions();
    for (int i=0;i<errors.length;i++) {
%>
	<font size="+1" color="#ff0000"><b><fmt:message key="<%= errors[i] %>" /></b></font><br>
<%    error.setDisplayed(true);
    }
 }
%>
<%-- End Display inlined error --%>

<%-- Include main view part respecting the store mode --%>
<br>
<% if (store.isMixedMode()) { %>
	<%@ include file="mixed_view.jsp" %>
<% } else { %>	
	<%@ include file="distinct_view.jsp" %> 
<% } %>
<%--End main view part --%>

<%-- Trash status information --%>
<br>
<table width="90%">
  <tr>
    <td width="100%"align="right">
      <%	if (trash.isEmpty()) { %>
        <img border="0" src="images/trash_empty.png" alt="<fmt:message key="trashbin.empty"/>">
      <% } else { %>
        <a href="<%= htmlhelper.getFolderDisplayAction(trash) %>">
          <img border="0" src="images/trash_full.png" alt="<fmt:message key="trashbin.full"/>">
        </a>
      <% } %>
    </td>
  </tr>
</table>
<br>
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
