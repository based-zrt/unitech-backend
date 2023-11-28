package tech.unideb.backend.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.unideb.backend.dto.LoginForm;
import tech.unideb.backend.dto.LoginResponse;
import tech.unideb.backend.dto.RegisterForm;
import tech.unideb.backend.exception.BackendApiException;
import tech.unideb.backend.model.AuditAction;
import tech.unideb.backend.model.Role;
import tech.unideb.backend.model.User;
import tech.unideb.backend.model.UserFeatures;
import tech.unideb.backend.repository.UserFeatureRepository;
import tech.unideb.backend.repository.UserRepository;
import tech.unideb.backend.service.AuditService;
import tech.unideb.backend.service.InviteService;
import tech.unideb.backend.service.TokenService;
import tech.unideb.backend.service.UserService;

import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * Service implementation for User.
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserFeatureRepository userFeatureRepository;
    private final PasswordEncoder passwordEncoder;
    private final InviteService inviteService;
    private final AuditService auditService;
    private final TokenService tokenService;

    @Override
    public User register(RegisterForm form, String ip) {
        if (form.username().isBlank()) throw BackendApiException.badRequest("Missing username");
        if (form.password().isBlank()) throw BackendApiException.badRequest("Missing password");
        if (form.email().isBlank()) throw BackendApiException.badRequest("Missing email");
        if (form.inviteKey().isBlank()) throw BackendApiException.badRequest("Missing invite key");

        if (userRepository.findByUsername(form.username()).isPresent())
            throw BackendApiException.badRequest("Username already exists");
        if (userRepository.findByEmail(form.email()).isPresent())
            throw BackendApiException.badRequest("Email already in use");

        var invite = inviteService.getByKey(form.inviteKey());
        if (invite == null) throw BackendApiException.badRequest("Invalid invite key");
        if (!inviteService.isValid(invite)) throw BackendApiException.badRequest("Invite not valid");

        var config = new UserFeatures(UUID.randomUUID().toString());
        var user = new User(
                form.username(),
                passwordEncoder.encode(form.password()),
                form.email(),
                ZonedDateTime.now(),
                ip,
                ZonedDateTime.now(),
                ip,
                Role.USER,
                config, invite
        );
        userFeatureRepository.save(config);
        userRepository.save(user);
        invite.addUse();
        inviteService.save(invite);
        auditService.audit(user, AuditAction.REGISTER, "Registered from " + ip);
        return user;
    }

    @Override
    public LoginResponse login(LoginForm form, String ip) {
        if (form.username().isBlank()) throw BackendApiException.badRequest("Missing username");
        if (form.password().isBlank()) throw BackendApiException.badRequest("Missing password");

        var user = userRepository.findByUsername(form.username());
        if (user.isEmpty()) throw BackendApiException.badRequest("Invalid credentials");
        if (!passwordEncoder.matches(form.password(), user.get().getPassword()))
            throw BackendApiException.badRequest("Invalid credentials");

        auditService.audit(user.get(), AuditAction.LOGIN, "Logged in from " + ip);
        return new LoginResponse(tokenService.createJwt(user.get()));
    }
}
