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
	JwmaPreferences prefs=(JwmaPreferences) session.getValue("jwma.preferences");
	JwmaInboxInfo inbox=(JwmaInboxInfo) session.getValue("jwma.inboxinfo");
	JwmaContacts ctdb=(JwmaContacts) session.getValue("jwma.contacts");
  JwmaContactGroup group=(JwmaContactGroup) session.getValue("jwma.contacts.group");

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
	<title>jwma WebMail</title>
	<script type="text/javascript">
	<!--

    function submitDeleteGroup(form){
			form.acton.value="group";
      form.todo.value="delete";
			form.submit();
		}//submitDeleteGroup

		function submitEditGroup(form){
			form.acton.value="group";
      form.todo.value="edit";
   		form.submit();
		}//submitCreateGroup

    function submitDoneGroup(form) {
      form.acton.value="database";
      form.todo.value="display";
      form.submit();
    }//submitDoneGroup

	// -->
	</script>
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
          <a href="<%= htmlhelper.getContactsControllerUrl() %>?acton=database&amp;todo=display">
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
           <a href="<%= htmlhelper.getContactsControllerUrl() %>?acton=database&amp;todo=display">
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

<%-- Display Contact Group --%>
<form method="post" action="<%= htmlhelper.getContactsControllerUrl() %>">
	<input type="hidden" name="acton" value="">
	<input type="hidden" name="todo" value="">
  <input type="hidden" name="group.id" value="<%= group.getUID() %>">
    <br>
    <table width="95%" cellpadding="1" cellspacing="0" border="0">
        <tr valign="bottom">
            <td bgcolor="#000000" colspan="2" width="80%" nowrap>
              <img src="images/addresses_small.png" width="29" height="20" alt="<i18n:message key="contacts.group"/>">
               <font face="Arial,Helvetica" color="#FFFFFF"><b><%= group.getName() %></b></font>
            </td>
            <td bgcolor="#000000" width="20%" nowrap>
                <input type="button" name="delete" value="<i18n:message key="contacts.delete.group"/>" onClick="submitDeleteGroup(this.form);">&nbsp;&nbsp;
                <input type="button" name="edit" value="<i18n:message key="contacts.edit.group"/>" onClick="submitEditGroup(this.form);">&nbsp;&nbsp;
                <input type="button" name="done" value="<i18n:message key="form.done"/>" onClick="submitDoneGroup(this.form);">
             </td>
        </tr>
        <tr bgcolor="#dadada">
            <td nowrap width="40%">
              <font color="#000000" face="Arial,Helvetica" size="-1">
                <b><i18n:message key="contacts.fullname"/></b>
              </font>
            </td>
            <td nowrap width="40%">
              <font color="#000000" face="Arial,Helvetica" size="-1"><b><i18n:message key="contacts.email"/></b></font>
            </td>
            <td nowrap width="20%">
              <font color="#000000" face="Arial,Helvetica" size="-1"><b><i18n:message key="contacts.phone"/></b></font>
            </td>
        </tr>
        <%
            JwmaContact[] contacts=group.listContacts();
            if(contacts==null || contacts.length==0) {
            //empty message
		    %>
        <tr>
            <td bgcolor="#eeeeee" colspan="3">
              <i><i18n:message key="group.nocontacts"/></i>
            </td>
        </tr>
        <%  } else {
                //loop over entries
                for (int index=0;index<contacts.length;index++) {
                JwmaContact contact=contacts[index];
        %>
        <tr>
            <td bgcolor="#eeeeee" nowrap width="40%">
              <font face="Arial,Helvetica" size="-1">
                <a href="<%= htmlhelper.getContactsControllerUrl() %>?acton=contact&amp;todo=display&amp;contact.id=<%= contact.getUID() %>">
                  <em><%= contact.getLastname() %>, <%= contact.getFirstname() %></em>
                </a>
              </font>
            </td>
            <td bgcolor="#eeeeee" nowrap width="40%">
              <font face="Arial,Helvetica" size="-1">
                <a href="<%= htmlhelper.getControllerUrl() %>?acton=message&amp;todo=compose&amp;to=<%= contact.getEmail() %>">
                  <%= contact.getEmail() %>
                </a>
              </font>
            </td>
            <td bgcolor="#eeeeee" width="20%">
              <% if (contact.isPrimarilyWorkContact()) { %>
                <%= contact.getWorkPhoneNumber() %>
              <% } else { %>
                <%= contact.getHomePhoneNumber() %>
              <% } %>
            </td>
        </tr>
        <%
				}//for end
			}//else end
		 %>
        <tr align="right">
            <td colspan="3" bgcolor="#000000">
                <input type="button" name="delete" value="<i18n:message key="contacts.delete.group"/>" onClick="submitDeleteGroup(this.form);">&nbsp;&nbsp;
                <input type="button" name="edit" value="<i18n:message key="contacts.edit.group"/>" onClick="submitEditGroup(this.form);">&nbsp;&nbsp;
                <input type="button" name="done" value="<i18n:message key="form.done"/>" onClick="submitDoneGroup(this.form);">
             </td>
        </tr>
    </table>
</form>
<%-- end display contacts --%>

<br><br>

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
