package com.fariasvision.TaskManager.dtos;

import lombok.Builder;

public record UsuarioInput(
        String name,
        String email,
        String password
) {
}
