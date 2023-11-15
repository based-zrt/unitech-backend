package tech.unideb.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tech.unideb.backend.component.UploadResponse;
import tech.unideb.backend.service.StorageService;
import tech.unideb.backend.service.UploadService;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

@RestController
@RequiredArgsConstructor
public class ViewController {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final UploadService uploadService;
    private final StorageService storageService;

    @GetMapping("/view/{id}")
    public UploadResponse viewUpload(@PathVariable String id) {
        var upload = uploadService.getUpload(id);
        if (upload == null) {
            // todo
        }
        try {
            var data = storageService.load(upload);
            return new UploadResponse(
                    upload.getUploader().getUsername(),
                    upload.getUploadDate().format(TIME_FORMATTER),
                    upload.getSize(),
                    Base64.getEncoder().encodeToString(data.getInputStream().readAllBytes())
            );
        } catch (IOException e) {
            // todo
        }
        return null;
    }
}
