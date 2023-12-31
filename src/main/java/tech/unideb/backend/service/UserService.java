package tech.unideb.backend.service;

import tech.unideb.backend.dto.LoginForm;
import tech.unideb.backend.dto.LoginResponse;
import tech.unideb.backend.dto.RegisterForm;
import tech.unideb.backend.model.User;

/**
 * Service interface for User.
 */
public interface UserService {

    User register(RegisterForm form, String ip);

    LoginResponse login(LoginForm form, String ip);
}
