package tech.unideb.backend.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tech.unideb.backend.exception.BackendApiException;
import tech.unideb.backend.model.Upload;
import tech.unideb.backend.repository.UploadRepository;
import tech.unideb.backend.repository.UserRepository;
import tech.unideb.backend.service.StorageService;
import tech.unideb.backend.service.UploadService;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UploadServiceImpl implements UploadService {
    private static final Random RANDOM = new Random();
    private final StorageService storageService;
    private final UploadRepository uploadRepository;
    private final UserRepository userRepository;

    @Override
    public @Nullable Upload getUpload(@NotNull String key) {
        long id = Long.parseLong(key, 36);
        return uploadRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Upload createUpload(@NotNull String userSecret, @NotNull MultipartFile data) {
        var userOpt = userRepository.getUserByUploadSecret(userSecret);
        if (userOpt.isEmpty()) throw BackendApiException.forbidden();

        var upload = new Upload();
        upload.setUploader(userOpt.get());
        upload.setFileName(RANDOM.nextInt(1000, 9999) + "_" + data.getOriginalFilename());
        // maybe set to user timezone later
        upload.setUploadDate(ZonedDateTime.now());
        upload.setSize(data.getSize());
        uploadRepository.save(upload);
        try {
            storageService.store(upload, data.getResource());
        } catch (IOException e) {
            throw new BackendApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to save image, try again later",
                    e.getClass().getSimpleName(), e.getMessage());
        }
        return upload;
    }
}
