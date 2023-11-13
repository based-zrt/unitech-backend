package tech.unideb.backend.service;

import tech.unideb.backend.model.AuditAction;
import tech.unideb.backend.model.User;

/**
 * Service interface for audit.
 */
public interface AuditService {
    void audit(User user, AuditAction action, String message);
}
