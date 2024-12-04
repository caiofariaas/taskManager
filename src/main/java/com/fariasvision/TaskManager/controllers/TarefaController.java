package com.fariasvision.TaskManager.controllers;

import com.fariasvision.TaskManager.dtos.TarefaResponse;
import com.fariasvision.TaskManager.dtos.TarefaInput;
import com.fariasvision.TaskManager.services.CreateTarefaService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class TarefaController {

    @Autowired
    CreateTarefaService createTarefaService;

    @MutationMapping
    public TarefaResponse createTask(@Argument @Valid TarefaInput tarefa){
        final var tarefaServiceTask = createTarefaService.createTask(tarefa);

        return TarefaResponse.builder()
                .title(tarefaServiceTask.getTitle())
                .description(tarefaServiceTask.getDescription())
                .deadLine(tarefaServiceTask.getDeadline())
                .status(tarefaServiceTask.getStatus())
                .build();
    }
}
