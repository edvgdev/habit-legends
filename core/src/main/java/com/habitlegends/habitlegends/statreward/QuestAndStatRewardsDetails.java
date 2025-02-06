package com.habitlegends.habitlegends.statreward;

import java.util.List;

import com.habitlegends.habitlegends.quest.QuestDTO;
import com.habitlegends.habitlegends.stat.StatRewardDetails;

public class QuestAndStatRewardsDetails {

    private QuestDTO quest;
    private List<StatRewardDetails> statRewards;

    public QuestAndStatRewardsDetails() {
    }

    public QuestAndStatRewardsDetails(QuestDTO quest, List<StatRewardDetails> statRewards) {
        this.quest = quest;
        this.statRewards = statRewards;
    }

    public QuestDTO getQuest() {
        return quest;
    }

    public void setQuest(QuestDTO quest) {
        this.quest = quest;
    }

    public List<StatRewardDetails> getStatRewards() {
        return statRewards;
    }

    public void setStatRewards(List<StatRewardDetails> statRewards) {
        this.statRewards = statRewards;
    }

}
