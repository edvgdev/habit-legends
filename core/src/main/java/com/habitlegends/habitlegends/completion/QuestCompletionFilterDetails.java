package com.habitlegends.habitlegends.completion;

import java.time.LocalDateTime;

/**
 * DTO containing the details for filtering quest completions
 */
public class QuestCompletionFilterDetails {
    private Long userId;
    private Long habitId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String description;

    public QuestCompletionFilterDetails() {
    }

    public QuestCompletionFilterDetails(Long userId, Long habitId, LocalDateTime startDate, LocalDateTime endDate,
            String description) {
        this.userId = userId;
        this.habitId = habitId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
