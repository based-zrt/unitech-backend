package tech.unideb.backend.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.unideb.backend.repository.UserRepository;
import tech.unideb.backend.service.UserService;

/**
 * Service implementation for User.
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
}
