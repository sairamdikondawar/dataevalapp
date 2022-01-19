package com.dataeval.model.pojo;

import java.io.Serializable;

import lombok.Data;

@Data
public class QuestionTypeModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8334462747351768635L;

	private Integer id;
	private String description;
	private String questionType;
	private String status;

}
