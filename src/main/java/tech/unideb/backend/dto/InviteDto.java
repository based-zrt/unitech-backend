package tech.unideb.backend.dto;

import java.time.ZonedDateTime;

public record InviteDto(String key, ZonedDateTime createdAt, String creator, ZonedDateTime expiryDate, int uses, int maxUses) {
}
