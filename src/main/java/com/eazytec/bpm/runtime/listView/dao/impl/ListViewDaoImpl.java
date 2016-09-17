package com.eazytec.bpm.runtime.listView.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Table;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.eazytec.bpm.runtime.listView.dao.ListViewDao;
import com.eazytec.dao.hibernate.GenericDaoHibernate;
import com.eazytec.exceptions.EazyBpmException;
import com.eazytec.model.LabelValue;
import com.eazytec.model.ListView;
import com.eazytec.model.ListViewButtons;
import com.eazytec.model.ListViewColumns;
import com.eazytec.model.ListViewGroupSetting;
import com.eazytec.model.MetaForm;
import com.eazytec.model.MetaTable;
import com.eazytec.model.Module;
import com.eazytec.model.Role;

@Repository("listViewDao")
@Table(name = "ListView")
public class ListViewDaoImpl extends GenericDaoHibernate<ListView, String>  implements ListViewDao {
	 private static final long serialVersionUID = 3690197650654049848L;
	 
	 	/**
		 * Constructor to create a Generics-based version using Group as the entity
		 */
		public ListViewDaoImpl() {
			super(ListView.class);
		}
	
		/**
		 * {@inheritDoc getListViewDetails}
		 */
		public ListView getListViewDetails(String listViewName)throws EazyBpmException {
			// TODO Auto-generated method stub
			Query query = getSession().createQuery("SELECT listView FROM ListView AS listView where listView.viewName='"+listViewName+"' and listView.active=true");
			ListView listView=null;
			if(!query.list().isEmpty()){
				listView=(ListView) query.list().get(0);
			}else{
				listView=null;
			}
			
			return listView;
		}
		
		/**
		 * {@inheritDoc getListViewDataMap}
		 */
		public List<String> getListViewDataMap(String listViewQuery)throws EazyBpmException{
			List<String> results = null;

	         //Query using HQL and print results
	         Query query =getSession().createSQLQuery(listViewQuery);
	         if(!listViewQuery.contains("SELECT")){
	        	 query.executeUpdate();
	         }else{
	        	 results =(List<String>) query.list();
	         }
	         return results;
		}
		
		/**
		 * {@inheritDoc getColumnsPropertyByListViewId}
		 */
		public List<ListViewColumns> getColumnsPropertyByListViewId(String listViewId)throws EazyBpmException{
	    	List<ListViewColumns> columnsProperty = getSession().createQuery("from ListViewColumns as listViewColumns where listViewColumns.listView.id in (:id) ORDER BY listViewColumns.orderBy asc").setParameter("id", listViewId).list();
	        if (columnsProperty.isEmpty()) {
	            return null;
	        } else {
	            return columnsProperty;
	        }
		}
		
		/**
		 * {@inheritDoc getColumnsPropertyByListViewId}
		 */
		public List<ListViewGroupSetting> getGroupSettingPropertyByListViewId(String listViewId)throws EazyBpmException{
	    	List<ListViewGroupSetting> groupSettingProperty = getSession().createQuery("from ListViewGroupSetting as listViewGroupSetting where listViewGroupSetting.listView.id in (:id)").setParameter("id", listViewId).list();
	        if (groupSettingProperty.isEmpty()) {
	            return null;
	        } else {
	            return groupSettingProperty;
	        }
		}
		
		/**
		 * {@inheritDoc saveListView}
		 */
		public ListView saveListView(ListView listView)throws EazyBpmException{
			/*if(listView.getId() != null && !(listView.getId().isEmpty())){
				getSession().update(listView);
			}else{
				listView.setId("");
				getSession().save(listView);
			}
			getSession().flush();
			return listView;*/
			 if (log.isDebugEnabled()) {
		            log.debug("List View id: " + listView.getId());
		        }
		        getSession().save(listView);
		        getSession().flush();
		        return listView;
		}
		
		/**
		 * {@inheritDoc getTableDetails}
		 */
		public ListView getListViewById(String listViewId)throws EazyBpmException{
			List<ListView> listView = getSession().createCriteria(ListView.class).add(Restrictions.eq("id", listViewId)).list();
			if (!listView.isEmpty()) {
				return listView.get(0);
			}else{
				return null;
			}
		}
		
