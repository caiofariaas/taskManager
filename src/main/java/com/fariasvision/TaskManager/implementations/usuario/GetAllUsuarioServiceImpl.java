package com.fariasvision.TaskManager.implementations.usuario;

import com.fariasvision.TaskManager.dtos.TarefaResponse;
import com.fariasvision.TaskManager.dtos.UsuarioResponse;
import com.fariasvision.TaskManager.repositories.UsuarioRepository;
import com.fariasvision.TaskManager.services.usuario.GetAllUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetAllUsuarioServiceImpl implements GetAllUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<UsuarioResponse> getAllUsers() {
        return usuarioRepository.findAll()
                .stream()
                .map(user -> UsuarioResponse.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .email(user.getEmail())
                        .tarefas(user.getTarefas().stream()
                                .map(tarefa -> TarefaResponse.builder()
                                        .id(tarefa.getId())
                                        .title(tarefa.getTitle())
                                        .description(tarefa.getDescription())
                                        .status(tarefa.getStatus())
                                        .deadLine(tarefa.getDeadline())
                                        .build()).collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());
    }
}
