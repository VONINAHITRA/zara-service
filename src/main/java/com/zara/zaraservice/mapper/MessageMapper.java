package com.zara.zaraservice.mapper;

import com.zara.zaraservice.dto.MessageDTO;
import com.zara.zaraservice.entity.MessageEntity;
import com.zara.zaraservice.entity.UserEntity;
import com.zara.zaraservice.enums.MessageStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MessageMapper {

    public static MessageEntity toEntity(MessageDTO messageDTO, UserEntity sender, UserEntity receiver) {
        if (messageDTO == null || sender == null || receiver == null) {
            throw new IllegalArgumentException("MessageDTO, sender ou receiver ne peut pas être null");
        }
        return MessageEntity.builder()
                .sender(sender)
                .receiver(receiver)
                .content(messageDTO.getContent())
                .status(MessageStatus.SENT)
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static MessageDTO toDTO(MessageEntity messageEntity) {
        if (messageEntity == null) {
            throw new IllegalArgumentException("MessageEntity ne peut pas être null");
        }
        return MessageDTO.builder()
                .id(messageEntity.getId())
                .senderId(messageEntity.getSender().getId())
                .receiverId(messageEntity.getReceiver().getId())
                .content(messageEntity.getContent())
                .status(messageEntity.getStatus().name())
                .timestamp(messageEntity.getTimestamp())
                .build();
    }
}

