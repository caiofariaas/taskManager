package com.fariasvision.TaskManager.security;


import com.fariasvision.TaskManager.entities.Usuario;
import com.fariasvision.TaskManager.infra.exceptions.usuario.UserNotFoundException;
import com.fariasvision.TaskManager.repositories.UsuarioRepository;
import com.fariasvision.TaskManager.security.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    /*

     recebe um objeto HttpServletRequest que representa a solicitação HTTP
     um objeto HttpServletResponse que representa a resposta HTTP
     e um objeto FilterChain que permite que o filtro atual passe o controle para o próximo filtro na cadeia.

    */

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Aqui nós chamamos a função para verificar se o Token existe no Header desta requisição

        if(getToken(request) != null){

            // Aqui nós recuperamos o Usuário utilizando a função getByLogin, que espera uma string sendo o Login/Username deste usuário!

            Usuario usuario = (Usuario) usuarioRepository.findByEmail(tokenService.getSubject(getToken(request))).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado!"));

            /*

             O SecurityContextHolder é uma classe que fornece acesso ao contexto de segurança da aplicação.
             Aqui, está sendo usado para definir a autenticação do usuário.
             O método setAuthentication é usado para definir a autenticação.

            */

            /*

            Está sendo criado um novo UsernamePasswordAuthenticationToken
            com informações do usuário e suas autorizações (ou seja, papéis/roles),
            e isso é armazenado no contexto de segurança da aplicação.

             */

            SecurityContextHolder
                    .getContext()
                    .setAuthentication(new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities()));
        }

        /*

        chama o método doFilter do objeto FilterChain para continuar a execução da cadeia de filtros.
        Isso garante que a requisição continue sendo processada pelos outros filtros na cadeia ou pelo manipulador do servlet final,
        permitindo que a lógica da aplicação seja executada.

         */

        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {

        if (request.getHeader("Authorization") != null){
            return request
                    .getHeader("Authorization")
                    .replace("Bearer ", "");
        }
        return null;
    }
}