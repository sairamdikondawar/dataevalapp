package com.dataeval.model.pojo;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserQuestionModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5252395588053979018L;
	private Integer id;
	private String answer;
	private String name;
	private String type;
	private UserSectionModel userSection;
	private Integer questionId;

	public UserQuestionModel() {
	}

}