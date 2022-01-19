package com.dataeval.model.response;

import java.util.Map;

public class ErrorResponse extends Response implements CommonResponse {
	private int status = 1;
	private Map<String, String> errorMessages;

	public ErrorResponse(String message) {
		super(message);
	}

	public int getStatus() {
		return status;
	}

	public void setErrorMessages(Map<String, String> errorMessages) {
		this.errorMessages = errorMessages;
	}

	public Map<String, String> getErrorMessages() {
		return errorMessages;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
