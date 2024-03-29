package dev.application.controllers;

import dev.application.models.dto.MessageDTO;
import dev.application.models.entities.MessageEntity;
import dev.application.models.entities.UserEntity;
import dev.application.models.websocket.WebSocketObject;
import dev.application.models.websocket.WebSocketType;
import dev.application.repositories.MessageRepo;
import dev.application.services.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/messages")
@Slf4j
public class MessageController {

    @Autowired
    private MessageService service;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/conversation")
    public void sendMessage(@Payload WebSocketObject<MessageDTO> msg) throws Exception {
        MessageDTO res = new MessageDTO();
        if(msg.getType() == WebSocketType.ADD)
            res = service.addMessage(msg.getContent());
        else if(msg.getType() == WebSocketType.DELETE) {
            res = service.deleteMessage(msg.getContent());
        }
        WebSocketObject<MessageDTO> sending = new WebSocketObject<MessageDTO>(msg.getType(), res);
        messagingTemplate.convertAndSend("/user/" + msg.getContent().getUsernameTo() + "/messages", sending);
        messagingTemplate.convertAndSend("/user/" + msg.getContent().getUsernameFrom() + "/messages", sending);
    }

    @GetMapping("/chat/{id}/{page}/{size}")
    public List<MessageDTO> allByChatId(@PathVariable("id") long id,
                                        @PathVariable("page") int page,
                                        @PathVariable("size") int size) throws Exception {
        return service.pageByChatId(id, page, size);
    }

    @GetMapping("/chat/{id}")
    public Long count( @PathVariable("id") long id) throws Exception {
        return service.CountByChatId(id);
    }
}
