package com.fariasvision.TaskManager.controllers;


import com.fariasvision.TaskManager.dtos.UsuarioResponse;
import com.fariasvision.TaskManager.repositories.UsuarioRepository;
import com.fariasvision.TaskManager.services.usuario.GetAllUsuarioService;
import com.fariasvision.TaskManager.services.usuario.GetUserByEmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Slf4j
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    GetAllUsuarioService getAllUsuarioService;

    @Autowired
    GetUserByEmailService getUserByEmailService;

    @QueryMapping
    public List<UsuarioResponse> getAllUsers(){
        return getAllUsuarioService.getAllUsers();
    }

    @QueryMapping
    public UsuarioResponse getUserByEmail(@Argument String email){
        return getUserByEmailService.getUserByEmail(email);
    }
}
