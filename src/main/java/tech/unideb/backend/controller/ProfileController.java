package tech.unideb.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.unideb.backend.dto.ProfileInfoResponse;
import tech.unideb.backend.dto.SharexConfig;
import tech.unideb.backend.model.Upload;
import tech.unideb.backend.repository.UploadRepository;
import tech.unideb.backend.security.annotations.LoggedIn;

import java.util.Optional;

@RestController
@RequestMapping(path = "/profile", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ProfileController extends AbstractBackendController {
    private final UploadRepository uploadRepository;

    @LoggedIn
    @GetMapping("/info")
    public ProfileInfoResponse getProfileInfo() {
        var user = getCurrentUser();
        int count = uploadRepository.countByUploader(user);
        Optional<Long> totalSize = uploadRepository.totalSizeByUploader(user);
        var uploads = uploadRepository.findAllByUploader(user);
        return new ProfileInfoResponse(
                user.getUsername(),
                user.getRole(),
                user.getEmail(),
                user.getFeatures().getUploadSecret(),
                count,
                totalSize.orElse(0L),
                user.getFeatures().getTotalUploadSize(),
                uploads.stream().map(Upload::toDto).toList()
        );
    }

    @LoggedIn
    @GetMapping("/sharexConfig")
    public ResponseEntity<?> generateSharexConfig() {
        var user = getCurrentUser();
        var config = new SharexConfig(user.getFeatures().getUploadSecret());
        return ResponseEntity.ok()
                .headers(h -> h.add("Content-Disposition",
                        "attachment; filename=\"unideb-tech.sxcu\""))
                .body(config);
    }
}
