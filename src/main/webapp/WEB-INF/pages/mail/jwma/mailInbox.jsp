<%@ page session="true" import="dtw.webmail.model.*" %>

<%-- Import taglibs and i18n bundle --%>
<%@ include file="/common/taglibs.jsp"%>

<%-- Error Messages --%>
<c:if test="${not empty errors}">
    <script type="text/javascript">
	    var error = "";
	    <c:forEach var="error" items="${errors}">
	    	error = error + "<c:out value='${error}' escapeXml='false'/> <br />";
		</c:forEach>
		validateMessageBox("Error", error, "error");
    </script>
     <c:remove var="errors"/>
</c:if>

<%-- Success Messages --%>
<c:if test="${not empty successMessages}">
    <script type="text/javascript">
	    var msg = "";
	    <c:forEach var="error" items="${successMessages}">
	    	msg = msg + "<c:out value='${error}' escapeXml='false'/> <br />";
		</c:forEach>
		validateMessageBox("Success", msg, "success");
    </script>
     <c:remove var="successMessages" scope="session"/> 
</c:if>

<c:set var="pathInfo" value='<%=request.getAttribute("javax.servlet.forward.servlet_path")%>'/>


<%= request.getAttribute("script") %>
<c:set var="from" value='<%=request.getAttribute("from")%>'/>

<script type="text/javascript">

$(function () {
    $('#grid_header_links').html($('#header_links').html());

});

$(document).ready(function() {
	var from = '${from}';
	if(from == "draft") {
		if('${sessionType}' == 'email'){
			replaceMailBreadcrumb('mail', 'draft');
		} else {
			replaceMailBreadcrumb('internal message', 'draft');
		}
	} else if(from == "inbox"){
		if('${sessionType}' == 'email'){
			replaceMailBreadcrumb('mail', 'inbox');
		} else {
			replaceMailBreadcrumb('internal message', 'inbox');
		}
	} else if(from == "sent-mail"){
		if('${sessionType}' == 'email'){
			replaceMailBreadcrumb('mail', 'sent mail');
		} else {
			replaceMailBreadcrumb('internal message', 'sent message');
		}
	} else if(from == "sent-later"){
		if('${sessionType}' == 'email'){
		} else {
			replaceMailBreadcrumb('internal message', 'send later');
		}
	}
	
});

</script>

<div id="header_links" align="right" style="display:none">
		<%-- <a class="padding5" href="#bpm/mail/jwma/refreshMailInbox?from=${from}" > <img class="header-menu-name" width="14px" height="14px" alt="refresh" src="/images/refresh.png" /></a> --%>
		<a class="padding10" id="deleteMails" href="javascript:void(0);"  onClick="deleteSelectedMails('${from}')"><fmt:message key="button.delete"/></a>
		<span class="simple-search pad-R10"><fmt:message key="button.search"/> </span><input style="height: 15px;" type="text" name="mailSearchBox" id="mailSearchBox" onkeyup="mailGridSearch('_INBOX_LIST');"/>
</div>
