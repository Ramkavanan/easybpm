<%@ include file="/common/taglibs.jsp"%>
<style>

/* tr.row-height {
	height: 5px;
} */

</style>
<%-- <div class="container-fluid" id="parent_container">
	<div class="row-fluid">
		<div id="col_prop_grid" class="span9"
			style="border: 1px solid #B6B6B6;">
			<div id="columns_property_grid">
				<table id="_LIST_VIEW_COLUMNS_PROPERTY"></table>
				<div id="_LIST_VIEW_COLUMNS_PROPERTY_PAGER"></div>
				<table width="100%">
					<tr class="row-height"></tr>
					<tr>
						<td align="center">
							<button type="button"
								onClick="_saveOrUpdateAllRecord('${listView.id}');"
								class="btn btn-primary normalButton padding0 line-height0">
								<fmt:message key="button.save" />
							</button>
							<button type="button" onClick="_deleteRecord();"
								class="btn btn-primary normalButton padding0 line-height0">
								<fmt:message key="button.delete" />
							</button>
							<button type="button" onClick="_deleteAllRecord();"
								class="btn btn-primary normalButton padding0 line-height0">
								<fmt:message key="button.deleteAll" />
							</button>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div id="col_prop_form" class="span9"
			style="overflow-y: auto; border: 1px solid #B6B6B6;">
			<table width="100%">
				<input type="hidden" id="columnPropertyId" name="columnPropertyId" />
				<tr class="row-height"></tr>
				<tr>
					<td class="pad-L10"><eazytec:label
							styleClass="control-label style3 style18 "
							key="listView.columnProperty.columnTitle" /></td>
					<td>
						<div class="controls">
							<input type="text" class="large" id="columnTitle"
								name="columnTitle" />
						</div>
					</td>
				</tr>
				<tr class="row-height"></tr>
				<tr>
					<td class="pad-L10"><eazytec:label
							styleClass="control-label style3 style18 "
							key="listView.columnProperty.dataFields" /></td>
					<td>
						<div class="controls">
							<select class="large" id="dataFields" name="dataFields">
							</select>
						</div>
					</td>
				</tr>
				<tr class="row-height"></tr>
				<tr>
					<td class="pad-L10"><eazytec:label
							styleClass="control-label style3 style18 "
							key="listView.columnProperty.columnWidth" /></td>
					<td>
						<div class="controls">
							<input type="text" class="large" id="columnWidth"
								name="columnWidth" value="0" />
						</div>
					</td>
				</tr>
				<tr class="row-height"></tr>
				<tr>
					<td class="pad-L10"><eazytec:label
							styleClass="control-label style3 style18 "
							key="listView.columnProperty.columnsOrder" /></td>
					<td>
						<div class="controls">
							<input type="text" class="large" id="columnsOrder"
								name="columnsOrder" value="0" />
						</div>
					</td>
				</tr>
				<tr class="row-height displayNone"></tr>
				<tr class="displayNone">
					<td class="pad-L10"><eazytec:label
							styleClass="control-label style3 style18 "
							key="listView.columnProperty.otherName" /></td>
					<td>
						<div class="controls">
							<input type="text" class="large" id="otherName" name="otherName" />
						</div>
					</td>
				</tr>
				<tr class="row-height"></tr>
				<tr>
					<td class="pad-L10"><eazytec:label
							styleClass="control-label style3 style18 "
							key="listView.columnProperty.replaceWords" /></td>
					<td>
						<div class="controls">
							<textarea class="large" id="replaceWords" name="replaceWords"
								placeholder="<fmt:message key='list.view.columnProperty.replaceWord.placeholder'/>"></textarea>
						</div>
					</td>
				</tr>
				<tr>
					<td class="pad-L10"></td>
					<td>
						<div class="controls">
							<fmt:message
								key='list.view.columnProperty.replaceWord.placeholder.ex' />
						</div>
					</td>
				</tr>
				<tr class="row-height"></tr>
				<tr>
					<td class="pad-L10"><eazytec:label
							styleClass="control-label style3 style18 "
							key="listView.columnProperty.isSort" /></td>
					<td>
						<div class="controls">
							<input type="checkbox" id="isSort" name="isSort" checked="true" />
						</div>
					</td>
				</tr>
				<tr class="row-height"></tr>
				<tr>
					<td class="pad-L10"><eazytec:label
							styleClass="control-label style3 style18 "
							key="listView.columnProperty.isAdvancedSearch" /></td>
					<td>
						<div class="controls">
							<input type="checkbox" id="isAdvancedSearch"
								name="isAdvancedSearch" />
						</div>
					</td>
				</tr>
				<tr class="row-height"></tr>
				<tr id="search_field_type" class="displayNone">
					<td class="pad-L10"><eazytec:label
							styleClass="control-label style3 style18 "
							key="listView.columnProperty.dataFieldType" /></td>
					<td>
						<div class="controls">
							<select id="dataFieldType" class="large" name="dataFieldType"
								onChange="showDataDictionary(this);">
								<option value="text"><fmt:message  key="listView.table.text"/></option>
								<option value="date"><fmt:message  key="message.date"/></option>
								<option value="datetime"><fmt:message  key="listView.table.dateTime"/></option>
								<option value="select"><fmt:message  key="listView.table.select"/></option>
								<option value="autocomplete"><fmt:message  key="form.palette.autocomplete"/></option>
								<option value="radio"><fmt:message  key="form.palette.radio"/></option>
								<!-- <option value="checkbox">Checkbox</option>  -->
							</select>
						</div>
					</td>
				</tr>
				<tr class="row-height"></tr>
				<tr id="is_range_field" class="displayNone">
					<td class="pad-L10"><eazytec:label
							styleClass="control-label style3 style18 "
							key="listView.columnProperty.isRange" /></td>
					<td>
						<div class="controls">
							<input type="checkbox" id="isRange" name="isRange" />
						</div>
					</td>
				</tr>
				<tr class="row-height"></tr>
				<tr id="data_dictionary_field" class="displayNone">
					<td class="pad-L10"><eazytec:label
							styleClass="control-label style3 style18 "
							key="dataDictionary.form" /></td>
					<td>
						<div class="controls">
							<input type="text" class="large" id="select_data_dictionary"
								name="select_data_dictionary"
								onClick='selectDataDictinary(this);' /> <input type="hidden"
								id="dataDictionary" name="dataDictionary" />
						</div>
					</td>
				</tr>
				<tr class="row-height"></tr>
				<tr>
					<td class="pad-L10"><eazytec:label
							styleClass="control-label style3 style18 "
							key="listView.columnProperty.isSimpleSearch" /></td>
					<td>
						<div class="controls">
							<input type="checkbox" id="isSimpleSearch" name="isSimpleSearch"
								checked="true" />
						</div>
					</td>
				</tr>
				<tr class="row-height"></tr>
				<tr>
					<td class="pad-L10"><eazytec:label
							styleClass="control-label style3 style18 "
							key="listView.columnProperty.textAlign" /></td>
					<td>
						<div class="controls">
							<input type="radio" id="textAlignLeft" name="textAlign"
								value="Left" checked="true" /> <fmt:message  key="listView.table.left"/> &nbsp;&nbsp;&nbsp; <input
								type="radio" id="textAlignCenter" name="textAlign"
								value="Center" /> <fmt:message  key="listView.table.center"/> &nbsp;&nbsp;&nbsp; <input type="radio"
								id="textAlignRight" name="textAlign" value="Right" /> <fmt:message  key="listView.table.right"/>
						</div>
					</td>
				</tr>
				<tr class="row-height"></tr>
				<tr>
					<td class="pad-L10"><eazytec:label
							styleClass="control-label style3 style18 "
							key="listView.columnProperty.isHidden" /></td>
					<td>
						<div class="controls">
							<input type="checkbox" id="isHidden" name="isHidden" />
						</div>
					</td>
				</tr>
				
				<tr class="row-height"></tr>
				<tr>
					<td class="pad-L10">
			            <eazytec:label styleClass="control-label style3 style18 " key="listView.columnProperty.columnType" />
					</td>
					<td>						
			            <div class="controls">
				           	<select id="columnType" class="large"  name="columnType">
				           		<option value="text"><fmt:message  key="listView.table.text"/></option>
				           		<option value="date"><fmt:message  key="message.date"/></option>
				           		<option value="number"><fmt:message  key="listView.table.number"/></option>
				           		
				           	</select>
			            </div>
				</td>
				</tr>
				
				<tr class="row-height displayNone"></tr>
				<tr class="displayNone">
					<td class="pad-L10"><eazytec:label
							styleClass="control-label style3 style18 "
							key="listView.columnProperty.isGroupBy" /></td>
					<td>
						<div class="controls">
							<input type="checkbox" id="isGroupBy" name="isGroupBy" />
						</div>
					</td>
				</tr>
				<tr class="row-height"></tr>
				<tr>
					<td class="pad-L10"><eazytec:label
							styleClass="control-label style3 style18 "
							key="listView.columnProperty.onRenderEventName" /></td>
					<td>
						<div class="controls">
							<input type="text" class="large" id="onRenderEventName"
								name="onRenderEventName" />
						</div>
					</td>
				</tr>
				<tr class="row-height"></tr>
				<tr>
					<td class="pad-L10"><eazytec:label
							styleClass="control-label style3 style18 "
							key="listView.columnProperty.onRenderEvent" /></td>
					<td>
						<div class="controls">
							<textarea type="text" class="large" id="onRenderEvent"
								name="onRenderEvent" />
						</div>
					</td>
				</tr>
				<tr class="row-height"></tr>
				<tr>
					<td class="pad-L10"><eazytec:label
							styleClass="control-label style3 style18 "
							key="listView.columnProperty.comment" /></td>
					<td>
						<div class="controls">
							<textarea type="text" class="large" id="comment" name="comment" />
						</div>
					</td>
				</tr>
				<tr class="row-height"></tr>
				<tr>
					<td colspan="2" align="center">
						<button type="button" onClick="_addRecord();"
							class="btn btn-primary normalButton padding0 line-height0">
							<fmt:message key="button.add" />
						</button>
						<button type="button" onClick="_clearRecord();"
							class="btn btn-primary normalButton padding0 line-height0">
							<fmt:message key="button.clear" />
						</button>
					</td>
				</tr>
				<tr class="row-height"></tr>
			</table>
		</div>
	</div>
