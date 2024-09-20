package com.backend.assignment.indpro.backend.assignment.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data

public class GetCommentDTO {
    private Long id;
    private Long authorId;
    private String content;
    private LocalDateTime createdAt;


}
