package com.habitlegends.habitlegends.statreward;

import java.util.List;

public interface HabitStatRewardService {

    HabitStatRewardDTO createHabitStatReward(HabitStatRewardDTO habitStatRewardDTO);

    HabitStatRewardDTO getHabitStatRewardById(Long id);

    List<HabitStatRewardDTO> getHabitStatRewardsByHabitId(Long id);

    List<HabitStatRewardDTO> getAllHabitStatRewards();

    HabitStatRewardDTO updateHabitStatReward(Long id, HabitStatRewardDTO habitDTO);

    void deleteHabitStatReward(Long id);
}
