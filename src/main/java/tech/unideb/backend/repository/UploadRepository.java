package tech.unideb.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.unideb.backend.model.Upload;

import java.util.Optional;

@Repository
public interface UploadRepository extends JpaRepository<Upload, Long> {

    Optional<Upload> findById(long id);
}
