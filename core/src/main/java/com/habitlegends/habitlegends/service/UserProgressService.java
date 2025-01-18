package com.habitlegends.habitlegends.service;

import java.util.List;
import com.habitlegends.habitlegends.dto.UserProgressDTO;

public interface UserProgressService {
    UserProgressDTO createUserProgress(UserProgressDTO userProgressDTO);

    UserProgressDTO getUserProgressById(Long id);

    List<UserProgressDTO> getAllUserProgress();

    UserProgressDTO updateUserProgress(Long id, UserProgressDTO userProgressDTO);

    void deleteUserProgress(Long id);
}
