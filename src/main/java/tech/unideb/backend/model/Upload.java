package tech.unideb.backend.model;

import jakarta.persistence.*;
import java.time.ZonedDateTime;

/**
 * Upload model.
 */
@Entity
@Table(name = "uploads")
public class Upload {
    @Id
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "uuid")
    private User uploader;
    private String fileName;
    private ZonedDateTime uploadDate;
    private long size;
}
