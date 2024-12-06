package com.fariasvision.TaskManager.services;

import com.fariasvision.TaskManager.entities.Tarefa;

public interface GetTarefaByIdService {
    Tarefa getTaskById(Long id);
}
