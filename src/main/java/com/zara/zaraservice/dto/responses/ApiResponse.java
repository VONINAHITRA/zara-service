package com.zara.zaraservice.dto.responses;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private Map<String, Object> status;

    public ApiResponse(HttpStatus httpStatus, String code, String message) {
        this.status = new HashMap<>();
        this.status.put("httpStatus", httpStatus.name());
        this.status.put("statusCode", httpStatus.value());
        this.status.put("message", message);
    }
}
