package tech.unideb.backend.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class JwtConfig {
    private final AppPropertiesConfig appConfig;

    @Bean
    public Algorithm jwtAlgorithm() {
        return Algorithm.HMAC256(appConfig.jwtSecret());
    }

    @Bean
    public JWTVerifier jwtVerifier(final Algorithm algorithm) {
        return JWT.require(algorithm)
            .acceptLeeway(10)
            .withIssuer(appConfig.jwtIssuer())
            .build();
    }
}
