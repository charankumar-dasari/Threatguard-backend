package com.charan.maliciousurldetector.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "url_scans")
public class UrlScan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String scannedUrl;

    private int threatScore;

    private boolean malicious;

    private String resultMessage;

    private LocalDateTime scannedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public UrlScan() {
    }

    public UrlScan(Long id, String scannedUrl, int threatScore, boolean malicious,
                   String resultMessage, LocalDateTime scannedAt, User user) {
        this.id = id;
        this.scannedUrl = scannedUrl;
        this.threatScore = threatScore;
        this.malicious = malicious;
        this.resultMessage = resultMessage;
        this.scannedAt = scannedAt;
        this.user = user;
    }

    @PrePersist
    public void setScannedAtBeforeSave() {
        this.scannedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
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

    public User getUser() {
        return user;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setUser(User user) {
        this.user = user;
    }
}