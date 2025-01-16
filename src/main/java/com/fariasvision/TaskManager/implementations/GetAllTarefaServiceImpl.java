package com.fariasvision.TaskManager.implementations;

import com.fariasvision.TaskManager.dtos.TarefaResponse;
import com.fariasvision.TaskManager.entities.Tarefa;
import com.fariasvision.TaskManager.repositories.TarefaRepository;
import com.fariasvision.TaskManager.services.GetAllTarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetAllTarefaServiceImpl implements GetAllTarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Override
    public List<TarefaResponse> getAllTasks() {

        return tarefaRepository.findAll()
                .stream()
                .map(tarefa -> TarefaResponse.builder()
                        .id(tarefa.getId())
                        .title(tarefa.getTitle())
                        .description(tarefa.getDescription())
                        .status(tarefa.getStatus())
                        .deadLine(tarefa.getDeadline())
                        .build()).collect(Collectors.toList());
    }
}
