<%@ include file="/common/taglibs.jsp"%>
<script>
</script>

 <form:form id="addMember" method="post" data-remote="true" accept-charset="UTF-8" onSubmit="_execute('target','');" class="padding5">
 <input name="taskId" id="taskId" type="hidden" value="${taskId}" />
 <input name="processInsId" id="processInsId" type="hidden" value="${processInsId}" />
 <input name="type" id="type" type="hidden" value="${type}" />
 <input name="resourceName" id="resourceName" type="hidden" value="${resourceName}" />
 <input type="hidden" id="taskSuperior" name="taskSuperior" />
 <input type="hidden" id="taskSubordinate" name="taskSubordinate" />
  <input type="hidden" id="scriptName" name="scriptName" value="${endScriptName}" />
   <input type="hidden" id="processDefinitionId" name="processDefinitionId" value="${processDefinitionId}" />
   
	<center>
   <table>
       <tr>
        <td>
        <c:if test="${type != jointConduction && type != 'startProcess'  && type != 'jump' && type != 'add' && type != 'defaultTask'}">
                  <%-- <p><fmt:message key="task.users"/></p> --%>
            <input type="hidden" id="userId" name="userId" onclick="showUserSelection('User', '${userSelectionType}' , 'userId', this, '')" class="large"/>
            <input type="text" id="userIdFullName" name="userIdFullName" onclick="showUserSelection('User', '${userSelectionType}' , 'userId', this, '')" class="large"/>
        </c:if>
            <c:if test="${type == add}">
            <input type="hidden" id="nextTaskOrganizersSelect" name="nextTaskOrganizersSelect" onclick="showOrganizerSelection('Organizers', '${userSelectionType}' , 'nextTaskOrganizersSelect', this, '','${organizersList}','true')" class="large"/>
	         <input type="text" id="nextTaskOrganizersname" name="nextTaskOrganizersname" onclick="showOrganizerSelection('Organizers', '${userSelectionType}' , 'nextTaskOrganizersSelect', document.getElementById('nextTaskOrganizersSelect'), '','${organizersList}','true')"  class="large"/>
	         	<div class="pad-T10">
                	<eazytec:label styleClass="control-label" key="task.roles"/>
                </div>
                    <div class="controls">
                        <select id="identityLinkType" name="identityLinkType" class="large">
                            <c:forEach items="${availableRtTaskRoles}" var="identityLinkType">
                                 <option value="${identityLinkType.value}">${identityLinkType.label}</option>
                            </c:forEach>
                        </select>
                    </div>
                     <input type="hidden" id="taskOrganizerOrder" name="taskOrganizerOrder" value="true"/>
            </c:if>
<!--            <c:if test="${type == 'collaborativeoperation'}">-->
<!--		 	<p><fmt:message key="task.users"/></p> 		 -->
<!--				<input type="text" id="userId" maxlength="50" name="userId" onclick="showOrganizerSelection('Users', '${userSelectionType}' , 'userId', this, '','[]','false')" class="large"/>-->
<!--				<input type="hidden" id="taskOrganizerOrder" name="taskOrganizerOrder" value="true"/>  -->
<!--		</c:if>-->
            <c:if test="${type == jointConduction || type == 'startProcess' || type == 'jump' || type == 'defaultTask'}">
            <div class="pad-T10">
            	<eazytec:label styleClass="control-label" key="task.role.organizers"/>
            </div>
	         <c:if test="${nodeType != 'null' && nodeType == 1}">
	            <input type="hidden" id="nextTaskOrganizersSelect" name="nextTaskOrganizersSelect" onclick="showOrganizerSelection('Organizers', 'single' , 'nextTaskOrganizersSelect', this, '','${organizersList}','true')" class="large"/>
	            <input type="text" id="nextTaskOrganizersname" name="nextTaskOrganizersname" onclick="showOrganizerSelection('Organizers', 'single' , 'nextTaskOrganizersSelect', document.getElementById('nextTaskOrganizersSelect'), '','${organizersList}','true')"  class="large"/>
	         </c:if>  
	         <c:if test="${nodeType == 'null' || nodeType != 1}">
	            <input type="hidden" id="nextTaskOrganizersSelect" name="nextTaskOrganizersSelect" onclick="showOrganizerSelection('Organizers', 'multi' , 'nextTaskOrganizersSelect', this, '','${organizersList}','true')" class="large"/>
	            <input type="text" id="nextTaskOrganizersname" name="nextTaskOrganizersname" onclick="showOrganizerSelection('Organizers', 'multi' , 'nextTaskOrganizersSelect', document.getElementById('nextTaskOrganizersSelect'), '','${organizersList}','true')"  class="large"/>
	         </c:if>  
            <p style="padding-top:10px;"> <fmt:message key="task.role.coordinators"/></p>
           	 
           	 	<input type="hidden" id="nextTaskCoordinatorsSelect" name="nextTaskCoordinatorsSelect" onclick="showUserSelection('Coordinators', 'multi' , 'nextTaskCoordinatorsSelect', this, '')" class="large"/>
            	<input type="text" id="nextTaskCoordinatorsName" name="nextTaskCoordinatorsName" onclick="showUserSelection('Coordinators', 'multi' , 'nextTaskCoordinatorsSelect', document.getElementById('nextTaskCoordinatorsSelect'), '')" class="large"/>
            	
	            <input type="hidden" id="taskOrganizerOrder" name="taskOrganizerOrder" value="true"/>
	            <input type="hidden" id="isJointConduction" name="isJointConduction" value="true"/>
            </c:if>
        </td>
    </tr>
    <tr></tr>
        <tr>
    	<td>
    		<br>
    		 <input type="checkbox" id="isMail" value=""/> <b>E-Mail</b> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    		 <input type="checkbox" id="isInternalMessage" value="" checked/> <b>Internal Message</b> 
    	</td>
    </tr>

    <tr>
        <td colspan="2" align="center" class="pad-T20">
            <c:if test="${type == jointConduction}">
                <button type="button" name="submit" class="btn btn-primary" onclick="submitJointConductionForm('${formId}',true,${endScriptName})">Submit</button>
            </c:if>
            <c:if test="${type == 'startProcess'}">
                <button type="button" name="submit" class="btn btn-primary" onclick="submitJointConductionProcess('${formId}','${processKey}','${formAction}',${endScriptName},'false')">Submit</button>
            </c:if>
            <c:if test="${type == 'defaultTask'}">
                <button type="button" name="submit" class="btn btn-primary" onclick="submitJointConductionProcess('${formId}','${processKey}','${formAction}',${endScriptName},'true')">Submit</button>
            </c:if>
            <c:if test="${type == 'jump'}">
                <button type="button" name="submit" class="btn btn-primary" onclick="submitJointConductionJumpTask('${taskId}','${activityId}','${resourceName}','${formId}',${canSave})">Submit</button>
            </c:if>
            <c:if test="${type == 'bulkReplace'}">
                <button type="button" name="submit" class="btn btn-primary" onclick="bulkTasksReplace()">Submit</button>
            </c:if>
            <c:if test="${type != jointConduction && type != 'startProcess' && type != 'jump' && type != 'bulkReplace' && type != 'defaultTask'}">
                <button type="button" name="submit" class="btn btn-primary" onclick="submitAddMembers('${type}','${formAction}')">Submit</button>
            </c:if>
        </td>
    </tr>
  </table>
  </center>
</form:form>