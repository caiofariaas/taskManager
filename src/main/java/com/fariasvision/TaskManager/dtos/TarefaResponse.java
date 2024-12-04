package com.fariasvision.TaskManager.dtos;

import com.fariasvision.TaskManager.entities.Usuario;
import lombok.Builder;

import java.io.Serializable;

@Builder
public record TarefaResponse(
        Long id,
        String title,
        String description,
        String status,
        String deadLine,
        Usuario usuario
) implements Serializable {
}
