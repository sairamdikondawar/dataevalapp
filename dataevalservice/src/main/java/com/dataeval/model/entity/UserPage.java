package com.dataeval.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * The persistent class for the USER_PAGE database table.
 * 
 */
@Entity
@Table(name = "USER_PAGE")
@NamedQuery(name = "UserPage.findAll", query = "SELECT u FROM UserPage u")
public class UserPage implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer layout;
	private String name;
	private Integer sequence;
	private UserForm userForm;;
	private List<UserSection> userSections;

	public UserPage() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLayout() {
		return this.layout;
	}

	public void setLayout(Integer layout) {
		this.layout = layout;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSequence() {
		return this.sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	@ManyToOne
	@JoinColumn(name = "USER_FLOW_ID")
	public UserForm getUserForm() {
		return userForm;
	}

	public void setUserForm(UserForm userForm) {
		this.userForm = userForm;
	}

	@OneToMany(mappedBy = "userPage")
	public List<UserSection> getUserSections() {
		return userSections;
	}

	public void setUserSections(List<UserSection> userSections) {
		this.userSections = userSections;
	}

}