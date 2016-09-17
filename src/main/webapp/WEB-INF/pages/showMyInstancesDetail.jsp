<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/common/messages.jsp" %>

<div class="page-header">
	<h2>Instance Detail View</h2>
	
</div>

<div align="right">
  	
	<strong><a id="backToPreviousPage"
		style="text-decoration: underline; padding: 10px;"
		href="javascript:void(0);" data-role="button" data-theme="b"
		onClick=" history.go(-1);"><fmt:message
				key="Back" /></a> </strong>
  
</div>

<%= request.getAttribute("instancesScript") %>


<div id="taskDetails">
<div>&nbsp;</div>
<c:if test="${fn:length(formProperties) gt 0}">
<div><span class="panel-title-style style3 style18">&nbsp;Task Details</span></div>
<div>
<table class="gridview" >
	<tr class="header">
		<th>Input</th>
		<th>Value</th>
	</tr>
	<c:forEach items="${formProperties}" var="formProperties">
		<tr>
			<td>${formProperties.name}</td>
			<td>${formProperties.value}</td>
		</tr>
	</c:forEach>
</table>
</div>
</div>
</c:if>




