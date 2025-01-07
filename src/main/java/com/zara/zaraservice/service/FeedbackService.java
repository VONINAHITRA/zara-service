package com.zara.zaraservice.service;

import com.zara.zaraservice.dto.ExperienceDTO;
import com.zara.zaraservice.dto.FeedbackDTO;
import com.zara.zaraservice.dto.FormationDTO;
import com.zara.zaraservice.dto.responses.ApiResponse;

import java.util.List;
import java.util.Optional;

public interface FeedbackService {
    FeedbackDTO createFeedback(FeedbackDTO feedbackDTO);

    List<FeedbackDTO> getAllFeedbackByConsultantId(Long id);

    List<FeedbackDTO> getAllFeedbackByClientId(Long id);

    Optional<FeedbackDTO> getFeedbackById(Long id);

    FeedbackDTO updateFeedback(Long id, FeedbackDTO feedbackDTO);

    ApiResponse deleteFeedback(Long id);
}
