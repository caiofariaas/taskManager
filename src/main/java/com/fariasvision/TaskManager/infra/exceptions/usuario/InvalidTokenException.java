package com.fariasvision.TaskManager.infra.exceptions.usuario;

import lombok.Getter;

@Getter
public class InvalidTokenException extends RuntimeException {

    private final String code;

    public InvalidTokenException(String message, Exception e) {
        super(message);
        this.code = "INVALID_TOKEN";
    }
}
