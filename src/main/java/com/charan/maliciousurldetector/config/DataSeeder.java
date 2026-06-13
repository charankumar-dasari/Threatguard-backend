package com.charan.maliciousurldetector.config;

import com.charan.maliciousurldetector.entity.MaliciousUrl;
import com.charan.maliciousurldetector.repository.MaliciousUrlRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final MaliciousUrlRepository maliciousUrlRepository;

    public DataSeeder(MaliciousUrlRepository maliciousUrlRepository) {
        this.maliciousUrlRepository = maliciousUrlRepository;
    }

    @Override
    public void run(String... args) {

        addUrlIfNotExists(
                "http://fake-bank-login.com",
                "PHISHING",
                "Fake banking login page"
        );

        addUrlIfNotExists(
                "http://free-gift-verify-account.com",
                "SCAM",
                "Fake gift verification scam"
        );

        addUrlIfNotExists(
                "http://paypal-password-update.net",
                "CREDENTIAL_THEFT",
                "Fake PayPal password update page"
        );
    }

    private void addUrlIfNotExists(String url, String threatType, String description) {

        if (!maliciousUrlRepository.existsByUrl(url)) {

            MaliciousUrl maliciousUrl = new MaliciousUrl();
            maliciousUrl.setUrl(url);
            maliciousUrl.setThreatType(threatType);
            maliciousUrl.setDescription(description);

            maliciousUrlRepository.save(maliciousUrl);
        }
    }
}