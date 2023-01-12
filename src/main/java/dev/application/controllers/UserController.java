package dev.application.controllers;

import dev.application.models.dto.UserDTO;
import dev.application.repositories.UserRepo;
import dev.application.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getUser/{username}")
    private UserDTO getUser(@PathVariable("username") String username) throws Exception {
        return userService.getUserByUsername(username);
    }

    @PostMapping(value="update", produces = MediaType.APPLICATION_JSON_VALUE)
    private UserDTO update(@RequestBody UserDTO user) throws Exception{
        return userService.updateUser(user);
    }
}
