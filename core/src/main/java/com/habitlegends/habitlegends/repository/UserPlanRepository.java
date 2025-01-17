package com.habitlegends.habitlegends.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.habitlegends.habitlegends.entity.UserPlan;

public interface UserPlanRepository extends JpaRepository<UserPlan, Integer> {

}
