package com.fariasvision.TaskManager.implementations;

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

        tarefaRepository.deleteById(id);

        return tarefaRepository.findById(id).isEmpty();
    }
}
