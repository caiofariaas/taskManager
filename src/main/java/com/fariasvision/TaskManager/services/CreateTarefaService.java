package com.fariasvision.TaskManager.services;

import com.fariasvision.TaskManager.dtos.TarefaInput;
import com.fariasvision.TaskManager.entities.Tarefa;

public interface CreateTarefaService {
    Tarefa createTask(TarefaInput data);
}
