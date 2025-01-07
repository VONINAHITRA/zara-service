package com.zara.zaraservice.service;

import com.zara.zaraservice.dto.requests.PasswordRequest;
import com.zara.zaraservice.dto.responses.ApiResponse;
import com.zara.zaraservice.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDTO> findAll();

    Optional<UserDTO> getUserById(Long userId);

    UserDTO registerUser(UserDTO userDTO);

    ApiResponse deleteUser(Long userId);

    UserDTO updateUser(Long userId, UserDTO userDTO);

    Optional<UserDTO> getUserByEmail(String userEmail);

    Optional<UserDTO> getUserByPhoneNumber(String userPhoneNumber);

    ApiResponse disableUserAccount(Long userId);

    ApiResponse activateUserAccount(Long userID);

    boolean comparePassword(PasswordRequest passwordRequest);
}
