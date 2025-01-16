package com.fariasvision.TaskManager.infra.exceptions.usuario;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {

    private final String code;

    public UserNotFoundException(String message) {
        super(message);
        this.code = "USER_NOT_FOUND";
    }
}
