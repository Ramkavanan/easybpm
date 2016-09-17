<%@ include file="/common/taglibs.jsp"%>
<div class="page-header">
	<div class="pull-left">
		<h2><fmt:message key="task.detail.view"/></h2>
	</div>
	<div class="height10"></div>
	<div id="header_links" align="right">
		<div align="right"><a class="padding10" style="text-decoration: underline;" id="backToPreviousPage" href="javascript:void(0);" data-role="button" data-theme="b"  onClick="backToPreviousPage()" data-icon=""><strong><fmt:message key="Back"/></strong></a> </div>
	</div>
</div>
<div class="row-fluid">
	<div class="widget">
		<div class="widget-body">
			<div class="form-horizontal no-margin">
				<div class="title">
					<span class="fs1" aria-hidden="true"></span>
					<h5><fmt:message key="task.process.details"/></h5>
				</div>
				<div class="control-group">
					<eazytec:label styleClass="control-label" key="process.name" />
						<div class="controls" style="padding-top: 8px;">${process.displayName}</div>
				</div>
				<%-- <div class="control-group">
					<label class="control-label"><span><fmt:message key="process.name"/></span></label>
					<div class="controls controls-row">${process.displayName}</div>
				</div> --%>
				<div class="control-group">
					<label class="control-label"><span><fmt:message key="process.description"/></span></label>
					<div class="controls" style="padding-top: 8px;">${process.description}</div>
				</div>
				<div class="title">
					<span class="fs1" aria-hidden="true"></span>
					<h5><fmt:message key="task.details"/></h5>
				</div>
				<div class="control-group">
					<label class="control-label"><span><fmt:message key="task.title"/></span></label>
					<div class="controls" style="padding-top: 8px;">${task.name}</div>
				</div>
				<div class="control-group">
					<label class="control-label"><span><fmt:message key="task.description"/></span></label>
					<div class="controls" style="padding-top: 8px;">${task.description}</div>
				</div>
				<div class="control-group">
					<label class="control-label"><span><fmt:message key="task.create.at"/></span></label>
					<div class="controls" style="padding-top: 8px;">${taskCreateTime}</div>
				</div>
				<div class="control-group">
					<label class="control-label"><span><fmt:message key="task.members.involved"/></span></label>
					<div class="controls" style="padding-top: 8px;"><a href="#" onclick = "getUsersToInvolve('${task.id}','${involveMembers}')" class="button_link">${involveMembers}</a></div>
				</div>
				<div class="control-group">
					<%= request.getAttribute("peopleScript") %>
				</div>
				<div class="control-group">
					<div class="form-actions no-margin">
						<c:if test="${(isTaskFormApplicable == true) && (isTaskClaimable == false) }">
							<input type="button" class="btn btn-primary" value=<fmt:message key="task.operate"/> onclick = "showTaskFormDetail('${task.id}')">
						</c:if>
						<c:if test="${isTaskClaimable == true}">
							<input type="button" name="claim" class="btn btn-primary" onclick="claimTask(${task.id});" value=<fmt:message key="task.claim"/> >
						</c:if>
						<input type="button" name="Print" class="btn btn-primary" onclick="showPrintPreviewFormId(this,'task_detail_view');" value=<fmt:message key="documentForm.print"/> >
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<%-- <div class="width-per-100 height-per-100">
	<div class="row-fluid content-div">
		<div align="right">
		 <strong><a id="backToPreviousPage" style="text-decoration: underline;padding: 10px;" href="javascript:void(0);" data-role="button" data-theme="b" onClick="backToPreviousPage()" data-icon=""><fmt:message key="Back" /></a> </strong>
	   </div>
		<div class="span12 box" ><h2 class="ui-widget fontSize1-3em"><fmt:message key="task.detail.view"/></h2>
			<div id="task_detail_view" style="width:100%;" class="floatLeft">
				<div class="task-detail-section">
					<span class="panel-title-style style3 style18"><fmt:message key="task.process.details"/></span>
					<div class="line-height5 width-per-100"><div class="outer-pad floatLeft style3 style18 width-per-10"><span><fmt:message key="process.name"/></span></div><div class="outer-pad width-per-94">&nbsp;${process.displayName}</div></div>
					<div class="line-height5 width-per-100"><div class="outer-pad floatLeft style3 style18 width-per-10"><span><fmt:message key="process.description"/></span></div><div class="outer-pad width-per-94">&nbsp;${process.description}</div></div>
				</div>
				<div style="height: 18px; width: 742px; overflow: hidden; padding-left: 0px; padding-top: 0px;"></div>
				<div class="content-area-hgt">
					<div class="task-detail-section">
					<span class="panel-title-style style3 style18"><fmt:message key="task.details"/></span>
					<div class="line-height5 width-per-100"><div class="outer-pad floatLeft style3 style18 width-per-10"><fmt:message key="task.title"/></div><div class="outer-pad width-per-94">&nbsp;${task.name}</div></div>
					<div class="line-height5 width-per-100"><div class="outer-pad floatLeft style3 style18 width-per-10"><fmt:message key="task.description"/></div><div class="outer-pad width-per-94">&nbsp;${task.description}</div></div>
					<div class="line-height5 width-per-100"><div class="outer-pad floatLeft style3 style18 width-per-10"><fmt:message key="task.create.at"/></div><div class="outer-pad width-per-94">&nbsp;${taskCreateTime}</div></div>
					</div>
				</div>		
				<div class="content-area-hgt">					
						
						<div class="task-detail-section members-detail-section">
							<span class="panel-title-style style3 style18"><fmt:message key="task.members.involved"/></span>
	                        <a href="#" onclick = "getUsersToInvolve('${task.id}','${involveMembers}')" class="button_link">${involveMembers}</a>
						 </div>			
						<%= request.getAttribute("peopleScript") %>
						<div>
							<div class="pad-L350" style="float:left;">
								<c:if test="${(isTaskFormApplicable == true) && (isTaskClaimable == false) }">
									<input type="button" class="btn btn-primary normalButton" value=<fmt:message key="task.operate"/> onclick = "showTaskFormDetail('${task.id}')">
								</c:if>
								<c:if test="${isTaskClaimable == true}">
									<input type="button" name="claim" class="btn btn-primary normalButton" onclick="claimTask(${task.id});" value=<fmt:message key="task.claim"/> >
								</c:if>
								<input type="button" name="Print" class="btn btn-primary normalButton" onclick="showPrintPreviewFormId(this,'task_detail_view');" value=<fmt:message key="documentForm.print"/> >
							</div>	
						</div>		
				</div>				
			</div>
			<div  style="width:45%;" class="floatLeft">
				<div class="task-detail-section">
					<c:if test="${taskEvents.size()>0}">
						<span class="panel-title-style style3 style18">Task Event Details </span>
						<table class="gridview1">
							<c:forEach var="event" items="${taskEvents}">
								<tr>
									<td><img id="profileImage" width="25" height="25" src="${event.fullMessage}"/><b>&nbsp;${event.userId}</b> 
									<c:if test="${event.action == 'AddUserLink'}">&nbsp;involved</c:if>
									<c:if test="${event.action == 'DeleteUserLink'}">&nbsp;removed</c:if>									
									 <i>&nbsp;${event.getMessageParts().get(0)}</i>&nbsp;as<b>&nbsp;${event.getMessageParts().get(1)}</b></td>
								</tr>
							</c:forEach>
						</table>
					</c:if>
				</div>	
			</div>		
		</div>
	</div>	
</div> --%>	


