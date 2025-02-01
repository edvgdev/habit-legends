package com.habitlegends.habitlegends.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.habitlegends.habitlegends.entity.HabitCompletion;

public interface HabitCompletionRepository extends JpaRepository<HabitCompletion, Long> {

    List<HabitCompletion> findByUserId(Long userId);

    @Query("SELECT hc FROM HabitCompletion hc WHERE hc.user.id = :userId AND DATE(hc.completedAt) = :today")
    List<HabitCompletion> findByUserIdAndCompletedAtDate(@Param("userId") Long userId, @Param("today") LocalDate today);
}
