package com.kosign.phone_shop.exeption;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends ApiException{

//    public ResourceNotFoundException(HttpStatus status, String message) {
//        super(status, message);
//    }

    public ResourceNotFoundException(String resourceName, Integer id) {
        super(HttpStatus.NOT_FOUND, String.format("%s with id = %d is not found", resourceName,id));
    }
}