</div> --%>
<div class="container-fluid" id="parent_container"  style="overflow-y: auto;">
	<div class="row-fluid" id="column_property_container">
		<div class="span6">
			<div class="widget background-none border-none">
				<div id="col_prop_grid" class="widget-body">
					<div id="columns_property_grid">
						<table id="_LIST_VIEW_COLUMNS_PROPERTY"></table>
						<div id="_LIST_VIEW_COLUMNS_PROPERTY_PAGER"></div>
						<div class="control-group"></div>
					</div>
				</div>
				<div id="col_prop_grid_button" align="center">
				<div class="control-group">
					<div class="button-background no-margin">
						<button type="button" onClick="_saveOrUpdateAllRecord('${listView.id}');" class="btn btn-primary">
							<fmt:message key="button.save" />
						</button>
						<button type="button" onClick="_deleteRecord();" class="btn btn-primary">
							<fmt:message key="button.delete" />
						</button>
						<button type="button" onClick="_deleteAllRecord();" class="btn btn-primary">
							<fmt:message key="button.deleteAll" />
						</button>
						<div class="clearfix"></div>
					</div>
				</div>
			</div>
			</div>
		</div>
		<div class="span6">
			<div class="widget background-none border-none">
				<div class="widget-body" id="col_prop_form">
					<input type="hidden" id="columnPropertyId" name="columnPropertyId" />
					
					<div class="control-group">
						<eazytec:label styleClass="control-label " key="listView.columnProperty.columnTitle" />
						<div class="controls">
							<input type="text" class="span12" id="columnTitle" name="columnTitle" />
						</div>
					</div>

					<div class="control-group">
						<eazytec:label styleClass="control-label" key="listView.columnProperty.dataFields" />
						<div class="controls">
							<select class="span12" id="dataFields" name="dataFields"> </select>
						</div>
					</div>

					<div class="control-group">
						<eazytec:label styleClass="control-label" key="listView.columnProperty.columnWidth" />
						<div class="controls">
							<input type="text" class="span12" id="columnWidth" name="columnWidth" value="0" />
						</div>
					</div>

					<div class="control-group">
						<eazytec:label styleClass="control-label " key="listView.columnProperty.columnsOrder" />
						<div class="controls">
							<input type="text" class="span12" id="columnsOrder" name="columnsOrder" value="0" />
							<input type="hidden" class="span12" id="currentColumnsOrder" name="currentColumnsOrder" />
						</div>
					</div>

					<div class="control-group">
						<eazytec:label styleClass="control-label" key="listView.columnProperty.otherName" />
						<div class="controls">
							<input type="text" class="span12" id="otherName" name="otherName" />
						</div>
					</div>

					<div class="control-group">
						<eazytec:label styleClass="control-label" key="listView.columnProperty.replaceWords" />
						<div class="controls">
							<textarea class="span12" id="replaceWords" name="replaceWords" placeholder="<fmt:message key='list.view.columnProperty.replaceWord.placeholder'/>"></textarea>
						</div>
					</div>

					<div class="control-group">
						<div class="controls">
							<fmt:message key='list.view.columnProperty.replaceWord.placeholder.ex' />
						</div>
					</div>

					<div class="control-group">
						<eazytec:label styleClass="control-label  " key="listView.columnProperty.isSort" />
						<div class="controls checkbox-label">
							<input type="checkbox" id="isSort" name="isSort" checked="true" />
						</div>
					</div>

					<div class="control-group">
						<eazytec:label styleClass="control-label" key="listView.columnProperty.isAdvancedSearch" />
						<div class="controls checkbox-label">
							<input type="checkbox" id="isAdvancedSearch" name="isAdvancedSearch" />
						</div>
					</div>

					<div class="control-group" id="search_field_type">
						<eazytec:label styleClass="control-label" key="listView.columnProperty.dataFieldType" />
						<div class="controls">
							<select id="dataFieldType" class="span12" name="dataFieldType" onChange="showDataDictionary(this);">
								<option value="text"><fmt:message  key="listView.table.text"/></option>
								<option value="date"><fmt:message  key="message.date"/></option>
								<option value="datetime"><fmt:message  key="listView.table.dateTime"/></option>
								<option value="select"><fmt:message  key="listView.table.select"/></option>
								<option value="autocomplete"><fmt:message  key="form.palette.autocomplete"/></option>
								<option value="radio"><fmt:message  key="form.palette.radio"/></option>
								<!-- <option value="checkbox">Checkbox</option>  -->
							</select>
						</div>
					</div>


					<div class="control-group" id="is_range_field">
						<eazytec:label styleClass="control-label" key="listView.columnProperty.isRange" />
						<div class="controls checkbox-label">
							<input type="checkbox" id="isRange" name="isRange" />
						</div>
					</div>

					<div class="control-group" id="data_dictionary_field">
						<eazytec:label styleClass="control-label" key="dataDictionary.form" />
						<div class="controls">
							<input type="text" class="span12" id="select_data_dictionary" name="select_data_dictionary" onClick='selectDataDictinary(this);' /> 
							<input type="hidden" id="dataDictionary" name="dataDictionary" />
						</div>
					</div>
					
					<div class="control-group">
						<eazytec:label styleClass="control-label" key="listView.columnProperty.isSimpleSearch" />
						<div class="controls checkbox-label">
							<input type="checkbox" id="isSimpleSearch" name="isSimpleSearch" checked="true" />
						</div>
					</div>

					<div class="control-group">
						<eazytec:label styleClass="control-label" key="listView.columnProperty.textAlign" />
						<div class="controls ">
							<span class="radio inline">
								<input type="radio" id="textAlignLeft" name="textAlign" value="Left" checked="true" /> <fmt:message  key="listView.table.left"/> &nbsp;
							</span>
							<span class="radio inline">
								<input type="radio" id="textAlignCenter" name="textAlign" value="Center" />  <fmt:message  key="listView.table.center"/> &nbsp;
							</span> 
							<span class="radio inline">
								<input type="radio" id="textAlignRight" name="textAlign" value="Right" /> <fmt:message  key="listView.table.right"/>
							</span>
						</div>
					</div>

					<div class="control-group">
						<eazytec:label styleClass="control-label" key="listView.columnProperty.isHidden" />
						<div class="controls checkbox-label ">
							<input type="checkbox" id="isHidden" name="isHidden" />
						</div>
					</div>
					
					<div class="control-group">
					        <eazytec:label styleClass="control-label style3 style18 " key="listView.columnProperty.columnType" />
					        <div class="controls">
					           	<select id="columnType" class="span12"  name="columnType">
					           		<option value="text"><fmt:message  key="listView.table.text"/></option>
				           			<option value="date"><fmt:message  key="message.date"/></option>
				           			<option value="number"><fmt:message  key="listView.table.number"/></option>
					           	</select>
				            </div>
				    </div>
					
					<div class="control-group">
						<eazytec:label styleClass="control-label" key="listView.columnProperty.isGroupBy" />
						<div class="controls checkbox-label">
							<input type="checkbox" id="isGroupBy" name="isGroupBy" />
						</div>
					</div>

					<div class="control-group">
						<eazytec:label styleClass="control-label" key="listView.columnProperty.onRenderEventName" />
						<div class="controls">
							<input type="text" class="span12" id="onRenderEventName" name="onRenderEventName" />
						</div>
					</div>

					<div class="control-group">
						<eazytec:label styleClass="control-label" key="listView.columnProperty.onRenderEvent" />
						<div class="controls">
							<textarea type="text" class="span12" id="onRenderEvent" name="onRenderEvent" /></textarea>
						</div>
					</div>

					<div class="control-group">
						<eazytec:label styleClass="control-label" key="listView.columnProperty.comment" />
						<div class="controls">
							<textarea type="text" class="span12" id="comment" name="comment" /></textarea>
						</div>
					</div>
				</div>
				<div id="col_prop_form_button" align="center">
					<div class="control-group">
						<div class="button-background no-margin">
							<button type="button" onClick="_addRecord();" class="btn btn-primary">
								<fmt:message key="button.add" />
							</button>
							<button type="button" onClick="_clearRecord();" class="btn btn-primary">
								<fmt:message key="button.clear" />
							</button>
							<div class="clearfix"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(function(){ 
	createColumnsProperty = [];
	deleteColumnsProperty = [];
	_row_id = 0;
	var listViewId = "${listView.id}";
	getListViewColumns("dataFields");
	columnsPropertyGrid();
	//var targetHeight = $('#target').height();
	var columnPropertyHeight =  $('#column_property_container').height();
	//var form_height = $('#col_prop_form').height();
	$('#col_prop_grid').height(parseInt(columnPropertyHeight)-100);
	$('#col_prop_form').height(parseInt(columnPropertyHeight)-100);
	//$('#col_prop_form').height(parseInt(targetHeight)-120);
	//$('#col_prop_grid').width('50%');
	
	var isListViewTemplate = "${isListViewTemplate}";
	if(listViewId && isListViewTemplate == "false"){
		getColumsPropety(listViewId);
	}else if(isListViewTemplate == "true"){
		var listViewTemplateId = "${listViewTemplateId}";
		if(listViewTemplateId){
			getColumsPropety(listViewTemplateId);
		}else{
			getColumsPropety(listViewId);
		}		
		var columnsProperty = new Array();
		var gridData = $("#_LIST_VIEW_COLUMNS_PROPERTY").jqGrid('getRowData'); 
		var _grid_row_id = 0;
		for(var i = 0; i < gridData.length; i++){
		    if (gridData[i]){
		    	var colsPrperty = gridData[i];
		    	var colsPropertyId = colsPrperty.id;
		    	colsPropertyId = ++_grid_row_id;
			    colsPrperty.id = colsPropertyId;
			    columnsProperty.push(colsPrperty);
          		createColumnsProperty[createColumnsProperty.length] = colsPrperty;
			   /*  $.each(selectColumnsArray, function(index, item) {
		          	if(item == colsPrperty.dataFields){
		          		columnsProperty.push(colsPrperty);
		          		createColumnsProperty[createColumnsProperty.length] = colsPrperty;
		          	}
		         }); */
		    }
		}	
    	$("#_LIST_VIEW_COLUMNS_PROPERTY").jqGrid('clearGridData');
		$('#_LIST_VIEW_COLUMNS_PROPERTY').jqGrid('setGridParam', {page: 1, data: columnsProperty}).trigger('reloadGrid');
	}
	$("#isAdvancedSearch").click(function() {
		var $this = $(this);
	    if ($this.is(':checked')) {
	    	$("#search_field_type").slideDown(1000);
	    	$("#is_range_field").show();
	    } else {
	    	$("#search_field_type").slideUp(1000);
	    	$("#data_dictionary_field").hide();
	    	$("#dataFieldType").val("");
	    	$("#dataDictionary").val("");
	    	$("#is_range_field").hide();
	    	$("#isRange").prop('checked', false);
	    }
	});
});
$(window).bind('resize', function() {
// 	 setTimeout(function() {
  		var winWidth = parseInt($("#col_prop_grid").width());
  		var form_height = $('#col_prop_form').height();
  		if(winWidth <= 50){
  			winWidth = parseInt($("#wizardTab-content").width()/2);
  			winWidth = winWidth + 2;
  		}
  		$("#_LIST_VIEW_COLUMNS_PROPERTY").jqGrid('setGridWidth', winWidth-22);
  		/* $("#_LIST_VIEW_COLUMNS_PROPERTY").jqGrid('setGridHeight', form_height-167); */
// 	 },1);			
}).trigger('resize');

function showDataDictionary(selectObj){
	if(selectObj.value == "select" || selectObj.value == "radio" || selectObj.value == "checkbox" || selectObj.value == "autocomplete"){
		$("#data_dictionary_field").show();
		$("#is_range_field").hide();
		$("#isRange").prop('checked', false);
	}else{
		$("#data_dictionary_field").hide();
		$("#is_range_field").show();
		$("#dataDictionary").val("");
	}
}

function selectDataDictinary(inputObj){
	var dataDictionary = $("#dataDictionary").val();
	var selectedValue = dataDictionary != "null" ? dataDictionary : "";
	showDataDictionaryListTree("Data Dictionary List", 'single', "select_data_dictionary" , selectedValue , '', "dataDictionary");
}
</script>
