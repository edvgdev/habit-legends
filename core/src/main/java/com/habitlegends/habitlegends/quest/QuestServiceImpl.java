package com.habitlegends.habitlegends.quest;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.habitlegends.habitlegends.statreward.QuestAndStatRewardsDetails;
import com.habitlegends.habitlegends.statreward.QuestStatRewardDTO;
import com.habitlegends.habitlegends.statreward.QuestStatRewardService;

@Service
public class QuestServiceImpl implements QuestService {

        private final QuestRepository questRepository;

        private final QuestStatRewardService questStatRewardService;

        private final ModelMapper modelMapper;

        public QuestServiceImpl(QuestRepository questRepository, QuestStatRewardService questStatRewardService,
                        ModelMapper modelMapper) {
                this.questRepository = questRepository;
                this.questStatRewardService = questStatRewardService;
                this.modelMapper = modelMapper;
        }

        @Override
        public QuestDetails createQuest(QuestAndStatRewardsDetails questAndStatRewardsDetails) {

                // Retrieve the quest from QuestStatRewardsDTO to save the quest.
                Quest quest = modelMapper.map(questAndStatRewardsDetails.getQuest(), Quest.class);
                Quest savedQuest = questRepository.save(quest);

                // Iterate each statRewards and save as a questStatRewardDTO
                List<QuestStatRewardDTO> questStatRewardDTOs = questAndStatRewardsDetails.getStatRewards().stream()
                                .map(statReward -> {
                                        QuestStatRewardDTO questStatRewardDTO = new QuestStatRewardDTO(null,
                                                        savedQuest.getId(),
                                                        statReward.getStatId(),
                                                        statReward.getReward(), null, null);

                                        return questStatRewardService.createQuestStatReward(questStatRewardDTO);
                                }).collect(Collectors.toList());

                String categoryName = savedQuest.getCategory().getName();

                QuestDetails questDetails = new QuestDetails(modelMapper.map(savedQuest, QuestDTO.class), categoryName,
                                questStatRewardDTOs);

                return questDetails;
        }

        @Override
        public QuestDetails updateQuest(Long id, QuestAndStatRewardsDetails questAndStatRewardsDetails) {
                // Retrieve the existing quest from the repository
                Quest quest = getQuestById(id);

                // Map the quest object to the existing quest object
                modelMapper.map(questAndStatRewardsDetails.getQuest(), quest);
                Quest updatedQuest = questRepository.save(quest);

                // Fetch existing QuestStatRewards by questId (as DTO)
                List<QuestStatRewardDTO> existingStatRewards = questStatRewardService.getQuestStatRewardsByQuestId(id);

                // Convert existingStatRewards to a Map for quick lookups by statId
                Map<Integer, QuestStatRewardDTO> existingStatRewardsMap = existingStatRewards.stream()
                                .collect(Collectors.toMap(QuestStatRewardDTO::getStatId,
                                                statRewardDTO -> statRewardDTO));

                // Process fromPayload (new stat rewards)
                List<QuestStatRewardDTO> fromPayload = questAndStatRewardsDetails.getStatRewards().stream()
                                .map(statRewardDetails -> {
                                        // Check if the statId exists in the current rewards
                                        QuestStatRewardDTO existingReward = existingStatRewardsMap
                                                        .get(statRewardDetails.getStatId());
                                        if (existingReward != null) {
                                                // Update existing reward with new reward value if applicable
                                                existingReward.setBaseStatReward(statRewardDetails.getReward());
                                                return existingReward; // Use the existing entity with updated values
                                        } else {
                                                // Create a new reward if no match is found
                                                return new QuestStatRewardDTO(
                                                                null, updatedQuest.getId(),
                                                                statRewardDetails.getStatId(),
                                                                statRewardDetails.getReward(), null, null);
                                        }
                                })
                                .collect(Collectors.toList());

                // Save updated and new rewards
                List<QuestStatRewardDTO> statRewardsToSave = fromPayload.stream()
                                .map(questStatRewardService::createQuestStatReward) // Save or update each reward
                                .collect(Collectors.toList());

                // Find stat rewards to delete (those that no longer exist in the request)
                Set<Integer> incomingStatIds = fromPayload.stream()
                                .map(QuestStatRewardDTO::getStatId)
                                .collect(Collectors.toSet());

                // Remove obsolete stat rewards
                existingStatRewards.stream()
                                .filter(existingStatRewardDTO -> !incomingStatIds
                                                .contains(existingStatRewardDTO.getStatId()))
                                .forEach(statReward -> questStatRewardService
                                                .deleteQuestStatReward(statReward.getId()));

                String categoryName = updatedQuest.getCategory().getName();

                // Combine the updated stat rewards into the response DTO
                QuestDetails questDetails = new QuestDetails(modelMapper.map(updatedQuest, QuestDTO.class),
                                categoryName,
                                statRewardsToSave);

                return questDetails;
        }

        @Override
        public QuestDTO getQuestDtoById(Long id) {
                Quest quest = getQuestById(id);
                return modelMapper.map(quest, QuestDTO.class);
        }

        @Override
        public Quest getQuestById(Long id) {
                return questRepository.findById(id).orElseThrow(
                                () -> new RuntimeException("Quest not found"));
        }

        @Override
        public QuestDetails getQuestDetailsById(Long id) {
                Quest quest = getQuestById(id);

                // Retrieve stat rewards and category name for the quest
                List<QuestStatRewardDTO> statRewards = questStatRewardService.getQuestStatRewardsByQuestId(id);
                String categoryName = quest.getCategory().getName();

                // Return QuestDetails instead of QuestDTO
                return new QuestDetails(modelMapper.map(quest, QuestDTO.class), categoryName, statRewards);
        }

        @Override
        public List<QuestDetails> getAllQuestDetails() {
                List<Quest> quests = questRepository.findAll();

                // Retrieve QuestStatRewards for all quests
                return quests.stream()
                                .map(quest -> {
                                        // Get the stat rewards for each quest
                                        List<QuestStatRewardDTO> statRewards = questStatRewardService
                                                        .getQuestStatRewardsByQuestId(quest.getId());
                                        // Convert each quest to QuestDTO and create QuestDetails
                                        String categoryName = quest.getCategory().getName();
                                        return new QuestDetails(modelMapper.map(quest, QuestDTO.class), categoryName,
                                                        statRewards);
                                })
                                .collect(Collectors.toList());
        }

        @Override
        public void deleteQuest(Long id) {
                if (!questRepository.existsById(id)) {
                        throw new RuntimeException("Quest not found");
                }

                // Optionally, handle deletion of related QuestStatRewards if needed
                List<QuestStatRewardDTO> statRewards = questStatRewardService.getQuestStatRewardsByQuestId(id);
                statRewards.forEach(statReward -> questStatRewardService.deleteQuestStatReward(statReward.getId()));

                questRepository.deleteById(id);

        }

}
