package com.habitlegends.habitlegends.dto;

import java.time.LocalDateTime;

public class HabitCompletionDTO {

    private Long id;
    private Long userHabitId;
    private LocalDateTime completedAt;
    private String description;
    private Integer expEarned;

    public HabitCompletionDTO() {
    }

    public HabitCompletionDTO(Long id, Long userHabitId, LocalDateTime completedAt, String description,
            Integer expEarned) {
        this.id = id;
        this.userHabitId = userHabitId;
        this.completedAt = completedAt;
        this.description = description;
        this.expEarned = expEarned;
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

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getExpEarned() {
        return expEarned;
    }

    public void setExpEarned(Integer expEarned) {
        this.expEarned = expEarned;
    }

    
    
}
