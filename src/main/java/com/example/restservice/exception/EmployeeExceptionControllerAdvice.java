package com.example.restservice.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeExceptionControllerAdvice {

	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<Object> handleNotFound(EmployeeNotFoundException ex) {
		EmployeeException empEx = new EmployeeException();
		empEx.setMessage(ex.getMessage());
		empEx.setStatus(HttpStatus.NOT_FOUND);
		empEx.setDate(new Date());
		return new ResponseEntity<Object>(empEx, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(EmployeeAlreadyPresentException.class)
	public ResponseEntity<Object> handleAlreadyExisting(EmployeeAlreadyPresentException ex) {
		EmployeeException empEx = new EmployeeException();
		empEx.setMessage(ex.getMessage());

		empEx.setStatus(HttpStatus.NOT_ACCEPTABLE);
		empEx.setDate(new Date());
		return new ResponseEntity<Object>(empEx, HttpStatus.NOT_ACCEPTABLE);

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleField(MethodArgumentNotValidException ex) {
		List<String> errorStr = new ArrayList<String>();
		FieldValidationStructure fv = new FieldValidationStructure();
		ex.getAllErrors().forEach(error -> {
			errorStr.add(error.getDefaultMessage());
		});

		fv.setTimestamp(new Date());
		fv.setMessage(errorStr);

		return new ResponseEntity<Object>(fv, HttpStatus.BAD_REQUEST);

	}

}
