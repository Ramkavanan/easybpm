<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp" %>
<%=request.getAttribute("script")%>
<script>
checkFormSubmit();
loadCKEditor(function() {
	  setTimeout(function(){	
			for(key in richTextBoxMap){
				$("#"+key).attr("style",richTextBoxMap[key]);
			}
		},1000);
	});
loadListViewsOnForm();
</script>
<div align="right">
  	
	<strong><a id="backToPreviousPage"
		style="text-decoration: underline; padding: 10px;"
		href="javascript:void(0);" data-role="button" data-theme="b"
		onClick="backToPreviousPage();"><fmt:message key="button.back" /></a> </strong>
  
</div>