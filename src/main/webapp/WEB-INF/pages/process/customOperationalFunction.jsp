<%@ include file="/common/taglibs.jsp"%>

<%-- <page:applyDecorator name="default"> --%>

<%-- <head>
    <title><fmt:message key="createCustomFunction.title"/></title>
    <meta name="heading" content="<fmt:message key='createCustomFunction.title'/>"/>
</head> --%>

    <script language="javascript">
    $(document).ready(function(){
    	var filePath="${customOperatingFunction.pictureByteArrayId}";
    	if(filePath!=""){
    		var fileName=filePath.split('/');
        	$("#importCustomPictureName").val(fileName[fileName.length-1]);
    	}
    });
    
    </script>
     
<%-- <div class="span12 box ">
 	Error Messages
<c:if test="${not empty errors}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:forEach var="error" items="${errors}">
            <c:out value="${error}"/><br />
        </c:forEach>
    </div>
    <c:remove var="errors"/>
    <script type="text/javascript">
    var error = "";
    <c:forEach var="error" items="${errors}">
    	error = error + "<c:out value='${error}' escapeXml='false'/> <br />";
	</c:forEach>
	validateMessageBox("Error", error, "error");
    </script>
     <c:remove var="errors"/>
</c:if>

Success Messages
<c:if test="${not empty successMessages}">
    <div class="alert alert-success fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:forEach var="msg" items="${successMessages}">
            <c:out value="${msg}"/><br />
        </c:forEach>
    </div>
    <c:remove var="successMessages" scope="session"/>
    <script type="text/javascript">
    var msg = "";
    <c:forEach var="error" items="${successMessages}">
    	msg = msg + "<c:out value='${error}' escapeXml='false'/> <br />";
	</c:forEach>
	validateMessageBox("Success", msg, "success");
	showListViews("CUSTOM_OPTION_LIST","Custom Operation");
    </script>
     <c:remove var="successMessages" scope="session"/> 
</c:if>

<c:if test="${not empty status.errorMessages}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:forEach var="error" items="${status.errorMessages}">
            <c:out value="${error}" escapeXml="false"/><br/>
        </c:forEach>
    </div>
   <script type="text/javascript">
    var error = "";
    <c:forEach var="error" items="${status.errorMessages}">
    	error = error + "<c:out value='${error}' escapeXml='false'/> <br />";
	</c:forEach>
	validateMessageBox("Error", error , "error");
	
    </script>
</c:if>

<c:set var="pathInfo" value='<%=request.getAttribute("javax.servlet.forward.servlet_path")%>'/>
<script type="text/javascript">
var path = "<c:out value='${pathInfo}' escapeXml='false'/>";
setIndexPageByRedirect(path);
</script>
  
