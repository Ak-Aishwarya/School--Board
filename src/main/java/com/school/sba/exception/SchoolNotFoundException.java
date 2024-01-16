package com.school.sba.exception;

public class SchoolNotFoundException extends RuntimeException{

	private String message;

	public SchoolNotFoundException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}


}
