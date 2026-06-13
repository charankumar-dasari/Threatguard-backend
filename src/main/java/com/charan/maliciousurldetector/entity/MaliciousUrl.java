package com.charan.maliciousurldetector.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "malicious_urls")
public class MaliciousUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 1000)
    private String url;

    private String threatType;

    private String description;

    private LocalDateTime addedAt;

    public MaliciousUrl() {
    }

    public MaliciousUrl(Long id, String url, String threatType, String description, LocalDateTime addedAt) {
        this.id = id;
        this.url = url;
        this.threatType = threatType;
        this.description = description;
        this.addedAt = addedAt;
    }

    @PrePersist
    public void setAddedAtBeforeSave() {
        this.addedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getThreatType() {
        return threatType;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getAddedAt() {
        return addedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setThreatType(String threatType) {
        this.threatType = threatType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAddedAt(LocalDateTime addedAt) {
        this.addedAt = addedAt;
    }
}