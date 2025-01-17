package com.habitlegends.habitlegends.service;

import java.util.List;

import com.habitlegends.habitlegends.dto.HabitStatRewardDTO;

public interface HabitStatRewardService {

    HabitStatRewardDTO createHabitStatReward(HabitStatRewardDTO habitStatRewardDTO);

    HabitStatRewardDTO getHabitStatRewardById(Long id);

    List<HabitStatRewardDTO> getHabitStatRewardsByHabitId(Long id);

    List<HabitStatRewardDTO> getAllHabitStatRewards();

    HabitStatRewardDTO updateHabitStatReward(Long id, HabitStatRewardDTO habitDTO);

    void deleteHabitStatReward(Long id);
}
