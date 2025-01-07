package com.zara.zaraservice.controller;

import com.zara.zaraservice.dto.requests.AuthRequestDTO;
import com.zara.zaraservice.dto.responses.AuthResponseDTO;
import com.zara.zaraservice.dto.requests.RefreshTokenRequestDTO;
import com.zara.zaraservice.dto.responses.TokenResponseDTO;
import com.zara.zaraservice.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO authRequestDTO) {
        return ResponseEntity.ok(authService.authenticate(authRequestDTO));
    }

    //TODO à faire ici
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String authorizationHeader) {
        return ResponseEntity.ok(authService.logout(authorizationHeader));
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<TokenResponseDTO> refreshToken(@RequestBody RefreshTokenRequestDTO tokenRequestDTO) {
        return ResponseEntity.ok(authService.refreshToken(tokenRequestDTO.getRefreshToken()));
    }
}
