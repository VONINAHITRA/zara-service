package com.zara.zaraservice.validation;

import com.zara.zaraservice.constants.ErrorMessages;
import com.zara.zaraservice.dto.UserDTO;
import com.zara.zaraservice.exception.customexceptions.NotFoundException;
import com.zara.zaraservice.exception.customexceptions.UserAlreadyExistsException;
import com.zara.zaraservice.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserValidation {

    private final UserRepository userRepository;

    public UserValidation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validateUserDTO(UserDTO userDTO) {
        if (isNullOrEmpty(userDTO.getPhoneNumber())) {
            throw new IllegalArgumentException(ErrorMessages.PHONE_REQUIRED);
        }
    }

    public void checkIfUserAlreadyExists(UserDTO userDTO) {

        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new UserAlreadyExistsException(
                    String.format(ErrorMessages.EMAIL_ALREADY_EXISTS, userDTO.getEmail())
            );
        }
        if (userRepository.existsByPhoneNumber(userDTO.getPhoneNumber())) {
            throw new UserAlreadyExistsException(
                    String.format(ErrorMessages.PHONE_ALREADY_EXISTS, userDTO.getPhoneNumber())
            );
        }
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    public void userIdIsExit(Long userId) {
        if (userId == null || !userRepository.existsById(userId)) {
            throw new NotFoundException(
                    String.format(ErrorMessages.USER_ID_REQUIRED, userId)
            );
        }
    }

}
