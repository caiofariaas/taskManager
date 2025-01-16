package com.fariasvision.TaskManager.infra;

import com.fariasvision.TaskManager.infra.exceptions.tarefa.TaskNotFoundException;
import com.fariasvision.TaskManager.infra.exceptions.usuario.UserAlreadyExistsException;
import com.fariasvision.TaskManager.infra.exceptions.usuario.UserNotFoundException;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import lombok.NonNull;
import org.springframework.graphql.execution.DataFetcherExceptionResolver;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Component
public class CustomGraphQLExceptionResolver implements DataFetcherExceptionResolver {

    @Override
    public @NonNull Mono<List<GraphQLError>> resolveException(@NonNull Throwable exception, @NonNull DataFetchingEnvironment environment) {
        if (exception instanceof UserNotFoundException ex) {
            return Mono.just(List.of(
                    GraphqlErrorBuilder.newError(environment)
                            .message(ex.getMessage())
                            .errorType(graphql.ErrorType.DataFetchingException)
                            .extensions(Map.of("code", ex.getCode()))
                            .build()
            ));
        }

        if (exception instanceof TaskNotFoundException ex){
            return Mono.just((List.of(
                    GraphqlErrorBuilder.newError(environment)
                            .message(ex.getMessage())
                            .errorType(ErrorType.DataFetchingException)
                            .extensions(Map.of("code", ex.getCode()))
                            .build()
            )));
        }

        if (exception instanceof UserAlreadyExistsException ex){
            return Mono.just((List.of(
                    GraphqlErrorBuilder.newError(environment)
                            .message(ex.getMessage())
                            .errorType(org.springframework.graphql.execution.ErrorType.UNAUTHORIZED)
                            .extensions(Map.of("code", ex.getCode()))
                            .build()
            )));
        }

        return Mono.empty();
    }
}
