package com.cha104g1.freshtown_springboot.login.model;


import javax.persistence.Entity;

import org.springframework.data.annotation.Transient;

public class IdentityVO {
	
	@Transient
	private Integer id;
	@Transient
	private String Name;
	@Transient
	private String personal;
	@Transient
	private String permissions;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String Name) {
		this.Name = Name;
	}
	public String getPersonal() {
		return personal;
	}
	public void setPersonal(String personal) {
		this.personal = personal;
	}
	public String getPermissions() {
		return permissions;
	}
	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

	
	
}
