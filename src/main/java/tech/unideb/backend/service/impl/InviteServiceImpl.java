package tech.unideb.backend.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.unideb.backend.repository.InviteRepository;
import tech.unideb.backend.service.InviteService;

@Service
@RequiredArgsConstructor
public class InviteServiceImpl implements InviteService {
    private final InviteRepository inviteRepository;
}
