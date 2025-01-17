package com.habitlegends.habitlegends.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.habitlegends.habitlegends.details.UserProfileDetails;
import com.habitlegends.habitlegends.dto.UserDTO;
import com.habitlegends.habitlegends.entity.User;
import com.habitlegends.habitlegends.exception.UnauthorizedException;
import com.habitlegends.habitlegends.repository.UserRepository;
import com.habitlegends.habitlegends.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserProfileDetails getUserProfile(UserDetails userDetails) {
        if (userDetails != null) {
            // Fetch user profile from the database
            String email = userDetails.getUsername();
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("Habit Category not found"));

            // Handle null UserPlan
            Integer planId = (user.getUserPlan() != null) ? user.getUserPlan().getId() : null;

            UserProfileDetails userProfileDetails = new UserProfileDetails(
                    user.getId(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getProfilePictureLink(),
                    planId,
                    user.getRole());
            return userProfileDetails;
        } else {
            // User is not authenticated
            throw new UnauthorizedException("User is not authenticated");
        }
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
    }

}
