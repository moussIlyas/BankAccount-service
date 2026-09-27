package com.sid.bankaccount.exceptions;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.graphql.execution.DataFetcherExceptionResolver;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.CompletionException;
import java.util.stream.Collectors;

@Component
public class CustomerDataFetchExceptionResolver implements DataFetcherExceptionResolver {

    @Override
    public Mono<List<GraphQLError>> resolveException(Throwable ex, DataFetchingEnvironment environment) {
        Throwable cause = ex instanceof CompletionException && ex.getCause() != null ? ex.getCause() : ex;
        if (cause instanceof AccountNotFoundException accountNotFoundException) {
            return error(environment, ErrorType.NOT_FOUND, accountNotFoundException.getMessage());
        }
        if (cause instanceof CustomerNotFoundException customerNotFoundException) {
            return error(environment, ErrorType.NOT_FOUND, customerNotFoundException.getMessage());
        }
        if (cause instanceof ConstraintViolationException constraintViolationException) {
            String message = constraintViolationException.getConstraintViolations().stream()
                    .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                    .collect(Collectors.joining(", "));
            return error(environment, ErrorType.BAD_REQUEST, message);
        }
        if (cause instanceof DataIntegrityViolationException) {
            return error(environment, ErrorType.BAD_REQUEST, "Invalid account data: " + cause.getMessage());
        }
        return Mono.empty();
    }

    private Mono<List<GraphQLError>> error(DataFetchingEnvironment environment, ErrorType errorType, String message) {
        return Mono.just(List.of(GraphqlErrorBuilder.newError(environment)
                .errorType(errorType)
                .message(message)
                .build()));
    }
}
