package com.habitlegends.habitlegends.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.habitlegends.habitlegends.details.UserHabitDetails;
import com.habitlegends.habitlegends.dto.UserHabitDTO;
import com.habitlegends.habitlegends.entity.Habit;
import com.habitlegends.habitlegends.entity.User;
import com.habitlegends.habitlegends.entity.UserHabit;
import com.habitlegends.habitlegends.repository.UserHabitRepository;
import com.habitlegends.habitlegends.service.HabitService;
import com.habitlegends.habitlegends.service.UserHabitService;
import com.habitlegends.habitlegends.service.UserService;

@Service
public class UserHabitServiceImpl implements UserHabitService {

    private final UserHabitRepository userHabitRepository;

    private final UserService userService;

    private final HabitService habitService;

    private final ModelMapper modelMapper;

    public UserHabitServiceImpl(UserHabitRepository userHabitRepository, UserService userService,
            HabitService habitService, ModelMapper modelMapper) {
        this.userHabitRepository = userHabitRepository;
        this.userService = userService;
        this.habitService = habitService;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public UserHabitDTO createUserHabit(UserHabitDetails userHabitDetails) {

        if (userHabitDetails == null || userHabitDetails.getHabitDetails() == null) {
            throw new IllegalArgumentException("UserHabitDetails and HabitDetails must not be null");
        }

        User user = modelMapper.map(userService.getUserById(userHabitDetails.getUserId()), User.class);
        Habit habit = modelMapper.map(habitService.getHabitById(userHabitDetails.getHabitDetails().getHabit().getId()),
                Habit.class);

        UserHabit userHabit = new UserHabit();
        userHabit.setUser(user);
        userHabit.setHabit(habit);

        UserHabit savedUserHabit = userHabitRepository.save(userHabit);

        return modelMapper.map(savedUserHabit, UserHabitDTO.class);
    }

    @Override
    @Transactional
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

    @Override
    public List<UserHabitDetails> getAllUserHabitDetails(Long userId) {

        List<UserHabitDTO> userHabitDTOs = userHabitRepository.findByUserId(userId)
                .stream().map(userHabit -> modelMapper.map(userHabit, UserHabitDTO.class))
                .collect(Collectors.toList());

        List<UserHabitDetails> userHabitDetailsList = userHabitDTOs.stream().map(
                userHabitDTO -> {
                    return new UserHabitDetails(
                            userId,
                            habitService.getHabitDetailsById(userHabitDTO.getId()));
                }).collect(Collectors.toList());

        return userHabitDetailsList;
    }

}
