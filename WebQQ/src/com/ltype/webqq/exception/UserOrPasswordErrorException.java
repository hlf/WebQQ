package com.ltype.webqq.exception;

public class UserOrPasswordErrorException extends WebQQException{
	public UserOrPasswordErrorException(){
		super();
	}
	public UserOrPasswordErrorException(String message){
		super(message);
	}	
}