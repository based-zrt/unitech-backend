package tech.unideb.backend.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import tech.unideb.backend.config.AppPropertiesConfig;
import tech.unideb.backend.model.Role;
import tech.unideb.backend.model.User;
import tech.unideb.backend.service.TokenService;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final JWTVerifier verifier;
    private final AppPropertiesConfig appConfig;
    private final Algorithm jwtAlgorithm;

    @Override
    public DecodedJWT verify(String token) {
        return verifier.verify(token);
    }

    @Override
    public User parseUser(DecodedJWT token) {
        var username = token.getSubject();
        var uuidString = token.getClaim("uuid").asString();
        var email = token.getClaim("email").asString();
        var role = token.getClaim("role").asString();
        if (username != null && uuidString != null && email != null && role != null)
            return new User(UUID.fromString(uuidString), username, email, Role.valueOf(role.toUpperCase()));
        throw new BadCredentialsException("Malformed JWT");
    }

    @Override
    public String createJwt(User user) {
        return JWT.create()
                .withIssuer(appConfig.jwtIssuer())
                .withExpiresAt(Instant.now().plus(appConfig.jwtExpiration()))
                .withSubject(user.getUsername())
                .withClaim("uuid", user.getUuid().toString())
                .withClaim("email", user.getEmail())
                .withClaim("role", user.getRole().toString())
                .sign(jwtAlgorithm);
    }
}
