package com.dataeval.model.pojo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
@Data
public class UserPageModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7004020936398095934L;
	private Integer id;
	private Integer layout;
	private String name;
	private Integer sequence;
	private UserFormModel userForm;;
	private List<UserSectionModel> userSections;

	public UserPageModel() {
	}

}