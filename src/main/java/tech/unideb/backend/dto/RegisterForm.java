package tech.unideb.backend.dto;

public record RegisterForm(String username, String email, String password, String inviteKey) {
}
