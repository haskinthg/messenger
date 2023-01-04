package dev.application.controllers;

import dev.application.models.RegisterStatus;
import dev.application.models.entities.UserEntity;
import dev.application.models.entities.auth.BaseRoleEntity;
import dev.application.models.entities.auth.BaseUserEntity;
import dev.application.models.entities.auth.RoleUserEntity;
import dev.application.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class RegistrationController {

    @Autowired
    public UserRepo userRepo;

    @PostMapping(value = "register")
    public RegisterStatus register(@RequestBody BaseUserEntity user) throws Exception {
        log.warn(" user: " + user.getUsername() + " " + user.getPassword());
        if (user.getPassword() != null && user.getPassword() != null) {
            if (userRepo.findByUsername(user.getUsername()) == null) {
                userRepo.save(new UserEntity(true, user.getUsername(), user.getPassword(), Collections.singleton(new RoleUserEntity(user.getUsername(), BaseRoleEntity.USER))));
                log.warn("registered new user: " + user.getUsername());
                return RegisterStatus.REG;
            } else return RegisterStatus.CONTAIN;
        } else return RegisterStatus.ERROR;
    }
}
