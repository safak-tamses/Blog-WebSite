package com.example.springsocial.payload;

import com.example.springsocial.model.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PostRequest {
    private String title;

    private String content;

    private Category category;
}
