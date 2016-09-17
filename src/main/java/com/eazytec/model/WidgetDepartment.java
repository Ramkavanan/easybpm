package com.eazytec.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.Indexed;

@Entity
@Table(name = "RE_WIDGET_DEPARTMENT")
public class WidgetDepartment {
	private static final long serialVersionUID = 3644859655330969141L;
	private String id;
	private String widgetId;
	private String departmentId;
	
	WidgetDepartment(){
		
	}
	
    public WidgetDepartment(String widgetId,String departmentId) {
    	this.widgetId = widgetId;
    	this.departmentId = departmentId;
    }
    
    @Id
	@Column(name = "ID")
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "WIDGET_ID",length = 100)
	public String getWidgetId() {
		return widgetId;
	}
	public void setWidgetId(String widgetId) {
		this.widgetId = widgetId;
	}
	
	@Column(name = "DEPARTMENT_ID",length = 100)
	public String getDepartmentId() {
		return departmentId;
	}
	
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

}
