package com.habitlegends.habitlegends.service;

import java.util.List;

import com.habitlegends.habitlegends.dto.HabitCompletionDTO;

public interface HabitCompletionService {
    HabitCompletionDTO createHabitCompletion(HabitCompletionDTO habitCompletionDTO);

    HabitCompletionDTO getHabitCompletionById(Long id);

    List<HabitCompletionDTO> getAllHabitCompletions();

    HabitCompletionDTO updateHabitCompletion(Long id, HabitCompletionDTO habitCompletionDTO);

    void deleteHabitCompletion(Long id);
}
