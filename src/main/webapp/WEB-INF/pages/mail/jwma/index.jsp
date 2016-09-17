<%@ page session="false" import="dtw.webmail.JwmaKernel" %>

<%--
 Redirect to main controller to obtain a jwma session with
 required resource references set.
--%>
<%
	response.sendRedirect(
		response.encodeRedirectUrl(
			JwmaKernel.getReference().getMainControllerUrl()
		)
	);
%>