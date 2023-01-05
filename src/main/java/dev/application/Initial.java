package dev.application;

import dev.application.models.ChatStatus;
import dev.application.models.entities.ChatEntity;
import dev.application.models.entities.MessageEntity;
import dev.application.models.entities.UserEntity;
import dev.application.models.entities.auth.BaseRoleEntity;
import dev.application.models.entities.auth.RoleUserEntity;
import dev.application.repositories.ChatRepo;
import dev.application.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;

@Component
public class Initial {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ChatRepo chatRepo;

    public void initial() {
        UserEntity user1 = new UserEntity(true, "user1", "1234", Collections.singleton(new RoleUserEntity("user1", BaseRoleEntity.USER)));
        UserEntity user2 = new UserEntity(true, "user2", "1234", Collections.singleton(new RoleUserEntity("user2", BaseRoleEntity.USER)));
        userRepo.save(user1);
        userRepo.save(user2);
        ChatEntity chat = new ChatEntity();
        chat.setChatStatus(ChatStatus.CREATED);
//        Set<UserEntity> s = Set.of(user1,user2);
//        chat.setUsers(s);
        chatRepo.save(chat);
        user1.setChats(Collections.singleton(chat));
        user2.setChats(Collections.singleton(chat));
        userRepo.save(user1);
        userRepo.save(user2);
    }
}
