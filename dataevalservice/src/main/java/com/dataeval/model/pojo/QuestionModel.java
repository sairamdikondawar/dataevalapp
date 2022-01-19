package com.dataeval.model.pojo;

import java.io.Serializable;

import lombok.Data;

@Data
public class QuestionModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3868664478168703881L;
	private Integer id;
	private Integer flowId;
	private Integer pageId;
	private String name;
	private String type;
	private String status;
	private FlowConfigModel flowConfig;
	private FlowPageModel flowPage;

}
