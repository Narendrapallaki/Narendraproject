package com.Image.customeException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.Image.Entity.Result;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(IdNotFound.class)
	public ResponseEntity<Result>response(IdNotFound ex)
	{
		
		Result r1=new Result();
		r1.setResult(ex.getMessage());
		r1.setStatus(String.valueOf(HttpStatus.NOT_FOUND));
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(r1);
		
	}
	
	
	@ExceptionHandler(Unsupportedfileformat.class)
	public ResponseEntity<Result>unsupportedFileExtension(Unsupportedfileformat ex)
	{
		
		Result r1=new Result();
		r1.setResult(ex.getMessage());
		r1.setStatus(String.valueOf(HttpStatus.NOT_FOUND));
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(r1);
		
	}
	
	

}
