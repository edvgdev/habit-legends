package com.habitlegends.habitlegends.dto;

import java.time.LocalDateTime;

public class HabitStatRewardDTO {

    private Long id;
    private Long habitId;
    private Integer statId;
    private Integer baseStatReward;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public HabitStatRewardDTO() {
    }

    public HabitStatRewardDTO(Long id, Long habitId, Integer statId, Integer baseStatReward, LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = id;
        this.habitId = habitId;
        this.statId = statId;
        this.baseStatReward = baseStatReward;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHabitId() {
        return habitId;
    }

    public void setHabitId(Long habitId) {
        this.habitId = habitId;
    }

    public Integer getStatId() {
        return statId;
    }

    public void setStatId(Integer statId) {
        this.statId = statId;
    }

    public Integer getBaseStatReward() {
        return baseStatReward;
    }

    public void setBaseStatReward(Integer baseStatReward) {
        this.baseStatReward = baseStatReward;
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
