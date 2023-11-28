package tech.unideb.backend.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import tech.unideb.backend.service.TokenService;

@Slf4j
@Component
@RequiredArgsConstructor
public class BackendAuthenticationProvider implements AuthenticationProvider {
    private final TokenService tokenService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var token = (BackendAuthenticationToken) authentication;

        var user = tokenService.parseUser(token.getCredentials());
        return new BackendAuthenticationToken(user, token.getCredentials());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(BackendAuthenticationToken.class);
    }
}
