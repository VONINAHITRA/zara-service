package com.zara.zaraservice.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackDTO {
    private Long id;

    private Long consultantId;

    private Long clientId;

    private Integer rating;

    private String comment;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;
}
