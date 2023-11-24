package tech.unideb.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.unideb.backend.model.UserConfig;

public interface ConfigRepository extends JpaRepository<UserConfig, Long> {
}
