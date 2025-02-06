package com.habitlegends.habitlegends.statprogress;

import java.util.List;

public interface StatProgressService {
    StatProgressDTO createStatProgress(StatProgressDTO statProgressDTO);

    StatProgressDTO getStatProgressById(Long id);

    List<StatProgressDTO> getAllStatProgress();

    StatProgressDTO updateStatProgress(Long id, StatProgressDTO statProgressDTO);

    void deleteStatProgress(Long id);

    List<StatProgressDTO> initializeStatProgressForNewUser(Long userId);

    void addPoints(Long userId, Long questId);

    List<UserStatDetails> getAllUserStatByUser(Long userId);
}
