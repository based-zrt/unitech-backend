package tech.unideb.backend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import tech.unideb.backend.dto.UploadDto;
import tech.unideb.backend.security.annotations.LoggedIn;
import tech.unideb.backend.service.UploadService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UploadController extends AbstractBackendController {
    private final UploadService uploadService;

    @PostMapping("/upload")
    public ResponseEntity<UploadDto> upload(@RequestPart("secret") String secret,
                                            @RequestPart("image") MultipartFile data) {
        var upload = uploadService.createUpload(secret, data);
        return ResponseEntity.ok(upload.toDto());
    }

    @LoggedIn
    @PostMapping("/upload/internal")
    public ResponseEntity<UploadDto> uploadFromDashboard(@RequestPart("image") MultipartFile data) {
        var user = getCurrentUser();
        var upload = uploadService.createUpload(user, data);
        return ResponseEntity.ok(upload.toDto());
    }
}
