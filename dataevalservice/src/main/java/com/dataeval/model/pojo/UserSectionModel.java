package com.dataeval.model.pojo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserSectionModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4401381008191967166L;
	
	private Integer id;
	@JsonProperty("label")
	private String name;
	private Integer sequence;
	private UserPageModel userPage;
	private Integer layout;
	@JsonProperty("fields")
	private List<UserQuestionModel> userQuestions;

	public UserSectionModel() {
	}

	 

}