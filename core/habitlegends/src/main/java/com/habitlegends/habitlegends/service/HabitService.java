package com.habitlegends.habitlegends.service;

import java.util.List;

import com.habitlegends.habitlegends.dto.HabitDTO;

public interface HabitService {
    HabitDTO createHabit(HabitDTO habitDTO);

    HabitDTO getHabitById(Long id);

    List<HabitDTO> getAllHabits();

    HabitDTO updateHabit(Long id, HabitDTO habitDTO);

    void deleteHabit(Long id);
}
