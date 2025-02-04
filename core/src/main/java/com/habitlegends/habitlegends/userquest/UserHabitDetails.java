package com.habitlegends.habitlegends.userquest;

import com.habitlegends.habitlegends.quest.HabitDetails;

public class UserHabitDetails {
    private Long userId;
    private HabitDetails habitDetails;

    public UserHabitDetails() {
    }

    public UserHabitDetails(Long userId, HabitDetails habitDetails) {
        this.userId = userId;
        this.habitDetails = habitDetails;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public HabitDetails getHabitDetails() {
        return habitDetails;
    }

    public void setHabitDetails(HabitDetails habitDetails) {
        this.habitDetails = habitDetails;
    }

}
