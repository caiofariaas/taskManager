package com.fariasvision.TaskManager.security.services;


import com.fariasvision.TaskManager.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthorizationService {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthenticationService authService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public OutUsuarioDTO register(InUsuarioDTO dados){

        Usuario usuario = new Usuario(dados, passwordEncoder.encode(dados.senha()));

        System.out.println("ROLES - " + usuario.getAuthorities());

        usuarioService.save(usuario);

        return new OutUsuarioDTO(usuario);
    }
}