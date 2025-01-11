package com.habitlegends.habitlegends.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.habitlegends.habitlegends.dto.HabitStatRewardDTO;
import com.habitlegends.habitlegends.entity.HabitStatReward;
import com.habitlegends.habitlegends.repository.HabitStatRewardRepository;
import com.habitlegends.habitlegends.service.HabitStatRewardService;

@Service
public class HabitStatRewardServiceImpl implements HabitStatRewardService {

    private final HabitStatRewardRepository habitStatRewardRepository;

    private final ModelMapper modelMapper;

    public HabitStatRewardServiceImpl(HabitStatRewardRepository habitStatRewardRepository, ModelMapper modelMapper) {
        this.habitStatRewardRepository = habitStatRewardRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public HabitStatRewardDTO createHabitStatReward(HabitStatRewardDTO habitStatRewardDTO) {
        HabitStatReward habitStatReward = modelMapper.map(habitStatRewardDTO, HabitStatReward.class);
        HabitStatReward savedHabitStatReward = habitStatRewardRepository.save(habitStatReward);
        return modelMapper.map(savedHabitStatReward, HabitStatRewardDTO.class);
    }

    @Override
    public HabitStatRewardDTO updateHabitStatReward(Long id, HabitStatRewardDTO habitStatRewardDTO) {
        HabitStatReward habitStatReward = habitStatRewardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("HabitStatReward not found"));
        modelMapper.map(habitStatRewardDTO, habitStatReward);
        HabitStatReward updatedHabitStatReward = habitStatRewardRepository.save(habitStatReward);
        return modelMapper.map(updatedHabitStatReward, HabitStatRewardDTO.class);
    }

    @Override
    public HabitStatRewardDTO getHabitStatRewardById(Long id) {
        HabitStatReward habitStatReward = habitStatRewardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("HabitStatReward not found"));
        return modelMapper.map(habitStatReward, HabitStatRewardDTO.class);
    }

    @Override
    public List<HabitStatRewardDTO> getHabitStatRewardsByHabitId(Long id) {
        // Retrieve the HabitStatRewards by habitId
        List<HabitStatReward> habitStatRewards = habitStatRewardRepository.findByHabitId(id);

        // Map each HabitStatReward entity to HabitStatRewardDTO using ModelMapper
        return habitStatRewards.stream()
                .map(habitStatReward -> modelMapper.map(habitStatReward, HabitStatRewardDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<HabitStatRewardDTO> getAllHabitStatRewards() {
        return habitStatRewardRepository.findAll()
                .stream()
                .map(habitStatReward -> modelMapper.map(habitStatReward, HabitStatRewardDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteHabitStatReward(Long id) {
        if (!habitStatRewardRepository.existsById(id)) {
            throw new RuntimeException("HabitStatReward not found");
        }
        habitStatRewardRepository.deleteById(id);
    }

}
