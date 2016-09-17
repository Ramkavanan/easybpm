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
	JwmaInboxInfo inbox=(JwmaInboxInfo) session.getValue("jwma.inboxinfo");
	JwmaContacts ctdb=(JwmaContacts) session.getValue("jwma.contacts");
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
		function submitDeleteContact(form){
			form.acton.value="contact";
      form.todo.value="delete";
			form.submit();
		}//submitDelete

		function submitCreateContact(form){
			form.acton.value="contact";
      form.todo.value="create";
   		form.submit();
		}//submitCreate

    function submitDeleteGroup(form){
			form.acton.value="group";
      form.todo.value="delete";
			form.submit();
		}//submitDelete

		function submitCreateGroup(form){
			form.acton.value="group";
      form.todo.value="create";
   		form.submit();
		}//submitCreate

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
	<font size="+1" color="#ff0000"><b><fmt:message key="<%= errors[i] %>"/></b></font><br>
<%    error.setDisplayed(true);
    }
 }
%>
<%-- End Display inlined error --%>

<%-- Display Contacts --%>
<form method="post" action="<%= htmlhelper.getContactsControllerUrl() %>">
	<input type="hidden" name="acton" value="">
	<input type="hidden" name="todo" value="">
  <input type="hidden" name="filtertype" value="">
    <br>
    <table width="95%" cellpadding="1" cellspacing="1" border="0">
        <tr valign="bottom">
            <td bgcolor="#000000" colspan="2">
              <img src="images/address_small.png" width="29" height="20" alt="<fmt:message key="contacts.contacts"/>">
               <font face="Arial,Helvetica" color="#FFFFFF"><b><fmt:message key="contacts.contacts"/></b></font>
            </td>
            <td align="right" valign="bottom" bgcolor="#000000" nowrap>
              <font face="Arial,Helvetica" color="#ffffff" size="-1">
              <b><%= htmlhelper.getAlphabeticFilter(ctdb) %></b>
              </font>
              &nbsp;&nbsp;
              <%= htmlhelper.getCategoryFilterSelect(ctdb,eventhandler) %>
            </td>
        </tr>
        <tr bgcolor="#dadada">
            <td nowrap width="5%">
              <font color="#000000" face="Arial,Helvetica"><b>#</b></font>
            </td>
            <td nowrap width="50%">
              <font color="#000000" face="Arial,Helvetica">
                <b><fmt:message key="contacts.fullname"/></b>
              </font>
            </td>
            <td nowrap width="45%">
              <font color="#000000" face="Arial,Helvetica"><b><fmt:message key="contacts.email"/></b></font>
            </td>
        </tr>
        <%
            JwmaContact[] contacts=ctdb.listContacts();
            if(contacts==null || contacts.length==0) {
            //empty message
		    %>
        <tr>
            <td bgcolor="#eeeeee" colspan="3">
              <i><fmt:message key="contacts.nocontact"/></i>
            </td>
        </tr>
        <%  } else {
                //loop over entries
                for (int index=0;index<contacts.length;index++) {
                JwmaContact contact=contacts[index];
        %>
        <tr> 
            <td bgcolor="#eeeeee" width="5%">
                <input type="checkbox" name="contact.id" value="<%= contact.getUID() %>">
            </td>
            <td bgcolor="#eeeeee" nowrap width="50%">
              <font face="Arial,Helvetica" size="-1">
                <a href="<%= htmlhelper.getContactsControllerUrl() %>?acton=contact&amp;todo=display&amp;contact.id=<%= contact.getUID() %>">
                  <em><%= contact.getLastname() %>, <%= contact.getFirstname() %></em>
                </a>
              </font>
            </td>
            <td bgcolor="#eeeeee" nowrap width="45%">
              <font face="Arial,Helvetica" size="-1">
                <a href="<%= htmlhelper.getControllerUrl() %>?acton=message&amp;todo=compose&amp;to=<%= contact.getEmail() %>">
                  <%= contact.getEmail() %>
                </a>
              </font>
            </td>
        </tr>
        <%
				}//for end
			}//else end
		 %> 
        <tr> 
            <td bgcolor="#000000" height="3" width="5%">&nbsp;</td>
            <td bgcolor="#000000" height="3" width="50%">&nbsp;</td>
            <td bgcolor="#000000" height="3" width="45%">&nbsp;</td>
        </tr>
        <%-- Fast create row --%>
        <tr>
            <td bgcolor="#eeeeee" width="5%"><i><fmt:message key="form.add"/></i></td>
            <td bgcolor="#eeeeee" nowrap width="50%">
                <input type="text" name="lastname" size="20">
                ,
                <input type="text" name="firstname" size="20">
            </td>
            <td bgcolor="#eeeeee" nowrap width="45%">
                <input type="text" name="email.primary" size="35">
            </td>
        </tr>
        <tr align="right">
            <td colspan="3" bgcolor="#000000">
                <input type="button" name="delete" value="<fmt:message key="contacts.delete.contacts"/>" onClick="submitDeleteContact(this.form);">
                <input type="button" name="create" value="<fmt:message key="contacts.create.contact"/>" onClick="submitCreateContact(this.form);">
            </td>
        </tr>
    </table>
