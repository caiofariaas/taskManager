package com.fariasvision.TaskManager.implementations;

import com.fariasvision.TaskManager.dtos.TarefaInput;
import com.fariasvision.TaskManager.entities.Tarefa;
import com.fariasvision.TaskManager.repositories.TarefaRepository;
import com.fariasvision.TaskManager.services.UpdateTarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateTarefaServiceImpl implements UpdateTarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Override
    public Tarefa updateTask(Long id, TarefaInput tarefa) {
        Tarefa task = tarefaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada!"));

        if(tarefa.title() != null){
            task.setTitle(tarefa.title());
        }
        if(tarefa.status() != null){
            task.setStatus(tarefa.status());
        }
        if(tarefa.deadline() != null){
            task.setDeadline(tarefa.deadline());
        }
        if(tarefa.description() != null){
            task.setDescription(tarefa.description());
        }

        tarefaRepository.save(task);

        return task;
    }
}
