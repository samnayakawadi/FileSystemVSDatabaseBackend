package com.eassessment.filesystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.eassessment.filesystem.response.GlobalResponse;

// This is global exception handler i.e. It is responsible to handler the exceptions globally.
// It just requires a exception class that extends the runtime exception
@ControllerAdvice
public class QuestionExceptionController {

	@ExceptionHandler(value= QuestionNotFoundException.class)
	public ResponseEntity<Object> exception(QuestionNotFoundException e){
		return new ResponseEntity<Object>(new GlobalResponse(false, "Question Not Found"), HttpStatus.NOT_FOUND); // The first parameter is body. We have created new GlobalResponse object & passed desired values. Second parameter is status code
	}
}
