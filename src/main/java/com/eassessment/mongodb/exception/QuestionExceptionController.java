package com.eassessment.mongodb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.eassessment.mongodb.response.GlobalResponse;

@ControllerAdvice
public class QuestionExceptionController {
	
	@ExceptionHandler(value= QuestionNotFoundException.class)
	public ResponseEntity<Object> exception(QuestionNotFoundException e){
		return new ResponseEntity<Object>(new GlobalResponse(false, "Question Not Found"), HttpStatus.NOT_FOUND); // The first parameter is body. We have created new GlobalResponse object & passed desired values. Second parameter is status code
	}

}
