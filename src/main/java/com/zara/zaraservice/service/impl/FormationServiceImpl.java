package com.zara.zaraservice.service.impl;

import com.zara.zaraservice.constants.ErrorMessages;
import com.zara.zaraservice.constants.SuccesMessages;
import com.zara.zaraservice.dto.FormationDTO;
import com.zara.zaraservice.dto.responses.ApiResponse;
import com.zara.zaraservice.entity.FormationEntity;
import com.zara.zaraservice.exception.customexceptions.NotFoundException;
import com.zara.zaraservice.mapper.FormationMapper;
import com.zara.zaraservice.repository.FormationRepository;
import com.zara.zaraservice.service.FormationService;
import com.zara.zaraservice.validation.FormationValidation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormationServiceImpl implements FormationService {

    private final FormationRepository formationRepository;
    private final FormationValidation formationValidation;

    public FormationServiceImpl(FormationRepository formationRepository, FormationValidation formationValidation) {
        this.formationRepository = formationRepository;
        this.formationValidation = formationValidation;
    }

    @Override
    public FormationDTO createFormation(FormationDTO formationDTO) {
        formationValidation.validateFormationDTO(formationDTO);
        FormationEntity formationEntity = FormationMapper.toEntity(formationDTO);

        return FormationMapper.toDTO(formationRepository.save(formationEntity));
    }

    @Override
    public FormationDTO updateFormation(Long id, FormationDTO formationDTO) {
        FormationEntity existingFormation = formationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format(ErrorMessages.EXPERIENCE_ID_REQUIRED, id)));

        existingFormation.setEstablishment(formationDTO.getEstablishment());
        existingFormation.setTitle(formationDTO.getTitle());
        existingFormation.setDescription(formationDTO.getDescription());
        existingFormation.setDateObtained(formationDTO.getDateObtained());

        return FormationMapper.toDTO(formationRepository.save(existingFormation));
    }

    @Override
    public List<FormationDTO> getFormationsByConsultantId(Long id) {
        return formationRepository.findByConsultantEntity_Id(id)
                .stream()
                .map(FormationMapper::toDTO)
                .toList();
    }

    @Override
    public Optional<FormationDTO> getFormationById(Long id) {
        return Optional.ofNullable(formationRepository.findById(id)
                .map(FormationMapper::toDTO)
                .orElseThrow(()-> new NotFoundException(
                        String.format(ErrorMessages.FORMATION_ID_REQUIRED, id))));
    }

    //TODO id à vérifier
    @Override
    public ApiResponse deleteFormation(Long id) {
        formationValidation.feedbackIdIsExit(id);
        formationRepository.deleteById(id);

        return new ApiResponse(
                HttpStatus.OK,
                "200",
                SuccesMessages.FORMATION_DELETE_WITH_SUCCESS
        );
    }
}
