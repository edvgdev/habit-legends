package com.habitlegends.habitlegends.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.habitlegends.habitlegends.dto.UserHabitStatDTO;
import com.habitlegends.habitlegends.entity.UserHabitStat;
import com.habitlegends.habitlegends.repository.UserHabitStatRepository;
import com.habitlegends.habitlegends.service.UserHabitStatService;

@Service
public class UserHabitStatServiceImpl implements UserHabitStatService {

    private final UserHabitStatRepository userHabitStatRepository;

    private final ModelMapper modelMapper;

    public UserHabitStatServiceImpl(UserHabitStatRepository userHabitStatRepository, ModelMapper modelMapper) {
        this.userHabitStatRepository = userHabitStatRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserHabitStatDTO createUserHabitStat(UserHabitStatDTO userHabitStatDTO) {
        UserHabitStat userHabitStat = modelMapper.map(userHabitStatDTO, UserHabitStat.class);
        UserHabitStat savedUserHabitStat = userHabitStatRepository.save(userHabitStat);
        return modelMapper.map(savedUserHabitStat, UserHabitStatDTO.class);
    }

    @Override
    public UserHabitStatDTO updateUserHabitStat(Long id, UserHabitStatDTO userHabitStatDTO) {
        UserHabitStat userHabitStat = userHabitStatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserHabitStat not found"));
        modelMapper.map(userHabitStatDTO, userHabitStat);
        UserHabitStat updatedUserHabitStat = userHabitStatRepository.save(userHabitStat);
        return modelMapper.map(updatedUserHabitStat, UserHabitStatDTO.class);
    }

    @Override
    public UserHabitStatDTO getUserHabitStatById(Long id) {
        UserHabitStat userHabitStat = userHabitStatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserHabitStat not found"));
        return modelMapper.map(userHabitStat, UserHabitStatDTO.class);
    }

    @Override
    public List<UserHabitStatDTO> getAllUserHabitStats() {
        return userHabitStatRepository.findAll()
                .stream()
                .map(userHabitStat -> modelMapper.map(userHabitStat, UserHabitStatDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUserHabitStat(Long id) {
        if (!userHabitStatRepository.existsById(id)) {
            throw new RuntimeException("UserHabitStat not found");
        }
        userHabitStatRepository.deleteById(id);
    }
}
