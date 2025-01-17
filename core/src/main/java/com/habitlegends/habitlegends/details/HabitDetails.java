package com.habitlegends.habitlegends.details;

import java.util.List;

import com.habitlegends.habitlegends.dto.HabitDTO;
import com.habitlegends.habitlegends.dto.HabitStatRewardDTO;

public class HabitDetails {
    private HabitDTO habit;
    private String categoryName;
    private List<HabitStatRewardDTO> habitStatRewards;

    public HabitDetails() {
    }

    public HabitDetails(HabitDTO habit, String categoryName, List<HabitStatRewardDTO> habitStatRewards) {
        this.habit = habit;
        this.categoryName = categoryName;
        this.habitStatRewards = habitStatRewards;
    }

    public HabitDTO getHabit() {
        return habit;
    }

    public void setHabit(HabitDTO habitDTO) {
        this.habit = habitDTO;
    }

    public List<HabitStatRewardDTO> getHabitStatRewards() {
        return habitStatRewards;
    }

    public void setHabitStatRewards(List<HabitStatRewardDTO> habitStatRewardDTOList) {
        this.habitStatRewards = habitStatRewardDTOList;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}
