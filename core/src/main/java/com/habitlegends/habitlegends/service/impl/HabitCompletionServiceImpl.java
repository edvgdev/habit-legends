package com.habitlegends.habitlegends.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.habitlegends.habitlegends.dto.HabitCompletionDTO;
import com.habitlegends.habitlegends.entity.HabitCompletion;
import com.habitlegends.habitlegends.repository.HabitCompletionRepository;
import com.habitlegends.habitlegends.service.HabitCompletionService;

@Service
public class HabitCompletionServiceImpl implements HabitCompletionService {

    private final HabitCompletionRepository habitCompletionRepository;

    private final ModelMapper modelMapper;

    public HabitCompletionServiceImpl(HabitCompletionRepository habitCompletionRepository, ModelMapper modelMapper) {
        this.habitCompletionRepository = habitCompletionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public HabitCompletionDTO createHabitCompletion(HabitCompletionDTO habitCompletionDTO) {
        HabitCompletion habitCompletion = modelMapper.map(habitCompletionDTO, HabitCompletion.class);
        HabitCompletion savedHabitCompletion = habitCompletionRepository.save(habitCompletion);
        return modelMapper.map(savedHabitCompletion, HabitCompletionDTO.class);
    }

    @Override
    public HabitCompletionDTO updateHabitCompletion(Long id, HabitCompletionDTO habitCompletionDTO) {
        HabitCompletion habitCompletion = habitCompletionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("HabitCompletion not found"));
        modelMapper.map(habitCompletionDTO, habitCompletion);
        HabitCompletion updatedHabitCompletion = habitCompletionRepository.save(habitCompletion);
        return modelMapper.map(updatedHabitCompletion, HabitCompletionDTO.class);
    }

    @Override
    public HabitCompletionDTO getHabitCompletionById(Long id) {
        HabitCompletion habitCompletion = habitCompletionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("HabitCompletion not found"));
        return modelMapper.map(habitCompletion, HabitCompletionDTO.class);
    }

    @Override
    public List<HabitCompletionDTO> getAllHabitCompletions() {
        return habitCompletionRepository.findAll()
                .stream()
                .map(habitCompletion -> modelMapper.map(habitCompletion, HabitCompletionDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteHabitCompletion(Long id) {
        if (!habitCompletionRepository.existsById(id)) {
            throw new RuntimeException("HabitCompletion not found");
        }
        habitCompletionRepository.deleteById(id);
    }
}
