package com.example.springsocial.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CommentRequest {
    private Long postId;
    private String comment;
}
