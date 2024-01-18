package com.andy.elearning.exeptions;

public class InvalidJwtTokenException extends RuntimeException{
    public InvalidJwtTokenException(String message){
        super(message);
    }
}
