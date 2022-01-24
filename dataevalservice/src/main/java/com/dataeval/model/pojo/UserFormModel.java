package com.dataeval.model.pojo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class UserFormModel extends AuditModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4492944135470794508L;

	private Integer id;

	private UserModel user;

	private List<UserPageModel> userPages;

	public UserFormModel() {
	}

}