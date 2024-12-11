package com.fariasvision.TaskManager.security.services;


import com.fariasvision.TaskManager.dtos.UsuarioInput;
import com.fariasvision.TaskManager.dtos.UsuarioResponse;
import com.fariasvision.TaskManager.entities.Usuario;
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

    public UsuarioResponse register(UsuarioInput dados){

        Usuario usuario = Usuario.builder()
                        .name(dados.name())
                        .password(passwordEncoder.encode(dados.password()))
                        .email(dados.email())
                        .build();

        System.out.println("ROLES - " + usuario.getAuthorities());

        usuarioRepository.save(usuario);

        return UsuarioResponse.builder()
                .id(usuario.getId())
                .name(usuario.getName())
                .email(usuario.getEmail())
                .build();
    }
}