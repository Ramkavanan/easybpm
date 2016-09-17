<%@ page session="true" import="dtw.webmail.model.*,
                                dtw.webmail.JwmaKernel,
                                dtw.webmail.util.StringUtil" %>

<%@ include file="/common/taglibs.jsp"%>

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
<div class="span12 box">
 	<h2><fmt:message key="login.login"/></h2>
        <%@ include file="/common/messages.jsp" %>
</div>
<div class="span12 box ">
	<div class="span11 scroll">
		<div class="table-create-user">
			<form:form  method="post" action="bpm/mail/jwma/getMailAuthendication" >
				<div class="line-height46 width-per-100">
					<div id="passwordDiv" class="width-per-100">
						<div class="floatLeft pad-T10 width-per-30"><eazytec:label styleClass="control-label style3 style18" key="login.password" /></div>
						<div class="floatLeft width-per-70" ><input type="password" name="password" size="25"> </div>
					</div>
					<div id="buttonDiv" class="width-per-100">
						<div class="floatLeft width-per-30">&nbsp;</div>
						<div class="floatLeft width-per-70">
							<button type="submit" class="btn btn-primary normalButton padding0 line-height0" name="login"  id="loginButton">
		                           <fmt:message key="login.login" />
				    		</button>
				    	</div>
					</div>
				</div>
				<input type="hidden" name="from" value="${from}">	
			</form:form>
		</div>
	</div>
</div>
