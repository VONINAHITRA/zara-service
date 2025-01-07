package com.zara.zaraservice.dto.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordRequest {
    private String newPassword;
    private String oldPassword;
}
