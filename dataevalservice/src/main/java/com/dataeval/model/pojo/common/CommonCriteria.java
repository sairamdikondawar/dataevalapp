package com.dataeval.model.pojo.common;

import java.util.List;

import lombok.Data;

@Data
public class CommonCriteria {

	private Integer page = 1;

	private Integer size = 10;

	private List<OrderInfo> sort;

	public List<OrderInfo> getSort() {
		return sort;
	}

	public void setSort(List<OrderInfo> sort) {
		this.sort = sort;
	}

}
