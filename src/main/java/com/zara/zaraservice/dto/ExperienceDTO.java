package com.zara.zaraservice.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExperienceDTO {
    private Long id;

    private String companyName;

    private String jobTitle;

    private LocalDate startDate;

    private LocalDate endDate;

    private String description;

    private String location;

    private Long consultantId;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;
}
