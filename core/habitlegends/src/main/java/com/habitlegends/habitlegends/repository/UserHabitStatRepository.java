package com.habitlegends.habitlegends.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.habitlegends.habitlegends.entity.UserHabitStat;

public interface UserHabitStatRepository extends JpaRepository<UserHabitStat, Long> {
    
}
