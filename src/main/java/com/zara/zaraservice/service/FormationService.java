package com.zara.zaraservice.service;

import com.zara.zaraservice.dto.FormationDTO;
import com.zara.zaraservice.dto.responses.ApiResponse;

import java.util.List;
import java.util.Optional;

public interface FormationService {
    FormationDTO createFormation(FormationDTO formationDTO);

    FormationDTO updateFormation(Long id, FormationDTO formationDTO);

    List<FormationDTO> getFormationsByConsultantId(Long id);

    Optional<FormationDTO> getFormationById(Long id);

    ApiResponse deleteFormation(Long id);
}
