package com.zara.zaraservice.validation;

import com.zara.zaraservice.constants.ErrorMessages;
import com.zara.zaraservice.exception.customexceptions.NotFoundException;
import com.zara.zaraservice.repository.FeedbackRepository;
import org.springframework.stereotype.Component;

@Component
public class FeedbackValidation {

    private final FeedbackRepository feedbackRepository;

    public FeedbackValidation(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public void validateFeedbackDTO(Integer rating, String comment ){
        if(isNullOrEmpty(comment)){
            throw new IllegalArgumentException(ErrorMessages.COMMENT_REQUIRED );
         }

        if (rating == null || rating < 1 || rating > 5) {
            throw new IllegalArgumentException(ErrorMessages.RATING_RANGE);
        }
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    public void feedbackIdIsExit(Long feedBackId) {
        if (feedBackId == null || !feedbackRepository.existsById(feedBackId)) {
            throw new NotFoundException(
                    String.format(ErrorMessages.FEEDBACK_ID_REQUIRED, feedBackId)
            );
        }
    }
}
