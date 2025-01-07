package com.zara.zaraservice.dto.requests;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailRequest {
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String email;
    private String avatar;
    private Long id;
    private String role;
}