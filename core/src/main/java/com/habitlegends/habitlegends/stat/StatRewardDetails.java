package com.habitlegends.habitlegends.stat;

public class StatRewardDetails {

    private Integer statId;
    private Integer reward;

    public StatRewardDetails() {
    }

    public StatRewardDetails(Integer statId, Integer reward) {
        this.statId = statId;
        this.reward = reward;
    }

    public Integer getStatId() {
        return statId;
    }

    public void setStatId(Integer statId) {
        this.statId = statId;
    }

    public Integer getReward() {
        return reward;
    }

    public void setReward(Integer reward) {
        this.reward = reward;
    }

}
