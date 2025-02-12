package com.habitlegends.habitlegends.userprogress;

import java.util.List;

public interface UserProgressService {
    UserProgressDTO createUserProgress(UserProgressDTO userProgressDTO);

    UserProgressDTO getUserProgressById(Long id);

    List<UserProgressDTO> getAllUserProgress();

    List<ProgressUpdateInfoDetails> updateUserProgress(UserProgressDTO userProgressDTO);

    void deleteUserProgress(Long id);

    UserProgressDTO getUserProgressByUserId(Long userId);

    List<ProgressUpdateInfoDetails> triggerProgressUpdateOnCompletion(Long userId, Integer expEarned);

    UserProgressDetails getUserProgressDetails();
}
