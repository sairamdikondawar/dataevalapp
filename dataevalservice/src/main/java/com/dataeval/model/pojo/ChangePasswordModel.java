package com.dataeval.model.pojo;

import lombok.Data;

@Data
public class ChangePasswordModel {
	
	private String currentPassword;
	
	private String newPassword;
	
	private String confirmPassword;
	

}