<c:if test="${empty customOperatingFunction.id}">
	<h2 id="createCustomFunctionHeader"><fmt:message key="createCustomFunction.title"/>
	<a id="backToPreviousPage" href="javascript:void(0);" data-role="button" data-theme="b"  onClick="showListViews('CUSTOM_OPTION_LIST','Custom Operation');" data-icon=""><fmt:message key="Back"/></a> 	
	</h2>
 </c:if>
 
 <c:if test="${not empty customOperatingFunction.id}">
			<h2 id="updateCustomFunctionHeader"><fmt:message key="updateCustomFunction.title"/></h2> 
         </c:if>
 
 <div class="span7 scroll">
		<div style="padding-left:100px;">
		 <form:form name="customOperatingFunction" id="customOperatingFunction" modelAttribute="customOperatingFunction" method="post" action="bpm/customOperating/saveCustomOperatingFunction" enctype="multipart/form-data" >
		 <form:hidden path="id"/>
		 <form:hidden path="pictureByteArrayId"/>
			<table width="100%">
			<tr height="20px;"></tr>
				<tr>
					<td class="fontSize14"><eazytec:label styleClass="style3 style18" key="customOperatingFunction.name"/></td>
					 <td class="uneditable-input1">			
					 			<c:if test="${empty customOperatingFunction.id}">
					                <form:input path="name" id="name" class="medium"/>
					            </c:if>
					            
					            <c:if test="${not empty customOperatingFunction.id}">
					               <form:input path="name" id="name" readonly="true"  class="medium"/> 
					            </c:if>
					</td>	
				</tr>
				 <tr height="10px;"></tr>
				<tr>
					<td class="fontSize14"><eazytec:label styleClass="control-label style3 style18 " key="customOperatingFunction.callFunction"/></td>
					<td width="436" class="uneditable-input1"><form:input path="callFunction" id="callFunction" class="medium"/></td>	
				</tr>	
			 	<tr height="10px;"></tr>
				<tr>
					<td class="fontSize14"><eazytec:label styleClass="control-label style3 style18 " key="customOperatingFunction.jsFunction"/></td>
					<td width="436" class="uneditable-input1"><form:textarea path="jsFunction" id="jsFunction" cols="18" class="medium" rows="5"/></td>	
				</tr>	
				 <tr height="10px;"></tr>
				<tr>
					<td class="fontSize14"><eazytec:label styleClass="control-label style3 style18 " key="customOperatingFunction.htmlProperty"/></td>
					<td width="436" class="uneditable-input1"><form:textarea path="htmlProperty" id="htmlProperty" cols="18" class="medium" rows="3"/></td>	
				</tr>

				<tr>
					<td class="fontSize14"><eazytec:label styleClass="control-label style3 style18 " key="customOperatingFunction.picture"/></td>	
					<td><div class="pad-T20"><input type="text" class="medium" id="importCustomPictureName" readonly="readonly"/><input type="file" name="file" id="importCustomPicture" multiple="multiple" class="bpm_import_file" size="23"/></div></td>
				</tr>
				 <tr height="10px;"></tr>
				  <tr id="descriptionRow">
       			 	<td width="271" height="40" ><eazytec:label styleClass="control-label style3 style18 " key="metaTable.description"/></td>
			         <td width="436" class="uneditable-input1"><form:textarea path="description" id="description" cols="18" class="medium" rows="3"/></td>
			    </tr>
				<tr>							
					<td ></td><td>
					<div align="left" class="pad-T20">
						
						<c:if test="${empty customOperatingFunction.id}">
					               <button type="submit"  class="btn btn-primary normalButton padding0 line-height0">
							         		<fmt:message key="button.save"/>
						         	</button>
					            </c:if>
					          <c:if test="${not empty customOperatingFunction.id}">
					                 <button type="submit"  class="btn btn-primary normalButton padding0 line-height0">
							         		<fmt:message key="button.update"/>
						         	</button>
					            </c:if>
					
						<button type="submit" onclick="showListViews('CUSTOM_OPTION_LIST', 'Custom Operation');" class="btn btn-primary normalButton padding0 line-height0">
							         		<fmt:message key="button.cancel"/>
						         	</button>
					</div>
					</td>
				</tr>						
			</table>
		</form:form>
		</div>
	</div> 
</div> --%>

<div class="page-header">
	<div class="pull-left">
		<c:if test="${empty customOperatingFunction.id}">
			<h2 id="createCustomFunctionHeader"><fmt:message key="createCustomFunction.title"/></h2>
		</c:if>
 
 		<c:if test="${not empty customOperatingFunction.id}">
			<h2 id="updateCustomFunctionHeader"><fmt:message key="updateCustomFunction.title"/></h2> 
 		</c:if>
	</div>
</div>
	<div class="height10"></div>
	<div align="right"><a style="padding:10px;text-decoration: underline;"  id="backToPreviousPage" href="javascript:void(0);" data-role="button" data-theme="b"  onClick="showListViews('CUSTOM_OPTION_LIST','Custom Operation');" data-icon=""><strong><fmt:message key="Back"/></strong></a> </div>	 
	<%-- Error Messages --%>
<c:if test="${not empty errors}">
    <%-- <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:forEach var="error" items="${errors}">
            <c:out value="${error}"/><br />
        </c:forEach>
    </div>
    <c:remove var="errors"/> --%>
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
    <%-- <div class="alert alert-success fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:forEach var="msg" items="${successMessages}">
            <c:out value="${msg}"/><br />
        </c:forEach>
    </div>
    <c:remove var="successMessages" scope="session"/> --%>
    <script type="text/javascript">
    var msg = "";
    <c:forEach var="error" items="${successMessages}">
    	msg = msg + "<c:out value='${error}' escapeXml='false'/> <br />";
	</c:forEach>
	validateMessageBox("Success", msg, "success");
	showListViews("CUSTOM_OPTION_LIST","Custom Operation");
    </script>
     <c:remove var="successMessages" scope="session"/> 
