package com.dataeval.model.pojo.common;

import java.io.Serializable;

import lombok.Data;

@Data
public class SortInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2002439596188189847L;
	String columnName;
	Integer order;
}