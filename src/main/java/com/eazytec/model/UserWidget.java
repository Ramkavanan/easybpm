package com.eazytec.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;


@Entity
@Table(name = "PORTAL_USER_WIDGET")
public class UserWidget  implements Serializable {
    private static final long serialVersionUID = 3690197650654049850L;
    private String id;
    private String widgetId;
    private int noOfWidget;
    private String userName;
        
    /**
     * Default constructor - creates a new instance with no values set.
     */
    public UserWidget() {
    }

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "USER_WIDGET_ID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public void setUserName(String userName){
    	this.userName = userName;
    	
    }
    
    @Column(name = "USER_NAME")
    public String getUserName(){
    	return userName;
    }
    
    @Column(name = "NO_OF_WIDGET")
	public int getNoOfWidget() {
		return noOfWidget;
	}

	public void setNoOfWidget(int noOfWidget) {
		this.noOfWidget = noOfWidget;
	}
	public void setWidgetId(String widgetId){
		this.widgetId = widgetId;
	}
	@Column(name="WIDGET_ID")
	public String getWidgetId(){
		return widgetId;
	}
	
}

