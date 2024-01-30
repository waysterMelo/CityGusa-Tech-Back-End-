package com.citygusa.citygusatech.Services.ExceptionsService;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message){
        super(message);
    }
}
