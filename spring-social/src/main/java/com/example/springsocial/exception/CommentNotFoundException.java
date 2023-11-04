package com.example.springsocial.exception;

public class CommentNotFoundException extends RuntimeException{
    public CommentNotFoundException(String message) {
        super("Comment not found");
    }
}
