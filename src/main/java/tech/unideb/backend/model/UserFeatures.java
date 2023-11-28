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
@Table(name = "user_features")
@NoArgsConstructor
public class UserFeatures {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @Column(nullable = false)
    private String uploadSecret;
    private long totalUploadSize = 500 * 1024 * 1024; // 500 MB total
    private long uploadMaxSize = 50 * 1024 * 1024; // 50 MB per upload
    private boolean embedEnabled = false;
    @Nullable
    private String embedColor;
    @Nullable
    private String embedTitle;
    @Nullable
    private String embedDescription;

    public UserFeatures(@NotNull String uploadSecret) {
        this.uploadSecret = uploadSecret;
    }
}
