package com.zara.zaraservice.mapper;

import com.zara.zaraservice.dto.ExperienceDTO;
import com.zara.zaraservice.entity.ConsultantEntity;
import com.zara.zaraservice.entity.ExperienceEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ExperienceMapper {

    public static ExperienceDTO toDTO(ExperienceEntity experience) {
        if (experience == null) {
            return null;
        }

        return ExperienceDTO.builder()
                .id(experience.getId())
                .companyName(experience.getCompanyName())
                .jobTitle(experience.getJobTitle())
                .startDate(experience.getStartDate())
                .endDate(experience.getEndDate())
                .description(experience.getDescription())
                .location(experience.getLocation())
                .consultantId(experience.getConsultantEntity() != null
                        ? experience.getConsultantEntity().getId()
                        : null)
                .createdDate(experience.getCreatedDate())
                .lastModifiedDate(experience.getLastModifiedDate())
                .build();
    }

    public static ExperienceEntity toEntity(ExperienceDTO experienceDTO) {
        if (experienceDTO == null) {
            return null;
        }

        return ExperienceEntity.builder()
                .id(experienceDTO.getId())
                .companyName(experienceDTO.getCompanyName())
                .jobTitle(experienceDTO.getJobTitle())
                .startDate(experienceDTO.getStartDate())
                .endDate(experienceDTO.getEndDate())
                .description(experienceDTO.getDescription())
                .location(experienceDTO.getLocation())
                .consultantEntity(experienceDTO.getConsultantId() != null
                        ? ConsultantEntity.builder().id(experienceDTO.getConsultantId()).build()
                        : null)
                .build();
    }

    public static List<ExperienceDTO> toDTOList(List<ExperienceEntity> experiences) {
        if (experiences == null) {
            return null;
        }

        return experiences.stream()
                .map(ExperienceMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static List<ExperienceEntity> toEntityList(List<ExperienceDTO> experienceDTOs) {
        if (experienceDTOs == null) {
            return null;
        }

        return experienceDTOs.stream()
                .map(ExperienceMapper::toEntity)
                .collect(Collectors.toList());
    }
}
