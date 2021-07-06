package com.bookMyShowExample.demo.exception;

public class NoDataFoundException extends RuntimeException{

	private static final long serialVersionUID = 1187989447964869978L;
	
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


	public NoDataFoundException(String errormessage) {
		this.errormessage=errormessage;
	}
}
