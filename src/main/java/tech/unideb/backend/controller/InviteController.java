package tech.unideb.backend.controller;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Nullable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.unideb.backend.dto.InviteDto;
import tech.unideb.backend.dto.InviteListResponse;
import tech.unideb.backend.model.Invite;
import tech.unideb.backend.security.annotations.IsAdmin;
import tech.unideb.backend.service.InviteService;

import java.time.ZonedDateTime;

@RestController
@RequestMapping(path = "/invite", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class InviteController extends AbstractBackendController {
    private final InviteService inviteService;

    @IsAdmin
    @GetMapping("/list")
    public InviteListResponse listInvites() {
        return new InviteListResponse(inviteService.listAllInvites().stream().map(Invite::toDto).toList());
    }

    public record InviteCreationRequest(int maxUses, @Nullable ZonedDateTime expiryDate) {}

    @IsAdmin
    @PostMapping("/create")
    public InviteDto createInvite(@RequestBody InviteCreationRequest request) {
        var user = getCurrentUser();
        var invite = new Invite(ZonedDateTime.now(), user, request.expiryDate(), 0, request.maxUses());
        inviteService.save(invite);
        return invite.toDto();
    }

    @IsAdmin
    @PostMapping("/invalidate")
    public ResponseEntity<?> invalidateInvite(@RequestBody String key) {
        var invite = inviteService.getByKey(key);
        if (invite == null) {
            return ResponseEntity.notFound().build();
        }
        invite.setExpiryDate(ZonedDateTime.now());
        inviteService.save(invite);
        return ResponseEntity.ok().build();
    }
}
