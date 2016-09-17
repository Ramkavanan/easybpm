package com.eazytec.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.Indexed;

@Entity
@Table(name = "RE_FORM_NAME_MAPPING")
public class FormNameMappings {
	private static final long serialVersionUID = 3644859655330969141L;
	private String id;
	private String formName;
	private String formId;
	/**
     * Default constructor - creates a new instance with no values set.
     */
    public FormNameMappings() {
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
	
	@Column(name = "FORM_NAME",length = 100)
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	
	@Column(name = "FORM_ID",length = 100)
	public String getFormId() {
		return formId;
	}
	public void setFormId(String formId) {
		this.formId = formId;
	}

}
