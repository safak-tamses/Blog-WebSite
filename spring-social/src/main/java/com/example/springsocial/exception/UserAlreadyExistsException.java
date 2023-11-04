package com.example.springsocial.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException() {
        super("Username already exists");
    }
}
