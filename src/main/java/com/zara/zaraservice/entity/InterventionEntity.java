package com.zara.zaraservice.entity;

import com.zara.zaraservice.enums.Role;
import com.zara.zaraservice.enums.StatusRDV;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;


@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "interventions")
public class InterventionEntity extends AbstractEntity {

    @Column
    private String localisation;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusRDV statusRDV;

    @ManyToOne
    @JoinColumn(name = "consultant_id", nullable = false)
    private ConsultantEntity consultantEntity;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEntity clientEntity;

    @Column(nullable = false)
    private LocalDateTime interventionDate;

    @Column
    private String description;
}
