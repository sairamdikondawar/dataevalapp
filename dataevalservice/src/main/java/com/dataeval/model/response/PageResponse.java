package com.dataeval.model.response;
public class PageResponse<T> extends SuccessResponse<T> {

	private Integer totalrecords;
	private int totalTaggedRecords;

	public Integer getTotalrecords() {
		return totalrecords;
	}

	public void setTotalrecords(Integer totalrecords) {
		this.totalrecords = totalrecords;
	}

	public int getTotalTaggedRecords() {
		return totalTaggedRecords;
	}

	public void setTotalTaggedRecords(int totalTaggedRecords) {
		this.totalTaggedRecords = totalTaggedRecords;
	}

}
