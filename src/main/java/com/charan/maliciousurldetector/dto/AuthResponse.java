package com.charan.maliciousurldetector.dto;

public class AuthResponse {

    private String token;
    private String tokenType;
    private Long userId;
    private String fullName;
    private String email;
    private String role;

    public AuthResponse() {
    }

    public AuthResponse(String token, String tokenType, Long userId, String fullName, String email, String role) {
        this.token = token;
        this.tokenType = tokenType;
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public Long getUserId() {
        return userId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }
}