<%-- end display contacts --%>

<%-- Display ContactGroups --%>
    <p>&nbsp;</p>
    <table width="95%" cellpadding="1" cellspacing="1" border="0">
        <tr valign="bottom">
            <td bgcolor="#000000" colspan="4">
              <img src="images/addresses_small.png" width="20" height="20" alt="<fmt:message key="contacts.groups"/>">
              <font face="Arial,Helvetica" color="#FFFFFF"><b><fmt:message key="contacts.groups"/></b></font>
            </td>
        </tr>
        <tr bgcolor="#dadada">
            <td nowrap width="5%"> <font color="#000000" face="Arial,Helvetica"><b>#</b></font>
            </td>
            <td nowrap width="30%"> <font color="#000000" face="Arial,Helvetica"><b><fmt:message key="contacts.group.name"/></b></font>
            </td>
            <td nowrap width="60%"> <font color="#000000" face="Arial,Helvetica"><b><fmt:message key="form.comments"/></b></font>
            </td>
             <td nowrap width="5%">&nbsp;</td>
        </tr>
        <%
            JwmaContactGroup[] groups=ctdb.listContactGroups();
            if(groups==null || groups.length==0) {
            //empty message
        %>
        <tr>
            <td bgcolor="#eeeeee" colspan="3"><i><fmt:message key="contacts.nogroup"/></i></td>
        </tr>
        <%  } else {
              //loop over entries
              for (int index=0;index<groups.length;index++) {
                JwmaContactGroup group=groups[index];
        %>
        <tr>
            <td bgcolor="#eeeeee" width="5%">
                <input type="checkbox" name="group.id" value="<%= group.getUID() %>">
            </td>
            <td bgcolor="#eeeeee" nowrap width="30%">
              <font face="Arial,Helvetica" size="-1">
                <a href="<%= htmlhelper.getContactsControllerUrl() %>?acton=group&amp;todo=display&amp;group.id=<%= group.getUID() %>">
                  <em><%= group.getName() %></em>
                </a>
              </font>
            </td>
            <td bgcolor="#eeeeee" nowrap width="60%">
              <font face="Arial,Helvetica" size="-1">
                <%= group.getComments() %>
              </font>
            </td>
            <td bgcolor="#eeeeee" align="center" valign="middle">
              <a href="<%= htmlhelper.getControllerUrl() %>?acton=message&amp;todo=compose&amp;to=<%= group.getName() %>:;">
                <font face="Arial, Helvetica" size="-2"><fmt:message key="message.to"/></font>
              </a>
            </td>
       </tr>
       <%
             }//for end
           }//else end
       %>
       <tr>
           <td bgcolor="#000000" height="3" width="5%">&nbsp;</td>
           <td bgcolor="#000000" height="3" width="30%">&nbsp;</td>
           <td bgcolor="#000000" height="3" width="60%">&nbsp;</td>
           <td bgcolor="#000000" height="3" width="5%">&nbsp;</td>
       </tr>
        <%-- Fast create row --%>
        <tr>
            <td bgcolor="#eeeeee" width="5%" align="left" valign="top"> <i> <fmt:message key="form.add"/></i></td>
            <td bgcolor="#eeeeee" nowrap width="30%" align="left" valign="top">
                <input type="text" name="group.name" size="30">
            </td>
            <td bgcolor="#eeeeee" nowrap width="60%" align="left" valign="top">
                <input type="text" name="group.comments" size="50">
            </td>
            <td bgcolor="#eeeeee" nowrap width="5%">&nbsp;</td>
        </tr>
        <tr align="right">
            <td colspan="4" bgcolor="#000000">
                <input type="button" name="delete" value="<fmt:message key="contacts.delete.groups"/>" onClick="submitDeleteGroup(this.form);">
                <input type="button" name="create" value="<fmt:message key="contacts.create.group"/>" onclick="submitCreateGroup(this.form);">
            </td>
        </tr>
    </table>
</form>

<p>&nbsp;</p>
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
