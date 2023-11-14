package tech.unideb.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * User model.
 */
@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    @Column(unique = true)
    private String username;
    private String passwordHash;
    private String email;
    private ZonedDateTime regDate;
    private String regIp;
    private ZonedDateTime lastLogin;
    private String lastIp;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToOne
    @JoinColumn(name = "config_id", referencedColumnName = "id")
    private UserConfig config;
}
