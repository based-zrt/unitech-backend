package tech.unideb.backend.component;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.security.SecureRandom;

/**
 * Upload id generator.
 */
public class UploadIdGenerator implements IdentifierGenerator {
    private final SecureRandom random = new SecureRandom();

    @Override
    public Object generate(SharedSessionContractImplementor session, Object object) {
        return random.nextLong(0xFFFFFFFFL, 0xFFFFFFFFFL);
    }
}
