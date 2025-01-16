package com.fariasvision.TaskManager.services.usuario;

import com.fariasvision.TaskManager.dtos.UsuarioResponse;

import java.util.List;

public interface GetAllUsuarioService {
    List<UsuarioResponse> getAllUsers();
}
