package dev.application.services;

import dev.application.models.ChatStatus;
import dev.application.models.dto.ChatDTO;
import dev.application.models.dto.MessageDTO;
import dev.application.models.dto.UserDTO;
import dev.application.models.entities.ChatEntity;
import dev.application.models.entities.UserEntity;
import dev.application.repositories.ChatRepo;
import dev.application.repositories.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
@Service
@Slf4j
public class ChatService {

    @Autowired
    private ChatRepo chatRepo;

    @Autowired
    private UserRepo userRepo;

    public Set<ChatDTO> getChatForUser(String username) {
        return chatRepo.findByUsers_Username(username).stream().map(ChatDTO::new).collect(Collectors.toSet());
    }

    public ChatDTO addChat(ChatDTO chatDTO) {
        ChatEntity chatEntity = new ChatEntity();
        for (UserDTO userDTO: chatDTO.getUsers()) {
            chatEntity.addUser(userRepo.findByUsername(userDTO.getUsername()));
        }
        return new ChatDTO(chatRepo.save(chatEntity));
    }


    public ChatDTO findBy2UsersOrCreate(String username1, String username2){
        ChatEntity chatEntity = chatRepo.findBy2Usernames(username1, username2);
        if(chatEntity==null) {
            chatEntity = new ChatEntity();
            chatEntity.setUsers(new HashSet<UserEntity>());
            chatEntity.addUser(userRepo.findByUsername(username1));
            chatEntity.addUser(userRepo.findByUsername(username2));
            chatEntity.setChatStatus(ChatStatus.CREATED);
            return new ChatDTO(chatRepo.save(chatEntity));
        }
        else {
            return new ChatDTO(chatEntity);
        }
    }
}
