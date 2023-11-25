package com.rest.appvoylio.dto;

import java.sql.Timestamp;
import java.util.Set;

import com.rest.appvoylio.entity.RoleEntity;

public class SignupDTO {
	
	private int uid;
	private String username;
	private String name;
	private String email;
	private String password;
	private Timestamp dos;
    private Set<RoleEntity> roles;
    
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Timestamp getDos() {
		return dos;
	}
	public void setDos(Timestamp dos) {
		this.dos = dos;
	}
	public Set<RoleEntity> getRoles() {
		return roles;
	}
	public void setRoles(Set<RoleEntity> roles) {
		this.roles = roles;
	}
	

	
}









