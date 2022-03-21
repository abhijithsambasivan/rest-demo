package com.example.restservice.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class EmployeeException {

	private String message;
	private HttpStatus status;
	private Date date;
	
	
	
	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
