package com.zara.zaraservice.mapper;

import com.zara.zaraservice.dto.InterventionDTO;
import com.zara.zaraservice.entity.InterventionEntity;
import com.zara.zaraservice.entity.ClientEntity;
import com.zara.zaraservice.entity.ConsultantEntity;

import java.util.List;
import java.util.stream.Collectors;

public class InterventionMapper {

    public static InterventionDTO toDTO(InterventionEntity intervention) {
        if (intervention == null) {
            return null;
        }

        return InterventionDTO.builder()
                .id(intervention.getId())
                .localisation(intervention.getLocalisation())
                .consultantId(
                        intervention.getConsultantEntity() != null ? intervention.getConsultantEntity().getId() : null
                )
                .clientId(
                        intervention.getClientEntity() != null ? intervention.getClientEntity().getId() : null
                )
                .interventionDate(intervention.getInterventionDate())
                .description(intervention.getDescription())
                .createdDate(intervention.getCreatedDate())
                .lastModifiedDate(intervention.getLastModifiedDate())
                .build();
    }

    public static InterventionEntity toEntity(InterventionDTO interventionDTO) {
        if (interventionDTO == null) {
            return null;
        }

        return InterventionEntity.builder()
                .id(interventionDTO.getId())
                .localisation(interventionDTO.getLocalisation())
                .consultantEntity(
                        interventionDTO.getConsultantId() != null
                                ? ConsultantEntity.builder().id(interventionDTO.getConsultantId()).build()
                                : null
                )
                .clientEntity(
                        interventionDTO.getClientId() != null
                                ? ClientEntity.builder().id(interventionDTO.getClientId()).build()
                                : null
                )
                .interventionDate(interventionDTO.getInterventionDate())
                .description(interventionDTO.getDescription())
                .build();
    }

    public static List<InterventionDTO> toDTOList(List<InterventionEntity> interventions) {
        if (interventions == null) {
            return null;
        }

        return interventions.stream()
                .map(InterventionMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static List<InterventionEntity> toEntityList(List<InterventionDTO> interventionDTOs) {
        if (interventionDTOs == null) {
            return null;
        }

        return interventionDTOs.stream()
                .map(InterventionMapper::toEntity)
                .collect(Collectors.toList());
    }
}
