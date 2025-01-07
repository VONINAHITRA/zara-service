package com.zara.zaraservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "feedbacks")
public class FeedbackEntity extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "consultant_id", nullable = false)
    private ConsultantEntity consultantEntity;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEntity clientEntity;

    @Column(nullable = false)
    private Integer rating; // Note sur 5

    @Column
    private String comment;

}
