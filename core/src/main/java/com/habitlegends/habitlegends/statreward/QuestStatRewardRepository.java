package com.habitlegends.habitlegends.statreward;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestStatRewardRepository extends JpaRepository<QuestStatReward, Long> {

    List<QuestStatReward> findByQuestId(Long questId);

}
