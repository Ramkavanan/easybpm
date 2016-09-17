
<div id="operatingFunctionDiv" class="btn-group">
  <a class="dropdown-toggle padding-1" data-toggle="dropdown" href="#">
<!--     <span class="fontSize13">Operating Function</span> -->
    <button class="btn btn-info">Operating Function</button>
  </a>
 ${operatingFunctionForTask.print}
  <ul class="dropdown-menu" style="min-width:150px;">
	<c:if test="${operatingFunctionForTask.returnOperation==true}">
		<li><a href="#" onClick="jumpTaskNodes('${deploymentId}','${resourceName}','${currentTaskName}','${processDefinitionId}','${taskId}','${activityId}',
		'return','${processInstanceId}',${operatingFunctionForTask.save},'${formId}','${executionId}');">Return</a></li>
	</c:if>
	<c:if test="${operatingFunction.returnOperation==true && operatingFunctionForTask.returnOperation==false}">
		<li><a href="#" onClick="operatingFunctionPermission('Retrun')">Return</a></li>
	</c:if>
	
	<c:if test="${ operatingFunctionForTask.recall==true || recallOperatingFunctionForTask.recall == true}">
		<li><a href="#" onClick="showRecall('${processInstanceId}','${taskId}','${resourceName}','${processDefinitionId}');">Recall</a></li>
	</c:if>
	<c:if test="${operatingFunction.recall==true && operatingFunctionForTask.recall==false}">
		<li><a href="#" onClick="operatingFunctionPermission('Recall')">Recall</a></li>
	</c:if>
	
	<c:if test="${ operatingFunctionForTask.withdraw==true}">
		<li><a href="#" onClick="withdraw('${processInstanceId}','${taskId}','${resourceName}' , '${processDefinitionId}');">Withdraw</a></li>
	</c:if>
	<c:if test="${operatingFunction.withdraw==true && operatingFunctionForTask.withdraw==false}">
		<li><a href="#" onClick="operatingFunctionPermission('Withdraw')">Withdraw</a></li>
	</c:if>
	
	<c:if test="${ operatingFunctionForTask.transfer==true}">
				<li><a href="#" onClick="getUsersToInvolve('${taskId}','${transfer}', 'Transfer','','${resourceName}','${nodeType}','${processDefinitionId}','${processInsId}')">Transfer</a></li>
	</c:if>
	<c:if test="${operatingFunction.transfer==true && operatingFunctionForTask.transfer==false}">
		<li><a href="#" onClick="operatingFunctionPermission('Transfer')">Transfer</a></li>
	</c:if>
	
	<c:if test="${operatingFunctionForTask.urge==true }">
		<li><a href="#" onClick="urgePopUp('${currentTaskName}','${processDefinitionId}','${taskId}','${processInsId}');">Urge</a></li>
	</c:if>
	<c:if test="${operatingFunction.urge==true && operatingFunctionForTask.urge==false }">
		<li><a href="#" onClick="operatingFunctionPermission('Urge')">Urge</a></li>
	</c:if>
	
	<c:if test="${(operatingFunctionForTask.add==true)}">
		<li><a href="#" onClick="getUsersToInvolve('${taskId}','${add}', 'Add Members','','${resourceName}','${nodeType}','${processDefinitionId}','${processInsId}')">Add</a></li>
	</c:if>
	
	<c:if test="${(operatingFunction.add==true) && (operatingFunctionForTask.add==false)}">
		<li><a href="#" onClick="operatingFunctionPermission('Add')">Add</a></li>
	</c:if>
	
	<c:if test="${operatingFunctionForTask.confluentSignature==true}">
		<li><a href="#" onClick="getUsersToInvolve('${taskId}','${confluentSignature}', 'Select User for Collaborative Operation','','${resourceName}','${nodeType}','${processDefinitionId}','${processInsId}')">Collaborative Operation</a></li>
	</c:if>
	<c:if test="${operatingFunction.confluentSignature==true && operatingFunctionForTask.confluentSignature==false}">
		<li><a href="#" onClick="operatingFunctionPermission('Collaborative Operation')">Collaborative Operation</a></li>
	</c:if>
	
	<c:if test="${operatingFunctionForTask.circulatePerusal==true}">
		<li><a href="#" onClick="getUsersToInvolve('${taskId}','${circulatePerusal}', 'Select User for Circulate Perusal','','${resourceName}','${nodeType}','${processDefinitionId}','${processInsId}')">Circulate Perusal</a></li>
	</c:if>
	<c:if test="${operatingFunction.circulatePerusal==true && operatingFunctionForTask.circulatePerusal==false}">
		<li><a href="#" onClick="operatingFunctionPermission('Circulate Perusal')">Circulate Perusal</a></li>
	</c:if>
	
	<c:if test="${operatingFunctionForTask.jump==true}">
		<li><a href="#" onClick="jumpTaskNodes('${deploymentId}','${resourceName}','${currentTaskName}','${processDefinitionId}','${taskId}','${activityId}',
		'jump','${processInstanceId}',${operatingFunctionForTask.save},'${formId}','${executionId}');">Jump</a></li>
	</c:if>
	<c:if test="${operatingFunction.jump==true && operatingFunctionForTask.jump==false}">
		<li><a href="#" onClick="operatingFunctionPermission('Jump')">Jump</a></li>
	</c:if>
	
	<c:if test="${operatingFunctionForTask.transactorReplacement==true}">
		<li><a href="#" onClick="getUsersToInvolve('${taskId}','${transactorReplacement}', 'Replace Transactor','','${resourceName}','${nodeType}','${processDefinitionId}','${processInsId}')">Replace Transactor</a></li>
	</c:if>
	<c:if test="${operatingFunction.transactorReplacement==true && operatingFunctionForTask.transactorReplacement==false}">
		<li><a href="#" onClick="operatingFunctionPermission('Replace Transactor')">Replace Transactor</a></li>
	</c:if>
	
	<c:if test="${operatingFunctionForTask.terminate==true}">
		<li><a href="#" onClick="terminate('${executionId}','${taskId}','${resourceName}' , '${processDefinitionId}');">Terminate</a></li>
	</c:if>
	<c:if test="${operatingFunction.terminate==true && operatingFunctionForTask.terminate==false}">
		<li><a href="#" onClick="operatingFunctionPermission('Terminate')">Terminate</a></li>
	</c:if>
	
	<c:if test="${operatingFunctionForTask.suspend==true}">
		<li><a href="#" onClick="suspend('${processInstanceId}','${taskId}','${resourceName}','${suspendState}','${processDefinitionId}');">Suspend</a></li>
	</c:if>
	<c:if test="${operatingFunction.suspend==true && operatingFunctionForTask.suspend==false}">
		<li><a href="#" onClick="operatingFunctionPermission('Suspend')">Suspend</a></li>
	</c:if>
	
	<c:if test="${operatingFunctionForTask.submit==true }">
		<li><a href="#" onClick="taskReturn('${taskId}','${resourceName}','${processDefinitionId}',${operatingFunctionForTask.save},'${formId}','${processInsId}');">Back</a></li>
	</c:if>
	</ul>
</div>
