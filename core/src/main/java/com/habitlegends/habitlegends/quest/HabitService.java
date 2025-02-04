package com.habitlegends.habitlegends.quest;

import java.util.List;

import com.habitlegends.habitlegends.statreward.HabitAndStatRewardsDetails;

public interface HabitService {
    HabitDetails createHabit(HabitAndStatRewardsDetails habitAndStatRewardsDetails);

    HabitDetails getHabitDetailsById(Long id);

    List<HabitDetails> getAllHabitDetails();

    HabitDTO getHabitDtoById(Long id);

    HabitDetails updateHabit(Long id, HabitAndStatRewardsDetails habitAndStatRewardsDetails);

    void deleteHabit(Long id);

    Habit getHabitById(Long id);

}
