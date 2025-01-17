package com.habitlegends.habitlegends.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.habitlegends.habitlegends.details.UserProfileDetails;
import com.habitlegends.habitlegends.dto.UserDTO;

public interface UserService {

    UserDTO getUserById(Long id);

    List<UserDTO> getAllUsers();

    void deleteUser(Long id);

    UserProfileDetails getUserProfile(UserDetails userDetails);
}
