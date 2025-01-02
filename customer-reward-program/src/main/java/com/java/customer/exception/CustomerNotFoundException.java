package com.java.customer.exception;

/**
 * 
 * @author mmasali
 *
 */
public class CustomerNotFoundException extends RuntimeException {
 
	private static final long serialVersionUID = 1L;
	private String message;

	public CustomerNotFoundException(String message) {
		super(message);
		this.message = message;
	}

	public CustomerNotFoundException() {
	}
}