package com.habitlegends.habitlegends.completion;

import java.time.LocalDateTime;

public class HabitCompletionDTO {

    private Long id;
    private Long userId;
    private Long habitId;
    private LocalDateTime completedAt;
    private String description;
    private Integer expEarned;

    public HabitCompletionDTO() {
    }

    public HabitCompletionDTO(Long id, Long userId, Long habitId, LocalDateTime completedAt, String description,
            Integer expEarned) {
        this.id = id;
        this.userId = userId;
        this.habitId = habitId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getHabitId() {
        return habitId;
    }

    public void setHabitId(Long habitId) {
        this.habitId = habitId;
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
