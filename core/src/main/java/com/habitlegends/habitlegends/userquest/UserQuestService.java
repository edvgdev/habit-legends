package com.habitlegends.habitlegends.userquest;

import java.util.List;

public interface UserQuestService {
    UserQuestDTO createUserQuest(UserQuestDetails userQuestDetails);

    UserQuestDTO getUserQuestById(Long id);

    List<UserQuestDTO> getAllUserQuests();

    UserQuestDTO updateUserQuest(Long id, UserQuestDTO userQuestDTO);

    void deleteUserQuest(Long id);

    List<UserQuestDetails> getAllUserQuestDetails(Long userId);

}
