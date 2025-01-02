package com.habitlegends.habitlegends.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.habitlegends.habitlegends.entity.Habit;

public interface HabitRepository extends JpaRepository<Habit, Long>{
    
}
