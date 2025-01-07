package com.zara.zaraservice.dto;

import com.zara.zaraservice.enums.MessageStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {
    private Long id;
    private Long senderId;     // ID de l'expéditeur
    private Long receiverId;   // ID du destinataire
    private String content;    // Contenu du message
    private String status;     // Statut du message (SENT, RECEIVED, READ)
    private LocalDateTime timestamp; // Date et heure de l'envoi

}
