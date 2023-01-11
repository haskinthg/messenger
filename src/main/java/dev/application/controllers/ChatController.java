package dev.application.controllers;

import dev.application.models.dto.ChatDTO;
import dev.application.models.websocket.WebSocketObject;
import dev.application.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("api/chats")
public class ChatController {

    @Autowired
    private ChatService service;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping
    public void sendMessage(WebSocketObject<ChatDTO> msg) throws Exception {

    }

    @GetMapping("/{username}")
    public Set<ChatDTO> chat(@PathVariable("username") String username) throws Exception {
        return service.getChatForUser(username);
    }

    @GetMapping("/{u1}/{u2}")
    public ChatDTO getChat(@PathVariable("u1") String u1, @PathVariable("u2") String u2) throws Exception{
        return service.findBy2UsersOrCreate(u1,u2);
    }

}
