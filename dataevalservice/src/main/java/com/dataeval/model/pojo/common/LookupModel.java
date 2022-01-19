package com.dataeval.model.pojo.common;

import java.io.Serializable;

import lombok.Data;

@Data
public class LookupModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 511992332920284646L;

	private String name;

	private Integer id;

	public LookupModel() {

	}

	public LookupModel(String name, Integer id) {

		this.name = name;
		this.id = id;

	}

}
