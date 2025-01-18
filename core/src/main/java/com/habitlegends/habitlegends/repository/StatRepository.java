package com.habitlegends.habitlegends.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.habitlegends.habitlegends.entity.Stat;

public interface StatRepository extends JpaRepository<Stat, Integer> {
    
}
