package com.zara.zaraservice.service.impl;

import com.zara.zaraservice.constants.ErrorMessages;
import com.zara.zaraservice.constants.SuccesMessages;
import com.zara.zaraservice.dto.ExperienceDTO;
import com.zara.zaraservice.dto.responses.ApiResponse;
import com.zara.zaraservice.entity.ExperienceEntity;
import com.zara.zaraservice.exception.customexceptions.NotFoundException;
import com.zara.zaraservice.mapper.ExperienceMapper;
import com.zara.zaraservice.repository.ExperienceRepository;
import com.zara.zaraservice.service.ExperienceService;
import com.zara.zaraservice.validation.ExperienceValidation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExperienceServiceImpl implements ExperienceService {

    private final ExperienceRepository experienceRepository;
    private final ExperienceValidation experienceValidation;

    public ExperienceServiceImpl(ExperienceRepository experienceRepository, ExperienceValidation experienceValidation) {
        this.experienceRepository = experienceRepository;
        this.experienceValidation = experienceValidation;
    }

    @Override
    public List<ExperienceDTO> getExperiencesByConsultantId(Long id) {
       return  experienceRepository.findByConsultantEntity_Id(id)
                .stream()
                .map(ExperienceMapper::toDTO)
                .toList();
    }

    @Override
    public Optional<ExperienceDTO> getExperienceById(Long id) {
        return Optional.ofNullable(experienceRepository.findById(id)
                .map(ExperienceMapper::toDTO)
                .orElseThrow(()-> new NotFoundException(
                        String.format(ErrorMessages.EXPERIENCE_ID_REQUIRED, id))));
    }

    //TODO date start doit être avant data end
    @Override
    public ExperienceDTO createExperience(ExperienceDTO experienceDTO) {
        experienceValidation.validateExperienceDTO(experienceDTO);
        experienceValidation.validateStartBeforeEnd(experienceDTO.getStartDate(), experienceDTO.getEndDate());

        ExperienceEntity experienceEntity = ExperienceMapper.toEntity(experienceDTO);

        return ExperienceMapper.toDTO(experienceRepository.save(experienceEntity));
    }

    //TODO date start doit être avant data end
    @Override
    public ExperienceDTO updateExperience(Long id, ExperienceDTO experienceDTO) {

        ExperienceEntity existingExperience = experienceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format(ErrorMessages.EXPERIENCE_ID_REQUIRED, id)));

        experienceValidation.validateStartBeforeEnd(experienceDTO.getStartDate(), experienceDTO.getEndDate());

        // Update all fields with values from the DTO
        existingExperience.setCompanyName(experienceDTO.getCompanyName());
        existingExperience.setJobTitle(experienceDTO.getJobTitle());
        existingExperience.setStartDate(experienceDTO.getStartDate());
        existingExperience.setEndDate(experienceDTO.getEndDate());
        existingExperience.setDescription(experienceDTO.getDescription());
        existingExperience.setLocation(experienceDTO.getLocation());

        return ExperienceMapper.toDTO(experienceRepository.save(existingExperience));
    }

    @Override
    public ApiResponse deleteExperience(Long id) {
        experienceValidation.experienceIsExist(id);
        experienceRepository.deleteById(id);

        return new ApiResponse(
                HttpStatus.OK,
                "200",
                SuccesMessages.EXPERIENCE_DELETE_WITH_SUCCESS
        );
    }
}
