package com.habitlegends.habitlegends.userquest;

import java.util.List;

public interface UserHabitService {
    UserHabitDTO createUserHabit(UserHabitDetails userHabitDetails);

    UserHabitDTO getUserHabitById(Long id);

    List<UserHabitDTO> getAllUserHabits();

    UserHabitDTO updateUserHabit(Long id, UserHabitDTO userHabitDTO);

    void deleteUserHabit(Long id);

    List<UserHabitDetails> getAllUserHabitDetails(Long userId);

}
