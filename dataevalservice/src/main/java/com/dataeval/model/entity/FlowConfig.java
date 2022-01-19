package com.dataeval.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the FLOW_CONFIG database table.
 * 
 */
@Entity
@Table(name = "FLOW_CONFIG")
@NamedQuery(name = "FlowConfig.findAll", query = "SELECT f FROM FlowConfig f")
public class FlowConfig implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String createdBy;
	private Date createdDate;
	private String flowName;
	private Role role;
	private List<Question> questions;

	public FlowConfig() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "CREATED_BY")
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE")
	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "FLOW_NAME")
	public String getFlowName() {
		return this.flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	// bi-directional one-to-one association to Role
	@ManyToOne
	@JoinColumn(name = "ROLE_ID")
	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	// bi-directional one-to-one association to Question
	@OneToMany(mappedBy = "flowConfig")
	public List<Question> getQuestions() {
		return this.questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

}