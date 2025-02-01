package com.habitlegends.habitlegends.service;

import java.util.List;

import com.habitlegends.habitlegends.details.UserProgressDetails;
import com.habitlegends.habitlegends.dto.UserProgressDTO;

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
