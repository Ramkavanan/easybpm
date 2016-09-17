<%@ page import="com.eazytec.common.util.CommonUtil,org.springframework.security.core.context.SecurityContextHolder,com.eazytec.model.User"%>
<%
User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
String currentRoles = CommonUtil.getRolesAsString(user);
String assMenus = "assignedMenus_"+user.getId();
String currentUserId = user.getId();
String userFullName = user.getFirstName()+" "+user.getLastName();
%>
<%@ page import ="com.eazytec.Constants" %>
<!DOCTYPE html>
<%@ include file="/common/taglibs.jsp"%>
<html lang="en">
<head>
	<title><decorator:title/> | <fmt:message key="webapp.name"/></title>
	<meta http-equiv="Cache-Control" content="IE=edge;no-store"/>
    <meta http-equiv="Pragma" content="IE=edge;no-cache"/>
    <meta http-equiv="Content-Type" content="IE=edge;text/html; charset=utf-8"/>
    <meta name="viewport" content="IE=edge;width=device-width, initial-scale=1.0">    
    
	<c:set var="prefLang" value="<%=request.getSession().getAttribute(Constants.PREFERRED_LOCALE_KEY)%>"/>
	<c:set var="prefSkin" value="<%=request.getSession().getAttribute(Constants.USER_PREFERRED_SKIN)%>"/>
	<c:set var="loggedInUser" value="<%= SecurityContextHolder.getContext().getAuthentication().getPrincipal()%>"/>
	
	<script type="text/javascript" src="<c:url value='/scripts/boostrap/html5-trunk.js'/>"></script>
	<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/styles/bootstrap/icomoon/style.css'/>" />

	<!--[if lte IE 7]>
    <script type="text/javascript" src="<c:url value='/style/bootstrap/icomoon/lte-ie7.js'/>"></script>
    <![endif]-->
    
    <c:choose>
	<c:when test="${fn:contains(header['User-Agent'],'MSIE')}">
		<%@ include file = "/common/includeStyleForIE.jsp" %>
    	<%@ include file = "/common/includeScriptForIE.jsp" %>
	</c:when>
	<c:otherwise>
		<%@ include file = "/common/includeStyle.jsp" %>
    	<%@ include file = "/common/includeScript.jsp" %>
	</c:otherwise>
</c:choose>
    
    <decorator:head/>
    <style>
    @font-face{
		font-family: segoe ui;
		src: url('/styles/font/SegoeUI/segoeui.ttf'),
     		 url('/styles/font/SegoeUI/segoeuib.ttf'),
     		 url('/styles/font/SegoeUI/segoeuii.ttf'),
     		 url('/styles/font/SegoeUI/segoeuil.ttf'),
     		 url('/styles/font/SegoeUI/segoeuiz.ttf'),
     		 url('/styles/font/SegoeUI/seguisb.ttf'); 
		}
	
    </style>
<script type="text/javascript">
var _mainContainer = "target";
var _params = "";
var topMenuArray = new Array();

var chatWindowArray = [];
var chatWindowPosition = [];

var onlineUserArray = [];

function clearOnlineUserData(){
	onlineUserArray = new Array();
}

function loadOnlineUserData(loginUserName, loginUserFullName, loginUserEmailId, loginUserImage, loginUserPosition,  peerUserName, peerUserFullName, peerUserEmailId, peerUserImage, peerUserPosition){
    var chatWindowInfo = {
        peerUserName:peerUserName, 
        peerUserFullName:peerUserFullName,
        peerUserEmailId:peerUserEmailId,
        peerUserImage:peerUserImage,
        peerUserPosition:peerUserPosition,
        loginUserName:loginUserName,
        loginUserFullName:loginUserFullName,
        loginUserEmailId:loginUserEmailId,
        loginUserImage:loginUserImage,
        loginUserPosition:loginUserPosition,
    };
   /* if(onlineUserArray.length > 0){
        //alert("onlineUserArray==="+onlineUserArray.length);
        for(var i=0;i<onlineUserArray.length;i++){
            var onlineUser = onlineUserArray[i];
            var peercheck = (onlineUser.peerUserName).replace(/ /g,'');
            var peerUserNameChk = peerUserName.replace(/ /g,'');
            //alert("peercheck==="+peercheck+"==peerUserName="+peerUserName+"=="+peercheck.indexOf(peerUserNameChk));
            if((peercheck.indexOf(peerUserNameChk)) == -1){
                onlineUserArray.push(chatWindowInfo);    
            }
        }    
    }else{*/
        onlineUserArray.push(chatWindowInfo);    
   /*}*/
}

function loadSelectedOnlineUser(){
	var selectedOnlineUser = document.getElementById("searchOnlineUsers").value; 
	var insertSearchedUser = "";
	document.getElementById("members").innerHTML = "";
	//alert("selectedOnlineUser=="+selectedOnlineUser);
	if(selectedOnlineUser != ""){
		for(var i=0;i<onlineUserArray.length;i++){
			var onlineUser = onlineUserArray[i];
			var userFullName = onlineUser.peerUserFullName.toLowerCase();
			var checkIndex = userFullName.indexOf(selectedOnlineUser.toLowerCase());
			if(checkIndex >= 0){
				insertSearchedUser += "<li style='padding-top:14px;'><img style='float: left;height:25px;width:25px' alt=''  src='"+onlineUser.peerUserImage+"'><a href='#' style='display:-moz-inline-box; padding:2px; position:relative;' data-lastname='"+this.userFullName+"' data-status='online' onclick=\"javascript:createWindow('" + onlineUser.loginUserName + "', '" + onlineUser.loginUserFullName + "', '"+ onlineUser.loginUserEmailId +"', '" + onlineUser.loginUserImage + "', '" + onlineUser.loginUserPosition + "', '" + onlineUser.peerUserName + "', '"+onlineUser.peerUserFullName+"', '"+onlineUser.peerUserEmailId+"', '" + onlineUser.peerUserImage + "', '" + onlineUser.peerUserPosition + "');\" > <span>"+onlineUser.peerUserFullName+"</span></a>&nbsp;&nbsp;&nbsp;<small style='padding:4px;'>"+onlineUser.peerUserPosition+"</small></li>";
			}
		}
		document.getElementById("members").innerHTML = insertSearchedUser;
	}else{
		for(var i=0;i<onlineUserArray.length;i++){
			var onlineUser = onlineUserArray[i];
			insertSearchedUser += "<li style='padding-top:14px;'><img style='float: left;height:25px;width:25px' alt=''  src='"+onlineUser.peerUserImage+"'><a href='#' style='display:-moz-inline-box; padding:2px; position:relative;' data-lastname='"+this.userFullName+"' data-status='online' onclick=\"javascript:createWindow('" + onlineUser.loginUserName + "', '" + onlineUser.loginUserFullName + "', '"+ onlineUser.loginUserEmailId +"', '" + onlineUser.loginUserImage + "', '" + onlineUser.loginUserPosition + "', '" + onlineUser.peerUserName + "', '"+onlineUser.peerUserFullName+"', '"+onlineUser.peerUserEmailId+"', '" + onlineUser.peerUserImage + "', '" + onlineUser.peerUserPosition + "');\" > <span>"+onlineUser.peerUserFullName+"</span></a>&nbsp;&nbsp;&nbsp;<small style='padding:4px;'>"+onlineUser.peerUserPosition+"</small></li>";
		}
		document.getElementById("members").innerHTML = insertSearchedUser;
	}
}

