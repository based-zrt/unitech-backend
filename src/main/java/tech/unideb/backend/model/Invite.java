package tech.unideb.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * Invite model.
 */
@Entity
@Data
@Table(name = "invites")
@NoArgsConstructor
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

    public Invite(ZonedDateTime createdDate, User creator, ZonedDateTime expiryDate, int uses, int maxUses) {
        this.key = UUID.randomUUID().toString();
        this.createdDate = createdDate;
        this.creator = creator;
        this.expiryDate = expiryDate;
        this.uses = uses;
        this.maxUses = maxUses;
    }

    public void addUse() {
        this.uses++;
    }
}
