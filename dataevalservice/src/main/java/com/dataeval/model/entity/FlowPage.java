package com.dataeval.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.OrderBy;
import org.hibernate.annotations.Where;

/**
 * The persistent class for the FLOW_PAGES database table.
 * 
 */
@Entity
@Table(name = "FLOW_PAGES")
@NamedQuery(name = "FlowPage.findAll", query = "SELECT f FROM FlowPage f")
public class FlowPage implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer flowId;
	private String name;
	private Integer sequence;
	private List<Question> questions;
	private List<PageSection> pageSections;
	private Integer layoutColumns;
	private String status;

	public FlowPage() {
	}

	public FlowPage(Integer id, String name) {

		this.id = id;
		this.name = name;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "FLOW_ID")
	public Integer getFlowId() {
		return this.flowId;
	}

	public void setFlowId(Integer flowId) {
		this.flowId = flowId;
	}

	public Integer getSequence() {
		return this.sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	// bi-directional one-to-one association to Question
//	@OneToMany(mappedBy = "flowPage")
	@Transient
	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	@Column(name = "LAYOUT_COLUMNS")
	public Integer getLayoutColumns() {
		return layoutColumns;
	}

	public void setLayoutColumns(Integer layoutColumns) {
		this.layoutColumns = layoutColumns;
	}

	@Column(name = "PAGE_NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(mappedBy = "page")
	@Where(clause = "lower(status) like 'active'")
	@OrderBy(clause = "sequence asc")
	public List<PageSection> getPageSections() {
		return pageSections;
	}

	public void setPageSections(List<PageSection> pageSections) {
		this.pageSections = pageSections;
	}

	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}