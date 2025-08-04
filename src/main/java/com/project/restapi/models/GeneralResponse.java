package com.project.restapi.models;

public class GeneralResponse {
 

	private boolean status;
	private String message;
	private Object data;
	private String token;

	public GeneralResponse() {
		super();
	}
	
	public GeneralResponse(boolean status, String message) {
		this.status = status;
		this.message = message;

	}

	public GeneralResponse(boolean status, String message, Object data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public GeneralResponse(boolean status, String message, String token) {
		super();
		this.status = status;
		this.message = message;
		this.token = token;
	}

	public GeneralResponse(boolean status, String message, Object data, String token) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
		this.token = token;
	}

	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}


}
