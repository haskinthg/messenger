package dev.application.services;

import dev.application.models.ChatStatus;
import dev.application.models.MessageStatus;
import dev.application.models.dto.ChatDTO;
import dev.application.models.dto.MessageDTO;
import dev.application.models.dto.UserDTO;
import dev.application.models.entities.ChatEntity;
import dev.application.models.entities.UserEntity;
import dev.application.repositories.ChatRepo;
import dev.application.repositories.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ChatService {

    @Autowired
    private ChatRepo chatRepo;

    @Autowired
    private UserRepo userRepo;

    public List<ChatDTO> getChatForUser(String username) throws Exception {
//        return chatRepo.findByUsers_Username(username).stream().map(ChatDTO::new).collect(Collectors.toSet());
        return chatRepo.findByChatStatusNotAndUsers_Username(ChatStatus.DELETED,username).stream().map(ChatDTO::new)
                .sorted(Comparator.nullsFirst(Comparator.comparing(ChatDTO::getDateLast)).reversed())
                .collect(Collectors.toList());

    }

    //    @Transactional
    public ChatDTO addChat(ChatDTO chatDTO) throws Exception {
        ChatEntity chatEntity = new ChatEntity();
        for (UserDTO userDTO : chatDTO.getUsers()) {
            chatEntity.addUser(userRepo.findByUsername(userDTO.getUsername()));
        }
        return new ChatDTO(chatRepo.save(chatEntity));
    }

    public void delChat(ChatDTO chatDT0) throws Exception {
        ChatEntity chat = chatRepo.findById(chatDT0.getId()).get();
        chat.setChatStatus(ChatStatus.DELETED);
        this.chatRepo.save(chat);
    }


    public ChatDTO findBy2UsersOrCreate(String username1, String username2) throws Exception {
        ChatEntity chatEntity = chatRepo.findBy2Usernames(username1, username2);
        if (chatEntity == null) {
            chatEntity = new ChatEntity();
            chatEntity.setUsers(new HashSet<UserEntity>());
            chatEntity.addUser(userRepo.findByUsername(username1));
            chatEntity.addUser(userRepo.findByUsername(username2));
            chatEntity.setChatStatus(ChatStatus.CREATED);
            return new ChatDTO(chatRepo.save(chatEntity));
        } else {
            return new ChatDTO(chatEntity);
        }
    }
}
