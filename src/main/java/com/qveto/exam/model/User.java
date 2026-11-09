package com.qveto.exam.model;

import java.time.LocalDateTime;

public class User {
    private final Long id;
    private final String userName;
    private final String passwordHash;
    private final String displayName;
    private final Role role;
    private final boolean active;
    private final LocalDateTime createdAt;

    public User(
        Long id,
        String userName,
        String passwordHash,
        String displayName,
        Role role,
        boolean active,
        LocalDateTime createdAt
    ) {
        this.id = id;
        this.userName = userName;
        this.passwordHash = passwordHash;
        this.displayName = displayName;
        this.role = role;
        this.active = active;
        this.createdAt = createdAt;
    }

    public Long getId() {return id;}
    public String getUserName() {return userName;}
    public String getPasswordHash() {return passwordHash;}
    public String getDisplayName() {return displayName;}
    public Role getRole() {return role;}
    public boolean isActive() {return active;}
    public LocalDateTime getCreatedAt() {return createdAt;} 
}   
