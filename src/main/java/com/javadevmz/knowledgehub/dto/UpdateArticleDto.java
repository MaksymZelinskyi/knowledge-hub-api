package com.javadevmz.knowledgehub.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateArticleDto(@NotBlank String title, @NotBlank String content) {
}
