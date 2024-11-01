package com.bbva.IntegradorJava.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;


@Getter
public class ExceptionsTP extends RuntimeException {
	    private final HttpStatus status;

	    public ExceptionsTP(final HttpStatus httpStatus) {
	        this.status = httpStatus;
	    }

	    public ExceptionsTP(final HttpStatus httpStatus, final String message) {
	        super(message);
	        this.status = httpStatus;
	    }

		public HttpStatus getStatus() {
			return status;
		}
		
		

	    
	    
	}
 
	
