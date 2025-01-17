package com.habitlegends.habitlegends.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.habitlegends.habitlegends.entity.UserProgress;

public interface UserProgressRepository extends JpaRepository<UserProgress, Long>{
    
}
