package com.eazytec.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.activiti.engine.impl.db.PersistentObject;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

/**
 * This class represents the basic email contact object
 *
 * @author mathi
 */
@Entity
@Table(name = "EMAIL_CONTACT")
@XmlRootElement
public class EmailContact extends BaseObject implements Serializable, PersistentObject {
    private static final long serialVersionUID = 3832626162173359411L;

    private String id;
    private String firstName;                   
    private String lastName;                    
    private String englishName;
    private String email;                       
    private String mobile;
    private String workPhone;
    private String homePhone;
    private String fax;
    private String website;
    private String createdBy;

	/**
     * Default constructor - creates a new instance with no values set.
     */
    public EmailContact() {
    }

    
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "ID", unique = true)
    public String getId() {
        return id;
    }
    
	@Override
	public void setId(String id) {
		this.id = id;
		// TODO Auto-generated method stub
		
	}

    @Column(name = "FIRST_NAME")
    @Field
    public String getFirstName() {
        return firstName;
    }

    @Column(name = "LAST_NAME")
    @Field
    public String getLastName() {
        return lastName;
    }

    @Column(name = "EMAIL")
    @Field
    public String getEmail() {
        return email;
    }

    @Column(name = "WORK_PHONE")
    public String getWorkPhone() {
        return workPhone;
    }

    @Column(name = "WEBSITE")
    @Field
    public String getWebsite() {
        return website;
    }

    /**
     * Returns the full name.
     *
     * @return firstName + ' ' + lastName
     */
    @Transient
    public String getFullName() {
        return firstName + ' ' + lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}


	@Column(name = "HOME_PHONE")
	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	@Column(name = "FAX")
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

      /**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}


	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	@Transient
      public Object getPersistentState() {
    	    Map<String, Object> persistentState = new HashMap<String, Object>();
    	    persistentState.put("firstName", firstName);
    	    persistentState.put("lastName", lastName);
    	    persistentState.put("email", email);
    	    return persistentState;
      }
      

    /**
     * {@inheritDoc}
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EmailContact)) {
            return false;
        }

        final EmailContact emailContact = (EmailContact) o;

        return !(email != null ? !email.equals(emailContact.getEmail()) : emailContact.getEmail() != null);

    }

    /**
     * {@inheritDoc}
     */
    public int hashCode() {
        return (email != null ? email.hashCode() : 0);
    }

    /**
     * {@inheritDoc}
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .append(this.email)
                .toString();
    }
    

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMobile() {
		return mobile;
	}

}
