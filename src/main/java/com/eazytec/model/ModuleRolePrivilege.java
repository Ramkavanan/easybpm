package com.eazytec.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "MODULE_ROLE_PRIVILEGE")
public class ModuleRolePrivilege {
	private static final long serialVersionUID = 3690197650654049848L;
    private String id;
    private Module module;
    private String roleName;
    private String roleType;
    private String privilegeName;
	private String privilegeType;
    
    
    public ModuleRolePrivilege(){
    }
    
    
    public ModuleRolePrivilege(Module module,String roleName,String roleType,String privilegeName,String privilegeType){
    	this.module=module;
    	this.roleName=roleName;
    	this.roleType=roleType;
    	this.privilegeName=privilegeName;
    	this.privilegeType=privilegeType;
    }
    
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "ID", unique = true)
	public String getId() {
		return id;
	}
    
	public void setId(String id) {
		this.id = id;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "MODULE_ID", nullable = false)
	public Module getModule() {
		return module;
	}
	
	public void setModule(Module module) {
		this.module = module;
	}
	
	@Column(name = "ROLE_NAME", length = 250)
	public String getRoleName() {
		return roleName;
	}
	
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	@Column(name = "ROLE_TYPE", length = 250)
	public String getRoleType() {
		return roleType;
	}
	
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	@Column(name = "PRIVILEGE_NAME", length = 250)
	public String getPrivilegeName() {
		return privilegeName;
	}

	public void setPrivilegeName(String privilegeName) {
		this.privilegeName = privilegeName;
	}

	@Column(name = "PRIVILEGE_TYPE", length = 250)
	public String getPrivilegeType() {
		return privilegeType;
	}


	public void setPrivilegeType(String privilegeType) {
		this.privilegeType = privilegeType;
	}
	
	
}
