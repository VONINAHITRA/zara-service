package com.zara.zaraservice.validation;

import com.zara.zaraservice.constants.ErrorMessages;
import com.zara.zaraservice.dto.ExperienceDTO;
import com.zara.zaraservice.exception.customexceptions.InvalidDataException;
import com.zara.zaraservice.exception.customexceptions.InvalidDateRangeException;
import com.zara.zaraservice.exception.customexceptions.NotFoundException;
import com.zara.zaraservice.repository.ExperienceRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ExperienceValidation {

    private final ExperienceRepository experienceRepository;

    public ExperienceValidation(ExperienceRepository experienceRepository) {
        this.experienceRepository = experienceRepository;
    }

    public void validateExperienceDTO(ExperienceDTO experienceDTO) {
        if (experienceDTO == null) {
            throw new InvalidDataException(ErrorMessages.EXPERIENCE_REQUIRED);
        }

        if (experienceDTO.getConsultantId() == null) {
            throw new InvalidDataException(ErrorMessages.EXPERIENCE_CONSULTANT_ID_REQUIRED);
        }

        if (isNullOrEmpty(experienceDTO.getCompanyName())) {
            throw new InvalidDataException(ErrorMessages.EXPERIENCE_COMPANY_NAME_REQUIRED);
        }

        if (isNullOrEmpty(experienceDTO.getJobTitle())) {
            throw new InvalidDataException(ErrorMessages.EXPERIENCE_JOB_TITLE_REQUIRED);
        }

        if (experienceDTO.getStartDate() == null) {
            throw new InvalidDataException(ErrorMessages.EXPERIENCE_START_DATE_REQUIRED);
        }
    }

    public void validateStartBeforeEnd(LocalDate startDate, LocalDate endDate){
        if(startDate !=null && endDate !=null && startDate.isAfter(endDate)){
                 throw new InvalidDateRangeException(
                         String.format(ErrorMessages.EXPERIENCE_DATE_VERIFICATION, "error"));
             }
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    public void experienceIsExist(Long id) {

        if (id == null || !experienceRepository.existsById(id)) {
            throw new NotFoundException(
                    String.format(ErrorMessages.EXPERIENCE_ID_REQUIRED, id)
            );
        }
    }

}
