package com.zara.zaraservice.service.impl;

import com.zara.zaraservice.constants.ErrorMessages;
import com.zara.zaraservice.constants.SuccesMessages;
import com.zara.zaraservice.dto.UserDTO;
import com.zara.zaraservice.dto.requests.PasswordRequest;
import com.zara.zaraservice.dto.responses.ApiResponse;
import com.zara.zaraservice.entity.UserEntity;
import com.zara.zaraservice.exception.customexceptions.NotFoundException;
import com.zara.zaraservice.mapper.UserMapper;
import com.zara.zaraservice.repository.UserRepository;
import com.zara.zaraservice.service.UserService;
import com.zara.zaraservice.validation.UserValidation;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserValidation userValidation;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserValidation userValidation, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userValidation = userValidation;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDTO> getUserById(Long userId) {
        return Optional.ofNullable(
               userRepository.findById(userId)
                  .map(UserMapper::toDTO)
                  .orElseThrow(() -> new NotFoundException(
                         String.format(ErrorMessages.USER_ID_REQUIRED, userId))));
    }

    @Override
    public Optional<UserDTO> getUserByPhoneNumber(String userPhoneNumber) {
        return Optional.ofNullable(
                userRepository.findByPhoneNumber(userPhoneNumber)
                        .map(UserMapper::toDTO)
                        .orElseThrow(() -> new NotFoundException(
                                String.format(ErrorMessages.USER_PHONE_NUMBER_REQUIRED, userPhoneNumber))));
    }

    @Override
    public Optional<UserDTO> getUserByEmail(String userEmail) {
        return Optional.ofNullable(
                userRepository.findByEmail(userEmail)
                 .map(UserMapper::toDTO)
                 .orElseThrow(() -> new NotFoundException(
                         String.format(ErrorMessages.USER_EMAIL_REQUIRED, userEmail))));
    }

    @Override
    public UserDTO registerUser(UserDTO userDTO) {

        userValidation.validateUserDTO(userDTO);
        userValidation.checkIfUserAlreadyExists(userDTO);

        UserEntity userEntity = UserMapper.toEntity(userDTO);
        userEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        return UserMapper.toDTO(userRepository.save(userEntity));
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        userValidation.validateUserDTO(userDTO);

        UserEntity existingUser = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format(ErrorMessages.USER_ID_REQUIRED, id)));

        existingUser.setFirstName(userDTO.getFirstName());
        existingUser.setLastName(userDTO.getLastName());
        existingUser.setAvatar(userDTO.getAvatar());
        existingUser.setPhoneNumber(userDTO.getPhoneNumber());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        existingUser.setRole(userDTO.getRole());
        existingUser.setActive(userDTO.getIsActive());

        return UserMapper.toDTO(userRepository.save(existingUser));
    }

    //TODO informer l'utiliateur que son compte a été désactivé (par sms ou email)
    @Override
    public ApiResponse disableUserAccount(Long userID) {
        UserEntity existingUser = userRepository.findById(userID)
                .orElseThrow(() -> new NotFoundException(
                        String.format(ErrorMessages.USER_ID_REQUIRED, userID)));
        existingUser.setActive(false);
        userRepository.save(existingUser);

        return new ApiResponse(
                HttpStatus.OK,
                "200",
                SuccesMessages.USER_DISABLED_WITH_SUCCESS
        );
    }

    //TODO informer l'utiliateur que son compte a été activé (par sms ou email)
    @Override
    public ApiResponse activateUserAccount(Long userID) {
        UserEntity existingUser = userRepository.findById(userID)
                .orElseThrow(() -> new NotFoundException(
                        String.format(ErrorMessages.USER_ID_REQUIRED, userID)));
        existingUser.setActive(true);
        userRepository.save(existingUser);

        return new ApiResponse(
                HttpStatus.OK,
                "200",
                SuccesMessages.USER_ACTIVATE_WITH_SUCCESS
        );
    }

    @Override
    public ApiResponse deleteUser(Long userId) {
        userValidation.userIdIsExit(userId);
        userRepository.deleteById(userId);

        return new ApiResponse(
                HttpStatus.OK,
                "200",
                SuccesMessages.USER_DELETE_WITH_SUCCESS
        );
    }

    @Override
    public boolean comparePassword(PasswordRequest passwordRequest) {
        return  passwordEncoder.matches(passwordRequest.getNewPassword(), passwordRequest.getOldPassword());
    }

}
