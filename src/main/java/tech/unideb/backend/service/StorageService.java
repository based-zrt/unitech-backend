package tech.unideb.backend.service;

import org.springframework.core.io.Resource;
import tech.unideb.backend.model.Upload;

public interface StorageService {

    default void init() {}

    void store(Upload upload, Resource data);

    Resource load(Upload upload);

    void delete(Upload upload);
}
