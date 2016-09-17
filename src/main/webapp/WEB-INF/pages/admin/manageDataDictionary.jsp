<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="dataDictionaryList.title"/></title>
    <meta name="heading" content="<fmt:message key='dataDictionary.manage'/>"/>
</head>
<%@ include file="/common/messages.jsp" %>

<script type="text/javascript">

function backToPreviousDataDictionary() {	
	var referenceId = document.getElementById("referenceParentId").value;
	//var hierarchyParentId = document.getElementById("hierarchyParentId").value;
	//var params = 'parentDictId='+referenceId+'&hierarchyParentId='+hierarchyParentId;//true&isSystemModule=false&isJspForm=false';	
	//_execute('target', params);
	//Backbone.history.navigate("#bpm/admin/dataDictionary", {trigger: true, replace: true});
	$.ajax({
	url: 'bpm/admin/getParentDictIdbyDictId',
        type: 'GET',
        contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		async: false,
		data: 'dictionaryId=' + referenceId,
		success : function(response) {
				if(response.length > 0){
					getDataDictionaryGrid(response);
				}
			}
	});
	

}
// var dataDictJson = '[{ "data" : "User-Defined",  "attr" : { "id" : "UserDefined", "name" : "UserDefined","parent" : "UserDefined" },"metadata": {"id" : "header", "name" : "User-Defined"}}, '
// 	+'{"data":"System-Defined","attr":{"id" : "SystemDefined","name" : "SystemDefined","parent" : "SystemDefined"},"metadata": {"id" : "SystemDefined", "name" : "System-Defined"}}]';



	/*
    data.rslt contains: 
    .o - the node being moved 
    .r - the reference node in the move 
    .ot - the origin tree instance 
    .rt - the reference tree instance 
    .p - the position to move to (may be a string - "last", "first", etc) 
    .cp - the calculated position to move to (always a number) 
    .np - the new parent 
    .oc - the original node (if there was a copy) 
    .cy - boolen indicating if the move was a copy 
    .cr - same as np, but if a root node is created this is -1 
    .op - the former parent 
    .or - the node that was previously in the position of the moved node 
    */

function getAllChildDictionaries(e,data) {
	var currentNode = data.rslt.obj.attr("id");
	var currentNodeName = data.rslt.obj.attr("name");
	var rootNode = data.inst.get_path()[0];
	var nodeLevel = data.inst.get_path().length;
	var selected_nodes = $("#data_dict_js_tree").jstree("get_selected", null, true); 
	var tree = $.jstree._reference($("#data_dict_js_tree")); 
	var children = tree._get_children(selected_nodes); 
	if ( children[0] == undefined ) {
		var childNodes = getDataDictionarychildNodes(currentNodeName,rootNode,nodeLevel);
		if( childNodes != null && childNodes.length > 0 ){
			for(var i=0; i<childNodes.length; i++) {
				$("#data_dict_js_tree").jstree("create", $("#"+currentNode) , false, childNodes[i] , false, true);
			}
		}
	}
}

/* function getAllSystemDictionaries(e,data) {
	var currentNode = data.rslt.obj.attr("id");
	var currentNodeName = data.rslt.obj.attr("name");
	var rootNode = data.inst.get_path()[0];
	var nodeLevel = data.inst.get_path().length;
	var selected_nodes = $("#data_dict_js_tree").jstree("get_selected", null, true); 
	var tree = $.jstree._reference($("#data_dict_js_tree")); 
	var children = tree._get_children(selected_nodes); 
	if ( children[0] == undefined ) {
		var childNodes = getDictChildNodes(currentNodeName,rootNode,nodeLevel);
		if( childNodes != null && childNodes.length > 0 ){
			for(var i=0; i<childNodes.length; i++) {
				$("#data_dict_js_tree").jstree("create", $("#"+currentNode) , false, childNodes[i] , false, true);
			}
		}
	}
}  */

