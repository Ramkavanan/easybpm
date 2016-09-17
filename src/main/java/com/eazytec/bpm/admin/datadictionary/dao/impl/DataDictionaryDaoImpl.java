package com.eazytec.bpm.admin.datadictionary.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.eazytec.bpm.admin.datadictionary.dao.DataDictionaryDao;
import com.eazytec.dao.hibernate.GenericDaoHibernate;
import com.eazytec.exceptions.BpmException;
import com.eazytec.exceptions.EazyBpmException;
import com.eazytec.model.DataDictionary;
import com.eazytec.model.LabelValue;

/**
 * 
 * @author Karthick
 *
 */
@Repository("dataDictionaryDao")
public class DataDictionaryDaoImpl extends GenericDaoHibernate<DataDictionary,String> implements DataDictionaryDao{
	
	
	public DataDictionaryDaoImpl() {
		super(DataDictionary.class);
	}

	/**
	 * {@inheritDoc}
	 */
	public DataDictionary addNewDictionary(DataDictionary dictionary)
			throws EazyBpmException {
		if (log.isDebugEnabled()) {
			//log.debug("DataDictionary id: " + dictionary.getId());
		}
		getSession().saveOrUpdate(dictionary);
		// necessary to throw a DataIntegrityViolation and catch it in
		// GroupManager
		getSession().flush();
		return dictionary;
	}

	/**
	 * {@inheritDoc}
	 */
	public void deleteDictionary() throws EazyBpmException {

	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<DataDictionary> getAllDataDictionaries() throws EazyBpmException {
		
		Query qry = getSession().createQuery(
				"from DataDictionary dataDictionary ");
		return qry.list();
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	public DataDictionary getDataDictionaryById(String id) throws EazyBpmException {

		Query qry = getSession().createQuery(
				"from DataDictionary dataDictionary where dataDictionary.id = '"
						+ id + "'");
		List<DataDictionary> dictionaries = qry.list();

		return (dictionaries != null && !dictionaries.isEmpty()) ? dictionaries
				.get(0) : new DataDictionary();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<LabelValue> getDictionaryLabelValueByParentId(String parentId)
			throws EazyBpmException {

		Query qry = getSession()
				.createQuery(
						"select new com.eazytec.model.LabelValue(dataDictionary.name as name,dataDictionary.id as id) from DataDictionary dataDictionary where dataDictionary.parentDictId = '"
								+ parentId + "'");
		List<LabelValue> dictionaries = qry.list();

		return dictionaries;

	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<LabelValue> getDictionaryValueByParentId(String parentId)
			throws EazyBpmException {

		Query qry = getSession()
				.createQuery(
						"select new com.eazytec.model.LabelValue(dataDictionary.value as value,dataDictionary.id as id) from DataDictionary dataDictionary where dataDictionary.parentDictId = '"
								+ parentId + "' and dataDictionary.isEnabled =true");
		List<LabelValue> dictionaries = qry.list();

		return dictionaries;

	}
	/**
	 *{@inheritDoc} 
	 */
	public List<DataDictionary> getDataDictionaryByParentId(String parentId) throws EazyBpmException {

		Query qry = getSession()
				.createQuery(
						"from DataDictionary dataDictionary where dataDictionary.parentDictId = '"
								+ parentId + "' order by dataDictionary.orderNo asc");
		List<DataDictionary> dictionaries = qry.list();

		return dictionaries;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<LabelValue> getAllDictionaryAsLabelValue()
			throws EazyBpmException {

		Query qry = getSession()
				.createQuery(
						"select new com.eazytec.model.LabelValue(dataDictionary.name as name,dataDictionary.id as id) from DataDictionary dataDictionary where (dataDictionary.parentDictId = 'UserDefined' or dataDictionary.parentDictId = 'SystemDefined') and dataDictionary.isEnabled =true");
		List<LabelValue> dictionaries = qry.list();

		return dictionaries;

	}
	/**
	 * {@inheritDoc}
	 */
	public boolean checkDictionaryNameExists(String dictName) throws EazyBpmException {
		
		Query qry = getSession()
				.createQuery(
						"select dataDictionary from DataDictionary dataDictionary where lower(dataDictionary.name) = '"+dictName+"' ");
		List<DataDictionary> dictionaries = qry.list();

		return (dictionaries != null && !dictionaries.isEmpty()) ? true:false;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean checkDictionaryValueExists(String dictValue) throws EazyBpmException {
		
		Query qry = getSession()
				.createQuery(
						"select dataDictionary from DataDictionary dataDictionary where lower(dataDictionary.value) = '"+dictValue+"' ");
		List<DataDictionary> dictionaries = qry.list();

		return (dictionaries != null && !dictionaries.isEmpty()) ? true:false;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean checkDictionaryCodeExists(String dictCode) throws EazyBpmException {
		
		Query qry = getSession()
				.createQuery(
						"select dataDictionary from DataDictionary dataDictionary where lower(dataDictionary.code) = '"+dictCode+"' ");
		List<DataDictionary> dictionaries = qry.list();

		return (dictionaries != null && !dictionaries.isEmpty()) ? true:false;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean deleteDataDictionary(String idList) throws EazyBpmException {

		Query qry = getSession().createQuery(
				"delete from DataDictionary dataDictionary where dataDictionary.id in ("
						+ idList + ")  or dataDictionary.parentDictId in ("+ idList+") ");
		int result = qry.executeUpdate();
		

		return result != 0  ? true : false;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean disableDataDictionary(String idList) throws EazyBpmException {

	
		Query qry = getSession()
				.createQuery(
						"update DataDictionary dataDictionary set dataDictionary.isEnabled = false where dataDictionary.id in ("
								+ idList + ") ");
		int result = qry.executeUpdate();
		

		return result != 0  ? true : false;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean updateDictionaryOrderNos(String dictionaryId,int orderNo) throws EazyBpmException {
		
		Query qry = getSession()
				.createQuery(
						"update DataDictionary dataDictionary set dataDictionary.orderNo = "+orderNo+" where dataDictionary.id = '"
								+ dictionaryId + "' ");
		int result = qry.executeUpdate();
		
		return result != 0  ? true : false;
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<?> getDynamicDictionaryValues(String query) throws EazyBpmException {
		
		List<?> list = null;
			Query qry = getSession()
					.createSQLQuery(query);
	    list = qry.list();
		return list;
		
	}
}
