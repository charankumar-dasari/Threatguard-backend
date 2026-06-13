package com.charan.maliciousurldetector.service;

import com.charan.maliciousurldetector.dto.AdminScanResponse;
import com.charan.maliciousurldetector.dto.AdminUserResponse;
import com.charan.maliciousurldetector.entity.MaliciousUrl;
import com.charan.maliciousurldetector.entity.UrlScan;
import com.charan.maliciousurldetector.entity.User;
import com.charan.maliciousurldetector.repository.MaliciousUrlRepository;
import com.charan.maliciousurldetector.repository.UrlScanRepository;
import com.charan.maliciousurldetector.repository.UserRepository;
import com.charan.maliciousurldetector.exception.ResourceNotFoundException;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

    private final UserRepository userRepository;
    private final UrlScanRepository urlScanRepository;
    private final MaliciousUrlRepository maliciousUrlRepository;

    public AdminService(UserRepository userRepository,
                        UrlScanRepository urlScanRepository,
                        MaliciousUrlRepository maliciousUrlRepository) {
        this.userRepository = userRepository;
        this.urlScanRepository = urlScanRepository;
        this.maliciousUrlRepository = maliciousUrlRepository;
    }

    public List<AdminUserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToAdminUserResponse)
                .collect(Collectors.toList());
    }

    public List<AdminScanResponse> getAllScans() {
        return urlScanRepository.findAll()
                .stream()
                .map(this::mapToAdminScanResponse)
                .collect(Collectors.toList());
    }

    public List<MaliciousUrl> getAllMaliciousUrls() {
        return maliciousUrlRepository.findAll();
    }

    public MaliciousUrl addMaliciousUrl(MaliciousUrl maliciousUrl) {

        if (maliciousUrlRepository.existsByUrl(maliciousUrl.getUrl())) {
            throw new RuntimeException("URL already exists in malicious database");
        }

        return maliciousUrlRepository.save(maliciousUrl);
    }

    public void deleteMaliciousUrl(Long id) {

        if (!maliciousUrlRepository.existsById(id)) {
            throw new ResourceNotFoundException("Malicious URL not found with id: " + id);
        }

        maliciousUrlRepository.deleteById(id);
    }

    private AdminUserResponse mapToAdminUserResponse(User user) {
        return new AdminUserResponse(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getRole().name(),
                user.getCreatedAt()
        );
    }

    private AdminScanResponse mapToAdminScanResponse(UrlScan scan) {

        String email = "Unknown";

        if (scan.getUser() != null) {
            email = scan.getUser().getEmail();
        }

        return new AdminScanResponse(
                scan.getId(),
                scan.getScannedUrl(),
                scan.getThreatScore(),
                scan.isMalicious(),
                scan.getResultMessage(),
                scan.getScannedAt(),
                email
        );
    }
}