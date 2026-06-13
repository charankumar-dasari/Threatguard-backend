package com.charan.maliciousurldetector.repository;

import com.charan.maliciousurldetector.entity.UrlScan;
import com.charan.maliciousurldetector.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UrlScanRepository extends JpaRepository<UrlScan, Long> {

    List<UrlScan> findByUserOrderByScannedAtDesc(User user);

    long countByUser(User user);

    long countByUserAndMalicious(User user, boolean malicious);
}