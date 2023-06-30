package com.main.user.service.exceptions;

public class UserIdNotFoundException extends RuntimeException{
	
	public UserIdNotFoundException(){
		super("UserNotFoundException");
	}
	
    public UserIdNotFoundException(String message){
		super(message);
	}

}
