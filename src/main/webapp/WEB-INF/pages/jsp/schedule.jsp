<script>
getScheduleList();
function getScheduleList(){
	$.ajax({
		type : 'GET',
		async : false,
		url : 'bpm/oa/getScheduleList',
		success : function(response) {
			var scheduleList = eval(response);
			var a = scheduleList.length;
			var htmlContent = "";
			if(scheduleList.length > 0){
				for(var idx = 0; idx < scheduleList.length; idx++){
					var startTempDate = (scheduleList[idx].startdate).split(" ");
					var endTempDate = (scheduleList[idx].endDate).split(" ");
					var createdDate = new Date(startTempDate[0]);
					var endDate = new Date(endTempDate[0]);
					var monthMap = {0:'jan', 1:'feb', 2:'mar', 3:'apr', 4:'may', 5:'jun', 6:'jul', 7:'aug', 8:'sep', 9:'oct', 10:'nov', 11:'dec'};
					htmlContent = htmlContent + '<div style="padding:5px 10px 20px 5px; background-color:#f9f9f9;">';
					htmlContent = htmlContent + '<div class="style15">'+scheduleList[idx].name+'</div>';
					htmlContent = htmlContent + '<div style="padding-top:8px;font-weight: normal;">'+scheduleList[idx].description+'</div>';
					htmlContent = htmlContent + '<div style="padding-top:8px;font-weight: normal;">'+scheduleList[idx].location+'</div>';
					htmlContent = htmlContent + '<div class="style16 style17" style="padding-top:10px; padding-bottom:15px; border-bottom:solid #CCCCCC 1px; ">';	
					htmlContent = htmlContent + 'Start Date: '+createdDate.getDate() +' '+ monthMap[createdDate.getMonth()] +' '+ createdDate.getFullYear()+''+'<br>';
					htmlContent = htmlContent + '<div class="style16 style17" style="padding-top:10px; padding-bottom:15px; border-bottom:solid #CCCCCC 1px; ">';	
					htmlContent = htmlContent + 'End Date: '+endDate.getDate() +' '+ monthMap[endDate.getMonth()] +' '+ endDate.getFullYear()+'';
// 					htmlContent = htmlContent + '<div class="small small style3" style="float:right;"> read more>></div>';
					htmlContent = htmlContent + '</div>';	
					htmlContent = htmlContent + '</div>';
					if(idx == 2){
						break;
					}	
				}
			$("#scheduleList_container").html(htmlContent);
			}
		}
	});
}
</script>
<div id="scheduleList_container"></div>
</div>

<div class="small small style3 readmore-home">
<a href="#bpm/oa/schedules" onClick="_execute('target','');">View All</a>

</div>
							 	
