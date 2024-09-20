package com.backend.assignment.indpro.backend.assignment.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "comments")

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne


    private Post post;
    @ManyToOne
    private User author;
    @Lob
    private String content;
    @CreationTimestamp
    private LocalDateTime createdAt;
}