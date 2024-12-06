package com.fariasvision.TaskManager.controllers;

import com.fariasvision.TaskManager.dtos.TarefaResponse;
import com.fariasvision.TaskManager.dtos.TarefaInput;
import com.fariasvision.TaskManager.entities.Tarefa;
import com.fariasvision.TaskManager.services.CreateTarefaService;
import com.fariasvision.TaskManager.services.GetAllTarefaService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class TarefaController {

    @Autowired
    CreateTarefaService createTarefaService;

    @Autowired
    GetAllTarefaService getAllTarefaService;

    @MutationMapping
    public TarefaResponse createTask(@Argument @Valid TarefaInput tarefa){
        final var tarefaServiceTask = createTarefaService.createTask(tarefa);

        log.info("{}", tarefaServiceTask);

        return TarefaResponse.builder()
                .id(tarefaServiceTask.getId())
                .title(tarefaServiceTask.getTitle())
                .description(tarefaServiceTask.getDescription())
                .deadLine(tarefaServiceTask.getDeadline())
                .status(tarefaServiceTask.getStatus())
                .build();
    }

    @QueryMapping
    public List<TarefaResponse> tasks (){
        List<Tarefa> tarefas = getAllTarefaService.tasks();

        return tarefas.stream()
                .map(tarefa -> TarefaResponse.builder()
                        .id(tarefa.getId())
                        .title(tarefa.getTitle())
                        .description(tarefa.getDescription())
                        .status(tarefa.getStatus())
                        .deadLine(tarefa.getDeadline())
                        .build()).collect(Collectors.toList());
    }
}
