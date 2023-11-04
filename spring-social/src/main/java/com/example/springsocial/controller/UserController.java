package com.example.springsocial.controller;

import com.example.springsocial.exception.ResourceNotFoundException;
import com.example.springsocial.model.Post;
import com.example.springsocial.model.User;
import com.example.springsocial.payload.CurrentUserResponse;
import com.example.springsocial.payload.PostRequest;
import com.example.springsocial.payload.PostResponse;
import com.example.springsocial.repository.UserRepository;
import com.example.springsocial.security.CurrentUser;
import com.example.springsocial.security.UserPrincipal;
import com.example.springsocial.service.PostService;
import com.example.springsocial.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;

@RestController
@SecurityRequirement(name = "bearerAuth")
@AllArgsConstructor
public class UserController {


    private final UserService userService;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public CurrentUserResponse getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userService.showCurrentUser(userPrincipal.getId());
    }


}
