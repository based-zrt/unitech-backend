package tech.unideb.backend.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

/**
 * Role Enum for User.
 */
@Getter
@RequiredArgsConstructor
public enum Role {
    ADMIN(List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"))),
    USER(List.of(new SimpleGrantedAuthority("ROLE_USER")));

    private final List<GrantedAuthority> authorities;
}
