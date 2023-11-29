package tech.unideb.backend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tech.unideb.backend.dto.ViewResponse;
import tech.unideb.backend.exception.BackendApiException;
import tech.unideb.backend.service.StorageService;
import tech.unideb.backend.service.UploadService;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ViewController {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final UploadService uploadService;
    private final StorageService storageService;

    @GetMapping("/view/{id}")
    public ViewResponse viewUpload(@PathVariable String id) {
        var upload = uploadService.getUpload(id);
        if (upload == null) throw BackendApiException.notFound("Upload does not exist");

        var features = upload.getUploader().getFeatures();
        return new ViewResponse(
                upload.toDto(),
                features.isEmbedEnabled(),
                features.getEmbedTitle(),
                features.getEmbedDescription(),
                features.getEmbedColor()
        );
    }

    @GetMapping("/view/{id}/raw")
    public ResponseEntity<Resource> viewRawImage(@PathVariable String id) {
        var upload = uploadService.getUpload(id);
        if (upload == null) throw BackendApiException.notFound("Upload does not exist");

        try {
            var data = storageService.load(upload);
            return ResponseEntity.ok()
                    .contentLength(upload.getSize())
                    .contentType(MediaType.IMAGE_PNG)
                    .lastModified(upload.getUploadDate())
                    .headers(h -> h.add("Content-Disposition",
                            "attachment; filename=\"" + upload.getFileName() + "\""))
                    .cacheControl(CacheControl.maxAge(1, TimeUnit.DAYS))
                    .body(data);
        } catch (IOException e) {
            throw new BackendApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to load id",
                    e.getClass().getSimpleName(), e.getMessage());
        }
    }
}
