package com.charan.maliciousurldetector.repository;

import com.charan.maliciousurldetector.entity.MaliciousUrl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MaliciousUrlRepository extends JpaRepository<MaliciousUrl, Long> {

    Optional<MaliciousUrl> findByUrl(String url);

    boolean existsByUrl(String url);
}