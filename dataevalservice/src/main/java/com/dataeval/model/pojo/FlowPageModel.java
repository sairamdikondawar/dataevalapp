package com.dataeval.model.pojo;

import java.io.Serializable;

import com.dataeval.model.entity.Question;

import lombok.Data;

@Data
public class FlowPageModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -666763502709039865L;
	private Integer id;
	private Integer flowId;
	private String name;
	private Integer sequence;
	private QuestionModel question;
	private Integer layoutColumns;

}
