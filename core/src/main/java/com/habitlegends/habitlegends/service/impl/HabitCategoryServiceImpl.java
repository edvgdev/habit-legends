package com.habitlegends.habitlegends.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.habitlegends.habitlegends.dto.HabitCategoryDTO;
import com.habitlegends.habitlegends.entity.HabitCategory;
import com.habitlegends.habitlegends.repository.HabitCategoryRepository;
import com.habitlegends.habitlegends.service.HabitCategoryService;

@Service
public class HabitCategoryServiceImpl implements HabitCategoryService {

    private final HabitCategoryRepository habitCategoryRepository;

    private final ModelMapper modelMapper;

    public HabitCategoryServiceImpl(HabitCategoryRepository habitCategoryRepository, ModelMapper modelMapper) {
        this.habitCategoryRepository = habitCategoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public HabitCategoryDTO createHabitCategory(HabitCategoryDTO habitCategoryDTO) {
        HabitCategory habitCategory = modelMapper.map(habitCategoryDTO, HabitCategory.class);
        HabitCategory savedHabitCategory = habitCategoryRepository.save(habitCategory);
        return modelMapper.map(savedHabitCategory, HabitCategoryDTO.class);
    }

    @Override
    public HabitCategoryDTO updateHabitCategory(Integer id, HabitCategoryDTO habitCategoryDTO) {
        HabitCategory habitCategory = habitCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Habit Category not found"));

        modelMapper.map(habitCategoryDTO, habitCategory);
        HabitCategory updatedHabitCategory = habitCategoryRepository.save(habitCategory);
        return modelMapper.map(updatedHabitCategory, HabitCategoryDTO.class);
    }

    @Override
    public HabitCategoryDTO getHabitCategoryById(Integer id) {
        HabitCategory habitCategory = habitCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Habit Category not found"));
        return modelMapper.map(habitCategory, HabitCategoryDTO.class);
    }

    @Override
    public List<HabitCategoryDTO> getAllHabitCategories() {
        return habitCategoryRepository.findAll()
                .stream()
                .map(habitCategory -> modelMapper.map(habitCategory, HabitCategoryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteHabitCategory(Integer id) {
        if (!habitCategoryRepository.existsById(id)) {
            throw new RuntimeException("Habit not found");
        }
        habitCategoryRepository.deleteById(id);
    }

}
