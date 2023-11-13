package tech.unideb.backend.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.unideb.backend.model.Audit;
import tech.unideb.backend.model.AuditAction;
import tech.unideb.backend.model.User;
import tech.unideb.backend.repository.AuditRepository;
import tech.unideb.backend.service.AuditService;

/**
 * Service implementation for audit.
 */
@Service
@RequiredArgsConstructor
public class AuditServiceImpl implements AuditService {

    private final AuditRepository auditRepository;

    @Override
    public void audit(User user, AuditAction action, String message) {
        Audit audit = new Audit(user, action, message);
        auditRepository.save(audit);
    }
}
