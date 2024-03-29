package com.sanrio_store.sanrio_store.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException
{

    private static final long serialVersion = 1L;
    public ResourceNotFoundException(String message){
        super(message);
        System.out.println(message);

    }
}
