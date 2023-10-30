package com.example.springsocial.service;

import com.example.springsocial.exception.DataNotFoundException;
import com.example.springsocial.model.Post;
import com.example.springsocial.model.User;
import com.example.springsocial.payload.PostRequest;
import com.example.springsocial.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;


import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public Post addPost(User user, PostRequest postRequest) {
        try {
            Post post = Post.builder()
                    .author(user)
                    .category(postRequest.getCategory())
                    .title(postRequest.getTitle())
                    .content(postRequest.getContent())
                    .releaseDate(new Date())
                    .build();
            postRepository.save(post);
        } catch (Exception e) {

        }
        return null;
    }

    public List<Post> getPostsByReleaseDate(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, Sort.by("releaseDate").descending());
        List<Post> p = postRepository.findAll(pageable).getContent();
        System.out.println("");
        return postRepository.findAll(pageable).getContent();
    }


}
