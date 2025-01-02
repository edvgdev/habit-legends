package com.habitlegends.habitlegends.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.habitlegends.habitlegends.dto.HabitDTO;
import com.habitlegends.habitlegends.entity.Habit;
import com.habitlegends.habitlegends.repository.HabitRepository;
import com.habitlegends.habitlegends.service.HabitService;

@Service
public class HabitServiceImpl implements HabitService {

    private final HabitRepository habitRepository;

    private final ModelMapper modelMapper;

    public HabitServiceImpl(HabitRepository habitRepository, ModelMapper modelMapper) {
        this.habitRepository = habitRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public HabitDTO createHabit(HabitDTO habitDTO) {
        Habit habit = modelMapper.map(habitDTO, Habit.class);
        Habit savedHabit = habitRepository.save(habit);
        return modelMapper.map(savedHabit, HabitDTO.class);
    }

    @Override
    public HabitDTO updateHabit(Long id, HabitDTO habitDTO) {
        Habit habit = habitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Habit not found"));
        modelMapper.map(habitDTO, habit);
        Habit updatedHabit = habitRepository.save(habit);
        return modelMapper.map(updatedHabit, HabitDTO.class);
    }

    @Override
    public HabitDTO getHabitById(Long id) {
        Habit habit = habitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Habit not found"));
        return modelMapper.map(habit, HabitDTO.class);
    }

    @Override
    public List<HabitDTO> getAllHabits() {
        return habitRepository.findAll()
                .stream()
                .map(habit -> modelMapper.map(habit, HabitDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteHabit(Long id) {
        if (!habitRepository.existsById(id)) {
            throw new RuntimeException("Habit not found");
        }
        habitRepository.deleteById(id);
    }
}
