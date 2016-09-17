package com.eazytec.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.eazytec.exceptions.BpmException;
import com.eazytec.exceptions.EazyBpmException;


/**
 * 
 * @author mahesh
 *
 */
public class GridUtil {
	public static String templateName = "GridTemplate.vm";
	public static String dynamicTemplateName = "DynamicGridTemplate.vm";
	
	public static String generateScript(VelocityEngine velocityEngine, Map<String, Object> context){
		  String result = null;
	        try {
	        	if(null == context.get("noOfRecords")){
	        		context.put("noOfRecords", "10");
	        	}
	        	if(null == context.get("link")){
	        		context.put("link", "");
	        	}
	            result = 
	                VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templateName, context);
	        } catch (Exception e) {
	           throw new EazyBpmException("Problem loading Grid :"+context.get("title"),e);
	        }
		return result;
	}
	
	/**
	 * To generate Dynamic script
	 * @param velocityEngine
	 * @param context
	 * @return
	 */
	public static String generateDynamicScript(VelocityEngine velocityEngine, Map<String, Object> context){
		  String result = null;
	        try {
	        	if(null == context.get("noOfRecords")){
	        		context.put("noOfRecords", "10");
	        	}
	        	if(null == context.get("link")){
	        		context.put("link", "");
	        	}
	            result = 
	                VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, dynamicTemplateName, context);
	        } catch (Exception e) {
		           throw new EazyBpmException("Problem loading Grid :"+context.get("title"),e);
	        }
		return result;
	}
	
	/**
	 * Set into context of grid column names and field names and it attributes
	 * @param context
	 * @return
	 * @throws Exception
	 */
	public static String generateScriptForRoleGrid(List<Map<String,Object>> list, VelocityEngine velocityEngine) throws BpmException{
		Map<String, Object> context = new HashMap<String, Object>();
		List<Map<String, Object>> fieldNameList = new ArrayList<Map<String, Object>>();
		String columnNames = "['Id','Role Name','Description','Type', 'Order No', 'Move Up', 'Move Down', 'Created At']";		
		context.put("title", "Roles");
		context.put("needCheckbox", true);
		/*context.put("link", "/bpm/admin/newRole");*/
		context.put("gridId", "ROLE_LIST");
		context.put("noOfRecords", "20");
		String jsonFieldValues = "";
		if(list != null && !(list.isEmpty())){
			jsonFieldValues = CommonUtil.getJsonString(list);	
		}
		context.put("jsonFieldValues", jsonFieldValues);
		context.put("columnNames", columnNames);	
		CommonUtil.createFieldNameList(fieldNameList, "roleId", "100", "left", "", "true");
		CommonUtil.createFieldNameList(fieldNameList, "name", "100", "left", "_showEditRole", "false");
		CommonUtil.createFieldNameList(fieldNameList, "description", "100", "left", "", "false");
		CommonUtil.createFieldNameList(fieldNameList, "roleType", "100", "left", "", "false");
		CommonUtil.createFieldNameList(fieldNameList, "orderNo", "100", "center", "", "true");
		CommonUtil.createFieldNameList(fieldNameList, "orderUpImg", "100", "center", "_moveRoleGridRowUpImage", "false");
		CommonUtil.createFieldNameList(fieldNameList, "orderDownImg", "100", "center", "_moveRoleGridRowDownImage", "false");
		CommonUtil.createFieldNameList(fieldNameList, "createdTimeByString", "100", "left", "", "false");
		context.put("fieldNameList", fieldNameList);		
		return GridUtil.generateScript(velocityEngine, context);
	}
	
	
	public static String generateScriptForLayoutGrid(List<Map<String,Object>> list, VelocityEngine velocityEngine) throws BpmException{
		Map<String, Object> context = new HashMap<String, Object>();
		List<Map<String, Object>> fieldNameList = new ArrayList<Map<String, Object>>();
		String columnNames = "['Id','Layout Name','Description','No Of Widget','Widget Names','Status']";		
		context.put("title", "Layouts");
		context.put("needCheckbox", true);
		context.put("gridId", "LAYOUT_LIST");
		context.put("noOfRecords", "20");
		String jsonFieldValues = "";
		if(list != null && !(list.isEmpty())){
			jsonFieldValues = CommonUtil.getJsonString(list);	
		}
		context.put("jsonFieldValues", jsonFieldValues);
		context.put("columnNames", columnNames);	
		CommonUtil.createFieldNameList(fieldNameList, "id", "100", "left", "", "true");
		CommonUtil.createFieldNameList(fieldNameList, "name", "100", "left", "_editLayout", "false");
		CommonUtil.createFieldNameList(fieldNameList, "description", "100", "left", "", "false");
		CommonUtil.createFieldNameList(fieldNameList, "noOfWidget", "100", "left", "", "false");
		CommonUtil.createFieldNameList(fieldNameList, "widgetNames", "100", "left", "", "false");
		CommonUtil.createFieldNameList(fieldNameList, "status", "100", "left", "", "false");
		context.put("fieldNameList", fieldNameList);		
		return GridUtil.generateScript(velocityEngine, context);
	}
	
	public static String generateScriptForWidgetGrid(List<Map<String,Object>> list, VelocityEngine velocityEngine) throws BpmException{
		Map<String, Object> context = new HashMap<String, Object>();
		List<Map<String, Object>> fieldNameList = new ArrayList<Map<String, Object>>();
		String columnNames = "['Id','Widget Name','URL','Is Active']";		
		context.put("title", "Widgets");
		context.put("needCheckbox", true);
		context.put("gridId", "WIDGET_LIST");
		context.put("noOfRecords", "20");
		String jsonFieldValues = "";
		if(list != null && !(list.isEmpty())){
			jsonFieldValues = CommonUtil.getJsonString(list);	
		}
		context.put("jsonFieldValues", jsonFieldValues);
		context.put("columnNames", columnNames);	
		CommonUtil.createFieldNameList(fieldNameList, "id", "100", "left", "", "true");
		CommonUtil.createFieldNameList(fieldNameList, "name", "100", "left", "_editWidget", "false");
		CommonUtil.createFieldNameList(fieldNameList, "widgetUrl", "100", "left", "", "false");
		CommonUtil.createFieldNameList(fieldNameList, "isActive", "100", "left", "", "false");
		context.put("fieldNameList", fieldNameList);		
		return GridUtil.generateScript(velocityEngine, context);
	}
	
	
	public static String generateScriptForQuickNavGrid(List<Map<String,Object>> list, VelocityEngine velocityEngine) throws BpmException{
		Map<String, Object> context = new HashMap<String, Object>();
		List<Map<String, Object>> fieldNameList = new ArrayList<Map<String, Object>>();
		String columnNames = "['Id','Navigation Name','Sort Order','Icon Path','Url','Add QuickNav']";		
		context.put("title", "Quick Navigations");
		context.put("needCheckbox", true);
		context.put("gridId", "QUICKNAV_LIST");
		context.put("noOfRecords", "20");
		String jsonFieldValues = "";
		if(list != null && !(list.isEmpty())){
			jsonFieldValues = CommonUtil.getJsonString(list);	
		}
		context.put("jsonFieldValues", jsonFieldValues);
		context.put("columnNames", columnNames);	
		CommonUtil.createFieldNameList(fieldNameList, "id", "100", "left", "", "true");
		CommonUtil.createFieldNameList(fieldNameList, "name", "100", "left", "_editQuickNav", "false");
		CommonUtil.createFieldNameList(fieldNameList, "sortOrder", "100", "left", "", "false");
		CommonUtil.createFieldNameList(fieldNameList, "iconPath", "100", "left", "", "false");
		CommonUtil.createFieldNameList(fieldNameList, "url", "100", "left", "", "false");
		CommonUtil.createFieldNameList(fieldNameList, "status", "100", "left", "", "false");
		context.put("fieldNameList", fieldNameList);		
		return GridUtil.generateScript(velocityEngine, context);
	}
	/**
	 * Set into context of grid column names and field names and it attributes
	 * @param context
	 * @return
	 * @throws Exception
	 */
	public static String generateScriptForGroupGrid(List<Map<String,Object>> list, VelocityEngine velocityEngine) throws BpmException{
		Map<String, Object> context = new HashMap<String, Object>();
		List<Map<String, Object>> fieldNameList = new ArrayList<Map<String, Object>>();
		String columnNames = "['Id','Group Name','Description','Person Incharge', 'Parent Group', 'Order No', 'Move Up', 'Move Down', 'Created At']";		
		context.put("title", "Groups");
		context.put("gridId", "GROUP_LIST");
		//context.put("link", "bpm/admin/groupForm");
		context.put("noOfRecords", "20");
		context.put("needCheckbox", true);
		String jsonFieldValues = "";
		if(list != null && !(list.isEmpty())){
			jsonFieldValues = CommonUtil.getJsonString(list);	
		}
		context.put("jsonFieldValues", jsonFieldValues);
		context.put("columnNames", columnNames);	
		CommonUtil.createFieldNameList(fieldNameList, "groupId", "100", "left", "", "true");
		CommonUtil.createFieldNameList(fieldNameList, "viewName", "100", "left", "_showEditGroup", "false");
		CommonUtil.createFieldNameList(fieldNameList, "description", "100", "left", "", "false");
		CommonUtil.createFieldNameList(fieldNameList, "personIncharge", "100", "left", "", "false");
		CommonUtil.createFieldNameList(fieldNameList, "superGroup", "100", "left", "", "false");
		CommonUtil.createFieldNameList(fieldNameList, "orderNo", "100", "center", "", "true");
		CommonUtil.createFieldNameList(fieldNameList, "orderUpImg", "100", "center", "_moveGroupGridRowUpImage", "false");
		CommonUtil.createFieldNameList(fieldNameList, "orderDownImg", "100", "center", "_moveGroupGridDownImage", "false");
		CommonUtil.createFieldNameList(fieldNameList, "createdTimeByString", "100", "left", "", "false");
		
		context.put("fieldNameList", fieldNameList);		
		return GridUtil.generateScript(velocityEngine, context);
	}
	
	/**
	 * Set into context of grid column names and field names and it attributes
	 * @param context
	 * @return
	 * @throws Exception
	 */
	public static String generateScriptForDepartmentGrid(List<Map<String,Object>> list, VelocityEngine velocityEngine) throws BpmException{
		Map<String, Object> context = new HashMap<String, Object>();
		List<Map<String, Object>> fieldNameList = new ArrayList<Map<String, Object>>();
		String columnNames = "['Id','Department Name','Description','Department Owner', 'Order No', 'Move Up', 'Move Down', 'Created At','Department Type']";		
		context.put("title", "Departments");
		context.put("gridId", "DEPARTMENT_LIST");
		/*context.put("link", "bpm/admin/departmentForm");*/
		context.put("noOfRecords", "20");
		context.put("needCheckbox", true);
		String jsonFieldValues = "";
		if(list != null && !(list.isEmpty())){
			jsonFieldValues = CommonUtil.getJsonString(list);	
		}
		context.put("jsonFieldValues", jsonFieldValues);
		context.put("columnNames", columnNames);	
		CommonUtil.createFieldNameList(fieldNameList, "departmentId", "100", "left", "", "true");
		CommonUtil.createFieldNameList(fieldNameList, "viewName", "60", "left", "_showEditDepartment", "false");
		CommonUtil.createFieldNameList(fieldNameList, "description", "60", "left", "", "false");
		CommonUtil.createFieldNameList(fieldNameList, "departmentOwner", "100", "left", "", "true");
		CommonUtil.createFieldNameList(fieldNameList, "orderNo", "100", "center", "", "true");
		CommonUtil.createFieldNameList(fieldNameList, "orderUpImg", "60", "center", "_moveDepartmentGridRowUpImage", "false");
		CommonUtil.createFieldNameList(fieldNameList, "orderDownImg", "60", "center", "_moveDepartmentGridDownImage", "false");
		CommonUtil.createFieldNameList(fieldNameList, "createdTimeByString", "60", "center", "", "false");
		CommonUtil.createFieldNameList(fieldNameList, "departmentType", "100", "center", "", "true");
		context.put("fieldNameList", fieldNameList);		
		return GridUtil.generateScript(velocityEngine, context);
	}
	
	/**
	 * Set into context of grid column names and field names and it attributes
	 * @param context
	 * @return
	 * @throws Exception
	 */
	public static String generateScriptForMenuGrid(List<Map<String,Object>> list, VelocityEngine velocityEngine) throws BpmException{
		Map<String, Object> context = new HashMap<String, Object>();
		List<Map<String, Object>> fieldNameList = new ArrayList<Map<String, Object>>();
		String columnNames = "['Id','Menu Name','Description']";		
		context.put("title", "Menus");
		context.put("gridId", "MENU_LIST");
		context.put("link", "bpm/admin/menuForm");
		context.put("noOfRecords", "20");
		String jsonFieldValues = "";
		if(list != null && !(list.isEmpty())){
			jsonFieldValues = CommonUtil.getJsonString(list);	
		}
		context.put("jsonFieldValues", jsonFieldValues);
		context.put("columnNames", columnNames);	
		CommonUtil.createFieldNameList(fieldNameList, "id", "100", "left", "", "true");
		CommonUtil.createFieldNameList(fieldNameList, "name", "100", "left", "_showEditMenu", "false");
		CommonUtil.createFieldNameList(fieldNameList, "description", "100", "left", "", "false");
		
		context.put("fieldNameList", fieldNameList);		
		return GridUtil.generateScript(velocityEngine, context);
	}
	
	/**
	 * Set into context of grid column names and field names and it attributes
	 * @param context
	 * @return
	 * @throws Exception
	 */
	public static String generateScriptForUserGrid(List<Map<String,Object>> list, VelocityEngine velocityEngine) throws BpmException{
		Map<String, Object> context = new HashMap<String, Object>();
		List<Map<String, Object>> fieldNameList = new ArrayList<Map<String, Object>>();
		String columnNames = "['Id','User Name','Full Name','Department','Email','Employee Number','Status']";		
		context.put("title", "Users");
		context.put("needCheckbox", true);
		context.put("gridId", "USER_LIST");
		/*context.put("link", "/bpm/admin/userform");
		context.put("params", "method=add");*/
		context.put("noOfRecords", "20");
		context.put("dynamicGridWidth", "organizationUserGridWidth");
		context.put("dynamicGridHeight", "organizationGridHeight");
		String jsonFieldValues = "";
		if(list != null && !(list.isEmpty())){
			jsonFieldValues = CommonUtil.getJsonString(list);	
		}
		context.put("jsonFieldValues", jsonFieldValues);
		context.put("columnNames", columnNames);	
		CommonUtil.createFieldNameList(fieldNameList, "id", "50", "left", "", "true");
		CommonUtil.createFieldNameList(fieldNameList, "username", "100", "left", "_showEditUser", "false");
		CommonUtil.createFieldNameList(fieldNameList, "fullName", "100", "left", "", "false");
		CommonUtil.createFieldNameList(fieldNameList, "department", "100", "left", "", "false");
		CommonUtil.createFieldNameList(fieldNameList, "email", "100", "left", "", "false");
		CommonUtil.createFieldNameList(fieldNameList, "employeeNumber", "80", "center", "", "false");
		CommonUtil.createFieldNameList(fieldNameList, "enabled", "50", "center", "_showEnabled", "false");
		context.put("fieldNameList", fieldNameList);		
		return GridUtil.generateScript(velocityEngine, context);
	}
	
	
	
	/**
	 * Set into context of grid column names and field names and it attributes
	 * @param context
	 * @return
	 * @throws Exception
	 */
	public static String generateScriptForDataDictionaryGrid(
			List<Map<String, Object>> dictionaryList,
			VelocityEngine velocityEngine) throws BpmException {
		Map<String, Object> context = new HashMap<String, Object>();
		List<Map<String, Object>> fieldNameList = new ArrayList<Map<String, Object>>();
		String columnNames = "['Id','Dictionary Name','Dictionary Code','Status','Order','Move Up','Move Down','Interior Dictionary']";
		context.put("title", "Data Dictionary List");
		context.put("gridId", "DICTIONARY_LIST");
		context.put("needCheckbox", true);
		context.put("needTreeStructure", true);
		context.put("dynamicGridWidth", "organizationUserGridWidth");
		context.put("dynamicGridHeight", "organizationGridHeight");

		String jsonFieldValues = "";
		if (dictionaryList != null && !(dictionaryList.isEmpty())) {
			jsonFieldValues = CommonUtil.getJsonString(dictionaryList);
		}
		context.put("jsonFieldValues", jsonFieldValues);
		context.put("columnNames", columnNames);

		CommonUtil.createFieldNameList(fieldNameList, "dictionaryId", "100",
				"left", "", "true");
		CommonUtil.createFieldNameList(fieldNameList, "name", "150", "left",
				"_editDataDictionary", "false");
		CommonUtil.createFieldNameList(fieldNameList, "code", "150", "left",
				"", "false");
		CommonUtil.createFieldNameList(fieldNameList, "isEnabled", "100",
				"center", "_showIsEnabled", "false");
		CommonUtil.createFieldNameList(fieldNameList, "orderNo", "100",
				"center", "", "true");
		CommonUtil.createFieldNameList(fieldNameList, "orderUpImg", "100",
				"center", "_showMoveUpImage", "false");
		CommonUtil.createFieldNameList(fieldNameList, "orderDownImg", "100",
				"center", "_showMoveDownImage", "false");
		CommonUtil.createFieldNameList(fieldNameList, "interiorDictionary",
				"100", "center", "_showInteriorDictionaries", "false");

		context.put("fieldNameList", fieldNameList);
		return GridUtil.generateScript(velocityEngine, context);
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	
	/**
	 * Set into context of grid column names and field names and it attributes
	 * @param context
	 * @return
	 * @throws Exception
	 */
	public static String generateScriptForScheduleGrid(List<Map<String,Object>> list, VelocityEngine velocityEngine) throws BpmException{
		Map<String, Object> context = new HashMap<String, Object>();
		List<Map<String, Object>> fieldNameList = new ArrayList<Map<String, Object>>();
		String columnNames = "['Id','Event Name','Description','Event Type ','Location','Start Date','End Date','Start Time','End Time','Assigned','CreatedBy']";		
		context.put("title", "Schedule");
		context.put("gridId", "SCHEDULE_LIST");
		context.put("noOfRecords", "20");
		context.put("needCheckbox", true);
		String jsonFieldValues = "";
		if(list != null && !(list.isEmpty())){
			jsonFieldValues = CommonUtil.getJsonString(list);	
		}
		context.put("jsonFieldValues", jsonFieldValues);
		context.put("columnNames", columnNames);	
		CommonUtil.createFieldNameList(fieldNameList, "id", "100", "left", "", "true");
		CommonUtil.createFieldNameList(fieldNameList, "eventName", "100", "left", "_showEditSchedule", "false");
		CommonUtil.createFieldNameList(fieldNameList, "description", "100", "left", "", "false");
		CommonUtil.createFieldNameList(fieldNameList, "eventType", "100", "left", "", "false");
		CommonUtil.createFieldNameList(fieldNameList, "location", "100", "center", "", "false");
		CommonUtil.createFieldNameList(fieldNameList, "startDate", "100", "center", "_changeStartDateFormat", "false");
		CommonUtil.createFieldNameList(fieldNameList, "endDate", "100", "center", "_changeEndDateFormat", "false");
		CommonUtil.createFieldNameList(fieldNameList, "startTime", "100", "center", "", "false");
		CommonUtil.createFieldNameList(fieldNameList, "endTime", "100", "center", "", "false");
		CommonUtil.createFieldNameList(fieldNameList, "assignedUser", "100", "center", "", "false");
		CommonUtil.createFieldNameList(fieldNameList,"createdBy","100","center","","true");
		context.put("fieldNameList", fieldNameList);		
		return GridUtil.generateScript(velocityEngine, context);
	}
	
		/**
	    * Set into context of grid column names and field names and it attributes
	    * @param context
	    * @return
	    * @throws Exception
	    */
	   public static String generateScriptForDocumentFormGrid(List<Map<String,Object>> list, VelocityEngine velocityEngine) throws BpmException{
	           Map<String, Object> context = new HashMap<String, Object>();
	           List<Map<String, Object>> fieldNameList = new ArrayList<Map<String, Object>>();
	           String columnNames = "['Id','DocumentForm Name','Created By','Created At']";            
	           context.put("title", "Documents");
	           context.put("needCheckbox", false);
	          /* context.put("link", "/bpm/dms/documentForm");*/
	           context.put("gridId", "DOCUMENTFORM_LIST");
	           context.put("noOfRecords", "20");
	           context.put("noOfRecords", "20");
	   		   context.put("dynamicGridWidth", "documentGridWidth");
	   		   context.put("dynamicGridHeight", "documentGridHeight");
	   		   context.put("needTreeStructure", true);
	           String jsonFieldValues = "";
	           if(list != null && !(list.isEmpty())){
	                   jsonFieldValues = CommonUtil.getJsonString(list);       
	           }
	           context.put("jsonFieldValues", jsonFieldValues);
	           context.put("columnNames", columnNames);        
	           CommonUtil.createFieldNameList(fieldNameList, "id", "25", "left", "", "true");
	           CommonUtil.createFieldNameList(fieldNameList, "name", "25", "left", "_showViewDocumentForm", "false");
	           CommonUtil.createFieldNameList(fieldNameList, "createdBy", "25", "left", "", "false");
	           CommonUtil.createFieldNameList(fieldNameList, "createdTimeByString", "25", "left", "", "false");
	           context.put("fieldNameList", fieldNameList);            
	           return GridUtil.generateScript(velocityEngine, context);
	   }
	   
	   
	   /**
		 * Set into context of grid column names and field names and it attributes
		 * @param context
		 * @return
		 * @throws Exception
		 */
		public static String generateScriptForDMSUserPermissionGrid(String gridType, List<Map<String,Object>> list, VelocityEngine velocityEngine) throws BpmException{
			Map<String, Object> context = new HashMap<String, Object>();
			List<Map<String, Object>> fieldNameList = new ArrayList<Map<String, Object>>();
			String columnNames = "";
			if(gridType.equalsIgnoreCase("document")){
				columnNames = "['Id','Name','Edit','Delete','Print', 'Download']";
			}else {
				columnNames = "['Id','Name','Write','Delete','Print','Download','Read']";
			}

			context.put("title", "User Permissions");
			context.put("gridId", "USER_PERMISSION_LIST");
			/*context.put("link", "bpm/admin/departmentForm");*/
			context.put("noOfRecords", "20");
			context.put("needCheckbox", true);
			context.put("dynamicGridWidth", "userPermissionGridWidth");
	   		context.put("dynamicGridHeight", "userPermissionGridHeight");
			String jsonFieldValues = "";
			if(list != null && !(list.isEmpty())){
				jsonFieldValues = CommonUtil.getJsonString(list);	
			}
			context.put("jsonFieldValues", jsonFieldValues);
			context.put("columnNames", columnNames);	
			CommonUtil.createFieldNameList(fieldNameList, "id", "100", "center", "", "true");
			CommonUtil.createFieldNameList(fieldNameList, "userName", "100", "left", "_editDmsUserPermission", "false");
			CommonUtil.createFieldNameList(fieldNameList, "canEdit", "100", "center", "", "false");
			CommonUtil.createFieldNameList(fieldNameList, "canDelete", "100", "center", "", "false");
			CommonUtil.createFieldNameList(fieldNameList, "canPrint", "100", "center", "", "false");
			CommonUtil.createFieldNameList(fieldNameList, "canDownload", "100", "center", "", "false");
			if(gridType.equalsIgnoreCase("folder")) {
				CommonUtil.createFieldNameList(fieldNameList, "canRead", "100", "center", "", "false");
			}			
			context.put("fieldNameList", fieldNameList);		
			return GridUtil.generateScript(velocityEngine, context);
		}
		
		/**
		 * Set into context of grid column names and field names and it attributes
		 * @param context
		 * @return
		 * @throws Exception
		 */
		public static String generateScriptForDMSRolePermissionGrid(String gridType, List<Map<String,Object>> list, VelocityEngine velocityEngine) throws BpmException{
			Map<String, Object> context = new HashMap<String, Object>();
			List<Map<String, Object>> fieldNameList = new ArrayList<Map<String, Object>>();
			String columnNames = "";
			if(gridType.equalsIgnoreCase("document")){
				columnNames = "['Id','Name','User Name','Edit','Delete','Print', 'Download','Read']";
			}else {
				columnNames = "['Id','Name','User Name','Write','Delete','Print','Download','Read']";
			}	
			context.put("title", "Permissions");
			context.put("gridId", "ROLE_PERMISSION_LIST");
			/*context.put("link", "bpm/admin/departmentForm");*/
			context.put("noOfRecords", "20");
			context.put("needCheckbox", true);
			context.put("dynamicGridWidth", "userPermissionGridWidth");
	   		context.put("dynamicGridHeight", "userPermissionGridHeight");
			String jsonFieldValues = "";
			if(list != null && !(list.isEmpty())){
				jsonFieldValues = CommonUtil.getJsonString(list);	
			}
			context.put("jsonFieldValues", jsonFieldValues);
			context.put("columnNames", columnNames);	
			CommonUtil.createFieldNameList(fieldNameList, "id", "100", "center", "", "true");
			CommonUtil.createFieldNameList(fieldNameList, "name", "100", "left", "_editDmsRolePermission", "true");
			CommonUtil.createFieldNameList(fieldNameList, "userFullName", "100", "left", "_editDmsRolePermission", "false");
			CommonUtil.createFieldNameList(fieldNameList, "canEdit", "100", "center", "", "false");
			CommonUtil.createFieldNameList(fieldNameList, "canDelete", "100", "center", "", "false");
			CommonUtil.createFieldNameList(fieldNameList, "canPrint", "100", "center", "", "false");
			CommonUtil.createFieldNameList(fieldNameList, "canDownload", "100", "center", "", "false");
//				if(gridType.equalsIgnoreCase("folder")) {
					CommonUtil.createFieldNameList(fieldNameList, "canRead", "100", "center", "", "false");
//				}
			context.put("fieldNameList", fieldNameList);		
			return GridUtil.generateScript(velocityEngine, context);
		}
		
		/**
		 * Set into context of grid column names and field names and it attributes
		 * @param context
		 * @return
		 * @throws Exception
		 */
		public static String generateScriptForDMSGroupPermissionGrid(String gridType, List<Map<String,Object>> list, VelocityEngine velocityEngine) throws BpmException{
			Map<String, Object> context = new HashMap<String, Object>();
			List<Map<String, Object>> fieldNameList = new ArrayList<Map<String, Object>>();
			String columnNames = "";
			if(gridType.equalsIgnoreCase("document")){
				columnNames = "['Id','Name','Edit','Delete','Print', 'Download']";
			}else {
				columnNames = "['Id','Name','Write','Delete','Print','Download','Read']";
			}
			context.put("title", "Group Permissions");
			context.put("gridId", "GROUP_PERMISSION_LIST");
			/*context.put("link", "bpm/admin/departmentForm");*/
			context.put("noOfRecords", "20");
			context.put("needCheckbox", true);
			context.put("dynamicGridWidth", "userPermissionGridWidth");
	   		context.put("dynamicGridHeight", "userPermissionGridHeight");
			String jsonFieldValues = "";
			if(list != null && !(list.isEmpty())){
				jsonFieldValues = CommonUtil.getJsonString(list);	
			}
			context.put("jsonFieldValues", jsonFieldValues);
			context.put("columnNames", columnNames);	
			CommonUtil.createFieldNameList(fieldNameList, "id", "100", "center", "", "true");
			CommonUtil.createFieldNameList(fieldNameList, "roleName", "100", "left", "_editDmsGroupPermission", "false");
			CommonUtil.createFieldNameList(fieldNameList, "canEdit", "100", "center", "", "false");	
			CommonUtil.createFieldNameList(fieldNameList, "canDelete", "100", "center", "", "false");
			CommonUtil.createFieldNameList(fieldNameList, "canPrint", "100", "center", "", "false");
			CommonUtil.createFieldNameList(fieldNameList, "canDownload", "100", "center", "", "false");
				if(gridType.equalsIgnoreCase("folder")) {
					CommonUtil.createFieldNameList(fieldNameList, "canRead", "100", "center", "", "false");
				}
			context.put("fieldNameList", fieldNameList);		
			return GridUtil.generateScript(velocityEngine, context);
		}
			
		
		public static String generateScriptForSysConfigGrid(List<Map<String,Object>> list, VelocityEngine velocityEngine) throws BpmException{
			Map<String, Object> context = new HashMap<String, Object>();
			List<Map<String, Object>> fieldNameList = new ArrayList<Map<String, Object>>();
			String columnNames = "['Id','Key','Description','Type','Value','SystemDefined']";		
			context.put("title", "Manage SysConfig");
			context.put("needHeader", true);
			context.put("needCheckbox", true);
			context.put("gridId", "SYSCONFIG_LIST");
			/*context.put("link", "/bpm/admin/userform");
			context.put("params", "method=add");*/
			context.put("noOfRecords", "20");
			String jsonFieldValues = "";
			if(list != null && !(list.isEmpty())){
				jsonFieldValues = CommonUtil.getJsonString(list);	
			}
			context.put("jsonFieldValues", jsonFieldValues);
			context.put("columnNames", columnNames);	
			CommonUtil.createFieldNameList(fieldNameList, "id", "50", "left", "", "true");
			CommonUtil.createFieldNameList(fieldNameList, "selectKey", "100", "left","_showEditSysConfig","false");
			CommonUtil.createFieldNameList(fieldNameList,"description","100","left","","false");
			CommonUtil.createFieldNameList(fieldNameList, "selectType", "100", "left", "", "false");
			CommonUtil.createFieldNameList(fieldNameList, "selectValue", "100", "left", "_showSysconfigValue", "false");
			CommonUtil.createFieldNameList(fieldNameList, "systemDefined","100","left","","true");
			context.put("fieldNameList", fieldNameList);		
			return GridUtil.generateScript(velocityEngine, context);
		}
		
		public static String generateScriptForTimingTaskGrid(List<Map<String,Object>> list, VelocityEngine velocityEngine) throws BpmException{
			Map<String, Object> context = new HashMap<String, Object>();
			List<Map<String, Object>> fieldNameList = new ArrayList<Map<String, Object>>();
			String columnNames = "['Task Name','Job Name','Description','Next FireTime','Previous FireTime','Repeat  Interval','Trigger Name']";		
			context.put("title", "TimingTask");
			context.put("needCheckbox", true);
			/*context.put("link", "/bpm/admin/newRole");*/
			context.put("gridId", "TIMINGTASK_LIST");
			context.put("noOfRecords", "20");
			String jsonFieldValues = "";
			if(list != null && !(list.isEmpty())){
				jsonFieldValues = CommonUtil.getJsonString(list);	
			}
			context.put("jsonFieldValues", jsonFieldValues);
			context.put("columnNames", columnNames);	
			
			CommonUtil.createFieldNameList(fieldNameList, "name", "100", "left", "_showEditTrigger", "false");
			CommonUtil.createFieldNameList(fieldNameList, "jobName", "100", "left", "", "false");
			CommonUtil.createFieldNameList(fieldNameList, "description", "100", "left", "", "false");
			CommonUtil.createFieldNameList(fieldNameList, "nextFireTime", "100", "left", "", "false");
			CommonUtil.createFieldNameList(fieldNameList, "previousFireTime", "100", "left", "", "false");
			CommonUtil.createFieldNameList(fieldNameList, "intervel", "100", "left", "", "false"); 
			CommonUtil.createFieldNameList(fieldNameList, "triggerName", "100", "left", "", "true");
			context.put("fieldNameList", fieldNameList);		
			return GridUtil.generateScript(velocityEngine, context);
		}

		
		public static String generateScriptForQuartzJobGrid(List<Map<String,Object>> list, VelocityEngine velocityEngine) throws BpmException{
			Map<String, Object> context = new HashMap<String, Object>();
			List<Map<String, Object>> fieldNameList = new ArrayList<Map<String, Object>>();
			String columnNames = "['Id ','Trigger Name',' Start Date ',' End Date']";		
			context.put("title", "QuartzJob");
			context.put("needCheckbox", true);
			/*context.put("link", "/bpm/admin/newRole");*/
			context.put("gridId", "QUARTZJOB_LIST");
			context.put("noOfRecords", "20");
			String jsonFieldValues = "";
			if(list != null && !(list.isEmpty())){
				jsonFieldValues = CommonUtil.getJsonString(list);	
			}
			context.put("jsonFieldValues", jsonFieldValues);
			context.put("columnNames", columnNames);	
			
			CommonUtil.createFieldNameList(fieldNameList, "id", "100", "left", "", "false");
			CommonUtil.createFieldNameList(fieldNameList, "triggerName", "100", "left", "_showEditQuartzJob", "false");
			CommonUtil.createFieldNameList(fieldNameList, "startDate", "100", "left", "", "false");
			CommonUtil.createFieldNameList(fieldNameList, "endDate", "100", "left", "", "false");
			context.put("fieldNameList", fieldNameList);		
			return GridUtil.generateScript(velocityEngine, context);
		}
		
		
		public static String generateScriptForSystemLogGrid(List<Map<String,Object>> list, VelocityEngine velocityEngine) throws BpmException{
			Map<String, Object> context = new HashMap<String, Object>();
			List<Map<String, Object>> fieldNameList = new ArrayList<Map<String, Object>>();
			String columnNames = "['id','Name','Type','Save Cycle','Status']";		
			context.put("title", "SystemLog");
			context.put("needCheckbox", true);
			context.put("gridId", "SYSTEMLOG_LIST");
			/*context.put("link", "/bpm/admin/userform");
			context.put("params", "method=add");*/
			context.put("noOfRecords", "20");
			String jsonFieldValues = "";
			if(list != null && !(list.isEmpty())){
				jsonFieldValues = CommonUtil.getJsonString(list);	
			}
			context.put("jsonFieldValues", jsonFieldValues);
			context.put("columnNames", columnNames);	
			CommonUtil.createFieldNameList(fieldNameList, "id", "50", "left", "", "true");
			CommonUtil.createFieldNameList(fieldNameList, "name", "50", "left", "_showEditSystemLog", "false");
			CommonUtil.createFieldNameList(fieldNameList, "logType", "100", "left","","false");
			CommonUtil.createFieldNameList(fieldNameList, "saveCycle", "100", "left", "", "false");
			CommonUtil.createFieldNameList(fieldNameList, "status", "100", "left", "", "false");
			context.put("fieldNameList", fieldNameList);		
			return GridUtil.generateScript(velocityEngine, context);
		}
		
		
		/* For Report */
		public static String generateScriptForReportGrid(List<Map<String,Object>> list, VelocityEngine velocityEngine) throws BpmException{
			Map<String, Object> context = new HashMap<String, Object>();
			List<Map<String, Object>> fieldNameList = new ArrayList<Map<String, Object>>();
			String columnNames = "['id','Report Name','Report Url','Classification','Roles','Description','Status','groups','users','departments']";		
			context.put("title", "Report");
			context.put("needCheckbox", true);
			context.put("gridId", "REPORT_LIST");
			/*context.put("link", "/bpm/report/");
			context.put("params", "method=add");*/
			context.put("noOfRecords", "20");
			String jsonFieldValues = "";
			if(list != null && !(list.isEmpty())){
				jsonFieldValues = CommonUtil.getJsonString(list);	
			}
			context.put("jsonFieldValues", jsonFieldValues);
			context.put("columnNames", columnNames);	
			CommonUtil.createFieldNameList(fieldNameList, "id", "100", "left", "", "true");
			CommonUtil.createFieldNameList(fieldNameList, "name", "100", "left", "_showEditReportForm", "false");
			CommonUtil.createFieldNameList(fieldNameList, "reportUrl", "100", "left","","false");
			CommonUtil.createFieldNameList(fieldNameList, "classification", "100", "left", "", "false");
			CommonUtil.createFieldNameList(fieldNameList, "roles", "100", "left", "", "false");				
			CommonUtil.createFieldNameList(fieldNameList, "users", "100", "left", "", "false");
			CommonUtil.createFieldNameList(fieldNameList, "groups", "100", "left", "", "false");
			CommonUtil.createFieldNameList(fieldNameList, "departments", "100", "left", "", "false");
			CommonUtil.createFieldNameList(fieldNameList, "status", "100", "left", "", "false");
			CommonUtil.createFieldNameList(fieldNameList, "description", "100", "left", "", "false");
			context.put("fieldNameList", fieldNameList);		
			return GridUtil.generateScript(velocityEngine, context);
		}
}
