package com.fariasvision.TaskManager.implementations.tarefa;

import com.fariasvision.TaskManager.infra.exceptions.tarefa.TaskNotFoundException;
import com.fariasvision.TaskManager.repositories.TarefaRepository;
import com.fariasvision.TaskManager.services.DeleteTarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteTarefaServiceImpl implements DeleteTarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Override
    public Boolean deleteTask(Long id) {
        tarefaRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Tarefa não encontrada!"));

        tarefaRepository.deleteById(id);

        return tarefaRepository.findById(id).isEmpty();
    }
}
