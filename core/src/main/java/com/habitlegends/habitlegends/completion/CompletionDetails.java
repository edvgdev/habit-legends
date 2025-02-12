package com.habitlegends.habitlegends.completion;

import java.util.List;

import com.habitlegends.habitlegends.userprogress.ProgressUpdateInfoDetails;

public class CompletionDetails {
    private QuestCompletionDTO questCompletion;

    private List<ProgressUpdateInfoDetails> progressUpdateInfoDetails;

    public CompletionDetails() {
    }

    public CompletionDetails(QuestCompletionDTO questCompletion,
            List<ProgressUpdateInfoDetails> progressUpdateInfoDetails) {
        this.questCompletion = questCompletion;
        this.progressUpdateInfoDetails = progressUpdateInfoDetails;
    }

    public QuestCompletionDTO getQuestCompletion() {
        return questCompletion;
    }

    public void setQuestCompletion(QuestCompletionDTO questCompletion) {
        this.questCompletion = questCompletion;
    }

    public List<ProgressUpdateInfoDetails> getProgressUpdateInfoDetails() {
        return progressUpdateInfoDetails;
    }

    public void setProgressUpdateInfoDetails(List<ProgressUpdateInfoDetails> progressUpdateInfoDetails) {
        this.progressUpdateInfoDetails = progressUpdateInfoDetails;
    }

}
