package com.dataeval.model.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the QUESTIONS database table.
 * 
 */
@Entity
@Table(name = "QUESTIONS")
@NamedQuery(name = "Question.findAll", query = "SELECT q FROM Question q")
public class Question implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String type;
	private String status;
	private FlowConfig flowConfig;
	private FlowPage flowPage;
	private PageSection pageSection;

	public Question() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "QUESTION_NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "QUESTION_TYPE")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	// bi-directional one-to-one association to FlowConfig
//	@ManyToOne
//	@JoinColumn(name = "FLOW_ID")
	@Transient
	public FlowConfig getFlowConfig() {
		return this.flowConfig;
	}

	public void setFlowConfig(FlowConfig flowConfig) {
		this.flowConfig = flowConfig;
	}

	// bi-directional one-to-one association to FlowPage
//	@ManyToOne
//	@JoinColumn(name = "PAGE_ID")
	@Transient
	public FlowPage getFlowPage() {
		return this.flowPage;
	}

	public void setFlowPage(FlowPage flowPage) {
		this.flowPage = flowPage;
	}

	@ManyToOne
	@JoinColumn(name = "SECTION_ID")
	public PageSection getPageSection() {
		return pageSection;
	}

	public void setPageSection(PageSection pageSection) {
		this.pageSection = pageSection;
	}

}