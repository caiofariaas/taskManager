package com.fariasvision.TaskManager.services.usuario;

import com.fariasvision.TaskManager.dtos.UsuarioInput;
import com.fariasvision.TaskManager.entities.Usuario;

public interface CreateUsuarioService {
    Usuario createUser(UsuarioInput usuario);
}
