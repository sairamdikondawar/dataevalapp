package com.dataeval.model.response;

import java.util.ArrayList;
import java.util.List;

public class EmptySuccessResponse extends Response implements SecuredResponse {
	private int status = 0;
	private Object resource;
	private List<String> enabledComponentIds = new ArrayList<String>();

	public EmptySuccessResponse(String message) {
		super(message);
	}

	public int getStatus() {
		return status;
	}

	public void setResource(Object resource) {
		this.resource = resource;
	}

	public Object getResource() {
		return resource;
	}

	public List<String> getEnabledComponentIds() {
		return enabledComponentIds;
	}

	public void setEnabledComponentIds(List<String> enabledComponentIds) {
		if (enabledComponentIds != null)
			this.enabledComponentIds = enabledComponentIds;
	}
}
