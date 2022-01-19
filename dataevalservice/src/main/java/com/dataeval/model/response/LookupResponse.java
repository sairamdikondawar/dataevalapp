package com.dataeval.model.response;

public class LookupResponse<T> {
	private T list;

	public void setList(T list) {
		this.list = list;
	}

	public T getList() {
		return list;
	}
}
