package com.mail.exceptionHandler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mail.entity.Result;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(CustomeException.class)
	public ResponseEntity<Result>response(CustomeException ex)
	{
		
	//	Map<String,Object>response=new HashMap<>();
		
		Result result=new Result();
		result.setResponse(ex.getMessage());
		
		
		result.setStatus(String.valueOf(HttpStatus.BAD_REQUEST));
		
		return ResponseEntity.ok(result);
		
	}

}
