package com.habitlegends.habitlegends.category;

import java.util.List;

public interface CategoryService {

    CategoryDTO createCategory(CategoryDTO categoryDTO);

    CategoryDTO updateCategory(Integer id, CategoryDTO categoryDTO);

    CategoryDTO getCategoryById(Integer id);

    List<CategoryDTO> getAllCategories();

    void deleteCategory(Integer id);
}
