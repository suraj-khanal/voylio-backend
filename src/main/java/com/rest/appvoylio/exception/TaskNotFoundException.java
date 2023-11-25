package com.rest.appvoylio.exception;

//This is a custom exception
//this is unchecked exception
public class TaskNotFoundException extends RuntimeException{
	
	public TaskNotFoundException(String message){
		super(message);
	}
}