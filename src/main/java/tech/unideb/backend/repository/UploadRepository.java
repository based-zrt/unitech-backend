package tech.unideb.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tech.unideb.backend.model.Upload;
import tech.unideb.backend.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UploadRepository extends JpaRepository<Upload, Long> {

    Optional<Upload> findById(long id);

    int countByUploader(User user);

    @Query("SELECT SUM(u.size) FROM Upload u WHERE u.uploader = :user")
    Optional<Long> totalSizeByUploader(@Param("user") User user);

    List<Upload> findAllByUploader(User user);
}
