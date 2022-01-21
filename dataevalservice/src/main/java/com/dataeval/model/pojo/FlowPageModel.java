package com.dataeval.model.pojo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FlowPageModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -666763502709039865L;
	private Integer id;
	private Integer flowId;

	@JsonProperty("label")
	private String name;
	private Integer sequence;
	private QuestionModel question;
	private Integer layoutColumns;
	private List<PageSectionModel> sections;

}
