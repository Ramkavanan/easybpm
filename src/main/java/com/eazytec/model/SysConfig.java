package com.eazytec.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * This class represents the basic "user" object in AppFuse that allows for authentication
 * and user management.  It implements Acegi Security's UserDetails interface.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 *         Updated by Dan Kibler (dan@getrolling.com)
 *         Extended to implement Acegi UserDetails interface
 *         by David Carter david@carter.net
 */
@Entity
@Table(name = "SYSCONFIG")
public class SysConfig extends BaseObject {
	private static final long serialVersionUID = 1L;

    private String id;
    private String selectKey;
    private String selectType;
    private String selectValue;       
    private boolean systemDefined;
    private String description;
     

    
	/**
     * Default constructor - creates a new instance with no values set.
     */
    public SysConfig() {
    }

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "ID", unique = true)
    public String getId() {
        return id;
    }

    
    @Column(name="SELECT_VALUE",length = 255)
    public String getSelectValue(){
    	return selectValue;
    }
    
    @Column(name="SELECT_KEY",nullable = false, length = 255, unique=true)
    public String getSelectKey() {
        return selectKey;
    }

    @Column(name="SELECT_TYPE",nullable = false, length = 50)
    public String getSelectType() {
        return selectType;
    }
    
    @Column(name="SYSTEM_DEFINED",nullable = false,length = 40)
    public boolean getSystemDefined(){
    	return systemDefined;
    }
    
    @Column(name="DESCRIPTION",length = 255)
    public String getDescription(){
    	return description;
    }
    
    public void setSystemDefined(boolean systemDefined){
    	this.systemDefined = systemDefined;
    }
  
    public void setId(String id) {
        this.id = id;
    }
    public void setSelectKey(String selectKey) {
        this.selectKey = selectKey;
    }

    public void setSelectType(String selectType) {
        this.selectType = selectType;
    }
  
    public void setSelectValue(String selectValue){
    	this.selectValue=selectValue;
    }
    
    public void setDescription(String description){
    	this.description=description;
    }

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
}