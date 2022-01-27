package com.dataeval.model.pojo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3868664478168703881L;
	private Integer id;
	private Integer flowId;
	private Integer pageId;
	@JsonAlias({ "label" })
	@JsonProperty("label")
	private String name;
	private String type;
	private String status;
	private FlowConfigModel flowConfig;
	private FlowPageModel flowPage;

	private Boolean required;
	private Boolean readonly;

	private String answer;
	private Integer sequence;

	@SuppressWarnings("unused")
	private String controlName;

	private PageSectionModel section;
	
	private List<String> userTypesList;

	public String getControlName() {

		return "field" + id;
	}

	public void setControlName(String controlName) {
		this.controlName = controlName;
	}

}
