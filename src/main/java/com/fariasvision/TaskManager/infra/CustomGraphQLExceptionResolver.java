package com.fariasvision.TaskManager.infra;

import com.fariasvision.TaskManager.infra.exceptions.tarefa.TaskNotFoundException;
import com.fariasvision.TaskManager.infra.exceptions.usuario.UserAlreadyExistsException;
import com.fariasvision.TaskManager.infra.exceptions.usuario.UserNotFoundException;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CustomGraphQLExceptionResolver extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError(Throwable exception, DataFetchingEnvironment environment) {

        System.out.println("CAPTURADA: " + exception.getClass().getName());

        if (exception instanceof UserNotFoundException ex) {
            return GraphqlErrorBuilder.newError(environment)
                    .message(ex.getMessage())
                    .errorType(graphql.ErrorType.DataFetchingException)
                    .extensions(Map.of("code", ex.getCode()))
                    .build();
        }

        if (exception instanceof TaskNotFoundException ex) {
            return GraphqlErrorBuilder.newError(environment)
                    .message(ex.getMessage())
                    .errorType(graphql.ErrorType.DataFetchingException)
                    .extensions(Map.of("code", ex.getCode()))
                    .build();
        }

        if (exception instanceof UserAlreadyExistsException ex) {
            return GraphqlErrorBuilder.newError(environment)
                    .message(ex.getMessage())
                    .errorType(org.springframework.graphql.execution.ErrorType.UNAUTHORIZED)
                    .extensions(Map.of("code", ex.getCode()))
                    .build();
        }

        // Para outros tipos de exceções, retorne null (nenhum erro tratado)
        return null;
    }
}
