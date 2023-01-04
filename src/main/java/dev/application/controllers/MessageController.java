package dev.application.controllers;

import dev.application.models.dto.MessageDTO;
import dev.application.models.entities.MessageEntity;
import dev.application.models.entities.UserEntity;
import dev.application.models.websocket.WebSocketObject;
import dev.application.repositories.MessageRepo;
import dev.application.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("api/messages")
public class MessageController {

    @Autowired
    private MessageService service;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/conversation")
    public void sendMessage(WebSocketObject<MessageDTO> msg) throws Exception {
        service.addMessage(msg.getContent());
        messagingTemplate.convertAndSend("user" + msg.getContent().getUsernameTo() + "/messages", msg.getContent());
    }


    @GetMapping("/user/{id}")
    public Set<MessageDTO> allByUserId(@PathVariable("id") long id) throws Exception {
        return service.allByUserId(id);
    }
}