var config = {
    contextPath: '${pageContext.request.contextPath}'
};

function getChatWindowByUserPair(loginUserName, peerUserName) {
	
	var chatWindow;
	
	for(var i = 0; i < chatWindowArray.length; i++) {
		var windowInfo = chatWindowArray[i];
		if (windowInfo.loginUserName == loginUserName && windowInfo.peerUserName == peerUserName) {
			chatWindow =  windowInfo.windowObj;
		}
	}
	
	return chatWindow;
}
var noOfChatWindows = 0;
var noOfCurrentlyOpenedWindows = 0;

function createWindow(loginUserName, loginUserFullName, loginUserEmailId, loginUserImage, loginUserPosition, peerUserName, peerUserFullName, peerUserEmailId, peerUserImage, peerUserPosition) {
	document.getElementById("searchOnlineUsers").value = "";
	noOfChatWindows += 1;
	var chatWindow = getChatWindowByUserPair(loginUserName, peerUserName);
	
	if (chatWindow == null) { //Not chat window created before for this user pair.
		noOfCurrentlyOpenedWindows +=1;
		chatWindow = new ChatWindow(); //Create new chat window.
		chatWindow.initWindow({
			loginUserName:loginUserName, 
			loginUserFullName:loginUserFullName,
			loginUserEmailId:loginUserEmailId,
			loginUserImage:loginUserImage,
			loginUserPosition:loginUserPosition,
			peerUserName:peerUserName,
			peerUserFullName:peerUserFullName,
			peerUserEmailId:peerUserEmailId,
			peerUserImage:peerUserImage,
			peerUserPosition:peerUserPosition,
			windowArray:chatWindowArray},noOfChatWindows);
		
		//collect all chat windows opended so far.
		var chatWindowInfo = { peerUserName:peerUserName, 
					       peerUserFullName:peerUserFullName,
					       peerUserEmailId:peerUserEmailId,
					       peerUserImage:peerUserImage,
					       peerUserPosition:peerUserPosition,
					       loginUserName:loginUserName,
				               loginUserFullName:loginUserFullName,
				               loginUserEmailId:loginUserEmailId,
				               loginUserImage:loginUserImage,
					       loginUserPosition:loginUserPosition,
				               windowObj:chatWindow 
				             };
		
		chatWindowArray.push(chatWindowInfo);
		chatWindowPosition.push(loginUserName + '_' +peerUserName +'_window');
	} else {
		var messageContainer = loginUserName + '_' +peerUserName;
		var windowId = messageContainer + '_window';
		if(document.getElementById(windowId).style.display == "none"){
			noOfCurrentlyOpenedWindows +=1;
			var footerId = messageContainer+"_footer";
	    	var headerId = messageContainer+"_header";
	    	var isVisible = $('#'+messageContainer).is(':visible');
	    	var isVisible = $('#'+messageContainer).is(':visible');
	    	if(isVisible == false){
	    		$('#'+headerId).removeClass('chat-header-active');
	    		$('#'+windowId).height(250);
	    		$('#'+messageContainer).show();
	    		$('#'+footerId).show();
	    	}
	    	var windowDIV = document.getElementById(windowId);
	    	if(noOfCurrentlyOpenedWindows > 1){
	    		windowDIV.style.right = ((noOfCurrentlyOpenedWindows-1)*15) - (noOfCurrentlyOpenedWindows-2) + "%" ;
	    	}else {
	    		windowDIV.style.right    = (noOfCurrentlyOpenedWindows)*1 + "%";
	    	}
			chatWindowPosition.push(loginUserName + '_' +peerUserName+'_window');
		}
	}
	
	chatWindow.show();
	loadAllChatUsers();
	return chatWindow;
}

function loadAllChatUsers(){
	var users = "";
	for(var i=0;i<onlineUserArray.length;i++){
		var onlineUser = onlineUserArray[i];
		users += "<li style='padding-top:14px;'><img style='float: left;height:25px;width:25px;' alt=''  src='"+onlineUser.peerUserImage+"'><a href='#' style='display:-moz-inline-box; padding:2px; position:relative;' data-lastname='"+this.userFullName+"' data-status='online' onclick=\"javascript:createWindow('" + onlineUser.loginUserName + "', '" + onlineUser.loginUserFullName + "', '"+ onlineUser.loginUserEmailId +"', '" + onlineUser.loginUserImage + "', '" + onlineUser.loginUserPosition + "', '" + onlineUser.peerUserName + "', '"+onlineUser.peerUserFullName+"', '"+onlineUser.peerUserEmailId+"', '" + onlineUser.peerUserImage + "', '" + onlineUser.peerUserPosition + "');\" > <span>"+onlineUser.peerUserFullName+"</span></a> &nbsp;&nbsp;&nbsp;<small style='padding:4px;'>"+onlineUser.peerUserPosition+"</small></li>";
	}
	document.getElementById("members").innerHTML = users;
}

function join(userName, fullName, email, image, position){
	$.cometChat.join(userName, fullName, email, image, position);
}

$.fn.slideFadeToggle = function(easing, callback) {
    return this.animate({ opacity: 'toggle', height: 'toggle' }, "fast", easing, callback);
};

function deselect() {
    $("#online_users_window").slideFadeToggle(function() { 
        $("#membersLink").removeClass("selected");
    });    
}

$(document).click(function(e) {
	if(filterURL.indexOf("processEditor") > -1){
		e.preventDefault();
	}
	if($(e.currentTarget.activeElement).attr('id') != "searchOnlineUsers"){
		if($('#membersLink').hasClass("selected")) {
			deselect();               
	    }
	}
});

window.onbeforeunload = function (e) {
        if(filterURL.indexOf("processEditor")> -1){
        	refresh = "true";
         }
};


