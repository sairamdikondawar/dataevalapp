package com.dataeval.model.response;

public class Response implements CommonResponse {
	private String message;
	private String locale = "en";

	public Response() {
	}

	public Response(String message) {
		this.message = message;
	}

	public String getLocale() {
		return locale;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
