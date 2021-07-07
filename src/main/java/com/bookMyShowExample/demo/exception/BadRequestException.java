package com.bookMyShowExample.demo.exception;

public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = 1770352510806321968L;
	int errorCode;
	String errormessage;

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrormessage() {
		return errormessage;
	}

	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}

	public BadRequestException(String errormessage) {
		this.errormessage = errormessage;
	}
}
