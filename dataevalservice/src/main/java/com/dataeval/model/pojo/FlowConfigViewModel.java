package com.dataeval.model.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class FlowConfigViewModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4946583103190257760L;
	private Integer id;
	private String createdBy;
	private Date createdDate;
	private String flowName;
	private RoleModel role;

}
