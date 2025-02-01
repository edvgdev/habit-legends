package com.habitlegends.habitlegends.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.habitlegends.habitlegends.entity.UserHabit;

public interface UserHabitRepository extends JpaRepository<UserHabit, Long> {

    List<UserHabit> findByUserId(Long userId);
}
