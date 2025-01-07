package com.zara.zaraservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "formations")
public class FormationEntity extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "consultant_id", nullable = false)
    private ConsultantEntity consultantEntity;

    @Column(nullable = false)
    private String establishment;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @Column
    private LocalDate dateObtained;
}
