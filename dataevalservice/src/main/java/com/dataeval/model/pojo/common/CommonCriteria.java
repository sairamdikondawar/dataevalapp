package com.dataeval.model.pojo.common;

import java.util.List;

import lombok.Data;

@Data
public class CommonCriteria {

	private Integer page = 0;

	private Integer size = 12;
	
	private String columnName;
	
	private Integer order;

	private List<SortInfo> sort;

	public List<SortInfo> getSort() {
		return sort;
	}

	public void setSort(List<SortInfo> sort) {
		this.sort = sort;
	}

}
