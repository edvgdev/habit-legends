package com.habitlegends.habitlegends.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.sql.ast.tree.expression.Over;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.habitlegends.habitlegends.details.UserProgressDetails;
import com.habitlegends.habitlegends.dto.RankDTO;
import com.habitlegends.habitlegends.dto.UserProgressDTO;
import com.habitlegends.habitlegends.entity.Rank;
import com.habitlegends.habitlegends.entity.User;
import com.habitlegends.habitlegends.entity.UserProgress;
import com.habitlegends.habitlegends.repository.UserProgressRepository;
import com.habitlegends.habitlegends.service.RankService;
import com.habitlegends.habitlegends.service.UserProgressService;
import com.habitlegends.habitlegends.service.UserService;

@Service
public class UserProgressServiceImpl implements UserProgressService {

    private final UserProgressRepository userProgressRepository;

    private final UserService userService;

    private final RankService rankService;

    private final ModelMapper modelMapper;

    private static final int BASE_EXP = 100;

    public UserProgressServiceImpl(UserProgressRepository userProgressRepository, UserService userService,
            RankService rankService, ModelMapper modelMapper) {
        this.userProgressRepository = userProgressRepository;
        this.userService = userService;
        this.rankService = rankService;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserProgressDTO createUserProgress(UserProgressDTO userProgressDTO) {
        UserProgress userProgress = prepareDtoToSave(userProgressDTO);
        UserProgress savedUserProgress = userProgressRepository.save(userProgress);
        return modelMapper.map(savedUserProgress, UserProgressDTO.class);
    }

    @Override
    public UserProgressDTO updateUserProgress(UserProgressDTO userProgressDTO) {
        // Fetch the existing UserProgress entity
        if (!userProgressRepository.existsById(userProgressDTO.getId())) {
            throw new RuntimeException("UserProgress not found");
        }
        // Prepare the updated entity using prepareDtoToSave
        UserProgress updatedUserProgress = prepareDtoToSave(userProgressDTO);

        // Save the updated UserProgress entity
        UserProgress savedUserProgress = userProgressRepository.save(updatedUserProgress);

        // Convert the updated entity to a DTO and return it
        return modelMapper.map(savedUserProgress, UserProgressDTO.class);
    }

    @Override
    public UserProgressDTO getUserProgressById(Long id) {
        UserProgress userProgress = userProgressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserProgress not found"));
        return modelMapper.map(userProgress, UserProgressDTO.class);
    }

    @Override
    public UserProgressDTO getUserProgressByUserId(Long userId) {
        UserProgress userProgress = userProgressRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("UserProgress not found"));
        return modelMapper.map(userProgress, UserProgressDTO.class);
    }

    @Override
    public List<UserProgressDTO> getAllUserProgress() {
        return userProgressRepository.findAll()
                .stream()
                .map(userProgress -> modelMapper.map(userProgress, UserProgressDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUserProgress(Long id) {
        if (!userProgressRepository.existsById(id)) {
            throw new RuntimeException("UserProgress not found");
        }
        userProgressRepository.deleteById(id);
    }

    @Override
    public void triggerProgressUpdateOnCompletion(Long userId, Integer expEarned) {
        if (expEarned <= 0) {
            return; // No need to update progress if no experience is earned
        }
        UserProgressDTO userProgressDTO = getUserProgressByUserId(userId);
        if (userProgressDTO == null) {
            throw new RuntimeException("User progress not found for user ID: " + userId);
        }

        userProgressDTO.setExp(userProgressDTO.getExp() + expEarned);

        UserProgressDTO updatedUserProgress = updateUserProgress(
                userProgressDTO);

        // Transfer to logger in the future
        System.out.println(updatedUserProgress);
    }

    @Override
    public UserProgressDetails getUserProgressDetails(Long userId) {
        UserProgressDTO userProgressDTO = getUserProgressByUserId(userId);
        RankDTO rank = rankService.getRankById(userProgressDTO.getRankId());

        UserProgressDetails userProgressDetails = new UserProgressDetails();
        userProgressDetails.setUserId(userId);
        userProgressDetails.setLevel(userProgressDTO.getLevel());
        userProgressDetails.setExp(userProgressDTO.getExp());
        userProgressDetails.setRank(rank.getName());
        userProgressDetails.setMinExpToNextRank(rank.getMinExp());
        userProgressDetails.setExpRequiredForNextLevel(getExpRequiredForNextLevel(userProgressDTO.getLevel()));

        return userProgressDetails;
    }

    private UserProgress prepareDtoToSave(UserProgressDTO userProgressDTO) {
        UserProgress userProgress = modelMapper.map(userProgressDTO, UserProgress.class);

        User user = userService.getUserById(userProgressDTO.getUserId());
        userProgress.setUser(user);

        RankDTO rankDto = rankService.determineRank(userProgressDTO.getExp())
                .orElse(null);

        Rank rank = modelMapper.map(rankDto, Rank.class);
        userProgress.setRank(rank);

        // Check for level-up during updates (skip during creation)
        if (userProgressDTO.getId() != null && checkForLevelUp(userProgressDTO.getLevel(), userProgressDTO.getExp())) {
            userProgress.setLevel(userProgressDTO.getLevel() + 1);
        }

        return userProgress;
    }

    private Integer getExpRequiredForNextLevel(Integer currentLevel) {
        return BASE_EXP * (currentLevel * currentLevel);
    }

    private boolean checkForLevelUp(Integer currentLevel, Integer currentExp) {
        int expRequired = getExpRequiredForNextLevel(currentLevel);
        return currentExp >= expRequired;
    }

}
