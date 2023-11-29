package tech.unideb.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("tech.unideb.backend.config")
public class BackendApplication {
    public static final String BASE_URL = System.getenv("BASE_URL");
    public static final String API_BASE_URL = System.getenv("API_BASE_URL");

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }
}
