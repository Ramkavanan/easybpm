package com.eazytec.bpm.metadata.process.dao.impl;


import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.eazytec.bpm.metadata.process.dao.ProcessDao;
import com.eazytec.dao.hibernate.GenericDaoHibernate;
import com.eazytec.exceptions.BpmException;
import com.eazytec.exceptions.EazyBpmException;
import com.eazytec.model.Classification;
import com.eazytec.model.LabelValue;
import com.eazytec.model.MetaForm;
import com.eazytec.model.Module;
import com.eazytec.model.Process;

@Repository("processDao")
public class ProcessDaoImpl extends GenericDaoHibernate<ProcessDao, String> implements ProcessDao {	
	
	public ProcessDaoImpl() {
        super(ProcessDao.class);
    }

	/* {@inheritDoc}
     */
    public Classification saveClassification(Classification classification) throws BpmException {
        if (log.isDebugEnabled()) {
            log.debug("classification id: " + classification.getId());
        }
        getSessionFactory().getCurrentSession().saveOrUpdate(classification);
        // necessary to throw a DataIntegrityViolation and catch 
        getSession().flush();
        return classification;
    }
    
    
    /* {@inheritDoc}
     */
   public boolean isClassificationExist(String classificationName) throws BpmException{
    	if (log.isDebugEnabled()) {
            log.debug("classification Name: " + classificationName);
        }
    	
    	 Criteria criteria = (Criteria) getSession().createCriteria(Classification.class);
    	 Criterion id = Restrictions.eq("id", classificationName).ignoreCase();
         Criterion name = Restrictions.eq("name",classificationName).ignoreCase();
         LogicalExpression expression = Restrictions.or(id, name);
         criteria.add(expression);
         List<Classification> classificationList = criteria.list();
         if (!classificationList.isEmpty()) {
             return true;
         }
         return false;
    	
    }
   
   /**
    * {@inheritDoc}
    */
   @SuppressWarnings("unchecked")
   public List<Process> getAllProcess(String whereParams) throws EazyBpmException {
	        Query query = getSession().createQuery("from Process AS process where process.isActiveVersion=1 "+whereParams);
	        return query.list();
   }
   
   /**
    * {@inheritDoc}
    */
   public List<Object[]> getNewsItemForHomePage() throws EazyBpmException {
	   Query query =getSession().createSQLQuery("SELECT NEWS.ID,NEWS.TITLE, NEWS.CREATEDTIME, NEWS.CK FROM NEWS AS NEWS ORDER BY NEWS.CREATEDTIME DESC LIMIT 3");
	   List<Object[]> results = (List<Object[]>) query.list();
       return results;
   }

   /**
	  * {@inheritDoc}
	  */
	 public List<Process> getProcessByIds(List<String> ids)throws EazyBpmException {
	    	log.info("Getting Process object of : "+ids);
	    	List<Process> processList = getSession().createQuery("from Process as process where process.id in (:list)").setParameterList("list", ids).list();
	        if (processList.isEmpty()) {
	            return null;
	        } else {
	            return processList;
	        }
	    }
	 /**
	  * {@inheritDoc}
	  */
	 public List<LabelValue> getAllProcessAsLabelValue() throws BpmException {
	    	List<LabelValue> processList = (List<LabelValue>) getSession()
					.createQuery(
							"select new com.eazytec.model.LabelValue(process.name as name,process.id as id) from Process as process where process.isActiveVersion=1")
					.list();

			return processList;
	    }
	 /**
	  * {@inheritDoc}
	  */ 
	  public List<LabelValue> searchProcessNames(String processName)throws BpmException{
		  List<LabelValue> processList = (List<LabelValue>) getSession()
					.createQuery(
							"select new com.eazytec.model.LabelValue(process.name as name,process.id as id) from Process as process where process.isActiveVersion=1 and process.name like '"+processName+"%'")
					.list();

			return processList;
	  }
	 
	 /**
	  * {@inheritDoc}
	  */
	public List<LabelValue> getAllProcessBySystemDefined(boolean isSystemDefined) throws Exception{
		List<LabelValue> processList = (List<LabelValue>) getSession().createQuery("select new com.eazytec.model.LabelValue(process.name as name,process.id as id) from Process as process where process.isActiveVersion = 1 AND process.isSystemDefined="+isSystemDefined).list();
		if(processList != null && !processList.isEmpty()){
			 return processList;
		} else {
			return null;
		}
	}
	
	public String getClassificationId(String deploymentId) {
		
		String classificationId = (String) getSession().createQuery("select process.classificationId as classificationId from Process as process where process.deploymentId='"+deploymentId+"'").list().get(0);
			if(classificationId != null) {
				return classificationId;
			}else {
				classificationId = "";
			}
			return classificationId;
	}
	/**
	 * {@inheritDoc}
	 */
	public List<Process> getAllProcess()throws EazyBpmException {
		List<Process> processList = (List<Process>) getSession()
				.createQuery(" from Process as process where process.isActiveVersion=1")
				.list();
        if (processList.isEmpty()) {
            return null;
        } else {
            return processList;
        }
    }
	 
}
