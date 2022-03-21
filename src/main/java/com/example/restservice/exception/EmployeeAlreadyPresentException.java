package com.example.restservice.exception;

public class EmployeeAlreadyPresentException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3107106664675992853L;

	public EmployeeAlreadyPresentException(String message) {
		super(message);

	}



}
