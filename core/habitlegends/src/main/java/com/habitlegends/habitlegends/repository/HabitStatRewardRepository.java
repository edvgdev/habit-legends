package com.habitlegends.habitlegends.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.habitlegends.habitlegends.entity.HabitStatReward;

public interface HabitStatRewardRepository extends JpaRepository<HabitStatReward, Long> {

    List<HabitStatReward> findByHabitId(Long habitId);

}
