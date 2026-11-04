package com.apibridge.auth.common;

public class UnauthorizeAccessException extends RuntimeException {
    public UnauthorizeAccessException(){
        super("You are not authorized to perform this operation!");
    }

    public UnauthorizeAccessException(String message){super(message);}
}
