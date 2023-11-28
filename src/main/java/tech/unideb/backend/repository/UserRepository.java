package tech.unideb.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tech.unideb.backend.model.User;

import java.util.Optional;

/**
 * Repository for User.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String username);

    @Query("SELECT u FROM User u WHERE u.features.uploadSecret = :secret")
    Optional<User> getUserByUploadSecret(@Param("secret") String uploadSecret);
}
