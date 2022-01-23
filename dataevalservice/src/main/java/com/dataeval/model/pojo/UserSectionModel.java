package com.dataeval.model.pojo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class UserSectionModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4401381008191967166L;
	
	private Integer id;
	private String name;
	private Integer sequence;
	private UserPageModel userPage;
	private List<UserQuestionModel> userQuestions;

	public UserSectionModel() {
	}

	 

}