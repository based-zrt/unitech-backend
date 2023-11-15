package tech.unideb.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import tech.unideb.backend.component.UploadIdGenerator;

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

    public String getIdString() {
        return Long.toString(id, 36);
    }
}
