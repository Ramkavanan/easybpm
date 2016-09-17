<%-- Import taglibs and i18n bundle --%>
<%@ taglib uri="http://jakarta.apache.org/taglibs/i18n-1.0" prefix="i18n" %>
<i18n:bundle baseName="viewcontent" localeAttribute="jwma.locale" resourceClassLoader="jwma.resourceloader"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"
        "http://www.w3.org/TR/REC-html40/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> 
	<meta http-equiv="Pragma" content="no-cache">
    <title><fmt:message key="jwma.webMail"/></title>
</head>

<body bgcolor="#ffffff" link="#666666" vlink="#666666" alink="#FFFFFF">
<%-- Header & Menu --%>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td height="36"><img src="images/logo.png" width="195" height="36" align="right" alt="jwma WebMail"></td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="3">
    <tr bgcolor="#000000">
        <td width="7%" align="center">
          <a href="login.jsp">
            <img src="images/login_small.png" width="21" height="20" border="0" alt="<i18n:message key="login.login"/>">
          </a>
        </td>
        <td width="93%" bgcolor="#000000">&nbsp;</td>
    </tr>
    <tr bgcolor="#dadada">
        <td width="7%" align="center" nowrap>
          <a href="login.jsp">
            <font face="Arial, Helvetica, sans-serif" size="-1"><b><i18n:message key="login.login"/></b></font>
          </a>
        </td>
        <td width="93%" nowrap><font color="#00000"></font></td>
    </tr>
</table>
<%-- End Header & Menu --%>


<%-- Display Message --%>
<p><i18n:message key="logout.successful"/></p>
<p><em><i18n:message key="logout.thanks"/></em></p>

<%-- Footer --%>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr bgcolor="#000000" valign="bottom">
    <td>
      <font size="-2" face="Arial, Helvetica, sans-serif" color="#FFFFFF">&copy; 2000-2003 jwma team</font>
    </td>
  </tr>
</table>
<%-- End Footer --%>
</body>
</html>
