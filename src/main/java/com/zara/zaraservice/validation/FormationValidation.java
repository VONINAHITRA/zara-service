package com.zara.zaraservice.validation;

import com.zara.zaraservice.constants.ErrorMessages;
import com.zara.zaraservice.dto.FormationDTO;
import com.zara.zaraservice.exception.customexceptions.InvalidDataException;
import com.zara.zaraservice.exception.customexceptions.InvalidDateRangeException;
import com.zara.zaraservice.exception.customexceptions.NotFoundException;
import com.zara.zaraservice.repository.FormationRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class FormationValidation {

    private final FormationRepository formationRepository;

    public FormationValidation(FormationRepository formationRepository) {
        this.formationRepository = formationRepository;
    }

    public void validateFormationDTO(FormationDTO formationDTO) {
        if (formationDTO == null) {
            throw new InvalidDataException(ErrorMessages.FORMATION_REQUIRED);
        }

        if (formationDTO.getTitle() == null) {
            throw new InvalidDataException(ErrorMessages.FORMATION_TITLE_REQUIRED);
        }

        if (isNullOrEmpty(formationDTO.getEstablishment())) {
            throw new InvalidDataException(ErrorMessages.FORMATION_ESTABLISHMENT_REQUIRED);
        }
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    public void feedbackIdIsExit(Long formationId) {
        if (formationId == null || !formationRepository.existsById(formationId)) {
            throw new NotFoundException(
                    String.format(ErrorMessages.FORMATION_ID_REQUIRED, formationId)
            );
        }
    }
}
