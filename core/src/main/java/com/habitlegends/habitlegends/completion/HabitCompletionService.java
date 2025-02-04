package com.habitlegends.habitlegends.completion;

import java.time.LocalDateTime;
import java.util.List;

public interface HabitCompletionService {
    HabitCompletionDTO createHabitCompletion(HabitCompletionDTO habitCompletionDTO);

    HabitCompletionDTO getHabitCompletionById(Long id);

    List<HabitCompletionDTO> getAllHabitCompletions();

    List<HabitCompletionDTO> getAllHabitCompletionsOfUser(Long userId);

    List<HabitCompletionDTO> getAllHabitCompletionsOfUserToday(Long userId);

    HabitCompletionDTO updateHabitCompletion(Long id, HabitCompletionDTO habitCompletionDTO);

    void deleteHabitCompletion(Long id);

    List<HabitCompletionDTO> getCompletionsByFilter(Long userId, Long habitId, LocalDateTime startDate,
            LocalDateTime endDate, String description);

}
