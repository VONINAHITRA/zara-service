package com.zara.zaraservice.mapper;

import com.zara.zaraservice.dto.ConsultantDTO;
import com.zara.zaraservice.entity.ConsultantEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ConsultantMapper {

    public static ConsultantDTO toDTO(ConsultantEntity consultant) {
        if (consultant == null) {
            return null;
        }

        return ConsultantDTO.builder()
                .id(consultant.getId())
                .interventionsCount(consultant.getInterventionsCount())
                .localisation(consultant.getLocalisation())
                .specialization(consultant.getSpecialization())
                .description(consultant.getDescription())
                .isAvailable(consultant.getIsAvailable())
                .user(UserMapper.toDTO(consultant.getUserEntity()))
                .interventions(InterventionMapper.toDTOList(consultant.getInterventionEntities()))
                .feedbacks(FeedbackMapper.toDTOList(consultant.getFeedbackEntities()))
                .formations(FormationMapper.toDTOList(consultant.getFormationEntities()))
                .experiences(ExperienceMapper.toDTOList(consultant.getExperienceEntities()))
                .createdDate(consultant.getCreatedDate())
                .lastModifiedDate(consultant.getLastModifiedDate())
                .build();
    }

    public static ConsultantEntity toEntity(ConsultantDTO consultantDTO) {
        if (consultantDTO == null) {
            return null;
        }

        return ConsultantEntity.builder()
                .interventionsCount(consultantDTO.getInterventionsCount())
                .localisation(consultantDTO.getLocalisation())
                .specialization(consultantDTO.getSpecialization())
                .description(consultantDTO.getDescription())
                .isAvailable(consultantDTO.getIsAvailable())
                .userEntity(UserMapper.toEntity(consultantDTO.getUser()))
                .interventionEntities(InterventionMapper.toEntityList(consultantDTO.getInterventions()))
                .feedbackEntities(FeedbackMapper.toEntityList(consultantDTO.getFeedbacks()))
                .formationEntities(FormationMapper.toEntityList(consultantDTO.getFormations()))
                .experienceEntities(ExperienceMapper.toEntityList(consultantDTO.getExperiences()))
                .build();
    }

    public static List<ConsultantDTO> toDTOList(List<ConsultantEntity> consultants) {
        if (consultants == null) {
            return null;
        }

        return consultants.stream()
                .map(ConsultantMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static List<ConsultantEntity> toEntityList(List<ConsultantDTO> consultantDTOs) {
        if (consultantDTOs == null) {
            return null;
        }

        return consultantDTOs.stream()
                .map(ConsultantMapper::toEntity)
                .collect(Collectors.toList());
    }
}
