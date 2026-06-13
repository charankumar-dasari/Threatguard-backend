package com.charan.maliciousurldetector.controller;

import com.charan.maliciousurldetector.dto.AdminScanResponse;
import com.charan.maliciousurldetector.dto.AdminUserResponse;
import com.charan.maliciousurldetector.entity.MaliciousUrl;
import com.charan.maliciousurldetector.service.AdminService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<AdminUserResponse>> getAllUsers() {
        return ResponseEntity.ok(adminService.getAllUsers());
    }

    @GetMapping("/scans")
    public ResponseEntity<List<AdminScanResponse>> getAllScans() {
        return ResponseEntity.ok(adminService.getAllScans());
    }

    @GetMapping("/malicious-urls")
    public ResponseEntity<List<MaliciousUrl>> getAllMaliciousUrls() {
        return ResponseEntity.ok(adminService.getAllMaliciousUrls());
    }

    @PostMapping("/malicious-urls")
    public ResponseEntity<MaliciousUrl> addMaliciousUrl(@RequestBody MaliciousUrl maliciousUrl) {
        return ResponseEntity.ok(adminService.addMaliciousUrl(maliciousUrl));
    }

    @DeleteMapping("/malicious-urls/{id}")
    public ResponseEntity<String> deleteMaliciousUrl(@PathVariable Long id) {
        adminService.deleteMaliciousUrl(id);
        return ResponseEntity.ok("Malicious URL deleted successfully");
    }
}