		/**
		 * {@inheritDoc saveListViewColumns}
		 */
		public ListViewColumns saveListViewColumns(ListViewColumns listViewDetails)throws EazyBpmException{
			/*if(listViewDetails.getId() != null && !(listViewDetails.getId().isEmpty())){
				getSession().merge(listViewDetails);
			}else{
				listViewDetails.setId("");
				getSession().save(listViewDetails);
			}
			getSession().flush();
			return listViewDetails;*/
			if (log.isDebugEnabled()) {
	            log.debug("List views columns id: " + listViewDetails.getId());
	        }
	        getSession().save(listViewDetails);
	        getSession().flush();
	        return listViewDetails;
		}
		
		/**
		 * {@inheritDoc saveListViewColumns}
		 */
		public ListViewGroupSetting saveListViewGroupSetting(ListViewGroupSetting listViewGroupSettingDetails)throws EazyBpmException{
			/*if(listViewGroupSettingDetails.getId() != null && !(listViewGroupSettingDetails.getId().isEmpty())){
				getSession().merge(listViewGroupSettingDetails);
			}else{
				listViewGroupSettingDetails.setId("");
				getSession().save(listViewGroupSettingDetails);
			}
			getSession().flush();
			return listViewGroupSettingDetails;*/
			if (log.isDebugEnabled()) {
	            log.debug("List views columns id: " + listViewGroupSettingDetails.getId());
	        }
	        getSession().save(listViewGroupSettingDetails);
	        getSession().flush();
	        return listViewGroupSettingDetails;
		}
		/**
		 * {@inheritDoc deleteListViewByIds}
		 */
		public void deleteListViewByIds(String deleteListViewColumnQuery)throws EazyBpmException{
			Query query = getSession().createQuery(deleteListViewColumnQuery);
			int result = query.executeUpdate();
			
			if(result==0){
				throw new EazyBpmException("Failed in deleting ListViewColumns ");
			}
		}
		
		/**
		 * {@inheritDoc checkListViewName}
		 */
		public boolean checkListViewName(String listViewName)throws EazyBpmException{
			List<ListView> listView = getSession().createCriteria(ListView.class).add(Restrictions.eq("viewName", listViewName)).list();
			if (listView.isEmpty()) {
				return false;
			}else{
				return true;
			}
		}

		/**
		 * {@inheritDoc saveListViewButtons}
		 */
		public ListViewButtons saveListViewButtons(ListViewButtons listViewDetails)throws EazyBpmException{
			
			if (log.isDebugEnabled()) {
	            log.debug("List views button id: " + listViewDetails.getId());
	        }
	        getSession().save(listViewDetails);
	        getSession().flush();
	        return listViewDetails;
	        /*if(listViewDetails.getId() != null && !(listViewDetails.getId().isEmpty())){
				getSession().merge(listViewDetails);
			}else{
				listViewDetails.setId("");
				getSession().save(listViewDetails);
			}

			return listViewDetails;*/
		}
		
		/**
		 * {@inheritDoc getButtonPropertyByListViewId}
		 */
		public List<ListViewButtons> getButtonPropertyByListViewId(String listViewId)throws EazyBpmException{
	    	List<ListViewButtons> columnsProperty = getSession().createQuery("from ListViewButtons as listViewButtons where listViewButtons.listView.id in (:id)").setParameter("id", listViewId).list();
	        if (columnsProperty.isEmpty()) {
	            return null;
	        } else {
	            return columnsProperty;
	        }
		}
		
		
		/**
		 * {@inheritDoc softDeleteTableData}
		 */
		public int softDeleteTableDataAndRestore(String tableIds, String tableName, String columnName, int status, String deletedUser, String deletedTime)throws EazyBpmException{
			 Query query;
			if(deletedUser == null && deletedTime == null ) {
				query = getSession().createSQLQuery("UPDATE "+tableName+" set is_delete = "+status+" WHERE "+columnName+" in "+tableIds);
			} else {
				 query = getSession().createSQLQuery("UPDATE "+tableName+" set is_delete = "+status+" , deleted_user='"+deletedUser+"' , deleted_time ='"+deletedTime+"' WHERE "+columnName+" in "+tableIds);
			}
			log.info("tableIds "+String.valueOf(tableIds)+"       status"+status+"   columnName"+columnName+"  tableName  "+tableName);
		     return query.executeUpdate();
		}
		
		/**
		 * {@inheritDoc deleteTableData}
		 */
		public int deleteTableData(String tableIds, String tableName, String columnName)throws EazyBpmException{
			 Query query = getSession().createQuery("Delete "+tableName+" WHERE "+columnName+" in "+tableIds);
		     return query.executeUpdate();
		}
		
