package com.habitlegends.habitlegends.completion;

import java.time.LocalDateTime;

/**
 * DTO containing the details for filtering quest completions
 */
public class QuestCompletionFilterDetails {
    private Long userId;
    private Long questId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String description;

    public QuestCompletionFilterDetails() {
    }

    public QuestCompletionFilterDetails(Long userId, Long questId, LocalDateTime startDate, LocalDateTime endDate,
            String description) {
        this.userId = userId;
        this.questId = questId;
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

    public Long getQuestId() {
        return questId;
    }

    public void setQuestId(Long questId) {
        this.questId = questId;
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
