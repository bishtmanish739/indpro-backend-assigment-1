package com.backend.assignment.indpro.backend.assignment.repository;

import com.backend.assignment.indpro.backend.assignment.entity.Comment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
@Transactional
public interface CommentRepository extends JpaRepository<Comment, Long> {
//    @Query("SELECT * FROM comments c WHERE c.post.post_id = :postId")
//    List<Comment> findByPostId(@Param("postId") Long postId);
    List<Comment> findAllCommentByPostId(Long postId);
}