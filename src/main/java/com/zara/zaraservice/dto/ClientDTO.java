package com.zara.zaraservice.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {

    private Long id;

    private String localisation;

    private UserDTO user;

    private List<InterventionDTO> interventions;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;
}
