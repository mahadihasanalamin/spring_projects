package com.practice.firstspringboot.error;

public class ProductNotFoundExceptionHandler extends Exception{
    public ProductNotFoundExceptionHandler(String message) {
        super(message);
    }
}
