package com.bookMyShowExample.demo.exception.exceptionHandler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bookMyShowExample.demo.exception.BusinessException;
import com.bookMyShowExample.demo.exception.NoDataFoundException;

@ControllerAdvice 
public class ExceptionHandlerController{
	
	@ExceptionHandler(NoDataFoundException.class)
	public ResponseEntity<BusinessException> handleNoDataException(HttpServletRequest request, NoDataFoundException ex){
			
		    BusinessException response = new BusinessException();
			response.setErrorCode(HttpStatus.NOT_FOUND.value());
			response.setErrorMessage(ex.getErrormessage());
			response.setUrl(request.getRequestURL().toString());
			
			return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		}

}
