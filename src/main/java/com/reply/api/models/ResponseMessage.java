package com.reply.api.models;

import org.springframework.http.HttpStatus;

public class ResponseMessage {

	private HttpStatus httpStatus;
	
	private String errorMessage;
	
	private Object payload;

	public ResponseMessage(HttpStatus httpStatus, String errorMessage, Object payload) {
		super();
		this.httpStatus = httpStatus;
		this.errorMessage = errorMessage;
		this.payload = payload;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Object getPayload() {
		return payload;
	}

	public void setPayload(Object payload) {
		this.payload = payload;
	}
	
	
}
