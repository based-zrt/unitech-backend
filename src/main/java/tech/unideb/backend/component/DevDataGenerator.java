package tech.unideb.backend.component;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import tech.unideb.backend.model.Invite;
import tech.unideb.backend.model.Role;
import tech.unideb.backend.model.User;
import tech.unideb.backend.model.UserConfig;
import tech.unideb.backend.repository.AuditRepository;
import tech.unideb.backend.repository.ConfigRepository;
import tech.unideb.backend.repository.InviteRepository;
import tech.unideb.backend.repository.UploadRepository;
import tech.unideb.backend.repository.UserRepository;

import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * Generates data for development and testing purposes.
 */
@Slf4j
@Component
@Profile({"default", "persistent"})
@RequiredArgsConstructor
public class DevDataGenerator {
    private final UserRepository userRepository;
    private final ConfigRepository configRepository;
    private final UploadRepository uploadRepository;
    private final AuditRepository auditRepository;
    private final InviteRepository inviteRepository;

    @PostConstruct
    public void populateDatabase() {
        createUsers();
        createInvites();
    }

    private void createUsers() {
        try {
            var adminConfig = new UserConfig("1234");
            var sus = new User("testadmin", "xd", "test_admin@unideb.tech", ZonedDateTime.now(),
                    "0.0.0.0", ZonedDateTime.now(), "0.0.0.0", Role.ADMIN, adminConfig, null);
            configRepository.save(adminConfig);
            userRepository.save(sus);
        } catch (Exception ignored) { }
    }

    private void createInvites() {
        try {
            var invite = new Invite(ZonedDateTime.now(), null, ZonedDateTime.now().plusMonths(1), 0, 10);
            inviteRepository.save(invite);
        } catch (Exception ignored) { }
    }
}
