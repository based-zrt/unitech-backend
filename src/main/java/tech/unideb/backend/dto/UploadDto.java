package tech.unideb.backend.dto;

public record UploadDto(String id, String user, String url, long size, String fileName, String uploadDate) {
}
