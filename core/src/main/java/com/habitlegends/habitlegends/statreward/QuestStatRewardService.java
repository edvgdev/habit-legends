package com.habitlegends.habitlegends.statreward;

import java.util.List;

public interface QuestStatRewardService {

    QuestStatRewardDTO createQuestStatReward(QuestStatRewardDTO questStatRewardDTO);

    QuestStatRewardDTO getQuestStatRewardById(Long id);

    List<QuestStatRewardDTO> getQuestStatRewardsByQuestId(Long id);

    List<QuestStatRewardDTO> getAllQuestStatRewards();

    QuestStatRewardDTO updateQuestStatReward(Long id, QuestStatRewardDTO questStatRewardDTO);

    void deleteQuestStatReward(Long id);
}
