package com.charan.maliciousurldetector.service;

import com.charan.maliciousurldetector.dto.DashboardStatsResponse;
import com.charan.maliciousurldetector.dto.UrlScanResponse;
import com.charan.maliciousurldetector.entity.UrlScan;
import com.charan.maliciousurldetector.entity.User;
import com.charan.maliciousurldetector.repository.MaliciousUrlRepository;
import com.charan.maliciousurldetector.repository.UrlScanRepository;
import com.charan.maliciousurldetector.repository.UserRepository;
import com.charan.maliciousurldetector.util.UrlThreatAnalyzer;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UrlScanService {

    private final UrlScanRepository urlScanRepository;
    private final UserRepository userRepository;
    private final MaliciousUrlRepository maliciousUrlRepository;
    private final UrlThreatAnalyzer urlThreatAnalyzer;

    public UrlScanService(UrlScanRepository urlScanRepository,
                          UserRepository userRepository,
                          MaliciousUrlRepository maliciousUrlRepository,
                          UrlThreatAnalyzer urlThreatAnalyzer) {
        this.urlScanRepository = urlScanRepository;
        this.userRepository = userRepository;
        this.maliciousUrlRepository = maliciousUrlRepository;
        this.urlThreatAnalyzer = urlThreatAnalyzer;
    }

    public UrlScanResponse scanUrl(String url, Authentication authentication) {

        User user = getLoggedInUser(authentication);

        int threatScore = urlThreatAnalyzer.calculateThreatScore(url);

        boolean existsInMaliciousDatabase = maliciousUrlRepository.existsByUrl(url);

        if (existsInMaliciousDatabase) {
            threatScore = 100;
        }

        boolean malicious = urlThreatAnalyzer.isMalicious(threatScore);

        String resultMessage = urlThreatAnalyzer.getResultMessage(threatScore);

        UrlScan urlScan = new UrlScan();
        urlScan.setScannedUrl(url);
        urlScan.setThreatScore(threatScore);
        urlScan.setMalicious(malicious);
        urlScan.setResultMessage(resultMessage);
        urlScan.setUser(user);

        UrlScan savedScan = urlScanRepository.save(urlScan);

        return mapToResponse(savedScan);
    }

    public List<UrlScanResponse> getMyScanHistory(Authentication authentication) {

        User user = getLoggedInUser(authentication);

        List<UrlScan> scans = urlScanRepository.findByUserOrderByScannedAtDesc(user);

        return scans.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public DashboardStatsResponse getDashboardStats(Authentication authentication) {

        User user = getLoggedInUser(authentication);

        long totalScans = urlScanRepository.countByUser(user);

        long maliciousScans = urlScanRepository.countByUserAndMalicious(user, true);

        long safeScans = urlScanRepository.countByUserAndMalicious(user, false);

        double threatRate = 0;

        if (totalScans > 0) {
            threatRate = ((double) maliciousScans / totalScans) * 100;
        }

        return new DashboardStatsResponse(
                totalScans,
                maliciousScans,
                safeScans,
                Math.round(threatRate)
        );
    }

    private User getLoggedInUser(Authentication authentication) {

        String email = authentication.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Logged-in user not found"));
    }

    private UrlScanResponse mapToResponse(UrlScan scan) {

        return new UrlScanResponse(
                scan.getId(),
                scan.getScannedUrl(),
                scan.getThreatScore(),
                scan.isMalicious(),
                scan.getResultMessage(),
                scan.getScannedAt()
        );
    }
}