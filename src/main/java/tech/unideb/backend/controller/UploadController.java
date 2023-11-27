package tech.unideb.backend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import tech.unideb.backend.dto.UploadResponse;
import tech.unideb.backend.exception.BackendApiException;
import tech.unideb.backend.service.UploadService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UploadController {
    private static final String BASE_URL = System.getenv("BASE_URL");
    private final UploadService uploadService;

    @PostMapping("/upload")
    public ResponseEntity<UploadResponse> upload(@RequestPart("secret") String secret,
                                                 @RequestPart("image") MultipartFile data) {
        var upload = uploadService.createUpload(secret, data);
        return ResponseEntity.ok(new UploadResponse(
                upload.getIdString(),
                upload.getUploader().getUsername(),
                BASE_URL + "view/" + upload.getIdString())
        );
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<BackendApiException> handleAccessDeniedException(AccessDeniedException e) {
        return new ResponseEntity<>(BackendApiException.forbidden("Invalid upload secret"), HttpStatus.FORBIDDEN);
    }
}
