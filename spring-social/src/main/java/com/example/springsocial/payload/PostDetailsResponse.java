package com.example.springsocial.payload;

import com.example.springsocial.model.Category;
import com.example.springsocial.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PostDetailsResponse {
    private Long postId;
    private String postAuthorName;
    private String postTitle;
    private String postContent;
    private Date postReleaseDate;
    private Category postCategory;
    private List<CommentDetailsResponse> postComments;
}
