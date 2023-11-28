package tech.unideb.backend.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.unideb.backend.dto.ProfileInfoResponse;
import tech.unideb.backend.repository.UploadRepository;
import tech.unideb.backend.repository.UserRepository;
import tech.unideb.backend.security.annotations.LoggedIn;

import java.util.Optional;

@RestController
@RequestMapping(path = "/profile", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ProfileController {
    private final UserRepository userRepository;
    private final UploadRepository uploadRepository;

    @LoggedIn
    @GetMapping("/info")
    public ProfileInfoResponse getProfileInfo() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        var user = userRepository.findByUsername(username).orElseThrow();
        int count = uploadRepository.countByUploader(user);
        Optional<Long> totalSize = uploadRepository.totalSizeByUploader(user);
        return new ProfileInfoResponse(
                username,
                user.getRole(),
                user.getEmail(),
                user.getFeatures().getUploadSecret(),
                count,
                totalSize.orElse(0L),
                user.getFeatures().getTotalUploadSize());
    }
}
