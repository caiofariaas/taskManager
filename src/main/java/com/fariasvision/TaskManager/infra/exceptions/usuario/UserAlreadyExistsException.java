package com.fariasvision.TaskManager.infra.exceptions.usuario;

import lombok.Getter;

@Getter
public class UserAlreadyExistsException extends RuntimeException {

    private final String code;

    public UserAlreadyExistsException(String message) {
        super(message);
        this.code = "USER_ALREADY_EXISTS";
    }
}
