package com.habitlegends.habitlegends.userquest;

import java.time.LocalDateTime;

public class UserQuestDTO {

    private Long id;
    private Long userId;
    private Long questId;
    private LocalDateTime createdAt;

    public UserQuestDTO() {
    }

    public UserQuestDTO(Long id, Long userId, Long questId, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.questId = questId;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getQuestId() {
        return questId;
    }

    public void setQuestId(Long questId) {
        this.questId = questId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
