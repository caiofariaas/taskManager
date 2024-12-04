package com.fariasvision.TaskManager.implementations;

import com.fariasvision.TaskManager.dtos.TarefaInput;
import com.fariasvision.TaskManager.entities.Tarefa;
import com.fariasvision.TaskManager.repositories.TarefaRepository;
import com.fariasvision.TaskManager.services.CreateTarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateTarefaServiceImpl  implements CreateTarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Override
    public Tarefa createTask(TarefaInput data) {

        return tarefaRepository.save(Tarefa.builder()
                .description(data.description())
                .title(data.title())
                .deadline(data.deadline())
                .status(data.status())
                .build());
    }
}
