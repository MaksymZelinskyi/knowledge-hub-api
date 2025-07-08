package com.javadevmz.knowledgehub.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateArticleDto(@NotBlank String title, @NotBlank String content) {
}
