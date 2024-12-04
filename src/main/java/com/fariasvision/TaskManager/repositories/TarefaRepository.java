package com.fariasvision.TaskManager.repositories;

import com.fariasvision.TaskManager.entities.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}
