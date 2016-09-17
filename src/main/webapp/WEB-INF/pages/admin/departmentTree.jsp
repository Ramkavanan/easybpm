<%@ include file="/common/taglibs.jsp"%>

<%-- <page:applyDecorator name="default"> --%>

<head>
    <title><fmt:message key="userList.title"/></title>
    <meta name="heading" content="<fmt:message key='user.manageUsers'/>"/>
</head>
<%@ include file="/common/messages.jsp" %>
<script type="text/javascript">

//the javascript written here because of using jstl tag for get department list
//construct json data for jsTree

function getActiveChildData(currentNode, parentNode, level, rootNode){
	var hasChildNode = 0;
    if(rootNode == "Role"){
        if(level == 1){
            <c:forEach items="${roleList}" var="role" varStatus="status">
                var roleId =  "${role.id}";
                var roleId2    = roleId.replace(/ /g,'');
                $("#department_tree").jstree("create", $("#"+currentNode), false, {attr : {id: roleId2, name: encodeURI("${role.id}")}, data: "${role.name}"}, false, true);
                hasChildNode++;
             </c:forEach>;
        }
    }else if(rootNode == "Organization"){
        if(level == 1){
             <c:forEach items="${departmentList}" var="rootDepartment" varStatus="status">
                var superDepartment = "${rootDepartment.superDepartment}";
                var superDept = superDepartment.replace(/ /g,'');
                if(currentNode == superDept){
                	hasChildNode = hasChildNode + 1;
                    var departmentId =  "${rootDepartment.id}";
                    var departmentId2    = departmentId.replace(/ /g,'');
                    $("#department_tree").jstree("create", $("#"+currentNode), false, {attr : {id: departmentId2, name: encodeURI("${rootDepartment.id}")}, data: "${rootDepartment.viewName}"}, false, true);
                }
            </c:forEach>;
        }else{
            <c:forEach items="${departmentList}" var="childDepartment" varStatus="status">
                var departmentId =  "${childDepartment.id}";
                var departmentId2    = departmentId.replace(/ /g,'');
                var superDepartment = "${childDepartment.superDepartment}";
                var superDept = superDepartment.replace(/ /g,'');
                   if(currentNode == superDept){
                	   hasChildNode = hasChildNode + 1;
                       $("#department_tree").jstree("create", $("#"+currentNode), false, {attr : {id: departmentId2, name: encodeURI("${childDepartment.id}")}, data: "${childDepartment.viewName}"}, false, true);
                   }
               </c:forEach>;
        }
    }else if(rootNode == "Group"){
        if(level == 1){
            <c:forEach items="${groupList}" var="parentGroup" varStatus="status">
                var groupId =  "${parentGroup.id}";
                var groupId2    = groupId.replace(/ /g,'');
                if("${parentGroup.isParent}" == "true"){
                	hasChildNode = hasChildNode + 1;
                    $("#department_tree").jstree("create", $("#"+currentNode), false, {attr : {id: groupId2, name: encodeURI("${parentGroup.id}")}, data: "${parentGroup.name}"}, false, true);
                }
            </c:forEach>;
        }else{
            <c:forEach items="${groupList}" var="parentGroup" varStatus="status">
                var groupId =  "${parentGroup.id}";
                var groupId2    = groupId.replace(/ /g,'');
                var superGroup = "${parentGroup.superGroup}";
                superGroup = superGroup.replace(/ /g,'');
                if(superGroup == currentNode){
                	hasChildNode = hasChildNode + 1;
                    $("#department_tree").jstree("create", $("#"+currentNode), false, {attr : {id: groupId, name: encodeURI("${parentGroup.id}")}, data: "${parentGroup.name}"}, false, true);
                }
            </c:forEach>;
        }
    }
    if(hasChildNode == 0){
    	$("#"+currentNode).removeClass('jstree-open');
    	$("#"+currentNode).removeClass('jstree-closed');
    	$("#"+currentNode).addClass('jstree-leaf');
    }
}
$(function () {
     var targetWidth = $('#target').width();
     var targetHeight = $('#target').height();
     var _width = parseInt(targetWidth) / 5;
     $('#department_tree').height(parseInt(targetHeight)-128);
     $('#department_tree').width("100%");
	 $('#user-details').width('100%');
     $('#user-grid').width('100%');
     $('#user-grid').height(parseInt(targetHeight)-128);
     $("#addUsers").hide();
     var jsonData = defaultJsonData();
     constructJsTree("department_tree",jsonData,"getDepartmentUserGrid",true);
});
</script>

