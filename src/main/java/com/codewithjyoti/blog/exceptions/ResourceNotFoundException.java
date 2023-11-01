package com.codewithjyoti.blog.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String s){
        super(String.format("Resource with id %s not found", s));
    }

}
