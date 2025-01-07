package com.zara.zaraservice.service;

import com.zara.zaraservice.dto.ConsultantDTO;
import com.zara.zaraservice.dto.responses.ApiResponse;

import java.util.List;
import java.util.Optional;

public interface ConsultantService {
    List<ConsultantDTO> findAll();

    ConsultantDTO createConsultant(ConsultantDTO consultantDTO);

    Optional<ConsultantDTO> getConsultantById(Long id);

    ConsultantDTO updateConsultant(Long id, ConsultantDTO consultantDTO);

    ApiResponse setAvailableOrNotConsultant(Long id);

    List<ConsultantDTO> searchConsultants(String keyword, String location);

    Optional<ConsultantDTO> getConsultantByUserId(Long userId);
}
