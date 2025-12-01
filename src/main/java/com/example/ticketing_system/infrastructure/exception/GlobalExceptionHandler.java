package com.example.ticketing_system.infrastructure.exception;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.net.URI;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // =======================================================
    // 1. Validaciones de DTOs (@Valid, @Validated)
    // =======================================================
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidation(MethodArgumentNotValidException ex, WebRequest request) {

        String path = ((ServletWebRequest) request).getRequest().getRequestURI();
        String traceId = MDC.get("traceId");

        // LOG
        log.warn("Validation failed - traceId={} - path={} - errors={}",
                traceId, path, ex.getBindingResult().getFieldErrors());

        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pd.setTitle("Validation error");
        pd.setType(URI.create("https://example.com/errors/validation"));
        pd.setDetail("One or more fields are invalid.");
        pd.setInstance(URI.create(path));

        // Errores campo → mensaje
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));

        pd.setProperty("errors", errors);
        pd.setProperty("timestamp", OffsetDateTime.now());
        pd.setProperty("traceId", traceId);

        return pd;
    }

    // =======================================================
    // 2. Cuando no existe un recurso (ID inexistente)
    // =======================================================
    @ExceptionHandler(EntityNotFoundException.class)
    public ProblemDetail handleEntityNotFound(EntityNotFoundException ex, WebRequest request) {

        String path = ((ServletWebRequest) request).getRequest().getRequestURI();
        String traceId = MDC.get("traceId");

        log.warn("Entity not found - traceId={} - path={} - msg={}",
                traceId, path, ex.getMessage());

        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        pd.setTitle("Resource not found");
        pd.setType(URI.create("https://example.com/errors/not-found"));
        pd.setDetail(ex.getMessage());
        pd.setInstance(URI.create(path));

        pd.setProperty("timestamp", OffsetDateTime.now());
        pd.setProperty("traceId", traceId);

        return pd;
    }

    // =======================================================
    // 3. Violación de restricciones SQL (FK, UNIQUE, NOT NULL)
    // =======================================================
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ProblemDetail handleDataIntegrity(DataIntegrityViolationException ex, WebRequest request) {

        String path = ((ServletWebRequest) request).getRequest().getRequestURI();
        String traceId = MDC.get("traceId");

        log.error("DB constraint violation - traceId={} - path={} - msg={}",
                traceId, path, ex.getMostSpecificCause().getMessage());

        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        pd.setTitle("Database constraint violation");
        pd.setType(URI.create("https://example.com/errors/db-constraint"));
        pd.setDetail("The request violates a database constraint.");
        pd.setInstance(URI.create(path));

        pd.setProperty("timestamp", OffsetDateTime.now());
        pd.setProperty("traceId", traceId);

        return pd;
    }

    // =======================================================
    // 4. Excepciones no manejadas (error 500)
    // =======================================================
    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGeneric(Exception ex, WebRequest request) {

        String path = ((ServletWebRequest) request).getRequest().getRequestURI();
        String traceId = MDC.get("traceId");

        log.error("Unhandled exception - traceId={} - path={} - error={}",
                traceId, path, ex.toString(), ex);

        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        pd.setTitle("Internal server error");
        pd.setType(URI.create("https://example.com/errors/internal"));
        pd.setDetail("An unexpected error occurred.");
        pd.setInstance(URI.create(path));

        pd.setProperty("timestamp", OffsetDateTime.now());
        pd.setProperty("traceId", traceId);

        return pd;
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Map<String, Object>> handleBadCredentials(BadCredentialsException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("type", "https://example.com/errors/authentication");
        body.put("title", "Credenciales inválidas");
        body.put("status", 401);
        body.put("detail", "Usuario o contraseña incorrectos.");
        body.put("timestamp", Instant.now().toString());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
    }
}
