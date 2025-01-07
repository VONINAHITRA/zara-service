package com.zara.zaraservice.dto.requests;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequestDTO {
    private String phoneNumber;
    private String password;
}
