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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.Indexed;

@Entity
@Table(name = "RE_LIST_VIEW")
public class ListView {
	private static final long serialVersionUID = 3644859655330969141L;
	private String id;
	private String viewName;
	private String viewTitle;
	private String selectColumns;
	private String fromQuery;
	private String whereQuery;
	private String orderBy;
	private String createdBy;
	private String groupBy;
	private String onRenderScriptName;
	private String onRenderScript;
	private boolean isTemplate = false;
	private boolean isNeedCheckbox = false;
	private boolean isFilterDuplicateData = false;
	private boolean isShowSearchBox = false;
	private int pageSize = 20;
	private Set<ListViewColumns> listViewColumns = new HashSet<ListViewColumns>();
	private Set<ListViewButtons> listViewButtons = new HashSet<ListViewButtons>();
	private Set<ListViewGroupSetting> listViewGroupSetting = new HashSet<ListViewGroupSetting>();
	private Set<Module> modules=new HashSet<Module>();
	private int version;
	private boolean active;
	private boolean isDelete;
	private boolean isShow;
	private Date createdTime;
	private Date deletedTime;
	private String deletedUser;

	@Column(name = "DELETED_USER",length = 255)
	public String getDeletedUser() {
		return deletedUser;
	}

	public void setDeletedUser(String deletedUser) {
		this.deletedUser = deletedUser;
	}
	@Column(name = "DELETED_TIME",length = 255)
	public Date getDeletedTime() {
		return deletedTime;
	}

	public void setDeletedTime(Date deletedTime) {
		this.deletedTime = deletedTime;
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
	
	@Column(name = "VIEW_NAME",length = 255)
	public String getViewName() {
		return viewName;
	}
	
	public void setViewName(String viewName) {
		this.viewName = viewName.toUpperCase();
	}
	
	@Column(name = "VIEW_TITLE",length = 255)
	public String getViewTitle() {
		return viewTitle;
	}

	public void setViewTitle(String viewTitle) {
		this.viewTitle = viewTitle;
	}

	@Column(name = "CREATED_TIME", columnDefinition = "DATETIME")
	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}	
	
	@Column(name = "SELECT_COLUMNS", columnDefinition = "longtext")
	public String getSelectColumns() {
		return selectColumns;
	}

	public void setSelectColumns(String selectColumns) {
		this.selectColumns = selectColumns;
	}

	@Column(name = "FROM_QUERY", columnDefinition = "longtext")
	public String getFromQuery() {
		return fromQuery;
	}
	
	public void setFromQuery(String fromQuery) {
		this.fromQuery = fromQuery;
	}
	
	@Column(name = "WHERE_QUERY", columnDefinition = "longtext")
	public String getWhereQuery() {
		return whereQuery;
	}
	
	public void setWhereQuery(String whereQuery) {
		this.whereQuery = whereQuery;
	}
	
	@Column(name = "ORDER_BY", columnDefinition = "longtext")
	public String getOrderBy() {
		return orderBy;
	}
	
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	
	@Column(name = "GROUP_BY", length = 255)
	public String getGroupBy() {
		return groupBy;
	}
	
	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}
	
	@Column(name = "ON_RENDER_SCRIPT_NAME", length = 255)
	public String getOnRenderScriptName() {
		return onRenderScriptName;
	}
	public void setOnRenderScriptName(String onRenderScriptName) {
		this.onRenderScriptName = onRenderScriptName;
	}

	@Column(name = "ON_RENDER_SCRIPT", columnDefinition = "longtext")
	public String getOnRenderScript() {
		return onRenderScript;
	}
	public void setOnRenderScript(String onRenderScript) {
		this.onRenderScript = onRenderScript;
	}
	
	@Column(name = "PAGE_SIZE", length = 11)
	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.ALL}, mappedBy = "listView")
	public Set<ListViewColumns> getListViewColumns() {
		return listViewColumns;
	}

	public void setListViewColumns(Set<ListViewColumns> listViewColumns) {
		this.listViewColumns = listViewColumns;
	}
	
	@OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.ALL}, mappedBy = "listView")
	public Set<ListViewButtons> getListViewButtons() {
		return listViewButtons;
	}

	public void setListViewButtons(Set<ListViewButtons> listViewButtons) {
		this.listViewButtons = listViewButtons;
	}

	@Column(name = "IS_FILTER_DUPLICATE_DATA", length = 40 ,columnDefinition="boolean default false")
	public boolean getIsFilterDuplicateData() {
		return isFilterDuplicateData;
	}

	public void setIsFilterDuplicateData(boolean isFilterDuplicateData) {
		this.isFilterDuplicateData = isFilterDuplicateData;
	}

	@Column(name = "IS_SHOW_SEARCH_BOX", length = 40,columnDefinition="boolean default false")
	public boolean getIsShowSearchBox() {
		return isShowSearchBox;
	}

	public void setIsShowSearchBox(boolean isShowSearchBox) {
		this.isShowSearchBox = isShowSearchBox;
	}
	
	@Column(name = "CREATED_BY", length = 50)
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.ALL}, mappedBy = "listView")
	public Set<ListViewGroupSetting> getListViewGroupSetting() {
		return listViewGroupSetting;
	}
	public void setListViewGroupSetting(
			Set<ListViewGroupSetting> listViewGroupSetting) {
		this.listViewGroupSetting = listViewGroupSetting;
	}
	
	/*@ManyToOne
    @JoinColumn(name="MODULE_ID")*/
	
	/*@ManyToMany(fetch = FetchType.LAZY,mappedBy = "listViews")*/
	@ManyToMany(fetch=FetchType.LAZY,cascade={CascadeType.ALL})
    @JoinTable(
            name = "MODULE_LIST_VIEW",
            joinColumns ={@JoinColumn(name = "LIST_VIEW_ID")},
            inverseJoinColumns =  @JoinColumn(name = "MODULE_ID")
    )
	public Set<Module> getModules() {
		return modules;
	}

	public void setModules(Set<Module> modules) {
		this.modules = modules;
	}
	@Column(name = "IS_NEED_CHECKBOX", length = 40, columnDefinition="boolean default false")
	public boolean getIsNeedCheckbox() {
		return isNeedCheckbox;
	}
	public void setIsNeedCheckbox(boolean isNeedCheckbox) {
		this.isNeedCheckbox = isNeedCheckbox;
	}

	@Column(name = "IS_TEMPLATE", length = 40, columnDefinition="boolean default false")
	public boolean getIsTemplate() {
		return isTemplate;
	}
	public void setIsTemplate(boolean isTemplate) {
		this.isTemplate = isTemplate;
	}
	
	@Column(name = "VERSION", columnDefinition = "int default 1")
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }

    @Column(name = "IS_ACTIVE", length = 40)
    public boolean isActive() {
        return active;
    }

    @Column(name = "IS_DELETE", columnDefinition="boolean default false")
	public boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	@Column(name = "IS_SHOW", columnDefinition="boolean default true")
	public boolean getIsShow() {
		return isShow;
	}

	public void setIsShow(boolean isShow) {
		this.isShow = isShow;
	}
    
	public void addModule(Module module){
		getModules().add(module);
	}
}
