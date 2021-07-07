package com.bookMyShowExample.demo.exception.exceptionHandler;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bookMyShowExample.demo.exception.BadRequestException;
import com.bookMyShowExample.demo.exception.BusinessExceptionStructure;
import com.bookMyShowExample.demo.exception.NoDataFoundException;

@ControllerAdvice 
public class ExceptionHandlerController{
	
	@ExceptionHandler(NoDataFoundException.class)
	public ResponseEntity<BusinessExceptionStructure> handleNoDataException(HttpServletRequest request, NoDataFoundException ex){
			
		    BusinessExceptionStructure response = new BusinessExceptionStructure();
			response.setErrorCode(HttpStatus.NOT_FOUND.value());
			response.setTimestamp(LocalDateTime.now());
			response.setErrorMessage(ex.getErrormessage());
			response.setUrl(request.getRequestURL().toString());
			
			return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		}
	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<BusinessExceptionStructure> handleBadRequestException(HttpServletRequest request, BadRequestException ex){
			
		    BusinessExceptionStructure response = new BusinessExceptionStructure();
			response.setErrorCode(HttpStatus.BAD_REQUEST.value());
			response.setTimestamp(LocalDateTime.now());
			response.setErrorMessage(ex.getErrormessage());
			response.setUrl(request.getRequestURL().toString());
			
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}

}
