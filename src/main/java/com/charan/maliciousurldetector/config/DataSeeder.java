package com.charan.maliciousurldetector.config;

import com.charan.maliciousurldetector.entity.MaliciousUrl;
import com.charan.maliciousurldetector.entity.Role;
import com.charan.maliciousurldetector.entity.User;
import com.charan.maliciousurldetector.repository.MaliciousUrlRepository;
import com.charan.maliciousurldetector.repository.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final MaliciousUrlRepository maliciousUrlRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(MaliciousUrlRepository maliciousUrlRepository,
                      UserRepository userRepository,
                      PasswordEncoder passwordEncoder) {
        this.maliciousUrlRepository = maliciousUrlRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        createAdminIfNotExists();

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

    private void createAdminIfNotExists() {

        String adminEmail = "admin@threatguard.com";

        User admin = userRepository.findByEmail(adminEmail).orElse(null);

        if (admin == null) {

            User newAdmin = new User();
            newAdmin.setFullName("ThreatGuard Admin");
            newAdmin.setEmail(adminEmail);
            newAdmin.setPassword(passwordEncoder.encode("admin123"));
            newAdmin.setRole(Role.ADMIN);

            userRepository.save(newAdmin);

        } else {

            admin.setRole(Role.ADMIN);
            admin.setPassword(passwordEncoder.encode("admin123"));

            userRepository.save(admin);
        }
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