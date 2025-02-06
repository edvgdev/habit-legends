package com.habitlegends.habitlegends.userquest;

import com.habitlegends.habitlegends.quest.QuestDetails;

public class UserQuestDetails {
    private Long userId;
    private QuestDetails questDetails;

    public UserQuestDetails() {
    }

    public UserQuestDetails(Long userId, QuestDetails questDetails) {
        this.userId = userId;
        this.questDetails = questDetails;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public QuestDetails getQuestDetails() {
        return questDetails;
    }

    public void setQuestDetails(QuestDetails questDetails) {
        this.questDetails = questDetails;
    }

}
