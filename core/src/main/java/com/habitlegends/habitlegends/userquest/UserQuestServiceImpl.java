package com.habitlegends.habitlegends.userquest;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.habitlegends.habitlegends.quest.Quest;
import com.habitlegends.habitlegends.quest.QuestService;
import com.habitlegends.habitlegends.user.User;
import com.habitlegends.habitlegends.user.UserService;

@Service
public class UserQuestServiceImpl implements UserQuestService {

    private final UserQuestRepository userQuestRepository;

    private final UserService userService;

    private final QuestService questService;

    private final ModelMapper modelMapper;

    public UserQuestServiceImpl(UserQuestRepository userQuestRepository, UserService userService,
            QuestService questService, ModelMapper modelMapper) {
        this.userQuestRepository = userQuestRepository;
        this.userService = userService;
        this.questService = questService;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public UserQuestDTO createUserQuest(UserQuestDetails userQuestDetails) {

        if (userQuestDetails == null || userQuestDetails.getQuestDetails() == null) {
            throw new IllegalArgumentException("UserQuestDetails and QuestDetails must not be null");
        }

        UserQuest userQuest = convertToEntity(userQuestDetails);

        UserQuest savedUserQuest = userQuestRepository.save(userQuest);

        return modelMapper.map(savedUserQuest, UserQuestDTO.class);
    }

    @Override
    @Transactional
    public UserQuestDTO updateUserQuest(Long id, UserQuestDTO userQuestDTO) {
        UserQuest userQuest = userQuestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserQuest not found"));
        modelMapper.map(userQuestDTO, userQuest);
        UserQuest updatedUserQuest = userQuestRepository.save(userQuest);
        return modelMapper.map(updatedUserQuest, UserQuestDTO.class);
    }

    @Override
    public UserQuestDTO getUserQuestById(Long id) {
        UserQuest userQuest = userQuestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserQuest not found"));
        return modelMapper.map(userQuest, UserQuestDTO.class);
    }

    // TODO: Change to specification
    @Override
    public List<UserQuestDTO> getAllUserQuests() {
        return userQuestRepository.findAll()
                .stream()
                .map(userQuest -> modelMapper.map(userQuest, UserQuestDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUserQuest(Long id) {
        if (!userQuestRepository.existsById(id)) {
            throw new RuntimeException("UserQuest not found");
        }
        userQuestRepository.deleteById(id);
    }

    // TODO: Change to specification
    @Override
    public List<UserQuestDetails> getAllUserQuestDetails(Long userId) {

        List<UserQuestDTO> userQuestDTOs = userQuestRepository.findByUserId(userId)
                .stream().map(userQuest -> modelMapper.map(userQuest, UserQuestDTO.class))
                .collect(Collectors.toList());

        List<UserQuestDetails> userQuestDetailsList = userQuestDTOs.stream().map(
                userQuestDTO -> {
                    return new UserQuestDetails(
                            userId,
                            questService.getQuestDetailsById(userQuestDTO.getQuestId()));
                }).collect(Collectors.toList());

        return userQuestDetailsList;
    }

    private UserQuest convertToEntity(UserQuestDetails userQuestDetails) {
        User user = userService.getUserById(userQuestDetails.getUserId());
        Quest quest = questService.getQuestById(userQuestDetails.getQuestDetails().getQuest().getId());

        UserQuest userQuest = new UserQuest();
        userQuest.setUser(user);
        userQuest.setQuest(quest);

        return userQuest;
    }

}
