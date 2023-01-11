package dev.application.controllers;

import dev.application.models.dto.UserDTO;
import dev.application.repositories.UserRepo;
import dev.application.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{filter}")
    private Set<UserDTO> getUsers(@PathVariable("filter") String filterUsername) {
        return userService.getUsersByFilter(filterUsername);
    }
}
