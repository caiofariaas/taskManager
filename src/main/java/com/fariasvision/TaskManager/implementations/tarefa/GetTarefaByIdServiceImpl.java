package com.fariasvision.TaskManager.implementations.tarefa;

import com.fariasvision.TaskManager.entities.Tarefa;
import com.fariasvision.TaskManager.infra.exceptions.tarefa.TaskNotFoundException;
import com.fariasvision.TaskManager.repositories.TarefaRepository;
import com.fariasvision.TaskManager.services.GetTarefaByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetTarefaByIdServiceImpl implements GetTarefaByIdService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Override
    public Tarefa getTaskById(Long id) {
        return tarefaRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Tarefa não encontrada!"));
    }
}
