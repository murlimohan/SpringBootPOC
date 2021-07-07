package com.bookMyShowExample.demo.exception;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BusinessExceptionStructure{

	private int errorCode;
	@JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	private String url;
	private String errorMessage;

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public BusinessExceptionStructure(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	public BusinessExceptionStructure() {
	}
	
}
