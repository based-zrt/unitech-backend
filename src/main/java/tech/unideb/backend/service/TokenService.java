package tech.unideb.backend.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import tech.unideb.backend.model.User;

public interface TokenService {

    DecodedJWT verify(String token);

    User parseUser(DecodedJWT token);

    String createJwt(User user);
}
