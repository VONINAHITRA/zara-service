package com.zara.zaraservice.service;

import com.zara.zaraservice.dto.ExperienceDTO;
import com.zara.zaraservice.dto.responses.ApiResponse;

import java.util.List;
import java.util.Optional;

public interface ExperienceService {
    ExperienceDTO createExperience(ExperienceDTO experienceDTO);

    List<ExperienceDTO> getExperiencesByConsultantId(Long id);

    Optional<ExperienceDTO> getExperienceById(Long id);

    ApiResponse deleteExperience(Long id);

    ExperienceDTO updateExperience(Long id, ExperienceDTO experienceDTO);
}