		/**
		 * {@inheritDoc deleteListViewDetailsById}
		 */
		public void deleteListViewDetailsById(ListView listViewObj)throws EazyBpmException{
			getSession().delete(listViewObj);
			getSession().flush();
		}
		
		/**
		 * {@inheritDoc getListViewByIds}
		 */
		public List<ListView> getListViewByIds(List<String> ids)throws EazyBpmException { 
	    	log.info("Getting ListView object of : "+ids);
	    	List<ListView> listViews = getSession().createQuery("from ListView as listView where listView.id in (:list)").setParameterList("list", ids).list();
	        if (listViews.isEmpty()) {
	            return null;
	        } else {
	            return listViews;
	        }
	    }
		
		/**
		 * {@inheritDoc getAllListView}
		 */
		public List<LabelValue> getAllListView()throws EazyBpmException { 
	    	List<LabelValue> listViews = getSession().createQuery(
					"select new com.eazytec.model.LabelValue(concat(listView.viewName ,' (', listView.version ,')') as viewName,listView.id as id) from ListView as listView where listView.active=true")
			.list();
	        if (listViews.isEmpty()) {
	            return null;
	        } else {
	            return listViews;
	        }
	    }
		
		public List<LabelValue> getAllListViewWithoutVersion()throws EazyBpmException { 
	    	List<LabelValue> listViews = getSession().createQuery(
					"select new com.eazytec.model.LabelValue(listView.viewName as viewName,listView.id as id) from ListView as listView where listView.active=true order by listView.viewName ")
			.list();
	        if (listViews.isEmpty()) {
	            return null;
	        } else {
	            return listViews;
	        }
	    }
		
		/**
		 * {@inheritDoc getAllListViewTemplate}
		 */
		public List<LabelValue> getAllListViewTemplate()throws EazyBpmException { 
	    	/*List<LabelValue> listViews = getSession().createQuery(
					"select new com.eazytec.model.LabelValue(concat(listView.viewName ,' (', listView.version ,')') as viewName,listView.id as id) from ListView as listView where listView.isTemplate = true")
			.list();*/
			List<LabelValue> listViews = getSession().createQuery(
					"select new com.eazytec.model.LabelValue(listView.viewName as viewName,listView.id as id) from ListView as listView where listView.isTemplate = true and listView.isDelete=false and listView.active = true")
			.list();
	        if (listViews.isEmpty()) {
	            return null;
	        } else {
	            return listViews;
	        }
	    }
		
	    /**
	     * {@inheritDoc}
	     */
	    @SuppressWarnings("unchecked")
	    public Integer getLatestVersionByForm(String listViewName) throws EazyBpmException {
	        Query query = getSession().createQuery("SELECT MAX(listView.version) FROM ListView AS listView WHERE listView.viewName = '"+listViewName+"'");
	        return Integer.valueOf(String.valueOf(query.list().get(0)));
	    }
		
	    /**
	     * {@inheritDoc}
	     */
	    @SuppressWarnings("unchecked")
	    public void updateListViewStatusByName(String listViewName) throws EazyBpmException{
	        Query query = getSession().createQuery("UPDATE ListView set active = 0 WHERE viewName='"+listViewName+"'");
	        int result = query.executeUpdate();
	    }
	    
	    /**
	     * {@inheritDoc}
	     */
	    @SuppressWarnings("unchecked")
	    public void updateListActiveStatusByListViewId(String listViewId,String tableName) throws EazyBpmException{
	        Query query = getSession().createQuery("UPDATE "+tableName+" set active = 0 WHERE listView='"+listViewId+"'");
	        int result = query.executeUpdate();
	    }
	    
	    /**
	     * {@inheritDoc}
	     */
	    public List<String> getListViewIdsByName(String viewName)throws EazyBpmException{
	    	List<String> listViews = getSession().createQuery("select listView.id from ListView as listView where listView.viewName='"+viewName+"'").list();
	        if (listViews.isEmpty()) {
	            return null;
	        } else {
	            return listViews;
	        }
	    }
	    
