package com.eazytec.bpm.runtime.form.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.jws.WebService;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.task.TaskDefinition;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.eazytec.bpm.common.util.I18nUtil;
import com.eazytec.bpm.runtime.form.service.FormService;
import com.eazytec.bpm.runtime.task.service.OperatingFunctionService;
import com.eazytec.exceptions.BpmException;
import com.eazytec.exceptions.DataSourceException;
import com.eazytec.service.UserManager;
import com.eazytec.util.DateUtil;

/**
 * <p>Form service at runtime, implements {@link FormService}</p>
 * @author madan
 *
 */
@Service("rtFormService")
@WebService(serviceName = "ProcessService", endpointInterface = "com.eazytec.bpm.runtime.form.service")
public class FormServiceImpl implements FormService{
	
	private org.activiti.engine.FormService formService;
	private OperatingFunctionService operatingFunctionService;
	private com.eazytec.bpm.runtime.process.service.ProcessService rtProcessService;

	@Autowired
	public void setRtProcessService(
          com.eazytec.bpm.runtime.process.service.ProcessService rtProcessService) {
		this.rtProcessService = rtProcessService;
	}
	
	@Autowired
	public void setPperatingFunctionService(OperatingFunctionService operatingFunctionService) {
		this.operatingFunctionService = operatingFunctionService;
	}

	/**
	 * {@inheritDoc submitStartForm}
	 */
	public ProcessInstance submitStartForm(String processDefinitionId, Map<String, String>formValues)throws DataSourceException{
		return formService.submitStartFormData(processDefinitionId, formValues);		
	}
	
	public ProcessInstance submitStartForm(String processDefinitionId, Map<String, String>formValues, Map<String, String[]>subFormValues, Map<String, byte[]>files, Map<String, String>filePaths,boolean isDraft)throws DataSourceException{
		try{
			return formService.submitStartFormData(processDefinitionId, formValues, subFormValues, files, filePaths,isDraft);		
		}catch(ActivitiException e){
			throw new BpmException(e.getMessage(),e);
		}		
	}
	
	public ProcessInstance updateStartForm(String processDefinitionId, String processInstanceId, Map<String, String>formValues, Map<String, String[]>subFormValues, Map<String, byte[]>files, Map<String, String>filePaths)throws DataSourceException{
		try{
			
			return formService.updateStartFormData(processDefinitionId, processInstanceId, formValues, subFormValues, files, filePaths);		
		}catch(ActivitiException e){
			throw new BpmException(e.getMessage(),e);
		}		
	}
	
	/**
	 * {@inheritDoc submitTaskForm}
	 */
	public void submitTaskForm(String taskId, Map<String, String>formValues)throws DataSourceException{
		try{
			formService.submitTaskFormData(taskId, formValues);
		}catch(ActivitiException e){
			  throw new BpmException(I18nUtil.getMessageProperty("error.formSubmitError")+" "+e.getMessage());			
		}
				
	}
	
	public Object submitTaskForm(String taskId, Map<String, String>formValues, Map<String, String[]>subFormValues, Map<String, byte[]>files, Map<String, String>filePaths, boolean isStartNodeTask, String proInsId)throws DataSourceException{
		try{
			return formService.submitTaskFormData(taskId, formValues, subFormValues, files, filePaths, isStartNodeTask, proInsId);
		}catch(ActivitiException e){
			  throw new BpmException(I18nUtil.getMessageProperty("error.formSubmitError")+" "+e.getMessage());			
		}
				
	}
	
	public void saveTaskForm(String taskId, Map<String, String>formValues, Map<String, String[]>subFormValues, Map<String, byte[]>files, Map<String, String>filePaths,String loggedInUserId, String proInsId)throws DataSourceException{
		try{
			formService.saveTaskFormData(taskId, formValues, subFormValues, files, filePaths,loggedInUserId, proInsId);
		}catch(ActivitiException e){
			  throw new BpmException(I18nUtil.getMessageProperty("error.formSubmitError")+" "+e.getMessage());			
		}
				
	}
	
	public Object updateTaskForm(String taskId, Map<String, String>formValues, Map<String, String[]>subFormValues, Map<String, byte[]>files, Map<String, String>filePaths, boolean isSignOff)throws DataSourceException{
		try{
			return formService.updateTaskFormData(taskId, formValues, subFormValues, files, filePaths, isSignOff);
		}catch(ActivitiException e){
			  throw new BpmException(I18nUtil.getMessageProperty("error.formSubmitError")+" "+e.getMessage());			
		}
				
	}
	
	public JSONArray getFormFieldTraceData(String processInstanceId,String formId) throws BpmException{
		try{
			List<Map<String,Object>>  fieldTraceDataList = formService.getFormFieldTraceData(processInstanceId, formId);
			JSONArray traceJsonArray = new JSONArray();
			JSONObject traceJson = null;
			for(Map<String,Object> fieldTraceData:fieldTraceDataList){
				traceJson = new JSONObject();
				traceJson.put("modifiedTime",  fieldTraceData.get("modifiedTime").toString());
				traceJson.put("modifiedBy", fieldTraceData.get("modifiedBy"));
				traceJson.put("fieldName", fieldTraceData.get("fieldName"));
				traceJson.put("chineseName", fieldTraceData.get("chineseName"));
				traceJson.put("oldValue", fieldTraceData.get("oldValue"));
				traceJson.put("newValue", fieldTraceData.get("newValue"));
				traceJsonArray.put(traceJson);
			}
			return traceJsonArray;
		}catch(ActivitiException e){
			  throw new BpmException(I18nUtil.getMessageProperty("error.gettingAuditDetails")+" "+e.getMessage());			
		}catch(DataSourceException e){
			  throw new BpmException(I18nUtil.getMessageProperty("error.gettingAuditDetails")+" "+e.getMessage());			
		}catch (JSONException e) {
			  throw new BpmException(I18nUtil.getMessageProperty("error.gettingJsonAuditDetails")+" "+formId+" "+e.getMessage());			
		}
	}
	
	@Autowired
	public void setFormService(org.activiti.engine.FormService formService) {
		this.formService = formService;
	}
	

}
