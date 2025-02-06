package com.habitlegends.habitlegends.quest;

import java.util.List;

import com.habitlegends.habitlegends.statreward.QuestAndStatRewardsDetails;

public interface QuestService {
    QuestDetails createQuest(QuestAndStatRewardsDetails questAndStatRewardsDetails);

    QuestDetails getQuestDetailsById(Long id);

    List<QuestDetails> getAllQuestDetails();

    QuestDTO getQuestDtoById(Long id);

    QuestDetails updateQuest(Long id, QuestAndStatRewardsDetails questAndStatRewardsDetails);

    void deleteQuest(Long id);

    Quest getQuestById(Long id);

}
