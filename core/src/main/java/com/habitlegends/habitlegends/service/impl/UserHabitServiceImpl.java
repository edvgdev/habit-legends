package com.habitlegends.habitlegends.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.habitlegends.habitlegends.dto.UserHabitDTO;
import com.habitlegends.habitlegends.entity.UserHabit;
import com.habitlegends.habitlegends.repository.UserHabitRepository;
import com.habitlegends.habitlegends.service.UserHabitService;

@Service
public class UserHabitServiceImpl implements UserHabitService {

    private final UserHabitRepository userHabitRepository;

    private final ModelMapper modelMapper;

    public UserHabitServiceImpl(UserHabitRepository userHabitRepository, ModelMapper modelMapper) {
        this.userHabitRepository = userHabitRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserHabitDTO createUserHabit(UserHabitDTO userHabitDTO) {
        UserHabit userHabit = modelMapper.map(userHabitDTO, UserHabit.class);
        UserHabit savedUserHabit = userHabitRepository.save(userHabit);
        return modelMapper.map(savedUserHabit, UserHabitDTO.class);
    }

    @Override
    public UserHabitDTO updateUserHabit(Long id, UserHabitDTO userHabitDTO) {
        UserHabit userHabit = userHabitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserHabit not found"));
        modelMapper.map(userHabitDTO, userHabit);
        UserHabit updatedUserHabit = userHabitRepository.save(userHabit);
        return modelMapper.map(updatedUserHabit, UserHabitDTO.class);
    }

    @Override
    public UserHabitDTO getUserHabitById(Long id) {
        UserHabit userHabit = userHabitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserHabit not found"));
        return modelMapper.map(userHabit, UserHabitDTO.class);
    }

    @Override
    public List<UserHabitDTO> getAllUserHabits() {
        return userHabitRepository.findAll()
                .stream()
                .map(userHabit -> modelMapper.map(userHabit, UserHabitDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUserHabit(Long id) {
        if (!userHabitRepository.existsById(id)) {
            throw new RuntimeException("UserHabit not found");
        }
        userHabitRepository.deleteById(id);
    }

}