	    /**
	     * {@inheritDoc}
	     */
	    public void inActivateAllListViewAndItsChild(String allListIds)throws EazyBpmException{
	    	 Query query = getSession().createQuery("UPDATE ListView set active = 0 WHERE id in "+allListIds);
		        int result = query.executeUpdate();
		        //query = getSession().createQuery("UPDATE ListViewButtons set active = 0 WHERE listView in "+allListIds);
		        //result = query.executeUpdate();
		        query = getSession().createQuery("UPDATE ListViewColumns set active = 0 WHERE listView in "+allListIds);
		        result = query.executeUpdate();
		        query = getSession().createQuery("UPDATE ListViewGroupSetting set active = 0 WHERE listView in "+allListIds);
		        result = query.executeUpdate();
	    }
	    
	    /**
	     * {@inheritDoc}
	     */
	    public void activateListViewStatusById(String viewId)throws EazyBpmException{
	    	Query query = getSession().createQuery("UPDATE ListView set active = 1 WHERE id='"+viewId+"'");
	        int result = query.executeUpdate();
	      /*  query = getSession().createQuery("UPDATE ListViewButtons set active = 1 WHERE listView='"+viewId+"'");
	        result = query.executeUpdate();*/
	        query = getSession().createQuery("UPDATE ListViewColumns set active = 1 WHERE listView='"+viewId+"'");
	        result = query.executeUpdate();
	        query = getSession().createQuery("UPDATE ListViewGroupSetting set active = 1 WHERE listView='"+viewId+"'");
	        result = query.executeUpdate();
	    }
	    
	    /**
	     * {@inheritDoc}
	     */
	    public void deleteAllListViewAndItsChild(String allListIds)throws EazyBpmException{
	    	Query query =getSession().createQuery("Delete ListViewGroupSetting WHERE listView in "+allListIds);
	        int result = query.executeUpdate();
	        query = getSession().createQuery("Delete ListViewButtons WHERE listView in "+allListIds);
	        result = query.executeUpdate();
	        query = getSession().createQuery("Delete ListViewColumns WHERE listView in "+allListIds);
	        result = query.executeUpdate();
	        query =  getSession().createQuery("Delete ListView WHERE id in "+allListIds);
	        result = query.executeUpdate();
	    }
	    
	    public List<ListView> getListViewDetailsByNames(String listViewNames)throws EazyBpmException{
	    	Query query = getSession().createQuery("from ListView as listView WHERE listView.viewName in "+listViewNames);
			getSession().flush();
			return query.list();
	    }
	    
	    public Module getModuleByListViewId(String listViewId)throws EazyBpmException{
	    	Query query = getSession().createSQLQuery("SELECT module.module_id AS module_id,module.name AS module_name FROM  ETEC_MODULE_LIST_VIEW  AS mobule_map,ETEC_MODULE AS module WHERE mobule_map.list_view_id='"+listViewId+"' AND 	module.module_id=mobule_map.module_id");
	    	List<Object[]> results=query.list();
	    	for(Object[] result:results){
	    		Module module=new Module();
	    		module.setId(String.valueOf(result[0]));
	    		module.setName(String.valueOf(result[1]));
	    		return module;
	    	}
	         
	    	return null;
	    }
	    
	    public List<LabelValue> getListViewNames(String listViewName)throws EazyBpmException{
	    	List<LabelValue> listViews = getSession().createQuery(
					"select new com.eazytec.model.LabelValue(listView.viewName as viewName,listView.id as id) from ListView as listView where listView.active=true and listView.viewName like '"+listViewName+"%'  order by listView.viewName ")
			.list();
	        if (listViews.isEmpty()) {
	            return null;
	        } else {
	            return listViews;
	        }
	    }
	    /**
	     * {@inheritDoc}
	     */
	    @SuppressWarnings("unchecked")
	    public List<ListViewColumns> getListViewColumns() {
	        Query qry = getSession().createQuery("from ListViewColumns listViewColumns  order by listViewColumns.orderBy");
	        return qry.list();
	    }
	    
	    /**
		 * {@inheritDoc}
		 */
		public boolean updateListViewColumnOrderNos(String columnId, int orderNo) throws EazyBpmException {
			Query qry = getSession().createQuery("update ListViewColumns As listViewColumns set listViewColumns.orderBy = "+orderNo+" where listViewColumns.id = '"+ columnId + "'");
			int result = qry.executeUpdate();
			return result != 0  ? true : false;
		}

		
		public int softDeleteTableDataAndRestore(String tableIds,
				String tableName, String columnName, int status)
				throws EazyBpmException {
			// TODO Auto-generated method stub
			return 0;
		}

		
}		



