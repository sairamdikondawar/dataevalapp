package com.dataeval.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.OrderBy;
import org.hibernate.annotations.Where;


/**
 * The persistent class for the PAGE_SECTIONS database table.
 * 
 */
@Entity
@Table(name="PAGE_SECTIONS")
@NamedQuery(name="PageSection.findAll", query="SELECT p FROM PageSection p")
public class PageSection implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String description;
	private String name;
	private String status;
	private Integer sequence;
	private FlowPage page;
	private List<Question> questions;
	private Integer layout=1;

	public PageSection() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

   @ManyToOne
   @JoinColumn(name = "PAGE_ID")
	public FlowPage getPage() {
		return page;
	}


	public void setPage(FlowPage page) {
		this.page = page;
	}


	@OneToMany(mappedBy = "section")
	@Where(clause = "lower(status) like 'active'")
	@OrderBy(clause = "sequence asc")
	public List<Question> getQuestions() {
		return questions;
	}


	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}


	@Column(name = "LAYOUT")
	public Integer getLayout() {
		return layout;
	}


	public void setLayout(Integer layout) {
		this.layout = layout;
	}


	@Column(name = "SEQUENCE")
	public Integer getSequence() {
		return sequence;
	}


	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

}