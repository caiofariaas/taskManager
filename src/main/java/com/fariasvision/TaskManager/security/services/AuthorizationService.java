package com.fariasvision.TaskManager.security.services;


import com.fariasvision.TaskManager.dtos.UsuarioInput;
import com.fariasvision.TaskManager.entities.Usuario;
import com.fariasvision.TaskManager.infra.exceptions.usuario.UserAlreadyExistsException;
import com.fariasvision.TaskManager.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthorizationService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AuthenticationService authService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Usuario register(UsuarioInput dados){

        usuarioRepository.findByEmail(dados.email())
                .ifPresent(user -> {
                    throw new UserAlreadyExistsException("Usuário com o email " + dados.email() + " já está registrado!");
                });

        Usuario usuario = Usuario.builder()
                        .name(dados.name())
                        .password(passwordEncoder.encode(dados.password()))
                        .email(dados.email())
                        .build();

        System.out.println("ROLES - " + usuario.getAuthorities());

        usuarioRepository.save(usuario);

        return usuario;
    }
}