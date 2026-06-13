package com.charan.maliciousurldetector.dto;

public class DashboardStatsResponse {

    private long totalScans;
    private long maliciousScans;
    private long safeScans;
    private double threatRate;

    public DashboardStatsResponse() {
    }

    public DashboardStatsResponse(long totalScans, long maliciousScans, long safeScans, double threatRate) {
        this.totalScans = totalScans;
        this.maliciousScans = maliciousScans;
        this.safeScans = safeScans;
        this.threatRate = threatRate;
    }

    public long getTotalScans() {
        return totalScans;
    }

    public void setTotalScans(long totalScans) {
        this.totalScans = totalScans;
    }

    public long getMaliciousScans() {
        return maliciousScans;
    }

    public void setMaliciousScans(long maliciousScans) {
        this.maliciousScans = maliciousScans;
    }

    public long getSafeScans() {
        return safeScans;
    }

    public void setSafeScans(long safeScans) {
        this.safeScans = safeScans;
    }

    public double getThreatRate() {
        return threatRate;
    }

    public void setThreatRate(double threatRate) {
        this.threatRate = threatRate;
    }
}