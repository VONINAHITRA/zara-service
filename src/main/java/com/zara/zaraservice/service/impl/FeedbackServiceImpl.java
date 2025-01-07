package com.zara.zaraservice.service.impl;

import com.zara.zaraservice.constants.ErrorMessages;
import com.zara.zaraservice.constants.SuccesMessages;
import com.zara.zaraservice.dto.ExperienceDTO;
import com.zara.zaraservice.dto.FeedbackDTO;
import com.zara.zaraservice.dto.FormationDTO;
import com.zara.zaraservice.dto.responses.ApiResponse;
import com.zara.zaraservice.entity.FeedbackEntity;
import com.zara.zaraservice.exception.customexceptions.NotFoundException;
import com.zara.zaraservice.mapper.FeedbackMapper;
import com.zara.zaraservice.repository.FeedbackRepository;
import com.zara.zaraservice.service.FeedbackService;
import com.zara.zaraservice.validation.FeedbackValidation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final FeedbackValidation feedbackValidation;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository, FeedbackValidation feedbackValidation) {
        this.feedbackRepository = feedbackRepository;
        this.feedbackValidation = feedbackValidation;
    }

    @Override
    public Optional<FeedbackDTO> getFeedbackById(Long id) {
        return Optional.ofNullable(feedbackRepository.findById(id)
                .map(FeedbackMapper::toDTO)
                .orElseThrow(()-> new NotFoundException(
                        String.format(ErrorMessages.FEEDBACK_ID_REQUIRED, id))));
    }

    @Override
    public List<FeedbackDTO> getAllFeedbackByConsultantId(Long id) {
        return feedbackRepository.findByConsultantEntity_Id(id)
                .stream()
                .map(FeedbackMapper::toDTO)
                .toList();
    }

    @Override
    public List<FeedbackDTO> getAllFeedbackByClientId(Long id) {
        return feedbackRepository.findByClientEntity_Id(id)
                .stream()
                .map(FeedbackMapper::toDTO)
                .toList();
    }

    @Override
    public FeedbackDTO createFeedback(FeedbackDTO feedbackDTO) {
         feedbackValidation.validateFeedbackDTO(feedbackDTO.getRating(), feedbackDTO.getComment());
         FeedbackEntity feedbackEntity = FeedbackMapper.toEntity(feedbackDTO);

        return FeedbackMapper.toDTO(feedbackRepository.save(feedbackEntity));
    }

    @Override
    public FeedbackDTO updateFeedback(Long id, FeedbackDTO feedbackDTO) {
        FeedbackEntity existFeedback = feedbackRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(
                        String.format(ErrorMessages.FEEDBACK_ID_REQUIRED, id)));

        feedbackValidation.validateFeedbackDTO(feedbackDTO.getRating(), feedbackDTO.getComment());

        existFeedback.setRating(feedbackDTO.getRating());
        existFeedback.setComment(feedbackDTO.getComment());

        return FeedbackMapper.toDTO(feedbackRepository.save(existFeedback));
    }

    //TODO id à vérifier
    @Override
    public ApiResponse deleteFeedback(Long id) {
        feedbackValidation.feedbackIdIsExit(id);
        feedbackRepository.deleteById(id);

        return new ApiResponse(
                HttpStatus.OK,
                "200",
                SuccesMessages.FEEDBACK_DELETE_WITH_SUCCESS
        );
    }

}
