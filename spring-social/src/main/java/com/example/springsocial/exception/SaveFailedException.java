package com.example.springsocial.exception;

public class SaveFailedException extends RuntimeException{
    public SaveFailedException(String message) {
        super("An error was encountered while saving");
    }
}
