package com.charan.maliciousurldetector.dto;

import jakarta.validation.constraints.NotBlank;

public class UrlScanRequest {

    @NotBlank(message = "URL is required")
    private String url;

    public UrlScanRequest() {
    }

    public UrlScanRequest(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}