$(document).ready(function () {
	var userName = '${pageContext.request.remoteUser}';
   	var fullName = '${loggedInUser.fullName}';
   	var email = '${loggedInUser.email}';
   	var position = '${loggedInUser.position}';
   	var profileImage = '${loggedInUser.pictureByteArrayId}';
   	$.cometChat.onLoad({memberListContainerID:'members'});
   	join(userName, fullName, email, profileImage, position);
	setSidebarHeight2();
	var index = 0;
	<c:forEach items='<%= this.getServletContext().getAttribute(assMenus) %>' var="menu">
		if("${menu.isParent}" == "true" && ("${menu.menuType}"=='top' || "${menu.menuType}"=='header') && "${menu.active}" == "true")	{
			var menu = "${menu.name}";
			var menuName = menu.toLowerCase();
			var menuName = menuName.replace(/\s+/g, '');
			topMenuArray[index] = menuName;
			index ++;
			$("#accordion_"+menuName).accordion({
				collapsible: true,
				animated: 'slide',
				autoHeight: false,
				navigation: true
			});
		}
	</c:forEach> 			
	for(var index = 0; index < topMenuArray.length; index++){
		var menuName = topMenuArray[index];
		if(menuName == 'home'){
			 $("#"+menuName+"_menu").show();
			 $("#"+menuName+"_top_menu").removeClass('top-menu').addClass('top-menu-active');
			 $("#"+menuName+"_menu_onselect_arrow").show();
			 $("#"+menuName+"_menu_onselect_menu2").show();
			 $("#"+menuName+"_menu_onselect_menu1").hide();
		}else{
			$("#"+menuName+"_menu").hide();
			$("#"+menuName+"_top_menu").removeClass('top-menu-active').addClass('top-menu');
			$("#"+menuName+"_menu_onselect_arrow").hide();
			$("#"+menuName+"_menu_onselect_menu1").show();
			$("#"+menuName+"_menu_onselect_menu2").hide();
		}
	}	
		
	$("#membersLink").live('click', function() {
		if($("#membersLink").hasClass("selected")) {
			deselect();               
		} else {
			$("#membersLink").addClass("selected");
			$("#online_users_window").slideFadeToggle();
		}
		return false;
	});

	/*  $(".close").live('click', function() {
	     deselect();
	     return false;
	 }); */
});
  
function getDefaultValForForm(type){
	var dayMap = {0:'Sunday', 1:'Monday', 2:'Tuesday', 3:'Wednesday', 4:'Thursday', 5:'Friday', 6:'Saturday'};
	var monthMap = {0:'January', 1:'February', 2:'March', 3:'April', 4:'May', 5:'June', 6:'July', 7:'August', 8:'September', 9:'October', 10:'November', 11:'December'};
	if(type.toLowerCase() == "time"){
		return '<fmt:formatDate value="<%= new java.util.Date() %>" pattern="HH:mm" />';
	}else if(type.toLowerCase() == "day"){
		var day = "<%= new java.util.Date().getDay() %>";
		return dayMap[day];
	}else if(type.toLowerCase() == "month"){
		var month = "<%= new java.util.Date().getMonth() %>";
		return monthMap[month];
	}else if(type.toLowerCase() == "year"){
		return '<fmt:formatDate value="<%= new java.util.Date() %>" pattern="yyyy" />';
	}else if(type.toLowerCase() == "date"){
		return '<fmt:formatDate value="<%= new java.util.Date() %>" pattern="yyyy-MM-dd" />';
	}else if(type.toLowerCase() == "datetime"){
		return '<fmt:formatDate value="<%= new java.util.Date() %>" pattern="yyyy-MM-dd HH:mm" />';
	}else if(type.toLowerCase() == "username"){
		return "${username}";
	}else if(type.toLowerCase() == "userlogin"){
		return "${userlogin}";
	}else if(type.toLowerCase() == "userid"){
		return "${userid}";
	}else if(type.toLowerCase() == "deptname"){
		return "${deptname}";
	}else if(type.toLowerCase() == "deptid"){
		return "${deptid}";
	}else if(type.toLowerCase() == "telephone"){
		return "${telephone}";
	}else if(type.toLowerCase() == "roleid"){
		return "${roleid}";
	}else if(type.toLowerCase() == "groupid"){
		return "${groupid}";
	}else if(type.toLowerCase() == "apppath"){
		return '<%=request.getRealPath("")%>';
	}
}
    
function setIndexPage(tempindexPage,menuType){
	$(".submenu li").removeClass('side-menu-active');
  	$(".submenu li a").removeClass('side-menu-active');
  	$("#"+tempindexPage).addClass('side-menu-active');
}
    
function _execute(container, requestedparams) {
	_mainContainer = container;
   	if(requestedparams != ""){
   		_params = requestedparams;
   	}
   	var currentURL = window.location.href;
	if(currentURL.indexOf("#bpm/admin/getAllDictionariesAsLabelValue") == -1){
    	destroyCKEditor();
	}
}
    
function showSideMenu(topMenu,tableId){
   	var activeIndex = 0;
   	if(tableId != ""){
   		if(document.getElementById(tableId)){
   			activeIndex=parseInt(document.getElementById(tableId).getAttribute('accordianindex'));	
   		}
   	}
   	for(var index = 0; index < topMenuArray.length; index++){
   		var menuName = topMenuArray[index];
   		if(menuName == topMenu){
   			 $("#"+menuName+"_menu").show();
   			 $("#"+menuName+"_top_menu").removeClass('top-menu').addClass('top-menu-active');
   			 $("#"+menuName+"_header_menu").removeClass('header-menu-background').addClass('header-menu-active');
   			 $("#"+menuName+"_menu_onselect_arrow").show();
   			 //$("#accordion_"+menuName).filter(":not(:has(.ui-state-active))").accordion("activate", 0);
   			 if(tableId != ""){
				 var east_container = document.getElementById(tableId);
				 if(east_container){
					 var parent_node_id = east_container.parentNode;
					 if(parent_node_id.parentNode.style.display){
						 if(parent_node_id.parentNode.style.display == "none"){
							$("#accordion_"+menuName).filter(":not(:has(.ui-state-active))").accordion("activate", activeIndex);	
							$("#accordion_"+menuName).accordion("activate", activeIndex);								 
						 }
					 } 
				 }
			 }/* else{
				$("#accordion_"+menuName).filter(":not(:has(.ui-state-active))").accordion("activate", activeIndex);	
			 	$("#accordion_"+menuName).accordion("activate", activeIndex);
			 } */
   			 $("#"+menuName+"_menu_onselect_menu1").hide();
   			 $("#"+menuName+"_menu_onselect_menu2").show();
   		}else{
   			$("#"+menuName+"_header_menu").removeClass('header-menu-active').addClass('header-menu-background');
   			$("#"+menuName+"_menu").hide();
   			$("#"+menuName+"_top_menu").removeClass('top-menu-active').addClass('top-menu');
   			$("#"+menuName+"_menu_onselect_arrow").hide();
   			$("#"+menuName+"_menu_onselect_menu1").show();
   			$("#"+menuName+"_menu_onselect_menu2").hide();
   		}
   	}	
}

