package com.backend.assignment.indpro.backend.assignment.repository;

import com.backend.assignment.indpro.backend.assignment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}