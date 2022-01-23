package com.dataeval.model.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the USER_QUESTIONS database table.
 * 
 */
@Entity
@Table(name = "USER_QUESTIONS")
@NamedQuery(name = "UserQuestion.findAll", query = "SELECT u FROM UserQuestion u")
public class UserQuestion implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String answer;
	private String name;
	private String type;
	private UserSection userSection;

	public UserQuestion() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@ManyToOne
	@JoinColumn(name = "USER_SECTION_ID")
	public UserSection getUserSection() {
		return userSection;
	}

	public void setUserSection(UserSection userSection) {
		this.userSection = userSection;
	}

}