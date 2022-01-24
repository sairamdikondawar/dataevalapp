package com.dataeval.model.pojo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9118891018788727984L;
	private Integer id;
	@JsonIgnore
	private String password;
	private String status;
	@JsonProperty(value = "name")
	private String userName;
	private RoleModel role;

}
