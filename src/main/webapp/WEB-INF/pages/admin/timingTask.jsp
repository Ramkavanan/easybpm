<%@ include file="/common/taglibs.jsp"%>


<div class="page-header">
	<h2>
		<fmt:message key="timingTask.heading" />
	</h2>
</div>
<div align="right">
	<strong><a id="backToPreviousPage" href="javascript:void(0);" style="text-decoration: underline; padding: 10px;" data-role="button" data-theme="b"  
		onClick="showListViews('TIMING_TASK','Timing Task');"><button class="btn"><fmt:message key="button.back"/></button></a></strong> 	
</div>	
	<spring:bind path="timingTask.*">
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
	validateMessageBox(form.title.error, error, "error");
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
	showListViews("TIMING_TASK","Timing Task");
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
	validateMessageBox(form.title.error, error , "error");
	
    </script>
</c:if>

<c:set var="pathInfo" value='<%=request.getAttribute("javax.servlet.forward.servlet_path")%>'/>
<script type="text/javascript">
var path = "<c:out value='${pathInfo}' escapeXml='false'/>";
setIndexPageByRedirect(path);
</script>
		
		
	</spring:bind>

	<!-- <div class="span7 scroll">
		<div class="table-create-user">
			<div id="target" class="span10" style="padding-left: 20PX;"> -->
	<div class="span12">
	<div class="row-fluid">
				<div class="widget-body">
	<div class="box_main" style="padding-top: 10px">

				<form:form id="timingTask" commandName="timingTask" method="post"
					autocomplete="off" modelAttribute="timingTask" cssClass="form-horizontal" 
					action="bpm/admin/saveTimingTask">
					<form:hidden path="jobName" id="jobName" value="${jobNameHidden}" />

					<div class="control-group">
						<eazytec:label styleClass="control-label" key="timingTask.name" />
						<div class="controls">
							<c:if test="${mode != 'EDIT'}">
								<form:input path="name" name="name" id="name" class="span5" />
							</c:if>
							<c:if test="${mode == 'EDIT'}">
								<form:input path="name" name="name" id="name" readonly="true"
									class="span5" />
							</c:if>

						</div>
					</div>
					<div class="control-group">
						<eazytec:label styleClass="control-label" key="timingTask.description" />
						<div class="controls">				
								<form:textarea path="description" id="description" class="span5" />
						</div>
					</div>
					<div class="control-group">
					
						<eazytec:label styleClass="control-label" key="timingTask.jobClassName" />
					
						<div class="controls">
							<!-- <b> Example : com.eazytec.quartz.SchedulerNotificationJob </b> -->
							<c:if test="${mode != 'EDIT'}">
				                <form:textarea path="jobClassName" name="jobClassName" id="jobClassName" class="span5" />
				                </c:if>
				                <c:if test="${mode == 'EDIT'}">
				                <form:textarea path="jobClassName" name="jobClassName" id="jobClassName" readonly="true" class="span5" />
				          </c:if>
						</div><br>
						<div class="controls">
						<b><fmt:message key="timingTask.exampleClassName"/></b>
					</div>
					</div>
					<div class="control-group">
						<eazytec:label styleClass="control-label" key="timingTask.parameter" />
						<div class="controls">				
								<form:input path="parameter" id="parameter" class="span5" />
						</div><br>
						<div class="controls">
								<fmt:message key="timingTask.parameter.format"/> 
						</div>
					</div>
					<div class="control-group">
						<eazytec:label styleClass="control-label" key="timingTask.jRunAt" />
						<div class="controls">				
								<form:input path="jRunAt" id="jRunAt" class="span2" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<form:select path="every" name="every" id="every" class="width24">
												<option value="Day"
													${fn:contains(timingTask.every, 'Day)') ? 'selected' : ''}><fmt:message key="timingTask.jRunAt.day"/></option>
												<option value="Hour"
													${fn:contains(timingTask.every, 'Hour') ? 'selected' : ''}><fmt:message key="timingTask.jRunAt.hour"/></option>
												<option value="Minute"
													${fn:contains(timingTask.every, 'Minute') ? 'selected' : ''}><fmt:message key="timingTask.jRunAt.minute"/></option>
								</form:select>
						</div>
					</div>
					<%-- <div class="control-group">
						<eazytec:label styleClass="control-label"  key="timingTask.jobRunOn" />
						<div class="controls">				
							<form:radiobutton path="jobRunOn"  name="jobRunOn" class="checkbox" id="immediately" value="Immediately" checked="true" onclick="showRunOn(this.value)" />
							<fmt:message key="runOn.immediately"/>&nbsp;&nbsp;			
							<form:radiobutton path="jobRunOn" name="jobRunOn" class="inline" id="on" value="on" checked="" onclick="showRunOn(this.value)" />
							<fmt:message key="runOn.on"/>&nbsp;&nbsp;
							<form:input path="jobRunAt" name="jobRunAt" id="jobRunAt" class="medium"  />
						</div>
					</div>	 --%>	
					<div class="control-group">
						<eazytec:label styleClass="control-label" key="timingTask.jobRunOn" />
						<div class="controls">
							<label class="radio inline span2"> 
									<form:radiobutton path="jobRunOn" name="jobRunOn" id="immediately" value="Immediately" checked="true"  onclick="showRunOn(this.value)" /> 
									<eazytec:label key="runOn.immediately" />
								</label> 
								<label class="radio inline span1"> 
									<form:radiobutton path="jobRunOn" name="jobRunOn" id="on" value="On" onchange="showRunOn(this.value)" />
									<eazytec:label key="runOn.on" />
								</label>
								<form:input path="jobRunAt" name="jobRunAt" id="jobRunAt" class="width18" />
						</div>
					</div>			
					<div class="control-group">
						<div class="form-actions no-margin">
							<button type="submit" class="btn btn-primary" name="save"
								onclick="bCancel=false;" id="saveButton">
								<c:choose>
									<c:when test="${mode == 'EDIT'}">
										<fmt:message key="button.update" />
									</c:when>
									<c:otherwise>
										<fmt:message key="button.save" />
									</c:otherwise>
								</c:choose>
							</button>
							<button type="button" class="btn btn-primary " name="next"
								onclick="showListViews('TIMING_TASK','Timing Task');" id="cancelButton"
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
	</div>
	<%-- <div class="span12">
		<div class="row-fluid">
		<div class="widget">
			<div class="widget-body">
			<form:form id="timingTask" commandName="timingTask" method="post"
				action="bpm/admin/saveTimingTask" autocomplete="off" cssClass="form-horizontal" 
				modelAttribute="timingTask" onSubmit="_execute('target','')">
				<c:if test="${timingTask.id != null}">
		       			 <form:hidden path="id"/>		        
		   		</c:if>
				<form:hidden  path="jobName"  id="jobName" value ="${jobNameHidden}"  />
				
					<div class="control-group">
						<eazytec:label styleClass="control-label" key="timingTask.name" />
						<div class="controls">
							<c:if test="${mode != 'EDIT'}">
				                <form:input path="name" name="name" id="name" class="span6" />
				                </c:if>
				                <c:if test="${mode == 'EDIT'}">
				                <form:input path="name" name="name" id="name" readonly="true" class="span6" />
				           </c:if>
							
						</div>
					</div>
					<div class="control-group">
						<eazytec:label styleClass="control-label" key="timingTask.description" />
						<div class="controls">				
								<form:textarea path="description" id="description" class="span6" />
						</div>
					</div>
					<div class="control-group">
						<eazytec:label styleClass="control-label" key="timingTask.jobClassName" />
						<div class="controls">
							<b> Example : com.eazytec.quartz.SchedulerNotificationJob </b>
							<c:if test="${mode != 'EDIT'}">
				                <form:textarea path="jobClassName" name="jobClassName" id="jobClassName" class="span6" />
				                </c:if>
				                <c:if test="${mode == 'EDIT'}">
				                <form:textarea path="jobClassName" name="jobClassName" id="jobClassName" readonly="true" class="span6" />
				          </c:if>
						</div>
					</div>
					<div class="control-group">
						<eazytec:label styleClass="control-label" key="timingTask.parameter" />
						<div class="controls">				
								<form:input path="parameter" id="parameter" class="span6" />
						</div>
						<div class="control-label">
								<fmt:message key="timingTask.parameter.format"/> 
						</div>
					</div>
					<div class="control-group">
						<eazytec:label styleClass="control-label" key="timingTask.jRunAt" />
						<div class="controls">				
								<form:input path="jRunAt" id="jRunAt" class="span6" />
								<form:select path="every" name="every" id="every" class="span6" style="vertical-align:baseline;">
												<option value="Day"
													${fn:contains(timingTask.every, 'Day)') ? 'selected' : ''}>Day</option>
												<option value="Hour"
													${fn:contains(timingTask.every, 'Hour') ? 'selected' : ''}>Hour</option>
												<option value="Minute"
													${fn:contains(timingTask.every, 'Minute') ? 'selected' : ''}>Minute</option>
								</form:select>
						</div>
					</div>
					<div class="control-group">
						<eazytec:label styleClass="radio inline" key="timingTask.jobRunOn" />
						<div class="controls">				
								<form:radiobutton path="jobRunOn" name="jobRunOn" id="immediately" value="Immediately" checked="true" onclick="showRunOn(this.value)" />
						<eazytec:label key="runOn.immediately" />
						</div>
						<div class="controls">				
								<form:radiobutton path="jobRunOn" name="jobRunOn" id="on" value="on" onclick="showRunOn(this.value)" />
						<eazytec:label key="runOn.on" />
						<form:input path="jobRunAt" name="jobRunAt" id="jobRunAt" class="large"  />
						</div>
					</div>



					<div class="control-group">
						<div class="form-actions no-margin">
							<button type="submit" class="btn btn-primary" name="save"
								onclick="bCancel=false;" id="saveButton">
								<c:choose>
									<c:when test="${timingTask.id != null}">
										<fmt:message key="button.update" />
									</c:when>
									<c:otherwise>
										<fmt:message key="button.save" />
									</c:otherwise>
								</c:choose>
							</button>
							<button type="button" class="btn btn-primary " name="next"
								onclick="showListViews('TIMING_TASK','Timing Task');" id="cancelButton"
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
</div> --%> <script type="text/javascript">

	$(function() {
		//alert(jobName);
		// alert('${jobNameHidden}'); 
		// alert($("#jobName").val()); 
		if(${mode != 'EDIT'}){
			if(${runOn == 'On'})
			{
				showRunOn('On');
			}
			else{
			var runOnValue = $("input:radio[name=jobRunOn]").val();
		  showRunOn(runOnValue);
		}
		}
		else{
			//alert(" iiii "+"${runOn}");
			if(${runOn == 'On'})
				{
				/* $("input:radio[name=jobRunOn]").click(function() {
				});
					*/   
					showRunOn('On');
				}
				else{
					var runOnValue = $("input:radio[name=jobRunOn]").val();
					  showRunOn(runOnValue);
				}
			
		}
		//var test=$(":radio[name=jobRunOn]").val();
		//alert(test);
		//var chckd=$(":radio[name=jobRunOn]").attr('checked',false).val();
		//alert(" chckd   "+chckd);
		//alert($('#jobRunOn').val());
		//showRunOn($('#jobRunOn').val());
		loadDateTimeField('jobRunAt');
	});
