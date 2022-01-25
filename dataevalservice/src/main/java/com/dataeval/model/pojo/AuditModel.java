package com.dataeval.model.pojo;


import java.util.Date;

import lombok.Data;

@Data
public class AuditModel {

	private Date creationDate;

	private String createdBy;

	private String updatedBy;

	private Date updatedDate;

}
