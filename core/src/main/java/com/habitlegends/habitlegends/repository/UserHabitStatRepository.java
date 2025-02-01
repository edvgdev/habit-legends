package com.habitlegends.habitlegends.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.habitlegends.habitlegends.entity.UserHabitStat;

public interface UserHabitStatRepository extends JpaRepository<UserHabitStat, Long> {

    @Query("SELECT uhs FROM UserHabitStat uhs WHERE uhs.user.id = :userId AND uhs.stat.id = :statId")
    Optional<UserHabitStat> findByUserIdAndStatId(@Param("userId") Long userId, @Param("statId") Integer statId);
}
