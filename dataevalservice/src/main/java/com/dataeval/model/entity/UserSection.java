package com.dataeval.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * The persistent class for the USER_SECTIONS database table.
 * 
 */
@Entity
@Table(name = "USER_SECTIONS")
@NamedQuery(name = "UserSection.findAll", query = "SELECT u FROM UserSection u")
public class UserSection implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private Integer sequence;
	private UserPage userPage;
	private Integer sectionId;
	private Integer layout;
	private List<UserQuestion> userQuestions;

	public UserSection() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "USER_PAGE_ID")
	public UserPage getUserPage() {
		return userPage;
	}

	public void setUserPage(UserPage userPage) {
		this.userPage = userPage;
	}

	@OneToMany(mappedBy = "userSection")
	public List<UserQuestion> getUserQuestions() {
		return userQuestions;
	}

	public void setUserQuestions(List<UserQuestion> userQuestions) {
		this.userQuestions = userQuestions;
	}

	@Column(name = "ACTUAL_SECTION_ID")
	public Integer getSectionId() {
		return sectionId;
	}

	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}

	@Column(name = "LAYOUT")
	public Integer getLayout() {
		return layout;
	}

	public void setLayout(Integer layout) {
		this.layout = layout;
	}

}