package com.dataeval.model.pojo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class UserFormModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4492944135470794508L;
	private Integer id;
	private UserModel user;
	private List<UserPageModel> userPages;

	public UserFormModel() {
	}

}