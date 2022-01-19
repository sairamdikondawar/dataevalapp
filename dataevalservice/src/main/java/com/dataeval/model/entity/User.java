package com.dataeval.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the USER database table.
 * 
 */
@Entity
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String password;
	private String status;
	private String userName;
	private Role role;
//	private List<Role> roles;

	public User() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "USER_NAME")
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	// bi-directional one-to-one association to Role
	@OneToOne
	@JoinColumn(name = "ROLE_ID")
	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	// bi-directional many-to-many association to Role
//	@ManyToMany
//	@JoinTable(name = "USER_ROLES", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = {
//			@JoinColumn(name = "ROLE_ID") })
//	public List<Role> getRoles() {
//		return this.roles;
//	}
//
//	public void setRoles(List<Role> roles) {
//		this.roles = roles;
//	}

}