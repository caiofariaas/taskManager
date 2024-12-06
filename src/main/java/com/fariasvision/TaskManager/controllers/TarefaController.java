package com.fariasvision.TaskManager.controllers;

import com.fariasvision.TaskManager.dtos.TarefaResponse;
import com.fariasvision.TaskManager.dtos.TarefaInput;
import com.fariasvision.TaskManager.entities.Tarefa;
import com.fariasvision.TaskManager.services.CreateTarefaService;
import com.fariasvision.TaskManager.services.GetAllTarefaService;
import com.fariasvision.TaskManager.services.GetByIdTarefaService;
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

    @Autowired
    GetByIdTarefaService getByIdTarefaService;

//    Create

    @MutationMapping
    public TarefaResponse createTask(@Argument @Valid TarefaInput tarefa){
        final var task = createTarefaService.createTask(tarefa);

        log.info("{}", task);

        return TarefaResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .deadLine(task.getDeadline())
                .status(task.getStatus())
                .build();
    }

//    Get all

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

//    Get by id

    @QueryMapping
    public TarefaResponse task(@Argument Long id){
        final var task = getByIdTarefaService.task(id);

        return TarefaResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .deadLine(task.getDeadline())
                .build();
    }
}
