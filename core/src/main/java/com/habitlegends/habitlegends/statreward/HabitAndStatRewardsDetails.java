package com.habitlegends.habitlegends.statreward;

import java.util.List;

import com.habitlegends.habitlegends.quest.HabitDTO;
import com.habitlegends.habitlegends.stat.StatRewardDetails;

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
