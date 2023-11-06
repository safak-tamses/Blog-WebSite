package com.example.springsocial.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class CommentDetailsResponse {
    private String commentAuthorName;
    private String commentContent;
    private Date commentDate;
}
