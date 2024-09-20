package com.backend.assignment.indpro.backend.assignment.dto;


import lombok.Data;

import javax.validation.constraints.NotBlank;
@Data

public class PostDTO {
    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private Long authorId;

    // Getters and Setters
}
