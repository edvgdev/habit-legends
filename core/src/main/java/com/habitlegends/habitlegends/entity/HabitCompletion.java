package com.habitlegends.habitlegends.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "habit_completion")
public class HabitCompletion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_habit_id", nullable = false)
    private UserHabit userHabit;

    @Column(name = "completed_at", nullable = false, updatable = false)
    private LocalDateTime completedAt;

    @Column(name = "description")
    private String description;

    @Column(name = "exp_earned", nullable = false)
    private Integer expEarned;

    @PrePersist
    protected void onCreate() {
        this.completedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserHabit getUserHabit() {
        return userHabit;
    }

    public void setUserHabit(UserHabit userHabit) {
        this.userHabit = userHabit;
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
