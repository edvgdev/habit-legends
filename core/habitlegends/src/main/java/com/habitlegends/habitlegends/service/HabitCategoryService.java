package com.habitlegends.habitlegends.service;

import java.util.List;

import com.habitlegends.habitlegends.dto.HabitCategoryDTO;

public interface HabitCategoryService {

    HabitCategoryDTO createHabitCategory(HabitCategoryDTO habitCategoryDTO);

    HabitCategoryDTO updateHabitCategory(Integer id, HabitCategoryDTO habitCategoryDTO);

    HabitCategoryDTO getHabitCategoryById(Integer id);

    List<HabitCategoryDTO> getAllHabitCategories();

    void deleteHabitCategory(Integer id);
}
