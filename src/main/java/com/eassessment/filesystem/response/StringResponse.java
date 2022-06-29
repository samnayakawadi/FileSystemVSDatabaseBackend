package com.eassessment.filesystem.response;

public class StringResponse {

	private String id;
	private Boolean status;
	private String msg;
	
	public StringResponse() {
		super();
	}
	public StringResponse(String id, Boolean status, String msg) {
		super();
		this.id = id;
		this.status = status;
		this.msg = msg;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

}
