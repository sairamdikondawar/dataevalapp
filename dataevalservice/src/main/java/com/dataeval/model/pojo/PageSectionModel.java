package com.dataeval.model.pojo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PageSectionModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8741149708203695938L;
	private Integer id;
	private String description;
	@JsonProperty("label")
	private String name;
	private String status;
	@JsonProperty("fields")
	private List<QuestionModel> questions;
}
