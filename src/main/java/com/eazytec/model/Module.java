package com.eazytec.model;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.transaction.annotation.Transactional;


/**
 * This class is used to schedule the events.
 *
 * @author madan
 */
@Entity
@Table(name = "MODULE")
public class Module extends BaseObject{

	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String description;
	//private Set<Classification> classification = new HashSet<Classification>();
	private int moduleOrder;
	//private int version;
	private Date createdTime;
	private Date updatedTime;
	private String createdBy;
	private String updatedBy;
	
	private boolean isEdit;
	private boolean isSystemModule;
	private boolean isPublic;

	private Set<User> administrators = new HashSet<User>();
	private Set<Department> departments = new HashSet<Department>();
	private Set<Role> roles = new HashSet<Role>();
	private Set<Group> groups = new HashSet<Group>();
	
	private Set<User> viewAdministrators = new HashSet<User>();
	private Set<Department> viewDepartments = new HashSet<Department>();
	private Set<Role> viewRoles = new HashSet<Role>();
	private Set<Group> viewGroups = new HashSet<Group>();
	//private Set<Menu> menus = new HashSet<Menu>();
	private Set<MetaForm> forms = new HashSet<MetaForm>();
	private Set<MetaTable> tables = new HashSet<MetaTable>();
	private Set<ListView> listViews = new HashSet<ListView>();
	private Set<ModuleRolePrivilege> modleRoles = new HashSet<ModuleRolePrivilege>();
	
	
	//private Set<Process> processes = new HashSet<Process>();
	//private boolean isParent;
	//private Set<Module> parentModules = new HashSet<Module>();
	//private Set<Module> childModules = new HashSet<Module>();
	
	
	//private Set<ProcessDefinitionEntity> process = new HashSet<ProcessDefinitionEntity>();
	
	

	/*@ManyToMany(fetch = FetchType.LAZY)
	@OrderBy("moduleOrder DESC")
    @JoinTable(
            name = "PARENT_MODULE",
            joinColumns = { @JoinColumn(name = "PARENT_MODULE_ID") },
            inverseJoinColumns = @JoinColumn(name = "MODULE_ID")
    )
	public Set<Module> getChildModules() {
		return childModules;
	}

	public void setChildModules(Set<Module> childModules) {
		this.childModules = childModules;
	}*/
	/*@Transactional
	@OneToMany(fetch = FetchType.LAZY,cascade={CascadeType.ALL})
    @JoinColumn(name="MODULE_ID")*/
	
	/*@Transactional
	@ManyToMany(fetch = FetchType.LAZY,cascade={CascadeType.ALL})
    @JoinTable(
            name = "MODULE_LISTVIEW",
            joinColumns = { @JoinColumn(name = "MODULE_ID") },
            inverseJoinColumns = @JoinColumn(name = "LISTVIEW_ID")
    )*/
	@ManyToMany(fetch = FetchType.LAZY,mappedBy = "modules")
	public Set<ListView> getListViews() {
		return new HashSet<ListView>(listViews);
	}

	public void setListViews(Set<ListView> listViews) {
		this.listViews = listViews;
	}
	
	/*@Transactional
	@OneToMany(fetch = FetchType.LAZY,cascade={CascadeType.ALL})
    @JoinColumn(name="MODULE_ID")*/
	/*@Transactional
	@ManyToMany(fetch = FetchType.LAZY,cascade={CascadeType.ALL})
    @JoinTable(
            name = "MODULE_FORM",
            joinColumns = { @JoinColumn(name = "MODULE_ID") },
            inverseJoinColumns = @JoinColumn(name = "FORM_ID")
    )*/
	@ManyToMany(fetch = FetchType.LAZY,mappedBy = "modules")
	public Set<MetaForm> getForms() {
		return new HashSet<MetaForm>(forms);
	}

	public void setForms(Set<MetaForm> forms) {
		this.forms = forms;
	}
	
	/*@Transactional
	@OneToMany(fetch = FetchType.LAZY,cascade={CascadeType.ALL})
    @JoinColumn(name="MODULE_ID")*/
	
	/*@Transactional
	@ManyToMany(fetch = FetchType.LAZY,cascade={CascadeType.ALL})
    @JoinTable(
            name = "MODULE_TABLES",
            joinColumns = { @JoinColumn(name = "MODULE_ID") },
            inverseJoinColumns = @JoinColumn(name = "TABLE_ID")
    )*/
	@ManyToMany(fetch = FetchType.LAZY,mappedBy = "modules")
	public Set<MetaTable> getTables() {
		return new HashSet<MetaTable>(tables);
	}

	public void setTables(Set<MetaTable> tables) {
		this.tables = tables;
	}

	/*@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "PARENT_MODULE",
            joinColumns = { @JoinColumn(name = "MODULE_ID") },
            inverseJoinColumns = @JoinColumn(name = "PARENT_MODULE_ID")
    )
	public Set<Module> getParentModules() {
		return parentModules;
	}

	public void setParentModules(Set<Module> parentModules) {
		this.parentModules = parentModules;
	}*/
	

