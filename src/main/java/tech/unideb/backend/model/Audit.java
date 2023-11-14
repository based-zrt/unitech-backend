package tech.unideb.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Audit log model.
 */
@Data
@Entity
@Table(name = "audits")
@NoArgsConstructor
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
