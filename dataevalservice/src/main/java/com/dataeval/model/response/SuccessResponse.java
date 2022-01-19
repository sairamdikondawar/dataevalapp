package com.dataeval.model.response;

import java.util.ArrayList;
import java.util.List;

public class SuccessResponse<T> implements SecuredResponse {
	private final String max = "30";
	private long totalCount = 0;
	private T list;
	private List<String> enabledComponentIds = new ArrayList<String>();

	public String getMax() {
		return max;
	}

	public void setList(T list) {
		this.list = list;
	}

	public T getList() {
		return list;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public List<String> getEnabledComponentIds() {
		return enabledComponentIds;
	}

	public void setEnabledComponentIds(List<String> enabledComponentIds) {
		if (enabledComponentIds != null)
			this.enabledComponentIds = enabledComponentIds;
	}

}
