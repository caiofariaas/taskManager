package com.fariasvision.TaskManager.implementations.usuario;

import com.fariasvision.TaskManager.dtos.TarefaResponse;
import com.fariasvision.TaskManager.dtos.UsuarioResponse;
import com.fariasvision.TaskManager.entities.Usuario;
import com.fariasvision.TaskManager.repositories.UsuarioRepository;
import com.fariasvision.TaskManager.services.usuario.GetUserByEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class GetUserByEmailServiceImpl implements GetUserByEmailService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public UsuarioResponse getUserByEmail(String email) {

        Usuario usuario = (Usuario) usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return UsuarioResponse.builder()
                .id(usuario.getId())
                .name(usuario.getName())
                .email(usuario.getEmail())
                .tarefas(usuario.getTarefas().stream()
                        .map(tarefa -> TarefaResponse.builder()
                                .id(tarefa.getId())
                                .title(tarefa.getTitle())
                                .description(tarefa.getDescription())
                                .status(tarefa.getStatus())
                                .deadLine(tarefa.getDeadline())
                                .build()).collect(Collectors.toList()))
                .build();
    }
}
