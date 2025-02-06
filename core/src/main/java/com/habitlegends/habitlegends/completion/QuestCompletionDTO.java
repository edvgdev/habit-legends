package com.habitlegends.habitlegends.completion;

import java.time.LocalDateTime;

/**
 * DTO class for quest completions
 */
public class QuestCompletionDTO {

    private Long id;
    private Long userId;
    private Long questId;
    private LocalDateTime completedAt;
    private String description;
    private Integer expEarned;

    public QuestCompletionDTO() {
    }

    public QuestCompletionDTO(Long id, Long userId, Long questId, LocalDateTime completedAt, String description,
            Integer expEarned) {
        this.id = id;
        this.userId = userId;
        this.questId = questId;
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

    public Long getQuestId() {
        return questId;
    }

    public void setQuestId(Long questId) {
        this.questId = questId;
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
