package com.zara.zaraservice.dto.responses;

public class TokenResponseDTO {
    private String accessToken;
    private String refreshToken;

    public TokenResponseDTO(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
