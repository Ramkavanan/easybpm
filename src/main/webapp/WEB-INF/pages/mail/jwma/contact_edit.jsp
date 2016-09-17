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

    function submitDone(form) {
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

<%-- Display Contact Form --%>
<form method="post" action="<%= htmlhelper.getContactsControllerUrl() %>">
	<input type="hidden" name="acton" value="contact">
  <input type="hidden" name="todo" value="update">
  <input type="hidden" name="contact.id" value="<%= contact.getUID() %>">
    <br>

    <table cellpadding="3" cellspacing="0" border="0" width="90%">
      <tr>
        <td align="left" bgcolor="#000000" width="80%" nowrap>
          <font face="Arial,Helvetica" color="#ffffff"><b><i18n:message key="contact.edit"/></b></font>
        </td>
        <td align="right" bgcolor="#000000" width="20%" nowrap>
							<font face="Arial,Helvetica" size="-1">
								<input type="reset" value="<i18n:message key="form.undo"/>">&nbsp;&nbsp;
								<input type="submit" name="save" value="<i18n:message key="form.save"/>">&nbsp;&nbsp;
                <input type="button" name="udone" value="<i18n:message key="form.done"/>" onClick="submitDone(this.form);">
							</font>
						</td>
      </tr>
      <tr>
        <td bgcolor="#eeeeee" colspan="3">
          <table border="0" cellspacing="0" cellpadding="2" width="100%">
            <tr>
            	<td><b><i18n:message key="contact.firstname"/></b></td>
            	<td>
            	  <input type="text" size="30" name="firstname" value="<%= contact.getFirstname() %>">
            	</td>
            	<td><b><i18n:message key="contact.category"/></b></td>
							<td>
								<%= htmlhelper.getCategoriesSelect(contact,ctdb.listContactCategories(),bundle) %>
							</td>
						</tr>
						<tr>
						<td><b><i18n:message key="contact.lastname"/></b></td>
						<td>
							<input type="text" size="30" name="lastname" value="<%= contact.getLastname() %>">
						</td>
            <td><b>&nbsp;</b></td>
						<td>
							<input type="text" size="30" name="_category" value="">
						</td>
					</tr>
					<tr>
					  <td><b><i18n:message key="contact.middlename"/></b></td>
            <td>
            	<input type="text" size="30" name="middlename" value="<%= contact.getMiddlename() %>">
           	</td>
           	<td><b><i18n:message key="contact.primarylocation"/></b></td>
						<td>
							<input type=radio name="primary.location" value="false" <%= ((contact.isPrimarilyWorkContact())? "":"checked") %>>
							<i18n:message key="contact.home"/>
							<input type=radio name="primary.location" value="true" <%= ((contact.isPrimarilyWorkContact())? "checked":"") %>>
							<i18n:message key="contact.work"/>
						</td>
					</tr>
					<tr>
						<td><b><i18n:message key="contact.nickname"/></b></td>
						<td>
							<input type="text" size="30" name="nickname" value="<%= contact.getNickname() %>">
						</td>
					</tr>
					<tr>
						<td><b><i18n:message key="contact.title"/></b></td>
						<td>
							<input type="text" size="30" name="title" value="<%= contact.getTitle() %>">
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
        <img src="images/up_small.png" width="15" height="15" align="right" border="0" alt="<i18n:message key="menu.totop"/>">
      </a>
      <font face="Arial,Helvetica" color="#ffffff"><b><i18n:message key="contact.phonenumbers"/></b></font>
			</td>
		</tr>
		<tr>
			<td bgcolor="#eeeeee">
				<table border="0" cellspacing="0" cellpadding="2" width="100%">
					<tr>
						<td><b><i18n:message key="contact.home"/></b></td>
						<td>
							<input type="text" size="20" name="phone.home" value="<%= contact.getHomePhoneNumber() %>">
						</td>
						<td><b><i18n:message key="contact.fax"/></b></td>
						<td>
							<input type="text" size="20" name="phone.fax" value="<%= contact.getFaxNumber() %>">
						</td>
					</tr>
					<tr>
						<td><b><i18n:message key="contact.work"/></b></td>
						<td>
							<input type="text" size="20" name="phone.work" value="<%= contact.getWorkPhoneNumber() %>">
						</td>
						<td><b><i18n:message key="contact.pager"/></b></td>
						<td>
							<input type="text" size="20" name="phone.pager" value="<%= contact.getPagerNumber() %>">
						</td>
					</tr>
					<tr>
						<td><b><i18n:message key="contact.mobile"/></b></td>
						<td>
							<input type="text" size="20" name="phone.mobile" value="<%= contact.getMobileNumber() %>">
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
        <img src="images/up_small.png" width="15" height="15" align="right" border="0" alt="<i18n:message key="menu.totop"/>">
      </a>
      <font face="Arial,Helvetica" color="#ffffff">
					<b><i18n:message key="contact.workinfo"/></b>
				</font>
			</td>
		</tr>
		<tr>
			<td bgcolor="eeeeee">
				<table border="0" cellspacing="0" cellpadding="2" width="100%">
					<tr>
						<td><b><i18n:message key="contact.company"/></b></td>
						<td>
							<input type="text" size="30" name="company" value="<%= contact.getCompany() %>">
						</td>
					</tr>
					<tr>
						<td><b><i18n:message key="contact.role"/></b></td>
						<td>
							<input type="text" size="30" name="role" value="<%= contact.getRole() %>">
						</td>
					</tr>
					<tr>
						<td><b><i18n:message key="contact.street"/></b></td>
						<td>
							<input type="text" size="30" name="work.street" value="<%= contact.getWorkStreet() %>">
						</td>
						<td><b><i18n:message key="contact.region"/></b></td>
						<td>
							<input type="text" size="30" name="work.region" value="<%= contact.getWorkRegion() %>">
						</td>
					</tr>
					<tr>
						<td><b><i18n:message key="contact.city"/></b></td>
						<td>
							<input type="text" size="30" name="work.city" value="<%= contact.getWorkCity() %>">
						</td>
						<td><b><i18n:message key="contact.country"/></b></td>
						<td>
							<input type="text" size="30" name="work.country" value="<%= contact.getWorkCountry() %>">
						</td>
					</tr>
					<tr>
						<td><b><i18n:message key="contact.zip"/></b></td>
						<td>
							<input type="text" size="15" name="work.zip" value="<%= contact.getWorkZIP() %>">
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
        <img src="images/up_small.png" width="15" height="15" align="right" border="0" alt="<i18n:message key="menu.totop"/>">
      </a>
      <font face="Arial,Helvetica" color="#ffffff">
					<b><i18n:message key="contact.homeinfo"/></b>
				</font>
			</td>
		</tr>
		<tr>
			<td bgcolor="eeeeee">
				<table border="0" cellspacing="0" cellpadding="2" width="100%">
					<tr>
						<td><b><i18n:message key="contact.street"/></b></td>
						<td>
							<input type="text" size="30" name="home.street" value="<%= contact.getHomeStreet() %>">
						</td>
						<td><b><i18n:message key="contact.region"/></b></td>
						<td>
							<input type="text" size="30" name="home.region" value="<%= contact.getHomeRegion() %>">
						</td>
					</tr>
					<tr>
						<td><b><i18n:message key="contact.city"/></b></td>
						<td>
							<input type="text" size="30" name="home.city" value="<%= contact.getHomeCity() %>">
						</td>
						<td><b><i18n:message key="contact.country"/></b></td>
						<td>
							<input type="text" size="30" name="home.country" value="<%= contact.getHomeCountry() %>">
						</td>
					</tr>
					<tr>
						<td><b><i18n:message key="contact.zip"/></b></td>
						<td>
							<input type="text" size="15" name="home.zip" value="<%= contact.getHomeZIP() %>">
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
        <img src="images/up_small.png" width="15" height="15" align="right" border="0" alt="<i18n:message key="menu.totop"/>">
      </a>
      <font face="Arial,Helvetica" color="#ffffff">
					<b><i18n:message key="contact.internetinfo"/></b>
				</font>
			</td>
		</tr>
		<tr>
			<td bgcolor="eeeeee">
				<table border="0" cellspacing="0" cellpadding="2" width="100%">
					<tr>
						<td><b><i18n:message key="contact.email"/></b></td>
						<td>
							<input type="text" size="30" name="email.primary" value="<%= contact.getEmail() %>">
						</td>
						<td><b><i18n:message key="contact.url"/></b></td>
						<td>
							<input type="text" size="30" name="personal.url" value="<%= contact.getURL() %>">
						</td>
					</tr>
					<tr>
						<td><b><i18n:message key="contact.altemail"/></b></td>
						<td>
							<input type="text" size="30" name="email.alternate" value="<%= contact.getAlternateEmail() %>">
						</td>
						<td><b><i18n:message key="contact.alturl"/></b></td>
						<td>
							<input type="text" size="30" name="company.url" value="<%= contact.getCompanyURL() %>">
						</td>
					</tr>
					<tr>
						<td><b><i18n:message key="contact.frequentrecipient"/></b></td>
						<td>
							<input type="radio" name="frequent" value="false" <%= (contact.isFrequentRecipient())? "":"checked" %>>
							<i18n:message key="form.no"/>
							<input type="radio" name="frequent" value="true" <%= (contact.isFrequentRecipient())? "checked":"" %>>
							<i18n:message key="form.yes"/>
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
        <img src="images/up_small.png" width="15" height="15" align="right" border="0" alt="<i18n:message key="menu.totop"/>">
      </a>
      <font face="Arial,Helvetica" color="#ffffff">
					<b><i18n:message key="contact.miscinfo"/></b>
				</font>
			</td>
		</tr>
		<tr>
			<td bgcolor="eeeeee">
				<table border="0" cellspacing="0" cellpadding="2" width="100%">
					<tr>
						<td><b><i18n:message key="contact.birthdate"/></b></td>
						<td>
							<input type="text" size="30" name="birthdate" value="<%= ((contact.getBirthDate()!=null)? prefs.getDateFormat().format(contact.getBirthDate()): "") %>">
						</td>
            <td>
              <%=  ((prefs.getDateFormat() instanceof SimpleDateFormat) ?
                      ((SimpleDateFormat)prefs.getDateFormat()).toPattern() : "")
              %>
            </td>
					</tr>
					<tr>
						<td><b><i18n:message key="contact.comments"/></b></td>
						<td>
							<textarea rows="4" cols="50" wrap="virtual" name="comments"><%= contact.getComments() %></textarea>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>

	<br><br>

	<table cellpadding="3" cellspacing="1" border="0" width="90%">
		<tr bgcolor="#000000">
			<td>
				<table cellpadding="0" cellspacing="0" border="0" width="100%">
					<tr>
						<td align="right">
							<font face="Arial,Helvetica" size=-1>
								<input type="reset" value="<i18n:message key="form.undo"/>">&nbsp;&nbsp;
								<input type="submit" name="save" value="<i18n:message key="form.save"/>">&nbsp;&nbsp;
                <input type="button" name="done" value="<i18n:message key="form.done"/>" onClick="submitDone(this.form);">
							</font>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>

<%-- end display contact form --%>


</form>

<p>&nbsp;</p>
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
