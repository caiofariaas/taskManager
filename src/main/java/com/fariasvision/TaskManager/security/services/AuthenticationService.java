package com.fariasvision.TaskManager.security.services;

import com.fariasvision.TaskManager.dtos.AuthenticationInput;
import com.fariasvision.TaskManager.dtos.TokenResponse;
import com.fariasvision.TaskManager.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationService {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    public TokenResponse loginAndCreateToken(AuthenticationInput dados){
        String tokenJWT = tokenService.gerarToken((Usuario) manager.authenticate(new UsernamePasswordAuthenticationToken(dados.email(), dados.password())).getPrincipal());

        return new TokenResponse(tokenJWT);
    }
}