package com.habitlegends.habitlegends.details;

import java.util.List;

import com.habitlegends.habitlegends.dto.HabitDTO;

public class HabitAndStatRewardsDetails {

    private HabitDTO habit;
    private List<StatRewardDetails> statRewards;

    public HabitAndStatRewardsDetails() {
    }

    public HabitAndStatRewardsDetails(HabitDTO habit, List<StatRewardDetails> statRewards) {
        this.habit = habit;
        this.statRewards = statRewards;
    }

    public HabitDTO getHabit() {
        return habit;
    }

    public void setHabit(HabitDTO habit) {
        this.habit = habit;
    }

    public List<StatRewardDetails> getStatRewards() {
        return statRewards;
    }

    public void setStatRewards(List<StatRewardDetails> statRewards) {
        this.statRewards = statRewards;
    }

}
