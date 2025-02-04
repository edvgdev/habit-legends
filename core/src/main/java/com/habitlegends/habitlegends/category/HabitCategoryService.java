package com.habitlegends.habitlegends.category;

import java.util.List;

public interface HabitCategoryService {

    HabitCategoryDTO createHabitCategory(HabitCategoryDTO habitCategoryDTO);

    HabitCategoryDTO updateHabitCategory(Integer id, HabitCategoryDTO habitCategoryDTO);

    HabitCategoryDTO getHabitCategoryById(Integer id);

    List<HabitCategoryDTO> getAllHabitCategories();

    void deleteHabitCategory(Integer id);
}
