package com.fariasvision.TaskManager.services;

import com.fariasvision.TaskManager.dtos.TarefaResponse;
import com.fariasvision.TaskManager.entities.Tarefa;

import java.util.List;

public interface GetAllTarefaService {
    List<TarefaResponse> getAllTasks();
}
