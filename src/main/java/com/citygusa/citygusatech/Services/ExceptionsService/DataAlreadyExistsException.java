package com.citygusa.citygusatech.Services.ExceptionsService;

public class DataAlreadyExistsException extends RuntimeException {
    public DataAlreadyExistsException(String s) {
        super(s);
    }
}
