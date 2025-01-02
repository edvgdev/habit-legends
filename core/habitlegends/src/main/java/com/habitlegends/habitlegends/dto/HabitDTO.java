package com.habitlegends.habitlegends.dto;

import java.time.LocalDateTime;

public class HabitDTO {

    private Long id;
    private String name;
    private String description;
    private Integer categoryId;
    private Integer baseExpReward;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    public HabitDTO() {
    }

    public HabitDTO(Long id, String name, String description, Integer categoryId, Integer baseExpReward,
            LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.categoryId = categoryId;
        this.baseExpReward = baseExpReward;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
    public Integer getBaseExpReward() {
        return baseExpReward;
    }
    public void setBaseExpReward(Integer baseExpReward) {
        this.baseExpReward = baseExpReward;
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