$(document).ready(function(){
//$.cookie('bpmAppBack','');
//$.cookie('bpmSideAppBack','');
	document.getElementById("referenceParentId").value = "${parentDictId}";
	    var targetWidth = $('#target').width();
	    var targetHeight = $('#target').height();
	    var _width = parseInt(targetWidth) / 5;
	    $('#data_dict_js_tree').height(parseInt(targetHeight)-128);
	    $('#data_dict_js_tree').width("100%");
		$('#user-details').width('100%');
	    $('#user-grid').width('100%');
	    $('#user-grid').height(parseInt(targetHeight)-128);
	document.getElementById("hierarchyParentId").value = '${hierarchyParentId}';
	
	getDataDictionaryGrid("${parentDictId}");
	constructJsTree("data_dict_js_tree",'${dataDictionaryTree}',"setSelectedNodeValue");
	//setTimeout(function() {
	    //   $("#UserDefined a").removeClass("").addClass('jstree-clicked');
	      // document.getElementById("referenceParentId").value = "UserDefined";
	//   },300);

	}); 



	$("#data_dict_js_tree").bind('loaded.jstree', function(event, data) {
	$("#data_dict_js_tree").jstree("select_node", '#'+document.getElementById("hierarchyParentId").value); 
	});

	function setSelectedNodeValue(e,data) {	
	 var nodeId = data.rslt.obj.context.attributes[1].nodeValue;
		
	//getAllChildDictionaries(e,data);
	var parentName = data.rslt.obj.attr("name");	
	var id = data.rslt.obj[0].id;//attributes[0].nodeValue;//'${parentDictId}';//attributes[0].nodeValue;
	document.getElementById("parentCheckValue").value = data.rslt.obj.attr("parentCheckId");
	var selected_nodes = $("#data_dict_js_tree").jstree("get_selected", null, true); 
	document.getElementById("referenceParentId").value = selected_nodes.attr("id");
	document.getElementById("hierarchyParentId").value = selected_nodes.attr("id");
	
	if(id.toLowerCase() === "systemdefined") {
		//$("#dict_operation_div").addClass("displayNone");
		document.getElementById("dict_operation_div").style.display = "none";
	} 
	if(id.toLowerCase() === "userdefined") {
		document.getElementById("dict_operation_div").style.display = "block";
	}

	if(id.toLowerCase() != "userdefined" || id.toLowerCase() != "systemdefined") {	
		
		if($.browser.msie != undefined && $.browser.msie && $.browser.version < 10){
			if(nodeId.indexOf("jstree-clicked") == -1) {
					 getDataDictionaryGrid(id);
					//id.focus();
			} else {
				if('${parentDictId}' != "UserDefined") {

						getDataDictionaryGrid('${parentDictId}');
				}
				
				}
			}	else {
					if(nodeId.indexOf("jstree-clicked") > -1) {
					
					 getDataDictionaryGrid(id);
					//id.focus();
			} else {
				if('${parentDictId}' != "UserDefined") {

						getDataDictionaryGrid('${parentDictId}');
				}
				
				}
			}	
	}
}

function getDataDictionaryGrid(id){
	var parentId = document.getElementById("parentCheckValue").value;
	document.getElementById("referenceParentId").value = id;
	$.ajax({
		url: 'bpm/admin/getDataDictionaryGrid',
        type: 'GET',
        dataType: 'html',
        contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		async: false,
		cache: false,
		data: 'parentDictId=' + id,
		success : function(response) {
			if(response.length > 0){
				setTimeout(function() {
					$("#user-grid").html(response);
					$('#grid_header_links').html($('#header_links').html());
				}, 100);
				
			}
			}
	});
	if(parentId.toLowerCase() === "systemdefined") {
		//$("#dict_operation_div").addClass("displayNone");
		document.getElementById("dict_operation_div").style.display = "none";
	}
	if(parentId.toLowerCase() === "userdefined") {
		document.getElementById("dict_operation_div").style.display = "block";
	}
	
}


function createNewDataDictionary() {
	
	var params = 'parentDictId='+document.getElementById("referenceParentId").value+'&hierarchyParentId='+document.getElementById("hierarchyParentId").value;
 	 document.location.href = "#bpm/admin/newDictionary";
	_execute('target',params); 
}

$.ajaxSetup({ cache: false });

function displaySqlTextInput() {
	if(document.getElementById("dict_type").value.toLowerCase() == "dynamic"){
		
		if($("#sqlStringDiv").hasClass("displayNone")) {
			$("#sqlStringDiv").addClass("displayBlock"); 
			$("#sqlStringDiv").removeClass("displayNone");
			$("#valueDiv").addClass("displayNone");
			$("#valueDiv").removeClass("displayBlock"); 
		}
	}else {
		if($("#valueDiv").hasClass("displayNone")) {
			$("#valueDiv").addClass("displayBlock");
			$("#valueDiv").removeClass("displayNone");
			$("#sqlStringDiv").removeClass("displayBlock"); 
			$("#sqlStringDiv").addClass("displayNone"); 
		}
	} 
}

function editDataDictionary(dictionaryId) {
	 var params = 'dictionaryId='+dictionaryId+'&hierarchyParentId='+document.getElementById("hierarchyParentId").value;;
	 document.location.href = "#bpm/admin/dictionaryDetail";
	_execute('target',params); 
}


</script>

<%-- <div class="span12">
<div class="container-fluid" id="parent_container">
	 <div class="row-fluid">
		<div class="span12">
			<div class="titleBG">
				<span class="floatLeft fontSize14 pad-L10 pad-T10">
				<fmt:message key="user.manage.menus"/>
				</span>
				<span class="floatRight fontSize14 pad-R10 pad-T10">
