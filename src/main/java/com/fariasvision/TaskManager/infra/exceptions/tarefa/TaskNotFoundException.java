package com.fariasvision.TaskManager.infra.exceptions.tarefa;

import lombok.Getter;

@Getter
public class TaskNotFoundException extends RuntimeException {

    private final String code;

    public TaskNotFoundException(String message) {
        super(message);
        this.code = "TASK_NOT_FOUND";
    }
}
