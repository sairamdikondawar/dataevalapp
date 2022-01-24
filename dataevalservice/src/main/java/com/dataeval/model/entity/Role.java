package com.dataeval.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the ROLES database table.
 * 
 */
@Entity
@Table(name = "ROLES")
@NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String roleDesc;
	private String roleName;
	private List<FlowConfig> flowConfigs;
//	private User user;
	private List<User> users;

	public Role() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "ROLE_DESC")
	public String getRoleDesc() {
		return this.roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	@Column(name = "ROLE_NAME")
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	// bi-directional one-to-one association to FlowConfig
//	@OneToOne(mappedBy = "role")
//	public FlowConfig getFlowConfig() {
//		return this.flowConfig;
//	}
//
//	public void setFlowConfig(FlowConfig flowConfig) {
//		this.flowConfig = flowConfig;
//	}

//	@OneToMany(mappedBy = "role")
	@Transient
	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@OneToMany(mappedBy = "role")
	public List<FlowConfig> getFlowConfigs() {
		return flowConfigs;
	}

	public void setFlowConfigs(List<FlowConfig> flowConfigs) {
		this.flowConfigs = flowConfigs;
	}

	// bi-directional one-to-one association to User Note : below were automated
	// genrated many to many relation ship
//	@OneToOne(mappedBy="role") 
//	public User getUser() {
//		return this.user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}
//
//
//	//bi-directional many-to-many association to User
//	@ManyToMany(mappedBy="roles")
//	public List<User> getUsers() {
//		return this.users;
//	}
//
//	public void setUsers(List<User> users) {
//		this.users = users;
//	}

}