loadLogo();    
function loadLogo(){
   	$.ajax({
   		url: 'bpm/fileupload/getLogo',
   	    type: 'GET',
   	 	dataType: 'json',
   		success : function(response) {
   			var res = eval(response);
   			if(res.length == 1){
				$("#bpm_logo").attr("src", res);
			}
   		}
   	});
}
</script>
</head>
<body<decorator:getProperty property="body.id" writeEntireProperty="true"/> class="eazybpmBody" id="default_body">
	<c:set var="currentMenu" scope="request"><decorator:getProperty property="meta.menu"/></c:set>
	<header id="header_menu">
		<a href="/" class="logo"><img id="bpm_logo" src="/images/logo/logo.png" alt="Logo" style="height:30px;"></a>
		<a href="#" class="department">Department : '${loggedInUser.department.viewName}'</a>
		<div id="mini-nav">
			<%@ include file="/common/headerMenu.jsp" %>  
			<div class="clearfix"></div>
		</div>
	</header>

	<div class="container-fluid">
		<div id="side_menu" class="left-sidebar hidden-tablet hidden-phone">
			<div class="user-details">
				<div class="user-img">
					<!-- <img src="/images/bootstrap/user-.png" class="" alt=""> -->
					<img src="${loggedInUser.pictureByteArrayId}" class="" alt="">
				</div>
				<div class="welcome-text">
					<span>Welcome</span>
					<p class="name">${loggedInUser.fullName}</p>
				</div>
			</div>
			<div class="content min-height-0px">
				<div id="accordion1" class="accordion no-margin">
					<div class="accordion-group">
						<div class="accordion-heading">
							<a href="#collapseOne" data-parent="#accordion1" data-toggle="collapse" class="accordion-toggle"> Online <span id="userCounter" class="label info-label label-success"></span></a>
						</div>
						<div class="accordion-body in collapse" id="collapseOne" style="height: auto;">
							<div class="accordion-inner">
								<div class="online-users">
									<div id="searchDiv"><span class="style1 fontColor" style="cursor: pointer;"><input type="text" id="searchOnlineUsers" name="searchOnlineUsers" class="style1 fontColor" style="width:91%" onkeyup="loadSelectedOnlineUser();" placeholder="Search Users" /></span><br /></div>
									<ul id="members" style="margin-bottom:14px;">
									</ul>
								</div>
							</div>
						</div>
					</div>
					<%@ include file="/common/mailMenu.jsp" %>
				</div>
			</div>
		</div>
		<div class="dashboard-wrapper" id="dashboard-wrapper">
			<div id="top_menu">
				<div id="main-nav">
		       		<%@ include file="/common/topMenu.jsp" %> 
		         <div class="clearfix"></div>
		       </div>
	       </div>
			<div class="main-container">
				<div class="navbar hidden-desktop">
					<div class="navbar-inner">
						<div class="container">
							<a data-target=".navbar-responsive-collapse" data-toggle="collapse" class="btn btn-navbar"> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span></a>
							<div class="nav-collapse collapse navbar-responsive-collapse">
								<%@ include file="/common/mobileMenu.jsp" %> 
							</div>
						</div>
					</div>
				</div>			
		
				<div class="row-fluid">
					<%-- <div class="span2" id="side_menu">
						<div class="span12">
							<div class="first">
								<%@ include file="/common/menu.jsp" %>  
								<div class="block" id="section-menu">
									<%@ include file="/common/sideMenu.jsp" %>
									<!-- <div style="position: absolute; bottom: 80px; left: 80px;">
										<img src="/styles/easybpm/images/logo_icon.png" width="51px" height="71px" />
									</div> -->
								</div>
				                 
							</div>
						</div>
					</div> --%>
					<div class="span12" id="ajax_loader">
						<div id="target_iframe_div" style="display:none;">
								<!-- <div class="span12" id="target_iframe_div_hd">
									<div class="titleBG">
										<span class="floatLeft fontSize14 pad-L10 pad-T10">Create Process</span>
										<span class="floatRight fontSize14 pad-L10 pad-T2 pad-R5">
							     			<div id="toggle-fullscreen-iframe" style="cursor: pointer;">
							     				<img  src="/images/easybpm/common/min.png" />
							     			</div> 
							     		</span>
										<div class="floatRight pad-T2">
											<ul id="tabs">
							     				<li id="tabs-process-1" class="selected" onclick="designView()">Design</li>
							     				<li id="tabs-process-2" onclick="sourceView()">Source</li>
							     			</ul>
										</div>
									</div>
								</div> -->
							<iframe id="target_iframe"></iframe>
			    		</div>
						<div id="target_div">
							<%-- <%@ include file="/common/messages.jsp" %>   --%>
							<div id="target">
								<%--@ include file="/WEB-INF/pages/user/homePage.jsp"--%> 
							</div>
							<div id="popupDialog" class="form-background"></div>	
							<div id="involveUsersPopupDialog"></div>
							<div id="userPopupDialog" class="form-background"></div>
							<div id="userPopupDialogManager"></div>
							<div id="userPopupDialogSecretary"></div>
							<div id="departmentPopupDialog"></div>
							<div id="partTimeDepartmentPopupDialog"></div>
							<div id="groupPopupDialog"></div>
							<div id="importPopupDialog"></div>
							<div id="importSQLPopupDialog"></div>
							<div id="importCSVPopupDialog"></div>
							
							<div id="exportMultiTablePopupDialog"></div>
							
							<div id="classificationPopupDialog"></div>
							<div id="processPreviewPopupDialog" style="text-align: center;vertical-align: middle;"></div>
							<div id="formAutoGenerationId"></div>
							<div id="tableRelationForeignKey"></div>
							<div id="doc_view_dialog" class="form-background"></div>
							<div id="print_preview" class="displayNone"></div>
			    		</div>
			    	</div>
					
				</div>
				
			</div>
		</div>
		<!-- dashboard-container -->
	</div>
	<input id="currentUserRoles" type="hidden" value="<%=currentRoles%>"  />
	<input id="currentUserId" type="hidden" value="<%=currentUserId%>"  />	
	<input id="userFullName" type="hidden" value="<%=userFullName%>"  />
	
	<!-- start new ui  -->
	<%-- <div class="container-fluid" id="parent_container">
		<div class="row-fluid">
		    <div class="span12">
				<div class="row-fluid head-menu" id="header_menu">
					<div class="span12 top-con-right" >
						<ul class="nav main">
							<c:forEach items="<%= this.getServletContext().getAttribute(assMenus) %>" var="menu">
								<c:if test="${menu.isParent==true && menu.menuType=='header' && menu.active == true}">	
									<li class="ic-dashboard">
										<c:choose>
											<c:when test="${menu.urlType == 'newTab'}">
												<a id="headNewTab" name="newTab" title="${menu.helpText}" href="${menu.menuUrl}" onClick="setIndexPage('${menu.indexPage}');checkMenu('${fn:replace(fn:toLowerCase(menu.name),' ','')}','${menu.urlType}','${menu.menuUrl}','${menu.indexPage}','','','','header');">
													<div id="${fn:replace(fn:toLowerCase(menu.name),' ','')}_header_menu" class="header-menu-background header-menu-border header-menu-padding">
														<img class="header-menu-name" id="${fn:replace(fn:toLowerCase(menu.name),' ','')}" src="${menu.iconPath1}" height="12px" width="12px"/>
														<span id="header-menu-name" class="header-menu-font">${menu.name}</span>
													</div>
												</a>											
											</c:when>
											<c:when test="${menu.urlType == 'listview' || menu.urlType == 'process'}">
												<a title="${menu.helpText}" href="javascript:void(0);" onClick="setIndexPage('${menu.indexPage}');checkMenu('${fn:replace(fn:toLowerCase(menu.name),' ','')}','${menu.urlType}','${menu.menuUrl}','${menu.indexPage}','${menu.listViewName}','${menu.listViewParam}','${menu.processName}','header');">
													<div id="${fn:replace(fn:toLowerCase(menu.name),' ','')}_header_menu" class="header-menu-background header-menu-border header-menu-padding">
														<img class="header-menu-name" id="${fn:replace(fn:toLowerCase(menu.name),' ','')}" src="${menu.iconPath1}" height="12px" width="12px"/>
														<span id="header-menu-name" class="header-menu-font">${menu.name}</span>
													</div>
												</a>											
											</c:when>
											<c:otherwise>
												<a title="${menu.helpText}" href="#bpm${menu.menuUrl}" onClick="setIndexPage('${menu.indexPage}');checkMenu('${fn:replace(fn:toLowerCase(menu.name),' ','')}','${menu.urlType}','${menu.menuUrl}','${menu.indexPage}','','','','header');">
													<div id="${fn:replace(fn:toLowerCase(menu.name),' ','')}_header_menu" class="header-menu-background header-menu-border header-menu-padding">
														<img class="header-menu-name" id="${fn:replace(fn:toLowerCase(menu.name),' ','')}" src="${menu.iconPath1}" height="12px" width="12px"/>
														<span id="header-menu-name" class="header-menu-font">${menu.name}</span>&nbsp;
													</div>
												</a>											
											</c:otherwise>
										</c:choose>
						 		 	</li>
								</c:if>
							</c:forEach> 
						</ul>
					    <div class="floatright" style="padding-right:1px;">
							<div class="floatleft marginleft10" >
								<ul class="inline-ul floatleft" >
								<li>
										<span class="style1">
											<div id="online_users_window" class="online_users_window pop">
												<div id="membersDiv">
													<div id="membersHead" class="chatBoxHeaderBackground padding5">Users Online</div>
													<div id="searchDiv"><span class="style1 fontColor" style="cursor: pointer;"><input type="text" id="searchOnlineUsers" name="searchOnlineUsers" class="style1 fontColor" style="width:91%" onkeyup="loadSelectedOnlineUser();" /></span><br /></div>
			    									<div id='members' class="padding5 overflow"></div>
		    									</div>
											</div>
											<img width="12px" height="12px" src="${loggedInUser.pictureByteArrayId}" id="chat">
											<span id="userCounter" style="color:#FCAA40;" >0</span>
											<a class="style1 style1-color header-menu-name" href="/members" id="membersLink" > Users Online</a>									
										</span>&nbsp;&nbsp;
									</li>
									<li class="dropdown">
										<span class="style1">
											&nbsp; Welcome 
											<c:if test="${pageContext.request.remoteUser != null}">
												<c:choose>
												    <c:when test = "${fn:length(loggedInUser.fullName) lt 25}">
												       <a class="style1 style1-color dropdown" href="javascript:void(0);" id="dropdownMenu" data-toggle="dropdown" >${loggedInUser.fullName}
    <b class="caret"></b>
  </a>
 												    </c:when>
												    <c:otherwise>
												         <a class="style1 style1-color" href="#bpm/admin/userform" onClick="_execute('target', 'from=profile');">${fn:substring(loggedInUser.fullName, 0, 22)} ... <b class="caret"></b></a>
												         
