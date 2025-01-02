package com.habitlegends.habitlegends.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.habitlegends.habitlegends.entity.HabitCategory;

public interface HabitCategoryRepository extends JpaRepository<HabitCategory, Integer> {
    
    
}
