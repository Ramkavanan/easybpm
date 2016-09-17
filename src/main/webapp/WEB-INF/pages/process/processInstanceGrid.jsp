<%@ include file="/common/taglibs.jsp"%>
<div class="page-header">	
	<h2>${gridTitle}</h2>
</div>
<div align="right">
		<strong><a id="backToPreviousPage" 
			style="text-decoration: underline; padding: 10px;"
			href="javascript:void(0);" data-role="button" data-theme="b"
			onClick="goBack()" ><fmt:message
					key="Back" /></a> </strong>
	</div>
<%= request.getAttribute("script") %>
	<script>
		function goBack() {
			window.history.back(-1);
		}
	
	</script>
