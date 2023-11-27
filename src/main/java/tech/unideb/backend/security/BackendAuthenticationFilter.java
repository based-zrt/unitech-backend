package tech.unideb.backend.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Nullable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import tech.unideb.backend.service.TokenService;

import java.io.IOException;

public class BackendAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private final TokenService tokenService;

    public BackendAuthenticationFilter(RequestMatcher matcher, TokenService tokenService,
                                       AuthenticationManager authenticationManager) {
        super(matcher);
        this.tokenService = tokenService;
        setAuthenticationManager(authenticationManager);
    }

    @Override
    protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
        if (super.requiresAuthentication(request, response)) {
            String token = findToken(request);
            if (token == null) {
                return false;
            }
            request.setAttribute("token", token);
            return true;
        }
        return false;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        var jwt = (String) request.getAttribute("token");
        try {
            var token = new BackendAuthenticationToken(tokenService.verify(jwt));
            return getAuthenticationManager().authenticate(token);
        } catch (TokenExpiredException e) {
            throw new CredentialsExpiredException("Token expired", e);
        } catch (JWTVerificationException e) {
            throw new BadCredentialsException("Invalid token", e);
        } catch (Exception e) {
            throw new InternalAuthenticationServiceException(e.getMessage(), e);
        }
    }

    @Nullable
    private String findToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null) {
            token = token.replace("Bearer", "").trim();
        } else {
            token = request.getParameter("token");
        }
        return token;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authResult);
        chain.doFilter(request, response);
    }
}
