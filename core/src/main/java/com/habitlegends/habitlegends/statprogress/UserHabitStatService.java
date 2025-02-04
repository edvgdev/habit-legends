package com.habitlegends.habitlegends.statprogress;

import java.util.List;

public interface UserHabitStatService {
    UserHabitStatDTO createUserHabitStat(UserHabitStatDTO userHabitStatDTO);

    UserHabitStatDTO getUserHabitStatById(Long id);

    List<UserHabitStatDTO> getAllUserHabitStats();

    UserHabitStatDTO updateUserHabitStat(Long id, UserHabitStatDTO userHabitStatDTO);

    void deleteUserHabitStat(Long id);

    List<UserHabitStatDTO> initializeUserHabitStatForNewUser(Long userId);

    void addPoints(Long userId, Long habitId);

    List<UserStatDetails> getAllUserStatByUser(Long userId);
}
