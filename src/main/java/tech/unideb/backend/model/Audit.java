package tech.unideb.backend.model;

import jakarta.persistence.*;

/**
 * Audit model.
 */
@Entity
@Table(name = "audits")
public class Audit {
    @Id
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "uuid")
    private User user;
    @Enumerated(EnumType.STRING)
    private AuditAction action;
}
