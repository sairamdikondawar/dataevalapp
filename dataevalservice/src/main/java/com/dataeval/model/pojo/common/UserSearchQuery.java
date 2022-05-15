package com.dataeval.model.pojo.common;

import lombok.Data;

@Data
public class UserSearchQuery extends CommonCriteria {

	private String roleName;

	private String userName;
	
	private String firstName;
	
	private String lastName;

	public String getRoleName() {
		return roleName == null || roleName.isEmpty() ? "PATIENT" : roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getUserName() {
		return userName == null || userName.isEmpty() ? null : userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
