package com.example.demo.Exception;

public class DuplicateCarRentalException extends RuntimeException {
    public DuplicateCarRentalException(String message){
        super(message);
    }
}
