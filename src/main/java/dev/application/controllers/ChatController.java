package dev.application.controllers;

import dev.application.models.dto.ChatDTO;
import dev.application.models.dto.UserDTO;
import dev.application.models.websocket.WebSocketObject;
import dev.application.models.websocket.WebSocketType;
import dev.application.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/chats")
public class ChatController {

    @Autowired
    private ChatService service;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;


    public void sendMessage(ChatDTO chat, WebSocketType type) throws Exception {
        WebSocketObject<ChatDTO> msg = new WebSocketObject<ChatDTO>(type, chat);
        for (UserDTO user: msg.getContent().getUsers()) {
            simpMessagingTemplate.convertAndSend("/user/" + user.getUsername() + "/chats", msg);
        }
    }


    @MessageMapping("/chatmsg")
    public void msg(@Payload WebSocketObject<ChatDTO> chatmsg) throws Exception {
        service.delChat(chatmsg.getContent());
    }

    @GetMapping("/{username}")
    public List<ChatDTO> chat(@PathVariable("username") String username) throws Exception {
        return service.getChatForUser(username);
    }

    @GetMapping("/{u1}/{u2}")
    public ChatDTO getChat(@PathVariable("u1") String u1, @PathVariable("u2") String u2) throws Exception{
        ChatDTO chat = service.findBy2UsersOrCreate(u1,u2);
        sendMessage(chat, WebSocketType.ADD);
        return chat;
    }

}
