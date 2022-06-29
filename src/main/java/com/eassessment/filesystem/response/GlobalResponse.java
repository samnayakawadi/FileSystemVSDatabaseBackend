package com.eassessment.filesystem.response;

public class GlobalResponse {
	private Boolean status = false;
	private String msg = "Error";
	
	public GlobalResponse(Boolean status, String msg) {
		super();
		this.status = status;
		this.msg = msg;
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