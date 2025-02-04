package com.habitlegends.habitlegends.statreward;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitStatRewardRepository extends JpaRepository<HabitStatReward, Long> {

    List<HabitStatReward> findByHabitId(Long habitId);

}
