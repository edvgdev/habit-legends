package com.habitlegends.habitlegends.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.habitlegends.habitlegends.details.UserProfileDetails;
import com.habitlegends.habitlegends.dto.UserDTO;
import com.habitlegends.habitlegends.entity.User;

public interface UserService {

    UserDTO getUserDTOById(Long id);

    List<UserDTO> getAllUserDTOs();

    void deleteUser(Long id);

    UserProfileDetails getUserProfile(UserDetails userDetails);

    User getUserById(Long id);
}
