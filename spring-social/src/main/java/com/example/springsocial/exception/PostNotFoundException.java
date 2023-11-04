package com.example.springsocial.exception;

public class PostNotFoundException extends RuntimeException{
    public PostNotFoundException(String message) {
        super("Post not found.");
    }
}
