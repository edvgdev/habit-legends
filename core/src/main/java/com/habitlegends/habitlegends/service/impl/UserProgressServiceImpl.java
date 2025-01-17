package com.habitlegends.habitlegends.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.habitlegends.habitlegends.dto.UserProgressDTO;
import com.habitlegends.habitlegends.entity.UserProgress;
import com.habitlegends.habitlegends.repository.UserProgressRepository;
import com.habitlegends.habitlegends.service.UserProgressService;

@Service
public class UserProgressServiceImpl implements UserProgressService {

    private final UserProgressRepository userProgressRepository;

    private final ModelMapper modelMapper;

    public UserProgressServiceImpl(UserProgressRepository userProgressRepository, ModelMapper modelMapper) {
        this.userProgressRepository = userProgressRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserProgressDTO createUserProgress(UserProgressDTO userProgressDTO) {
        UserProgress userProgress = modelMapper.map(userProgressDTO, UserProgress.class);
        UserProgress savedUserProgress = userProgressRepository.save(userProgress);
        return modelMapper.map(savedUserProgress, UserProgressDTO.class);
    }

    @Override
    public UserProgressDTO updateUserProgress(Long id, UserProgressDTO userProgressDTO) {
        UserProgress userProgress = userProgressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserProgress not found"));
        modelMapper.map(userProgressDTO, userProgress);
        UserProgress updatedUserProgress = userProgressRepository.save(userProgress);
        return modelMapper.map(updatedUserProgress, UserProgressDTO.class);
    }

    @Override
    public UserProgressDTO getUserProgressById(Long id) {
        UserProgress userProgress = userProgressRepository.findById(id)
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

}
