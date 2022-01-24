package com.dataeval.model.pojo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserQuestionModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5252395588053979018L;
	private Integer id;
	private String answer;
	@JsonProperty("label")
	private String name;
	private String type;
	private boolean readonly=true;
	private UserSectionModel userSection;
	private Integer questionId;
	private boolean required=false;
	
	@SuppressWarnings("unused")
	private String controlName;


	public String getControlName() {

		return "field" + id;
	}

	public void setControlName(String controlName) {
		this.controlName = controlName;
	}

	public UserQuestionModel() {
	}

}