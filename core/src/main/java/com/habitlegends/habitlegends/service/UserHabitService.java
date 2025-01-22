package com.habitlegends.habitlegends.service;

import java.util.List;

import com.habitlegends.habitlegends.details.UserHabitDetails;
import com.habitlegends.habitlegends.dto.UserHabitDTO;

public interface UserHabitService {
    UserHabitDTO createUserHabit(UserHabitDetails userHabitDetails);

    UserHabitDTO getUserHabitById(Long id);

    List<UserHabitDTO> getAllUserHabits();

    UserHabitDTO updateUserHabit(Long id, UserHabitDTO userHabitDTO);

    void deleteUserHabit(Long id);

    List<UserHabitDetails> getAllUserHabitDetails(Long userId);

}
