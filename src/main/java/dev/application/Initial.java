package dev.application;

import dev.application.models.entities.UserEntity;
import dev.application.models.entities.auth.BaseRoleEntity;
import dev.application.models.entities.auth.RoleUserEntity;
import dev.application.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class Initial {
    @Autowired
    private UserRepo userRepo;

    public void initial() {
        UserEntity user = new UserEntity(true, "user1", "1234", Collections.singleton(new RoleUserEntity("user1", BaseRoleEntity.USER)));
        if (userRepo.findByUsername(user.getUsername()) == null) {
            userRepo.save(user);
        }
    }
}
