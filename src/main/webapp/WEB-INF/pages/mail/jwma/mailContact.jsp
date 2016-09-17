<%@ include file="/common/taglibs.jsp"%>


<%-- <head>
    <title><fmt:message key="mailContact.title"/></title>
    <meta name="heading" content="<fmt:message key='emailContact.title'/>"/>
</head> --%>

<script type="text/javascript">

	
</script>
<%-- 
<div class="span12 box ">
<h2><fmt:message key="emailContact.heading"/></h2>
    <spring:bind path="emailContact.*">
        <%@ include file="/common/messages.jsp" %>
        <fmt:message key="jwma.errorMessages"/>
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
		
		<fmt:message key="jwma.successMessages"/>
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
			showListViews('CONTACTS','Contacts');
		    </script>
		     <c:remove var="successMessages" scope="session"/> 
		</c:if>
		<c:if test="${!newTab}">
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
		</c:if>
		<c:set var="pathInfo" value='<%=request.getAttribute("javax.servlet.forward.servlet_path")%>'/>
		<script type="text/javascript">
		var path = "<c:out value='${pathInfo}' escapeXml='false'/>";
		setIndexPageByRedirect(path);
		</script>
    </spring:bind>
 	<div class="span11 scroll">
		<div class="table-create-user">
			<form:form  id="emailContact" commandName="emailContact" method="post" action="/bpm/mail/jwma/saveMailContact" autocomplete="off" modelAttribute="emailContact" onSubmit="_execute('target','');">
				<form:hidden path="id"/>
				<form:hidden path="createdBy"/>
				<table width="100%" border="1" class="line-height46">
					<tr>
					    <td width="271" height="40" >
					    	<eazytec:label styleClass="control-label style3 style18" key="emailContact.firstName"/>
						<td width="436" class="uneditable-input1">
			                <form:input path="firstName" id="firstName" class="medium"/>
			    	    </td>
		  			</tr>
					
					<tr>
				    	<td width="271" height="40" >
				    		<eazytec:label styleClass="control-label style3 style18" key="emailContact.lastName"/>
				    	</td>
						<td width="436" class="uneditable-input1">
			                <form:input path="lastName" id="lastName" class="medium"/>
					    </td>
			   		</tr>
			    
			    	<tr>
				    	<td width="271" height="40" >
				    		<eazytec:label styleClass="control-label style3 style18" key="emailContact.englishName"/>
				    	</td>
						<td width="436" class="uneditable-input1">
			                <form:input path="englishName" id="englishName" class="medium"/>
					    </td>
			   		</tr>
			   		
			    	<tr>
				    	<td width="271" height="40" >
				    		<eazytec:label styleClass="control-label style3 style18" key="emailContact.email"/>
				    	</td>
						<td width="436" class="uneditable-input1">
			                <form:input path="email" id="email" class="medium"/>
					    </td>
			   		</tr>
					
					<tr>
				    	<td width="271" height="40" >
				    		<eazytec:label styleClass="control-label style3 style18" key="emailContact.mobile"/>
				    	</td>
						<td width="436" class="uneditable-input1">
			                <form:input path="mobile" id="mobile" class="medium"/>
					    </td>
			   		</tr>
			   		
			   		<tr>
				    	<td width="271" height="40" >
				    		<eazytec:label styleClass="control-label style3 style18" key="emailContact.workPhone"/>
				    	</td>
						<td width="436" class="uneditable-input1">
			                <form:input path="workPhone" id="workPhone" class="medium"/>
					    </td>
			   		</tr>
			   		
			   		<tr>
				    	<td width="271" height="40" >
				    		<eazytec:label styleClass="control-label style3 style18" key="emailContact.homePhone"/>
				    	</td>
						<td width="436" class="uneditable-input1">
			                <form:input path="homePhone" id="homePhone" class="medium"/>
					    </td>
			   		</tr>
			   		
			   		<tr>
				    	<td width="271" height="40" >
				    		<eazytec:label styleClass="control-label style3 style18" key="emailContact.fax"/>
				    	</td>
						<td width="436" class="uneditable-input1">
			                <form:input path="fax" id="fax" class="medium"/>
					    </td>
			   		</tr>
					
					<tr>
				    	<td width="271" height="40" >
				    		<eazytec:label styleClass="control-label style3 style18" key="emailContact.website"/>
				    	</td>
						<td width="436" class="uneditable-input1">
			                <form:input path="website" id="website" class="medium"/>
					    </td>
			   		</tr>
			   		
					<tr>
					<td></td>
						<td width="436">
							<button type="submit" class="btn btn-primary normalButton padding0 line-height0" name="save" id="saveButton">
			        		<c:choose>
                                    <c:when test ="${emailContact.id != null && emailContact.id!= ''}">
                                            <fmt:message key="button.update"/>
                                    </c:when>
                                    <c:otherwise>
                                            <fmt:message key="button.save"/>
                                    </c:otherwise>
                            </c:choose>
			    		</button>
						</td>
					</tr>
				</table>  
			</form:form>
		</div>
	</div>
