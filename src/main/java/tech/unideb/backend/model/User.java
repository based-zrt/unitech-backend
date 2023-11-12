package tech.unideb.backend.model;

import jakarta.persistence.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
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
}
