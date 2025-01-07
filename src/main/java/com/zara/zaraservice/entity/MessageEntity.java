package com.zara.zaraservice.entity;

import com.zara.zaraservice.enums.MessageStatus;
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
@Table(name = "messages")
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserEntity sender; // Expéditeur

    @ManyToOne
    private UserEntity receiver; // Destinataire

    @Column(nullable = false)
    private String content; // Contenu du message

    @Enumerated(EnumType.STRING)
    private MessageStatus status = MessageStatus.SENT; // Statut du message

    private LocalDateTime timestamp = LocalDateTime.now(); // Date d'envoi
}