package com.zara.zaraservice.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsultantDTO {
    private Long id;

    private int interventionsCount;

    private Boolean isAvailable;

    private String localisation;

    private String specialization;

    private String description;

    private UserDTO user;

    private List<InterventionDTO> interventions;

    private List<FeedbackDTO> feedbacks;

    private List<FormationDTO> formations;

    private List<ExperienceDTO> experiences;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;
}
