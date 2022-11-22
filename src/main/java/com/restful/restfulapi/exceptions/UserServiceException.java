package com.restful.restfulapi.exceptions;

public class UserServiceException extends RuntimeException{

    public UserServiceException(String message){
        super(message);
    }
}
