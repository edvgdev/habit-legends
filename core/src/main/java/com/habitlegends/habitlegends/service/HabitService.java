package com.habitlegends.habitlegends.service;

import java.util.List;

import com.habitlegends.habitlegends.details.HabitAndStatRewardsDetails;
import com.habitlegends.habitlegends.details.HabitDetails;
import com.habitlegends.habitlegends.dto.HabitDTO;
import com.habitlegends.habitlegends.entity.Habit;

public interface HabitService {
    HabitDetails createHabit(HabitAndStatRewardsDetails habitAndStatRewardsDetails);

    HabitDetails getHabitDetailsById(Long id);

    List<HabitDetails> getAllHabitDetails();

    HabitDTO getHabitDtoById(Long id);

    HabitDetails updateHabit(Long id, HabitAndStatRewardsDetails habitAndStatRewardsDetails);

    void deleteHabit(Long id);

    Habit getHabitById(Long id);

}
