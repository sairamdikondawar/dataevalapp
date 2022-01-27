package com.dataeval.model.pojo.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class QuestionCriteria extends CommonCriteria {
	
	private Integer sectionId; 
	private String qName;

}
