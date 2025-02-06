package com.habitlegends.habitlegends.quest;

import java.util.List;

import com.habitlegends.habitlegends.statreward.QuestStatRewardDTO;

public class QuestDetails {
    private QuestDTO quest;
    private String categoryName;
    private List<QuestStatRewardDTO> questStatRewards;

    public QuestDetails() {
    }

    public QuestDetails(QuestDTO quest, String categoryName, List<QuestStatRewardDTO> questStatRewards) {
        this.quest = quest;
        this.categoryName = categoryName;
        this.questStatRewards = questStatRewards;
    }

    public QuestDTO getQuest() {
        return quest;
    }

    public void setQuest(QuestDTO questDTO) {
        this.quest = questDTO;
    }

    public List<QuestStatRewardDTO> getQuestStatRewards() {
        return questStatRewards;
    }

    public void setQuestStatRewards(List<QuestStatRewardDTO> questStatRewards) {
        this.questStatRewards = questStatRewards;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}
