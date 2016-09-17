<%@ include file="/common/taglibs.jsp"%>
<%-- <div class="span12">
	<div class="row-fluid">
		<div class="span1"></div>
		<div class="span10">
			<div style="padding-left:10%;">
				<div class="wizard-steps">
					<ul id="wizardTabs">
						<div class="active-step"><a href="javascript:void(0);" id="wizardTab-1" class="fontSize13">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="listView.viewBasicProperty"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></div>
						<div class="completed-step"><a href="javascript:void(0);" id="wizardTab-2" class="fontSize13">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="listView.columnsProper"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></div>
						<div class="completed-step"><a href="javascript:void(0);" id="wizardTab-3" class="fontSize13">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="listView.groupSetting"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></div>
						<div class="completed-step"><a href="javascript:void(0);" id="wizardTab-4" class="fontSize13">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="listView.buttons"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></div>
					</ul>
				</div>
				<div class="height60"></div>
			</div>
		</div>
		<div class="span1"></div>
	</div>
	<div class="row-fluid">
		<div class="span12">	
			<div style="padding-left:3%;padding-right:1%;">
				<div id="wizardTab-content" style="overflow-y:auto;">
					<div id="wizardTab-content-1" class="displayBlock">
					 	<%@ include file="/WEB-INF/pages/listview/viewBasicProperty.jsp"%>
					</div>
					<div id="wizardTab-content-2" class="displayNone">
						<fieldset>
				    	  	<legend class="accordion-heading">
					     	</legend> 
					     	<div id="collapse-job" class="accordion-body">
					     		<%@ include file="/WEB-INF/pages/listview/columnsProperty.jsp"%>
							</div>
						</fieldset>
					</div>
					<div id="wizardTab-content-3" class="displayNone">
						<fieldset>
							<legend class="accordion-heading">
					        </legend>
					        <div id="collapse-address" class="accordion-body">
					      		<%@ include file="/WEB-INF/pages/listview/groupSettingProperty.jsp"%> 
					        </div>
						</fieldset>
					</div>
					<div id="wizardTab-content-4" class="displayNone">
						<fieldset>
							<legend class="accordion-heading">
					        </legend>
					        <div id="collapse-address" class="accordion-body">
					      		<%@ include file="/WEB-INF/pages/listview/buttonProperty.jsp"%> 
					        </div>
						</fieldset>
					</div>
				</div>
			</div>
		</div>
	</div>
	 <input type="hidden" id="showNewListView" name="showNewListView" value="${showNewListView}"/>
</div> --%>
<c:if test="${isFromModule=='false' || empty isFromModule}" >
<c:if test="${empty moduleId}" >
<div class="page-header">
	<h2><fmt:message key="listView.createListView"/></h2>
</div>
<div align="right">
	<strong><a class="floatRight pad-R10" style="text-decoration: underline;" id="headerLink" href="javascript:void(0);" onclick="showListViews('LIST_VIEW','Manage List View');"><fmt:message key="button.back"/></a></strong>
</div>
		<div class="height10"></div>
	

</c:if>
<c:if test="${not empty moduleId}" >
<div class="page-header">
	<h2>${viewName}</h2>
</div>
<div align="right">
	<strong><a class="floatRight pad-R10" style="text-decoration: underline;" id="headerLink" href="javascript:void(0);" onclick="showListViews('LIST_VIEW','Manage List View');"><fmt:message key="button.back"/></a></strong>
</div>
		<div class="height10"></div>
	