</script> <v:javascript formName="timingTask" staticJavascript="false" /> <script
								type="text/javascript"
								src="<c:url value="/scripts/validator.jsp"/>"></script>
<%-- <%@ include file="/common/taglibs.jsp"%>


<div class="span12 box">
	<h2>
		<fmt:message key="timingTask.heading" />
		<a id="backToPreviousPage"  data-role="button" data-theme="b"  onClick="showListViews('TIMING_TASK','Timing Task');" data-icon=""><fmt:message key="Back"/></a> 	
	</h2>
	<spring:bind path="timingTask.*">
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
	showListViews("TIMING_TASK","Timing Task");
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
		
		
	</spring:bind>

	<div class="span7 scroll">
		<div class="table-create-user">
			<div id="target" class="span10" style="padding-left: 20PX;">
			
				<form:form id="timingTask" commandName="timingTask" method="post" autocomplete="off" modelAttribute="timingTask" action="bpm/admin/saveTimingTask">
				<form:hidden  path="jobName"  id="jobName" value ="${jobNameHidden}"  />
				
					<table>
						<tr>
					    	<td width="271" height="40">
					    		<eazytec:label styleClass="control-label style3 style18" key="timingTask.name" />
					    	</td>
							<td width="436" class="uneditable-input1">
									<c:if test="${mode != 'EDIT'}">
				                <form:input path="name" name="name" id="name" class="large" />
				                </c:if>
				                <c:if test="${mode == 'EDIT'}">
				                <form:input path="name" name="name" id="name" readonly="true" class="large" />
				                </c:if>
						    </td>
						</tr>
						
						<tr>
					    	<td width="271" height="40">
					    		<eazytec:label styleClass="control-label style3 style18" key="timingTask.description" />
					    	</td>
					    	<td width="436" class="uneditable-input1">
					    		<form:textarea path="description" id="description" rows="4" cols="65" class="medium" style="width:84%" />
					    	</td>
						</tr>
						
						<tr>
					    	<td width="271" height="40">
					    		<eazytec:label styleClass="control-label style3 style18" key="timingTask.jobClassName" />
					    	</td>
					    	<td width="436" class="uneditable-input1">
					    	<b> Example : com.eazytec.quartz.SchedulerNotificationJob </b>
									<c:if test="${mode != 'EDIT'}">
				                <form:textarea path="jobClassName" name="jobClassName" id="jobClassName" class="large" />
				                </c:if>
				                <c:if test="${mode == 'EDIT'}">
				                <form:textarea path="jobClassName" name="jobClassName" id="jobClassName" readonly="true" class="large" />
				                </c:if>
						    </td>
						</tr>
						
						  <tr>
					    	<td width="271" height="40">
					    		<eazytec:label styleClass="control-label style3 style18" key="timingTask.parameter" />
					    	</td>
					    	<td width="436" class="uneditable-input1">
					    		<form:input path="parameter" id="parameter"  class="large"  />
					    	</td>
						</tr>
						<tr><td></td>
						<td>
						<div class="span7 control-label style3 style18">
												<fmt:message key="timingTask.parameter.format"/> 
							    	</div>
							    	<td>
						  </tr>
						<tr>
					    	<td width="271" height="40">
					    		<eazytec:label styleClass="control-label style3 style18" key="timingTask.jRunAt" />
					    	</td>
					    	<td width="436" class="uneditable-input1">
					    		<table width="100%">
					    			<tr>
					    				<td width="50%">
					    					<form:input path="jRunAt" name="jRunAt" id="jRunAt" class="small" />
					    				</td>
					    				<td>&nbsp;</td>
					    				<td width="50%">
					    					<form:select path="every" name="every" id="every" class="medium-high" style="vertical-align:baseline;">
												<option value="Day"
													${fn:contains(timingTask.every, 'Day)') ? 'selected' : ''}>Day</option>
												<option value="Hour"
													${fn:contains(timingTask.every, 'Hour') ? 'selected' : ''}>Hour</option>
												<option value="Minute"
													${fn:contains(timingTask.every, 'Minute') ? 'selected' : ''}>Minute</option>
											</form:select>
					    				</td>
					    			</tr>
					    		</table>
					    	</td>
						</tr>
						<tr>
							<td width="271" height="40">
					    		<eazytec:label styleClass="control-label style3 style18" key="timingTask.jobRunOn" />
					    	</td>
					    	<td width="436" class="uneditable-input1">
					    		<label class="radio inline"> 
									<form:radiobutton path="jobRunOn" name="jobRunOn" id="immediately" value="Immediately" checked="true"  onclick="showRunOn(this.value)" /> 
									<eazytec:label key="runOn.immediately" />
								</label> 
								<label class="radio inline"> 
									<form:radiobutton path="jobRunOn" name="jobRunOn" id="on" value="On" onchange="showRunOn(this.value)" />
									<eazytec:label key="runOn.on" />
								</label>
								<form:input path="jobRunAt" name="jobRunAt" id="jobRunAt" class="large"  />
							</td>
						</tr>
						<tr>
							<td width="271" height="40">
							</td>
							<td width="436" class="uneditable-input1">
								<button type="submit" class="btn btn-primary normalButton padding0 line-height0" name="save" onclick="bCancel=false;" id="saveButton">
									<c:choose>
										<c:when test="${mode == 'EDIT'}">
											<fmt:message key="button.update" />
										</c:when>
										<c:otherwise>
											<fmt:message key="button.save" />
										</c:otherwise>
									</c:choose>
								</button>&nbsp;&nbsp;
								<button type="button" class="btn btn-primary normalButton padding0 line-height0" name="next" onclick="showListViews('TIMING_TASK','Timing Task');" id="cancelButton" style="cursor: pointer;">
			        			<fmt:message key="button.cancel"/>
			        			</button>
							</td>
						</tr>
					</table>
				</form:form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">

	$(function() {
		//alert(jobName);
		// alert('${jobNameHidden}'); 
		// alert($("#jobName").val()); 
		if(${mode != 'EDIT'}){
			if(${runOn == 'On'})
			{
				showRunOn('On');
			}
			else{
			var runOnValue = $("input:radio[name=jobRunOn]").val();
		  showRunOn(runOnValue);
		}
		}
		else{
			//alert(" iiii "+"${runOn}");
			if(${runOn == 'On'})
				{
				/* $("input:radio[name=jobRunOn]").click(function() {
				});
					*/   
					showRunOn('On');
				}
				else{
					var runOnValue = $("input:radio[name=jobRunOn]").val();
					  showRunOn(runOnValue);
				}
			
		}
		//var test=$(":radio[name=jobRunOn]").val();
		//alert(test);
		//var chckd=$(":radio[name=jobRunOn]").attr('checked',false).val();
		//alert(" chckd   "+chckd);
		//alert($('#jobRunOn').val());
		//showRunOn($('#jobRunOn').val());
		loadDateTimeField('jobRunAt');
	});
</script>
<v:javascript formName="timingTask" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value="/scripts/validator.jsp"/>"></script> --%>
