package com.fariasvision.TaskManager.services.usuario;

import com.fariasvision.TaskManager.dtos.UsuarioResponse;

public interface GetUserByEmailService {
    UsuarioResponse getUserByEmail(String email);
}
