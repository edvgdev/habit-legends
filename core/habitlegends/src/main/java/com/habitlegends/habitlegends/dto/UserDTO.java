package com.habitlegends.habitlegends.dto;

import java.time.LocalDateTime;

public class UserDTO {
    
    private Long id;
    private String oauthId;
    private String oauthProvider;
    private String username;
    private String email;
    private String passwordHash;
    private String profilePictureLink;
    private Integer planId;
    private Integer roleId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UserDTO() {
    }

    public UserDTO(Long id, String oauthId, String oauthProvider, String username, String email, String passwordHash,
            String profilePictureLink, Integer planId, Integer roleId, LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = id;
        this.oauthId = oauthId;
        this.oauthProvider = oauthProvider;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.profilePictureLink = profilePictureLink;
        this.planId = planId;
        this.roleId = roleId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOauthId() {
        return oauthId;
    }

    public void setOauthId(String oauthId) {
        this.oauthId = oauthId;
    }

    public String getOauthProvider() {
        return oauthProvider;
    }

    public void setOauthProvider(String oauthProvider) {
        this.oauthProvider = oauthProvider;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getProfilePictureLink() {
        return profilePictureLink;
    }

    public void setProfilePictureLink(String profilePictureLink) {
        this.profilePictureLink = profilePictureLink;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    

    
}
