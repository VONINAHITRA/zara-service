package com.zara.zaraservice.validation;

import com.zara.zaraservice.constants.ErrorMessages;
import com.zara.zaraservice.dto.requests.AuthRequestDTO;
import com.zara.zaraservice.entity.UserEntity;
import com.zara.zaraservice.exception.customexceptions.InvalidRefreshTokenException;
import com.zara.zaraservice.exception.customexceptions.NotFoundException;
import com.zara.zaraservice.exception.customexceptions.UserInactiveException;
import com.zara.zaraservice.repository.UserRepository;
import com.zara.zaraservice.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

//TODO à vérifier
@Component
public class AuthValidation {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;


    public AuthValidation(UserRepository userRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public void validatePhoneNumberAndPassword(AuthRequestDTO authRequestDTO) {

        UserEntity user = userRepository.findByPhoneNumber(authRequestDTO.getPhoneNumber())
                .orElseThrow(() -> new NotFoundException(
                        String.format(ErrorMessages.USER_PHONE_NUMBER_REQUIRED, authRequestDTO.getPhoneNumber())));

        /*if (!Boolean.TRUE.equals(user.isActive())) {
            throw new UserInactiveException(
                    String.format(ErrorMessages.USER_NOT_ACTIVE, user.getFirstName(), user.getLastName()));
        }*/

        if (!passwordEncoder.matches(authRequestDTO.getPassword(), user.getPassword())) {
            throw new NotFoundException(String.format(ErrorMessages.USER_PASSWORD_REQUIRED, authRequestDTO.getPassword()));
        }
    }

    public void validateRefreshToken(String refreshToken) {
        if (refreshToken == null || refreshToken.isEmpty()) {
            throw new InvalidRefreshTokenException(ErrorMessages.USER_TOKEN_NULL_EMPTY);
        }

        if (!jwtUtil.validateToken(refreshToken, jwtUtil.extractUsername(refreshToken))) {
            throw new InvalidRefreshTokenException(ErrorMessages.USER_TOKEN_INVALID);
        }
    }
}
