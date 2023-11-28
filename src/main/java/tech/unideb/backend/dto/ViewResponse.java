package tech.unideb.backend.dto;

import org.jetbrains.annotations.Nullable;

public record ViewResponse(
        UploadDto upload,
        boolean embed,
        @Nullable
        String embedTitle,
        @Nullable
        String embedDescription,
        @Nullable
        String embedColor
) { }
