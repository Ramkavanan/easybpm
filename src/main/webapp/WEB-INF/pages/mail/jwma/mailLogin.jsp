<%@ page session="true" import="dtw.webmail.model.*,
                                dtw.webmail.JwmaKernel,
                                dtw.webmail.util.StringUtil" %>

<%-- Ensure valid session. --%>
<%
    if(session.isNew() || session.getValue("jwma.session")==null) {	
    	response.sendRedirect(response.encodeRedirectUrl("index.jsp"));
		return;
    }
%> 
<%-- Import taglibs and i18n bundle --%>
<%@ include file="/common/taglibs.jsp"%>
<%-- <i18n:bundle baseName="viewcontent" localeAttribute="jwma.locale"
             resourceClassLoader="jwma.resourceloader" id="bundle"/>
<i18n:bundle baseName="errormessages" localeAttribute="jwma.locale"
             resourceClassLoader="jwma.resourceloader" id="errorbundle" /> --%>


<%-- Prepare references for use in the page --%>
<%
	JwmaHtmlHelper htmlhelper=(JwmaHtmlHelper) session.getValue("jwma.htmlhelper");
  boolean remember = false;
  String username = null;
  String postoffice = null;
  Cookie[] cookies = request.getCookies();
  if (cookies != null && cookies.length>0) {
    for(int i=0; i< cookies.length;i++) {
      if(cookies[i].getName().equals("jwmalogin")) {
        String[] vals= StringUtil.split(cookies[i].getValue(),".");
        username = vals[0];
        postoffice = vals[1];
        remember = true;
      }
    }
  }
%>
<% 
	Object o=session.getValue("jwma.error");
	JwmaError error=null;
    if (o!=null) {
		error=(JwmaError) o;
    }
  boolean displaypo = JwmaKernel.getReference().getConfiguration().getPostOfficeAllowOverride();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"
        "http://www.w3.org/TR/REC-html40/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> 
	<meta http-equiv="Pragma" content="no-cache">
	<title><fmt:message key="jwma.webMail"/></title>
</head>
<body bgcolor="#FFFFFF" link="#666666" vlink="#666666" alink="#FFFFFF">

<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr align="left" valign="top"> 
        <td height="19"><img src="images/logo.png" width="195" height="36" alt="jwma WebMail" align="right"></td>
    </tr>
    <tr align="left" valign="top" bgcolor="#000000"> 
        <td height="19">&nbsp;</td>
    </tr>
</table>
<%-- Display inlined error --%>
<% if (error!=null && !error.isDisplayed()) {
      String[] errors=error.getDescriptions();
      for (int i=0;i<errors.length;i++) {
%>
	<font size="+1" color="#ff0000"><b><fmt:message key="<%= errors[i] %>" /></b></font><br>
<%      error.setDisplayed(true);
      }
   }
%>
<%-- End Display inlined error --%>
<br>
<form action="bpm/mail/jwma/doMailLogin" method="post" enctype="application/x-www-form-urlencoded">
	<input type="hidden" name="acton" value="session">
	<input type="hidden" name="todo" value="login">
	<table border="0" align="center" cellpadding="1" cellspacing="1">
        <tr valign="bottom">
			<td colspan="2" bgcolor="#000000" height="20">
                <img src="images/login_small.png" width="21" height="20" align="right" alt="<fmt:message key="login.login"/>">
                <font face="Arial, Helvetica, sans-serif" size="+1" color="FFFFFF"><b><fmt:message key="login.login"/></b></font>
            </td>
		</tr>
		<tr>
			<td width="22%" bgcolor="#dddddd">
				<font face="Arial, Helvetica"><fmt:message key="login.username"/>:</font>
			</td>
			<td width="78%" bgcolor="#eeeeee">
				<input type="text" name="username" size="25" value="<%= ((remember)? username :"") %>">
			</td>
		</tr>
		<tr>
			<td width="22%" bgcolor="#dddddd">
				<font face="Arial, Helvetica"><fmt:message key="login.password"/>:</font>
			</td>
			<td width="78%" bgcolor="#eeeeee">
				<input type="password" name="password" size="25">
			</td>
		</tr>
    <% if(displaypo) { %>
      <tr>
        <td width="22%" bgcolor="#dddddd">
          <font face="Arial, Helvetica"><fmt:message key="login.postoffice"/>:</font>
        </td>
        <td width="78%" bgcolor="#eeeeee">
          <%= htmlhelper.getPostOfficeSelect(postoffice) %>
        </td>
		</tr>
    <% } %>
		<tr>
      <td bgcolor="#000000" align="left" heigh="20">
        <input type="checkbox" name="remember" value="true" <%= ((remember)? "checked" : "") %>>
        <font face="Arial, Helvetica, sans-serif" color="#ffffff" size="-1">
          <fmt:message key="login.remember"/>
        </font>
      </td>
			<td bgcolor="#000000" align="right" height="20">
				<input type="reset" name="reset" value="<fmt:message key="form.clear" />">
				&nbsp;&nbsp;&nbsp;
				<input type="submit" value="<fmt:message key="login.login" />" name="submit">
			</td>
		</tr>
	</table>
</form>
<br>
<%-- Footer --%>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr bgcolor="#000000" valign="bottom"> 
        <td height="20"> <a href="#top"><img src="images/up_small.png" width="15" height="15" align="right" border="0" alt="To Top."></a>
            <font size="-2" face="Arial, Helvetica, sans-serif" color="#FFFFFF">&copy; 
            2000-2003 jwma team</font> </td>
  </tr>
</table>
<%-- End Footer --%>
</body>
</html>
 
