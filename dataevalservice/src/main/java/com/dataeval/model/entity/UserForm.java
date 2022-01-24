package com.dataeval.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * The persistent class for the USER_FLOWS database table.
 * 
 */
@Entity
@Table(name = "USER_FORMS")
@NamedQuery(name = "UserForm.findAll", query = "SELECT u FROM UserForm u")
public class UserForm extends AuditEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private User user;
	
//	private List<UserPage> userPages;

	public UserForm() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	 

}