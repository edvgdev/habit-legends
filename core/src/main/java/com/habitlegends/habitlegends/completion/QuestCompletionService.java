package com.habitlegends.habitlegends.completion;

import java.util.List;

/**
 * Service class for handling quest completions
 */
public interface QuestCompletionService {
    QuestCompletionDTO createQuestCompletion(QuestCompletionDTO questCompletionDTO);

    List<QuestCompletionDTO> getCompletionsByFilter(QuestCompletionFilterDetails filterDetails);

}
