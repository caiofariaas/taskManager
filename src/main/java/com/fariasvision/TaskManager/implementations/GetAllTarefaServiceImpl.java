package com.fariasvision.TaskManager.implementations;

import com.fariasvision.TaskManager.entities.Tarefa;
import com.fariasvision.TaskManager.repositories.TarefaRepository;
import com.fariasvision.TaskManager.services.GetAllTarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllTarefaServiceImpl implements GetAllTarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Override
    public List<Tarefa> tasks() {
        return tarefaRepository.findAll();
    }
}
