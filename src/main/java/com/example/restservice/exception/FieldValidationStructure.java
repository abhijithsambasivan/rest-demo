package com.example.restservice.exception;

import java.util.Date;
import java.util.List;

public class FieldValidationStructure {

	private Date timestamp;
	private List<String> message;

	
	public List<String> getMessage() {
		return message;
	}
	public void setMessage(List<String> message) {
		this.message = message;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	
	
}