<!-- 				<a title="Create New" data-icon="" onclick="_execute('menu_form','');refreshJSTree('menu_js_tree');" data-theme="b" data-role="button" href="#bpm/admin/menuForm" >Create New</a> -->
				</span>
			</div> 
		</div>
	</div> 
	<div class="row-fluid">
	    <div class="span12">
			<div id="data_dict_tree" class="span3" style="width:20%;overflow:auto;">
				<div id="data_dict_js_tree" style="background-image: url('/styles/easybpm/images/palette_line.jpg');"></div>
			</div>
			<div id="user-grid" class="span9" style="width:80%;overflow-y:auto;overflow-x:hidden;">
  					<%= request.getAttribute("script") %>
<!--   					<script type="text/javascript">$('#grid_header_links').html($('#header_links').html());</script> -->
			</div>
		</div>
	</div>
</div>

</div> --%>

	<div id="parent_container">
		<div class="row-fluid">
				<div class="page-header">
					<h2><fmt:message key="manageDataDictionary.title"/></h2>
				

				 	<div class="height10"></div>
						<div id="header_links" align="right" style="padding: 2px 0">
		            		<a class="padding3" id="createUser" href="javascript:void(0);" data-role="button" data-theme="b"  onClick="createNewDataDictionary()"  data-icon="">
		            		<button class="btn"><fmt:message key="button.createNew"/></button></a>
							<a class="padding3" id="enableUsers" href="javascript:void(0);" data-role="button" data-theme="b"  onClick="return validateAndOperateFunction('disable',document.getElementById('referenceParentId').value);"  data-icon="">
							<button class="btn"><fmt:message key="button.disable"/></button></a>
							<a class="padding3" id="backToPreviousDataDictionary" href="javascript:void(0);" data-role="button" data-theme="b"  onClick="backToPreviousDataDictionary();"  data-icon="">
							<button class="btn"><fmt:message key="button.back"/></button></a>
							<div id="dict_operation_div" style="position:relative;float:right">
								<a class="padding3" id="deleteUsers" href="javascript:void(0);" data-role="button" data-theme="b"  onClick="return validateAndOperateFunction('delete',document.getElementById('referenceParentId').value);"  data-icon="">
								<button class="btn"><fmt:message key="button.delete"/></button></a>
					</div>
 	           	</div>
 	           	</div>
	
			<div class="span12">
				<div class="span3">
					<div class="widget">
						<div class="widget-header">
							<div class="title">
								<span class="fs1" aria-hidden="true"></span> Dictionary Type 
							</div>
						</div>
						<div class="widget-body">
							<div id="data_dict_js_tree" class="floatLeft department-list-departments department-jstree" style="overflow:auto"></div>
							<div class="clearfix"></div>
						</div>
					</div>
				</div>
				<div class="span9">
					<div class="widget">
						<div class="widget-header">
							<div class="title">
								<span class="fs1" aria-hidden="true"></span> Dictionary List
							</div>
						</div>
						<div class="widget-body overflow">
							<div id="user-details" class="floatLeft department-list-users">
                            	<div id="user-grid">
                                	<%= request.getAttribute("script") %>
                            	</div>
                        	</div>
							<div class="clearfix"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
<%-- <div id="header_links" align="right" style="display:none">
	<a id="enableUsers" href="javascript:void(0);" data-role="button" data-theme="b"  onClick="updateUserStatus('enable');"  data-icon=""><fmt:message key="button.enable"/></a>
	<a id="disableUsers" href="javascript:void(0);" data-role="button" data-theme="b"  onClick="updateUserStatus('disable');"  data-icon=""><fmt:message key="button.disable"/></a>
	<a style="padding:10px;" id="createUser" href="javascript:void(0);" data-role="button" data-theme="b"  onClick="createNewDataDictionary()"  data-icon=""><fmt:message key="button.createNew"/></a>
	<a style="padding:10px;" id="enableUsers" href="javascript:void(0);" data-role="button" data-theme="b"  onClick="return validateAndOperateFunction('disable',document.getElementById('referenceParentId').value);"  data-icon=""><fmt:message key="button.disable"/></a>
	<div id="dict_operation_div" style="position:relative;float:right">
	<a style="padding:10px;" id="deleteUsers" href="javascript:void(0);" data-role="button" data-theme="b"  onClick="return validateAndOperateFunction('delete',document.getElementById('referenceParentId').value);"  data-icon=""><fmt:message key="button.delete"/></a>
	</div>
</div> --%>
<input type="hidden" id="parentCheckValue"/>
<input type="hidden" id="referenceParentId"/>
<input type="hidden" id="hierarchyParentId" name="hierarchyParentId"/>