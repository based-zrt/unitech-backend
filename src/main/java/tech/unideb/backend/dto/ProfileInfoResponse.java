package tech.unideb.backend.dto;

import tech.unideb.backend.model.Role;

import java.util.List;

public record ProfileInfoResponse(
        String username,
        Role role,
        String email,
        String uploadSecret,
        int uploadCount,
        long usedSpace,
        long maxSpace,
        List<UploadDto> uploads
) {
}
