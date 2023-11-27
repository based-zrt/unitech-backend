package tech.unideb.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.unideb.backend.model.UserConfig;

@Repository
public interface ConfigRepository extends JpaRepository<UserConfig, Long> {
}
