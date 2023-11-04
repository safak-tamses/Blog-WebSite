package com.example.springsocial.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CurrentUserResponse {
    private String name;
    private String email;
    private String imageUrl;
}
