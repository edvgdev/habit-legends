package com.habitlegends.habitlegends.userquest;

import java.time.LocalDateTime;

public class UserHabitDTO {

    private Long id;
    private Long userId;
    private Long habitId;
    private LocalDateTime createdAt;

    public UserHabitDTO() {
    }

    public UserHabitDTO(Long id, Long userId, Long habitId, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.habitId = habitId;
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

    public Long getHabitId() {
        return habitId;
    }

    public void setHabitId(Long habitId) {
        this.habitId = habitId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
