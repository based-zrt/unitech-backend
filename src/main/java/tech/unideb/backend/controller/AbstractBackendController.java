package tech.unideb.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import tech.unideb.backend.model.User;
import tech.unideb.backend.repository.UserRepository;

public abstract class AbstractBackendController {
    @Autowired
    private UserRepository userRepository;

    protected User getCurrentUser() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username).orElseThrow();
    }
}
