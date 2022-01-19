package com.dataeval.model.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the QUESTION_TYPES database table.
 * 
 */
@Entity
@Table(name = "QUESTION_TYPES")
@NamedQuery(name = "QuestionType.findAll", query = "SELECT q FROM QuestionType q")
public class QuestionType implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String description;
	private String questionType;
	private String status;

	public QuestionType() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "QUESTION_TYPE")
	public String getQuestionType() {
		return this.questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}