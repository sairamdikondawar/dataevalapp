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
	private PageSection section;
	private Integer sequence;

	private Boolean required = false;
	private Boolean readonly = false;

	private String answer;

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

	@Column(name = "REQUIRED")
	public Boolean getRequired() {
		return required;
	}

	public void setRequired(Boolean required) {
		this.required = required;
	}

	@Transient
	public Boolean getReadonly() {
		return readonly;
	}

	public void setReadonly(Boolean readonly) {
		this.readonly = readonly;
	}

	@ManyToOne
	@JoinColumn(name = "SECTION_ID")
	public PageSection getSection() {
		return section;
	}

	public void setSection(PageSection section) {
		this.section = section;
	}

	@Transient
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Column(name="SEQUENCE")
	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

}