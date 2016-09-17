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
  String eventhandler="onChange=\"submitSetCategoryFilter(this.form);\"";

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
	<script type="text/javascript">
	<!--

    function submitAddContacts(form){
			form.acton.value="group";
      form.todo.value="addcontacts";
			form.submit();
		}//submitAddContacts

    function submitRemoveContacts(form){
			form.acton.value="group";
      form.todo.value="removecontacts";
			form.submit();
		}//submitAddContacts

		function submitUpdateGroup(form){
			form.acton.value="group";
      form.todo.value="update";
   		form.submit();
		}//submitCreate

    function submitDone(form) {
      form.acton.value="database"
      form.todo.value="display";
      form.submit();
    }//submitDone

    function submitSetCategoryFilter(form) {
      form.acton.value="database";
      form.todo.value="setfilter";
      form.filtertype.value="category";
      form.submit();
    }//submitSetCategoryFilter

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

<%-- Display Contact Group Form --%>
<form method="post" action="<%= htmlhelper.getContactsControllerUrl() %>">
	<input type="hidden" name="acton" value="">
	<input type="hidden" name="todo" value="">
  <input type="hidden" name="group.id" value="<%= group.getUID() %>">
  <input type="hidden" name="filtertype" value="">
    <br>
    <table width="90%" cellpadding="1" cellspacing="0" border="0">
        <tr valign="bottom">
            <td bgcolor="#000000">
              <img src="images/addresses_small.png" width="29" height="20" alt="<i18n:message key="contacts.group"/>">
               <font face="Arial,Helvetica" color="#FFFFFF"><b><i18n:message key="contacts.edit.group"/></b></font>
            </td>
            <td align="right" bgcolor="#000000">
            <font face="Arial,Helvetica" size="-1">
              <input type="reset" value="<i18n:message key="form.undo"/>">&nbsp;&nbsp;
              <input type="button" name="update" value="<i18n:message key="form.update"/>" onClick="submitUpdateGroup(this.form);">&nbsp;&nbsp;
              <input type="button" name="done" value="<i18n:message key="form.done"/>" onClick="submitDone(this.form);">
            </font>
				  </td>
        </tr>
        <tr bgcolor="#eeeeee">
            <td width="10%" nowrap>
              <font color="#000000" face="Arial,Helvetica" size="-1">
                <b><i18n:message key="contacts.group.name"/></b>
              </font>
            </td>
            <td width="90%" nowrap align="left">
              <input type="text" size="40" name="group.name" value="<%= group.getName() %>">
            </td>
        </tr>
        <tr bgcolor="#eeeeee">
            <td  valign="top" width="10%" nowrap>
              <font color="#000000" face="Arial,Helvetica" size="-1">
                <b><i18n:message key="form.comments"/></b>
              </font>
            </td>
            <td width="90%" nowrap align="left">
              <textarea rows="3" cols="40" wrap="virtual" name="group.comments"><%= group.getComments() %></textarea>
            </td>
        </tr>
        <tr>
          <td align="right" valign="bottom" bgcolor="#000000" colspan="2" nowrap>
            <font face="Arial,Helvetica" color="#ffffff" size="-1">
              <b><%= htmlhelper.getAlphabeticFilter(ctdb,bundle) %></b>
            </font>
            &nbsp;&nbsp;
            <%= htmlhelper.getCategoryFilterSelect(ctdb,eventhandler,bundle) %>
          </td>
			  </tr>
        <tr>
          <td colspan="2">
            <table border="0" width="100%" cellpadding="5" cellspacing="0" border="0">
              <tr>
                <td bgcolor="#dadada"><font face="Arial,Helvetica" color="#000000" size="-1"><b><i18n:message key="group.nonmembers"/></b></font></td>
                <td bgcolor="#dadada">&nbsp;</td>
                <td bgcolor="#dadada"><font face="Arial,Helvetica" color="#000000" size="-1"><b><i18n:message key="group.members"/></b></font></td>
              </tr>
              <tr>
                <td bgcolor="#eeeeee" width="40%"><%= htmlhelper.getNonMembersSelect(group,ctdb) %></td>
                <td valign="middle" align="center" bgcolor="#eeeeee" width="10%">
                  <input type="button" name="addcontacts" value="<i18n:message key="group.add"/>" onClick="submitAddContacts(this.form);">
                   <br><br>
                  <input type="button" name="removecontacts" value="<i18n:message key="group.remove"/>" onClick="submitRemoveContacts(this.form);">
                </td>
                <td bgcolor="#eeeeee" width="40%"><%= htmlhelper.getGroupMembersSelect(group) %></td>
              </tr>
            </table>
          </td>
        </tr>
        <tr>
            <td colspan="3" bgcolor="#000000" height="20">&nbsp;</td>
        </tr>
    </table>
</form>
<%-- end edit contact group --%>

<br>

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