</span>
												    </c:otherwise>
												</c:choose>
											</c:if>
					                  
					                    <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
   <li><a tabindex="-1" href="#bpm/admin/userform" onClick="_execute('target', 'from=profile');">Profile</a></li>
  <li><a tabindex="-1" href="#bpm/admin/getChangePassword" onClick="_execute('target', '');">Change Password</a></li>
  <li class="dropdown-submenu dropdown-menu"><a tabindex="-1" href="#">Change Skin</a>
  <ul class="dropdown-menu" style=" border-radius: 0 0px 0px; left: -100%;   margin-left: -1px;   margin-top: -6px;  top: 0;">
  <li><a tabindex="-1" href="#" onClick="setUserPreferredSkin('');">Default</a></li>
  <li><a tabindex="-1" href="#" onClick="setUserPreferredSkin('red');">Red</a></li>
  <li><a tabindex="-1" href="#" onClick="setUserPreferredSkin('blue');">Blue</a></li>
  </ul>
  </li>
  <li class="dropdown-submenu dropdown-menu"><a tabindex="-1" href="#">Language</a>
  <ul class="dropdown-menu" style=" border-radius: 0 0px 0px; left: -100%;   margin-left: -1px;   margin-top: -6px;  top: 0;">
  <li><a tabindex="-1" href="#" onClick="setLocale('en');">English</a></li>
  <li><a tabindex="-1" href="#" onClick="setLocale('zh-CN');">Chinese</a></li>
  
  </ul>
   <li class="divider"></li>
  <li><a tabindex="-1" href="/logout" onClick="clearMenuDataFromCookie();window.top.logOut();" >Log Out</a></li>
  </ul>

									</li>
									
								</ul>
								<br />
							</div> 
							<div class="floatleft" style="padding-top:3px;">
								<a title="Logout" href="/logout" onclick="clearMenuDataFromCookie();window.top.logOut();"><img src="/styles/easybpm/images/logout.jpg" alt="Profile Pic" /></a>
							</div>
						</div>
					</div>
				</div>
				<div class="row-fluid" id="top_menu">
					<div class="span12 header-repeat">
						<div class="floatleft">
						<a onclick="_execute('target',''); showSideMenu('home');" href="#bpm/user/homePage" title="Home">
							<c:if test="${prefSkin == ''}">
								<img id="bpm_logo" src="/images/logo/logo.png" alt="Logo"/>
							</c:if>
							<c:if test="${prefSkin != ''}">
								<img id="bpm_logo" src="/images/logo/logo-${prefSkin}.png" alt="Logo"/>
							</c:if>
						</a>
						</div> 
						<div id="top-tab" style="padding-left:14.7%;">
							 <ul>
								  <c:forEach items="<%= this.getServletContext().getAttribute(assMenus) %>" var="topMenu">
										<c:if test="${topMenu.isParent==true && topMenu.menuType=='top' && topMenu.active == true}">	
											<li>
												<c:choose>
													<c:when test="${topMenu.urlType == 'newTab'}">
														<a id="topNewTab" name="newTab" title="${topMenu.helpText}" href="${topMenu.menuUrl}" onClick="setIndexPage('${topMenu.indexPage}');checkMenu('${fn:toLowerCase(topMenu.name)}','${topMenu.urlType}','${topMenu.menuUrl}','${topMenu.indexPage}','','','','top');">
																<div id="${fn:toLowerCase(topMenu.name)}_top_menu" class="top-menu">
													  		<img id="${fn:toLowerCase(topMenu.name)}_menu_onselect_menu1" class="top-menu-img" src="${topMenu.iconPath1}"  />
													  		<c:if test="${not empty topMenu.iconPath2}">
													  			<img id="${fn:toLowerCase(topMenu.name)}_menu_onselect_menu2" class="top-menu-img" src="${topMenu.iconPath2}" style="display:none;"/>
													  		</c:if>
													  		<c:if test="${empty topMenu.iconPath2}">
													  			<img id="${fn:toLowerCase(topMenu.name)}_menu_onselect_menu2" class="top-menu-img" src="${topMenu.iconPath1}" style="display:none;"/>
													  		</c:if>
													  		<img id="${fn:toLowerCase(topMenu.name)}_menu_onselect_arrow" style="display:none;" class="menu-onselect-arrow" src="/styles/easybpm/images/menu-onselect-arrow.png" />
													  		<span>${fn:toUpperCase(topMenu.name)}</span>
											 		 	</div>
									 		 		</a>
													</c:when>
													<c:when test="${topMenu.urlType == 'listview' || topMenu.urlType == 'process'}">
														<a title="${topMenu.helpText}" href="javascript:void(0);" onClick="setIndexPage('${topMenu.indexPage}');checkMenu('${fn:toLowerCase(topMenu.name)}','${topMenu.urlType}','${topMenu.menuUrl}','${topMenu.indexPage}','${topMenu.listViewName}','${topMenu.listViewParam}','${topMenu.processName}','top');">
																<div id="${fn:toLowerCase(topMenu.name)}_top_menu" class="top-menu">
													  		<img id="${fn:toLowerCase(topMenu.name)}_menu_onselect_menu1" class="top-menu-img" src="${topMenu.iconPath1}"  />
													  		<c:if test="${not empty topMenu.iconPath2}">
													  			<img id="${fn:toLowerCase(topMenu.name)}_menu_onselect_menu2" class="top-menu-img" src="${topMenu.iconPath2}" style="display:none;"/>
													  		</c:if>
													  		<c:if test="${empty topMenu.iconPath2}">
													  			<img id="${fn:toLowerCase(topMenu.name)}_menu_onselect_menu2" class="top-menu-img" src="${topMenu.iconPath1}" style="display:none;"/>
													  		</c:if>
													  		<img id="${fn:toLowerCase(topMenu.name)}_menu_onselect_arrow" style="display:none;" class="menu-onselect-arrow" src="/styles/easybpm/images/menu-onselect-arrow.png" />
													  		<span>${fn:toUpperCase(topMenu.name)}</span>
											 		 	</div>
									 		 		</a>
													</c:when>
													<c:otherwise>
														<a title="${topMenu.helpText}" href="#bpm${topMenu.menuUrl}" onClick="setIndexPage('${topMenu.indexPage}');checkMenu('${fn:toLowerCase(topMenu.name)}','${topMenu.urlType}','${topMenu.menuUrl}','${topMenu.indexPage}','','','','top');">
													<div id="${fn:toLowerCase(topMenu.name)}_top_menu" class="top-menu">
												  		<img id="${fn:toLowerCase(topMenu.name)}_menu_onselect_menu1" class="top-menu-img" src="${topMenu.iconPath1}"  />
												  		<c:if test="${not empty topMenu.iconPath2}">
												  			<img id="${fn:toLowerCase(topMenu.name)}_menu_onselect_menu2" class="top-menu-img" src="${topMenu.iconPath2}" style="display:none;"/>
												  		</c:if>
												  		<c:if test="${empty topMenu.iconPath2}">
												  			<img id="${fn:toLowerCase(topMenu.name)}_menu_onselect_menu2" class="top-menu-img" src="${topMenu.iconPath1}" style="display:none;"/>
												  		</c:if>
												  		<img id="${fn:toLowerCase(topMenu.name)}_menu_onselect_arrow" style="display:none;" class="menu-onselect-arrow" src="/styles/easybpm/images/menu-onselect-arrow.png" />
												  		<span>${fn:toUpperCase(topMenu.name)}</span>
										 		 	</div>
									 		 	</a>
									 			</c:otherwise>
												</c:choose>
								 		 	</li>
										</c:if>
								</c:forEach>
							 </ul>
						</div>
					</div>
				</div>
				
				<div class="row-fluid">
					<div class="span2" id="side_menu">
						<div class="span12">
							<div class="first">
								<%@ include file="/common/menu.jsp" %>  
								<div class="block" id="section-menu">
									<%@ include file="/common/sideMenu.jsp" %>
									<!-- <div style="position: absolute; bottom: 80px; left: 80px;">
										<img src="/styles/easybpm/images/logo_icon.png" width="51px" height="71px" />
									</div> -->
								</div>
				                 
							</div>
						</div>
					</div>
					<div class="span10" id="ajax_loader">
						<div class="span12 target-background" id="target_iframe_div" style="display:none;">
								<!-- <div class="span12" id="target_iframe_div_hd">
									<div class="titleBG">
										<span class="floatLeft fontSize14 pad-L10 pad-T10">Create Process</span>
										<span class="floatRight fontSize14 pad-L10 pad-T2 pad-R5">
							     			<div id="toggle-fullscreen-iframe" style="cursor: pointer;">
							     				<img  src="/images/easybpm/common/min.png" />
							     			</div> 
							     		</span>
										<div class="floatRight pad-T2">
											<ul id="tabs">
							     				<li id="tabs-process-1" class="selected" onclick="designView()">Design</li>
							     				<li id="tabs-process-2" onclick="sourceView()">Source</li>
							     			</ul>
										</div>
									</div>
								</div> -->
							<iframe id="target_iframe"></iframe>
			    		</div>
						<div class="span12 target-background" id="target_div">
							<%@ include file="/common/messages.jsp" %>  
							<div style="height: 100%;width: 100%;">
								<div class="span12">
									<div id="target">
										<%@ include file="/WEB-INF/pages/user/homePage.jsp"%> 
									</div>
									<div id="popupDialog" class="form-background"></div>	
									<div id="involveUsersPopupDialog"></div>
									<div id="userPopupDialog" class="form-background"></div>
									<div id="userPopupDialogManager"></div>
									<div id="userPopupDialogSecretary"></div>
									<div id="departmentPopupDialog"></div>
									<div id="partTimeDepartmentPopupDialog"></div>
									<div id="groupPopupDialog"></div>
									<div id="importPopupDialog"></div>
									<div id="importSQLPopupDialog"></div>
									<div id="importCSVPopupDialog"></div>
									
									<div id="exportMultiTablePopupDialog"></div>
									
									<div id="classificationPopupDialog"></div>
									<div id="processPreviewPopupDialog" style="text-align: center;vertical-align: middle;"></div>
									<div id="formAutoGenerationId"></div>
									<div id="tableRelationForeignKey"></div>
									<div id="doc_view_dialog" class="form-background"></div>
									<div id="print_preview" class="displayNone"></div>
									<decorator:body/> 
								</div>   
								<c:if test="${currentMenu == 'AdminMenu'}">
									<div class="span2">
										<menu:useMenuDisplayer name="Velocity" config="navlistMenu.vm" permissions="rolesAdapter">
										<menu:displayMenu name="AdminMenu"/>
										</menu:useMenuDisplayer>
									</div>
								</c:if>
							</div> 
			    		</div>
			    	</div>
		    	</div>
			</div>
		</div>
	</div> --%>
	<!-- end new ui  -->
	<%-- <input id="currentUserRoles" type="hidden" value="<%=currentRoles%>"  />
	<input id="currentUserId" type="hidden" value="<%=currentUserId%>"  />	
	<input id="userFullName" type="hidden" value="<%=userFullName%>"  />
	<div class="clear"></div> --%>

