package tech.unideb.backend.service;

import org.springframework.core.io.Resource;
import tech.unideb.backend.model.Upload;

import java.io.IOException;

public interface StorageService {

    default void init() throws IOException {}

    void store(Upload upload, Resource data) throws IOException;

    Resource load(Upload upload) throws IOException;

    void delete(Upload upload) throws IOException;
}
