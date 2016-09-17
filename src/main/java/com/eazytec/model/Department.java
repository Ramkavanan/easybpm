package com.eazytec.model;

import org.activiti.engine.impl.db.HasRevision;
import org.activiti.engine.impl.db.PersistentObject;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.search.annotations.Field;
import org.springframework.security.core.GrantedAuthority;

import com.eazytec.util.DateUtil;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * This class is used to represent available departments in the database.
 *
 * @author madan
 */
@XmlRootElement
@Entity
@Table(name = "DEPARTMENT")
@NamedQueries({
        @NamedQuery(
                name = "findDepartmentByName",
                query = "select r from Department r where r.name = :name "
        )
})
public class Department extends BaseObject implements Serializable, GrantedAuthority, PersistentObject, HasRevision {
    private static final long serialVersionUID = 3690197650654049848L;
    private String id;
    private String name;
    private String viewName;
    private String description;
    private int revision;
    private String departmentType;
    private boolean isParent;
    private String superDepartment;
    //private Set<Role> departmentalRoles = new HashSet<Role>();
    private Set<Department> subDepartments = new HashSet<Department>();
    private String departmentOwner;
    private String userAction;
    private int version;
    private Date createdTime = new Date();
    private Set<User> administrators = new HashSet<User>();
    private Set<User> interfacePeople= new HashSet<User>();
    private String createdTimeByString;
    private String departmentId; // Transient variable for id

    private Set<Module> modules = new HashSet<Module>();
    private String departmentRole;
   // private Set<Module> modules = new HashSet<Module>();
    private Integer orderNo;

	/**
     * Default constructor - creates a new instance with no values set.
     */
    public Department() {
    }

    /**
     * Create a new instance and set the name.
     *
     * @param name name of the department.
     */
    public Department(final String name) {
        this.name = name;
    }
    
    public Department(String id, String name) {
    	this.id = id;
    	this.name = name;
    }
    @Id
    @Column(name = "ID", length=70)    
    //@GeneratedValue(strategy = GenerationType.AUTO)
    public String getId() {
        return id;
    }

    /**
     * @return the name property (getAuthority required by Acegi's GrantedAuthority interface)
     * @see org.springframework.security.core.GrantedAuthority#getAuthority()
     */
    @JsonIgnore
    @Transient
    public String getAuthority() {
        return getName();
    }

    @Column(name = "NAME", length = 200)
    public String getName() {
        return this.name;
    }

    @Column(name = "DESCRIPTION", length = 250)
    public String getDescription() {
        return this.description;
    }   
    
    @Transient
    public int getRevision() {
        return revision;
    }
    
    @Column(name = "VIEW_NAME")
    public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setType(String departmentType) {
        this.departmentType = departmentType;
    }
     
	public void setRevision(int revision) {
	    this.revision = revision;
	}
	
	@Transient
	@JsonIgnore
	public Object getPersistentState() {
	    Map<String, Object> persistentState = new HashMap<String, Object>();
	    persistentState.put("name", name);
	    persistentState.put("departmentType", departmentType);
	    return persistentState;
	}
	
	@Column(name = "REVISION", nullable = false, length = 50, columnDefinition = "int default 1")
    @Field
	public int getRevisionNext() {
	    return revision+1;
	}
	
	public void setRevisionNext(int revision) {
	    this.revision=revision+1;
	}
	
	@Column(name = "DEPARTMENT_TYPE", length = 64)
    public String getDepartmentType() {
		return departmentType;
	}

	public void setDepartmentType(String departmentType) {
		this.departmentType = departmentType;
	}

	@Column(name = "IS_PARENT", nullable = false)
	public boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(boolean isParent) {
		this.isParent = isParent;
	}

	@Column(name = "SUPER_DEPARTMENT")
	public String getSuperDepartment() {
		return superDepartment;
	}
	
	@OneToMany(fetch=FetchType.EAGER)
	public Set<Department>getSubDepartments(){
		return subDepartments;
	}

	public void setSuperDepartment(String superDepartment) {
		this.superDepartment = superDepartment;
	}

	public void setSubDepartments(Set<Department> subDepartments) {
		this.subDepartments = subDepartments;
	}

	/*@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "DEPARTMENT_ROLE",
            joinColumns = { @JoinColumn(name = "DEPARTMENT_ID") },
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID")
    )
	public Set<Role> getDepartmentalRoles() {
		return departmentalRoles;
	}

	public void setDepartmentalRoles(Set<Role> departmentalRoles) {
		this.departmentalRoles = departmentalRoles;
	}
*/
	/**
     * {@inheritDoc}
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Department)) {
            return false;
        }

        final Department department = (Department) o;

        return !(name != null ? !name.equals(department.name) : department.name != null);

    }

    /**
     * {@inheritDoc}
     */
    public int hashCode() {
        return (name != null ? name.hashCode() : 0);
    }

    /**
     * {@inheritDoc}
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .append(this.name)
                .toString();
    }

	public void setDepartmentOwner(String departmentOwner) {
		this.departmentOwner = departmentOwner;
	}
	
	@Column(name = "DEPARTMENT_OWNER")
	public String getDepartmentOwner() {
		return departmentOwner;
	}
	
	@Transient
	public Integer getVersion() {
		return version;
	}

	 @Column(name = "VERSION", nullable = false, length = 50, columnDefinition = "int default 1")
     @Field
     public int getVersionNext() {
   	    return version+1;
     }
	 
	public void setVersion(Integer version) {
		this.version = version;
	}
	
	 public void setVersionNext(int version) {
		    this.version=version+1;
	}
	 
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	@Column(name = "CREATED_TIME", columnDefinition = "DATETIME")
	public Date getCreatedTime() {
		return createdTime;
	}
	
	@XmlTransient
	@JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "DEPARTMENT_ADMINISTRATORS",
            joinColumns = { @JoinColumn(name = "DEPARTMENT_ID") },
            inverseJoinColumns = @JoinColumn(name = "USER_ID")
    )
	public Set<User> getAdministrators() {
		return administrators;
	}
	@XmlTransient
	@JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "DEPARTMENT_INTERFACE_PEOPLE",
            joinColumns = { @JoinColumn(name = "DEPARTMENT_ID") },
            inverseJoinColumns = @JoinColumn(name = "USER_ID")
    )
	public Set<User> getInterfacePeople() {
		return interfacePeople;
	}
    
    public void setAdministrators(Set<User> administrators) {
		this.administrators = administrators;
	}

	public void setInterfacePeople(Set<User> interfacePeople) {
		this.interfacePeople = interfacePeople;
	}

	public void setUserAction(String userAction) {
		this.userAction = userAction;
	}

	@Column(name = "USER_ACTION")
	public String getUserAction() {
		return userAction;
	}
	
	@Transient
	public String getCreatedTimeByString() {
		return DateUtil.convertDateToString(createdTime);
	}

	public void setCreatedTimeByString(String createdTimeByString) {
		this.createdTimeByString = createdTimeByString;
	}

	@Transient
	public String getDepartmentId() {
		departmentId = this.id;
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	
	@Column(name = "ORDER_NO", length = 10)
	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	
	
	/*@ManyToMany(fetch = FetchType.LAZY,mappedBy = "departments")*/
	@Transient
	public Set<Module> getModules() {
		return modules;
	}

	public void setModules(Set<Module> modules) {
		this.modules = modules;
	}

	@Column(name = "DEPARTMENT_ROLE", length = 250)
	public String getDepartmentRole() {
		return departmentRole;
	}

	public void setDepartmentRole(String departmentRole) {
		this.departmentRole = departmentRole;
	}
	
}