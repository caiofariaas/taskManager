package com.fariasvision.TaskManager.implementations.tarefa;

import com.fariasvision.TaskManager.infra.exceptions.usuario.UserNotFoundException;
import com.fariasvision.TaskManager.dtos.TarefaInput;
import com.fariasvision.TaskManager.entities.Tarefa;
import com.fariasvision.TaskManager.entities.Usuario;
import com.fariasvision.TaskManager.repositories.TarefaRepository;
import com.fariasvision.TaskManager.repositories.UsuarioRepository;
import com.fariasvision.TaskManager.services.CreateTarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateTarefaServiceImpl  implements CreateTarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Tarefa createTask(TarefaInput data) {

        Usuario usuario = (Usuario) usuarioRepository.findByEmail(data.userEmail())
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado!"));

        Tarefa tarefa = Tarefa.builder()
                .description(data.description())
                .title(data.title())
                .deadline(data.deadline())
                .status(data.status())
                .usuario(usuario)
                .build();

        usuario.getTarefas().add(tarefa);

        tarefaRepository.save(tarefa);
        usuarioRepository.save(usuario);

        return tarefa;
    }
}
