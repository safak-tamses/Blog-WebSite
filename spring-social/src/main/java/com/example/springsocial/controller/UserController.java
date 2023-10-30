package com.example.springsocial.controller;

import com.example.springsocial.exception.ResourceNotFoundException;
import com.example.springsocial.model.Post;
import com.example.springsocial.model.User;
import com.example.springsocial.payload.PostRequest;
import com.example.springsocial.repository.UserRepository;
import com.example.springsocial.security.CurrentUser;
import com.example.springsocial.security.UserPrincipal;
import com.example.springsocial.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;

@RestController
@SecurityRequirement(name = "bearerAuth")
@AllArgsConstructor
public class UserController {


    private final UserRepository userRepository;
    private final PostService postService;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
    @PostMapping("/user/post/add")
    @PreAuthorize("hasRole('USER')")
    public Post addPost(@CurrentUser UserPrincipal userPrincipal,@RequestBody PostRequest postRequest){
        return postService.addPost(userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId())), postRequest);
    }

    @GetMapping("/user/post/show")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Post>> getPostsByReleaseDate(
            @RequestParam(name = "pageNumber", defaultValue = "1") int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "8") int pageSize) {
        return ResponseEntity.ok(postService.getPostsByReleaseDate(pageNumber,pageSize));
    }
}
