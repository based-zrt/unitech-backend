package tech.unideb.backend.service;

import tech.unideb.backend.model.AuditAction;

/**
 * Service interface for audit.
 */
public interface AuditService {
    void audit(AuditAction action, String message);
}
