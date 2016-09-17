package com.eazytec.bpm.admin.datadictionary.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.eclipse.jetty.util.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eazytec.Constants;
import com.eazytec.bpm.admin.datadictionary.dao.DataDictionaryDao;
import com.eazytec.bpm.admin.datadictionary.service.DataDictionaryService;
import com.eazytec.bpm.common.LabelValueBean;
import com.eazytec.bpm.common.QueryParser;
import com.eazytec.common.util.DataDictionaryComparator;
import com.eazytec.exceptions.BpmException;
import com.eazytec.exceptions.EazyBpmException;
import com.eazytec.model.DataDictionary;
import com.eazytec.model.LabelValue;
import com.eazytec.bpm.common.util.StringUtil;

/**
 * 
 * @author Karthick
 * 
 */
@Service("dataDictionaryService")
@WebService(serviceName = "DataDictionaryService", endpointInterface = "com.eazytec.bpm.admin.datadictionary.service")
public class DataDictionaryServiceImpl implements DataDictionaryService {
	
	private DataDictionaryDao dataDictionaryDao;

	/**
	 * {@inheritDoc}
	 */
	public DataDictionary addNewDataDictionary(DataDictionary dictionary)
			throws EazyBpmException {
		DataDictionary dataDictionary = dataDictionaryDao.addNewDictionary(dictionary);
		return dataDictionary;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean deleteDataDictionary(String idList) throws EazyBpmException {
		
		return dataDictionaryDao.deleteDataDictionary(idList);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean disableDataDictionary(String idList) throws EazyBpmException {
		
		return dataDictionaryDao.disableDataDictionary(idList);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<DataDictionary> getAllDataDictionaries() throws EazyBpmException {
		
		return dataDictionaryDao.getAllDataDictionaries();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<LabelValue> getDictionaryCodeByParentId(String parentId) throws EazyBpmException {
		
		return dataDictionaryDao.getDictionaryLabelValueByParentId(parentId);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<DataDictionary> getDataDictionaryByParentId(String parentId) throws EazyBpmException {
		
		return dataDictionaryDao.getDataDictionaryByParentId(parentId);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public DataDictionary getDataDictionaryById(String id) throws EazyBpmException {
		
		return dataDictionaryDao.getDataDictionaryById(id);
	}
	/**
	 * {@inheritDoc}
	 */
	public boolean checkDictionaryNameExists(String dictName) throws EazyBpmException {
		
		return dataDictionaryDao.checkDictionaryNameExists(dictName);
	}
	/**
	 * {@inheritDoc}
	 */
	public boolean checkDictionaryValueExists(String dictValue) throws EazyBpmException {
		
		return dataDictionaryDao.checkDictionaryValueExists(dictValue);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean checkDictionaryCodeExists(String dictCode) throws EazyBpmException {
		
		return dataDictionaryDao.checkDictionaryCodeExists(dictCode);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public int generateOrderNo(String parentDictId) throws EazyBpmException {
		
		List<DataDictionary> dictionaryList = dataDictionaryDao.getDataDictionaryByParentId(parentDictId);
		if(dictionaryList != null && !dictionaryList.isEmpty()) {
			Collections.sort(dictionaryList, new DataDictionaryComparator());
			int maxOrderNo = dictionaryList.get(dictionaryList.size() - 1).getOrderNo();
			return maxOrderNo + 1;
		}else {
			return Constants.DEFAULT_ORDER_NO;
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean updateDictionaryOrderNos(
			List<Map<String, Object>> dictionaryList) throws EazyBpmException {
		
		boolean hasUpdated = false;
 		for (Map<String, Object> dictionary : dictionaryList) {
 			hasUpdated = dataDictionaryDao.updateDictionaryOrderNos(
					(String) dictionary.get("dictionaryId"),
					Integer.parseInt((String)dictionary.get("orderNo")));
		}

		return hasUpdated;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean checkQueryValidity(String query) throws Exception,EazyBpmException {
		boolean isValidSqlQuery = false;
		QueryParser parser = new QueryParser(query);
		List<LabelValueBean> selectParams = parser.getAllSelectColumns();
		if(!selectParams.isEmpty()){
			if(selectParams.size() != 2 ) {
				throw new EazyBpmException("select params should be two"); 
			}else {
				for(LabelValueBean selectParam : selectParams){
					if(selectParam.getLabel().equalsIgnoreCase("*")){
						throw new EazyBpmException("Select * is not allowed"); 
					}
				}
				List<?> values = dataDictionaryDao.getDynamicDictionaryValues(query);
				/*if(values != null && !values.isEmpty()) {
					isValidSqlQuery = true;
					
				}else {
					throw new EazyBpmException("Not a valid sql query, check its table name and column names");
				}*/
			}
		}else {
			throw new EazyBpmException("Not a valid sql query, check its params and syntax");
		}
		return isValidSqlQuery;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<LabelValue> getSqlDictionaryData(String query) throws EazyBpmException {
		List<?> dataList = dataDictionaryDao.getDynamicDictionaryValues(query);
		List<LabelValue> dictionaryLabelValue = new ArrayList<LabelValue>();
		LabelValue labelValue = null;
		for(int i=0; i< dataList.size(); i++){
			if(dataList.get(i) != null){
	        	if(dataList.get(i) instanceof String){
	        		String value = dataList.get(i).toString();
	    			labelValue = new LabelValue(value,value);
	    			dictionaryLabelValue.add(labelValue);
	        	}else {
	        		Object[] dataValues = (Object[])dataList.get(i);
	        		if(dataValues[1] != null && dataValues[0] != null){
	        			labelValue = new LabelValue(dataValues[1].toString(),dataValues[0].toString());
	        			dictionaryLabelValue.add(labelValue);
	        		}
	    			
	        	}
			}
        }
		return dictionaryLabelValue;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<LabelValue> getAllDictionaryAsLabelValue() throws EazyBpmException {
		return dataDictionaryDao.getAllDictionaryAsLabelValue();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<LabelValue> getChildDictionaryValuesByParentId(String parentId) throws EazyBpmException {
		List<LabelValue> dictionaryLabelValue = new ArrayList<LabelValue>();
		List<DataDictionary> childDictionaries = dataDictionaryDao.getDataDictionaryByParentId(parentId);
		LabelValue labelValue = null;
		for(DataDictionary childDictionary : childDictionaries){
			if(childDictionary.getType().equalsIgnoreCase("static")){
				labelValue = new LabelValue(childDictionary.getName(),childDictionary.getCode());
				dictionaryLabelValue.add(labelValue);
			}else if(childDictionary.getType().equalsIgnoreCase("dynamic")){
				List<LabelValue> dynamicDictionaryValues = getSqlDictionaryData(childDictionary.getSqlString());
				dictionaryLabelValue.addAll(dynamicDictionaryValues);
			}
		}
		return dictionaryLabelValue;
	}
	
	@Autowired
	public void setDataDictionaryDao(DataDictionaryDao dataDictionaryDao) {
		this.dataDictionaryDao = dataDictionaryDao;
	}
	

	/**
     *{@inheritDoc} 
     */
    public List<Map<String,Object>> getDictionaryValueByParentId(String parentId) {
    	
    	List<Map<String,Object>> dictionaryList = new ArrayList<Map<String,Object>>();
    	List<LabelValue> dictionaries = null;
    		DataDictionary dictionary = getDataDictionaryById(parentId);
    		if(dictionary != null){
        		if(dictionary.getType().equalsIgnoreCase("dynamic")) {
        			dictionaries= getSqlDictionaryData(dictionary.getSqlString());
        			
        		}else {
        			dictionaries = getChildDictionaryValuesByParentId(parentId);
        		}
    		}
			for(LabelValue dictionaryInfo : dictionaries){
				
				Map<String, Object> dictDetail = new HashMap<String, Object>();
				dictDetail.put("dictValue", dictionaryInfo.getLabel());
				dictDetail.put("dictId", dictionaryInfo.getValue());
				dictionaryList.add(dictDetail);
			}
        return dictionaryList;
    }

}
