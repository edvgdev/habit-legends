package com.habitlegends.habitlegends.user;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.habitlegends.habitlegends.exception.UnauthorizedException;

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
                    .orElseThrow(() -> new RuntimeException("User not found"));

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
    public UserDTO getUserDTOById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<UserDTO> getAllUserDTOs() {
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
