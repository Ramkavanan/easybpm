package com.eazytec.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.Indexed;


@Entity
@Table(name = "OPINION")
public class UserOpinion implements Serializable {
	private static final long serialVersionUID = 4453338766237619444L;
	private String id;
	private String word;
	private String userId;
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID", unique = true)
	public String getId() {
		return id;
	}
	
   	public void setId(String id) {
		this.id = id;
	}
   	
	@Column(name = "WORD",unique = false, nullable = false)
	public String getWord() {
		return word;
	}
	
	public void setWord(String word) {
		this.word = word;
	}
	
	@Column(name = "USER_ID")
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
	