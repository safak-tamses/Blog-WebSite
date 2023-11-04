package com.example.springsocial.repository;

import com.example.springsocial.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;


import java.util.List;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query(value = "SELECT * FROM Post WHERE users_id = ?1", nativeQuery = true)
    List<Post> findAllByUser_UserId(Long userId);
}
