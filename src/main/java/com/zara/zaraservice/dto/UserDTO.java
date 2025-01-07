package com.zara.zaraservice.dto;

import com.zara.zaraservice.enums.Role;
import com.zara.zaraservice.entity.ClientEntity;
import com.zara.zaraservice.entity.ConsultantEntity;
import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    //TODO to create the new clas strategy
    private Long id;

    private String firstName;

    private String lastName;

    private String avatar;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    private String token;

    private Role role;

    @Builder.Default
    private Boolean isActive = false;

    private ConsultantEntity consultantEntity;

    private ClientEntity clientEntity;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;
}
