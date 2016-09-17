<%@ include file="/common/taglibs.jsp"%>

<%-- <page:applyDecorator name="default"> --%>

<head>
    <title><fmt:message key="userList.title"/></title>
    <meta name="heading" content="<fmt:message key='user.manageUsers'/>"/>
</head>
<script type="text/javascript">
$(function () {
	 var targetWidth = $('#target').width();
	 var targetHeight = $('#target').height();
	 var _width = parseInt(targetWidth) / 5;
	 $('#tree_structure').height(parseInt(targetHeight)-42);
	 $('#tree_structure').width("100%");
	 $('#user-grid').width('100%');
	 $('#data-details').width('100%');
	 $('#user-grid').height(parseInt(targetHeight)-42);
	 var jsonData ='${jsonTree}';
	 constructJsTree("tree_structure",jsonData,"getProcessGrid",false);
});
$("#tree_structure").bind('before.jstree', function(event, data) {
	if (data.func == 'check_move') {
		return false;
	}
});


</script>
<div class="span12 target-background">
	<div class="row-fluid">
		<div class="span12">
			<div class="titleBG">
				<span class="floatLeft fontSize14 pad-L10 pad-T10">Classifications</span>
			</div>
		</div>
	</div>
	 <%@ include file="/common/messages.jsp" %>
	 <div class="row-fluid">
            <div class="span12" style='background-image: url("/styles/easybpm/images/palette_line.jpg");'>
                <div class="span3" class="width-per-20">
                    <div id="tree_structure" class="floatLeft department-list-departments department-jstree"></div>
                </div>
                <div class="span9" class="width-per-80">
                    <div class="span12">
		       				<div id="data-details" class="floatLeft department-list-users">
								 <div id="header_links" align="right" class="displayNone;">
									<a class="padding10" id="export" href="javascript:void(0);" data-role="button" data-theme="b"  onClick="bulkExportProcess('ProcessVersion')"  data-icon="">Export</a>
									<!-- <a style="padding:10px;" id="delete" href="javascript:void(0);" data-role="button" data-theme="b"  onClick="bulkDeleteProcess('ProcessVersion')"  data-icon="">Delete</a>
								 --></div>
							
							<div id="user-grid" >
							<%= request.getAttribute("script") %>
							<div id="gridRecordDetails"></div>
							
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>


<script type="text/javascript">
$('#grid_header_links').html($('#header_links').html());
$(document).ready(function() {
	
	setTimeout(function() {
		$("#all a").removeClass("").addClass('jstree-clicked');	
	},300);
	
 });
</script>	


