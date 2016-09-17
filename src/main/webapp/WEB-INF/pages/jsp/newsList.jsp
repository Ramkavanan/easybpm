<script>
getNewsList();
function getNewsList(){
	$.ajax({
		type : 'GET',
		async : false,
		url : 'bpm/news/getNewsList',
		success : function(response) {
			var newsList = eval(response);
			var htmlContent = "";
			if(newsList.length > 0){
				for(var idx = 0; idx < newsList.length; idx++){
					var tempDate = (newsList[idx].CREATEDTIME).split(" ");
					var createdDate = new Date(tempDate[0]);
					var monthMap = {0:'jan', 1:'feb', 2:'mar', 3:'apr', 4:'may', 5:'jun', 6:'jul', 7:'aug', 8:'sep', 9:'oct', 10:'nov', 11:'dec'};
					htmlContent = htmlContent + '<div style="padding:5px 10px 20px 5px; background-color:#f9f9f9;">';
					htmlContent = htmlContent + '<div class="style15">'+newsList[idx].Title+'</div>';
					htmlContent = htmlContent + '<div style="padding-top:8px;font-weight: normal;">'+newsList[idx].Information+'</div>';	
					htmlContent = htmlContent + '<div class="style16 style17" style="padding-top:10px; padding-bottom:15px; border-bottom:solid #CCCCCC 1px; ">';	
					htmlContent = htmlContent + ''+createdDate.getDate() +' '+ monthMap[createdDate.getMonth()] +' '+ createdDate.getFullYear()+'';
					//htmlContent = htmlContent + '<div class="small small style3" style="float:right;"> read more>></div>';
					htmlContent = htmlContent + '</div>';	
					htmlContent = htmlContent + '</div>';
					if(idx == 2){
						break;
					}	
				}
			$("#newsList_container").html(htmlContent);
			}
		}
	});
}
</script>
<div id="newsList_container"></div>
</div>
<div class="small small style3 readmore-home">
<a href="javascript:void(0);" onClick="showListViews('NEWS_LIST','News');">View All</a>
</div>
									
							
