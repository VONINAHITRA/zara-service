package com.zara.zaraservice.service.impl;

import com.zara.zaraservice.dto.MessageDTO;
import com.zara.zaraservice.entity.MessageEntity;
import com.zara.zaraservice.entity.UserEntity;
import com.zara.zaraservice.enums.MessageStatus;
import com.zara.zaraservice.mapper.MessageMapper;
import com.zara.zaraservice.mapper.UserMapper;
import com.zara.zaraservice.repository.MessageRepository;
import com.zara.zaraservice.repository.UserRepository;
import com.zara.zaraservice.service.MessageService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final MessageMapper messageMapper;
    private final UserMapper userMapper;

    public MessageServiceImpl(MessageRepository messageRepository,
                              UserRepository userRepository,
                              MessageMapper messageMapper,  UserMapper userMapper) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.messageMapper = messageMapper;
        this.userMapper = userMapper;
    }

    @Override
    public MessageDTO sendMessage(MessageDTO messageDTO) {
        // Vérifiez que les IDs de l'expéditeur et du destinataire sont valides
        
        // Récupérer l'expéditeur
        UserEntity sender = userRepository.findById(messageDTO.getSenderId())
                .orElseThrow(() -> new RuntimeException(
                        String.format("Expéditeur introuvable avec l'ID: %d", messageDTO.getSenderId())));

        // Récupérer le destinataire
        UserEntity receiver = userRepository.findById(messageDTO.getReceiverId())
                .orElseThrow(() -> new RuntimeException(
                        String.format("Destinataire introuvable avec l'ID: %d", messageDTO.getReceiverId())));

        // Créer l'entité du message
        MessageEntity message = MessageEntity.builder()
                .sender(sender)                // Association avec l'expéditeur
                .receiver(receiver)            // Association avec le destinataire
                .content(messageDTO.getContent())  // Contenu du message
                .status(MessageStatus.SENT)    // Statut par défaut
                .timestamp(LocalDateTime.now()) // Date d'envoi
                .build();

        // Sauvegarder dans la base
        MessageEntity savedMessage = messageRepository.save(message);

        // Retourner le DTO du message sauvegardé
        return MessageDTO.builder()
                .id(savedMessage.getId())
                .senderId(savedMessage.getSender().getId())
                .receiverId(savedMessage.getReceiver().getId())
                .content(savedMessage.getContent())
                .status(savedMessage.getStatus().name())
                .timestamp(savedMessage.getTimestamp())
                .build();
    }


    @Override
    public MessageDTO readMessage(Long messageId) {
        // Récupérer le message par ID
        MessageEntity message = messageRepository.findById(messageId)
                .orElseThrow(() -> new RuntimeException(
                        String.format("Message introuvable avec l'ID: %d", messageId)));

        // Mettre à jour le statut à 'READ'
        message.setStatus(MessageStatus.READ);

        // Sauvegarder les modifications
        MessageEntity updatedMessage = messageRepository.save(message);

        // Retourner le message mis à jour sous forme de DTO
        return MessageDTO.builder()
                .id(updatedMessage.getId())
                .senderId(updatedMessage.getSender().getId())
                .receiverId(updatedMessage.getReceiver().getId())
                .content(updatedMessage.getContent())
                .status(updatedMessage.getStatus().name())
                .timestamp(updatedMessage.getTimestamp())
                .build();
    }


    // Récupération des messages entre deux utilisateurs
    public List<MessageDTO> getMessages(Long userId1, Long userId2) {
        List<MessageEntity> messages = messageRepository.findMessagesBetweenUsers(userId1, userId2);

        // Convertir les entités en DTOs
        return messages.stream()
                .map(MessageMapper::toDTO)
                .collect(Collectors.toList());
    }

}
