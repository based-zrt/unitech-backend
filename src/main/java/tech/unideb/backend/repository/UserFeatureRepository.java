package tech.unideb.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.unideb.backend.model.UserFeatures;

@Repository
public interface UserFeatureRepository extends JpaRepository<UserFeatures, Long> {
}
