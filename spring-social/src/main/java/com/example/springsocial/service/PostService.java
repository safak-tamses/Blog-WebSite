package com.example.springsocial.service;

import com.example.springsocial.exception.PostNotFoundException;
import com.example.springsocial.model.Post;
import com.example.springsocial.model.User;
import com.example.springsocial.payload.PostRequest;
import com.example.springsocial.payload.PostResponse;
import com.example.springsocial.repository.PostRepository;
import com.example.springsocial.util.GenericResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;

    public Post findPostById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(""));
    }

    public GenericResponse addPost(Long userId, PostRequest postRequest) {
        try {
            User u = userService.findUserById(userId);
            Post post = Post.builder()
                    .author(u)
                    .category(postRequest.getCategory())
                    .title(postRequest.getTitle())
                    .content(postRequest.getContent())
                    .releaseDate(new Date())
                    .build();
            postRepository.save(post);
            return new GenericResponse("Post saved successfully.", Boolean.TRUE);
        } catch (Exception e) {

        }
        return null;
    }

    public GenericResponse<List<String>> getPosts(int pageNumber, int pageSize) {
        try {
            List<Post> allPost = postRepository.findAll();
            List<Post> sortedPostList = allPost.stream()
                    .sorted((post1, post2) -> post2.getReleaseDate().compareTo(post1.getReleaseDate()))
                    .collect(Collectors.toList());

            int fromIndex = (pageNumber - 1) * pageSize;
            int toIndex = Math.min(fromIndex + pageSize, sortedPostList.size());

            List<Post> postsOnPage = sortedPostList.subList(fromIndex, toIndex);
            List<String> titles = postsOnPage.stream().map(Post::getTitle).collect(Collectors.toList());

            return new GenericResponse<>(titles,Boolean.TRUE);
        } catch (Exception e) {
            throw new PostNotFoundException("");
        }
    }


    public GenericResponse<List<PostResponse>> getPaginatedPosts(int pageNumber, int pageSize) {
        try {
            PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize, Sort.by(Sort.Order.desc("releaseDate")));

            Page<Post> page = postRepository.findAll(pageRequest);

            List<PostResponse> postResponses = page.getContent().stream()
                    .map(post -> new PostResponse(
                            post.getAuthor().getName(),
                            post.getTitle(),
                            post.getContent(),
                            post.getReleaseDate(),
                            post.getCategory(),
                            post.getComments().size(),
                            post.getId()
                    ))
                    .collect(Collectors.toList());

            return new GenericResponse<>(postResponses,Boolean.TRUE);
        } catch (Exception e) {
            throw new PostNotFoundException("");
        }
    }

    public GenericResponse<List<PostResponse>> showOwnPosts(Long userId, int pageNumber, int pageSize) {
        try {

            List<Post> posts = postRepository.findAllByUser_UserId(userId);

            List<Post> sortedPostList = posts.stream()
                    .sorted((post1, post2) -> post2.getReleaseDate().compareTo(post1.getReleaseDate()))
                    .collect(Collectors.toList());

            int fromIndex = (pageNumber - 1) * pageSize;
            int toIndex = Math.min(fromIndex + pageSize, sortedPostList.size());

            List<Post> postsOnPage = sortedPostList.subList(fromIndex, toIndex);


            List<PostResponse> postResponses = postsOnPage.stream()
                    .map(post -> new PostResponse(
                            post.getAuthor().getName(),
                            post.getTitle(),
                            post.getContent(),
                            post.getReleaseDate(),
                            post.getCategory(),
                            post.getComments().size(),
                            post.getId()
                    ))
                    .collect(Collectors.toList());

            return new GenericResponse<>(postResponses,Boolean.TRUE);
        } catch (Exception e) {
            throw new PostNotFoundException("");
        }
    }


}
