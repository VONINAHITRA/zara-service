package com.zara.zaraservice.service.impl;

import com.zara.zaraservice.constants.ErrorMessages;
import com.zara.zaraservice.constants.SuccesMessages;
import com.zara.zaraservice.dto.ConsultantDTO;
import com.zara.zaraservice.dto.responses.ApiResponse;
import com.zara.zaraservice.entity.ConsultantEntity;
import com.zara.zaraservice.entity.UserEntity;
import com.zara.zaraservice.exception.customexceptions.NotFoundException;
import com.zara.zaraservice.mapper.ClientMapper;
import com.zara.zaraservice.mapper.ConsultantMapper;
import com.zara.zaraservice.repository.ConsultantRepository;
import com.zara.zaraservice.repository.UserRepository;
import com.zara.zaraservice.service.ConsultantService;
import com.zara.zaraservice.validation.ConsultantValidation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConsultantServiceImpl implements ConsultantService {

    private final ConsultantRepository consultantRepository;
    private final ConsultantValidation consultantValidation;
    private final UserRepository userRepository;

    public ConsultantServiceImpl(ConsultantRepository consultantRepository, ConsultantValidation consultantValidation, UserRepository userRepository) {
        this.consultantRepository = consultantRepository;
        this.consultantValidation = consultantValidation;
        this.userRepository = userRepository;
    }

    @Override
    public List<ConsultantDTO> findAll(){
        return consultantRepository.findAll()
                .stream()
                .filter(consultantEntity -> Boolean.TRUE.equals(consultantEntity.getIsAvailable()))
                .map(ConsultantMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ConsultantDTO> getConsultantById(Long id) {
        return Optional.ofNullable(consultantRepository.findById(id)
                .map(ConsultantMapper::toDTO)
                .orElseThrow(() -> new NotFoundException( String.format(ErrorMessages.CONSULTANT_ID_REQUIRED, id))));
    }

    @Override
    public Optional<ConsultantDTO> getConsultantByUserId(Long userId) {
        return Optional.ofNullable(consultantRepository.findByUserId(userId)
                .map(ConsultantMapper::toDTO)
                .orElseThrow(()-> new NotFoundException(String.format(ErrorMessages.CONSULTANT_ID_REQUIRED, userId))));
    }

    @Override
    public ConsultantDTO createConsultant(ConsultantDTO consultantDTO) {

       UserEntity userEntity = userRepository.findById(consultantDTO.getUser().getId())
                  .orElseThrow(() -> new NotFoundException(
                          String.format(ErrorMessages.USER_ID_REQUIRED, consultantDTO.getUser().getId())));

       ConsultantEntity consultantEntity = ConsultantMapper.toEntity(consultantDTO);
       consultantEntity.setUserEntity(userEntity);
       ConsultantEntity savedConsultant = consultantRepository.save(consultantEntity);

            return ConsultantMapper.toDTO(savedConsultant);
        }

    @Override
    public ConsultantDTO updateConsultant(Long id, ConsultantDTO consultantDTO) {

        ConsultantEntity existingConsultant = consultantRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format(ErrorMessages.CONSULTANT_ID_REQUIRED, id)));

        existingConsultant.setLocalisation(consultantDTO.getLocalisation());
        existingConsultant.setSpecialization(consultantDTO.getSpecialization());
        existingConsultant.setDescription(consultantDTO.getDescription());

        return ConsultantMapper.toDTO(consultantRepository.save(existingConsultant));
    }

    @Override
    public ApiResponse setAvailableOrNotConsultant(Long id) {
        ConsultantEntity existingConsultant = consultantRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format(ErrorMessages.CONSULTANT_ID_REQUIRED, id)));

        if(existingConsultant.getIsAvailable()){
            existingConsultant.setIsAvailable(false);
        }else{
            existingConsultant.setIsAvailable(true);
        }
        consultantRepository.save(existingConsultant);

        return new ApiResponse(
                HttpStatus.OK,
                "200",
                SuccesMessages.USER_DISABLED_WITH_SUCCESS
        );
    }

    @Override
    public List<ConsultantDTO> searchConsultants(String keyword, String location) {
           List<ConsultantEntity> entities = consultantRepository.searchConsultants(
                keyword != null && !keyword.isEmpty() ? keyword : null,
                location != null && !location.isEmpty() ? location : null
               );
        return entities.stream()
                .map(ConsultantMapper::toDTO)
                .collect(Collectors.toList());
    }

}
