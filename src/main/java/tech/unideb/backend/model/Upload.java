package tech.unideb.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import tech.unideb.backend.BackendApplication;
import tech.unideb.backend.component.UploadIdGenerator;
import tech.unideb.backend.dto.UploadDto;

import java.time.ZonedDateTime;

/**
 * Upload model.
 */

@Data
@Entity
@Table(name = "uploads")
@NoArgsConstructor
public class Upload {
    @Id
    @GenericGenerator(name = "upload_generator", type = UploadIdGenerator.class)
    @GeneratedValue(generator = "upload_generator")
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "uuid")
    private User uploader;
    private String fileName;
    private ZonedDateTime uploadDate;
    private long size;
    private boolean hidden = false;

    public String getIdString() {
        return Long.toString(id, 36);
    }

    public UploadDto toDto() {
        return new UploadDto(
                getIdString(),
                uploader.getUsername(),
                BackendApplication.BASE_URL + "view/" + getIdString(),
                BackendApplication.API_BASE_URL + "view/" + getIdString() + "/raw",
                size,
                // remove the unique filename slug
                fileName.substring(5),
                uploadDate.toString()
        );
    }

    public boolean isPublic() {
        return !hidden;
    }
}
