package com.fariasvision.TaskManager.controllers;


import com.fariasvision.TaskManager.dtos.AuthenticationInput;
import com.fariasvision.TaskManager.dtos.TokenResponse;
import com.fariasvision.TaskManager.dtos.UsuarioInput;
import com.fariasvision.TaskManager.dtos.UsuarioResponse;
import com.fariasvision.TaskManager.security.services.AuthenticationService;
import com.fariasvision.TaskManager.security.services.AuthorizationService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;


@Controller
@Slf4j
public class AuthenticationController {

    @Autowired
    private AuthenticationService authService;

    @Autowired
    private AuthorizationService authorizationService;

    @MutationMapping
    public TokenResponse login(@Argument @Valid AuthenticationInput authenticationInput) {
        return authService.loginAndCreateToken(authenticationInput);
    }

    @MutationMapping
    public UsuarioResponse register(@Argument @Valid UsuarioInput usuario) {

        final var user = authorizationService.register(usuario);

        log.info("{}", user);

        return UsuarioResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}