<%-- <div class="span12 target-background">
    <div class="container-fluid" id="parent_container">
        <div class="row-fluid">
            <div class="span12">
                <div class="titleBG">
                	<span class="floatLeft fontSize14 pad-L10 pad-T10"><fmt:message key="user.manageUsers"/></span>
                </div>
            </div>
        </div>
        <div class="row-fluid">
            <div class="span12" style='background-image: url("/styles/easybpm/images/palette_line.jpg");'>
                <div class="span3" style="width:20%;">
                    <div id="department_tree" class="floatLeft department-list-departments department-jstree"></div>
                </div>
                <div class="span9" style=";width:80%;">
                    <div class="span12">
                           <div id="user-details" class="floatLeft department-list-users">
                            <div id="header_links" align="right" style="display:none">
                                <a style="padding:10px;" id="createUser" href="#bpm/admin/userform" data-role="button" data-theme="b"  onClick="return createDepartmentUser()"  data-icon=""><fmt:message key="button.createNew"/></a>
                                <a style="padding:10px;" id="enableUsers" href="javascript:void(0);" data-role="button" data-theme="b"  onClick="updateUserStatus('disable');"  data-icon=""><fmt:message key="button.disable"/></a>
                                <a style="padding:10px;" id="deleteUsers" href="javascript:void(0);" data-role="button" data-theme="b"  onClick="deleteUsers()"  data-icon=""><fmt:message key="button.delete"/></a>
                                <a style="padding:10px;" id="addUsers" href="javascript:void(0);" data-role="button" data-theme="b"  onClick="showUsers('User','multi', 'selectUsers');"  data-icon=""><fmt:message key="button.add"/></a>
                            </div>
                            <div id="user-grid" >
                                <%= request.getAttribute("script") %>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div> --%>


	<div class="row-fluid">
			<div class="page-header">
				<h2><fmt:message key="user.manageUsers"/></h2>
			
			 	<div class="height10"></div>
			<div id="header_links" align="right">
           		<a class="padding3" id="createUser" href="#bpm/admin/userform" data-role="button" data-theme="b"  onClick="return createDepartmentUser()"  data-icon=""><button class="btn"><fmt:message key="button.createNew"/></button></a>
                   <a class="padding3" id="enableUsers" href="javascript:void(0);" data-role="button" data-theme="b"  onClick="updateUserStatus('disable');"  data-icon=""><button class="btn"><fmt:message key="button.disable"/></button></a>
                   <a class="padding3" id="deleteUsers" href="javascript:void(0);" data-role="button" data-theme="b"  onClick="deleteUsers()"  data-icon=""><button class="btn"><fmt:message key="button.delete"/></button></a>
  	                <a class="padding3" id="addUsers" href="javascript:void(0);" data-role="button" data-theme="b"  onClick="showUsers('User','multi', 'selectUsers');"  data-icon=""><button class="btn"><fmt:message key="button.add"/></button></a>
	           	</div>
	           	</div>

		<div class="span12">
			<div class="span3">
				<div class="widget">
					<div class="widget-header">
						<div class="title">
							<span class="fs1" aria-hidden="true"></span> <fmt:message key='userList.user'/>
						</div>
					</div>
					<div class="widget-body">
						<div id="department_tree" class="floatLeft department-list-departments department-jstree overflow"></div>
						<div class="clearfix"></div>
					</div>
				</div>
			</div>
			<div class="span9">
				<div class="widget">
					<div class="widget-header">
						<div class="title">
							<span class="fs1" aria-hidden="true"></span><fmt:message key='userList.title'/>
						</div>
					</div>
					<div class="widget-body">
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
 
 
<%-- <div class="span12 target-background">
    <div class="row-fluid">
        <div class="span12">
            <div class="titleBG">
                <span class="floatLeft fontSize14 pad-L10 pad-T10"><fmt:message key="user.manageUsers"/></span>
            </div>
        </div>
    </div>
     <%@ include file="/common/messages.jsp" %>
    <div class="row-fluid" style='background-image: url("/styles/easybpm/images/palette_line.jpg");'>
        <div class="span12">
               <table width="100%">
                       <tr>
                           <td width="25%">
                               <div class="span12">
                                   <div id="department_tree" class="floatLeft department-list-departments department-jstree"></div>
                               </div>
                           </td>
                           <td width="75%">
                               <div class="span12">
                               <div id="user-details" class="floatLeft department-list-users">
                            <div id="header_links" align="right" style="display:none">
                                <a style="padding:10px;" id="createUser" href="#bpm/admin/userform" data-role="button" data-theme="b"  onClick="return createDepartmentUser()"  data-icon=""><fmt:message key="button.createNew"/></a>
                                <a style="padding:10px;" id="enableUsers" href="javascript:void(0);" data-role="button" data-theme="b"  onClick="updateUserStatus('disable');"  data-icon=""><fmt:message key="button.disable"/></a>
                                <a style="padding:10px;" id="deleteUsers" href="javascript:void(0);" data-role="button" data-theme="b"  onClick="deleteUsers()"  data-icon=""><fmt:message key="button.delete"/></a>
                            </div>
                            <div id="user-grid" >
                            <%= request.getAttribute("script") %>
                            </div>
                            </div>
                            </div>
                           </td>
                       </tr>
               </table>
        </div>
    </div>
</div> --%>
<script type="text/javascript">
$('#grid_header_links').html($('#header_links').html());
$(document).ready(function() {

    setTimeout(function() {
        $("#all a").removeClass("").addClass('jstree-clicked');
    },300);

 });
</script>