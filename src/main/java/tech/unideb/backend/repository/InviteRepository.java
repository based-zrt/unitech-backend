package tech.unideb.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.unideb.backend.model.Invite;

@Repository
public interface InviteRepository extends JpaRepository<Invite, Long> {
}
