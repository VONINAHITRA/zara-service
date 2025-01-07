package com.zara.zaraservice.mapper;

import com.zara.zaraservice.dto.FormationDTO;
import com.zara.zaraservice.entity.ConsultantEntity;
import com.zara.zaraservice.entity.FormationEntity;

import java.util.List;
import java.util.stream.Collectors;

public class FormationMapper {

    public static FormationDTO toDTO(FormationEntity formation) {
        if (formation == null) {
            return null;
        }

        return FormationDTO.builder()
                .id(formation.getId())
                .consultantId(
                        formation.getConsultantEntity() != null ? formation.getConsultantEntity().getId() : null
                )
                .establishment(formation.getEstablishment())
                .title(formation.getTitle())
                .description(formation.getDescription())
                .dateObtained(formation.getDateObtained())
                .createdDate(formation.getCreatedDate())
                .lastModifiedDate(formation.getLastModifiedDate())
                .build();
    }

    public static FormationEntity toEntity(FormationDTO formationDTO) {
        if (formationDTO == null) {
            return null;
        }

        return FormationEntity.builder()
                .id(formationDTO.getId())
                .consultantEntity(
                        formationDTO.getConsultantId() != null
                                ? ConsultantEntity.builder().id(formationDTO.getConsultantId()).build()
                                : null
                )
                .establishment(formationDTO.getEstablishment())
                .title(formationDTO.getTitle())
                .description(formationDTO.getDescription())
                .dateObtained(formationDTO.getDateObtained())
                .build();
    }

    public static List<FormationDTO> toDTOList(List<FormationEntity> formations) {
        if (formations == null) {
            return null;
        }

        return formations.stream()
                .map(FormationMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static List<FormationEntity> toEntityList(List<FormationDTO> formationDTOs) {
        if (formationDTOs == null) {
            return null;
        }

        return formationDTOs.stream()
                .map(FormationMapper::toEntity)
                .collect(Collectors.toList());
    }
}
