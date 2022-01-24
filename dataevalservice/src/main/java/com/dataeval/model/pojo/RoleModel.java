package com.dataeval.model.pojo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class RoleModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 930144606258826073L;

	private Integer id;
	private String roleDesc;
	
	@JsonProperty("name")
	private String roleName;

}
