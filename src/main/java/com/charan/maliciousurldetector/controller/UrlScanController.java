package com.charan.maliciousurldetector.controller;

import com.charan.maliciousurldetector.dto.DashboardStatsResponse;
import com.charan.maliciousurldetector.dto.UrlScanRequest;
import com.charan.maliciousurldetector.dto.UrlScanResponse;
import com.charan.maliciousurldetector.service.UrlScanService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scans")
public class UrlScanController {

    private final UrlScanService urlScanService;

    public UrlScanController(UrlScanService urlScanService) {
        this.urlScanService = urlScanService;
    }

    @PostMapping
    public ResponseEntity<UrlScanResponse> scanUrl(@Valid @RequestBody UrlScanRequest request,
                                                   Authentication authentication) {

        UrlScanResponse response = urlScanService.scanUrl(request.getUrl(), authentication);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/history")
    public ResponseEntity<List<UrlScanResponse>> getMyScanHistory(Authentication authentication) {

        List<UrlScanResponse> history = urlScanService.getMyScanHistory(authentication);

        return ResponseEntity.ok(history);
    }

    @GetMapping("/stats")
    public ResponseEntity<DashboardStatsResponse> getDashboardStats(Authentication authentication) {

        DashboardStatsResponse stats = urlScanService.getDashboardStats(authentication);

        return ResponseEntity.ok(stats);
    }
}