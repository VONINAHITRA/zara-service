package com.zara.zaraservice.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FormationDTO {
    private Long id;

    private Long consultantId;

    private String establishment;

    private String title;

    private String description;

    private LocalDate dateObtained;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;
}
