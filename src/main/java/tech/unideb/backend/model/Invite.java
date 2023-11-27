package tech.unideb.backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;

/**
 * Invite model.
 */
@Entity
@Data
public class Invite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "invite_key")
    private String key;

    private ZonedDateTime createdDate;

    @ManyToOne
    @JoinColumn(name = "creator_id", referencedColumnName = "uuid")
    private User creator;

    private ZonedDateTime expiryDate;

    private int uses;

    private int maxUses;
}