</c:if>
</c:if>
<div class="row-fluid">
	<div class="span12">
		<div class="widget">
			<div class="widget-body">
				<div id="wizard">
					<ol>
						<li><fmt:message key="listView.viewBasicProperty"/></li>
						<li><fmt:message key="listView.columnsProper"/></li>
						<li><fmt:message key="listView.groupSetting"/></li>
						<li><fmt:message key="listView.buttons"/></li>
					</ol> 
					<div class="well">
					<div id="basicProperty">
					<div id="basicPropertyDiv" class="form-horizontal no-margin">
						<%@ include file="/WEB-INF/pages/listview/viewBasicProperty.jsp"%>
					</div>
					</div>
				</div>
				
				<div class="well">
				<div id="columnProperty">
					<div class="form-horizontal no-margin" id=collapse-address>
						<%@ include file="/WEB-INF/pages/listview/columnsProperty.jsp"%>
					</div>
					</div>
				</div>
				<div class="well">
				<div id="groupProperty">
					<div class="form-horizontal no-margin" id="collapse-address">
						<%@ include file="/WEB-INF/pages/listview/groupSettingProperty.jsp"%> 
					</div>
					</div>
				</div>
				
				<div class="well">
				<div id="buttonProperty">
					<div class="form-horizontal no-margin" id="collapse-address">
						<%@ include file="/WEB-INF/pages/listview/buttonProperty.jsp"%> 
					</div>
					</div>
				</div>
			</div>
			</div>
		</div>
	</div>
</div>
<input type="hidden" id="showNewListView" name="showNewListView" value="${showNewListView}"/>
<input type="hidden" id="isFromModule" name="isFromModule" value="${isFromModule}"/>
<script type="text/javascript">
$(document).ready(function(){
	if('${isFromModule}' == 'true'){
		$("#wizard").bwizard();
		$(".bwizard-buttons").css({display: 'none'});
		var newHeight = $("#target").height();
		var wizardHeight = parseInt(newHeight) -155;
// 		$('#wizard').css({height: wizardHeight});
		var width = $("#basicProperty").width();
		var basicWidth =  parseInt(width) - 1;
		var basicHeight = parseInt(newHeight) - 222;
		$('#basicProperty').css({height: basicHeight,overflow:'auto'});
		$('#basicPropertyDiv').css({width: basicWidth});
		
		var columnHeight = parseInt(newHeight) - 222;
		$('#columnProperty').css({height: columnHeight,overflow:'auto'});
		
		var groupHeight = parseInt(newHeight) - 222;
		$('#groupProperty').css({height: groupHeight,overflow:'auto'});
		
		var buttonHeight = parseInt(newHeight) - 222;
		$('#buttonProperty').css({height: buttonHeight,overflow:'auto'});
	} else {
		$("#wizard").bwizard();
		$(".bwizard-buttons").css({display: 'none'});
		var newHeight = $("#target").height();
		var wizardHeight = parseInt(newHeight) -110;
// 		$('#wizard').css({height: wizardHeight});
		
		var basicHeight = parseInt(newHeight) - 152;
		$('#basicProperty').css({height: basicHeight,overflow:'auto'});
		var columnHeight = parseInt(newHeight) - 152;
		$('#columnProperty').css({height: columnHeight,overflow:'auto'});
		
		var groupHeight = parseInt(newHeight) - 152;
		$('#groupProperty').css({height: groupHeight,overflow:'auto'});
		
		var buttonHeight = parseInt(newHeight) - 152;
		$('#buttonProperty').css({height: buttonHeight,overflow:'auto'});
	}
	
	
  	changeWizardByTab();
  	$("#saveButton").attr("disabled", "disabled");
   	
	 
 if('${showNewListView}' && '${showNewListView}' == 'true'){
		var selected_nodes = $("#module_relation_tree").jstree("get_selected", null, true);
		$("#module_relation_tree").jstree("deselect_node", selected_nodes);
		if('${isUpdate}' == 'true'){
			 $("#module_relation_tree").jstree("remove","#"+'${removeListViewId}_${moduleId}');
		 }
		 $("#module_relation_tree").jstree("create", $("#"+'${newListViewModuleId}'+"ListViews"), false, {attr : {id: '${listView.id}_${moduleId}',isedit:'true',isSystemModule:"${isSystemModule}", name: "${listView.viewName}",moduleid:"${moduleId}",nodeid:"${listView.id}"}, data: "${listView.viewName}",metadata: {id: '${listView.id}_${moduleId}',name:"${listView.viewName}",moduleid:"${moduleId}",nodeid:"${listView.id}"}}, false, true);
		 $("#module_relation_tree").jstree("select_node", '#'+'${listView.id}_${moduleId}');
	 
	
 }
 
});

/* var newHeight = $("#target").height(); */

	

</script>
