package com.example.springsocial.controller;

import com.example.springsocial.payload.CommentRequest;
import com.example.springsocial.security.CurrentUser;
import com.example.springsocial.security.UserPrincipal;
import com.example.springsocial.service.CommentService;
import com.example.springsocial.util.GenericResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name = "bearerAuth")
@AllArgsConstructor
@RequestMapping("user/comment/")
public class CommentController {
    private final CommentService commentService;
    @PostMapping("add")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<GenericResponse> addComment(@CurrentUser UserPrincipal userPrincipal,
                                                      @RequestBody CommentRequest commentRequest
                                                      ){
        return new ResponseEntity<>(commentService.addComment(userPrincipal.getId(),commentRequest), HttpStatus.OK);
    }

    @GetMapping("show")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<GenericResponse> showCommentsForPost(Long postId,
                                                               @RequestParam(defaultValue = "0") int pageNumber,
                                                               @RequestParam(defaultValue = "8") int pageSize){
        return new ResponseEntity<>(commentService.seeTheCommentOfThePost(postId,pageNumber,pageSize),HttpStatus.OK);
    }

}
