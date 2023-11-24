package tech.unideb.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * UserConfig model.
 */
@Data
@Entity
@Table(name = "user_configs")
@NoArgsConstructor
public class UserConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @Column(nullable = false)
    private String uploadSecret;
    private boolean embedEnabled = false;
    @Nullable
    private String embedColor;
    @Nullable
    private String embedTitle;
    @Nullable
    private String embedDescription;

    public UserConfig(@NotNull String uploadSecret) {
        this.uploadSecret = uploadSecret;
    }
}