	/**
     * Default constructor - creates a new instance with no values set.
     */
    public Module() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the id
	 */
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "MODULE_ID", unique = true)
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}	

	/**
	 * @return the eventName
	 */
	
	@Column(name = "NAME",unique = true)
	public String getName() {
		return name;
	}

	/**
	 * @param eventName the eventName to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/*@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "CLASSIFICATION_MODULE",
            joinColumns = { @JoinColumn(name = "MODULE_ID") },
            inverseJoinColumns = @JoinColumn(name = "CLASSIFICATION_ID")
    )
	public Set<Classification> getClassification() {
		return classification;
	}

	public void setClassification(Set<Classification> classification) {
		this.classification = classification;
	}*/
	

	@Column(name = "MODULE_ORDER",columnDefinition = "int default 1")
	public int getModuleOrder() {
		return moduleOrder;
	}

	public void setModuleOrder(int moduleOrder) {
		this.moduleOrder = moduleOrder;
	}

	/*@ManyToMany(fetch = FetchType.LAZY)
	    @JoinTable(
	            name = "USER_MODULE",
	            joinColumns = { @JoinColumn(name = "MODULE_ID") },
	            inverseJoinColumns = @JoinColumn(name = "USER_ID")
	    )
	public Set<User> getAdministrators() {
		return administrators;
	}

	public void setAdministrators(Set<User> administrators) {
		this.administrators = administrators;
	}*/
	
	@Transient
	public Set<User> getAdministrators() {
		return administrators;
	}

	public void setAdministrators(Set<User> administrators) {
		this.administrators = administrators;
	}
	
	@Transient
	public Set<User> getViewAdministrators() {
		return viewAdministrators;
	}

	public void setViewAdministrators(Set<User> viewAdministrators) {
		this.viewAdministrators = viewAdministrators;
	}

	/*@ManyToMany(fetch = FetchType.LAZY)
	    @JoinTable(
	            name = "DEPARTMENT_MODULE",
	            joinColumns = { @JoinColumn(name = "MODULE_ID") },
	            inverseJoinColumns = @JoinColumn(name = "DEPARTMENT_ID")
	    )
	public Set<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(Set<Department> departments) {
		this.departments = departments;
	}*/
	@Transient
	public Set<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(Set<Department> departments) {
		this.departments = departments;
	}

/*	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}*/

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	/*@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "MENU_MODULE",
            joinColumns = { @JoinColumn(name = "MODULE_ID") },
            inverseJoinColumns = @JoinColumn(name = "MENU_ID")
    )
	public Set<Menu> getMenus() {
		return menus;
	}

	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}*/
	
/*	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "PROCESS_MODULE",
            joinColumns = { @JoinColumn(name = "MODULE_ID") },
            inverseJoinColumns = @JoinColumn(name = "PROCESS_ID")
    )*/
	/*@OneToMany(fetch=FetchType.LAZY)
	public Set<Process> getProcesses() {
		return processes;
	}

	public void setProcesses(Set<Process> processes) {
		this.processes = processes;
	}*/
	
	/*@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "RE_FORM_MODULE",
            joinColumns = { @JoinColumn(name = "MODULE_ID") },
            inverseJoinColumns = @JoinColumn(name = "FORM_ID")
    )*/
	
	
	/*@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "PROCESS_MODULE",
            joinColumns = { @JoinColumn(name = "MODULE_ID") },
            inverseJoinColumns = @JoinColumn(name = "PROCESS_ID")
    )
	public Set<ProcessDefinitionEntity> getProcess() {
		return process;
	}

	public void setProcess(Set<ProcessDefinitionEntity> process) {
		this.process = process;
	}	*/

/*	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "ROLE_MODULE",
            joinColumns = { @JoinColumn(name = "MODULE_ID") },
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID")
    )
	public Set<Role> getRoles() {
		return roles;
	}
	
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}*/
	@Transient
	public Set<Role> getRoles() {
		return roles;
	}
	
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	/*@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "GROUP_MODULE",
            joinColumns = { @JoinColumn(name = "MODULE_ID") },
            inverseJoinColumns = @JoinColumn(name = "GROUP_ID")
    )
	public Set<Group> getGroups() {
		return groups;
	}
	
	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}*/
	@Transient
	public Set<Group> getGroups() {
		return groups;
	}
	
	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

	/**
     * {@inheritDoc}
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Module)) {
            return false;
        }

        final Module schedule = (Module) o;

        return !(id != null ? !id.equals(schedule.id) : schedule.id != null);

    }

    /**
     * {@inheritDoc}
     */
    public int hashCode() {
        return (id != null ? id.hashCode() : 0);
    }

    /**
     * {@inheritDoc}
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .append(this.id)
                .toString();
    }
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "module")
	public Set<ModuleRolePrivilege> getModleRoles() {
		return modleRoles;
	}

	public void setModleRoles(Set<ModuleRolePrivilege> modleRoles) {
		this.modleRoles = modleRoles;
	}

	@Transient
	public Set<Department> getViewDepartments() {
		return viewDepartments;
	}

	public void setViewDepartments(Set<Department> viewDepartments) {
		this.viewDepartments = viewDepartments;
	}

	@Transient
	public Set<Role> getViewRoles() {
		return viewRoles;
	}

	public void setViewRoles(Set<Role> viewRoles) {
		this.viewRoles = viewRoles;
	}

	@Transient
	public Set<Group> getViewGroups() {
		return viewGroups;
	}

	public void setViewGroups(Set<Group> viewGroups) {
		this.viewGroups = viewGroups;
	}
	
	@Transient
	public boolean isEdit() {
		return isEdit;
	}

	public void setEdit(boolean isEdit) {
		this.isEdit = isEdit;
	}
	
	@Column(name = "IS_SYSTEM_MODULE" , columnDefinition = "boolean default false" )
	public boolean getIsSystemModule() {
		return isSystemModule;
	}

	public void setIsSystemModule(boolean isSystemModule) {
		this.isSystemModule = isSystemModule;
	}
	
	@Column(name = "IS_PUBLIC" , columnDefinition = "boolean default false" )
	public boolean getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}
	
	/*@Column(name = "IS_PARENT")
	public boolean getIsParent() {
		return isParent;
	}
	
	public void setIsParent(boolean isParent) {
		this.isParent = isParent;
	}*/
    
    
    
    
}
