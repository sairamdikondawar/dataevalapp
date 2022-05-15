package com.dataeval.model.pojo.common;

import lombok.Data;

@Data
public class PatientSearchCriteria extends CommonCriteria {

	private String userName;
	private String firstName;
	private String lastName;

}
