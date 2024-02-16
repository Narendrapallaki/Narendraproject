package com.jdbc.CustomExceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.jdbc.Entity.Result;

@ControllerAdvice
public class GlobalExceptionHandler {

	/*
	 * @ExceptionHandler(RecordNotFoundException.class) public
	 * ResponseEntity<Map<String, Object>>
	 * handleRecordNotFoundException(RecordNotFoundException ex) { Map<String,
	 * Object> response = new HashMap<>(); response.put("message", ex.getMessage());
	 * response.put("status", HttpStatus.NOT_FOUND); return new
	 * ResponseEntity<>(response, HttpStatus.NOT_FOUND); }
	 */

	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<Result> hl(RecordNotFoundException ex) {

		Result r1 = new Result();

		r1.setResponse(ex.getMessage());
		r1.setStatus(String.valueOf(HttpStatus.NOT_FOUND));
		return ResponseEntity.ok(r1);
	}

}
