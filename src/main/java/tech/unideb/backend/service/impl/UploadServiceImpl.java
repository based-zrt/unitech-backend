package tech.unideb.backend.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.unideb.backend.repository.UploadRepository;
import tech.unideb.backend.service.UploadService;

@Service
@RequiredArgsConstructor
public class UploadServiceImpl implements UploadService {
    private final UploadRepository uploadRepository;
}
