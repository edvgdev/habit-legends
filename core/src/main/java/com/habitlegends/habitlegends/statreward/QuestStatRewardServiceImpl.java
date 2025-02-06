package com.habitlegends.habitlegends.statreward;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class QuestStatRewardServiceImpl implements QuestStatRewardService {

    private final QuestStatRewardRepository questStatRewardRepository;

    private final ModelMapper modelMapper;

    public QuestStatRewardServiceImpl(QuestStatRewardRepository questStatRewardRepository, ModelMapper modelMapper) {
        this.questStatRewardRepository = questStatRewardRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public QuestStatRewardDTO createQuestStatReward(QuestStatRewardDTO questStatRewardDTO) {
        QuestStatReward questStatReward = modelMapper.map(questStatRewardDTO, QuestStatReward.class);
        QuestStatReward savedQuestStatReward = questStatRewardRepository.save(questStatReward);
        return modelMapper.map(savedQuestStatReward, QuestStatRewardDTO.class);
    }

    @Override
    public QuestStatRewardDTO updateQuestStatReward(Long id, QuestStatRewardDTO questStatRewardDTO) {
        QuestStatReward questStatReward = questStatRewardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("QuestStatReward not found"));
        modelMapper.map(questStatRewardDTO, questStatReward);
        QuestStatReward updatedQuestStatReward = questStatRewardRepository.save(questStatReward);
        return modelMapper.map(updatedQuestStatReward, QuestStatRewardDTO.class);
    }

    @Override
    public QuestStatRewardDTO getQuestStatRewardById(Long id) {
        QuestStatReward questStatReward = questStatRewardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("QuestStatReward not found"));
        return modelMapper.map(questStatReward, QuestStatRewardDTO.class);
    }

    // TODO: Change to specification
    @Override
    public List<QuestStatRewardDTO> getQuestStatRewardsByQuestId(Long id) {
        // Retrieve the questStatRewards by questId
        List<QuestStatReward> questStatRewards = questStatRewardRepository.findByQuestId(id);

        // Map each questStatReward entity to QuestStatRewardDTO using ModelMapper
        return questStatRewards.stream()
                .map(questStatReward -> modelMapper.map(questStatReward, QuestStatRewardDTO.class))
                .collect(Collectors.toList());
    }

    // TODO: Change to specification
    @Override
    public List<QuestStatRewardDTO> getAllQuestStatRewards() {
        return questStatRewardRepository.findAll()
                .stream()
                .map(questStatReward -> modelMapper.map(questStatReward, QuestStatRewardDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteQuestStatReward(Long id) {
        if (!questStatRewardRepository.existsById(id)) {
            throw new RuntimeException("QuestStatReward not found");
        }
        questStatRewardRepository.deleteById(id);
    }

}
