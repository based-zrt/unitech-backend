package tech.unideb.backend.exception;

import lombok.Getter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class BackendApiException extends ResponseStatusException {
    private final HttpHeaders headers;
    private final Object[] args;

    public BackendApiException(HttpStatus status) {
        super(status);
        this.headers = HttpHeaders.EMPTY;
        this.args = new String[0];
    }

    public BackendApiException(HttpStatus status, String reason) {
        super(status, reason);
        this.headers = HttpHeaders.EMPTY;
        this.args = new String[0];
    }

    public BackendApiException(HttpStatus status, String reason, Object... args) {
        super(status, reason);
        this.headers = HttpHeaders.EMPTY;
        this.args = args;
    }

    public static BackendApiException notFound() {
        return new BackendApiException(HttpStatus.NOT_FOUND);
    }

    public static BackendApiException badRequest() {
        return new BackendApiException(HttpStatus.BAD_REQUEST);
    }

    public static BackendApiException badRequest(final String reason) {
        return new BackendApiException(HttpStatus.BAD_REQUEST, reason);
    }
}
