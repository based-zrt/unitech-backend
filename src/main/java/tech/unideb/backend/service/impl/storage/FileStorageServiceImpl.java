package tech.unideb.backend.service.impl.storage;

import com.google.common.hash.Hashing;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import tech.unideb.backend.model.Upload;
import tech.unideb.backend.service.StorageService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileStorageServiceImpl implements StorageService {
    private static final Path ROOT = Path.of("uploads");

    @Override
    public void store(Upload upload, Resource data) throws IOException {
        var path = getFilePath(upload);
        Files.createDirectories(path.getParent());
        Files.copy(data.getInputStream(), path);
    }

    @Override
    public Resource load(Upload upload) {
        var path = getFilePath(upload);
        return new PathResource(path);
    }

    @Override
    public void delete(Upload upload) throws IOException {
        var path = getFilePath(upload);
        Files.delete(path);
    }

    private Path getFilePath(Upload upload) {
        // You might wonder why the layering?
        // most file systems' performance degrades when there are too many files in a single directory
        var hash = Hashing.sha256().hashString(upload.getFileName(), StandardCharsets.UTF_8);
        var layer1 = hash.toString().substring(0, 2);
        var layer2 = hash.toString().substring(2, 4);
        return ROOT.resolve(layer1).resolve(layer2).resolve(upload.getFileName());
    }
}
