package tech.unideb.backend.service.impl;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;
import tech.unideb.backend.model.Upload;
import tech.unideb.backend.repository.UploadRepository;
import tech.unideb.backend.service.UploadService;

@Service
@RequiredArgsConstructor
public class UploadServiceImpl implements UploadService {
    private final UploadRepository uploadRepository;

    @Override
    public @Nullable Upload getUpload(@NotNull String key) {
        long id = Long.parseLong(key, 36);
        return uploadRepository.getReferenceById(id);
    }
}
