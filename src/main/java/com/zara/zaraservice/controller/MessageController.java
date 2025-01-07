package com.zara.zaraservice.controller;

import com.zara.zaraservice.dto.MessageDTO;
import com.zara.zaraservice.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController( MessageService messageService) {
        this.messageService = messageService;
    }

    // Envoyer un message
    @PostMapping("/send")
    @SendTo("/topic/messages")
    public MessageDTO sendMessage(@RequestBody MessageDTO messageDTO) {
        return messageService.sendMessage(messageDTO);//ResponseEntity.ok(savedMessage);
    }

    // Récupérer les messages entre deux utilisateurs
    @GetMapping("/{userId1}/{userId2}")
    public ResponseEntity<List<MessageDTO>> getMessages(@PathVariable Long userId1, @PathVariable Long userId2) {
        List<MessageDTO> messages = messageService.getMessages(userId1, userId2);
        return ResponseEntity.ok(messages);
    }

    // Endpoint pour lire un message et mettre à jour son statut
    @PatchMapping("/{messageId}/read")
    public ResponseEntity<MessageDTO> readMessage(@PathVariable Long messageId) {
        MessageDTO updatedMessage = messageService.readMessage(messageId);
        return ResponseEntity.ok(updatedMessage);
    }

}
