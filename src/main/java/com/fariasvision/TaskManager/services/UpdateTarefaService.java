package com.fariasvision.TaskManager.services;

import com.fariasvision.TaskManager.dtos.TarefaInput;
import com.fariasvision.TaskManager.entities.Tarefa;

public interface UpdateTarefaService {
    Tarefa updateTask(Long id, TarefaInput tarefa);
}
