package dev.application.services;

import dev.application.models.dto.UserDTO;
import dev.application.models.entities.UserEntity;
import dev.application.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public Set<UserDTO> getUsersByFilter(String filter) {
        return userRepo.findByFilter(filter).stream().map(UserDTO::new).collect(Collectors.toSet());
    }

    public UserDTO getUserByUsername(String username) throws Exception{
        return new UserDTO(userRepo.findByUsername(username));
    }

    public UserDTO updateUser(UserDTO user)throws Exception {
        UserEntity ue = userRepo.findById(user.getId()).get();
        ue.setPhoto(user.getPhoto());
        return new UserDTO(userRepo.save(ue));
    }
}
