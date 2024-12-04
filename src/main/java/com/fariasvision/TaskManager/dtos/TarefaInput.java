package com.fariasvision.TaskManager.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

public record TarefaInput(
        @NotNull
        String title,
        @Nullable
        String description,
        @NotNull
        String status,
        @Nullable
        String deadline,
        @Nullable
        String userEmail
) {
}
