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
        UserEntity user3 = new UserEntity(true, "user3", "1234", Collections.singleton(new RoleUserEntity("user3", BaseRoleEntity.USER)));
        UserEntity user4 = new UserEntity(true, "user4", "1234", Collections.singleton(new RoleUserEntity("user4", BaseRoleEntity.USER)));
        UserEntity user5 = new UserEntity(true, "user5", "1234", Collections.singleton(new RoleUserEntity("user5", BaseRoleEntity.USER)));
        UserEntity user6 = new UserEntity(true, "user6", "1234", Collections.singleton(new RoleUserEntity("user6", BaseRoleEntity.USER)));
        UserEntity user7 = new UserEntity(true, "user7", "1234", Collections.singleton(new RoleUserEntity("user7", BaseRoleEntity.USER)));
        UserEntity user8 = new UserEntity(true, "user8", "1234", Collections.singleton(new RoleUserEntity("user8", BaseRoleEntity.USER)));
        userRepo.save(user1);
        userRepo.save(user2);
        userRepo.save(user3);
        userRepo.save(user4);
        userRepo.save(user5);
        userRepo.save(user6);
        userRepo.save(user7);
        userRepo.save(user8);
        ChatEntity chat1 = new ChatEntity();
        ChatEntity chat2 = new ChatEntity();
        ChatEntity chat3 = new ChatEntity();
        ChatEntity chat4 = new ChatEntity();
        chat1.setChatStatus(ChatStatus.CREATED);
        chat2.setChatStatus(ChatStatus.CREATED);
        chat3.setChatStatus(ChatStatus.CREATED);
        chat4.setChatStatus(ChatStatus.CREATED);
        chatRepo.save(chat1);
        chatRepo.save(chat3);
        chatRepo.save(chat4);
        chatRepo.save(chat2);
        user1.setChats(Collections.singleton(chat1));
        user2.setChats(Collections.singleton(chat1));
        user3.setChats(Collections.singleton(chat2));
        user4.setChats(Collections.singleton(chat2));
        user5.setChats(Collections.singleton(chat3));
        user6.setChats(Collections.singleton(chat3));
        user7.setChats(Collections.singleton(chat4));
        user8.setChats(Collections.singleton(chat4));
        userRepo.save(user1);
        userRepo.save(user2);
        userRepo.save(user3);
        userRepo.save(user4);
        userRepo.save(user5);
        userRepo.save(user6);
        userRepo.save(user7);
        userRepo.save(user8);
    }
}
