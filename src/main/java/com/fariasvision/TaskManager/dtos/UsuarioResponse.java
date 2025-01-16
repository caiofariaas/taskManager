package com.fariasvision.TaskManager.dtos;

import lombok.Builder;

import java.util.List;

@Builder
public record UsuarioResponse(
        Long id,
        String name,
        String email,
        List<TarefaResponse> tarefas
) {
}
