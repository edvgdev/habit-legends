package com.habitlegends.habitlegends.userquest;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserHabitRepository extends JpaRepository<UserHabit, Long> {

    List<UserHabit> findByUserId(Long userId);
}
