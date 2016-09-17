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

<%-- Prepare references for use in the page --%>
<%
	JwmaHtmlHelper htmlhelper=(JwmaHtmlHelper) session.getValue("jwma.htmlhelper");
    JwmaPreferences prefs=(JwmaPreferences) session.getValue("jwma.preferences");
    JwmaStoreInfo store=(JwmaStoreInfo) session.getValue("jwma.storeinfo");
	JwmaMessage message=(JwmaMessage) session.getValue("jwma.message");
	JwmaFolder folder=(JwmaFolder) session.getValue("jwma.folder");
	JwmaError error=null;
    Object o=session.getValue("jwma.error");
	if (o!=null) {
		error=(JwmaError) o;
    }
	//Prepare datestring
	String date=prefs.getDateFormat().format(message.getDate());
	if (message.isSent()) {
		date="<i>"+date+"</i>";
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

<%-- Display message --%>
<table>
  <tr bgcolor="#FFFFFF">
    <td>
      <img src="images/folder.png" width="36" height="36" alt="<i18n:message key="menu.tofolder"/>">
    </td>
    <td align="left">
      <font face="Arial,Helvetica" color="#000000">
        <b><%= htmlhelper.getPathHierarchyNavigator(store,folder)%></b>
      </font>
    </td>
  </tr>
</table>
<table border="0" width="100%" cellspacing="1" cellpadding="1">
  <tr>
    <td align="left" bgcolor="#000000" height="20">
      <img src="images/message_small.png" width="20" height="20" alt="<i18n:message key="message.message"/>">
      <font color="#FFFFFF" face="Arial, Helvetica, sans-serif">
        <b> #<%= message.getMessageNumber() %></b>
      </font>
    </td>
    <td align="right" bgcolor="#000000" height="20">
      <font color="#FFFFFF" face="Arial, Helvetica, sans-serif">
        <b> <i18n:message key="message.date"/>:<%= date %></b>
      </font>
    </td>
  </tr>
  <tr>
    <td  align=left valign=top width="20%" bgcolor="#dddddd">
      <b><i18n:message key="message.from"/>:</b>
    </td>
    <td  align=left valign=top width="80%" bgcolor="#eeeeee">
      <%= message.getFrom() %>
    </td>
  </tr>
  <tr>
    <td  align=left valign=top width="20%" bgcolor="#dddddd">
      <b><i18n:message key="message.to"/>:</b>
    </td>
    <td  align=left valign=top width="80%" bgcolor="#eeeeee">
      <%= message.getTo() %>
    </td>
  </tr>
  <tr>
    <td  align=left valign=top width="20%" bgcolor="#dddddd">
      <b><i18n:message key="message.subject"/>:</b>
    </td>
    <td  align=left valign=top width="80%" bgcolor="#eeeeee">
      <em><%= message.getSubject() %></em>
    </td>
  </tr>
    <tr> 
      <td  align="left" valign="top" colspan="2" bordercolor="#eeeeee">
        <% if (message.isSinglepart()) { %>
          <pre><%= message.getBody() %></pre>
        <% } else {
              JwmaMessagePart[] parts=message.getMessageParts();
              for (int i=0;i<parts.length;i++) {
        %>
          <%= htmlhelper.getPartDescription(parts[i], bundle) %>
        <%
              }
           }
		%>
      </td>
  </tr>
</table>
<br>
<p>
<table>
  <tr>
    <td>
      <font size="-2" face="arial,helvetica">
        <a href="message.jsp">
          <i18n:message key="message.printed"/> jwma.
        </a>
      </font>
    </td>
  </tr>
</table>
</p>
</body>
</html>
