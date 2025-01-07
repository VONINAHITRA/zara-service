package com.zara.zaraservice.service;

import com.zara.zaraservice.dto.requests.AuthRequestDTO;
import com.zara.zaraservice.dto.responses.AuthResponseDTO;
import com.zara.zaraservice.dto.responses.TokenResponseDTO;

public interface AuthService  {
    AuthResponseDTO authenticate(AuthRequestDTO authRequestDTO);

    TokenResponseDTO refreshToken(String refreshToken);

    String logout(String authorizationHeader);
}
