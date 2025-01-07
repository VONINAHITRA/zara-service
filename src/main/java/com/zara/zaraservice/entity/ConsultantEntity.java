package com.zara.zaraservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "consultants")
public class ConsultantEntity extends AbstractEntity {

    @Column
    private int interventionsCount;

    @Column(nullable = false)
    private Boolean isAvailable = true; ;

    @Column
    private String localisation;

    @Column
    private String specialization;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @OneToMany(mappedBy = "consultantEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InterventionEntity> interventionEntities;

    @OneToMany(mappedBy = "consultantEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FeedbackEntity> feedbackEntities;

    @OneToMany(mappedBy = "consultantEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FormationEntity> formationEntities;

    @OneToMany(mappedBy = "consultantEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExperienceEntity> experienceEntities;
}
