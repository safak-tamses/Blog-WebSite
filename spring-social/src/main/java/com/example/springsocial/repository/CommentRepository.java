package com.example.springsocial.repository;

import com.example.springsocial.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = "SELECT * FROM Comment WHERE post_id = ?1", nativeQuery = true)
    List<Comment> findAllByPost_id(Long postId);

    @Query(value = "SELECT * FROM Comment WHERE post_id = ?1 AND users_id = ?2", nativeQuery = true)
    List<Comment> findAllByPost_idAndUser_id(Long postId, Long userId);
}
