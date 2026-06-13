package com.charan.maliciousurldetector.util;

import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.regex.Pattern;

@Component
public class UrlThreatAnalyzer {

    private static final Pattern IP_PATTERN =
            Pattern.compile("^(http://|https://)?(\\d{1,3}\\.){3}\\d{1,3}.*");

    public int calculateThreatScore(String url) {

        int score = 0;
        String lowerUrl = url.toLowerCase();

        if (!lowerUrl.startsWith("https://")) {
            score += 10;
        }

        if (lowerUrl.length() > 75) {
            score += 15;
        }

        if (countOccurrences(lowerUrl, '.') > 4) {
            score += 15;
        }

        if (countOccurrences(lowerUrl, '-') > 3) {
            score += 10;
        }

        if (lowerUrl.contains("@")) {
            score += 25;
        }

        if (IP_PATTERN.matcher(lowerUrl).matches()) {
            score += 30;
        }

        if (hasSuspiciousTld(lowerUrl)) {
            score += 20;
        }

        if (hasManySubdomains(lowerUrl)) {
            score += 15;
        }

        if (lowerUrl.contains("login")) {
            score += 10;
        }

        if (lowerUrl.contains("verify")) {
            score += 10;
        }

        if (lowerUrl.contains("update")) {
            score += 10;
        }

        if (lowerUrl.contains("free")) {
            score += 10;
        }

        if (lowerUrl.contains("gift")) {
            score += 15;
        }

        if (lowerUrl.contains("bank")) {
            score += 20;
        }

        if (lowerUrl.contains("paypal")) {
            score += 20;
        }

        if (lowerUrl.contains("password")) {
            score += 15;
        }

        return Math.min(score, 100);
    }

    public boolean isMalicious(int score) {
        return score >= 50;
    }

    public String getResultMessage(int score) {

        if (score >= 80) {
            return "High risk malicious URL detected";
        }

        if (score >= 50) {
            return "Suspicious URL detected";
        }

        if (score >= 25) {
            return "Low risk URL. Be careful before opening";
        }

        return "URL appears safe";
    }

    private int countOccurrences(String text, char character) {

        int count = 0;

        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == character) {
                count++;
            }
        }

        return count;
    }

    private boolean hasSuspiciousTld(String url) {
        return url.endsWith(".tk")
                || url.endsWith(".ml")
                || url.endsWith(".ga")
                || url.endsWith(".cf")
                || url.endsWith(".xyz")
                || url.endsWith(".top")
                || url.endsWith(".click")
                || url.endsWith(".info");
    }

    private boolean hasManySubdomains(String url) {

        try {
            URI uri = new URI(url);

            String host = uri.getHost();

            if (host == null) {
                return false;
            }

            String[] parts = host.split("\\.");

            return parts.length > 3;

        } catch (Exception e) {
            return false;
        }
    }
}