package tech.unideb.backend.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.web.multipart.MultipartFile;
import tech.unideb.backend.model.Upload;

public interface UploadService {

    @Nullable
    Upload getUpload(@NotNull String key);

    Upload createUpload(@NotNull String userSecret, @NotNull MultipartFile data);
}
