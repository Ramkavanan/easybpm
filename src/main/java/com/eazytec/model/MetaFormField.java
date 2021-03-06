package com.eazytec.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.Indexed;


@Entity
@Table(name = "RE_FORM_FIELD")
public class MetaFormField {
	private static final long serialVersionUID = 3644859655330969141L;
	private String id;
	private String name;
	private MetaForm formId;
	private MetaTableColumns columnId;
	
	/**
     * Default constructor - creates a new instance with no values set.
     */
    public MetaFormField() {
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
	
	@Column(name = "NAME",length = 100)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "FORM_ID", nullable = false)
    public MetaForm getFormId() {
		return formId;
	}
	public void setFormId(MetaForm formId) {
		this.formId = formId;
	}
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "COLUMN_ID", nullable = false)
    public MetaTableColumns getColumnId() {
		return columnId;
	}
	public void setColumnId(MetaTableColumns columnId) {
		this.columnId = columnId;
	}
	
}
