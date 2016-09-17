package com.eazytec.webapp.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.app.VelocityEngine;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.eazytec.Constants;
import com.eazytec.bpm.admin.datadictionary.service.DataDictionaryService;
import com.eazytec.common.util.CommonUtil;
import com.eazytec.common.util.GridUtil;
import com.eazytec.exceptions.BpmException;
import com.eazytec.exceptions.EazyBpmException;
import com.eazytec.model.DataDictionary;
import com.eazytec.model.LabelValue;
import com.eazytec.model.User;
import com.eazytec.util.JSTreeUtil;

/**
 * <p>
 * Holds all the functionalities related to configure a data dictionary
 * Functions such as Create,Delete,Disable are carried out.
 * </p>
 * @author Megala
 * @author Karthick
 * 
 */
@Controller
public class DataDictionaryController extends BaseFormController{
	
	/**
     * Log variable for all child classes. Uses LogFactory.getLog(getClass()) from Commons Logging
     */
    protected final Log log = LogFactory.getLog(getClass());
    public VelocityEngine velocityEngine;
    private DataDictionaryService dataDictionaryService;
    
    
    /**
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "bpm/admin/dataDictionary", method = RequestMethod.GET)
    public ModelAndView showDataDictionaryList(ModelMap model,@RequestParam("parentDictId") String parentDictId,@RequestParam("hierarchyParentId") String hierarchyParentId)  {
    	
        try {
        	String[] reqdColmns = {"dictionaryId","name","code","isEnabled","orderNo"};
        	List<DataDictionary> dataDictionaries =  dataDictionaryService.getDataDictionaryByParentId(parentDictId);
        	
        	//List<Map<String,Object>> dataList = CommonUtil.getMapListFromObjectListByFieldNames(dataDictionaries,reqdColmns);
//        	String script=GridUtil.generateScriptForDataDictionaryGrid(dataList, velocityEngine);
//            model.addAttribute("script", script);
            model.addAttribute("type", "dictionaryList");
            model.addAttribute("parentDictId",parentDictId);
            model.addAttribute("hierarchyParentId",hierarchyParentId);
//	    	model.addAttribute("availableDictionaries", dataDictionaries);
            
	    	//Constructing Tree
	    	JSONArray jsonDataArray = new JSONArray();
	    	if(parentDictId.equalsIgnoreCase("UserDefined")){
	    		jsonDataArray.put(JSTreeUtil.constructDefaultDataDictionaryJson("UserDefined","User-Defined",dataDictionaries));
	    	}else {
	    		jsonDataArray.put(JSTreeUtil.constructDefaultDataDictionaryJson("UserDefined","User-Defined",dataDictionaryService.getDataDictionaryByParentId("UserDefined")));
	    	}
	    	if(parentDictId.equalsIgnoreCase("SystemDefined")){
	    		jsonDataArray.put(JSTreeUtil.constructDefaultDataDictionaryJson("SystemDefined","System-Defined",dataDictionaries));
	    	}else {
	    		jsonDataArray.put(JSTreeUtil.constructDefaultDataDictionaryJson("SystemDefined","System-Defined",dataDictionaryService.getDataDictionaryByParentId("SystemDefined")));
	    	}
	    	model.addAttribute("dataDictionaryTree",jsonDataArray);
        } catch (Exception e) {
            model.addAttribute("searchError",e.getMessage());
            log.error("Error while showing dictionary data list"+e.getMessage(),e);
        }
        return new ModelAndView("admin/manageDataDictionary", model);
        
    }
    
    
    
    /**
     * Creates new Dictionary 
     * If dictionary refers a parent dictionary,its id is related. if not the default root node
     * (system / user ) is mentioned
     * @return
     * 		View page containing the dictionary list
     * @throws Exception
     */
    @RequestMapping(value = "bpm/admin/newDictionary", method = RequestMethod.GET)
    public ModelAndView addNewDictionary(ModelMap model,@RequestParam("parentDictId") String id,@RequestParam("hierarchyParentId") String hierarchyParentId)  {
    	
    	DataDictionary dataDictionary = new DataDictionary();
    	dataDictionary.setParentDictId(id);
    	model.addAttribute("mode",Constants.CREATE_MODE);
    	model.addAttribute("dataDictionary",dataDictionary);
		model.addAttribute("hierarchyParentId",hierarchyParentId);
    	//model.addAttribute("isEnabled",dataDictionary.getIsEnabled());
    	return new ModelAndView("admin/dataDictionaryForm", model);
    }
    
    
    /**
     * 
     * Gets all data dictionaries as label value @author Karthick
     * Displays the data dictionaries list in tree structure @author Megala
     * @return
     * 		
     * @throws Exception
     */
    @RequestMapping(value = "bpm/admin/getAllDictionariesAsLabelValue", method = RequestMethod.GET)
    public ModelAndView getAllDictionariesAsLabelValue(ModelMap model, @RequestParam("selectionType") String selectionType, 
    		@RequestParam("appendTo") String appendTo, @RequestParam("selectedValues") String selectedValues, @RequestParam("callAfter") String callAfter,
    		@RequestParam("hiddenElementId") String hiddenElementId, @RequestParam("rootNodeURL") String rootNodeURL,
    		@RequestParam("childNodeURL") String childNodeURL, HttpServletRequest request) {
    	try {
			Map<String, Object> context = new HashMap<String, Object>();
    		context.put("selectionType", selectionType);
    		context.put("selection", "dataDictionary");
    		if(selectedValues != null && !selectedValues.isEmpty()){
    			context.put("selectedValues", convertJsonArray(selectedValues));
    		}else{
    			context.put("selectedValues", "");
    		}
    		context.put("hiddenElementId", hiddenElementId);
    		context.put("needContextMenu", true);
    		context.put("needTreeCheckbox", true);
    		context.put("rootNodeURL", rootNodeURL);
    		context.put("childNodeURL", childNodeURL);
    		String script=VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "organizationTree.vm", context);
    		model.addAttribute("script", script);
    		model.addAttribute("appendTo",appendTo);
    		model.addAttribute("callAfter",callAfter);
    		model.addAttribute("selectionType",selectionType);
    		model.addAttribute("selectedValues",selectedValues);
    		model.addAttribute("hiddenElementId", hiddenElementId);
		}catch (EazyBpmException e){
			saveError(request, "Data Dictionary list cannot be retrived : "+e.getMessage());
            log.error(e.getMessage(),e);
		}
    	catch (Exception e) {
    		saveError(request, "Data Dictionary list cannot be retrived : " +e.getMessage());
			log.error("Error while getting all dictionaries as label value "+e.getMessage(),e);
		}
    	return new ModelAndView("admin/dataDictionaryTree", model);
    }
   
    /**
     * Convert data dictionary object to json array
     * @param users
     * @return
     * @throws JSONException
     */
    private JSONArray convertJsonArray(String selectedValues) throws JSONException{
    	JSONObject respObj = new JSONObject();
    	JSONArray responseMsg = new JSONArray();
	   	DataDictionary dictionary = dataDictionaryService.getDataDictionaryById(selectedValues);
		respObj.put("name", dictionary.getName());
		respObj.put("id", dictionary.getId());
		responseMsg.put(respObj);
   	 	return responseMsg;
    }
   
    /**
   	 * get child nodes of data dictionary
   	 * 
   	 * @param model
   	 * @param currentNode
   	 * @param errors
   	 * @param request
   	 * @param response
   	 * 
   	 * @return
   	 */
   	@RequestMapping(value="bpm/admin/getDataDictionaryNodes", method = RequestMethod.GET)
       public @ResponseBody String getDataDictionaryNodes(ModelMap model,@RequestParam("currentNode") String currentNode, @RequestParam("rootNode") String rootNode, @RequestParam("nodeLevel") int nodeLevel, 
       		User user, BindingResult errors, HttpServletRequest request, HttpServletResponse response){
   		Locale locale = request.getLocale();
   		JSONArray nodes = new JSONArray();
       	try {
       		List<LabelValue> dictionaries = dataDictionaryService.getAllDictionaryAsLabelValue();
       		nodes = CommonUtil.getNodesFromLabelValue(dictionaries);
       	} catch (BpmException e) {
   			log.warn(e.getMessage(),e);
   			saveError(request, getText("errors.userSelection.getNodes",locale));
   		} catch (Exception e) {
   			saveError(request, getText("errors.userSelection.getNodes",locale));
       		log.warn(e.getMessage(),e);
       	}
   		return nodes.toString();
       }
    
    /**
     * 
     * @param dataDictionary
     * @return
     * @throws EazyBpmException
     */
    @RequestMapping(value = "bpm/admin/saveDictionary")
    public ModelAndView saveDictionary(ModelMap model,@ModelAttribute("dataDictionary") DataDictionary dataDictionary,BindingResult errors,@RequestParam("hierarchyParentId") String hierarchyParentId,HttpServletRequest request) {
        Locale locale = request.getLocale();  
    	ResourceBundle appResourceBundle = ResourceBundle.getBundle(Constants.BUNDLE_KEY ,locale);
    	try {
    		User user = (User) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal();
    		dataDictionary.setCreatedBy(user.getUsername());
    		String mode = Constants.EDIT_MODE;
    		String successMessage = "success.dataDictionary.update";
    		if(dataDictionary.getId().equals(Constants.EMPTY_STRING)) {
    			dataDictionary.setId(null);
    			mode = Constants.CREATE_MODE;
    			successMessage = "success.dataDictionary.save";
    		}
    		model.addAttribute("mode",mode);
    		model.addAttribute("hierarchyParentId",(hierarchyParentId.contains(",")) ? hierarchyParentId.split(",")[0] : hierarchyParentId);
        //	model.addAttribute("isEnabled",dataDictionary.getIsEnabled());
    		boolean dictNameExits = dataDictionaryService.checkDictionaryNameExists(dataDictionary.getName().toLowerCase());
    		if(dataDictionary.getType().equals("static")){
    			boolean dictValueExits = dataDictionaryService.checkDictionaryValueExists(dataDictionary.getValue().toLowerCase());
    			if(dataDictionary.getId() == null && dictValueExits){
	            	throw new EazyBpmException("Dictionary Value already Exists");
	            }
    		}
    		boolean dictCodeExists = dataDictionaryService.checkDictionaryCodeExists(dataDictionary.getCode().toLowerCase());
    		if (validator != null) {
	        	validator.validate(dataDictionary, errors);
	            if (errors.hasErrors()) { 
	            	return new ModelAndView("admin/dataDictionaryForm", model);
	            }
	            if(dataDictionary.getId() == null && dictNameExits){
	            	throw new EazyBpmException("Dictionary Name already Exists");
	            }
	            if(dataDictionary.getId() == null && dictCodeExists){
	            	throw new EazyBpmException("Dictionary Code already Exists");
//	    			model.addAttribute("dictCodeExists", "true");
//	    			return new ModelAndView("admin/dataDictionaryForm", model);
	    		}
	            if(dataDictionary.getType().equalsIgnoreCase("dynamic")) {
	            	dataDictionaryService.checkQueryValidity(dataDictionary.getSqlString());
	            }
	        }
    		if(mode.equals(Constants.CREATE_MODE)){
    			dataDictionary.setOrderNo(dataDictionaryService.generateOrderNo(Constants.CREATE_MODE)); 			
    		}else{
    			dataDictionary.setOrderNo(dataDictionaryService.generateOrderNo(Constants.EDIT_MODE));
    		}
			
    		DataDictionary dictionary = dataDictionaryService.addNewDataDictionary(dataDictionary);
    		model.addAttribute("parentDictId", dictionary.getParentDictId());
    		model.addAttribute("isSaved","true");
    		//model.addAttribute("hierarchyParentId", hierarchyParentId);
           	saveMessage(request, getText(successMessage,locale));
           	log.info("Data Dictionary Name : "+dictionary.getName()+" "+getText("save.success",locale));
		} catch (EazyBpmException be) {
			saveError(request, be.getMessage());
			log.error("Error while adding a new dictionary", be);
			model.addAttribute("isSaved","false");
			return new ModelAndView("admin/dataDictionaryForm",model);
		} catch (Exception e) {
			String errorMessage = e.getMessage();
			String sqlNestedException = appResourceBundle.getString("sqlNestedException");
			if(errorMessage.contains(sqlNestedException))
				errorMessage = errorMessage.substring(0,errorMessage.indexOf(sqlNestedException));
			saveError(request,errorMessage);
			log.error("Error while adding a new dictionary", e);
			model.addAttribute("isSaved","false");
			return new ModelAndView("admin/dataDictionaryForm",model);
		}
    	return new ModelAndView("redirect:dataDictionary", model);
    }
    
    /**
     * 
     * @param currentNode
     * @return
     */
    @RequestMapping(value="bpm/admin/getDictionaryChildNodes", method = RequestMethod.GET)
    public @ResponseBody String getDataDictChildNodes(@RequestParam("currentNode") String currentNode){
    	JSONArray nodes = new JSONArray();
    	try {
    			List<LabelValue> dictionaries = dataDictionaryService.getDictionaryCodeByParentId(currentNode);
        		nodes = CommonUtil.getNodesFromLabelValue(dictionaries);
    	}catch (Exception e) {
    		log.error(e.getMessage(),e);
    	}
		return nodes.toString();
    } 
    
    @RequestMapping(value = "bpm/admin/getDataDictionaryGrid", method = RequestMethod.GET,produces="text/html;charset=UTF-8")
	public @ResponseBody String getDataDictionaryGrid(
			@RequestParam("parentDictId") String parentDictId)
			throws EazyBpmException {

    	String script = Constants.EMPTY_STRING;
    	String[] reqdColmns = {"dictionaryId","name","code","isEnabled","orderNo"};
    	try {
			List<DataDictionary> dataDictionaries = dataDictionaryService.getDataDictionaryByParentId(parentDictId);
			List<Map<String,Object>> dataList = CommonUtil.getMapListFromObjectListByFieldNames(dataDictionaries,reqdColmns,"");
        	script=GridUtil.generateScriptForDataDictionaryGrid(dataList, velocityEngine);
		} catch (Exception e) {
			log.error("Error while showing the data dictionary grid"+e.getMessage(),e);
		}
		return script;
	}
    
    /**
     * to get parent dictionary id 
     * @param dictionaryId
     * @return
     * @throws EazyBpmException
     */
    @RequestMapping(value = "bpm/admin/getParentDictIdbyDictId", method = RequestMethod.GET)
    public @ResponseBody String getParentDictIdByDictId(@RequestParam("dictionaryId") String dictionaryId) throws EazyBpmException {
    	String parentId = "";
    	try{
    		if(dictionaryId != null && dictionaryId != "" && dictionaryId.length()>0){
    			DataDictionary dataDictionary = dataDictionaryService.getDataDictionaryById(dictionaryId);
    			parentId = dataDictionary.getParentDictId();
    		} else {
    			return null;
    		}
    		
    	} catch(Exception e){
    		return null;
    	}
    	return parentId;
    }
    
    
    
	/**
	 * Returns the Data dictionary detail of the dictionary id that is passed as
	 * a parameter
	 * 
	 * @param id Dictionary Id
	 * @return
	 * 		
	 * @throws EazyBpmException
	 */
    @RequestMapping(value = "bpm/admin/dictionaryDetail", method = RequestMethod.GET)
    public ModelAndView getDataDictionaryById(ModelMap model,@RequestParam("dictionaryId") String dictionaryId,@RequestParam("hierarchyParentId") String hierarchyParentId) {
    	
    	try {
    		DataDictionary dictionary = dataDictionaryService.getDataDictionaryById(dictionaryId);
    		model.addAttribute("dataDictionary", dictionary);
    		model.addAttribute("mode",Constants.EDIT_MODE);
    		model.addAttribute("hierarchyParentId", hierarchyParentId);
        	//model.addAttribute("isEnabled",dictionary.getIsEnabled());
		} catch (Exception e) {
			log.error("Error while getting the dictionary details :"+e.getMessage(),e);
		}
    	
    	return new ModelAndView("admin/dataDictionaryForm", model);
    }
    
    /**
     * 
     * Gets all data dictionaries as label value
     * @return
     * 		
     * @throws Exception
     */
    @RequestMapping(value = "bpm/admin/getDictValueByParentId", method = RequestMethod.GET)
    public @ResponseBody List<Map<String,Object>> getDictionaryValueByParentId(ModelMap model,@RequestParam("parentId") String parentId) {
    	
    	List<Map<String,Object>> dictionaryList = new ArrayList<Map<String,Object>>();
    	List<LabelValue> dictionaries = null;
    	try {
    		DataDictionary dictionary = dataDictionaryService.getDataDictionaryById(parentId);
    		if(dictionary.getType().equalsIgnoreCase("dynamic")) {
    			dictionaries= dataDictionaryService.getSqlDictionaryData(dictionary.getSqlString());
    			
    		}else {
    			dictionaries = dataDictionaryService.getChildDictionaryValuesByParentId(parentId);
    		}
			
			for(LabelValue dictionaryInfo : dictionaries){
				
				Map<String, Object> dictDetail = new HashMap<String, Object>();
				dictDetail.put("dictValue", dictionaryInfo.getLabel());
				dictDetail.put("dictId", dictionaryInfo.getValue());
				dictionaryList.add(dictDetail);
			}
		} catch (Exception e) {
			log.error("Error while getting all dictionaries as label value"+e.getMessage(),e);
		}
        return dictionaryList;
    }
    
    /**
     * 
     * @param operation
     * @param dataDictionaryIds
     * @param request
     * @return
     */
    @RequestMapping(value = "bpm/admin/dataDictionaryGrid", method = RequestMethod.GET)
	public @ResponseBody List<Map<String,Object>> handleDataDictionary(
			@RequestParam("dataDictionaryIds") String dataDictionaryIds,@RequestParam("parentDictId") String parentDictId,
			HttpServletRequest request) {
    	List<Map<String,Object>> dataList = null;
		
		try{
			 List<String> dictIdList =CommonUtil.convertJsonToListStrings(dataDictionaryIds);
			 String dictionaryIds = CommonUtil.getCommaSeparatedString(dictIdList);
			 dataDictionaryService.disableDataDictionary(dictionaryIds);
			String[] reqdColmns = {"dictionaryId","name","code","isEnabled","orderNo"};
        	List<DataDictionary> dataDictionaries =  dataDictionaryService.getDataDictionaryByParentId(parentDictId);
        	dataList = CommonUtil.getMapListFromObjectListByFieldNames(dataDictionaries,reqdColmns,"");
			log.info("Retrived dataDictionaryGrid");
		}catch (Exception e) {
			log.error(e.getMessage(), e);
		}	
    	return	dataList;	
    }
    
    @RequestMapping(value = "bpm/admin/deleteDataDictionaryGrid", method = RequestMethod.GET)
	public @ResponseBody ModelAndView deleteDataDictionary(
			@RequestParam("dataDictionaryIds") String dataDictionaryIds,@RequestParam("parentDictId") String parentDictId,@RequestParam("hierarchyParentId") String hierarchyParentId,
			HttpServletRequest request) {
    	List<Map<String,Object>> dataList = null;
    	ModelMap model=new ModelMap();
    	Locale locale = request.getLocale();
    	model.addAttribute("parentDictId",parentDictId);
        model.addAttribute("hierarchyParentId",hierarchyParentId);
		try{
			 List<String> dictIdList =CommonUtil.convertJsonToListStrings(dataDictionaryIds);
			 String dictionaryIds = CommonUtil.getCommaSeparatedString(dictIdList);
			 dataDictionaryService.deleteDataDictionary(dictionaryIds);
			 saveMessage(request, getText("datadictionary.delete",locale));
			 log.info("DataDictionaryGrid Deleted Successfuly");
		}catch (Exception e) {
			log.error(e.getMessage(), e);
		}	
		return new ModelAndView("redirect:dataDictionary",model);	
    }
    
	/**
	 * Updates Order No of Data dictionary when the user click on move up and
	 * move down images in data dictionary grid.
	 * 
	 * @param dataDictionaryString
	 * @return response after updating the ordernos
	 */
    @RequestMapping(value = "bpm/admin/updateOrderNos", method = RequestMethod.POST)
    public @ResponseBody String updateDataDictionaryOrderNos(@RequestParam("dataDictionaryJson") String dataDictionaryString) {
    	JSONObject respObj = new JSONObject();
    	JSONArray responseMsg = new JSONArray();
    	try {
			List<Map<String,Object>> dictionaryList = CommonUtil.convertJsonToList(dataDictionaryString);
			boolean hasUpdated = dataDictionaryService.updateDictionaryOrderNos(dictionaryList);
			if(hasUpdated){
				respObj.put("isSuccess", "true");
				responseMsg.put(respObj);
				return responseMsg.toString();
			}
		} catch (Exception e) {
			log.error("Error while updating order nos for Datadictionary",e);
		}
    	return Constants.EMPTY_STRING;
    			
    }
    
    /**
	 * Returns the Data dictionary detail of the dictionary id that is passed as
	 * a parameter
	 * 
	 * @param id Dictionary Id
	 * @return
	 * 		
	 * @throws EazyBpmException
	 */
    @RequestMapping(value = "bpm/admin/getDataDictById", method = RequestMethod.GET)
    public @ResponseBody String getDataDictById(ModelMap model,@RequestParam("dictionaryId") String dictionaryId) {
    	JSONObject respObj = new JSONObject();
    	JSONArray responseMsg = new JSONArray();
    	try {
    		DataDictionary dictionary = dataDictionaryService.getDataDictionaryById(dictionaryId);
    		respObj.put("name", dictionary.getName());
    		respObj.put("id", dictionary.getId());
    		responseMsg.put(respObj);
    		return responseMsg.toString();
		} catch (Exception e) {
			log.error("Error while getting the dictionary details by id : "+dictionaryId+"  : "+e.getMessage(),e);
		}
    	return Constants.EMPTY_STRING;
    }
    
    @Autowired
	public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }

    @Autowired
	public void setDataDictionaryService(DataDictionaryService dataDictionaryService) {
		this.dataDictionaryService = dataDictionaryService;
	}
}
