package com.dataeval.model.pojo.common;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserFormsQuery extends CommonCriteria {

	private String startDate;

	private String endDate;

	private Integer roleName;
	
	private String userName;

}
