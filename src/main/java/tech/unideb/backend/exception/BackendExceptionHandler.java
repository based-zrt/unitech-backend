package tech.unideb.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(basePackages = "tech.unideb.backend")
public class BackendExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BackendApiException.class)
    public ResponseEntity<BackendApiException> handleException(BackendApiException e) {
        return new ResponseEntity<>(e, e.getHeaders(), e.getStatusCode().value());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<BackendApiException> handleAccessDeniedException(AccessDeniedException e) {
        return new ResponseEntity<>(BackendApiException.forbidden(e.getMessage()), HttpStatus.FORBIDDEN);
    }
}
