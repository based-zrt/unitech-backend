package tech.unideb.backend.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("unideb.backend")
public record AppPropertiesConfig(
        String jwtSecret,
        String jwtIssuer
) {}
