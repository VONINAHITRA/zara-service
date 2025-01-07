package com.zara.zaraservice.dto;

import com.zara.zaraservice.enums.StatusRDV;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InterventionDTO {
    private Long id;

    private String localisation;

    private StatusRDV statusRDV;

    private Long consultantId;

    private Long clientId;

    private LocalDateTime interventionDate;

    private String description;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;
}