</c:if>

<c:if test="${not empty status.errorMessages}">
    <%-- <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:forEach var="error" items="${status.errorMessages}">
            <c:out value="${error}" escapeXml="false"/><br/>
        </c:forEach>
    </div> --%>
   <script type="text/javascript">
    var error = "";
    <c:forEach var="error" items="${status.errorMessages}">
    	error = error + "<c:out value='${error}' escapeXml='false'/> <br />";
	</c:forEach>
	validateMessageBox("Error", error , "error");
	
    </script>
</c:if>

<c:set var="pathInfo" value='<%=request.getAttribute("javax.servlet.forward.servlet_path")%>'/>
<script type="text/javascript">
var path = "<c:out value='${pathInfo}' escapeXml='false'/>";
setIndexPageByRedirect(path);
</script>
  
 
		<div class="row-fluid">
			<div class="widget">
				<div class="widget-body ">
				<form:form name="customOperatingFunction" id="customOperatingFunction" cssClass="form-horizontal no-margin" modelAttribute="customOperatingFunction" method="post" action="bpm/customOperating/saveCustomOperatingFunction" enctype="multipart/form-data" >
		 			<form:hidden path="id"/>
		 			<form:hidden path="pictureByteArrayId"/>
				
					<div class="control-group">
					    <eazytec:label styleClass="control-label"  key="customOperatingFunction.name"/>
						<div class="controls">
			                <c:if test="${empty customOperatingFunction.id}"> 
					        <form:input path="name" id="name" class="span6"/>
					        </c:if>
					            
					        <c:if test="${not empty customOperatingFunction.id}">
					            <form:input path="name" id="name" readonly="true"  class="span6"/> 
					        </c:if>
			    	    </div>
		  			</div>
					
					<div class="control-group">
					    <eazytec:label styleClass="control-label" key="customOperatingFunction.callFunction"/>
						<div class="controls">
			                <form:input path="callFunction" id="callFunction" class="span6"/>
					    </div>
			   		</div>
			    
			    	<div class="control-group">
					    <eazytec:label styleClass="control-label " key="customOperatingFunction.jsFunction"/>
				    	<div class="controls">
			                <form:textarea path="jsFunction" id="jsFunction"  class="span6" rows="5"/>	
					  	</div>
					</div>
				
				    <div class="control-group">
					    	<eazytec:label styleClass="control-label" key="customOperatingFunction.htmlProperty"/>	
				    	<div class="controls">
			                <form:textarea path="htmlProperty" id="htmlProperty"  class="span6" rows="3"/>
					 	</div>
			   		</div>
					
					<div class="control-group">
					    <eazytec:label styleClass="control-label" key="customOperatingFunction.picture"/> 		
				    	<div class="controls">
			                <input type="text" class="medium" id="importCustomPictureName" readonly="readonly"/>
			                <input type="file" name="file" id="importCustomPicture" multiple="multiple" class="bpm_import_file" size="23"/>
					    </div>
			   		</div>
			   		
			   		<div class="control-group" id="descriptionRow">
					    <eazytec:label styleClass="control-label style3 style18 " key="metaTable.description"/>		
				    	<div class="controls">
			                 <form:textarea path="description" id="description"  class="span6" rows="3"/>
					    </div>
			   		</div>
			   		<div class="control-group">
							<div class="form-actions no-margin">
								<button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false;" id="saveButton">
                                 	<c:choose>
                                        <c:when test ="${not empty customOperatingFunction.id}">
                                        	<fmt:message key="button.update"/>
                                        </c:when>
                                        <c:otherwise>
                                            <fmt:message key="button.save"/>
                                        </c:otherwise>
                                 	</c:choose>     	
                            	</button>
                            	<button type="button" class="btn btn-primary " name="next" onclick="showListViews('CUSTOM_OPTION_LIST','Custom Operation');" id="cancelButton" style="cursor: pointer;">
			        				<fmt:message key="button.cancel"/>
			        			</button>
								<div class="clearfix"></div>
							</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>


<%-- </page:applyDecorator> --%>
