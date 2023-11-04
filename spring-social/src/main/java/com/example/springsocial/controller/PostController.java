package com.example.springsocial.controller;

import com.example.springsocial.exception.ResourceNotFoundException;
import com.example.springsocial.model.Post;
import com.example.springsocial.payload.PostRequest;
import com.example.springsocial.payload.PostResponse;
import com.example.springsocial.security.CurrentUser;
import com.example.springsocial.security.UserPrincipal;
import com.example.springsocial.service.PostService;
import com.example.springsocial.util.GenericResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user/post/")
public class PostController {
    private final PostService postService;

    @PostMapping("add")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<GenericResponse> addPost(@CurrentUser UserPrincipal userPrincipal, @RequestBody PostRequest postRequest) {
        return new ResponseEntity<>(postService.addPost(userPrincipal.getId(), postRequest), HttpStatus.OK);
    }

    @GetMapping("show")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<GenericResponse<List<String>>> getPosts(@RequestParam int pageNumber, @RequestParam int pageSize) {
        return new ResponseEntity<>(postService.getPosts(pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("shows")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<GenericResponse<List<PostResponse>>> getPaginatedPosts(@RequestParam(defaultValue = "0") int pageNumber,
                                                                @RequestParam(defaultValue = "8") int pageSize) {
        return new ResponseEntity<>(postService.getPaginatedPosts(pageNumber, pageSize),HttpStatus.OK);
    }
    @GetMapping("show/own")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<GenericResponse<List<PostResponse>>> getOwnPosts(@CurrentUser UserPrincipal userPrincipal,
                                                                           @RequestParam(defaultValue = "0") int pageNumber,
                                                                           @RequestParam(defaultValue = "8") int pageSize){
        return new ResponseEntity<>(postService.showOwnPosts(userPrincipal.getId(),pageNumber,pageSize),HttpStatus.OK);
    }

}
