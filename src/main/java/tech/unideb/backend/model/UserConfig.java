package tech.unideb.backend.model;

import jakarta.persistence.*;

/**
 * UserConfig model.
 */
@Entity
@Table(name = "user_configs")
public class UserConfig {
    @Id
    private long id;
    private String uploadSecret;
    private boolean embedEnabled;
    private String embedColor;
    private String embedTitle;
    private String embedDescription;
}
