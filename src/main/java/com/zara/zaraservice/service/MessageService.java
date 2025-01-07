package com.zara.zaraservice.service;

import com.zara.zaraservice.dto.MessageDTO;
import com.zara.zaraservice.entity.MessageEntity;
import com.zara.zaraservice.enums.MessageStatus;

import java.util.List;

public interface MessageService {

    List<MessageDTO> getMessages(Long userId1, Long userId2);

    MessageDTO sendMessage(MessageDTO messageDTO);

    MessageDTO readMessage(Long messageId);
}
