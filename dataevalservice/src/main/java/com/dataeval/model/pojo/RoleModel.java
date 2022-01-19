package com.dataeval.model.pojo;

import java.io.Serializable;

import lombok.Data;

@Data
public class RoleModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 930144606258826073L;

	private Integer id;
	private String roleDesc;
	private String roleName;

}
