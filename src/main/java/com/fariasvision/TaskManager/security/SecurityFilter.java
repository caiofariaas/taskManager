package com.fariasvision.TaskManager.security;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fariasvision.TaskManager.entities.Usuario;
import com.fariasvision.TaskManager.infra.exceptions.usuario.InvalidTokenException;
import com.fariasvision.TaskManager.infra.exceptions.usuario.UserNotFoundException;
import com.fariasvision.TaskManager.repositories.UsuarioRepository;
import com.fariasvision.TaskManager.security.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
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

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        try {
            String token = getToken(request);

            if (token != null) {
                String subject = tokenService.getSubject(token);

                Usuario usuario = (Usuario) usuarioRepository.findByEmail(subject)
                        .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado!"));

                SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities())
                );
            }

            filterChain.doFilter(request, response);

        } catch (InvalidTokenException e) {
            handleException(response, HttpServletResponse.SC_UNAUTHORIZED, "Token inválido", "INVALID_TOKEN", e);
        } catch (TokenExpiredException e){
            handleException(response, HttpServletResponse.SC_UNAUTHORIZED, "Token Expirado", "EXPIRED_TOKEN", e);
        } catch (UserNotFoundException e) {
            handleException(response, HttpServletResponse.SC_NOT_FOUND, "Usuário não encontrado", "USER_NOT_FOUND", e);
        }
    }

    private void handleException(HttpServletResponse response, int status, String message, String code, Exception exception)
            throws IOException {
        response.setStatus(status);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String jsonError = """
            {
                "errors": [
                    {
                        "message": "%s",
                        "extensions": {
                            "code": "%s"
                        }
                    }
                ]
            }
        """.formatted(message, code);

        response.getWriter().write(jsonError);
    }

    private String getToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.replace("Bearer ", "");
        }
        return null;
    }
}
