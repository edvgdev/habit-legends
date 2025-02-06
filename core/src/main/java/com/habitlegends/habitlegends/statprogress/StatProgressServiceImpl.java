package com.habitlegends.habitlegends.statprogress;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.habitlegends.habitlegends.stat.Stat;
import com.habitlegends.habitlegends.stat.StatDTO;
import com.habitlegends.habitlegends.stat.StatService;
import com.habitlegends.habitlegends.statreward.QuestStatRewardDTO;
import com.habitlegends.habitlegends.statreward.QuestStatRewardService;
import com.habitlegends.habitlegends.user.UserService;

@Service
public class StatProgressServiceImpl implements StatProgressService {

    private final StatProgressRepository statProgresssRepository;

    private final StatService statService;

    private final QuestStatRewardService questStatRewardService;

    private final UserService userService;

    private final ModelMapper modelMapper;

    public StatProgressServiceImpl(StatProgressRepository statProgresssRepository, StatService statService,
            QuestStatRewardService questStatRewardService, UserService userService, ModelMapper modelMapper) {
        this.statProgresssRepository = statProgresssRepository;
        this.statService = statService;
        this.questStatRewardService = questStatRewardService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<StatProgressDTO> initializeStatProgressForNewUser(Long userId) {
        List<StatProgressDTO> statProgressDTOs = statService.getAllStats().stream()
                .map(stat -> {
                    StatProgressDTO statProgressDTO = new StatProgressDTO(
                            null,
                            userId,
                            stat.getId(),
                            0,
                            null,
                            null);
                    return createStatProgress(statProgressDTO);
                }).collect(Collectors.toList());

        return statProgressDTOs;
    }

    @Override
    public StatProgressDTO createStatProgress(StatProgressDTO statProgressDTO) {
        StatProgress statProgress = convertToEntity(statProgressDTO);
        StatProgress savedStatProgress = statProgresssRepository.save(statProgress);
        return modelMapper.map(savedStatProgress, StatProgressDTO.class);
    }

    @Override
    public void addPoints(Long userId, Long questId) {
        List<QuestStatRewardDTO> questStatRewardDTOs = questStatRewardService.getQuestStatRewardsByQuestId(questId);

        for (QuestStatRewardDTO questStatRewardDTO : questStatRewardDTOs) {
            StatProgress statProgress = statProgresssRepository.findByUserIdAndStatId(userId,
                    questStatRewardDTO.getStatId()).orElseGet(
                            () -> {
                                StatProgressDTO statProgressDTO = new StatProgressDTO(null, userId,
                                        questStatRewardDTO.getStatId(), 0, null,
                                        null);

                                return modelMapper.map(statProgressDTO, StatProgress.class);
                            });

            statProgress.setCurrentPoints(statProgress.getCurrentPoints() + questStatRewardDTO.getBaseStatReward());
            statProgresssRepository.save(statProgress);
        }

    }

    @Override
    public StatProgressDTO updateStatProgress(Long id, StatProgressDTO statProgressDTO) {
        StatProgress statProgress = statProgresssRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("StatProgress not found"));
        modelMapper.map(statProgressDTO, statProgress);
        StatProgress updatedStatProgress = statProgresssRepository.save(statProgress);
        return modelMapper.map(updatedStatProgress, StatProgressDTO.class);
    }

    @Override
    public StatProgressDTO getStatProgressById(Long id) {
        StatProgress statProgress = statProgresssRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("StatProgress not found"));
        return modelMapper.map(statProgress, StatProgressDTO.class);
    }

    // TODO: Change to specification
    @Override
    public List<StatProgressDTO> getAllStatProgress() {
        return statProgresssRepository.findAll()
                .stream()
                .map(statProgress -> modelMapper.map(statProgress, StatProgressDTO.class))
                .collect(Collectors.toList());
    }

    // TODO: Change to specification
    @Override
    public List<UserStatDetails> getAllUserStatByUser(Long userId) {
        List<StatDTO> allStats = statService.getAllStatsDto();
        List<StatProgress> statProgresses = statProgresssRepository.findByUserId(userId);

        Map<Long, StatProgress> statProgressMap = statProgresses.stream()
                .collect(Collectors.toMap(
                        statProgress -> (long) statProgress.getStat().getId(),
                        Function.identity()));

        return allStats.stream()
                .map(stat -> {
                    StatProgress statProgress = statProgressMap.get((long) stat.getId());
                    return new UserStatDetails(
                            userId,
                            stat.getName(),
                            statProgress != null ? statProgress.getCurrentPoints() : 0,
                            stat.getIcon());
                })
                .collect(Collectors.toList());
    }

    @Override
    public void deleteStatProgress(Long id) {
        if (!statProgresssRepository.existsById(id)) {
            throw new RuntimeException("StatProgress not found");
        }
        statProgresssRepository.deleteById(id);
    }

    private StatProgress convertToEntity(StatProgressDTO statProgressDTO) {
        StatProgress statProgress = modelMapper.map(statProgressDTO, StatProgress.class);
        statProgress.setUser(userService.getUserById(statProgressDTO.getUserId()));
        statProgress.setStat(modelMapper.map(statService.getStatById(statProgressDTO.getStatId()), Stat.class));
        return statProgress;
    }
}
