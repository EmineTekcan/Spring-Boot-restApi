package com.demos.ILService.exception;

public class IlAlreadyExistException extends RuntimeException{
    public IlAlreadyExistException(String msg){
        super(msg);
    }
}
