<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp" %>
<%-- <page:applyDecorator name="default"> --%>

<head>
    <title><fmt:message key="userList.title"/></title>
    <meta name="heading" content="<fmt:message key='user.manageUsers'/>"/>
</head>
<script type="text/javascript">


$(function () {
/*  	window.onunload = refreshParent;
	function refreshParent() {
	   // window.opener.location.reload();
	    window.opener.refreshGridData(0,$('#type').val());
	} */ 
	 var targetWidth = $('#target').width();
	 var targetHeight = $('#target').height();
	 var _width = parseInt(targetWidth) / 5;
	 $('#tree_structure').height(parseInt(targetHeight)-127);
	 $('#tree_structure').width("100%");
	 $('#user-grid').width('100%');
	 $('#data-details').width('100%');
	 $('#user-grid').height(parseInt(targetHeight)-127);
	 var jsonData ='${jsonTree}';
	 constructJsTree("tree_structure",jsonData,"getProcessGrid",false);
});

$("#tree_structure").bind('before.jstree', function(event, data) {
	if (data.func == 'check_move') {
		return false;
	}
});

</script>
<div class="row-fluid target-background">
	<div class="span12">
		<div class="page-header">
			<div class="pull-left">
				<h2>${gridTitle}</h2>
			</div>
			<c:if test="${gridType != 'myDocumentInstance' }">
				<div id="header_links" align="right">
					<c:if test="${gridType == 'listprocess'}"> 
						 <a style="padding:3px;" id="import" href="javascript:void(0);" data-role="button" data-theme="b"  onClick="importXml()"  data-icon=""><button class="btn" style="line-height: 14px;">Import</button></a>
						 <a style="padding:3px;" id="export" href="javascript:void(0);" data-role="button" data-theme="b"  onClick="bulkExportProcess('ProcessDefiniton')"  data-icon=""><button class="btn" style="line-height: 14px;">Export</button></a>
						 <a style="padding:3px;" id="delete" href="javascript:void(0);" data-role="button" data-theme="b"  onClick="bulkDeleteProcess('ProcessDefiniton')"  data-icon=""><button class="btn" style="line-height: 14px;">Delete</button></a>
					</c:if>
					<c:if test="${gridType == 'myInstances'}">
						<a style="padding: 3px;" id="export" href="javascript:void(0);" data-role="button" data-theme="b" onClick="deleteMultipleProcessInstances()" data-icon=""><button class="btn" style="line-height: 14px;">Delete</button></a>
						<a style="padding: 3px;" id="export" href="javascript:void(0);" data-role="button" data-theme="b"  onClick="gridDataExportToCSV('MyInstances','myInstances')"  data-icon=""><button class="btn" style="line-height: 14px;">Export CSV</button></a>
					</c:if>
				</div>
			</c:if>
		</div>
	</div>
</div>

<c:if test="${not empty isEndScriptExist}">
	<c:if test="${isEndScriptExist == 'true'}"> 
		<%= request.getAttribute("endScript") %>
	</c:if>
</c:if>
<c:if test="${not empty isStartScriptExist}">
	<c:if test="${isStartScriptExist == 'true'}"> 
 		<%= request.getAttribute("startScript") %>
 	</c:if>
 </c:if>
 
 <div class="row-fluid">
   	<div class="span12">
       	<c:choose>
 				<c:when test="${gridType != 'documentToBeDone' && gridType != 'readDocumentList' && gridType != 'toReadDocument' && gridType != 'doneDocumentList' && gridType != 'myDocumentInstance'}">
              <div class="span3">
              		<div class="widget">
					<div class="widget-header">
						<div class="title">
							<span class="fs1" aria-hidden="true"></span> <fmt:message key="listView.classifications"/> 
						</div>
					</div>
					<div class="widget-body" style="overflow: auto;">
						<div id="tree_structure" class="floatLeft department-list-departments department-jstree"></div>
						<div class="clearfix"></div>
					</div>
				</div>
              </div>
            <div class="span9">
				<div class="widget">
					<div class="widget-header">
						<div class="title">
							<span class="fs1" aria-hidden="true"></span> ${gridTitle}
						</div>
					</div>
					<div class="widget-body">
						<div id="data-details" class="floatLeft department-list-users">
                           	<div id="user-grid" >
                               	<%= request.getAttribute("script") %>
								<div id="gridRecordDetails"></div>
                           	</div>
                       	</div>
						<div class="clearfix"></div>
					</div>
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<%= request.getAttribute("script") %>
			<div id="gridRecordDetails"></div>
		</c:otherwise>
		</c:choose>
	</div>
</div>

<script type="text/javascript">
/* $('#grid_header_links').html($('#header_links').html()); */
$(document).ready(function() {
/* 	 var status = '${statusMsg}';
	 var gridType = '${gridType}';
	 $('#type').val(gridType);
	 if(status == "success" ){
		 document.getElementById('task_target').style.visibility = 'hidden'; 	
	 } */
	checkMenu('','url','/process/myInstances','ff8081813c8a17e8013c8a27188a0003','','','','side');

/* 	setTimeout(function() {
		$("#all a").removeClass("").addClass('jstree-clicked');	
	},300); */
	
 });
</script>