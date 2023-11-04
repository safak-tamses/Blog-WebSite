package com.example.springsocial.exception;

public class UserNotFound extends RuntimeException{
    public UserNotFound(String message) {
        super("User not found!");
    }
}
