package com.dataeval.model.pojo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
public class UserPageModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7004020936398095934L;
	private Integer id;
	private Integer layout;
	@JsonProperty("label")
	private String name;
	private Integer sequence;
	private UserFormModel userForm;;
	@JsonProperty("sections")
	private List<UserSectionModel> userSections;

	public UserPageModel() {
	}

}