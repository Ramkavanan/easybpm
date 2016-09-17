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
             resourceClassLoader="jwma.resourceloader" id="errorbundle" />
 --%>
<%-- Prepare references for use in the page --%>
<%
	JwmaHtmlHelper htmlhelper=(JwmaHtmlHelper) session.getValue("jwma.htmlhelper");
	JwmaInboxInfo inbox=(JwmaInboxInfo) session.getValue("jwma.inboxinfo");
	JwmaMessage message=(JwmaMessage) session.getValue("jwma.composemessage");
	JwmaPreferences prefs=(JwmaPreferences) session.getValue("jwma.preferences");
  JwmaContacts ctdb=(JwmaContacts) session.getValue("jwma.contacts");
	String inserthandler="onChange=\"this.form.to.value=this.value;\"";
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
</head>
<body bgcolor="#ffffff" link="#666666" vlink="#666666" alink="#FFFFFF">
	<script type="text/javascript">

	    function submitSend(aform) {
				aform.savedraft.value="false";
				$("#compose").submit();
				//aform.submit();
			}
	
	    function submitSave(aform) {
	      aform.savedraft.value="true";
	      aform.submit();
	    }

	</script>
<%-- Header & Menu --%>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
	    <td height="36" width="80%"> <img src="images/inbox.png" alt="Inbox" width="36" height="36" align="middle">
            <font face="Arial,Helvetica" size="-1">
              <b><a href="<%= htmlhelper.getFolderDisplayAction(inbox) %>"><fmt:message key="inbox.name"/></a></b>
              <em>
                <fmt:message key="inbox.status">
                  <%-- New messages based on the new flag
                      <fmt:messageArg value="<%= new Integer(inbox.getNewMessageCount()) %>" />
                  --%>
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
          <img src="images/compose_small.png" width="20" height="20" border="0" alt="<fmt:message key="menu.compose"/>">
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
          <font face="Arial, Helvetica, sans-serif" size="-1"><b><fmt:message key="menu.compose"/></b></font>
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
<%
  if (error!=null && !error.isDisplayed()) {
    String[] errors=error.getDescriptions();
    for (int i=0;i<errors.length;i++) {
%>
      <font size="+1" color="#ff0000"><b><fmt:message key="<%= errors[i] %>"/></b></font><br>
<%    error.setDisplayed(true);
    }
  }
%>
<%-- End Display inlined error --%>
<br><br>
<form id="compose" method="post" enctype="multipart/form-data" action="bpm/mail/jwma/sendMail" onSubmit="_execute('target','')">
  <input type="hidden" name="savedraft">
  <table width="90%" border="0" cellspacing="1" cellpadding="0">
    <tr>
      <td colspan="2" bgcolor="#000000" height="20">
        <img src="images/compose_small.png" width="20" height="20" alt="Compose">
        <font face="Arial, Helvetica, sans-serif" color="#FFFFFF">
          <b><fmt:message key="compose.header"/></b>
        </font>
      </td>
    </tr>
    <tr>
      <td width="17%" bgcolor="#DDDDDD">
        <font face="Arial, Helvetica, sans-serif">
          <b><fmt:message key="message.to"/></b>
        </font>
      </td>
      <td width="82%" bgcolor="#EEEEEE">
        <input type="text" name="to" size="50" value="<%= message.getTo() %>">
        <%= htmlhelper.getFrequentSelect(ctdb,inserthandler) %>
      </td>
    </tr>
    <tr>
      <td width="17%" bgcolor="#DDDDDD">
        <font face="Arial, Helvetica, sans-serif">
          <b><fmt:message key="compose.cc"/></b>
        </font>
      </td>
      <td width="82%" bgcolor="#EEEEEE">
        <input type="text" name="ccto" size="50" value="<%= message.getCCTo() %>">
      </td>
    </tr>
    <tr>
      <td width="17%" bgcolor="#DDDDDD">
        <font face="Arial, Helvetica, sans-serif">
          <b><fmt:message key="compose.bcc"/></b>
        </font>
      </td>
      <td width="82%" bgcolor="#EEEEEE">
        <input type="text" name="bccto" size="50" value="<%= message.getBCCTo() %>">
      </td>
    </tr>
    <tr>
      <td width="17%" bgcolor="#DDDDDD">
        <font face="Arial, Helvetica, sans-serif">
          <b><fmt:message key="compose.attachments"/></b>
        </font>
      </td>
      <td width="82%" bgcolor="#EEEEEE">
        <input type="file" name="attachment" size="36">
      </td>
    </tr>
    <tr>
      <td width="17%" bgcolor="#DDDDDD">
        <font face="Arial, Helvetica, sans-serif">
          <b><fmt:message key="message.subject"/></b>
        </font>
      </td>
      <td width="82%" bgcolor="#EEEEEE">
        <input type="text" name="subject" size="50" value="<%= message.getSubject() %>">
      </td>
    </tr>
    <tr>
      <td colspan="2" bgcolor="#EEEEEE"><br>
        <textarea name="body" cols="80" rows="25"><%= message.getBody() %></textarea>
      </td>
    </tr>
    <tr>
      <td colspan="2" bgcolor="#DDDDDD">
      <% if(message.isMultipart()) {
              JwmaMessagePart[] parts=message.getMessageParts();
              for (int i=0;i<parts.length;i++) {
                if (prefs.isDisplayingInlined()) {
      %>
      <%= htmlhelper.displayPartInlined(session,parts[i],prefs) %>
      <%        } else { %>
      <%= htmlhelper.getPartDescription(parts[i]) %>
      <%
                }
              }
         }
		   %>
      </td>
    </tr>
    <tr>
      <td colspan="2" bgcolor="#000000" height="20">
        <font color="#FFFFFF" face="Arial, Helvetica, sans-serif" size="-1">
          <fmt:message key="compose.mailidentity"/>
        </font>
        <%= htmlhelper.getMailIdentitySelect(prefs) %>
      </td>
    </tr>
    <tr>
      <td colspan="2" bgcolor="#000000" height="20">
        <input type="checkbox" name="toggleautosign" value="true">
        <font face="Arial, Helvetica, sans-serif" color="#ffffff" size="-1">
          <fmt:message key="compose.toggle.autosign"/>
        </font>
        &nbsp;&nbsp;<input type="checkbox" name="togglerndappend" value="true">
        <font face="Arial, Helvetica, sans-serif" color="#ffffff" size="-1">
          <fmt:message key="compose.toggle.randomappend"/>
        </font>
      </td>
    </tr>
    <tr align="right">
      <td colspan="2" bgcolor="#000000" height="20">
        <input type="reset" value="<fmt:message key="form.undo"/>" name="reset">&nbsp;&nbsp;
        <input type="button" value="<fmt:message key="compose.savedraft"/>" name="send" onClick="submitSave(this.form);">&nbsp;&nbsp;
        <input type="button" value="<fmt:message key="compose.sendmail"/>" name="save" onClick="submitSend(this.form);">
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
