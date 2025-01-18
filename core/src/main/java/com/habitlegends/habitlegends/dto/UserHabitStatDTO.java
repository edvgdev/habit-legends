package com.habitlegends.habitlegends.dto;

import java.time.LocalDateTime;

public class UserHabitStatDTO {

    private Long id;
    private Long userHabitId;
    private Integer statId;
    private Integer currentPoints;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UserHabitStatDTO() {
    }
    
    public UserHabitStatDTO(Long id, Long userHabitId, Integer statId, Integer currentPoints, LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = id;
        this.userHabitId = userHabitId;
        this.statId = statId;
        this.currentPoints = currentPoints;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserHabitId() {
        return userHabitId;
    }

    public void setUserHabitId(Long userHabitId) {
        this.userHabitId = userHabitId;
    }

    public Integer getStatId() {
        return statId;
    }

    public void setStatId(Integer statId) {
        this.statId = statId;
    }

    public Integer getCurrentPoints() {
        return currentPoints;
    }

    public void setCurrentPoints(Integer currentPoints) {
        this.currentPoints = currentPoints;
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
