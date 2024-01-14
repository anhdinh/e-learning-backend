package com.andy.elearning.exeptions;

public class UsernameHasAlreadyExistedException extends RuntimeException{
    public UsernameHasAlreadyExistedException(String message){
        super(message);
    }
}
