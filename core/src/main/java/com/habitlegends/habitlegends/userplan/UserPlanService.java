package com.habitlegends.habitlegends.userplan;

import java.util.List;

public interface UserPlanService {
    UserPlanDTO createUserPlan(UserPlanDTO userPlanDTO);

    UserPlanDTO getUserPlanById(Integer id);

    List<UserPlanDTO> getAllUserPlans();

    UserPlanDTO updateUserPlan(Integer id, UserPlanDTO userPlanDTO);

    void deleteUserPlan(Integer id);
}
