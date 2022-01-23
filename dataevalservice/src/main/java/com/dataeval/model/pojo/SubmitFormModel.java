package com.dataeval.model.pojo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class SubmitFormModel implements Serializable {

	private static final long serialVersionUID = -8008339837365036141L;

	List<QuestionModel> userQuestions;

}
