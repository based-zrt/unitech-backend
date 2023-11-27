package tech.unideb.backend.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DurationUnit;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@ConfigurationProperties("unideb.backend")
public record AppPropertiesConfig(
        String jwtSecret,
        String jwtIssuer,
        @DurationUnit(ChronoUnit.DAYS)
        Duration jwtExpiration
) {}
