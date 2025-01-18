package com.habitlegends.habitlegends.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.habitlegends.habitlegends.entity.UserHabit;

public interface UserHabitRepository extends JpaRepository<UserHabit, Long> {
    
}
