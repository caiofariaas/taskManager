package com.fariasvision.TaskManager.implementations.usuario;

import com.fariasvision.TaskManager.dtos.UsuarioInput;
import com.fariasvision.TaskManager.entities.Usuario;
import com.fariasvision.TaskManager.repositories.UsuarioRepository;
import com.fariasvision.TaskManager.services.usuario.CreateUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateUsuarioServiceImpl implements CreateUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Usuario createUser(UsuarioInput usuario) {

        return usuarioRepository.save(
                Usuario.builder()
                        .name(usuario.name())
                        .email(usuario.email())
                        .password(passwordEncoder.encode(usuario.password()))
                        .build()
        );
    }
}
