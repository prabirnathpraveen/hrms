package com.main.hrms.common;

public class AccessDeniedException extends RuntimeException{
    
	private static final long serialVersionUID = 1L;

	public AccessDeniedException(String message){
        super(message);
    }
    
}
