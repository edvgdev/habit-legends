package com.habitlegends.habitlegends.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.habitlegends.habitlegends.dto.UserPlanDTO;
import com.habitlegends.habitlegends.entity.UserPlan;
import com.habitlegends.habitlegends.repository.UserPlanRepository;
import com.habitlegends.habitlegends.service.UserPlanService;

@Service
public class UserPlanServiceImpl implements UserPlanService {

    private final UserPlanRepository userPlanRepository;

    private final ModelMapper modelMapper;

    public UserPlanServiceImpl(UserPlanRepository userPlanRepository, ModelMapper modelMapper) {
        this.userPlanRepository = userPlanRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserPlanDTO createUserPlan(UserPlanDTO userPlanDTO) {
        UserPlan userPlan = modelMapper.map(userPlanDTO, UserPlan.class);
        UserPlan savedUserPlan = userPlanRepository.save(userPlan);
        return modelMapper.map(savedUserPlan, UserPlanDTO.class);
    }

    @Override
    public UserPlanDTO updateUserPlan(Integer id, UserPlanDTO userPlanDTO) {
        UserPlan userPlan = userPlanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserPlan not found"));
        modelMapper.map(userPlanDTO, userPlan);
        UserPlan updatedUserPlan = userPlanRepository.save(userPlan);
        return modelMapper.map(updatedUserPlan, UserPlanDTO.class);
    }

    @Override
    public UserPlanDTO getUserPlanById(Integer id) {
        UserPlan userPlan = userPlanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserPlan not found"));
        return modelMapper.map(userPlan, UserPlanDTO.class);
    }

    @Override
    public List<UserPlanDTO> getAllUserPlans() {
        return userPlanRepository.findAll()
                .stream()
                .map(userPlan -> modelMapper.map(userPlan, UserPlanDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUserPlan(Integer id) {
        if (!userPlanRepository.existsById(id)) {
            throw new RuntimeException("UserPlan not found");
        }
        userPlanRepository.deleteById(id);
    }
}
