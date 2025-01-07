package com.zara.zaraservice.service.impl;

import com.zara.zaraservice.constants.ErrorMessages;
import com.zara.zaraservice.constants.SuccesMessages;
import com.zara.zaraservice.dto.UserDTO;
import com.zara.zaraservice.dto.requests.AuthRequestDTO;
import com.zara.zaraservice.dto.responses.AuthResponseDTO;
import com.zara.zaraservice.dto.responses.TokenResponseDTO;
import com.zara.zaraservice.entity.UserEntity;
import com.zara.zaraservice.exception.customexceptions.NotFoundException;
import com.zara.zaraservice.security.JwtUtil;
import com.zara.zaraservice.security.TokenBlacklistService;
import com.zara.zaraservice.service.AuthService;
import com.zara.zaraservice.validation.AuthValidation;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final JwtUtil jwtUtil;
    private final AuthValidation authValidation;
    private final TokenBlacklistService blacklistService;
    private final UserServiceImpl userService;

    public AuthServiceImpl(JwtUtil jwtUtil, AuthValidation authValidation, TokenBlacklistService blacklistService, UserServiceImpl userService) {
        this.jwtUtil = jwtUtil;
        this.authValidation = authValidation;
        this.blacklistService = blacklistService;
        this.userService = userService;
    }

    @Override
    public AuthResponseDTO authenticate(AuthRequestDTO authRequestDTO) {
        authValidation.validatePhoneNumberAndPassword(authRequestDTO);
        Optional<UserDTO> user = userService.getUserByPhoneNumber(authRequestDTO.getPhoneNumber());

        String accessToken = null;
        String refreshToken = null;

        if (user.isPresent()) {
            UserDTO getUser = user.get();
            accessToken = jwtUtil.generateToken(
                    getUser.getPhoneNumber(),
                    getUser.getFirstName(),
                    getUser.getLastName(),
                    getUser.getEmail(),
                    getUser.getAvatar(),
                    getUser.getId(),
                    String.valueOf(getUser.getRole()));

            refreshToken = jwtUtil.generateRefreshToken(
                    getUser.getPhoneNumber(),
                    getUser.getFirstName(),
                    getUser.getLastName(),
                    getUser.getEmail(),
                    getUser.getAvatar(),
                    getUser.getId(),
                    String.valueOf(getUser.getRole()));
        }

        return new AuthResponseDTO(accessToken, refreshToken);
    }

    //TODO à refaire (exception)
    @Override
    public TokenResponseDTO refreshToken(String refreshToken) {
        authValidation.validateRefreshToken(refreshToken);
        Optional<UserDTO> userByPhoneNumber = userService.getUserByPhoneNumber(jwtUtil.extractUsername(refreshToken));
        Optional<UserDTO> userByEmail = userService.getUserByEmail(jwtUtil.extractUsername(refreshToken));

        String newAccessToken = null;

        if (userByPhoneNumber.isPresent()) {
            UserDTO getUser = userByPhoneNumber.get();
             newAccessToken = jwtUtil.generateToken(
                    getUser.getPhoneNumber(),
                    getUser.getFirstName(),
                    getUser.getLastName(),
                    getUser.getEmail(),
                    getUser.getAvatar(),
                    getUser.getId(),
                    String.valueOf(getUser.getRole().name()));
        }else {
            UserDTO getUser = userByEmail.get();
            newAccessToken = jwtUtil.generateToken(
                    getUser.getPhoneNumber(),
                    getUser.getFirstName(),
                    getUser.getLastName(),
                    getUser.getEmail(),
                    getUser.getAvatar(),
                    getUser.getId(),
                    String.valueOf(getUser.getRole().name()));
        }

        return new TokenResponseDTO(newAccessToken, refreshToken);
    }

    @Override
    public String logout(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            blacklistService.blacklistToken(token);
        } else {
            throw new RuntimeException(ErrorMessages.TOKEN_MISSING_OR_INVALID);
        }

        return SuccesMessages.USER_LOGOUT_WITH_SUCCESS;
    }
}
