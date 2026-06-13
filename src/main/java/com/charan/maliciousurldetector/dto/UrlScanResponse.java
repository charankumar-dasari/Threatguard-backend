package com.charan.maliciousurldetector.dto;

import java.time.LocalDateTime;

public class UrlScanResponse {

    private Long scanId;
    private String scannedUrl;
    private int threatScore;
    private boolean malicious;
    private String resultMessage;
    private LocalDateTime scannedAt;

    public UrlScanResponse() {
    }

    public UrlScanResponse(Long scanId, String scannedUrl, int threatScore,
                           boolean malicious, String resultMessage, LocalDateTime scannedAt) {
        this.scanId = scanId;
        this.scannedUrl = scannedUrl;
        this.threatScore = threatScore;
        this.malicious = malicious;
        this.resultMessage = resultMessage;
        this.scannedAt = scannedAt;
    }

    public Long getScanId() {
        return scanId;
    }

    public String getScannedUrl() {
        return scannedUrl;
    }

    public int getThreatScore() {
        return threatScore;
    }

    public boolean isMalicious() {
        return malicious;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public LocalDateTime getScannedAt() {
        return scannedAt;
    }

    public void setScanId(Long scanId) {
        this.scanId = scanId;
    }

    public void setScannedUrl(String scannedUrl) {
        this.scannedUrl = scannedUrl;
    }

    public void setThreatScore(int threatScore) {
        this.threatScore = threatScore;
    }

    public void setMalicious(boolean malicious) {
        this.malicious = malicious;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public void setScannedAt(LocalDateTime scannedAt) {
        this.scannedAt = scannedAt;
    }
}