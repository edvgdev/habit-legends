package com.habitlegends.habitlegends.user;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

    UserDTO getUserDTOById(Long id);

    List<UserDTO> getAllUserDTOs();

    void deleteUser(Long id);

    UserProfileDetails getUserProfile(UserDetails userDetails);

    User getUserById(Long id);
}
