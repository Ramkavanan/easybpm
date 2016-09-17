<script>
getDoToList();
function getDoToList(){
	$.ajax({
		type : 'GET',
		async : false,
		url : 'bpm/todo/getToDOList',
		success : function(response) {
			var todoList = eval(response);
			var htmlContent = "";
			if(todoList.length > 0){
				for(var idx = 0; idx < todoList.length; idx++){
					var tempDate = (todoList[idx].createTime).split(" ");
					// rawObject.id + '\',\''+ rawObject.suspensionState + '\',\''+ rawObject.lastOperationPerformed + '\',\''+ rawObject.executionId + '\',\''
					//rawObject.resourceName + '\',\''+ rawObject.processDefinitionId + '\',\''+ rawObject.owner + '\');"><b>'+operate+'</b></a> ';
					var createdDate = new Date(tempDate[0]);
					var monthMap = {0:'jan', 1:'feb', 2:'mar', 3:'apr', 4:'may', 5:'jun', 6:'jul', 7:'aug', 8:'sep', 9:'oct', 10:'nov', 11:'dec'};
					htmlContent = htmlContent + '<div style="padding:5px 10px 20px 5px; background-color:#f9f9f9;">';
					htmlContent = htmlContent + '<div class="style15">'+todoList[idx].name+' - '+
					'<a href="javascript:void(0);" onclick="showTaskFormDetail(\''+todoList[idx].id + '\',\''+ todoList[idx].suspensionState + '\',\''+ todoList[idx].lastOperationPerformed + '\',\''+ todoList[idx].executionId + '\',\''
					+ todoList[idx].resourceName + '\',\''+ todoList[idx].processDefinitionId + '\',\''+ todoList[idx].owner + '\');"><u>'+todoList[idx].processName+'</u></a></div>';
					htmlContent = htmlContent + '<div style="padding-top:8px;font-weight: normal;"></div>';	
					htmlContent = htmlContent + '<div class="style16 style17" style="padding-top:10px; padding-bottom:15px; border-bottom:solid #CCCCCC 1px; ">';	
					htmlContent = htmlContent + ''+createdDate.getDate() +' '+ monthMap[createdDate.getMonth()] +' '+ createdDate.getFullYear()+'';
					/* htmlContent = htmlContent + '<a href="javascript:void(0);" onclick="showTaskFormDetail(\''
					+ todoList[idx].id + '\',\''+ todoList[idx].suspensionState + '\',\''+ todoList[idx].lastOperationPerformed + '\',\''+ todoList[idx].executionId + '\',\''
					+ todoList[idx].resourceName + '\',\''+ todoList[idx].processDefinitionId + '\',\''+ todoList[idx].owner + '\');"><b>Operate</b></a> '; */
					//htmlContent = htmlContent + '<div class="small small style3" style="float:right;"> read more>></div>';
					htmlContent = htmlContent + '</div>';	
					htmlContent = htmlContent + '</div>';	
					if(idx == 2){
						break;
					}
				}
			$("#todlist_container").html(htmlContent);
			}
		}
	});
}
</script>
<div id="todlist_container"></div>
</div>

<div class="small small style3 readmore-home">
<a href="#bpm/manageTasks/mybucket" onClick="_execute('target','');">View All</a>

</div>
								
