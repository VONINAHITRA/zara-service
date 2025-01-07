package com.zara.zaraservice.mapper;

import com.zara.zaraservice.dto.FeedbackDTO;
import com.zara.zaraservice.entity.FeedbackEntity;
import com.zara.zaraservice.entity.ConsultantEntity;
import com.zara.zaraservice.entity.ClientEntity;

import java.util.List;
import java.util.stream.Collectors;

public class FeedbackMapper {

    public static FeedbackDTO toDTO(FeedbackEntity feedback) {
        if (feedback == null) {
            return null;
        }

        return FeedbackDTO.builder()
                .id(feedback.getId())
                .consultantId(
                        feedback.getConsultantEntity() != null ? feedback.getConsultantEntity().getId() : null
                )
                .clientId(
                        feedback.getClientEntity() != null ? feedback.getClientEntity().getId() : null
                )
                .rating(feedback.getRating())
                .comment(feedback.getComment())
                .createdDate(feedback.getCreatedDate())
                .lastModifiedDate(feedback.getLastModifiedDate())
                .build();
    }

    public static FeedbackEntity toEntity(FeedbackDTO feedbackDTO) {
        if (feedbackDTO == null) {
            return null;
        }

        return FeedbackEntity.builder()
                .id(feedbackDTO.getId())
                .consultantEntity(
                        feedbackDTO.getConsultantId() != null
                                ? ConsultantEntity.builder().id(feedbackDTO.getConsultantId()).build()
                                : null
                )
                .clientEntity(
                        feedbackDTO.getClientId() != null
                                ? ClientEntity.builder().id(feedbackDTO.getClientId()).build()
                                : null
                )
                .rating(feedbackDTO.getRating())
                .comment(feedbackDTO.getComment())
                .createdDate(feedbackDTO.getCreatedDate())
                .lastModifiedDate(feedbackDTO.getLastModifiedDate())
                .build();
    }

    public static List<FeedbackDTO> toDTOList(List<FeedbackEntity> feedbacks) {
        if (feedbacks == null) {
            return null;
        }

        return feedbacks.stream()
                .map(FeedbackMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static List<FeedbackEntity> toEntityList(List<FeedbackDTO> feedbackDTOs) {
        if (feedbackDTOs == null) {
            return null;
        }

        return feedbackDTOs.stream()
                .map(FeedbackMapper::toEntity)
                .collect(Collectors.toList());
    }
}
