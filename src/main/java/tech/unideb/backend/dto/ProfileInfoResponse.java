package tech.unideb.backend.dto;

import tech.unideb.backend.model.Role;

public record ProfileInfoResponse(
        String username,
        Role role,
        String email,
        String uploadSecret,
        int uploadCount,
        long usedSpace,
        long maxSpace
) {
}