</div> --%>

<div class="page-header">
	
		<h2><fmt:message key="emailContact.heading"/></h2>
	
</div>
	
	<div align="right"><strong><a class="padding10" style="text-decoration: underline;" id="backToPreviousPage" href="javascript:void(0);" data-role="button" data-theme="b"  onClick="showListViews('CONTACTS','Contacts');" data-icon=""><fmt:message key="Back"/></a></strong>
 </div>
 <div class="height10"></div>
	<spring:bind path="emailContact.*">
        <%-- <%@ include file="/common/messages.jsp" %> --%>
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
			showListViews('CONTACTS','Contacts');
		    </script>
		     <c:remove var="successMessages" scope="session"/> 
		</c:if>
		<c:if test="${!newTab}">
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
		</c:if>
		<c:set var="pathInfo" value='<%=request.getAttribute("javax.servlet.forward.servlet_path")%>'/>
		<script type="text/javascript">
		var path = "<c:out value='${pathInfo}' escapeXml='false'/>";
		setIndexPageByRedirect(path);
		</script>
    </spring:bind>
		<div class="row-fluid">
			<div class="widget">
				<div class="widget-body ">
				<form:form  id="emailContact" commandName="emailContact" method="post" cssClass="form-horizontal no-margin" action="/bpm/mail/jwma/saveMailContact" autocomplete="off" modelAttribute="emailContact" onSubmit="_execute('target','');">
					<form:hidden path="id"/>
					<form:hidden path="createdBy"/>
				
					<div class="control-group">
					    <eazytec:label styleClass="control-label" key="emailContact.firstName"/>
						<div class="controls">
			                <form:input path="firstName" id="firstName" class="span6"/>
			    	    </div>
		  			</div>
					
					<div class="control-group">
					    <eazytec:label styleClass="control-label"  key="emailContact.lastName"/>
						<div class="controls">
			                <form:input path="lastName" id="lastName" class="span6"/>
					    </div>
			   		</div>
			    
			    	<div class="control-group">
					    <eazytec:label styleClass="control-label"  key="emailContact.englishName"/>    		
				    	<div class="controls">
			                <form:input path="englishName" id="englishName" class="span6"/>
					  	</div>
					</div>
					
				    <div class="control-group">
					    <eazytec:label styleClass="control-label" key="emailContact.email"/>    		
				    	<div class="controls">
			                <form:input path="email" id="email" class="span6"/>
					 	</div>
			   		</div>
					
					<div class="control-group">
					    <eazytec:label styleClass="control-label" key="emailContact.mobile"/>    		
				    	<div class="controls">
			                <form:input path="mobile" id="mobile" class="span6"/>
					    </div>
			   		</div>
			   		
			   		<div class="control-group">
					    <eazytec:label styleClass="control-label" key="emailContact.workPhone"/>    		
				    	<div class="controls">
			                <form:input path="workPhone" id="workPhone" class="span6"/>
					    </div>
			   		</div>
			   		
			   		<div class="control-group">
					    <eazytec:label styleClass="control-label" key="emailContact.homePhone"/>    		
				    	<div class="controls">
			                <form:input path="homePhone" id="homePhone" class="span6"/>
					   </div>
					</div>
			   		
			   		<div class="control-group">
					    <eazytec:label styleClass="control-label" key="emailContact.fax"/>    		
				    	<div class="controls">
			                <form:input path="fax" id="fax" class="span6"/>
						</div>
					</div>
					
					<div class="control-group">
					    <eazytec:label styleClass="control-label" key="emailContact.website"/>    		
				    	<div class="controls">
			                <form:input path="website" id="website" class="span6"/>
					    </div>
			   		</div>
			   		
					<div class="control-group">	
				    	<div class="form-actions no-margin">
							<button type="submit" class="btn btn-primary" name="save" id="saveButton">
			        			<c:choose>
                                    <c:when test ="${emailContact.id != null && emailContact.id!= ''}">
                                    	<fmt:message key="button.update"/>
                                    </c:when>
                                    <c:otherwise>
                                    	<fmt:message key="button.save"/>
                                    </c:otherwise>
                            	</c:choose>
			    			</button>
			    			<button type="button" class="btn btn-primary " name="next"
								 onClick="showListViews('CONTACTS','Contacts');" id="cancelButton"
								style="cursor: pointer;">
								<fmt:message key="button.cancel" />
							</button>
							
			    			<div class="clearfix"></div>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>


<%-- </page:applyDecorator> --%>
<v:javascript formName="emailContact" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value="/scripts/validator.jsp"/>"></script>
