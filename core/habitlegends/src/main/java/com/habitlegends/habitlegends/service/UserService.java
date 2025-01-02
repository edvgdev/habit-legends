package com.habitlegends.habitlegends.service;

import java.util.List;

import com.habitlegends.habitlegends.dto.UserDTO;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(Long id, UserDTO userDTO);

    UserDTO getUserById(Long id);

    List<UserDTO> getAllUsers();

    void deleteUser(Long id);
}
