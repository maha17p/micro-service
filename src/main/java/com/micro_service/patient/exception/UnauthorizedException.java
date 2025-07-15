package com.micro_service.patient.exception;

public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException(String  message){
        super(message);
    }
}
