package com.fariasvision.TaskManager.implementations;

import com.fariasvision.TaskManager.entities.Tarefa;
import com.fariasvision.TaskManager.repositories.TarefaRepository;
import com.fariasvision.TaskManager.services.GetByIdTarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetByIdTarefaServiceImpl implements GetByIdTarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Override
    public Tarefa task(Long id) {
        return tarefaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada!"));
    }
}
