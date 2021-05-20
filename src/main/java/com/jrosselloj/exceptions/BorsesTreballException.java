package com.jrosselloj.exceptions;


public class BorsesTreballException extends Exception {
	
	private static final long serialVersionUID = 4193278326752445356L;
	
	private String message;
	
	public BorsesTreballException(String message) {
		super();
		this.message = message;
	}
	
	
	public String getMessage() {
		return message;
	}
	
	
	
}
