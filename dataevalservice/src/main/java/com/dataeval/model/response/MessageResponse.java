package com.dataeval.model.response;
public class MessageResponse<T> {

	String message;
	T responseObject;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getResponseObject() {
		return responseObject;
	}

	public void setResponseObject(T responseObject) {
		this.responseObject = responseObject;
	}
}