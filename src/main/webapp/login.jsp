<%@ include file="/common/taglibs.jsp" %>
<%@page import="com.eazytec.util.PropertyReader"%>
<%@ page import="org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices" %>
<%@ page import="javax.servlet.http.Cookie" %>
<%@ page session="true" %>
<%@page import="java.util.UUID"%><head>

<head>
    <title><fmt:message key="login.title"/></title>
    <meta name="menu" content="Login"/>
<link href="styles/login.css" rel="stylesheet" type="text/css" media="all" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
    <%
	
		Boolean isCaptchaNeeded=Boolean.valueOf(PropertyReader.getInstance().getPropertyFromFile("Boolean", "system.captcha.needed"));
		String uuid = UUID.randomUUID().toString();
	%>
	<script type="text/javascript">

function randomUUID() {
	  var s = [], itoh = '0123456789ABCDEF';
	 
	  // Make array of random hex digits. The UUID only has 32 digits in it, but we
	  // allocate an extra items to make room for the '-'s we'll be inserting.
	  for (var i = 0; i < 36; i++) s[i] = Math.floor(Math.random()*0x10);
	 
	  // Conform to RFC-4122, section 4.4
	  s[14] = 4;  // Set 4 high bits of time_high field to version
	  s[19] = (s[19] & 0x3) | 0x8;  // Specify 2 high bits of clock sequence
	 
	  // Convert to hex chars
	  for (var i = 0; i < 36; i++) s[i] = itoh[s[i]];
	 
	  // Insert '-'s
	  s[8] = s[13] = s[18] = s[23] = '-';
	 
	  return s.join('');
	}

function showLicenseAlert(){
	var licenseKey = prompt("Enter License Key");	
	if(licenseKey != null && licenseKey.length > 0){
		licenseKey = licenseKey.replace(/&/g, '`');
		$.ajax({
			type : 'GET',
			async : false,
			url : '/bpm/license/saveLicense',
			data:'licenseKey='+licenseKey,
			success : function() {
				alert("License Key Added success Fully");
			}
		});
		//alert(a);
	} else {
		alert("Please, Enter the license key !!");
	}
}

</script>
	
</head>
<body>
<!-- contact-form -->	
<div class="message warning">
	<div class="inset">
		<div class="login-head">
			<h1>EazyTec User Login </h1>
		</div>
		<form method="post" id="loginForm" action="<c:url value='/j_spring_security_check'/>" onsubmit="saveUsername(this);return validateForm(this)" class="signin-wrapper"  autocomplete="off">
						<c:if test="${param.error != null}">
						    <div class="alert alert-error fade in">
						    	<c:choose>
								    <c:when test = "${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message == 'Bad credentials'}">
								       <fmt:message key="errors.password.mismatch"/>
								    </c:when>
								    <c:otherwise>
								        <c:out value="${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}"/>
								       <c:set var="license" value="${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}"/>
								        <c:if test="${fn:contains(license, 'Host')}">
									    <a href="" onclick="showLicenseAlert()"/>License Save</a>
									</c:if>
			        							       
								    </c:otherwise>
								</c:choose>
						    </div>
						</c:if>
				
				<div class="clear"> </div>
				<li>
					<input type="text" class="text" name="j_username" id="j_username" placeholder="<fmt:message key="label.username"/>" tabindex="1"><a href="#" class=" icon user"></a>
				</li>
					<div class="clear"> </div>
				<li>
					<input type="password" placeholder="<fmt:message key="label.password"/>" name="j_password" id="j_password" tabindex="2"> <a href="#" class="icon lock"></a>
				</li>
				<div class="clear"> </div>
				<% if(null != isCaptchaNeeded && isCaptchaNeeded ){ %>
							<div class="clearfix">
					           <img src="/captcha/jcaptcha.jpg?uuid=<%=uuid%>" id="captchaImage" />
					            <img src="/images/refresh.png" onclick="refresh();" height="25px" width="25px" style="margin-left:20px">
					           <br />
					         </div>
					   
				<div class="clear"> </div>
				
				<li>
					 <input type="text" class="text" name="jcaptcha" placeholder="Captcha" tabindex="3" required/><a href="#" class="icon lock"></a>
				</li>
				 <% } %>
				 <div class="clear"> </div>   
				<li class="no-border">
					<input type="checkbox" name="_spring_security_remember_me" id="rememberMe" tabindex="4"/>&nbsp;&nbsp;&nbsp;<span style="color:#8d8d8d;font-size:15px;"><fmt:message key="login.rememberMe"/></span>
				</li>
				<div class="submit">
					<input type="submit" value="Sign in" name="login"  >
							  <div class="clear">  </div>	
				</div>
					
		</form>
	</div>					
</div>
</div>
<div class="clear"> </div>
<c:set var="scripts" scope="request">
<%@ include file="/scripts/login.js"%>
</c:set>
</body>
</html>