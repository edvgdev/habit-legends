package com.habitlegends.habitlegends.userplan;

import java.time.LocalDateTime;

public class UserPlanDTO {

    private Integer id;
    private String name;
    private String description;
    private float expMultiplier;
    private float statMultiplier;
    private double price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UserPlanDTO() {
    }

    public UserPlanDTO(Integer id, String name, String description, float expMultiplier, float statMultiplier,
            double price, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.expMultiplier = expMultiplier;
        this.statMultiplier = statMultiplier;
        this.price = price;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public float getExpMultiplier() {
        return expMultiplier;
    }

    public void setExpMultiplier(float expMultiplier) {
        this.expMultiplier = expMultiplier;
    }

    public float getStatMultiplier() {
        return statMultiplier;
    }

    public void setStatMultiplier(float statMultiplier) {
        this.statMultiplier = statMultiplier;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
