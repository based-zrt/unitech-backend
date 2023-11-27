package tech.unideb.backend.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.jetbrains.annotations.Nullable;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import tech.unideb.backend.model.User;

import java.util.List;

public class BackendAuthenticationToken extends AbstractAuthenticationToken {
    private final DecodedJWT token;
    private final User user;

    public BackendAuthenticationToken(User user, DecodedJWT token) {
        super(user.getRole().getAuthorities());
        this.token = token;
        this.user = user;
    }

    public BackendAuthenticationToken(DecodedJWT token) {
        super(null);
        this.token = token;
        this.user = null;
    }

    @Override
    public DecodedJWT getCredentials() {
        return token;
    }

    @Override
    public User getPrincipal() {
        if (user == null)
            throw new AuthenticationServiceException("Token is not authenticated");
        return user;
    }

    @Override
    public String getName() {
        if (user == null)
            throw new AuthenticationServiceException("Token is not authenticated");
        return user.getUsername();
    }
}
