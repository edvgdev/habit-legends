package com.habitlegends.habitlegends.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.habitlegends.habitlegends.entity.HabitStatReward;

public interface HabitStatRewardRepository extends JpaRepository<HabitStatReward, Long> {
    
}
