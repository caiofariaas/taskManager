package com.fariasvision.TaskManager.dtos;

import lombok.Builder;

@Builder
public record UsuarioResponse(
        Long id,
        String name,
        String email
) {
}
