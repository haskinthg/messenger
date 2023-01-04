package dev.application.services;

import dev.application.models.dto.ChatDTO;
import dev.application.models.dto.MessageDTO;
import dev.application.models.dto.UserDTO;
import dev.application.models.entities.ChatEntity;
import dev.application.models.entities.UserEntity;
import dev.application.repositories.ChatRepo;
import dev.application.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
@Service
public class ChatService {

    @Autowired
    private ChatRepo chatRepo;

    @Autowired
    private UserRepo userRepo;

    public Set<ChatDTO> getChatForUser(String username) {

        return chatRepo.findByUsers_Username(username).stream().map(chat -> new ChatDTO(chat.getId(),
                chat.getUsers().stream().map(UserDTO::new).collect(Collectors.toSet()),
                chat.getMessages().stream().map(MessageDTO::new).collect(Collectors.toSet()),
                chat.getChatStatus())).collect(Collectors.toSet());
    }

    public ChatDTO addChat(ChatDTO chatDTO) {
        ChatEntity chatEntity = new ChatEntity();
        for (UserDTO userDTO: chatDTO.getUsers()) {
            chatEntity.addUser(userRepo.findByUsername(userDTO.getUsername()));
        }
        return new ChatDTO(chatRepo.save(chatEntity));
    }
}
