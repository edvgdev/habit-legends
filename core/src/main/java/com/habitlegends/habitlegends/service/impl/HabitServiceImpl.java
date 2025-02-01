package com.habitlegends.habitlegends.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.habitlegends.habitlegends.details.HabitAndStatRewardsDetails;
import com.habitlegends.habitlegends.details.HabitDetails;
import com.habitlegends.habitlegends.dto.HabitDTO;
import com.habitlegends.habitlegends.dto.HabitStatRewardDTO;
import com.habitlegends.habitlegends.entity.Habit;
import com.habitlegends.habitlegends.repository.HabitRepository;
import com.habitlegends.habitlegends.service.HabitService;
import com.habitlegends.habitlegends.service.HabitStatRewardService;

@Service
public class HabitServiceImpl implements HabitService {

        private final HabitRepository habitRepository;

        private final HabitStatRewardService habitStatRewardService;

        private final ModelMapper modelMapper;

        public HabitServiceImpl(HabitRepository habitRepository, HabitStatRewardService habitStatRewardService,
                        ModelMapper modelMapper) {
                this.habitRepository = habitRepository;
                this.habitStatRewardService = habitStatRewardService;
                this.modelMapper = modelMapper;
        }

        @Override
        public HabitDetails createHabit(HabitAndStatRewardsDetails habitAndStatRewardsDetails) {

                // Retrieve the habit from habitStatRewardsDTO to save the habit.
                Habit habit = modelMapper.map(habitAndStatRewardsDetails.getHabit(), Habit.class);
                Habit savedHabit = habitRepository.save(habit);

                // Iterate each statRewards and save as a habitStatRewardDTO
                List<HabitStatRewardDTO> habitStatRewardDTOs = habitAndStatRewardsDetails.getStatRewards().stream()
                                .map(statReward -> {
                                        HabitStatRewardDTO habitStatRewardDTO = new HabitStatRewardDTO(null,
                                                        savedHabit.getId(),
                                                        statReward.getStatId(),
                                                        statReward.getReward(), null, null);

                                        return habitStatRewardService.createHabitStatReward(habitStatRewardDTO);
                                }).collect(Collectors.toList());

                String categoryName = savedHabit.getCategory().getName();

                HabitDetails habitDetails = new HabitDetails(modelMapper.map(savedHabit, HabitDTO.class), categoryName,
                                habitStatRewardDTOs);

                return habitDetails;
        }

        @Override
        public HabitDetails updateHabit(Long id, HabitAndStatRewardsDetails habitAndStatRewardsDetails) {
                // Retrieve the existing habit from the repository
                Habit habit = getHabitById(id);

                // Map the habit object to the existing habit object
                modelMapper.map(habitAndStatRewardsDetails.getHabit(), habit);
                Habit updatedHabit = habitRepository.save(habit);

                // Fetch existing HabitStatRewards by habitId (as DTO)
                List<HabitStatRewardDTO> existingStatRewards = habitStatRewardService.getHabitStatRewardsByHabitId(id);

                // Convert existingStatRewards to a Map for quick lookups by statId
                Map<Integer, HabitStatRewardDTO> existingStatRewardsMap = existingStatRewards.stream()
                                .collect(Collectors.toMap(HabitStatRewardDTO::getStatId,
                                                statRewardDTO -> statRewardDTO));

                // Process fromPayload (new stat rewards)
                List<HabitStatRewardDTO> fromPayload = habitAndStatRewardsDetails.getStatRewards().stream()
                                .map(statRewardDetails -> {
                                        // Check if the statId exists in the current rewards
                                        HabitStatRewardDTO existingReward = existingStatRewardsMap
                                                        .get(statRewardDetails.getStatId());
                                        if (existingReward != null) {
                                                // Update existing reward with new reward value if applicable
                                                existingReward.setBaseStatReward(statRewardDetails.getReward());
                                                return existingReward; // Use the existing entity with updated values
                                        } else {
                                                // Create a new reward if no match is found
                                                return new HabitStatRewardDTO(
                                                                null, updatedHabit.getId(),
                                                                statRewardDetails.getStatId(),
                                                                statRewardDetails.getReward(), null, null);
                                        }
                                })
                                .collect(Collectors.toList());

                // Save updated and new rewards
                List<HabitStatRewardDTO> statRewardsToSave = fromPayload.stream()
                                .map(habitStatRewardService::createHabitStatReward) // Save or update each reward
                                .collect(Collectors.toList());

                // Find stat rewards to delete (those that no longer exist in the request)
                Set<Integer> incomingStatIds = fromPayload.stream()
                                .map(HabitStatRewardDTO::getStatId)
                                .collect(Collectors.toSet());

                // Remove obsolete stat rewards
                existingStatRewards.stream()
                                .filter(existingStatRewardDTO -> !incomingStatIds
                                                .contains(existingStatRewardDTO.getStatId()))
                                .forEach(statReward -> habitStatRewardService
                                                .deleteHabitStatReward(statReward.getId()));

                String categoryName = updatedHabit.getCategory().getName();

                // Combine the updated stat rewards into the response DTO
                HabitDetails habitDetails = new HabitDetails(modelMapper.map(updatedHabit, HabitDTO.class),
                                categoryName,
                                statRewardsToSave);

                return habitDetails;
        }

        @Override
        public HabitDTO getHabitDtoById(Long id) {
                Habit habit = getHabitById(id);
                return modelMapper.map(habit, HabitDTO.class);
        }

        @Override
        public Habit getHabitById(Long id) {
                return habitRepository.findById(id).orElseThrow(
                                () -> new RuntimeException("Habit not found"));
        }

        @Override
        public HabitDetails getHabitDetailsById(Long id) {
                Habit habit = getHabitById(id);

                // Retrieve stat rewards and category name for the habit
                List<HabitStatRewardDTO> statRewards = habitStatRewardService.getHabitStatRewardsByHabitId(id);
                String categoryName = habit.getCategory().getName();

                // Return HabitDetails instead of HabitDTO
                return new HabitDetails(modelMapper.map(habit, HabitDTO.class), categoryName, statRewards);
        }

        @Override
        public List<HabitDetails> getAllHabitDetails() {
                List<Habit> habits = habitRepository.findAll();

                // Retrieve HabitStatRewards for all habits
                return habits.stream()
                                .map(habit -> {
                                        // Get the stat rewards for each habit
                                        List<HabitStatRewardDTO> statRewards = habitStatRewardService
                                                        .getHabitStatRewardsByHabitId(habit.getId());
                                        // Convert each habit to HabitDTO and create HabitDetails
                                        String categoryName = habit.getCategory().getName();
                                        return new HabitDetails(modelMapper.map(habit, HabitDTO.class), categoryName,
                                                        statRewards);
                                })
                                .collect(Collectors.toList());
        }

        @Override
        public void deleteHabit(Long id) {
                if (!habitRepository.existsById(id)) {
                        throw new RuntimeException("Habit not found");
                }

                // Optionally, handle deletion of related HabitStatRewards if needed
                List<HabitStatRewardDTO> statRewards = habitStatRewardService.getHabitStatRewardsByHabitId(id);
                statRewards.forEach(statReward -> habitStatRewardService.deleteHabitStatReward(statReward.getId()));

                habitRepository.deleteById(id);

        }

}