<script type="text/javascript">
$.cookie('windowHeight',$(window).height());

function goToInbox(type) {
	 if(type == "Mail") {
		 document.location.href = "#bpm/mail/jwma/showMailInbox?sessionType=email";
		 _execute('target','');
	 } else {
		 document.location.href = "#bpm/mail/jwma/showMailInbox?sessionType=internalMessage";
		 _execute('target','');
	 }
}
 function generateMailRemainder(layout,message,type) {

        var n = noty({
            text        : '<a onclick="goToInbox(\''+type+'\');"><img  src="/images/logo/mail.png" height="50px" width="50px" alt="EazyTec">'+message+'</a>',
            type        : 'information',
            dismissQueue: false,
            layout      : layout,
            theme       : 'defaultTheme'
        });
       
    }

$(document).ready(function(){
	var homeLoded = false;
	var windowLocation = window.location;
	if(window.location == windowLocation.protocol + '//' + windowLocation.hostname + ':' + windowLocation.port + windowLocation.pathname && homeLoded == false){
		/* checkMenu('home','url','/user/homePage','','','','','top','','top_menu_ff8081813c8630b9013c863c52110001','home');
		setBreadCrumb('home', '', '','/user/homePage');
		document.location.href = "#bpm/user/homePage";
		_execute('target', '');
		homeLoded = true; */
	}
	$('#headNewTab,#topNewTab,#sideMenuNewTab a').click(function(event) {
		event.preventDefault();
		var location = $(this).attr('name');
		var newTabUrl = $(this).attr('href');
		if(location == "newTab") {
			if(newTabUrl.charAt(0) == '/') {
				window.open("#bpm"+newTabUrl);
				_execute('target', '');	
			}else if(newTabUrl.toLowerCase().substring(0, 3) == 'www') {
				window.open("http://"+newTabUrl);
			    _execute('target', '');				
			}else if( (newTabUrl.toLowerCase().indexOf("http") > -1 ) || (newTabUrl.toLowerCase().indexOf("https") > -1 ) ) {
				window.open(newTabUrl);
			    _execute('target', '');				
			}/* else if(newTabUrl.toLowerCase().substring(0, 4) == '#bpm') {
				window.open("http://"+newTabUrl.substring(4));
			    _execute('target', '');				
			} */else {
				window.open("http://"+newTabUrl);
			    _execute('target', '');					
			}
		}else if(homeLoded == false){
			checkMenu('home','url','/user/homePage','','','','','top','','top_menu_ff8081813c8630b9013c863c52110001','home');
			setBreadCrumb('home', '', '','/user/homePage','','');
			document.location.href = "#bpm/user/homePage";
			_execute('target', '');
			homeLoded = true;
		}
	});
	
	var bpmMenuName = $.cookie('bpmMenuName');
    var bpmUrlType = $.cookie('bpmUrlType');
    var bpmMenuUrl = $.cookie('bpmMenuUrl');
    var bpmTableId = $.cookie('bpmTableId');
    var bpmListViewName = $.cookie('bpmListViewName');
    var bpmListViewParams = $.cookie('bpmListViewParams'); 
    var bpmProcessName=$.cookie('bpmProcessName');
    var bpmMenuType=$.cookie('bpmMenuType',bpmMenuType);
    
    var bpmSideMenuName=$.cookie('bpmSideMenuName');
	var bpmSideUrlType=$.cookie('bpmSideUrlType');
	var bpmSideAppBack=$.cookie('bpmSideAppBack');
	var bpmSideTableId=$.cookie('bpmSideTableId');
	var bpmSideListViewName=$.cookie('bpmSideListViewName');
	var bpmSideListViewParams=$.cookie('bpmSideListViewParams');
	var bpmSideProcessName=$.cookie('bpmSideProcessName');
	var bpmSideScript=$.cookie('bpmSideScript');
	var bpmSideMenuType=$.cookie('bpmSideMenuType');
	
	if(bpmTableId!=null && bpmTableId!="" && bpmMenuUrl!="" && bpmMenuUrl!=null){
		setIndexPage(bpmTableId);
        checkMenu(bpmMenuName,bpmUrlType,bpmMenuUrl,bpmTableId,bpmListViewName,bpmListViewParams,bpmProcessName,bpmMenuType,'');
 	}
    
    if(bpmSideTableId!=null && bpmSideTableId!="" && bpmSideUrlType!="" && bpmSideUrlType!=null){
    	if(bpmSideUrlType=='url'){
    		document.location.href = "#bpm"+bpmSideAppBack;
    	}else if(bpmSideUrlType=='newTab'){
    		document.location.href = bpmSideAppBack;
    	}
    	
 	  	showSideMenu(bpmMenuName,bpmSideTableId);
 	  	setTimeout(function(){setIndexPage(bpmSideTableId)},1000);
   		setTimeout(function(){checkMenu(bpmSideMenuName,bpmSideUrlType,bpmSideAppBack,bpmSideTableId,bpmSideListViewName,bpmSideListViewParams,bpmSideProcessName,bpmSideMenuType,bpmSideScript)},950);
   		
   		if(bpmSideUrlType=='script'){
   			var callback_function = new Function(bpmSideScript);
   			setTimeout(function(){callback_function()},1000);
   		}
   		resetSubSideMenu();
   	}else if(bpmMenuName!="" && bpmTableId!=null && bpmTableId!= ""){
   		showSideMenu(bpmMenuName, bpmTableId);
   		setTimeout(function(){checkMenu(bpmMenuName,bpmUrlType,'',bpmTableId,bpmListViewName,bpmListViewParams,bpmProcessName,bpmMenuType,'')},1050);
	}else if(homeLoded == false){
		checkMenu('home','url','/user/homePage','','','','','top','','top_menu_ff8081813c8630b9013c863c52110001','home');
		setBreadCrumb('home', '', '','/user/homePage','','');
	   	document.location.href = "#bpm/user/homePage";
	   	_execute('target','');
	   	homeLoded = true;
	}
    
    homeLoded = false;
    
});
 
