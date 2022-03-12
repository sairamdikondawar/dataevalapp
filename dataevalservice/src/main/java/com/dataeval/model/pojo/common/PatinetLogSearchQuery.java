package com.dataeval.model.pojo.common;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PatinetLogSearchQuery extends CommonCriteria {

	private String startDate;

	private String endDate;

	private String callType;
	
	private String patientName;

}
