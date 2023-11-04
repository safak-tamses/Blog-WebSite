package com.example.springsocial.payload;

import com.example.springsocial.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class PostResponse {
    private String contentAuthorName;
    private String contentTitle;
    private String content;
    private Date contentReleaseDate;
    private Category contentCategory;
    private Integer numberOfCommentsOfTheContent;
    private Long contentId;
}
