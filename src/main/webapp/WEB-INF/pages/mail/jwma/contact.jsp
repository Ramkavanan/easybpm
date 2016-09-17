<%@ page session="true" import="dtw.webmail.model.*,
                                java.text.SimpleDateFormat" %>

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
  JwmaContact contact=(JwmaContact) session.getValue("jwma.contacts.contact");

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
		}//submitDeleteContact

		function submitEditContact(form){
      form.acton.value="contact";
      form.todo.value="edit";
   		form.submit();
		}//submitEditContact

    function submitDoneContact(form) {
      form.acton.value="database";
      form.todo.value="display";
      form.submit();
    }//submitDoneContact

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

<%-- Display Contact --%>
<br>
 <form method="post" action="<%= htmlhelper.getContactsControllerUrl() %>">
  <table cellpadding="3" cellspacing="0" border="0" width="90%">
    <tr>
      <td height="20" width="60%" align="left" bgcolor="#000000" nowrap>
        <font face="Arial,Helvetica" color="#ffffff"><b>
          <%= contact.getLastname() %>, <%= contact.getFirstname() %> <%= contact.getMiddlename() %>
          &nbsp;&nbsp;
          <% if (contact.isPrimarilyWorkContact()) { %>
            [<fmt:message key="contact.work"/>
          <% } else { %>
            [<fmt:message key="contact.home"/>
          <% } %>
          <% if (contact.isFrequentRecipient()) { %>
            , <fmt:message key="contact.frequentrecipient"/>]
          <% } else { %>
            ]
          <% } %>
        </b></font>
		  </td>
      <td align="right" height="20" width="40%" bgcolor="#000000" nowrap>
        <font face="Arial,Helvetica" size=-1>
          <input type="button" name="udelete" value="<fmt:message key="contacts.delete.contact"/>" onClick="submitDeleteContact(this.form);">&nbsp;&nbsp;
					<input type="button" name="uedit" value="<fmt:message key="contacts.edit.contact"/>" onClick="submitEditContact(this.form);">&nbsp;&nbsp;
          <input type="button" name="udone" value="<fmt:message key="form.done"/>" onClick="submitDoneContact(this.form);">
        </font>
			</td>
    </tr>
    <tr>
      <td bgcolor="#eeeeee" valign="top" align="left">
        <table border="0" cellspacing="0" cellpadding="2" width="60%">
          <tr>
              <td width="10%"><b><fmt:message key="contact.nickname"/>:</b></td>
              <td width="90%"><%= contact.getNickname() %></td>
          </tr>
          <tr>
              <td width="10%"><b><fmt:message key="contact.title"/>:</b></td>
              <td width="90%"><%= contact.getTitle() %></td>
          </tr>
          <tr>
              <td width="10%"><b><fmt:message key="contact.category"/>:</b></td>
							<td width="90%"><%= contact.getCategory() %></td>
					</tr>
          <tr>
              <td width="10%"><b><fmt:message key="contact.birthdate"/>:</b></td>
							<td width="90%"><%= ((contact.getBirthDate()!=null)? prefs.getDateFormat().format(contact.getBirthDate()): "") %></td>
					</tr>
        </table>
      </td>
      <td bgcolor="#eeeeee" valign="top" align="left">
				<table border="0" cellspacing="0" cellpadding="2" width="40%">
					<tr>
						<td width="10%"><b><fmt:message key="contact.home"/>:</b></td>
						<td width="90%"><%= contact.getHomePhoneNumber() %></td>
					</tr>
					<tr>
            <td width="10%"><b><fmt:message key="contact.fax"/>:</b></td>
						<td width="90%"><%= contact.getFaxNumber() %></td>
					</tr>
					<tr>
						<td width="10%"><b><fmt:message key="contact.work"/>:</b></td>
						<td width="90%"><%= contact.getWorkPhoneNumber() %></td>
					</tr>
          <tr>
						<td width="10%"><b><fmt:message key="contact.pager"/>:</b></td>
						<td width="90%"><%= contact.getPagerNumber() %></td>
					</tr>
					<tr>
						<td width="10%"><b><fmt:message key="contact.mobile"/>:</b></td>
						<td width="90%"><%= contact.getMobileNumber() %></td>
					</tr>
				</table>
      </td>
    </tr>
  </table>
  <br>
  <br>

	<table cellpadding="3" cellspacing="1" border="0" width="90%">
		<tr>
			<td align="left" bgcolor="#000000">
			 <a href="#top">
        <img src="images/up_small.png" width="15" height="15" align="right" border="0" alt="<fmt:message key="menu.totop"/>">
      </a>
      <font face="Arial,Helvetica" color="#ffffff">
					<b><fmt:message key="contact.workinfo"/></b>
				</font>
			</td>
		</tr>
		<tr>
			<td valign="top" align="left" bgcolor="eeeeee">
				<table border="0" cellspacing="0" cellpadding="2" width="100%">
					<tr>
						<td width="10%"><b><fmt:message key="contact.company"/>:</b></td>
						<td width="90%" colspan="3"><%= contact.getCompany() %></td>
					</tr>
					<tr>
						<td width="10%"><b><fmt:message key="contact.role"/>:</b></td>
						<td width="90%" colspan="3"><%= contact.getRole() %></td>
					</tr>
					<tr>
						<td width="10%"><b><fmt:message key="contact.street"/>:</b></td>
						<td width="40%"><%= contact.getWorkStreet() %></td>
						<td width="10%"><b><fmt:message key="contact.region"/>:</b></td>
						<td width="40%"><%= contact.getWorkRegion() %></td>
					</tr>
					<tr>
						<td width="10%"><b><fmt:message key="contact.city"/>:</b></td>
						<td width="40%"><%= contact.getWorkCity() %></td>
						<td width="10%"><b><fmt:message key="contact.country"/>:</b></td>
						<td width="40%"><%= contact.getWorkCountry() %></td>
					</tr>
					<tr>
						<td width="10%"><b><fmt:message key="contact.zip"/></b></td>
						<td width="90%" colspan="3"><%= contact.getWorkZIP() %></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>

	<br><br>

	<table cellpadding="3" cellspacing="1" border="0" width="90%">
		<tr>
			<td align="left" bgcolor="#000000">
			 <a href="#top">
        <img src="images/up_small.png" width="15" height="15" align="right" border="0" alt="<fmt:message key="menu.totop"/>">
      </a>
      <font face="Arial,Helvetica" color="#ffffff">
					<b><fmt:message key="contact.homeinfo"/></b>
				</font>
			</td>
		</tr>
		<tr>
			<td valign="top" align="left" bgcolor="eeeeee">
				<table border="0" cellspacing="0" cellpadding="2" width="100%">
					<tr>
						<td width="10%"><b><fmt:message key="contact.street"/>:</b></td>
						<td width="40%"><%= contact.getHomeStreet() %></td>
						<td width="10%"><b><fmt:message key="contact.region"/>:</b></td>
						<td width="40%"><%= contact.getHomeRegion() %></td>
					</tr>
					<tr>
						<td width="10%"><b><fmt:message key="contact.city"/>:</b></td>
						<td width="40%"><%= contact.getHomeCity() %></td>
						<td width="10%"><b><fmt:message key="contact.country"/>:</b></td>
						<td width="40%"><%= contact.getHomeCountry() %></td>
					</tr>
					<tr>
						<td width="10%"><b><fmt:message key="contact.zip"/>:</b></td>
						<td width="90%" colspan="3"><%= contact.getHomeZIP() %></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>

	<br><br>

	<table cellpadding="3" cellspacing="1" border="0" width="90%">
		<tr>
			<td align="left" bgcolor="#000000">
			 <a href="#top">
        <img src="images/up_small.png" width="15" height="15" align="right" border="0" alt="<fmt:message key="menu.totop"/>">
      </a>
      <font face="Arial,Helvetica" color="#ffffff">
					<b><fmt:message key="contact.internetinfo"/></b>
				</font>
			</td>
		</tr>
		<tr>
			<td bgcolor="eeeeee">
				<table border="0" cellspacing="0" cellpadding="2" width="100%">
					<tr>
						<td width="10%" nowrap><b><fmt:message key="contact.email"/>:</b></td>
						<td width="40%">
              <a href="<%= htmlhelper.getControllerUrl() %>?acton=message&amp;todo=compose&amp;to=<%= contact.getEmail() %>">
                <font size="-1" face="Arial,Helvetica"><%= contact.getEmail() %></font>
              </a>
            </td>
						<td width="10%" nowrap><b><fmt:message key="contact.url"/>:</b></td>
						<td width="40%">
               <a href="<%= contact.getURL() %>" target="_new">
                <font size="-1" face="Arial,Helvetica"><%= contact.getURL() %></font>
               </a>
            </td>
					</tr>
					<tr>
						<td width="10%" nowrap><b><fmt:message key="contact.altemail"/>:</b></td>
						<td width="40%">
              <a href="<%= htmlhelper.getControllerUrl() %>?acton=message&amp;todo=compose&amp;to=<%= contact.getAlternateEmail() %>">
                <font size="-1" face="Arial,Helvetica"><%= contact.getAlternateEmail() %></font>
              </a>
            </td>
						<td width="10%" nowrap><b><fmt:message key="contact.alturl"/>:</b></td>
						<td width="40%">
              <a href="<%= contact.getCompanyURL() %>">
                <font size="-1" face="Arial,Helvetica"><%= contact.getCompanyURL() %></font>
              </a>
            </td>
					</tr>
				</table>
			</td>
		</tr>
	</table>

	<br><br>

	<table cellpadding="3" cellspacing="1" border="0" width="90%">
		<tr>
			<td align="left" bgcolor="#000000">
			 <a href="#top">
        <img src="images/up_small.png" width="15" height="15" align="right" border="0" alt="<fmt:message key="menu.totop"/>">
      </a>
      <font face="Arial,Helvetica" color="#ffffff">
					<b><fmt:message key="contact.comments"/></b>
				</font>
			</td>
		</tr>
		<tr>
			<td bgcolor="eeeeee">
        <%= contact.getComments() %>
			</td>
	  </tr>
	</table>


  <input type="hidden" name="acton" value="">
	<input type="hidden" name="todo" value="">
  <input type="hidden" name="contact.id" value="<%= contact.getUID() %>">
  	<table cellpadding="3" cellspacing="1" border="0" width="90%">
		  <tr bgcolor="#000000">
		  	<td>
			  	<table cellpadding="0" cellspacing="0" border="0" width="100%">
				  	<tr>
					  	<td align="right" height="20" nowrap>
						  	<font face="Arial,Helvetica" size=-1>
							  	<input type="button" name="delete" value="<fmt:message key="contacts.delete.contact"/>" onClick="submitDeleteContact(this.form);">&nbsp;&nbsp;
							  	<input type="button" name="edit" value="<fmt:message key="contacts.edit.contact"/>" onClick="submitEditContact(this.form);">&nbsp;&nbsp;
                  <input type="button" name="done" value="<fmt:message key="form.done"/>" onClick="submitDoneContact(this.form);">
                  </font>
						  </td>
					  </tr>
				  </table>
			  </td>
		  </tr>
	  </table>
  </form>
<%-- end display contact --%>
<br><br>
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
