package com.rest.appvoylio.entity;


import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "role_tbl")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rid;

    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private Set<SignupEntity> users;

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Set<SignupEntity> getUsers() {
		return users;
	}

	public void setUsers(Set<SignupEntity> users) {
		this.users = users;
	}

	
    
}
