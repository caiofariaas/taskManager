package com.fariasvision.TaskManager.controllers;

import com.fariasvision.TaskManager.dtos.TarefaResponse;
import com.fariasvision.TaskManager.dtos.TarefaInput;
import com.fariasvision.TaskManager.entities.Tarefa;
import com.fariasvision.TaskManager.services.CreateTarefaService;
import com.fariasvision.TaskManager.services.GetAllTarefaService;
import com.fariasvision.TaskManager.services.GetTarefaByIdService;
import com.fariasvision.TaskManager.services.UpdateTarefaService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.Arguments;
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
    GetTarefaByIdService getTarefaByIdService;

    @Autowired
    UpdateTarefaService updateTarefaService;

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
    public List<TarefaResponse> getAllTasks(){
        List<Tarefa> tarefas = getAllTarefaService.getAllTasks();

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
    public TarefaResponse getTaskById(@Argument Long id){
        final var task = getTarefaByIdService.getTaskById(id);

        return TarefaResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .deadLine(task.getDeadline())
                .build();
    }

    @MutationMapping
    public TarefaResponse updateTask(@Argument Long id, @Argument TarefaInput tarefa){
        final var task = updateTarefaService.updateTask(id, tarefa);

        return TarefaResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .deadLine(task.getDeadline())
                .build();
    }
}

