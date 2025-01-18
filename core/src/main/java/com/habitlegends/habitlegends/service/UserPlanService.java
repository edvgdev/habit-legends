package com.habitlegends.habitlegends.service;

import java.util.List;

import com.habitlegends.habitlegends.dto.UserPlanDTO;

public interface UserPlanService {
    UserPlanDTO createUserPlan(UserPlanDTO userPlanDTO);

    UserPlanDTO getUserPlanById(Integer id);

    List<UserPlanDTO> getAllUserPlans();

    UserPlanDTO updateUserPlan(Integer id, UserPlanDTO userPlanDTO);

    void deleteUserPlan(Integer id);
}
