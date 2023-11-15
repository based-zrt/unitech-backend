package tech.unideb.backend.service.impl.storage;

import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.BucketAlreadyExistsException;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import tech.unideb.backend.model.Upload;
import tech.unideb.backend.service.StorageService;

import java.io.IOException;
import java.net.URI;

@Service
@Profile("prod")
public class S3StorageServiceImpl implements StorageService {
    private static final String BUCKET = System.getenv("S3_BUCKET");
    private S3Client client;

    @Override
    public void init() {
        client = S3Client.builder()
                .endpointOverride(URI.create(System.getenv("S3_HOST")))
                .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
                .build();

        try {
            client.createBucket(b -> b.bucket(BUCKET));
        } catch (BucketAlreadyExistsException ignored) { }
    }

    @Override
    public void store(Upload upload, Resource data) throws IOException {
        var request = PutObjectRequest.builder()
                .bucket(BUCKET)
                .key(upload.getIdString())
                .build();

        client.putObject(request, RequestBody.fromInputStream(data.getInputStream(), data.contentLength()));
    }

    @Override
    public Resource load(Upload upload) {
        var request = GetObjectRequest.builder()
                .bucket(BUCKET)
                .key(upload.getIdString())
                .build();

        var result = client.getObjectAsBytes(request);
        return new ByteArrayResource(result.asByteArray());
    }

    @Override
    public void delete(Upload upload) {
        var request = DeleteObjectRequest.builder()
                .bucket(BUCKET)
                .key(upload.getIdString())
                .build();

        client.deleteObject(request);
    }
}
