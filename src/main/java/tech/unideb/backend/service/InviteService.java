package tech.unideb.backend.service;

import tech.unideb.backend.model.Invite;

public interface InviteService {

    Invite getByKey(String key);

    boolean isValid(Invite invite);

    void save(Invite invite);
}
