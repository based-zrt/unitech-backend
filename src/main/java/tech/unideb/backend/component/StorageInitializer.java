package tech.unideb.backend.component;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tech.unideb.backend.service.StorageService;

import java.io.IOException;

/**
 * This component initializes the storage service.
 */
@Component
@RequiredArgsConstructor
public class StorageInitializer {
    private final StorageService storageService;

    @PostConstruct
    public void initStorage() throws IOException {
        storageService.init();
    }
}
