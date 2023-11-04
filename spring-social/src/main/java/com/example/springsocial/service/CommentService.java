package com.example.springsocial.service;

import com.example.springsocial.exception.CommentNotFoundException;
import com.example.springsocial.exception.SaveFailedException;
import com.example.springsocial.model.Comment;
import com.example.springsocial.model.Post;
import com.example.springsocial.model.User;
import com.example.springsocial.payload.CommentRequest;
import com.example.springsocial.payload.CommentResponse;
import com.example.springsocial.repository.CommentRepository;
import com.example.springsocial.util.GenericResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final PostService postService;

    public GenericResponse addComment(Long userId, CommentRequest commentRequest) {
        try {
            User u = userService.findUserById(userId);
            Post p = postService.findPostById(commentRequest.getPostId());
            Comment comment = Comment.builder()
                    .comment(commentRequest.getComment())
                    .commentDate(new Date())
                    .user(u)
                    .post(p)
                    .build();
            commentRepository.save(comment);
            return new GenericResponse<>("Comment saved succesfully.", Boolean.TRUE);
        } catch (Exception e) {
            throw new SaveFailedException("");
        }
    }

    public GenericResponse<List<CommentResponse>> seeTheCommentOfThePost(Long postId, int pageNumber, int pageSize) {
        try {
            List<Comment> commentList = commentRepository.findAllByPost_id(postId);
            List<Comment> sortedCommentList = commentList.stream()
                    .sorted((post1, post2) -> post2.getCommentDate().compareTo(post1.getCommentDate()))
                    .collect(Collectors.toList());
            int fromIndex = (pageNumber - 1) * pageSize;
            int toIndex = Math.min(fromIndex + pageSize, sortedCommentList.size());

            List<Comment> commentsOnPage = sortedCommentList.subList(fromIndex, toIndex);
            List<CommentResponse> commentResponseList = commentsOnPage.stream()
                    .map(comment -> new CommentResponse(
                            comment.getComment(),
                            comment.getPost().getId(),
                            comment.getUser().getId(),
                            comment.getCommentDate()
                    ))
                    .collect(Collectors.toList());
            return new GenericResponse<>(commentResponseList, Boolean.TRUE);

        } catch (Exception e) {
            throw new CommentNotFoundException("");
        }
    }

    public GenericResponse<List<CommentResponse>> commentsOwnPosts(Long postId, Long userId, int pageNumber, int pageSize) {
        try {
            List<Comment> commentList = commentRepository.findAllByPost_idAndUser_id(postId, userId);
            List<Comment> sortedCommentList = commentList.stream()
                    .sorted((post1, post2) -> post2.getCommentDate().compareTo(post1.getCommentDate()))
                    .collect(Collectors.toList());
            int fromIndex = (pageNumber - 1) * pageSize;
            int toIndex = Math.min(fromIndex + pageSize, sortedCommentList.size());

            List<Comment> commentsOnPage = sortedCommentList.subList(fromIndex, toIndex);
            List<CommentResponse> commentResponseList = commentsOnPage.stream()
                    .map(comment -> new CommentResponse(
                            comment.getComment(),
                            comment.getPost().getId(),
                            comment.getUser().getId(),
                            comment.getCommentDate()
                    ))
                    .collect(Collectors.toList());
            return new GenericResponse<>(commentResponseList, Boolean.TRUE);
        } catch (Exception e) {
            throw new CommentNotFoundException("");
        }
    }
}
