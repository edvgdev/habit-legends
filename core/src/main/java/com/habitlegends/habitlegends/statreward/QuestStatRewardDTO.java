package com.habitlegends.habitlegends.statreward;

import java.time.LocalDateTime;

public class QuestStatRewardDTO {

    private Long id;
    private Long questId;
    private Integer statId;
    private Integer baseStatReward;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public QuestStatRewardDTO() {
    }

    public QuestStatRewardDTO(Long id, Long questId, Integer statId, Integer baseStatReward, LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = id;
        this.questId = questId;
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

    public Long getQuestId() {
        return questId;
    }

    public void setQuestId(Long questId) {
        this.questId = questId;
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
