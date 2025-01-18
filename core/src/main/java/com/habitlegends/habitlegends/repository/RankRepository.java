package com.habitlegends.habitlegends.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.habitlegends.habitlegends.entity.Rank;

public interface RankRepository extends JpaRepository<Rank, Integer>{
    
}