$(window).load(function () {
    $('head').append('<link href="/images/favicon.ico" rel="shortcut icon" type="image/x-icon" />');
});
  
$( window ).resize(function() {
	setSidebarHeight2();
	var windowHeight = $(window).height();
	var windowOldHeight = $.cookie('windowHeight');
	var bpmMenuName = $.cookie('bpmMenuName');
    var bpmUrlType = $.cookie('bpmUrlType');
    var bpmMenuUrl = $.cookie('bpmMenuUrl');
    var bpmTableId = $.cookie('bpmTableId');
    var bpmListViewName = $.cookie('bpmListViewName');
    var bpmListViewParams = $.cookie('bpmListViewParams'); 
    var bpmProcessName=$.cookie('bpmProcessName');
    var bpmMenuType=$.cookie('bpmMenuType');
    var rootMenuUrl = $.cookie('rootMenuUrl');
    
    var bpmsidemenutype = $.cookie('bpmSideMenuType');
	var menutype="";
	var urlType = "";
	var listViewParams = "";
	var listViewName = "";
	var bpmAppBack = "";
	if(bpmsidemenutype == null || bpmsidemenutype == ""){
	menutype = bpmMenuType;
	} else {
	menutype = bpmsidemenutype;
	}
	if(menutype == "side" || menutype == "Side" ){
		urlType = $.cookie('bpmSideUrlType');
		listViewParams = $.cookie('bpmSideListViewParams');
		listViewName = $.cookie('bpmSideListViewName');
		var script = $.cookie('bpmSideScript');
		if(script != null || script !="null" || script != ''){
			
			var callback_function = new Function(script);
   			setTimeout(function(){callback_function();},100);
		}
	} else {
	urlType = $.cookie('bpmUrlType');
	listViewParams = $.cookie('bpmListViewParams');
	listViewName = $.cookie('bpmListViewName');
	}
    if(bpmMenuUrl != null && (windowOldHeight != windowHeight) && rootMenuUrl != "/user/homePage"){
		if((windowOldHeight < windowHeight) && (windowOldHeight < (parseInt(windowHeight)-100))){
			if(urlType == "listview" || urlType == "listView"){
		    	if(listViewParams != ""){
		    		var listViewParams = "[{"+listViewParams+"}]";
		    		var listViewNameAsTitle=listViewName;    	
		    		checkMenu(bpmMenuName,bpmUrlType,bpmMenuUrl,bpmTableId,listViewName,listViewParams,bpmProcessName,bpmMenuType,'');
		    		showListViewsWithContainerAndParam(listViewName, listViewNameAsTitle.replace('_',' '), 'target', eval(listViewParams));
		    	}else{
		    		var listViewNameAsTitle=listViewName;
		    		checkMenu(bpmMenuName,bpmUrlType,bpmMenuUrl,bpmTableId,listViewName,listViewParams,bpmProcessName,bpmMenuType,'');
		    		showListViewsWithContainer(listViewName, listViewNameAsTitle.replace('_',' '), 'target');
		    	}
		    } else {
		    	/*$.cookie('windowHeight',$(window).height());
		        checkMenu(bpmMenuName,bpmUrlType,bpmMenuUrl,bpmTableId,listViewName,listViewParams,bpmProcessName,bpmMenuType,'');
			   	document.location.href = '#'+window.filterURL+'';
			   	_execute('target','');*/
		    }
		} else if((windowOldHeight > windowHeight) && (windowOldHeight > (parseInt(windowHeight)+100))){
			if(urlType == "listview" || urlType == "listView"){
		    	if(listViewParams != ""){
		    		var listViewParams = "[{"+listViewParams+"}]";
		    		var listViewNameAsTitle=listViewName;    	
		    		$.cookie('bpmListViewName',listViewName);
		    		$.cookie('bpmUrlType',urlType);
		    		//checkMenu(bpmMenuName,bpmUrlType,bpmMenuUrl,bpmTableId,listViewName,listViewParams,bpmProcessName,bpmMenuType,'');
		    		showListViewsWithContainerAndParam(listViewName, listViewNameAsTitle.replace('_',' '), 'target', eval(listViewParams));
		    	}else{
		    		var listViewNameAsTitle=listViewName;
		    		//checkMenu(bpmMenuName,bpmUrlType,bpmMenuUrl,bpmTableId,listViewName,listViewParams,bpmProcessName,bpmMenuType,'');
		    		$.cookie('bpmListViewName',listViewName);
		    		$.cookie('bpmUrlType',urlType);
		    		showListViewsWithContainer(listViewName, listViewNameAsTitle.replace('_',' '), 'target');
		    	}
		    } else {
		    	/*$.cookie('windowHeight',$(window).height());
		        checkMenu(bpmMenuName,bpmUrlType,bpmMenuUrl,bpmTableId,listViewName,listViewParams,bpmProcessName,bpmMenuType,'');
			   	document.location.href =  '#'+window.filterURL+'';
			   	_execute('target','');*/
		    }
		}
		$.cookie('windowHeight',windowHeight);
 	} else if(rootMenuUrl == "/user/homePage") {
			
 		if((windowOldHeight < windowHeight) && (windowOldHeight < (parseInt(windowHeight)-100))){
			$.cookie('windowHeight',$(window).height());
	        checkMenu(bpmMenuName,bpmUrlType,bpmMenuUrl,bpmTableId,bpmListViewName,bpmListViewParams,bpmProcessName,bpmMenuType,'');
		   	document.location.href = '#'+window.filterURL+'';
		   	_execute('target','');
		} else if((windowOldHeight > windowHeight) && (windowOldHeight > (parseInt(windowHeight)+100))){
			$.cookie('windowHeight',$(window).height());
	        checkMenu(bpmMenuName,bpmUrlType,bpmMenuUrl,bpmTableId,bpmListViewName,bpmListViewParams,bpmProcessName,bpmMenuType,'');
		   	document.location.href =  '#'+window.filterURL+'';
		   	_execute('target','');
		}
 		$.cookie('windowHeight',windowHeight);
 	}
 	
});

/* $(".submenu li").click(function(){
	 $(".submenu li").removeClass('side-menu-active');
	 $(".submenu li a").removeClass('side-menu-active');
	 $(this).addClass('side-menu-active');
}); */

</script>
</body>
</html>
