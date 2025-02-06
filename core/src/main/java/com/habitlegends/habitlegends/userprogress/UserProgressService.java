package com.habitlegends.habitlegends.userprogress;

import java.util.List;

public interface UserProgressService {
    UserProgressDTO createUserProgress(UserProgressDTO userProgressDTO);

    UserProgressDTO getUserProgressById(Long id);

    List<UserProgressDTO> getAllUserProgress();

    UserProgressDTO updateUserProgress(UserProgressDTO userProgressDTO);

    void deleteUserProgress(Long id);

    UserProgressDTO getUserProgressByUserId(Long userId);

    void triggerProgressUpdateOnCompletion(Long userId, Integer expEarned);

    UserProgressDetails getUserProgressDetails(Long userId);
}
