package com.dataeval.model.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include. NON_NULL)
public class FlowConfigModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6492481035788008043L;
	private Integer id;
	private String createdBy;
	private Date createdDate;
	private String flowName;
	private RoleModel role;
	private List<QuestionModel> questions;

}
