package dev.application.controllers;

import dev.application.models.dto.UserDTO;
import dev.application.repositories.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("all")
    private Set<UserDTO> getUsers() {
//        return userRepo.findAll().stream().map(u -> new User(u.getId(), u.getUsername(), u.getPhoto())).collect(Collectors.toSet());
        return null;
    }
}
