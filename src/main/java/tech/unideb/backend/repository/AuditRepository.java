package tech.unideb.backend.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.unideb.backend.model.Audit;
import tech.unideb.backend.model.AuditAction;
import tech.unideb.backend.model.User;

/**
 * Repository for Audit.
 */
@Repository
public interface AuditRepository extends JpaRepository<Audit, Long> {
    List<Audit> findAllByUser(User user);

    List<Audit> findAllByAction(AuditAction action);
}
