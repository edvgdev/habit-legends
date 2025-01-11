package com.habitlegends.habitlegends.service;

import java.util.List;

import com.habitlegends.habitlegends.details.HabitAndStatRewardsDetails;
import com.habitlegends.habitlegends.details.HabitDetails;

public interface HabitService {
    HabitDetails createHabit(HabitAndStatRewardsDetails habitAndStatRewardsDetails);

    HabitDetails getHabitById(Long id);

    List<HabitDetails> getAllHabits();

    HabitDetails updateHabit(Long id, HabitAndStatRewardsDetails habitAndStatRewardsDetails);

    void deleteHabit(Long id);
}
