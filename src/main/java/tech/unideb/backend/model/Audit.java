package tech.unideb.backend.model;

import jakarta.persistence.*;

/**
 * Audit model.
 */
@Entity
@Table(name = "audits")
public class Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "uuid")
    private User user;
    @Enumerated(EnumType.STRING)
    private AuditAction action;

    private String message;

    public Audit(User user, AuditAction action, String message) {
        this.user = user;
        this.action = action;
        this.message = message;
    }
}
