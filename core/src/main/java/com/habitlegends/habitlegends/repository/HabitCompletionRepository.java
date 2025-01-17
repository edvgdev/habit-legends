package com.habitlegends.habitlegends.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.habitlegends.habitlegends.entity.HabitCompletion;

public interface HabitCompletionRepository extends JpaRepository<HabitCompletion, Long> {
    
}
