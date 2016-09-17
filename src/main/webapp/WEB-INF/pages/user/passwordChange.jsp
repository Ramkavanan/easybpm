<%@ include file="/common/taglibs.jsp"%>
<%-- <page:applyDecorator name="default"> --%>
<head>
    <title><fmt:message key="documentForm.title"/></title>
    <meta name="heading" content="<fmt:message key='documentForm.title'/>"/>
</head>
<script type="text/javascript">
    
</script>
<div class="span12">

	<div class="row-fluid">
		<div class="span12">
			<div class="titleBG">
				<span class="floatLeft fontSize14 pad-L10 pad-T10">Change Password</span>
			</div>	
		</div>
	</div>
	<div class="span12 box">
	 	<spring:bind path="userObject.*">
	        <%@ include file="/common/messages.jsp" %> 
	    </spring:bind>
	
		<div class="span12 scroll">
			<div class="row-fluid">
				  <form:form  id="userObj" commandName="userObj" method="post" action="" autocomplete="off" enctype="multipart/form-data" onSubmit="_execute('target','')">
					 <div class="row-fluid control-group">
						<div class="span2"> </div>
				    	<div class="span2">
				    		<eazytec:label styleClass="control-label style3 style18" key="userObjecttyt.oldPassword"/>
				    	</div>
				    	<div class="span1"> </div>    	
					</div>
						
					 <div class="span7">
	    		  		<button type="submit" class="btn btn-primary" name="save" id="save">
		                    <c:choose>
		                         <c:when test ="${not empty userObject.id}">
		                                 <fmt:message key="button.update"/>
		                         </c:when>
		                         <c:otherwise>
		                                 <fmt:message key="button.save"/>
		                         </c:otherwise>
		                    </c:choose>
		               	</button>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>


