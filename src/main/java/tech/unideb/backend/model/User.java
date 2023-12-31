package tech.unideb.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * User model.
 */
@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User implements UserDetails {
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
    private String avatarId;

    @Enumerated(EnumType.STRING)
    private Role role;
    private boolean locked = false;

    @OneToOne
    @JoinColumn(name = "feature_id", referencedColumnName = "id")
    private UserFeatures features;

    @ManyToOne
    @JoinColumn(name = "invite_id", referencedColumnName = "id")
    private Invite invite;

    public User(UUID uuid, String username, String email, Role role) {
        this.uuid = uuid;
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public User(String username, String passwordHash, String email, ZonedDateTime regDate, String regIp,
                ZonedDateTime lastLogin, String lastIp, Role role, UserFeatures features, Invite invite) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.email = email;
        this.regDate = regDate;
        this.regIp = regIp;
        this.lastLogin = lastLogin;
        this.lastIp = lastIp;
        this.role = role;
        this.features = features;
        this.invite = invite;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("base"));
    }

    @Override
    public String getPassword() {
        return passwordHash;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !isLocked();
    }
}
