package com.javadevmz.knowledgehub.dto;

import jakarta.validation.constraints.NotBlank;

public record RegisterDto(@NotBlank String username, @NotBlank String email, @NotBlank String firstName, @NotBlank String lastName, @NotBlank String password) {
}
