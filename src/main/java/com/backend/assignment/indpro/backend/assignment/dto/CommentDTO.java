package com.backend.assignment.indpro.backend.assignment.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
@Data

public class CommentDTO {
    @NotBlank(message = "Content is mandatory")
    private String content;

    private Long authorId;
}