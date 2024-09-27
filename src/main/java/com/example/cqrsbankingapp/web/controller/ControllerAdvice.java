package com.example.cqrsbankingapp.web.controller;

import com.example.cqrsbankingapp.domain.exeption.ResourceAlreadyExistsException;
import com.example.cqrsbankingapp.domain.exeption.ResourceNotFoundException;
import com.example.cqrsbankingapp.web.dto.MessageDto;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Global exception handler for all controllers in the application.
 * <p>
 * This class is annotated with {@code @RestControllerAdvice} to provide
 * centralized exception handling for REST controllers. It allows you to
 * define custom responses for different types of exceptions across
 * all controllers.
 * <p>
 * Common use cases:
 * - Handle specific exceptions (e.g., {@code IllegalArgumentException}, {@code NullPointerException})
 * - Customize error responses (e.g., return specific HTTP status codes, error messages)
 * - Global exception logging
 * <p>
 * Example:
 * - If a {@code ConstraintViolationException} occurs in any controller,
 * this advice can catch it and return a meaningful error response.
 */
@RestControllerAdvice
public class ControllerAdvice {

    /**
     * Handles {@link ResourceNotFoundException} and responds with HTTP 404 (NOT FOUND).
     *
     * This method catches any {@code ResourceNotFoundException} thrown by the controllers
     * and returns a {@link MessageDto} containing the error message. If no message is provided
     * in the exception, it defaults to "Not found."
     *
     * @param e the exception thrown when a resource is not found
     * @return a {@code MessageDto} with the error message
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public MessageDto resourceNotFound(final ResourceNotFoundException e) {
        return new MessageDto(e.getMessage() != null ? e.getMessage() : "Not found.");
    }

    /**
     * Handles {@link ResourceAlreadyExistsException} and responds with HTTP 400 (BAD REQUEST).
     *
     * This method is triggered when an attempt is made to create a resource that already exists.
     * It returns a {@link MessageDto} with an appropriate error message, or a default message
     * "Already exists" if none is provided in the exception.
     *
     * @param e the exception thrown when a resource already exists
     * @return a {@code MessageDto} with the error message
     */
    @ExceptionHandler(ResourceAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MessageDto resourceAlreadyExists(final ResourceAlreadyExistsException e) {
        return new MessageDto(e.getMessage() != null ? e.getMessage() : "Already exists.");
    }

    /**
     * Handles {@link MethodArgumentNotValidException} and responds with HTTP 400 (BAD REQUEST).
     *
     * This method is called when validation of request body fields fails. It collects all
     * field-specific validation errors and returns them in a {@link MessageDto} along with
     * the message "Validation failed."
     *
     * @param e the exception thrown when method arguments are not valid
     * @return a {@code MessageDto} containing validation error messages
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MessageDto validation(final MethodArgumentNotValidException e) {
        Map<String, String> errors = e.getBindingResult()
                .getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField,
                        DefaultMessageSourceResolvable::getDefaultMessage,
                        (existingMessage, newMessage) -> existingMessage + " " + newMessage
                ));
        return new MessageDto("Validation failed.", errors);
    }

    /**
     * Handles {@link IllegalStateException} and responds with HTTP 400 (BAD REQUEST).
     *
     * This method catches {@code IllegalStateException}, which typically occurs when the application
     * is in an invalid state. It returns a {@link MessageDto} containing the exception's message.
     *
     * @param e the exception thrown due to an illegal state
     * @return a {@code MessageDto} with the error message
     */
    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MessageDto illegalState(final IllegalStateException e) {
        return new MessageDto(e.getMessage());
    }

    /**
     * Handles general {@link Exception} and responds with HTTP 500 (INTERNAL SERVER ERROR).
     *
     * This method catches any other unhandled exceptions and returns a generic error response
     * indicating a server error. The exact exception message is not exposed to the client for security reasons.
     *
     * @param e the general exception caught by the application
     * @return a {@code MessageDto} with a generic server error message
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public MessageDto exception(Exception e) {
        return new MessageDto("Server error");
    }

}
//@RestControllerAdvice
//public class ControllerAdvice {
//
//    @ExceptionHandler(ResourceNotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public MessageDto resourceNotFound(final ResourceNotFoundException e) {
//
//        return new MessageDto(e.getMessage() != null ? e.getMessage() : "Not found.");
//    }
//
//    @ExceptionHandler(ResourceAlreadyExistsException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public MessageDto resourceAlreadyExists(final ResourceAlreadyExistsException e) {
//        return new MessageDto(e.getMessage() != null ? e.getMessage() : "Already exists.");
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public MessageDto validation(
//            final MethodArgumentNotValidException e) {
//        Map<String, String> errors = e.getBindingResult()
//                .getFieldErrors().stream()
//                .collect(Collectors.toMap(FieldError::getField,
//                        DefaultMessageSourceResolvable::getDefaultMessage,
//                        (existingMessage, newMessage) ->
//                                existingMessage + " " + newMessage
//                ));
//        return new MessageDto("Validation failed.", errors);
//
//    }
//
//    @ExceptionHandler(IllegalStateException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public MessageDto illegalState(final IllegalStateException e) {
//        return new MessageDto(e.getMessage());
//    }
//
//    @ExceptionHandler
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public MessageDto exception(Exception e) {
//        return new MessageDto("Server error");
//    }
//
//}