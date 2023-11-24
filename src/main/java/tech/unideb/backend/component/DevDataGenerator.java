package tech.unideb.backend.component;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import tech.unideb.backend.model.Role;
import tech.unideb.backend.model.User;
import tech.unideb.backend.model.UserConfig;
import tech.unideb.backend.repository.AuditRepository;
import tech.unideb.backend.repository.ConfigRepository;
import tech.unideb.backend.repository.UploadRepository;
import tech.unideb.backend.repository.UserRepository;

import java.time.ZonedDateTime;

/**
 * Generates data for development and testing purposes.
 */
@Slf4j
@Component
@Profile("default")
@RequiredArgsConstructor
public class DevDataGenerator {
    private final UserRepository userRepository;
    private final ConfigRepository configRepository;
    private final UploadRepository uploadRepository;
    private final AuditRepository auditRepository;

    @PostConstruct
    public void populateDatabase() {
        createUsers();
    }

    private void createUsers() {
        var adminConfig = new UserConfig("1234");
        var sus = new User("testadmin", "xd", "test_admin@unideb.tech", ZonedDateTime.now(),
                "0.0.0.0", ZonedDateTime.now(), "0.0.0.0", Role.ADMIN, adminConfig);
        configRepository.save(adminConfig);
        userRepository.save(sus);
    }
}
