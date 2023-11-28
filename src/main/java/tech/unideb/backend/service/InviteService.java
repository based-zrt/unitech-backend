package tech.unideb.backend.service;

import tech.unideb.backend.model.Invite;

import java.util.List;

public interface InviteService {

    Invite getByKey(String key);

    boolean isValid(Invite invite);

    void save(Invite invite);

    List<Invite> listAllInvites();
}
