package tech.unideb.backend.service.impl;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;
import tech.unideb.backend.model.Invite;
import tech.unideb.backend.repository.InviteRepository;
import tech.unideb.backend.service.InviteService;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class InviteServiceImpl implements InviteService {
    private final InviteRepository inviteRepository;

    @Nullable
    @Override
    public Invite getByKey(@NotNull String key) {
        return inviteRepository.findByKey(key).orElse(null);
    }

    @Override
    public boolean isValid(@NotNull Invite i) {
        var notExpired = i.getExpiryDate() != null && i.getExpiryDate().isAfter(ZonedDateTime.now());
        var hasUsesLeft = i.getUses() < i.getMaxUses();
        return notExpired && hasUsesLeft;
    }

    @Override
    public void save(Invite invite) {
        inviteRepository.save(invite);
    }
}
