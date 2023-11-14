package tech.unideb.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String uploadSecret;
    private boolean embedEnabled;
    private String embedColor;
    private String embedTitle;
    private String embedDescription;
}
